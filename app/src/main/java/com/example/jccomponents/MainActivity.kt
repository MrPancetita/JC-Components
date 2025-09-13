package com.example.jccomponents

import android.app.AlertDialog
import android.media.tv.AdRequest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.estimateAnimationDurationMillis
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jccomponents.ui.theme.JCComponentsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JCComponentsTheme {
                ContentMain {
                    finish()
                }
            }
        }
    }
}

@Composable
fun ContentMain(onFinish: ()-> Unit) {
    val context = LocalContext.current
    val colorBar = context.getColor(R.color.purple_200)
    //Snackbar
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    //Menu
    var expandedMenu by remember { mutableStateOf(false) }
    //FloationActionButton
    var fabPosition by remember { mutableStateOf(FabPosition.Center) }
    var showFab by remember { mutableStateOf(true)}
    //Dialog
    var openDialog by remember { mutableStateOf(false) }



    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        bottomBar = {
            BottomAppBar (containerColor = Color(colorBar)) {
                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                    val msg = stringResource(R.string.message_success_event)
                    IconButton (onClick = {
                        scope.launch { snackbarHostState.showSnackbar(msg) }
                    }) {
                        Icon(Icons.Filled.Menu,
                            contentDescription = "Menu") }
                }
                Spacer(Modifier.weight(1f, true))  // Mueve los 3 puntos a la derecha
                Box {
                    val msgBye = stringResource(R.string.message_bye)
                    val exitStr = stringResource(R.string.action_exit)
                    IconButton(onClick = {
                        expandedMenu = true
                    })
                    {
                        Icon(Icons.Filled.MoreVert,
                            contentDescription = "Options")
                    }

                    DropdownMenu(expanded = expandedMenu,
                        onDismissRequest = {expandedMenu = false}
                    ) {
                        DropdownMenuItem(
                            text = { Text(exitStr) },
                            onClick = {
                                expandedMenu = false
                                Toast.makeText(context, msgBye, Toast.LENGTH_SHORT).show()
                                onFinish()
                            }
                        )
                    }

                }
            }

        },
        floatingActionButton = {
            AnimatedVisibility(visible = showFab,
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                FloatingActionButton(onClick = {
                    fabPosition = if (fabPosition == FabPosition.End) {
                        FabPosition.Center
                    } else {
                        FabPosition.End
                    }
                }) {
                    Icon(
                        Icons.Default.SwapHoriz,
                        contentDescription = null
                    )
                }
            }
            /*if (showFab) {
                FloatingActionButton(onClick = {
                    fabPosition = if (fabPosition == FabPosition.End) {
                        FabPosition.Center
                    } else {
                        FabPosition.End
                    }
                }) {
                    Icon(
                        Icons.Default.SwapHoriz,
                        contentDescription = null
                    )
                }
            }*/
        },
        floatingActionButtonPosition = fabPosition) { innerPadding ->
        Box(Modifier.fillMaxSize()) {


            val actionGo = stringResource(R.string.action_go)
            val recordMsg = stringResource(R.string.message_record)
            ContentView(Modifier.padding(innerPadding)) { msg, isSwitchChecked, isDialogShow ->
                msg?.let {
                    scope.launch {
                        val result = snackbarHostState
                            .showSnackbar(msg, actionLabel = actionGo, duration = SnackbarDuration.Indefinite)
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                Toast.makeText(context, recordMsg, Toast.LENGTH_SHORT).show()
                            } SnackbarResult.Dismissed -> {

                            }
                        }
                    }
                }
                isSwitchChecked?.let {
                    showFab = isSwitchChecked
                }
                isDialogShow?.let {
                    openDialog = isDialogShow

                }


            }
            if (openDialog) {
                SergioDialog(
                    onDismissRequest = {
                        openDialog = false
                        Log.i("Sergio", "Dismiss") },
                    onConfirm = {
                        openDialog = false
                        Log.i("Sergio", "Confirmar")},
                    onCancel = { openDialog = false
                        Log.i("Sergio", "Cancelar") },
                    onNeutral = { openDialog = false
                        Log.i("Sergio", "Saltar") }
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JCComponentsTheme {
        ContentMain{}
    }
}