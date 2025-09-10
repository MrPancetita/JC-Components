@file:OptIn(ExperimentalGlideComposeApi::class)

package com.example.jccomponents

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.jccomponents.ui.theme.JCComponentsTheme

/**
 * Project: JC Components
 * From: com.example.jccomponents
 * Created by: Contr
 * On: 31/08/2025 at 23:35
 * Creado en Settings -> Editor -> File and Code Templates
 */


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ContentPreview()
{
    JCComponentsTheme {
        ContentView(Modifier) { }
    }

}


@Composable
fun ContentView(modifier: Modifier, onContentEvent: (Boolean) -> Unit) {
    Column(modifier
        .verticalScroll(rememberScrollState())) {
        var isSwitchChecked by remember { mutableStateOf(true) }
        var isSkipped by remember {mutableStateOf(false)}

        Text(text = stringResource(if(isSwitchChecked) R.string.sw_hide_fab else R.string.sw_show_fab))
        Switch(
            checked = isSwitchChecked,
            onCheckedChange = { currentValue ->
                isSwitchChecked = currentValue
                onContentEvent(isSwitchChecked)
            })

        if (!isSkipped) {
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.common_padding_default))
            ) {
                ConstraintLayout(
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(R.dimen.common_padding_default),
                            end = dimensionResource(R.dimen.common_padding_default),
                            top = dimensionResource(R.dimen.common_padding_default)
                        )
                ) {

                    val (imgCard, tvTitle, tvDescription, btnBuy, btnSkip) = createRefs()

                    val image =
                        ContextCompat.getDrawable(LocalContext.current, R.drawable.angry_doge)
                    Image(
                        bitmap = image!!.toBitmap().asImageBitmap(),
                        contentDescription = "doge angry",
                        modifier = Modifier
                            .constrainAs(imgCard) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)

                            }
                            .size(dimensionResource(R.dimen.img_size))
                            .background(Color.Cyan)
                    )
                    val paddingDefault = dimensionResource(R.dimen.common_padding_default)
                    Text(
                        stringResource(R.string.title_black_friday),
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .constrainAs(tvTitle) {
                                start.linkTo(imgCard.end, margin = paddingDefault)
                                end.linkTo(parent.end)
                                top.linkTo(imgCard.top)
                                width = Dimension.fillToConstraints
                            })
                    Text(
                        stringResource(R.string.large_description),
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .constrainAs(tvDescription) {
                                start.linkTo(tvTitle.start)
                                end.linkTo(tvTitle.end)
                                top.linkTo(tvTitle.bottom)
                                bottom.linkTo(imgCard.bottom)
                                width = Dimension.fillToConstraints
                            })
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .constrainAs(btnBuy) {
                                end.linkTo(parent.end)
                                top.linkTo(tvDescription.bottom)

                            }) {
                        Icon(Icons.Default.Shop, "buy button")
                        Text(stringResource(R.string.btn_buy))
                    }
                    TextButton(
                        onClick = {
                            isSkipped = true
                        },
                        modifier = Modifier
                            .constrainAs(btnSkip) {
                                end.linkTo(btnBuy.start)
                                top.linkTo(btnBuy.top)
                            }) {
                        Text(stringResource(R.string.btn_skip))
                    }
                }

            }
        }

        var urlValue by remember { mutableStateOf("https://ae-pic-a1.aliexpress-media.com/kf/S4ccab0751a844afeaac2337ad2c6b34aT.jpg_220x220q75.jpg_.avif") }
        Card(Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.common_padding_min)))
        {
            ConstraintLayout {
                val (imgUrl, cForm) = createRefs()
                GlideImage(
                    model = urlValue,
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(imgUrl) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        }
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.img_cover_height))
                        .background(colorResource(R.color.yellow_500)),
                    loading = placeholder(R.drawable.ic_baseline_timer_24),
                    failure = placeholder(R.drawable.ic_baseline_broken_image_24),
                    contentScale = ContentScale.Crop
                )
                
                Column (Modifier
                    .constrainAs(cForm) {
                        top.linkTo(imgUrl.bottom)
                    }
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.common_padding_default))){
                    Text(
                        stringResource(R.string.title_black_friday),
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .fillMaxWidth())

                    var emailValue by remember { mutableStateOf("")}
                    OutlinedTextField(value = emailValue,
                        onValueChange = {emailValue = it},
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.common_padding_default)),
                        label = { Text(stringResource(R.string.hint_email))},
                        trailingIcon = {
                            if (emailValue.isNotEmpty()) {
                                Icon(Icons.Default.Clear,
                                    null,
                                    modifier = Modifier.clickable { emailValue = "" })
                            }},
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                }
            }
        }
    }
}


