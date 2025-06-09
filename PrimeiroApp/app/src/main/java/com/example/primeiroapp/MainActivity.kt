package com.example.primeiroapp

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.viewinterop.AndroidView


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        ProdutoItem()
                    }
                }
            }
        }
    }
}

@Composable
fun ProdutoItem() {
    val context = LocalContext.current
    var nome by remember { mutableStateOf("") }
    var curso by remember { mutableStateOf("") }
    var tel by remember { mutableStateOf("") }
    var obs by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confSenha by remember { mutableStateOf("") }

    var botaoVisivel by remember { mutableStateOf(true) }

    var videovisiivel by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .height(180.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Red, Color.Blue
                        )
                    )
                )
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.avatar_woman),
                contentDescription = "avatar image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .offset(y = 70.dp)
                    .size(150.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center)
            )
        }

        Spacer(Modifier.height(55.dp))

        Column(
            Modifier.padding(16.dp)
        ) {
            Text(
                text = "Cadastre-se",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W500,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            //CAMPO NOME
            OutlinedTextField(
                value = nome,
                onValueChange = {nome = it},
                label = { Text("nome") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            //CAMPO TEL
            OutlinedTextField(
                value = tel,
                onValueChange = { tel = it },
                label = { Text("Telefone") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            //CAMPO Curso
            OutlinedTextField(
                value = curso,
                onValueChange = { curso = it },
                label = { Text("Curso") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            //CAMPO OBS
            OutlinedTextField(
                value = obs,
                onValueChange = { obs = it },
                label = { Text("Observação") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text("Senha") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
                )
            )


            Spacer(modifier = Modifier.height(32.dp))

            //BOTAO CADASTRO
            Button(
                onClick = {
                    println("Nome: $nome")
                    println("Telefone: $tel")
                    println("Senha: $senha")
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = nome.isNotBlank() && tel.isNotBlank() &&
                        senha.isNotBlank()
            ) {
                Text(text = "Cadastrar")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Mostrar o vídeo quando videoVisivel for true
            if (videovisiivel) {
                AndroidView(
                    factory = { ctx ->
                        VideoView(ctx).apply {
                            setVideoURI(Uri.parse("android.resource://${ctx.packageName}/${R.raw.seu_video}"))
                            setOnPreparedListener { it.isLooping = false }
                            setOnCompletionListener {
                                videovisiivel = false
                                botaoVisivel = true
                            }
                            start()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            //botao secreto
            if (botaoVisivel) {
                Button(
                    onClick = {
                        botaoVisivel = false
                        videovisiivel = true
                        },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text("Bora")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            ProdutoItem()
        }
    }
}