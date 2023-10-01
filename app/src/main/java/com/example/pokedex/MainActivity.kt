package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    ScreenMain()
                }
            }
        }
    }
}


@Composable
@Preview
fun ScreenMain() {
    val roundCornerShape = RoundedCornerShape(10.dp)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 0.dp, end = 0.dp, top = 10.dp, bottom = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .width(350.dp)
                .height(150.dp),
            painter = painterResource(id = R.drawable.img), contentDescription = "Logo emblema"
        )

        mySpacer(size = 10)
        Box(
            modifier = Modifier
                .width(400.dp)
                .height(200.dp)
                .padding(start = 10.dp, end = 10.dp, top = 0.dp, bottom = 0.dp)
                .border(
                    width = 2.8.dp,
                    color = Color.Red.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(32.dp)
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    modifier = Modifier
                        .width(350.dp)
                        .height(150.dp)
                        .padding(10.dp), painter = painterResource(
                        id = R.drawable.img_1
                    ), contentDescription = "darkrai"
                )
                Text(
                    text = "Nombre = Darkrai",
                    color = Color.Blue,
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp),
            enabled = true,
            onClick = {
            }, colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Red,
                contentColor = Color.White,
                disabledContainerColor = Color.DarkGray,
                disabledContentColor = Color.White
            ), shape = roundCornerShape
        ) {
            Text(text = "Capturar", fontSize = 15.sp)
        }
        mySpacer(size = 10)
        Text(
            text = "Información: Has recorrido 10 mts",
            color = Color.Red,
            fontSize = 15.sp,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Monospace
        )
    }
}
@Composable
fun mySpacer(size: Int) {
    Spacer(
        modifier = Modifier
            .height(size.dp)
            .width(0.dp)
    )

}

