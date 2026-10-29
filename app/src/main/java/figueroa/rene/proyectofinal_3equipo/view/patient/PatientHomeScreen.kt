
package figueroa.rene.proyectofinal_3equipo.view.patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF164F80)
private val Teal = Color(0xFF107B91)
private val PageBackground = Color(0xFFF5F8FC)
private val MainText = Color(0xFF202D42)
private val SecondaryText = Color(0xFF627795)
private val BorderColor = Color(0xFFDCE5F0)
private val ConfirmedColor = Color(0xFFD9F4EC)
private val PendingColor = Color(0xFFFFEDD4)

private data class Appointment(
    val day: String,
    val date: String,
    val time: String,
    val duration: String,
    val doctor: String,
    val specialty: String,
    val initials: String,
    val type: String,
    val price: String,
    val status: String,
    val location: String
)

@Composable
fun PatientHomeScreen(
    patientName: String = "María",
    onProfile: () -> Unit = {},
    onScheduleAppointment: () -> Unit = {},
    onHistory: () -> Unit = {},
    onAppointmentDetails: (String) -> Unit = {},
    onRateDoctor: (String) -> Unit = {}
) {
    val appointments = remember {
        listOf(
            Appointment(
                day = "Lun",
                date = "19 oct",
                time = "10:30 a.m.",
                duration = "20 min",
                doctor = "Dra. Mariana Valdez Ruiz",
                specialty = "Cardiología",
                initials = "MV",
                type = "Cita de seguimiento",
                price = "$500 MXN",
                status = "Confirmada",
                location = "Consultorio 204, Torre Médica, Calle 5 de Febrero 818, Cd. Obregón"
            ),
            Appointment(
                day = "Mié",
                date = "21 oct",
                time = "4:00 p.m.",
                duration = "15 min",
                doctor = "Dr. Luis Fernando Ochoa",
                specialty = "Dermatología",
                initials = "LO",
                type = "Consulta general",
                price = "$200 MXN",
                status = "Pendiente",
                location = "Consulta virtual"
            ),
            Appointment(
                day = "Mar",
                date = "27 oct",
                time = "12:15 p.m.",
                duration = "30 min",
                doctor = "Lic. Andrea Camacho Félix",
                specialty = "Nutrición",
                initials = "AC",
                type = "Primera consulta",
                price = "$300 MXN",
                status = "Pendiente",
                location = "Consulta virtual"
            ),
            Appointment(
                day = "Vie",
                date = "30 oct",
                time = "9:00 a.m.",
                duration = "20 min",
                doctor = "Dr. Ricardo Peralta Soto",
                specialty = "Medicina general",
                initials = "RP",
                type = "Consulta general",
                price = "$350 MXN",
                status = "Confirmada",
                location = "Consultorio presencial"
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PageBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Navy,
                    shape = RoundedCornerShape(
                        bottomStart = 28.dp,
                        bottomEnd = 28.dp
                    )
                )
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 24.dp,
                    bottom = 24.dp
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Hola, $patientName",
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Tienes ${appointments.size} citas próximas",
                        fontSize = 14.sp,
                        color = Color(0xFFD8E8F5)
                    )
                }

                Surface(
                    modifier = Modifier.size(48.dp),
                    shape = CircleShape,
                    color = Color(0xFF28648D),
                    contentColor = Color.White,
                    onClick = onProfile
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Outlined.PersonOutline,
                            contentDescription = "Perfil",
                            modifier = Modifier.size(27.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChipLike(text = "Fecha y hora")
                FilterChipLike(text = "Especialidad")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp,
                    vertical = 20.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Próximas citas",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainText
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "${appointments.size} citas · Todos los estados",
                    fontSize = 12.sp,
                    color = SecondaryText
                )
            }

            Icon(
                imageVector = Icons.Outlined.CalendarMonth,
                contentDescription = "Calendario",
                tint = Navy,
                modifier = Modifier.size(26.dp)
            )
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                start = 24.dp,
                end = 24.dp,
                bottom = 20.dp
            ),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE1F5F3)
                    ),
                    shape = RoundedCornerShape(18.dp),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        Color(0xFF79DCD5)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = Color.White,
                            modifier = Modifier.size(42.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Outlined.StarOutline,
                                    contentDescription = null,
                                    tint = Color(0xFFD98200)
                                )
                            }
                        }

                        Spacer(Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "¿Cómo te fue el 14 oct?",
                                fontWeight = FontWeight.SemiBold,
                                color = MainText,
                                fontSize = 13.sp
                            )
                            Text(
                                "Califica al Dr. Ricardo Peralta",
                                color = SecondaryText,
                                fontSize = 12.sp
                            )
                        }

                        Button(
                            onClick = {
                                onRateDoctor("Dr. Ricardo Peralta Soto")
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF0F4F8),
                                contentColor = Navy
                            )
                        ) {
                            Text("Calificar")
                        }
                    }
                }
            }

            items(appointments) { appointment ->
                AppointmentCard(
                    appointment = appointment,
                    onDetails = {
                        onAppointmentDetails(appointment.doctor)
                    }
                )
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            shadowElevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 10.dp
                    ),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Outlined.CalendarMonth,
                        contentDescription = "Citas",
                        tint = Navy
                    )
                    Text(
                        "Citas",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Navy
                    )
                }

                Button(
                    onClick = onScheduleAppointment,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Teal
                    ),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Text("+ Agendar", fontWeight = FontWeight.Bold)
                }

                Surface(
                    onClick = onHistory,
                    color = Color.Transparent
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Outlined.History,
                            contentDescription = "Historial",
                            tint = SecondaryText
                        )
                        Text(
                            "Historial",
                            fontSize = 11.sp,
                            color = SecondaryText
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FilterChipLike(text: String) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(24.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor)
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 14.dp,
                vertical = 11.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Schedule,
                contentDescription = null,
                tint = Navy,
                modifier = Modifier.size(17.dp)
            )
            Spacer(Modifier.width(7.dp))
            Text(
                text = text,
                fontSize = 12.sp,
                color = MainText,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun AppointmentCard(
    appointment: Appointment,
    onDetails: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            BorderColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        onClick = onDetails
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.CalendarMonth,
                    contentDescription = null,
                    tint = Navy,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "${appointment.day} ${appointment.date}",
                    fontWeight = FontWeight.Bold,
                    color = MainText,
                    modifier = Modifier.weight(1f)
                )

                val statusColor =
                    if (appointment.status == "Confirmada") {
                        ConfirmedColor
                    } else {
                        PendingColor
                    }

                val statusTextColor =
                    if (appointment.status == "Confirmada") {
                        Color(0xFF00866A)
                    } else {
                        Color(0xFFB85A00)
                    }

                Surface(
                    color = statusColor,
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = appointment.status,
                        color = statusTextColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        )
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Outlined.Schedule,
                    contentDescription = null,
                    tint = SecondaryText,
                    modifier = Modifier.size(17.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "${appointment.time} · ${appointment.duration}",
                    color = SecondaryText,
                    fontSize = 13.sp
                )
            }

            Spacer(Modifier.height(14.dp))
            androidx.compose.material3.HorizontalDivider(
                color = BorderColor
            )
            Spacer(Modifier.height(14.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(44.dp),
                    shape = CircleShape,
                    color = Color(0xFFD5F2F0)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            appointment.initials,
                            color = Teal,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }

                Spacer(Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        appointment.doctor,
                        fontWeight = FontWeight.Bold,
                        color = MainText,
                        fontSize = 13.sp
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        appointment.specialty,
                        color = SecondaryText,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    color = Color(0xFFF0F4F8),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        appointment.type,
                        color = Navy,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Surface(
                    color = Color(0xFFF0F4F8),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        appointment.price,
                        color = MainText,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            Surface(
                color = Color(0xFFF7F9FC),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    BorderColor
                )
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Outlined.PersonOutline,
                        contentDescription = null,
                        tint = Navy
                    )
                    Spacer(Modifier.width(10.dp))
                    Column {
                        Text(
                            if (appointment.location.contains("virtual", true)) {
                                "Virtual"
                            } else {
                                "Presencial"
                            },
                            color = MainText,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                        Text(
                            appointment.location,
                            color = SecondaryText,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Ver detalle  ›",
                    color = Navy,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}
