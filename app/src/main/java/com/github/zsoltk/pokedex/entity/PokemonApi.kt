package com.github.zsoltk.pokedex.entity

import androidx.annotation.FloatRange
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

object PokemonApi {

    /**
     * ciclo de vida
     */
    fun loadPokemon(): Observable<List<Pokemon>> =
        Observable
            .just(pokemons)
            .delay(200, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .map {
                if (Random.nextFloat() < randomFailureChance) throw FakeApiException()
                it
            }


    /**
     * Mostramos error en la pantalla por si algo no jala
     */
    @FloatRange(from = 0.0, to = 1.0)
    val randomFailureChance: Float = 0.1f

    class FakeApiException : RuntimeException("Test exception, please ignore")
}
