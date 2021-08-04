package com.challenge.get.lesson

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.challenge.get.base.utils.HexToJetpackColor
import com.challenge.get.base.viewmodel.NavigationViewModel
import com.challenge.get.model.Input
import com.challenge.get.model.Lesson
import com.challenge.get.model.Step

@Composable
fun LessonScreen(
    lessonViewModel: LessonViewModel,
    navigationViewModel: NavigationViewModel
) {
    val step = lessonViewModel.step.observeAsState()
    val textState = remember { mutableStateOf(TextFieldValue()) }
    Scaffold(
        topBar = { LessonTopBar() },
        content = {
            step.value?.let {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Sentence(it, textState)

                    val active = textState.value.text != "" || it.input == null

                    CompleteButton(active) {
                        if(active){
                            lessonViewModel.completeStep()
                            if ( lessonViewModel.steps.size == 1) {
                                navigationViewModel.lessonSuccess()
                            } else {
                                lessonViewModel.nextStep()
                            }
                        }
                    }
                }
            } ?: ProgressIndicator()
        }
    )
}

@Composable
fun ProgressIndicator() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun LessonTopBar() {
    TopAppBar(
        title = { Text(text = "Coding Challenge") },
        backgroundColor = MaterialTheme.colors.surface
    )
}

@Composable
fun Sentence(
    lesson: Lesson,
    textState: MutableState<TextFieldValue>
) {
    var sum = ""

    Row(
        modifier = Modifier.padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        lesson.content.mapIndexed { index, content ->
            if (
                lesson.input != null &&
                sum.length == lesson.input!!.startIndex &&
                sum.length + content.text.length >= lesson.input!!.endIndex
            ) {
                StepInput(content, lesson.input!!, sum.length, textState)
            } else {
                StepText(content)
            }

            sum += content.text
        }
    }
}

@Composable
fun StepText(step: Step) {
    Text(
        text = step.text,
        style = MaterialTheme.typography.body1,
        color = HexToJetpackColor.getColor(step.color)
    )
}

@Composable
fun StepInput(step: Step, input: Input, acc: Int, textState: MutableState<TextFieldValue>) {
    if (input.startIndex - acc != 0) {
        StepText(
            Step(
                text = step.text.substring(0, input.startIndex - acc),
                color = step.color
            )
        )
    }

    TextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        textStyle = TextStyle(color = HexToJetpackColor.getColor(step.color)),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
    )

    if (input.endIndex - acc < step.text.length) {
        StepText(
            Step(
                text = step.text.substring(input.endIndex - acc, step.text.length),
                color = step.color
            )
        )
    }
}

@Composable
fun CompleteButton(active: Boolean , nextLesson: () -> Unit) {
    Button(
        onClick = nextLesson,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .alpha(
                if (active) 1f else 0.5f
            )
    ) {
        Text(
            text = "Run"
        )
    }
}

