package de.barut.mybeworbenapp.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.barut.mybeworbenapp.R

class ComposableTab(private val subtitel: Int = 0) {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun Tab(actions: @Composable () -> Unit = {},content : @Composable () -> Unit = {}) {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Column {
                        Text(text = stringResource(id = R.string.tab_titel))
                        if (subtitel != 0)
                            Text(text = stringResource(id = subtitel), fontSize = 14.sp)
                    }
                },
                    actions = { actions() })
            },
            content = { content() }
        )
    }

    @Composable
    fun SetIconToMenu(drawable: Int = 0, text: String = "", onclick: () -> Unit = {}) {
        IconButton(onClick = { onclick() }) {
            Row(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
                if (drawable != 0)
                    Icon(painter = painterResource(id = drawable), contentDescription = "")
                if (text != "")
                    Text(text = text)
            }
        }
    }
}