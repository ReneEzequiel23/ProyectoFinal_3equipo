package figueroa.rene.proyectofinal_3equipo.view.recovery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import figueroa.rene.proyectofinal_3equipo.ui.theme.ErrorRed
import figueroa.rene.proyectofinal_3equipo.ui.theme.PaleBackground
import figueroa.rene.proyectofinal_3equipo.ui.theme.Teal
import figueroa.rene.proyectofinal_3equipo.view.components.*
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import figueroa.rene.proyectofinal_3equipo.ui.theme.ProyectoFinal_3equipoTheme

@Composable
fun NewPasswordScreen(
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmation: String,
    onConfirmationChange: (String) -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    notice: String
) {
    var showPassword by rememberSaveable { mutableStateOf(false) }
    Column(Modifier.fillMaxSize().background(PaleBackground)) {
        AuthHeader(
            title = "Crea una nueva contraseña",
            subtitle = "Elige una contraseña segura que no hayas usado antes en Dr.Call.",
            showBack = true,
            onBack = onBack,
            icon = "✓"
        )
        Column(
            Modifier.fillMaxSize().clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            FieldLabel("Nueva contraseña")
            PasswordField(password, onPasswordChange, showPassword) { showPassword = !showPassword }
            Spacer(Modifier.height(16.dp))
            InfoBanner("Tu contraseña debe tener mínimo 8 caracteres, una letra mayúscula y un número.")
            Spacer(Modifier.height(20.dp))
            FieldLabel("Confirmar contraseña")
            PasswordField(confirmation, onConfirmationChange, showPassword) { showPassword = !showPassword }
            if (notice.isNotBlank()) {
                Spacer(Modifier.height(12.dp))
                Text(notice, color = if (notice.startsWith("Contraseña actualizada")) Teal else ErrorRed, fontSize = 13.sp)
            }
            Spacer(Modifier.height(48.dp))
            PrimaryButton("Guardar contraseña", onSave)
        }
    }
}
