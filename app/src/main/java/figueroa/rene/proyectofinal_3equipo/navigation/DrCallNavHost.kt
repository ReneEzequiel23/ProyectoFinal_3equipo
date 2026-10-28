
package figueroa.rene.proyectofinal_3equipo.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import figueroa.rene.proyectofinal_3equipo.model.AuthDestination
import figueroa.rene.proyectofinal_3equipo.ui.theme.ProyectoFinal_3equipoTheme
import figueroa.rene.proyectofinal_3equipo.view.recovery.DemoHomeScreen
import figueroa.rene.proyectofinal_3equipo.view.login.LoginScreen
import figueroa.rene.proyectofinal_3equipo.view.recovery.EmailSentScreen
import figueroa.rene.proyectofinal_3equipo.view.recovery.ForgotPasswordScreen
import figueroa.rene.proyectofinal_3equipo.view.recovery.NewPasswordScreen
import figueroa.rene.proyectofinal_3equipo.view.register.RegisterScreen
import figueroa.rene.proyectofinal_3equipo.viewmodel.AuthViewModel

@Composable
fun DrCallNavHost() {
    val navController = rememberNavController()
    val viewModel = remember { AuthViewModel() }
    val state = viewModel.uiState

    LaunchedEffect(state.destination) {
        val route = when (state.destination) {
            AuthDestination.LOGIN -> Login
            AuthDestination.FORGOT_PASSWORD -> ForgotPassword
            AuthDestination.EMAIL_SENT -> EmailSent
            AuthDestination.NEW_PASSWORD -> NewPassword
            AuthDestination.REGISTER -> Register
            AuthDestination.HOME -> Home
        }

        navController.navigate(route) {
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        }
    }

    ProyectoFinal_3equipoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFF6F9FC)
        ) {
            DrCallNavigationGraph(
                navController = navController,
                viewModel = viewModel
            )

            if (state.biometricDialogVisible) {
                AlertDialog(
                    onDismissRequest = viewModel::hideBiometricDialog,
                    containerColor = Color.White,
                    title = {
                        Text(
                            "Confirma tu identidad",
                            color = Color(0xFF202D42),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    text = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "◎",
                                color = Color(0xFF117A91),
                                fontSize = 56.sp
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                "La lectura de huella se integrará después con Android BiometricPrompt.",
                                color = Color(0xFF647A99),
                                textAlign = TextAlign.Center
                            )
                        }
                    },
                    confirmButton = {
                        TextButton(
                            onClick = viewModel::hideBiometricDialog
                        ) {
                            Text(
                                "Entendido",
                                color = Color(0xFF174F80)
                            )
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = viewModel::hideBiometricDialog
                        ) {
                            Text(
                                "Usar contraseña",
                                color = Color(0xFF174F80)
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun DrCallNavigationGraph(
    navController: NavHostController,
    viewModel: AuthViewModel
) {
    val state = viewModel.uiState

    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        composable<Login> {
            LoginScreen(
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
        }

        composable<ForgotPassword> {
            ForgotPasswordScreen(
                email = state.resetEmail,
                onEmailChange = viewModel::updateResetEmail,
                onBack = viewModel::backToLogin,
                onSend = viewModel::sendResetEmail,
                notice = state.notice
            )
        }

        composable<EmailSent> {
            EmailSentScreen(
                email = state.resetEmail,
                onBackToLogin = viewModel::backToLogin,
                onContinueToNewPassword =
                    viewModel::simulateOpenResetLink,
                onResend = viewModel::simulateResendEmail,
                notice = state.notice
            )
        }

        composable<NewPassword> {
            NewPasswordScreen(
                password = state.newPassword,
                onPasswordChange = viewModel::updateNewPassword,
                confirmation = state.confirmPassword,
                onConfirmationChange =
                    viewModel::updateConfirmPassword,
                onSave = viewModel::saveNewPassword,
                onBack = viewModel::backToLogin,
                notice = state.notice
            )
        }

        composable<Register> {
            RegisterScreen(
                isDoctor = state.registerAsDoctor,
                onRoleChange = viewModel::updateRegisterRole,
                onBack = viewModel::backToLogin,
                onCreate = viewModel::simulateCreateAccount,
                notice = state.notice
            )
        }

        composable<Home> {
            DemoHomeScreen(
                email = state.loginEmail,
                onLogout = viewModel::logout
            )
        }
    }
}
