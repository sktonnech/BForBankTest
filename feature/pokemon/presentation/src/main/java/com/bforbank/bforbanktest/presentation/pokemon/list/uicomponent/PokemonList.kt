package com.bforbank.bforbanktest.presentation.pokemon.list.uicomponent

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bforbank.bforbanktest.presentation.pokemon.model.PokemonUI
import com.bforbank.core.designsystem.theme.BForBankTheme
import com.bforbank.core.designsystem.utils.LightAndDarkPreviews
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun PokemonList(
    modifier: Modifier,
    pokemonList: ImmutableList<PokemonUI>,
    listState: LazyListState = rememberLazyListState(),
    isLoadingMore: Boolean,
    onItemClicked: (PokemonUI) -> Unit,
    onLoadMore: () -> Unit,
    searchText: String
) {

    val reachedBottom: Boolean by remember { derivedStateOf { listState.reachedBottom() } }

    // load more if scrolled to bottom
    LaunchedEffect(reachedBottom) {
        if (reachedBottom && !isLoadingMore) {
            println("slim loadMore")
            onLoadMore()
        }
    }

    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        items(
            items = pokemonList,
            key = { pokemon: PokemonUI -> pokemon.id },
        ) { pokemon ->
            PokemonListItem(
                pokemon = pokemon,
                searchText = searchText,
                onItemClicked = onItemClicked)
        }
        if (isLoadingMore) {
            item {
                LoadingMessage()
            }
        }
    }
}




@LightAndDarkPreviews
@Composable
fun PokemonListPreview() {
    Surface {
        BForBankTheme {
            PokemonList(
                modifier = Modifier,
                pokemonList = persistentListOf(
                    PokemonUI(id = 1, name = "name1", url = "url"),
                    PokemonUI(id = 2, name = "name2", url = "url"),
                    PokemonUI(id = 3, name = "name3", url = "url"),
                    PokemonUI(id = 4, name = "name4", url = "url"),
                ),
                isLoadingMore = false,
                onItemClicked={},
            onLoadMore={},
            searchText= "text"
            )
        }
    }
}

// to detect that we scroll to the end of list
private fun LazyListState.reachedBottom(): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return this.lastScrolledForward
            && lastVisibleItem?.index != 0
            && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - 2
}
