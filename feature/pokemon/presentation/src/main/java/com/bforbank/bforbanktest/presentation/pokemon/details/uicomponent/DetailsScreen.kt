package com.bforbank.bforbanktest.presentation.pokemon.details.uicomponent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bforbank.bforbanktest.feature.pokemon.presentation.R
import com.bforbank.bforbanktest.presentation.pokemon.model.PokemonUI
import com.bforbank.core.designsystem.theme.BForBankTheme

@Composable
fun DetailsScreen(modifier: Modifier, pokemon: PokemonUI) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }
    Column(modifier = modifier) {
        //  animation on the image
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut()
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokemon_placeholder),
                contentDescription = "Pokemon Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "ID: ${pokemon.id}",
                style = BForBankTheme.typography.bodySmall,
                fontSize = 14.sp
            )
            Text(text = pokemon.name, style = BForBankTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Pokemon description : ${pokemon.url}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}