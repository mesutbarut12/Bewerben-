package de.barut.mybeworbenapp.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import de.barut.mybeworbenapp.entities.BewerbenModel
import de.barut.mybeworbenapp.ui.composable.ComposableTab
import de.barut.mybeworbenapp.ui.theme.orange500
import de.barut.mybeworbenapp.R
import de.barut.mybeworbenapp.viewmodel.OverViewViewModel
import javax.inject.Inject


class OverView @Inject constructor() {

    @Inject
    lateinit var viewmodel: OverViewViewModel
    lateinit var items: State<List<BewerbenModel>>


    @Composable
    fun Start(navController: NavController) {
        viewmodel.get()
        items = viewmodel.liveData.observeAsState(listOf())


        ComposableTab(R.string.tab_sub_titel).Tab(actions = {
            OutlinedButton(onClick = {fakeItems()}, modifier = Modifier.padding(8.dp)) {
                Text(text = "Muster Items")
            }
        }) {
            LazyColumn(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxSize()
            ) {

                items(items.value.count()) {
                    CustomItems(pos = it,{
                        val model = Gson().toJson(items.value.get(it))
                        navController.navigate("Details/$model"){
                            popUpTo("OverView")
                        }
                    }){
                        viewmodel.delete(items.value.get(it))
                    }
                }
            }
            Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxSize()) {
                CustomButton(text = "Item Hinzufügen", onclick = {
                    navController.navigate("AddItem"){
                        popUpTo("OverView")
                    }
                })
            }
        }
    }

    @Composable
    private fun CustomItems(pos: Int,cardView : () -> Unit,delete : () -> Unit) {

        Card(
            elevation = 3.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp).clickable { cardView() },
            backgroundColor = orange500
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_delete_24),
                        contentDescription = "Arrow Right",
                        modifier = Modifier.clickable { delete() }
                    )
                    Text(
                        text = items.value[pos].name,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 20.sp
                    )
                }

                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "Arrow Right"
                )
            }

        }
    }

    @Composable
    private fun CustomButton(text: String, onclick: () -> Unit = {}, block: @Composable () -> Unit = {}) {
        Button(onClick = { onclick() }, modifier = Modifier.fillMaxWidth()) {
            Text(text = text, fontSize = 20.sp)
            block()
        }
        Spacer(modifier = Modifier.padding(bottom = 8.dp))


    }

    private fun fakeItems() {
        viewmodel.add(arrayListOf(
            BewerbenModel(null, "true", "Dönerhaus Hassee", "27.07.2022", "", "Herr Barut"),
            BewerbenModel(null, "false", "Siemens", "13.05.2022", "", "Herr Mustermann"),
            BewerbenModel(null, "true", "Bayraktar", "2.03.2022", "", "Herr Kaya"),
            BewerbenModel(null, "false", "Musterman Gmbh", "31.05.2022", "", "Herr Gustav"),
            BewerbenModel(null, "true", "Test", "03.02.2022", "", "Herr Scholz")
        ))

    }

}