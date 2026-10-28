package figueroa.rene.proyectofinal_3equipo.view.recovery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import figueroa.rene.proyectofinal_3equipo.ui.theme.ErrorRed
import figueroa.rene.proyectofinal_3equipo.ui.theme.Muted
import figueroa.rene.proyectofinal_3equipo.ui.theme.PaleBackground
import figueroa.rene.proyectofinal_3equipo.ui.theme.ProyectoFinal_3equipoTheme
import figueroa.rene.proyectofinal_3equipo.view.components.*
import figueroa.rene.proyectofinal_3equipo.view.login.LoginScreen

@Composable
fun ForgotPasswordScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    onBack: () -> Unit,
    onSend: () -> Unit,
    notice: String
) {
    Column(Modifier.fillMaxSize().background(PaleBackground)) {
        AuthHeader(
            title = "Recupera tu contraseña",
            subtitle = "Escribe el correo con el que te registraste y te enviaremos un enlace para crear una nueva.",
            showBack = true,
            onBack = onBack,
            icon = "▣"
        )
        Column(
            Modifier.fillMaxSize().clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                .padding(horizontal = 24.dp, vertical = 28.dp)
        ) {
            FieldLabel("Correo electrónico")
            OutlinedTextField(
                value = email, onValueChange = onEmailChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("nombre@correo.com", color = Muted.copy(alpha = 0.75f)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                shape = RoundedCornerShape(12.dp), colors = fieldColors()
            )
            Spacer(Modifier.height(18.dp))
            InfoBanner("Si no ves el correo en tu bandeja de entrada, revisa la carpeta de spam o correo no deseado.")
            if (notice.isNotBlank()) {
                Spacer(Modifier.height(12.dp))
                Text(notice, color = ErrorRed, fontSize = 13.sp)
            }
            Spacer(Modifier.weight(1f))
            PrimaryButton("Enviar enlace", onSend)
            Spacer(Modifier.height(18.dp))
            CenterFooter("¿Recordaste tu contraseña? ", "Inicia sesión", onBack)
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ForgotPasswordScreenPreview() {
    ProyectoFinal_3equipoTheme() {

        ForgotPasswordScreen(
            email = "paciente@correo.com",
            onEmailChange = {},
            onBack = {},
            onSend = {},
            notice = "El correo no existe"
        )
    }
}

