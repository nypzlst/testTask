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
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val tasks = remember { mutableStateListOf<Task>() }
            var todoText by remember { mutableStateOf(TextFieldValue("")) }

            TestTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->(
                        Column(
                            modifier = Modifier.fillMaxSize().padding(innerPadding)
                        ) {
                            Row(
                                modifier = Modifier.statusBarsPadding().fillMaxWidth().padding(16.dp)
                            ) {
                                TextTODOField (
                                    modifier = Modifier.weight(1f),
                                    value = todoText,
                                    onValueChange = { todoText = it },
                                )

                                FilledButton(onClick = {
                                    if(todoText.text.isNotBlank()){
                                        tasks.add(Task(id = tasks.size, title = todoText.text))
                                        todoText = TextFieldValue("")
                                    }

                                })

                            }
                            TaskList(tasks = tasks)
                        }
                        )
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
fun TextTODOField(modifier: Modifier = Modifier, value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit,){
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {Text(text = "Write TODO")},
        modifier = modifier
    )
}

data class Task(
    val id:Int,
    val title: String
)

@Composable
fun TaskRow(tasks: Task){
    Text(
        text = "Task: ${tasks.title}",
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun TaskList(tasks: List<Task>){
    LazyColumn {
        items(tasks){
            task -> TaskRow(task)
        }
    }
}


