package com.github.zsoltk.pokedex.home

import androidx.compose.Composable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutPadding
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.github.zsoltk.pokedex.R
import com.github.zsoltk.pokedex.home.appbar.MainAppBar
import com.github.zsoltk.pokedex.home.news.NewsSection

interface Home {

    sealed class MenuItem(
        val label: String,
        val colorResId: Int
    ) {
        object Pokedex : MenuItem("Pokedex", R.color.poke_teal)
        object Moves : MenuItem("Movimientos", R.color.poke_red)
        object Abilities : MenuItem("Habilidades", R.color.poke_light_blue)
        object Items : MenuItem("Equipamientos", R.color.poke_yellow)
        object Locations : MenuItem("Localizaciones", R.color.poke_purple)
        object TypeCharts : MenuItem("Mis Pokemons", R.color.poke_brown)
    }

    companion object {
        @Composable
        fun Content(onMenuItemSelected: (MenuItem) -> Unit) {
            VerticalScroller {
                Column {
                    MainAppBar(onMenuItemSelected)
                    Container(modifier = LayoutPadding(32.dp)) {
                        NewsSection()
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewMainScreen() {
    Home.Content(onMenuItemSelected = {})
}
