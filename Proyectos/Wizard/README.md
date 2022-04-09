# Proyecto 1

## 🔮🧙 Implementación de Wizard 

Equipo:

Bonilla Reyes Dafne &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; - &nbsp; &nbsp; 319089660  
García Ponce José Camilo &nbsp; - &nbsp; &nbsp; 319210536

---

### Uso

Compilar desde `Wizard/`

```
javac -d . *.java Estructuras/*.java
```

Ejecutar desde `Wizard/`

```
java Wizard/Proyecto1 #jugadores archivo.txt
```

## Explicación

<div align="justify">
Wizard es un juego de cartas desarrollado por rondas que consisten de cierto número de trucos. Dependiendo del número de jugadores habrá más o menos rondas para conseguir puntos. El ganador de cada truco será quien juegue la carta más valiosa de la ronda. Cada jugador al inicio de cada ronda apostará el número de trucos que cree que ganará. Si al final de la ronda acertó la apuesta, gana puntos, de lo contrario, los pierde. Lo más importante en el juego es ganar la cantidad exacta de trucos que el jugador predijo que ganarı́a. Gana el jugador con más puntuación al final del juego. 
</div>

## Como jugar

<div align="justify">
Para iniciar el juego, se debe escribir el número de jugadores y el nombre del archivo en donde se guardará el historial del juego (si el archivo no existe, se creará y si ya existe, se borrará su contenido). A continuación, cada jugador se turnará dependiendo de su número y turno para jugar a través de la terminal. Al final de cada ronda se tendrá la posiblidad de terminar el juego. Cuando el juego termine, se enviará un mensaje de texto indicando quien gano la partida. Finalmente, la partida se terminara. Cada vez que se le pide algo a un jugador (texto) este tendra la oportunidad de escribir "h" para recibir todo el historial del juego (se recomienda precaucion, ya que el historial puede llegar a ser muy extenso).
</div>
