package figueroa.rene.proyectofinal_3equipo.dummies

import figueroa.rene.proyectofinal_3equipo.model.Appointment
import java.time.LocalDate

val sampleAppointments = listOf(
    Appointment(
        date = LocalDate.of(2026, 10, 19),
        time = "10:30 a.m.",
        duration = "20 min",
        doctor = "Dra. Mariana Valdez Ruiz",
        specialty = "Cardiología",
        initials = "MV",
        type = "Cita de seguimiento",
        price = "$500 MXN",
        status = "Confirmada",
        location = "Consultorio 204, Torre Médica"
    ),
    Appointment(
        date = LocalDate.of(2026, 10, 21),
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
        date = LocalDate.of(2026, 10, 27),
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
        date = LocalDate.of(2026, 10, 30),
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