package com.bforbank.bforbanktest.presentation.pokemon.list.uicomponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bforbank.bforbanktest.presentation.R
import com.bforbank.bforbanktest.presentation.pokemon.model.PokemonUI
import com.bforbank.core.designsystem.theme.BForBankTheme
import com.bforbank.core.designsystem.utils.LightAndDarkPreviews

@Composable
internal fun PokemonListItem(
    pokemon: PokemonUI,
    onItemClicked: (PokemonUI) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClicked(pokemon) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            // Pokémon Image
            Image(
                painter = painterResource(id = R.drawable.pokemon_placeholder),
                contentDescription = "${pokemon.name} Image",
                modifier = Modifier
                    .size(64.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Pokémon Details
            Column {
                Text(
                    text = "ID: ${pokemon.id}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 14.sp
                )
                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp
                )
            }
        }
    }
}


@LightAndDarkPreviews
@Composable
fun PokemonListItemPreview() {
    Surface {
        BForBankTheme() {
            PokemonListItem(
                pokemon = PokemonUI(id = 1, name = "picacku", url = "url"),
                onItemClicked = {}
            )
        }
    }
}