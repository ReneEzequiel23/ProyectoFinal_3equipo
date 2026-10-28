package figueroa.rene.proyectofinal_3equipo.view.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import figueroa.rene.proyectofinal_3equipo.ui.theme.ErrorRed
import figueroa.rene.proyectofinal_3equipo.ui.theme.FieldBorder
import figueroa.rene.proyectofinal_3equipo.ui.theme.Muted
import figueroa.rene.proyectofinal_3equipo.ui.theme.Navy
import figueroa.rene.proyectofinal_3equipo.ui.theme.PaleBackground
import figueroa.rene.proyectofinal_3equipo.ui.theme.ProyectoFinal_3equipoTheme
import figueroa.rene.proyectofinal_3equipo.view.components.*

@Composable
fun LoginScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    showError: Boolean,
    onLogin: () -> Unit,
    onForgotPassword: () -> Unit,
    onRegister: () -> Unit,
    onBiometric: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().background(PaleBackground)
    ) {
        AuthHeader(
            title = "Inicia sesión",
            subtitle = "Consulta tus citas, recetas e historial desde cualquier lugar.",
            showLogo = true
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                .background(PaleBackground)
                .verticalScroll(rememberScrollState())
                .padding(start = 24.dp, end = 24.dp, top = 26.dp, bottom = 24.dp)
        ) {
            if (showError) {
                ErrorBanner(
                    title = "No pudimos iniciar sesión",
                    message = "Tu correo o contraseña no coinciden. Inténtalo de nuevo o recupera tu contraseña."
                )
                Spacer(Modifier.height(18.dp))
            }

            FieldLabel("Correo electrónico")
            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("nombre@correo.com", color = Muted.copy(alpha = 0.75f)) },
                singleLine = true,
                isError = showError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                shape = RoundedCornerShape(12.dp),
                colors = fieldColors()
            )
            if (showError) {
                Text("⚠ Verifica tu correo", color = ErrorRed, fontSize = 12.sp,
                    modifier = Modifier.padding(top = 5.dp))
            }

            Spacer(Modifier.height(20.dp))
            FieldLabel("Contraseña")
            var showPassword by rememberSaveable { mutableStateOf(false) }
            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Tu contraseña", color = Muted.copy(alpha = 0.75f)) },
                singleLine = true,
                isError = showError,
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    Text(
                        if (showPassword) "Ocultar" else "Ver",
                        color = Muted,
                        fontSize = 12.sp,
                        modifier = Modifier.clickable { showPassword = !showPassword }.padding(8.dp)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = fieldColors()
            )
            if (showError) {
                Text("⚠ Contraseña incorrecta", color = ErrorRed, fontSize = 12.sp,
                    modifier = Modifier.padding(top = 5.dp))
            }

            TextButton(
                onClick = onForgotPassword,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("¿Olvidaste tu contraseña?", color = Navy, fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.height(76.dp))
            PrimaryButton(text = "Iniciar sesión", onClick = onLogin)
            Spacer(Modifier.height(18.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(Modifier.weight(1f).height(1.dp).background(FieldBorder))
                Text("  o continúa con  ", color = Muted, fontSize = 12.sp)
                Spacer(Modifier.weight(1f).height(1.dp).background(FieldBorder))
            }
            Spacer(Modifier.height(18.dp))
            OutlinedButton(
                onClick = onBiometric,
                modifier = Modifier.fillMaxWidth().height(54.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Navy)
            ) {
                Text("◎   Ingresar con huella", fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.height(28.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("¿No tienes cuenta? ", color = Muted, fontSize = 14.sp)
                Text(
                    "Regístrate",
                    color = Navy,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable(onClick = onRegister)
                )
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun LoginScreenPreview() {
    ProyectoFinal_3equipoTheme() {
        LoginScreen(
            email = "paciente@correo.com",
            onEmailChange = {},
            password = "DrCall123",
            onPasswordChange = {},
            showError = false,
            onLogin = {},
            onForgotPassword = {},
            onRegister = {},
            onBiometric = {}
        )
    }
}