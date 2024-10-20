package com.bforbank.bforbanktest.presentation.pokemon.model

import android.os.Parcelable
import androidx.compose.runtime.Stable
import com.bforbank.bforbanktest.domain.model.PokemonEntity
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.parcelize.Parcelize

@Parcelize
@Stable
data class PokemonUI(val id: Int, val name: String, val url: String) : Parcelable


fun PokemonEntity.toPokemonUI() = PokemonUI(id = id, name = name, url = url)

fun List<PokemonEntity>.toPokemonUIList(): PersistentList<PokemonUI> =
    this.map {
        it.toPokemonUI()
    }.toPersistentList()
