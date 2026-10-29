package figueroa.rene.proyectofinal_3equipo.model

data class AuthUiState(
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