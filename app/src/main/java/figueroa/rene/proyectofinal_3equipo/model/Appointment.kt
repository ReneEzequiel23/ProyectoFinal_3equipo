package figueroa.rene.proyectofinal_3equipo.model

import java.time.LocalDate

data class Appointment(
    val date: LocalDate,
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