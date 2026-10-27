package figueroa.rene.proyectofinal_3equipo.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import figueroa.rene.proyectofinal_3equipo.model.AuthDestination
import figueroa.rene.proyectofinal_3equipo.model.AuthUiState

/**
 * Controla el estado y las validaciones del flujo de autenticación local.
 * No autentica usuarios reales ni envía correos.
 */
class AuthViewModel {
    var uiState by mutableStateOf(AuthUiState())
        private set

    fun updateLoginEmail(value: String) {
        uiState = uiState.copy(loginEmail = value, loginError = false)
    }

    fun updateLoginPassword(value: String) {
        uiState = uiState.copy(loginPassword = value, loginError = false)
    }

    fun login() {
        val valid = uiState.loginEmail.trim().equals("paciente@correo.com", ignoreCase = true) &&
            uiState.loginPassword == "DrCall123"

        uiState = if (valid) {
            uiState.copy(
                destination = AuthDestination.HOME,
                loginError = false,
                notice = "Sesión de demostración iniciada."
            )
        } else {
            uiState.copy(loginError = true)
        }
    }

    fun openForgotPassword() {
        uiState = uiState.copy(
            destination = AuthDestination.FORGOT_PASSWORD,
            resetEmail = uiState.loginEmail,
            notice = ""
        )
    }

    fun updateResetEmail(value: String) {
        uiState = uiState.copy(resetEmail = value, notice = "")
    }

    fun sendResetEmail() {
        val email = uiState.resetEmail.trim()
        val validEmail = email.contains("@") && email.substringAfter("@").contains(".")
        uiState = if (validEmail) {
            uiState.copy(destination = AuthDestination.EMAIL_SENT, notice = "")
        } else {
            uiState.copy(notice = "Escribe un correo electrónico válido.")
        }
    }

    fun simulateResendEmail() {
        uiState = uiState.copy(notice = "Correo de demostración reenviado.")
    }

    fun simulateOpenResetLink() {
        uiState = uiState.copy(
            destination = AuthDestination.NEW_PASSWORD,
            newPassword = "",
            confirmPassword = "",
            notice = ""
        )
    }

    fun updateNewPassword(value: String) {
        uiState = uiState.copy(newPassword = value, notice = "")
    }

    fun updateConfirmPassword(value: String) {
        uiState = uiState.copy(confirmPassword = value, notice = "")
    }

    fun saveNewPassword() {
        val message = when {
            uiState.newPassword.length < 8 -> "Usa al menos 8 caracteres."
            uiState.newPassword.none { it.isUpperCase() } -> "Incluye al menos una mayúscula."
            uiState.newPassword.none { it.isDigit() } -> "Incluye al menos un número."
            uiState.newPassword != uiState.confirmPassword -> "Las contraseñas no coinciden."
            else -> "Contraseña actualizada en la demostración."
        }

        uiState = if (message.startsWith("Contraseña actualizada")) {
            uiState.copy(
                destination = AuthDestination.LOGIN,
                loginPassword = "",
                loginError = false,
                notice = message
            )
        } else {
            uiState.copy(notice = message)
        }
    }

    fun openRegister() {
        uiState = uiState.copy(destination = AuthDestination.REGISTER, notice = "")
    }

    fun updateRegisterRole(isDoctor: Boolean) {
        uiState = uiState.copy(registerAsDoctor = isDoctor, notice = "")
    }

    fun simulateCreateAccount() {
        uiState = uiState.copy(
            notice = "Registro visual de demostración. Todavía no se guardan cuentas."
        )
    }

    fun showBiometricDialog() {
        uiState = uiState.copy(biometricDialogVisible = true)
    }

    fun hideBiometricDialog() {
        uiState = uiState.copy(biometricDialogVisible = false)
    }

    fun backToLogin() {
        uiState = uiState.copy(
            destination = AuthDestination.LOGIN,
            notice = "",
            loginError = false
        )
    }

    fun logout() {
        uiState = uiState.copy(
            destination = AuthDestination.LOGIN,
            loginPassword = "",
            loginError = false,
            notice = ""
        )
    }
}
