package de.barut.mybeworbenapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import de.barut.mybeworbenapp.entities.BewerbenModel
import de.barut.mybeworbenapp.ui.theme.MyBeworbenAppTheme
import de.barut.mybeworbenapp.viewmodel.MainViewModel
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var addItem: AddItem

    @Inject
    lateinit var overview: OverView

    @Inject
    lateinit var details : Details


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBeworbenAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigate()
                }
            }
        }
    }


    @Composable
    fun Navigate() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "OverView") {
            composable("OverView") {
                overview.Start(navController = navController)
            }
            composable("AddItem") {
                addItem.Start(navController = navController)

            }
            composable("Details/{model}", arguments =
            listOf(navArgument("model"){type = NavType.StringType})){
                val json = it.arguments?.getString("model")
                val model = Gson().fromJson(json,BewerbenModel::class.java)
                details.Start(navController = navController,model)
            }
        }
    }
}


