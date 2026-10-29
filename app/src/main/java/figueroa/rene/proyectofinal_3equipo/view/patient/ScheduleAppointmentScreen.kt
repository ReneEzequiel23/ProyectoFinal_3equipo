
package figueroa.rene.proyectofinal_3equipo.view.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import figueroa.rene.proyectofinal_3equipo.model.Appointment
import java.time.LocalDate

private val ScheduleNavy = Color(0xFF164F80)
private val ScheduleBackground = Color(0xFFF5F8FC)

@Composable
fun ScheduleAppointmentScreen(
    onBack: () -> Unit,
    onAppointmentConfirmed: (Appointment) -> Unit
) {
    val specialties = listOf(
        "Medicina general",
        "Cardiología",
        "Dermatología",
        "Nutrición"
    )

    val doctors = mapOf(
        "Medicina general" to listOf("Dr. Ricardo Peralta Soto"),
        "Cardiología" to listOf("Dra. Mariana Valdez Ruiz"),
        "Dermatología" to listOf("Dr. Luis Fernando Ochoa"),
        "Nutrición" to listOf("Lic. Andrea Camacho Félix")
    )

    val dates = listOf(
        "29 de octubre",
        "30 de octubre",
        "31 de octubre"
    )

    val times = listOf(
        "9:00 a.m.",
        "10:30 a.m.",
        "12:00 p.m.",
        "4:00 p.m."
    )

    var selectedSpecialty by remember {
        mutableStateOf(specialties[0])
    }
    var selectedDoctor by remember {
        mutableStateOf(doctors[specialties[0]]!![0])
    }
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var showConfirmation by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ScheduleBackground)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("← Regresar a mis citas")
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Agendar cita médica",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = ScheduleNavy
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Selecciona la especialidad, el médico y el horario de tu consulta.",
            color = Color(0xFF627795),
            fontSize = 15.sp
        )

        Spacer(Modifier.height(24.dp))

        ScheduleSection(title = "1. Especialidad") {
            specialties.forEach { specialty ->
                SelectionOption(
                    text = specialty,
                    selected = selectedSpecialty == specialty,
                    onClick = {
                        selectedSpecialty = specialty
                        selectedDoctor = doctors[specialty]!![0]
                    }
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        ScheduleSection(title = "2. Médico") {
            doctors[selectedSpecialty]?.forEach { doctor ->
                SelectionOption(
                    text = doctor,
                    selected = selectedDoctor == doctor,
                    onClick = { selectedDoctor = doctor }
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        ScheduleSection(title = "3. Fecha disponible") {
            dates.forEach { date ->
                SelectionOption(
                    text = date,
                    selected = selectedDate == date,
                    onClick = { selectedDate = date }
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        ScheduleSection(title = "4. Horario disponible") {
            times.forEach { time ->
                SelectionOption(
                    text = time,
                    selected = selectedTime == time,
                    onClick = { selectedTime = time }
                )
            }
        }

        Spacer(Modifier.height(28.dp))

        Button(
            onClick = { showConfirmation = true },
            enabled = selectedDate.isNotBlank() &&
                    selectedTime.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ScheduleNavy
            ),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("Confirmar cita")
        }

        Spacer(Modifier.height(16.dp))
    }

    if (showConfirmation) {
        AlertDialog(
            onDismissRequest = { showConfirmation = false },
            title = { Text("Confirmar cita") },
            text = {
                Text(
                    "Especialidad: $selectedSpecialty\n" +
                            "Médico: $selectedDoctor\n" +
                            "Fecha: $selectedDate\n" +
                            "Horario: $selectedTime\n\n" +
                            "¿Deseas confirmar esta cita de prueba?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val appointment = Appointment(
                            date = when (selectedDate) {
                                "29 de octubre" -> LocalDate.of(2026, 10, 29)
                                "30 de octubre" -> LocalDate.of(2026, 10, 30)
                                else -> LocalDate.of(2026, 10, 31)
                            },
                            time = selectedTime,
                            duration = "20 min",
                            doctor = selectedDoctor,
                            specialty = selectedSpecialty,
                            initials = selectedDoctor
                                .split(" ")
                                .mapNotNull { it.firstOrNull()?.takeIf(Char::isUpperCase) }
                                .takeLast(2)
                                .joinToString(""),
                            type = "Consulta general",
                            price = "$350 MXN",
                            status = "Confirmada",
                            location = "Consultorio presencial"
                        )

                        showConfirmation = false
                        onAppointmentConfirmed(appointment)
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showConfirmation = false }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
private fun ScheduleSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF202D42)
        )

        Spacer(Modifier.height(10.dp))

        content()
    }
}

@Composable
private fun SelectionOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val background = if (selected) {
        Color(0xFFE2EFFA)
    } else {
        Color.White
    }

    val borderColor = if (selected) {
        ScheduleNavy
    } else {
        Color(0xFFDCE5F0)
    }

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = background
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            borderColor
        )
    ) {
        Row(
            modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                color = Color(0xFF202D42),
                fontWeight = if (selected) {
                    FontWeight.SemiBold
                } else {
                    FontWeight.Normal
                }
            )

            if (selected) {
                Text(
                    text = "✓",
                    color = ScheduleNavy,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
