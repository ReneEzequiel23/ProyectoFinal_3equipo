package figueroa.rene.proyectofinal_3equipo.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import figueroa.rene.proyectofinal_3equipo.model.AuthDestination
import figueroa.rene.proyectofinal_3equipo.view.*
import figueroa.rene.proyectofinal_3equipo.viewmodel.AuthViewModel
import figueroa.rene.proyectofinal_3equipo.ui.theme.ProyectoFinal_3equipoTheme

@Composable
fun DrCallNavHost() {
    val viewModel = remember { AuthViewModel() }
    val state = viewModel.uiState

    ProyectoFinal_3equipoTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFF6F9FC)) {
            when (state.destination) {
                AuthDestination.LOGIN -> LoginScreen(
                    email = state.loginEmail,
                    onEmailChange = viewModel::updateLoginEmail,
                    password = state.loginPassword,
                    onPasswordChange = viewModel::updateLoginPassword,
                    showError = state.loginError,
                    onLogin = viewModel::login,
                    onForgotPassword = viewModel::openForgotPassword,
                    onRegister = viewModel::openRegister,
                    onBiometric = viewModel::showBiometricDialog
                )

                AuthDestination.FORGOT_PASSWORD -> ForgotPasswordScreen(
                    email = state.resetEmail,
                    onEmailChange = viewModel::updateResetEmail,
                    onBack = viewModel::backToLogin,
                    onSend = viewModel::sendResetEmail,
                    notice = state.notice
                )

                AuthDestination.EMAIL_SENT -> EmailSentScreen(
                    email = state.resetEmail,
                    onBackToLogin = viewModel::backToLogin,
                    onContinueToNewPassword = viewModel::simulateOpenResetLink,
                    onResend = viewModel::simulateResendEmail,
                    notice = state.notice
                )

                AuthDestination.NEW_PASSWORD -> NewPasswordScreen(
                    password = state.newPassword,
                    onPasswordChange = viewModel::updateNewPassword,
                    confirmation = state.confirmPassword,
                    onConfirmationChange = viewModel::updateConfirmPassword,
                    onSave = viewModel::saveNewPassword,
                    onBack = viewModel::backToLogin,
                    notice = state.notice
                )

                AuthDestination.REGISTER -> RegisterScreen(
                    isDoctor = state.registerAsDoctor,
                    onRoleChange = viewModel::updateRegisterRole,
                    onBack = viewModel::backToLogin,
                    onCreate = viewModel::simulateCreateAccount,
                    notice = state.notice
                )

                AuthDestination.HOME -> DemoHomeScreen(
                    email = state.loginEmail,
                    onLogout = viewModel::logout
                )
            }
        }

        if (state.biometricDialogVisible) {
            AlertDialog(
                onDismissRequest = viewModel::hideBiometricDialog,
                containerColor = Color.White,
                title = {
                    Text("Confirma tu identidad", color = Color(0xFF202D42), fontWeight = FontWeight.Bold)
                },
                text = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("◎", color = Color(0xFF117A91), fontSize = 56.sp)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "La lectura de huella se integrará después con Android BiometricPrompt.",
                            color = Color(0xFF647A99),
                            textAlign = TextAlign.Center
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = viewModel::hideBiometricDialog) {
                        Text("Entendido", color = Color(0xFF174F80))
                    }
                },
                dismissButton = {
                    TextButton(onClick = viewModel::hideBiometricDialog) {
                        Text("Usar contraseña", color = Color(0xFF174F80))
                    }
                }
            )
        }
    }
}
