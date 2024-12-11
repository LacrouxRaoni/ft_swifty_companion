package com.api.swift_companion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.api.swift_companion.ui.theme.Swift_companionTheme


import android.view.animation.AnimationUtils
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

import android.text.Editable
import android.text.TextWatcher
import android.animation.ObjectAnimator
import android.animation.Animator
import android.animation.ValueAnimator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val editText = findViewById<EditText>(R.id.etBlinkingInput)

        // Inicializa a cor padrão do hint
        val defaultHintColor = editText.currentHintTextColor

        // Animação de piscar para o hint
        val blinkAnimation = ValueAnimator.ofFloat(1f, 0f).apply {
            duration = 500
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { animator ->
                val alpha = animator.animatedValue as Float
                val hintColor = defaultHintColor and 0x00FFFFFF or ((alpha * 255).toInt() shl 24)
                editText.setHintTextColor(hintColor)
            }
            start()
        }

        // Para a animação ao digitar
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    blinkAnimation.cancel() // Para a animação
                    editText.setHintTextColor(defaultHintColor) // Restaura a cor original
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
