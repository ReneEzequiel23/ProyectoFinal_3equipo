package figueroa.rene.proyectofinal_3equipo.view.recovery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import figueroa.rene.proyectofinal_3equipo.ui.theme.Muted
import figueroa.rene.proyectofinal_3equipo.ui.theme.Navy
import figueroa.rene.proyectofinal_3equipo.ui.theme.PaleBackground
import figueroa.rene.proyectofinal_3equipo.ui.theme.ProyectoFinal_3equipoTheme
import figueroa.rene.proyectofinal_3equipo.ui.theme.Teal
import figueroa.rene.proyectofinal_3equipo.ui.theme.TextDark
import figueroa.rene.proyectofinal_3equipo.view.components.*

@Composable
fun EmailSentScreen(
    email: String,
    onBackToLogin: () -> Unit,
    onContinueToNewPassword: () -> Unit,
    onResend: () -> Unit,
    notice: String
) {
    Column(
        modifier = Modifier.fillMaxSize().background(PaleBackground).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(90.dp))
        Box(
            Modifier.size(96.dp).clip(CircleShape).background(Color(0xFFE5ECF3)),
            contentAlignment = Alignment.Center
        ) {
            Text("✉", color = Navy, fontSize = 42.sp)
        }
        Spacer(Modifier.height(24.dp))
        Text("Revisa tu correo", fontSize = 27.sp, fontWeight = FontWeight.Bold, color = TextDark)
        Spacer(Modifier.height(14.dp))
        Text(
            "Enviamos un enlace a ${maskEmail(email)} para crear una nueva contraseña. El enlace vence en 30 minutos.",
            color = Muted, textAlign = TextAlign.Center, fontSize = 14.sp, lineHeight = 21.sp
        )
        if (notice.isNotBlank()) {
            Spacer(Modifier.height(14.dp))
            Text(notice, color = Teal, fontSize = 13.sp)
        }
        Spacer(Modifier.weight(1f))
        PrimaryButton("Volver a iniciar sesión", onBackToLogin)
        Spacer(Modifier.height(12.dp))
        OutlinedButton(
            onClick = onContinueToNewPassword,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("Simular abrir enlace", color = Navy, fontWeight = FontWeight.SemiBold)
        }
        Spacer(Modifier.height(14.dp))
        CenterFooter("¿No te llegó? ", "Reenviar correo", onResend)
        Spacer(Modifier.height(24.dp))
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun EmailSentScreenPreview() {
    ProyectoFinal_3equipoTheme() {
        EmailSentScreen(
            email = "paciente@correo.com",
            onBackToLogin = {},
            onContinueToNewPassword = {},
            onResend = {},
            notice = "El correo no existe"
        )
    }
}