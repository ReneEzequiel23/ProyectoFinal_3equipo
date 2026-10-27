package figueroa.rene.proyectofinal_3equipo.model

/**
 * Destinos disponibles en el flujo de autenticación de demostración.
 */
enum class AuthDestination {
    LOGIN,
    FORGOT_PASSWORD,
    EMAIL_SENT,
    NEW_PASSWORD,
    REGISTER,
    HOME
}

/**
 * Estado que necesita la interfaz para dibujar el flujo de Dr.Call.
 */
data class AuthUiState(
    val destination: AuthDestination = AuthDestination.LOGIN,
    val loginEmail: String = "",
    val loginPassword: String = "",
    val loginError: Boolean = false,
    val resetEmail: String = "",
    val newPassword: String = "",
    val confirmPassword: String = "",
    val registerAsDoctor: Boolean = false,
    val biometricDialogVisible: Boolean = false,
    val notice: String = ""
)
