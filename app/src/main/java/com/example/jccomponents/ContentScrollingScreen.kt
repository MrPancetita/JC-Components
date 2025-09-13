@file:OptIn(ExperimentalGlideComposeApi::class)

package com.example.jccomponents

import android.graphics.Bitmap
import android.util.Log
import android.webkit.URLUtil
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Umbrella
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
        ContentView(Modifier) { _, _, _ -> }
    }

}


@Composable
fun ContentView(modifier: Modifier, onContentEvent: (String?, Boolean?, Boolean?) -> Unit) {

    var isSkipped by remember {mutableStateOf(false)}
    var colorMain by remember { mutableStateOf(Color.Transparent) }

    Column(modifier
        .verticalScroll(rememberScrollState())
        .background(colorMain)) {

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
                    val buyMsg = stringResource(R.string.btn_buy)
                    Button(
                        onClick = { onContentEvent (buyMsg, null, null)},
                        modifier = Modifier
                            .constrainAs(btnBuy) {
                                end.linkTo(parent.end)
                                top.linkTo(tvDescription.bottom)

                            }) {
                        Icon(Icons.Default.Shop, "buy button")
                        Text(buyMsg)
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

        val defaultUrlValue = "https://ae-pic-a1.aliexpress-media.com/kf/S4ccab0751a844afeaac2337ad2c6b34aT.jpg_220x220q75.jpg_.avif"
        var urlValue by remember { mutableStateOf("") }
        Card(Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.common_padding_min)))
        {
            ConstraintLayout {
                val (imgUrl, cForm) = createRefs()
                GlideImage(
                    model = urlValue.ifEmpty { defaultUrlValue },
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
                    var emailBackgroundColor by remember {mutableStateOf(Color.Transparent)}
                    OutlinedTextField(value = emailValue,
                        onValueChange = {emailValue = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.common_padding_default))
                            .onFocusChanged {
                                emailBackgroundColor = if (it.isFocused) {
                                    Color.Yellow
                                } else {
                                    Color.Transparent
                                }
                            }
                            .background(emailBackgroundColor),
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
                    OutlinedTextField(value = urlValue,
                        onValueChange = {urlValue = it},
                        singleLine = true,
                        label = {
                            Text(stringResource(R.string.hint_image_url))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.common_padding_default)),
                        trailingIcon = {
                            if (urlValue.isNotEmpty()) {
                                Icon(Icons.Default.Clear,
                                    null,
                                    modifier = Modifier.clickable { urlValue = "" })
                            }},
                        isError = urlValue.isNotEmpty() && !URLUtil.isValidUrl(urlValue),
                        supportingText = {
                            Text( if (urlValue.isEmpty()) stringResource(R.string.helper_text_required)
                                else if (!URLUtil.isValidUrl(urlValue)) stringResource(R.string.error_invalid_url)
                                else stringResource(R.string.helper_text_required))
                        })

                    var passwordValue by remember { mutableStateOf("") }
                    var isPasswordVisible by remember { mutableStateOf(false) }
                    var isPasswordCheckBoxChecked by remember { mutableStateOf(false) }

                    OutlinedTextField(value = passwordValue,
                        onValueChange = {passwordValue = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.common_padding_default)),
                        label = { Text(stringResource(R.string.hint_password))},
                        singleLine = true,
                        trailingIcon = {
                                Icon(if (isPasswordVisible) Icons.Default.Visibility
                                    else Icons.Default.VisibilityOff,
                                    contentDescription = null,
                                    modifier = Modifier.clickable {
                                        isPasswordVisible = !isPasswordVisible
                                    })
                        },
                        visualTransformation =
                            if (isPasswordVisible) VisualTransformation.None
                            else PasswordVisualTransformation(),
                        enabled = isPasswordCheckBoxChecked
                    )

                    Row (
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isPasswordCheckBoxChecked,
                            onCheckedChange = { isChecked -> isPasswordCheckBoxChecked = isChecked }
                        )
                        Text(stringResource(R.string.cb_enable_password))

                        var isSwitchChecked by remember { mutableStateOf(true) }

                        Spacer(Modifier.weight(1f))

                        Text(text = stringResource(if(isSwitchChecked) R.string.sw_hide_fab else R.string.sw_show_fab))
                        Switch(
                            checked = isSwitchChecked,
                            onCheckedChange = { currentValue ->
                                isSwitchChecked = currentValue
                                onContentEvent(null, isSwitchChecked, null)
                            })
                    }

                    var cpVisible by remember { mutableStateOf(true) }
                    var selectedChip by remember { mutableStateOf(false) }
                    var chipValue by remember {mutableStateOf("contreras.engineer@outlook.com")}
                    val context = LocalContext.current

                    if (cpVisible) {
                        InputChip(
                            selected = selectedChip,
                            label = {
                                Text(chipValue)
                            },
                            onClick = {
                                selectedChip = !selectedChip
                                Toast.makeText(context, chipValue, Toast.LENGTH_SHORT).show()
                            },
                            avatar = if (selectedChip) {
                                {
                                    Icon(
                                        Icons.Default.Done,
                                        contentDescription = null,
                                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                                    )
                                }
                            } else {
                                null
                            },
                            trailingIcon = {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(FilterChipDefaults.IconSize)
                                        .clickable {
                                            cpVisible = false
                                        })


                            }
                        )
                    }

                    var sliderValue by remember { mutableStateOf(7f) }
                    Slider(value = sliderValue,
                        onValueChange = {sliderValue = it},
                        valueRange = 0f..10f,
                        steps = 90,
                        onValueChangeFinished = {
                            chipValue = context.getString(R.string.sld_value, sliderValue)
                        }
                    )

                    HorizontalDivider(Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.common_padding_middle)))


                    Text(
                        stringResource(R.string.title_black_friday),
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        stringResource(R.string.large_description),
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    val colors = listOf(
                        stringResource(R.string.btn_red),
                        stringResource(R.string.btn_green),
                        stringResource(R.string.btn_blue))

                    var selectedIndex by remember { mutableStateOf(-1) }
                    colorMain = when(selectedIndex) {
                        0 -> Color.Red
                        1 -> Color.Green
                        2 -> Color.Blue
                        else -> Color.Transparent
                    }



                    Row(
                        modifier = Modifier
                            .padding(top=dimensionResource(R.dimen.common_padding_min))
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center)
                     {
                        SingleChoiceSegmentedButtonRow{
                            colors.forEachIndexed { index, label ->
                                SegmentedButton(
                                    shape = SegmentedButtonDefaults.itemShape(
                                        index = index,
                                        count = colors.size
                                    ),
                                    onClick = { selectedIndex = index },
                                    selected = index == selectedIndex,
                                    label = {
                                        Text(label)
                                    })
                            }
                        }
                    }

                    var count by remember { mutableStateOf(21) }
                    BadgedBox(
                        modifier = Modifier
                            .padding(top = dimensionResource(R.dimen.common_padding_default)),
                        badge = {
                            if (count > 0) {
                                Badge{
                                    Text("$count")
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications icon",
                            modifier = Modifier
                                .clickable {
                                    onContentEvent(null, null, true)

                                })
                    }

                    Box(Modifier
                        .fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd) {
                        Button(onClick = { count++ },
                            modifier = Modifier
                                .padding(vertical = dimensionResource(R.dimen.common_padding_default))) {
                            Text(stringResource(R.string.common_ok))
                        }
                    }










                }
            }
        }
    }
}


