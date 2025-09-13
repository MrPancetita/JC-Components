package com.example.jccomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.jccomponents.ui.theme.Typography

/**
 * Project: JC Components
 * From: com.example.jccomponents
 * Created by: Contr
 * On: 13/09/2025 at 19:33
 * Creado en Settings -> Editor -> File and Code Templates
 */

@Preview(showBackground = true)
@Composable
private fun DialogPreview() {
    SergioDialog({},{},{},{})
}



@Composable
fun SergioDialog(onDismissRequest: () -> Unit,
               onConfirm: () -> Unit,
               onCancel: () -> Unit,
               onNeutral: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest()}) {
        Card(Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(dimensionResource(R.dimen.common_padding_default))) {
            Column(Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.common_padding_default)),
                verticalArrangement = Arrangement.Center) {
                Text(stringResource(R.string.dialog_title),
                    style = Typography.headlineMedium
                )
                Text(stringResource(R.string.dialog_message),
                    style = Typography.bodyMedium
                )

                Row(Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    TextButton(onClick = { onNeutral() }) {
                        Text(stringResource(R.string.btn_skip))
                    }

                    Spacer(Modifier.weight(1f))

                    TextButton(onClick = { onCancel() }) {
                        Text(stringResource(R.string.common_cancel))
                    }
                    TextButton(onClick = { onConfirm() }) {
                        Text(stringResource(R.string.common_ok))
                    }

                }

            }


        }


    }

}




@Composable
fun MyAlertDialog(onDismissRequest: () -> Unit) {
    AlertDialog(
        title = {
            Text("Sergio to loco")
        },
        text = {
            Text("Dominando el Compose")
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text("Cancel")
            }

        }
    )
}