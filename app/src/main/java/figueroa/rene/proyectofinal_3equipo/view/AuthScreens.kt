package figueroa.rene.proyectofinal_3equipo.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF174F80)
private val Teal = Color(0xFF117A91)
private val PaleBackground = Color(0xFFF6F9FC)
private val TextDark = Color(0xFF202D42)
private val Muted = Color(0xFF647A99)
private val FieldBorder = Color(0xFFD9E3EF)
private val ErrorRed = Color(0xFFFF3B3B)

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
            CenterFooter("¿Ya tienes cuenta? ", "Inicia sesión", onBack)
        }
    }
}

@Composable
fun DemoHomeScreen(email: String, onLogout: () -> Unit) {
    Column(
        Modifier.fillMaxSize().background(PaleBackground).padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Dr.Call", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Navy)
        Spacer(Modifier.height(12.dp))
        Text("¡Sesión iniciada!", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = TextDark)
        Text("Cuenta de demostración: $email", color = Muted, textAlign = TextAlign.Center)
        Spacer(Modifier.height(28.dp))
        PrimaryButton("Cerrar sesión", onLogout)
    }
}

@Composable
private fun AuthHeader(
    title: String,
    subtitle: String,
    showLogo: Boolean = false,
    showBack: Boolean = false,
    onBack: (() -> Unit)? = null,
    icon: String? = null
) {
    Box(
        modifier = Modifier.fillMaxWidth().height(if (showLogo) 248.dp else 260.dp)
            .background(Navy)
    ) {
        Canvas(Modifier.fillMaxSize()) {
            drawCircle(color = Teal.copy(alpha = 0.8f), radius = 175.dp.toPx(),
                center = androidx.compose.ui.geometry.Offset(size.width * 0.91f, 0f))
            drawCircle(color = Color(0xFF0D6B96).copy(alpha = 0.85f), radius = 82.dp.toPx(),
                center = androidx.compose.ui.geometry.Offset(size.width * 0.96f, size.height * 0.92f))
        }
        Column(
            Modifier.fillMaxSize().padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 34.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            if (showBack) {
                Box(
                    Modifier.size(48.dp).clip(RoundedCornerShape(14.dp))
                        .background(Color.White.copy(alpha = 0.14f))
                        .clickable { onBack?.invoke() },
                    contentAlignment = Alignment.Center
                ) {
                    Text("‹", fontSize = 34.sp, color = Color.White)
                }
                Spacer(Modifier.height(18.dp))
                if (icon != null) {
                    Box(
                        Modifier.size(54.dp).clip(RoundedCornerShape(16.dp)).background(Teal),
                        contentAlignment = Alignment.Center
                    ) { Text(icon, color = Color.White, fontSize = 27.sp) }
                    Spacer(Modifier.height(18.dp))
                }
            }
            if (showLogo) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier.size(48.dp).clip(RoundedCornerShape(14.dp)).background(Color.White),
                        contentAlignment = Alignment.Center
                    ) { Text("+", color = Navy, fontSize = 34.sp, fontWeight = FontWeight.Light) }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("Dr.Call", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.Bold)
                        Text("Citas médicas", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)
                    }
                }
                Spacer(Modifier.height(24.dp))
            }
            Text(title, color = Color.White, fontSize = 27.sp, fontWeight = FontWeight.Bold, lineHeight = 32.sp)
            Spacer(Modifier.height(8.dp))
            Text(subtitle, color = Color.White.copy(alpha = 0.88f), fontSize = 14.sp, lineHeight = 21.sp)
        }
    }
}

@Composable
private fun FieldLabel(text: String) {
    Text(
        text = text,
        color = TextDark,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
private fun BasicField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(placeholder, color = Muted.copy(alpha = 0.75f), fontSize = 14.sp) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(12.dp),
        colors = fieldColors()
    )
}

@Composable
private fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    showPassword: Boolean,
    onToggleVisibility: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("••••••••", color = Muted.copy(alpha = 0.75f)) },
        singleLine = true,
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            Text(
                if (showPassword) "Ocultar" else "Ver",
                color = Muted,
                fontSize = 12.sp,
                modifier = Modifier.clickable(onClick = onToggleVisibility).padding(8.dp)
            )
        },
        shape = RoundedCornerShape(12.dp),
        colors = fieldColors()
    )
}

@Composable
private fun PrimaryButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(56.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Teal, contentColor = Color.White)
    ) {
        Text(text, fontWeight = FontWeight.Bold, fontSize = 15.sp)
    }
}

@Composable
private fun RoleButton(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    if (selected) {
        Button(
            onClick = onClick,
            modifier = modifier.height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Navy)
        ) { Text(text, fontSize = 12.sp, fontWeight = FontWeight.Bold) }
    } else {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier.height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Muted)
        ) { Text(text, fontSize = 12.sp) }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.width(4.dp).height(22.dp).clip(RoundedCornerShape(3.dp)).background(Color(0xFF00BFA5)))
        Spacer(Modifier.width(10.dp))
        Text(text, color = TextDark, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
    Spacer(Modifier.height(18.dp))
}

@Composable
private fun ErrorBanner(title: String, message: String) {
    Row(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFFFF5F1)).padding(14.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text("⚠", color = ErrorRed, fontSize = 20.sp)
        Spacer(Modifier.width(10.dp))
        Column {
            Text(title, color = Navy, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            Spacer(Modifier.height(4.dp))
            Text(message, color = Navy, fontSize = 12.sp, lineHeight = 17.sp)
        }
    }
}

@Composable
private fun InfoBanner(message: String) {
    Row(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp))
            .background(Color.White).padding(14.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text("ⓘ", color = Color(0xFF2676FF), fontSize = 18.sp)
        Spacer(Modifier.width(10.dp))
        Text(message, color = TextDark, fontSize = 12.sp, lineHeight = 18.sp)
    }
}

@Composable
private fun CenterFooter(prefix: String, action: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(prefix, color = Muted, fontSize = 13.sp)
        Text(
            action, color = Navy, fontWeight = FontWeight.Bold, fontSize = 13.sp,
            modifier = Modifier.clickable(onClick = onClick)
        )
    }
}

@Composable
private fun fieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = Color(0xFF00BCD4),
    unfocusedBorderColor = FieldBorder,
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.White,
    focusedTextColor = TextDark,
    unfocusedTextColor = TextDark,
    cursorColor = Teal
)

private fun maskEmail(email: String): String {
    val parts = email.split("@")
    if (parts.size != 2 || parts[0].isEmpty()) return email
    val visible = parts[0].take(1)
    return "$visible•••••@${parts[1]}"
}
