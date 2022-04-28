package manual.casas

import koin.casas.Puerta
import koin.casas.Ventana
import java.util.*

data class Casa(
    private val ventana: Ventana,
    private val puerta: Puerta,
) {
    val id: UUID = UUID.randomUUID()

    fun entrar() {
        puerta.abrir()
        puerta.cerrar()
    }

    fun ventilar() {
        ventana.abrir()
    }
}
