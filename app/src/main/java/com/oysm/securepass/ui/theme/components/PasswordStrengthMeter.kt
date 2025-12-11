package com.oysm.securepass.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class PasswordStrength {
    WEAK, FAIR, GOOD, STRONG
}

fun calculatePasswordStrength(password: String): PasswordStrength {
    var score = 0

    if (password.length >= 8) score++
    if (password.length >= 12) score++
    if (password.any { it.isUpperCase() }) score++
    if (password.any { it.isLowerCase() }) score++
    if (password.any { it.isDigit() }) score++
    if (password.any { !it.isLetterOrDigit() }) score++

    return when {
        score <= 2 -> PasswordStrength.WEAK
        score <= 4 -> PasswordStrength.FAIR
        score <= 5 -> PasswordStrength.GOOD
        else -> PasswordStrength.STRONG
    }
}

@Composable
fun PasswordStrengthMeter(password: String) {
    val strength = calculatePasswordStrength(password)
    val (color, label) = when (strength) {
        PasswordStrength.WEAK -> Color(0xFFE53935) to "Weak"
        PasswordStrength.FAIR -> Color(0xFFFFA726) to "Fair"
        PasswordStrength.GOOD -> Color(0xFF42A5F5) to "Good"
        PasswordStrength.STRONG -> Color(0xFF4CAF50) to "Strong"
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            "Password Strength",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(4) { index ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(6.dp)
                        .background(
                            color = if (index < strength.ordinal + 1) color else Color.LightGray,
                            shape = RoundedCornerShape(3.dp)
                        )
                )
            }
        }

        Text(
            label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}