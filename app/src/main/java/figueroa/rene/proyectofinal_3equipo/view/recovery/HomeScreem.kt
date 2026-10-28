package figueroa.rene.proyectofinal_3equipo.view.recovery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import figueroa.rene.proyectofinal_3equipo.ui.theme.Muted
import figueroa.rene.proyectofinal_3equipo.ui.theme.Navy
import figueroa.rene.proyectofinal_3equipo.ui.theme.PaleBackground
import figueroa.rene.proyectofinal_3equipo.ui.theme.TextDark
import figueroa.rene.proyectofinal_3equipo.view.components.*

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