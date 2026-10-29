
package figueroa.rene.proyectofinal_3equipo.repository

import figueroa.rene.proyectofinal_3equipo.model.Appointment
import figueroa.rene.proyectofinal_3equipo.dummies.sampleAppointments
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object AppointmentRepository {

    private val _appointments = MutableStateFlow(sampleAppointments)

    val appointments: StateFlow<List<Appointment>> =
        _appointments.asStateFlow()

    fun addAppointment(appointment: Appointment) {
        _appointments.value = _appointments.value + appointment
    }

    fun getAppointments(): List<Appointment> {
        return _appointments.value
    }
}
