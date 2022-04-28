package koin.casas

import org.koin.dsl.module

val CasasModule = module {
    // Puertas y ventanas como instancias nuevas
    factory { Puerta() }
    factory { Ventana() }
    // Creamos la casa como Singleton
    single { Casa(get(), get()) }
}