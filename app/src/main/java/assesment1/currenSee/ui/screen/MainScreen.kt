package assesment1.currenSee.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import assesment1.currenSee.R
import assesment1.currenSee.navigation.Screen
import assesment1.currenSee.ui.theme.AssesmentMobproTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.About.route)
                        }
                    ){
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ){padding ->
        ScreenContent(Modifier.padding(padding) )
    }
}

enum class Currency {
    IDR , USD
}

@Composable
fun ScreenContent(modifier: Modifier) {
    var nilai by rememberSaveable { mutableStateOf("") }
    var nilaiError by rememberSaveable { mutableStateOf(false) }
    var result by rememberSaveable { mutableStateOf("") }

    val radioOptions = listOf(Currency.IDR, Currency.USD)
    var conversion by rememberSaveable { mutableStateOf(radioOptions[0]) }
    val context = LocalContext.current
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.app_intro)
        )

        OutlinedTextField(
            value = nilai,
            onValueChange = { nilai = it },
            label = { Text(text = stringResource(R.string.nilai)) },
            isError = nilaiError,
            trailingIcon = { IconPicker(nilaiError, "") },
            supportingText = { ErrorHint(nilaiError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row (
            modifier = Modifier
                .padding(top = 6.dp)
        ) {
            radioOptions.forEach { currency ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                ) {
                    RadioButton(
                        selected = conversion == currency,
                        onClick = { conversion = currency }
                    )
                    Text(
                        text = when (currency) {
                            Currency.IDR -> "IDR to USD"
                            Currency.USD -> "USD to IDR"
                        }
                    )
                }
            }
        }

        Button(
            onClick = {
                nilaiError = (nilai == "" || nilai == "0")
                if (!nilaiError) {
                    result = convertCurrency(input = nilai, conversion = conversion )
                }
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(R.string.hitung))
        }

        if (result != "") {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = result,
                style = MaterialTheme.typography.displaySmall
            )
            Button(
                onClick = {
                    shareData(
                        context = context,
                        message = context.getString(R.string.bagikan_template,result)
                    )
                },
                modifier = Modifier.padding(top=8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical=16.dp)
            ) {
                Text(text = stringResource(id = R.string.bagikan))
            }
        }
    }
}

fun convertCurrency(input: String, conversion: Currency): String {
    val amount = input.toDoubleOrNull() ?: return "Input tidak valid"
    return when (conversion) {
        Currency.IDR -> "${amount / 15896} USD"
        Currency.USD -> "${amount * 15896} IDR"
    }
}

@Composable
fun  IconPicker(isError: Boolean, unit: String){
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError){
        Text(text = stringResource(R.string.input_invalid))
    }
}


private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    AssesmentMobproTheme {
        MainScreen(rememberNavController())
    }
}