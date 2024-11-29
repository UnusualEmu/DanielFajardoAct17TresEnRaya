package com.example.actividad17_tres_en_raya

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var jugadorActual = "X"  // El jugador que está jugando ahora
    private var tableroJuego = Array(3) { Array(3) { "" } }  // El tablero del juego
    private lateinit var mensajeEstado: TextView  // Muestra si el jugador ganó o es empate
    private lateinit var botonReiniciar: Button // El botón que reinicia el juego

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Aquí encontramos el TextView que muestra el estado del juego
        mensajeEstado = findViewById(R.id.statusMessage)

        // Ahora buscamos los botones del tablero (de 1 a 9)
        val botones = arrayOf(
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9)
        )

        // Aquí les asignamos lo que pasa cuando le dan click a un botón
        for (i in botones.indices) {
            botones[i].setOnClickListener { clickEnCelda(i) }
        }

        // Aquí está el botón de reiniciar y qué pasa cuando lo presionas
        botonReiniciar = findViewById(R.id.resetButton)
        botonReiniciar.setOnClickListener {
            reiniciarJuego()
        }
    }

    // Lo que pasa cuando un jugador hace click en una celda
    private fun clickEnCelda(indice: Int) {
        val fila = indice / 3
        val columna = indice % 3

        // Si la celda ya está ocupada, no hacemos nada
        if (tableroJuego[fila][columna] != "") return

        // Actualizamos el tablero y el botón
        tableroJuego[fila][columna] = jugadorActual
        val boton = findViewById<Button>(resources.getIdentifier("button${indice + 1}", "id", packageName))
        boton.text = jugadorActual

        // Ahora revisamos si el jugador actual ha ganado
        if (verificarGanador()) {
            mensajeEstado.text = "¡El jugador $jugadorActual ha ganado!"
        } else if (esTableroLleno()) {
            mensajeEstado.text = "¡Empate!"
        } else {
            // Si no ganó, cambiamos de jugador
            jugadorActual = if (jugadorActual == "X") "O" else "X"
            mensajeEstado.text = "Turno del jugador $jugadorActual"
        }
    }

    // Aquí verificamos si hay un ganador
    private fun verificarGanador(): Boolean {
        // Revisamos las filas, columnas y diagonales
        for (i in 0..2) {
            // Revisa las filas
            if (tableroJuego[i][0] == jugadorActual && tableroJuego[i][1] == jugadorActual && tableroJuego[i][2] == jugadorActual) return true
            // Revisa las columnas
            if (tableroJuego[0][i] == jugadorActual && tableroJuego[1][i] == jugadorActual && tableroJuego[2][i] == jugadorActual) return true
        }
        // Revisa las diagonales
        if (tableroJuego[0][0] == jugadorActual && tableroJuego[1][1] == jugadorActual && tableroJuego[2][2] == jugadorActual) return true
        if (tableroJuego[0][2] == jugadorActual && tableroJuego[1][1] == jugadorActual && tableroJuego[2][0] == jugadorActual) return true

        return false
    }

    // Revisa si el tablero está lleno
    private fun esTableroLleno(): Boolean {
        for (fila in tableroJuego) {
            if (fila.contains("")) return false
        }
        return true
    }

    // Aquí reiniciamos el juego
    private fun reiniciarJuego() {
        // Restablecemos el tablero
        tableroJuego = Array(3) { Array(3) { "" } }

        // Restablecemos el texto de los botones
        val botones = arrayOf(
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9)
        )

        // Limpiamos el texto de todos los botones
        botones.forEach { it.text = "" }

        // Reiniciamos el mensaje de estado
        jugadorActual = "X"
        mensajeEstado.text = "Turno del jugador $jugadorActual"
    }
}
