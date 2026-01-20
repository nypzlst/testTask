package com.example.testtask

import android.R.attr.onClick
import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testtask.ui.theme.TestTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Row(
                        modifier = Modifier.statusBarsPadding().fillMaxSize().padding(16.dp)
                    ) {
                        TextTODOField (
                            modifier = Modifier.weight(1f)
                        )
                        FilledButton(onClick = {
                            println("Кнопка нажата!")
                        })
                    }

                }
            }
        }
    }
}


@Composable
fun FilledButton(onClick: () -> Unit, modifier: Modifier = Modifier){
    Button(
        onClick = onClick,
        modifier = modifier
    ){
        Text(text = "Add")
    }
}

@Composable
fun TextTODOField(modifier: Modifier = Modifier){
    TextField(
        state = rememberTextFieldState(initialText = ""),
        placeholder = {Text(text = "Write TODO")},
        modifier = modifier
    )
}