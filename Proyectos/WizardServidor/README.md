Proyecto 1
=========================================

Implementación de Wizard
----------------------------------------------------

Equipo:

Bonilla Reyes Dafne &nbsp;   &nbsp;   &nbsp;   &nbsp;   &nbsp;   &nbsp;   - &nbsp;   &nbsp;   319089660  
García Ponce José Camilo &nbsp;   - &nbsp;   &nbsp;   319210536  

----------------------------------------------------

### Uso

Compilar desde `WizardServidor/`

```
javac -d . *.java Estructuras/*.java
```

Ejecutar el servidor desde `WizardServidor/` 

```
java WizardServidor/Proyecto1Servidor #jugadores archivo.txt puerto
```

Uniser a la partida desde otra terminal
```
telnet localhost puerto
```

Uniser a la partida desde otra computadora*
```
telnet ip puerto
```

## Explicación

<div align="justify">
Wizard es un juego de cartas desarrollado por rondas que consisten de cierto número de trucos. Dependiendo del número de jugadores habrá más o menos rondas para conseguir puntos. El ganador de cada truco será quien juegue la carta más valiosa de la ronda. Cada jugador al inicio de cada ronda apostará el número de trucos que cree que ganará. Si al final de la ronda acertó la apuesta, gana puntos, de lo contrario, los pierde. Lo más importante en el juego es ganar la cantidad exacta de trucos que el jugador predijo que ganarı́a. Gana el jugador con más puntuación al final del juego. 
</div>

## Como jugar

<div align="justify">
Primero se tiene que iniciar el servidor desde una computadora, dando el número de jugadores, un archivo para que se guarde el historial del juego (si el archivo no existe se creara y si ya existe se borrara lo que ya tenia antes) y un puerto (para que los jugadores se conecten). Luego los jugadores se deben conectar al servidor por medio de telnet, para esto hay dos casos: si es la misma computadora se usa a localhost, si es otra computadora se usa la ip de la computadora que inicio el servidor (esto en teoria deberia funcionar, pero debido a que el equipo no tiene la capacidad y conocimiento suficiente para hacerlo, no pudimos probrarlo, se intento pero no se logro, los routers son muy poderosos). Luego que ya los jugadores esten conectados, se inicia el juego. Cada jugador recibira texto (lo que sucede en el juego) en su terminal, y deberá ingresar texto cuando se le pida, una aclaracion en esta parte, si el jugador solo debe escribir una linea de texto a la vez, no más, ya que si pone más es posible que solo lea la primera y luego cuando ingrese más texto no se lea bien (por esto esta version del proyecto es más inestable). Al final de cada ronda se hace una votacion para ver si el juego sigue o no. Cuando el juego termine cada jugador se le enviará un mensaje de texto que indicará si gano o perdió, seguido seran desconectados del servidor.
</div>
