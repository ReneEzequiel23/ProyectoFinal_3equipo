package figueroa.rene.proyectofinal_3equipo.view.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import figueroa.rene.proyectofinal_3equipo.ui.theme.PaleBackground
import figueroa.rene.proyectofinal_3equipo.ui.theme.Teal
import figueroa.rene.proyectofinal_3equipo.ui.theme.TextDark
import figueroa.rene.proyectofinal_3equipo.view.components.*

@Composable
fun RegisterScreen(
    isDoctor: Boolean,
    onRoleChange: (Boolean) -> Unit,
    onBack: () -> Unit,
    onCreate: () -> Unit,
    notice: String
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmation by rememberSaveable { mutableStateOf("") }
    var showPassword by rememberSaveable { mutableStateOf(false) }

    Column(Modifier.fillMaxSize().background(PaleBackground)) {
        AuthHeader(
            title = "Crea tu cuenta",
            subtitle = if (isDoctor) "Regístrate como médico para recibir citas y administrar tu agenda."
            else "Regístrate como paciente para agendar y consultar tus citas.",
            showBack = true, onBack = onBack
        )
        Column(
            Modifier.fillMaxSize().clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Text("¿Cómo usarás Dr.Call?", color = TextDark, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                RoleButton("Soy paciente", selected = !isDoctor, modifier = Modifier.weight(1f)) { onRoleChange(false) }
                RoleButton("Soy médico", selected = isDoctor, modifier = Modifier.weight(1f)) { onRoleChange(true) }
            }
            Spacer(Modifier.height(24.dp))
            SectionTitle("Datos personales")
            FieldLabel("Nombre completo")
            BasicField(name, { name = it }, "Como aparece en tu identificación")
            Spacer(Modifier.height(16.dp))
            FieldLabel("Correo electrónico")
            BasicField(email, { email = it }, "nombre@correo.com", KeyboardType.Email)
            Spacer(Modifier.height(16.dp))
            FieldLabel("Fecha de nacimiento")
            BasicField("", {}, "dd/mm/aaaa")
            Spacer(Modifier.height(16.dp))
            FieldLabel("Género")
            BasicField("", {}, "Selecciona una opción")
            Spacer(Modifier.height(16.dp))
            FieldLabel("Teléfono")
            BasicField(phone, { phone = it }, "10 dígitos", KeyboardType.Phone)

            if (isDoctor) {
                Spacer(Modifier.height(24.dp))
                SectionTitle("Datos profesionales")
                FieldLabel("Especialidad médica")
                BasicField("", {}, "Selecciona tu especialidad")
                Spacer(Modifier.height(16.dp))
                FieldLabel("Cédula profesional")
                BasicField("", {}, "Número de cédula")
                Spacer(Modifier.height(16.dp))
                FieldLabel("Dirección del consultorio")
                BasicField("", {}, "Calle, número, colonia y ciudad")
            }

            Spacer(Modifier.height(24.dp))
            SectionTitle("Seguridad")
            FieldLabel("Contraseña")
            PasswordField(password, { password = it }, showPassword) { showPassword = !showPassword }
            Spacer(Modifier.height(16.dp))
            FieldLabel("Confirmar contraseña")
            PasswordField(confirmation, { confirmation = it }, showPassword) { showPassword = !showPassword }
            Spacer(Modifier.height(20.dp))
            if (notice.isNotBlank()) {
                Text(notice, color = Teal, fontSize = 13.sp)
                Spacer(Modifier.height(12.dp))
            }
            PrimaryButton("Crear cuenta", onCreate)
            Spacer(Modifier.height(18.dp))
            CenterFooter ("¿Ya tienes cuenta? ", "Inicia sesión", onBack)
        }
    }
}
