package com.example.androidsampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.contentsquare.android.Contentsquare
import com.contentsquare.android.compose.analytics.TriggeredOnResume

class ComposeSimpleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // Display simple compose screen
                SimpleScreen()
            }
        }
    }
}

@Composable
fun SimpleScreen() {
    // Send screen name to Contentsquare tracking when screen is displayed
    TriggeredOnResume { Contentsquare.send("Simple-Compose-Activity") }

    Scaffold (
        topBar = { TopAppBar(
            title = {
                Text(text = stringResource(R.string.app_name))
            }
        )}
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.text_simple_compose_activity))
        }
    }}

@Preview
@Composable
fun PreviewSimpleScreen() {
    MaterialTheme {
        SimpleScreen()
    }
}