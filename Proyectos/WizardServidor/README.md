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
  
   En este caso, se usa la IP de la computadora que inició el servidor (esto en teoría debería funcionar, pero debido a que el equipo no tiene la      capacidad y conocimientos suficiente para hacerlo, no pudimos probrarlo, se intentó, pero no se logró, los routers son muy poderosos). Después, una vez que los jugadores están conectados, se inicia el juego. 

Cada jugador recibirá un mensaje de lo que sucede en el juego a través de su terminal. Al final de cada ronda se hace una votación para ver si el juego sigue o no. Cuando el juego termine, a cada jugador se le enviará un mensaje de texto indicando si fue ganador o no. Finalmente, todos los jugadores serán desconectados del servidor.
  
### Aclaración
  
  Cada jugador debe escribir solo una línea de texto a la vez, ya que de lo contrario, es posible que el programa lea solo la primera línea y después, cuando se ingrese más texto este se lea incorrectamente (por esto, ésta versión del proyecto es más inestable). 
</div>
