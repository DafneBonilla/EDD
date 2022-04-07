Proyecto 1
=========================================

Implementación de Wizard
----------------------------------------------------

Equipo:

Bonilla Reyes Dafne &nbsp;   &nbsp;   &nbsp;   &nbsp;   &nbsp;   &nbsp;   - &nbsp;   &nbsp;   319089660  
García Ponce José Camilo &nbsp;   - &nbsp;   &nbsp;   319210536  

----------------------------------------------------

### Uso

Realizamos dos versiones del proyecto, una para que se juegue en una sola terminal y otra versión con un servidor (una version más "volátil") para poder tener multijugador usando diferentes terminales y (en teoría, porque no pudimos probarlo) multijugador de diferentes computadoras.
Cada versión del proyecto tiene su forma de usar y su README respectivo con un pequeña explicación.

## Explicación

<div align="justify">
Wizard es un juego de cartas desarrollado por rondas que consisten de cierto número de trucos. Dependiendo del número de jugadores habrá más o menos rondas para conseguir puntos. El ganador de cada truco será quien juegue la carta más valiosa de la ronda. Cada jugador al inicio de cada ronda apostará el número de trucos que cree que ganará. Si al final de la ronda acertó la apuesta, gana puntos, de lo contrario, los pierde. Lo más importante en el juego es ganar la cantidad exacta de trucos que el jugador predijo que ganarı́a. Gana el jugador con más puntuación al final del juego. 
</div>

## Estructura

<div align="justify">
En el proyecto tenemos diferentes clases, primero están las clases para objetos del juego, como carta, jugador, baraja, entre otros. En estas clases utilizamos listas principalmente para tener las manos de cada jugador y la baraja principal del juego. Las otras clases son para cosas de la partida, como ronda, trucos y la partida misma. En estas clases utilizamos listas para guardar a los jugadores y también para saber las cartas que jugó cada uno. Las dos versiones funcionan de la misma manera, solo hay pequeñas diferencias en la parte de jugador, en como se agregan jugadores, como se les envía lo que pasa en el juego y como leemos lo que juegan los jugadores.
</div>

## Inconvenientes

<div align="justify">
En la versión sin servidor no hay inconvenientes, las veces que lo probamos funcionó bien, solo lo que no pudimos probrar (no sabemos como generar que suceda este caso) es que la excepción al intentar escribir en el archivo funcione correctamente.

En la versión con servidor, si hubo algunos inconvenientes, el mismo de la excepción al escribir y que no pudimos probrar que realmente si funcionara con diferentes computadoras, ya que cuando intentamos probarlo no pudimos conectarnos por medio de telnet por la seguridad de las IP y routers. 
</div>
