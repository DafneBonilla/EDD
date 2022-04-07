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
Para iniciar el servidor, se debe escribir el número de jugadores, el nombre de un archivo para que se guarde el historial del juego (si el archivo no existe, se creará y si ya existe, se borrará su contenido) y un puerto (para conectar a los jugadores). A continuación, cada jugador debe conectarse al servidor por medio de telnet, para lo cual, hay dos opciones: 

* Unirse a la partida través de la misma computadora:
`telnet localhost puerto`
* Unirse a la partida través de otra computadora:
`telnet <IP del host> puerto`
En este caso, se usa la IP de la computadora que inicio el servidor (esto en teoría deberia funcionar, pero debido a que el equipo no tiene la capacidad y conocimiento suficiente para hacerlo, no pudimos probrarlo, se intentó, pero no se logró, los routers son muy poderosos). Despues, una vez que los jugadores estén conectados, se inicia el juego. 

Cada jugador recibira texto (lo que sucede en el juego) en su terminal, y deberá ingresar texto cuando se le pida, una aclaracion en esta parte, si el jugador solo debe escribir una linea de texto a la vez, no más, ya que si pone más es posible que solo lea la primera y luego cuando ingrese más texto no se lea bien (por esto esta version del proyecto es más inestable). Al final de cada ronda se hace una votacion para ver si el juego sigue o no. Cuando el juego termine cada jugador se le enviará un mensaje de texto que indicará si gano o perdió, seguido seran desconectados del servidor.
</div>
