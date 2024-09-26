package com.example.projeto_app_academia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projeto_app_academia.ui.screen.HistoricoScreen
import com.example.projeto_app_academia.ui.screen.HomeScreen
import com.example.projeto_app_academia.ui.screen.LoginScreen
import com.example.projeto_app_academia.ui.screen.SignUpScreen
import com.example.projeto_app_academia.ui.screen.treino.TreinoScreen
import kotlinx.coroutines.launch

object AcademiaRotas {
    val TELA_HOME = "tela_home"
    val TELA_lOGIN = "tela_login"
    val TELA_SIGNUP = "tela_signup"
    val TELA_TREINO = "tela_treino"
    val TELA_HISTORICO = "tela_historico"
}

@Preview(
    device = Devices.PIXEL
)
@Composable
fun AcademiaApp(){

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed)

    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent(navController, drawerState) },
        content = {
            NavHost(
                navController = navController,
                startDestination = AcademiaRotas.TELA_HOME
            ){
                composable(AcademiaRotas.TELA_HOME) {
                    HomeScreen(drawerState, navController)
                }
                composable(AcademiaRotas.TELA_lOGIN) {
                    LoginScreen(drawerState, navController)
                }
                composable(AcademiaRotas.TELA_SIGNUP) {
                    SignUpScreen(drawerState, navController)
                }
                composable(AcademiaRotas.TELA_TREINO) {
                    TreinoScreen(drawerState, navController)
                }
                composable(AcademiaRotas.TELA_HISTORICO) {
                    HistoricoScreen(drawerState, navController)
                }
            }
        }
    )
}

@Composable
private fun DrawerContent(navController: NavController, drawerState: DrawerState) {

    val coroutineScope = rememberCoroutineScope()



    val currentBack by navController.currentBackStackEntryAsState()
    val rotaAtual = currentBack?.destination?.route ?: AcademiaRotas.TELA_HOME

    val ehRotaHome = rotaAtual == AcademiaRotas.TELA_HOME
    val ehRotaLogin = rotaAtual == AcademiaRotas.TELA_lOGIN
    val ehRotaSignUp = rotaAtual == AcademiaRotas.TELA_SIGNUP
    val ehRotaTreino = rotaAtual == AcademiaRotas.TELA_TREINO
    val ehRotaHistorico = rotaAtual == AcademiaRotas.TELA_HISTORICO


    Column(

        modifier = Modifier
            .width(300.dp)
            .background(Color.White)
            .fillMaxHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "WorkoutWise",
                color = Color(0xFF275367),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
            thickness = 1.dp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(40.dp))


        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaHome)),
            modifier = Modifier.padding(20.dp, 5.dp),
            onClick = {
                navController.navigate(AcademiaRotas.TELA_HOME)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Tela Home",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehRotaHome)
            )
            Text(
                text = "Home", fontSize = 30.sp,
                color = getColorTexto(ehRotaHome)

            )
        }
        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaLogin)),
            modifier = Modifier.padding(20.dp, 5.dp),
            onClick = {
                navController.navigate(AcademiaRotas.TELA_lOGIN)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {
            Icon(
                imageVector = Icons.Default.AccountBox,
                contentDescription = "Tela Login",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehRotaLogin)
            )
            Text(
                text = "Login", fontSize = 30.sp,
                color = getColorTexto(ehRotaLogin)

            )
        }
        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaSignUp)),
            modifier = Modifier.padding(20.dp, 5.dp),
            onClick = {
            navController.navigate(AcademiaRotas.TELA_SIGNUP)
            coroutineScope.launch {
                drawerState.close()
            }
        }) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Tela SignUp",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehRotaSignUp)
            )
            Text(
                text = "SignUp", fontSize = 30.sp,
                color = getColorTexto(ehRotaSignUp)

            )
        }
        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaTreino)),
            modifier = Modifier.padding(20.dp, 5.dp),
            onClick = {
                navController.navigate(AcademiaRotas.TELA_TREINO)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Tela Treino",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehRotaTreino)
            )
            Text(
                text = "Treino", fontSize = 30.sp,
                color = getColorTexto(ehRotaTreino)

            )
        }
        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaHistorico)),
            modifier = Modifier.padding(20.dp, 5.dp),
            onClick = {
                navController.navigate(AcademiaRotas.TELA_HISTORICO)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Tela Treino",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehRotaHistorico)
            )
            Text(
                text = "Historico", fontSize = 30.sp,
                color = getColorTexto(ehRotaHistorico)

            )
        }
    }
}

fun getColorTexto(estateSelecionada: Boolean): Color {
    if (estateSelecionada) {
        return Color.White
    } else {
        return Color(0xFF174F79)
    }
}


fun getColorMenu(estateSelecionada: Boolean): Color {
    if (estateSelecionada) {
        return Color(0xFF174F79)
    } else {
        return Color.Transparent
    }
}
