package figueroa.rene.proyectofinal_3equipo.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import figueroa.rene.proyectofinal_3equipo.model.AuthUiState

class AuthViewModel {
    var uiState by mutableStateOf(AuthUiState())
        private set

    fun updateLoginEmail(value: String) {
        uiState = uiState.copy(loginEmail = value, loginError = false)
    }

    fun updateLoginPassword(value: String) {
        uiState = uiState.copy(loginPassword = value, loginError = false)
    }

    fun login(): Boolean {
        val valid = uiState.loginEmail.trim().equals("paciente@correo.com", ignoreCase = true) &&
            uiState.loginPassword == "DrCall123"

        uiState = if (valid) {
            uiState.copy(
                loginError = false,
                notice = "Sesión de demostración iniciada."
            )
        } else {
            uiState.copy(loginError = true)
        }
        return valid
    }

    fun openForgotPassword() {
        uiState = uiState.copy(
            resetEmail = uiState.loginEmail,
            notice = ""
        )

    }

    fun updateResetEmail(value: String) {
        uiState = uiState.copy(resetEmail = value, notice = "")
    }


    fun sendResetEmail(): Boolean {
        val email = uiState.resetEmail.trim()
        val validEmail = email.contains("@") &&
                email.substringAfter("@").contains(".")

        return if (validEmail) {
            uiState = uiState.copy(notice = "")
            true
        } else {
            uiState = uiState.copy(
                notice = "Escribe un correo electrónico válido."
            )
            false
        }
    }


    fun simulateResendEmail() {
        uiState = uiState.copy(notice = "Correo de demostración reenviado.")
    }

    fun simulateOpenResetLink() {
        uiState = uiState.copy(
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

    fun saveNewPassword(): Boolean {
        val message = when {
            uiState.newPassword.length < 8 -> "Usa al menos 8 caracteres."
            uiState.newPassword.none { it.isUpperCase() } -> "Incluye al menos una mayúscula."
            uiState.newPassword.none { it.isDigit() } -> "Incluye al menos un número."
            uiState.newPassword != uiState.confirmPassword -> "Las contraseñas no coinciden."
            else -> "Contraseña actualizada en la demostración."
        }

        val success = message.startsWith("Contraseña actualizada")

        uiState = if (message.startsWith("Contraseña actualizada")) {
            uiState.copy(
                loginPassword = "",
                loginError = false,
                notice = message
            )
        } else {
            uiState.copy(notice = message)
        }
        return success
    }

    fun openRegister() {
        uiState = uiState.copy( notice = "")
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
            notice = "",
            loginError = false
        )
    }

    fun logout() {
        uiState = uiState.copy(
            loginPassword = "",
            loginError = false,
            notice = ""
        )
    }
}
