
package figueroa.rene.proyectofinal_3equipo.view.patient

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale
import figueroa.rene.proyectofinal_3equipo.model.Appointment
import figueroa.rene.proyectofinal_3equipo.dummies.sampleAppointments
import figueroa.rene.proyectofinal_3equipo.ui.theme.BorderColor
import figueroa.rene.proyectofinal_3equipo.ui.theme.ConfirmedColor
import figueroa.rene.proyectofinal_3equipo.ui.theme.MainText
import figueroa.rene.proyectofinal_3equipo.ui.theme.Navy
import figueroa.rene.proyectofinal_3equipo.ui.theme.PageBackground
import figueroa.rene.proyectofinal_3equipo.ui.theme.PendingColor
import figueroa.rene.proyectofinal_3equipo.ui.theme.SecondaryText
import figueroa.rene.proyectofinal_3equipo.ui.theme.Teal

private val spanishLocale = Locale("es", "MX")

private val fullDateFormatter =
    DateTimeFormatter.ofPattern("EEEE d 'de' MMMM", spanishLocale)

private val monthFormatter =
    DateTimeFormatter.ofPattern("MMMM yyyy", spanishLocale)

@Composable
fun PatientHomeScreen(
    patientName: String = "María",
    onScheduleAppointment: () -> Unit = {}
) {
    var selectedView by remember {
        mutableStateOf(AppointmentView.CARDS)
    }

    var displayedMonth by remember {
        mutableStateOf(YearMonth.of(2026, 10))
    }

    var selectedDate by remember {
        mutableStateOf(LocalDate.of(2026, 10, 27))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PageBackground)
    ) {
        // Encabezado
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Navy,
                    RoundedCornerShape(
                        bottomStart = 28.dp,
                        bottomEnd = 28.dp
                    )
                )
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 24.dp,
                    bottom = 22.dp
                )
        ) {
            Text(
                text = "Hola, $patientName",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(Modifier.height(5.dp))

            Text(
                text = "Consulta y organiza tus citas médicas",
                fontSize = 14.sp,
                color = Color(0xFFD8E8F5)
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = "${sampleAppointments.size} citas registradas",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Column(
            modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
                bottom = 8.dp
            )
        ) {
            Text(
                text = "Mis citas",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = MainText
            )

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                ViewOption(
                    text = "Tarjetas",
                    selected = selectedView == AppointmentView.CARDS,
                    modifier = Modifier.weight(1f),
                    onClick = { selectedView = AppointmentView.CARDS }
                )

                ViewOption(
                    text = "Lista",
                    selected = selectedView == AppointmentView.LIST,
                    modifier = Modifier.weight(1f),
                    onClick = { selectedView = AppointmentView.LIST }
                )

                ViewOption(
                    text = "Calendario",
                    selected = selectedView == AppointmentView.CALENDAR,
                    modifier = Modifier.weight(1.15f),
                    onClick = { selectedView = AppointmentView.CALENDAR }
                )
            }
        }
        when (selectedView) {
            AppointmentView.CARDS -> {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(
                        horizontal = 20.dp,
                        vertical = 12.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(
                        items = sampleAppointments,
                        key = { it.date.toString() + it.doctor }
                    ) { appointment ->
                        AppointmentCard(appointment)
                    }
                }
            }

            AppointmentView.LIST -> {
                WeeklyAppointmentList(
                    modifier = Modifier.weight(1f)
                )
            }

            AppointmentView.CALENDAR -> {
                MonthlyAppointmentCalendar(
                    displayedMonth = displayedMonth,
                    selectedDate = selectedDate,
                    onPreviousMonth = {
                        displayedMonth = displayedMonth.minusMonths(1)
                        selectedDate = displayedMonth.atDay(1)
                    },
                    onNextMonth = {
                        displayedMonth = displayedMonth.plusMonths(1)
                        selectedDate = displayedMonth.atDay(1)
                    },
                    onSelectDate = { date ->
                        selectedDate = date
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Navegación inferior: solo las funciones prioritarias
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            shadowElevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CalendarMonth,
                        contentDescription = null,
                        tint = Navy
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = "Mis citas",
                        color = Navy,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }

                Button(
                    onClick = onScheduleAppointment,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Teal
                    ),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Text(
                        text = "+ Agendar cita",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun ViewOption(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        color = if (selected) Navy else Color.White,
        border = BorderStroke(
            1.dp,
            if (selected) Navy else BorderColor
        )
    ) {
        Box(
            modifier = Modifier.padding(
                horizontal = 5.dp,
                vertical = 12.dp
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = if (selected) Color.White else SecondaryText,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun AppointmentCard(
    appointment: Appointment
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, BorderColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
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
                    modifier = Modifier.size(19.dp)
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = appointment.date.format(
                        DateTimeFormatter.ofPattern(
                            "EEE d MMM",
                            spanishLocale
                        )
                    ).replaceFirstChar { it.uppercase(spanishLocale) },
                    color = MainText,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                StatusLabel(appointment.status)
            }

            Spacer(Modifier.height(10.dp))

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
            HorizontalDivider(color = BorderColor)
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
                            fontWeight = FontWeight.Bold
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
                InfoLabel(appointment.type)
                InfoLabel(appointment.price)
            }

            Spacer(Modifier.height(12.dp))

            Surface(
                color = Color(0xFFF7F9FC),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, BorderColor)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CalendarMonth,
                        contentDescription = null,
                        tint = Navy
                    )

                    Spacer(Modifier.width(10.dp))

                    Column {
                        Text(
                            if (appointment.location.contains(
                                    "virtual",
                                    ignoreCase = true
                                )
                            ) "Consulta virtual" else "Consulta presencial",
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
        }
    }
}

@Composable
private fun StatusLabel(status: String) {
    val confirmed = status == "Confirmada"

    Surface(
        color = if (confirmed) ConfirmedColor else PendingColor,
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = status,
            color = if (confirmed) Color(0xFF00866A)
            else Color(0xFFB85A00),
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 6.dp
            )
        )
    }
}

@Composable
private fun InfoLabel(text: String) {
    Surface(
        color = Color(0xFFF0F4F8),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            color = Navy,
            fontSize = 11.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun WeeklyAppointmentList(
    modifier: Modifier = Modifier
) {
    val weeks = sampleAppointments.groupBy { appointment ->
        appointment.date.with(
            DayOfWeek.MONDAY
        )
    }.toSortedMap()

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 12.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        weeks.forEach { (weekStart, appointments) ->
            item(key = "week-$weekStart") {
                Text(
                    text = "Semana del ${weekStart.format(
                        DateTimeFormatter.ofPattern("d 'de' MMMM", spanishLocale)
                    )}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Navy,
                    modifier = Modifier.padding(
                        top = 8.dp,
                        bottom = 2.dp
                    )
                )
            }

            items(
                items = appointments.sortedBy { it.date },
                key = { "list-${it.date}-${it.doctor}" }
            ) { appointment ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    border = BorderStroke(1.dp, BorderColor),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                appointment.date.dayOfMonth.toString(),
                                color = Navy,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp
                            )

                            Text(
                                appointment.date.format(
                                    DateTimeFormatter.ofPattern("EEE", spanishLocale)
                                ).replaceFirstChar { it.uppercase(spanishLocale) },
                                color = SecondaryText,
                                fontSize = 12.sp
                            )
                        }

                        Spacer(Modifier.width(14.dp))
                        Box(
                            modifier = Modifier
                                .width(3.dp)
                                .height(62.dp)
                                .background(
                                    Teal,
                                    RoundedCornerShape(4.dp)
                                )
                        )
                        Spacer(Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                appointment.time,
                                color = Navy,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            )

                            Spacer(Modifier.height(4.dp))

                            Text(
                                appointment.doctor,
                                color = MainText,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )

                            Text(
                                appointment.specialty,
                                color = SecondaryText,
                                fontSize = 12.sp
                            )
                        }

                        StatusLabel(appointment.status)
                    }
                }
            }
        }
    }
}

@Composable
private fun MonthlyAppointmentCalendar(
    displayedMonth: YearMonth,
    selectedDate: LocalDate,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit,
    onSelectDate: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val appointmentsByDate = sampleAppointments.groupBy { it.date }
    val firstDay = displayedMonth.atDay(1)
    val offset = firstDay.dayOfWeek.value - 1
    val totalCells = ((offset + displayedMonth.lengthOfMonth() + 6) / 7) * 7

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            border = BorderStroke(1.dp, BorderColor),
            shape = RoundedCornerShape(18.dp)
        ) {
            Column(
                modifier = Modifier.padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        Icons.Outlined.ChevronLeft,
                        contentDescription = "Mes anterior",
                        tint = Navy,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable(onClick = onPreviousMonth)
                            .padding(4.dp)
                    )

                    Text(
                        displayedMonth.format(monthFormatter)
                            .replaceFirstChar { it.uppercase(spanishLocale) },
                        color = MainText,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )

                    Icon(
                        Icons.Outlined.ChevronRight,
                        contentDescription = "Mes siguiente",
                        tint = Navy,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable(onClick = onNextMonth)
                            .padding(4.dp)
                    )
                }

                Spacer(Modifier.height(18.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    listOf("L", "M", "X", "J", "V", "S", "D").forEach { day ->
                        Text(
                            text = day,
                            modifier = Modifier.weight(1f),
                            color = SecondaryText,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }

                Spacer(Modifier.height(8.dp))

                for (week in 0 until totalCells / 7) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        for (dayColumn in 0..6) {
                            val cellIndex = week * 7 + dayColumn
                            val dayNumber = cellIndex - offset + 1
                            val validDay = dayNumber in 1..displayedMonth.lengthOfMonth()

                            if (validDay) {
                                val date = displayedMonth.atDay(dayNumber)
                                val hasAppointment = appointmentsByDate.containsKey(date)
                                val selected = date == selectedDate

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(vertical = 3.dp)
                                        .size(36.dp)
                                        .background(
                                            color = when {
                                                selected -> Navy
                                                hasAppointment -> Color(0xFFDDF3F1)
                                                else -> Color.Transparent
                                            },
                                            shape = CircleShape
                                        )
                                        .clickable {
                                            onSelectDate(date)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = dayNumber.toString(),
                                            color = when {
                                                selected -> Color.White
                                                else -> MainText
                                            },
                                            fontSize = 12.sp,
                                            fontWeight = if (selected || hasAppointment) {
                                                FontWeight.Bold
                                            } else {
                                                FontWeight.Normal
                                            }
                                        )
                                    }
                                }
                            } else {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(vertical = 3.dp)
                                        .size(36.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(
                                Color(0xFFDDF3F1),
                                CircleShape
                            )
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        "Día con cita",
                        color = SecondaryText,
                        fontSize = 11.sp
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = selectedDate.format(fullDateFormatter)
                .replaceFirstChar { it.uppercase(spanishLocale) },
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = MainText
        )

        Spacer(Modifier.height(10.dp))

        val selectedAppointments = appointmentsByDate[selectedDate].orEmpty()

        if (selectedAppointments.isEmpty()) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, BorderColor)
            ) {
                Text(
                    text = "No tienes citas para este día.",
                    color = SecondaryText,
                    modifier = Modifier.padding(18.dp),
                    fontSize = 13.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f, fill = false),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(selectedAppointments) { appointment ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        border = BorderStroke(1.dp, BorderColor),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    appointment.time,
                                    color = Navy,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(1f)
                                )
                                StatusLabel(appointment.status)
                            }

                            Spacer(Modifier.height(6.dp))

                            Text(
                                appointment.doctor,
                                color = MainText,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp
                            )

                            Text(
                                appointment.specialty,
                                color = SecondaryText,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
