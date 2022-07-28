package de.barut.mybeworbenapp.view

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import de.barut.mybeworbenapp.R
import de.barut.mybeworbenapp.entities.BewerbenModel
import de.barut.mybeworbenapp.ui.composable.ComposableTab
import de.barut.mybeworbenapp.ui.theme.orange500
import de.barut.mybeworbenapp.ui.theme.orange700
import de.barut.mybeworbenapp.viewmodel.AddItemViewModel
import javax.inject.Inject


class AddItem @Inject constructor(
    var viewmodel: AddItemViewModel
) {

    var switchChecked = "in Bearbeitung"



    @Composable
    fun Start(navController: NavController) {

        var composableTab = ComposableTab(R.string.tab_sub_titel_add_item)
        composableTab.Tab(actions = {

            val bewerben = CustomSwitch()
            val bewerbenToString = viewmodel.changeBewerbenToString(bewerben.value)
            switchChecked = bewerbenToString

        }) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                // The Values from the User
                val name = CustomTextFields(text = "Name")
                val date = CustomTextFields(text = "Datum", singleLine = true)
                val description = CustomTextFields(text = "Beschreibung")
                val contaxtPerson = CustomTextFields(text = "Ansprechpartner")




                Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxSize()) {
                    CustomButton(text = "Speichern", onclick = {
                        val model = BewerbenModel(
                            null,
                            switchChecked,
                            name.value,
                            date.value,
                            description.value,
                            contaxtPerson.value
                        )

                        val checkUserInputOk = viewmodel.checkUserInputOK(name.value)
                        if (!checkUserInputOk)
                            viewmodel.add(model)
                        navController.navigate("OverView") {
                            popUpTo("OverView"){inclusive = true}
                        }

                    })

                    CustomButton(text = "Zurück", onclick = {
                        navController.navigate("OverView"){
                            popUpTo("OverView") {inclusive = true}
                        }
                    })
                }
            }
        }
    }

    @Composable
    private fun CustomTextFields(text: String, singleLine: Boolean = false): MutableState<String> {
        val remember = remember { mutableStateOf(" ") }
        TextField(
            value = remember.value, onValueChange = { remember.value = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = orange700,
                backgroundColor = orange500,
                textColor = Color.White,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Red
            ),
            label = { Text(text = text) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = singleLine,
        )
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        return remember
    }

    @Composable
    private fun CustomButton(text: String, onclick: () -> Unit = {}, block: @Composable () -> Unit = {}) {
        Button(onClick = { onclick() }, modifier = Modifier.fillMaxWidth()) {
            Text(text = text, fontSize = 20.sp)
            block()
        }
        Spacer(modifier = Modifier.padding(bottom = 8.dp))


    }

    @Composable
    private fun CustomSwitch(): MutableState<Boolean> {
        var remember = remember { mutableStateOf(false) }
        var rememberToastMessage = remember{ mutableStateOf("")}
        Switch(checked = remember.value, onCheckedChange = {
            remember.value = it
            if(it)
                rememberToastMessage.value = "Beworben"
            else
                rememberToastMessage.value = "in bearbeitung"
        })

        Toast.makeText(LocalContext.current,rememberToastMessage.value,Toast.LENGTH_SHORT).show()
        return remember
    }


}