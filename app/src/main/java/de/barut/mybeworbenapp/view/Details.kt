package de.barut.mybeworbenapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import de.barut.mybeworbenapp.R
import de.barut.mybeworbenapp.entities.BewerbenModel
import de.barut.mybeworbenapp.ui.composable.ComposableTab
import de.barut.mybeworbenapp.ui.theme.orange500
import de.barut.mybeworbenapp.viewmodel.DetailsViewModel
import javax.inject.Inject

class Details @Inject constructor(){

    @Inject lateinit var detailsViewModel: DetailsViewModel

    @Composable
    fun Start(navController: NavController,model : BewerbenModel){
        ComposableTab(R.string.tab_sub_titel_details).Tab {
            Column(modifier = Modifier.fillMaxSize()) {
                CustomText(text = detailsViewModel.checkDataIsEmptyAndReturnHint(model.name,"Firmenname"))
                CustomText(text = detailsViewModel.checkDataIsEmptyAndReturnHint(model.date,"Datum"))
                CustomText(text = detailsViewModel.checkDataIsEmptyAndReturnHint(model.description,"Beschreibung"))
                CustomText(text = detailsViewModel.checkDataIsEmptyAndReturnHint(model.contactPerson,"Kontakt Person"))
            }

        }
    }

    @Composable
    fun CustomText(text : String){
        Card(elevation = 3.dp, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        , backgroundColor = orange500) {
            Text(text = text,modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), fontSize = 20.sp)

        }
    }
}