package figueroa.rene.proyectofinal_3equipo.model

enum class AuthDestination {
    LOGIN,
    FORGOT_PASSWORD,
    EMAIL_SENT,
    NEW_PASSWORD,
    REGISTER,
    HOME
}

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
