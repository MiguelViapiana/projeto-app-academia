package com.example.projeto_app_academia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projeto_app_academia.ui.screen.HomeScreen

@Preview(
    device = Devices.PIXEL
)
@Composable
fun AcademiaApp(){
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent() },
        content = { HomeScreen(drawerState) }
    )
}

@Composable
private fun DrawerContent() {
    Column(
        modifier = Modifier
            .width(300.dp)
            .background(Color.White)
            .padding(30.dp)
            .fillMaxHeight()
    ) {
        Text(text = "Item 1", fontSize = 30.sp)
    }
}


//ModalNavigationDrawer(
//drawerState = drawerState,
//drawerContent = { DrawerContent() },
//content = { TarefasSceen(drawerState) }
//)
//}
//
//@Composable
//private fun DrawerContent() {
//    Column(
//        modifier = Modifier
//            .width(300.dp)
//            .background(Color.White)
//            .padding(30.dp)
//            .fillMaxHeight()
//    ) {
//        Spacer(modifier = Modifier.height(70.dp))
//        Text(text = "Item 1", fontSize = 30.sp)
//        Text(text = "Item 2", fontSize = 30.sp)
//        Text(text = "Item 3", fontSize = 30.sp)
//    }
//}