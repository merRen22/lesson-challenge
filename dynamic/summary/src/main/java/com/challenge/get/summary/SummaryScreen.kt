package com.challenge.get.summary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.challenge.get.base.utils.tFormatParserMiliseconds
import com.challenge.get.base.utils.parse
import com.challenge.get.base.viewmodel.NavigationViewModel
import com.challenge.get.model.Summary
import java.util.*
import java.util.concurrent.TimeUnit

@Composable
fun SummaryScreen(
    summaryViewModel: SummaryViewModel,
    navigationViewModel: NavigationViewModel
) {
    val summaries = summaryViewModel.summaries.observeAsState()
    Scaffold(
        topBar = { SummaryTopBar() },
        content = {
            summaries.value?.let {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    LazyColumn {
                        item {
                            it.forEach { summary ->
                                Summary(summary = summary)
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
fun Summary(summary: Summary) {

    val sDate: Date = parse(tFormatParserMiliseconds, summary.started)
    val cDate: Date = parse(tFormatParserMiliseconds, summary.completed)
    val diffInMs: Long = cDate.time - sDate.time
    val diffInSec: Long = TimeUnit.MILLISECONDS.toSeconds(diffInMs)

    Row {
        Text(
            text = "ID - ${summary.id} | Duration $diffInSec s",
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun SummaryTopBar() {
    TopAppBar(
        title = { Text(text = "Your Summary") },
        backgroundColor = MaterialTheme.colors.surface
    )
}