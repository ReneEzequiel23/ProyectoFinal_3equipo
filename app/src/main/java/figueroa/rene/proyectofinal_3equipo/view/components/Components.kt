package figueroa.rene.proyectofinal_3equipo.view.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import figueroa.rene.proyectofinal_3equipo.ui.theme.ErrorRed
import figueroa.rene.proyectofinal_3equipo.ui.theme.FieldBorder
import figueroa.rene.proyectofinal_3equipo.ui.theme.Muted
import figueroa.rene.proyectofinal_3equipo.ui.theme.Navy
import figueroa.rene.proyectofinal_3equipo.ui.theme.Teal
import figueroa.rene.proyectofinal_3equipo.ui.theme.TextDark
import figueroa.rene.proyectofinal_3equipo.view.components.ErrorBanner

@Composable
fun AuthHeader(
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
        Canvas (Modifier.fillMaxSize()) {
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
fun FieldLabel(text: String) {
    Text(
        text = text,
        color = TextDark,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun BasicField(
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
fun PasswordField(
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
fun PrimaryButton(text: String, onClick: () -> Unit) {
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
fun RoleButton(
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
fun SectionTitle(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.width(4.dp).height(22.dp).clip(RoundedCornerShape(3.dp)).background(Color(0xFF00BFA5)))
        Spacer(Modifier.width(10.dp))
        Text(text, color = TextDark, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
    Spacer(Modifier.height(18.dp))
}

@Composable
fun ErrorBanner(title: String, message: String) {
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
fun InfoBanner(message: String) {
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
fun CenterFooter(prefix: String, action: String, onClick: () -> Unit) {
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
fun fieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = Color(0xFF00BCD4),
    unfocusedBorderColor = FieldBorder,
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.White,
    focusedTextColor = TextDark,
    unfocusedTextColor = TextDark,
    cursorColor = Teal
)

fun maskEmail(email: String): String {
    val parts = email.split("@")
    if (parts.size != 2 || parts[0].isEmpty()) return email
    val visible = parts[0].take(1)
    return "$visible•••••@${parts[1]}"
}
