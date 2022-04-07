Proyecto 1
=========================================

Implementación de Wizard
----------------------------------------------------

Equipo:

Bonilla Reyes Dafne &nbsp;   &nbsp;   &nbsp;   &nbsp;   &nbsp;   &nbsp;   - &nbsp;   &nbsp;   319089660  
García Ponce José Camilo &nbsp;   - &nbsp;   &nbsp;   319210536  

----------------------------------------------------

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
Primero se tiene que iniciar el juego, dando el número de jugadores y un archivo para que se guarde el historial del juego (si el archivo no existe se creara y si ya existe se borrara lo que ya tenia antes). Luego los jugadores se tendran que ir turnando dependiendo de su número de jugadora para poder ingresar lo que la terminal les pida. Al final de cada ronda se tendra la posiblidad de terminar el juego. Cuando el juego termine cada jugador se le enviará un mensaje de texto que indicará si gano o perdió, seguido seran desconectados del servidor.
</div>