# Proyecto 2

## 🔗🔒 Implementación de Encerrado con Minimax

Equipo:

Bonilla Reyes Dafne &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; - &nbsp; &nbsp; 319089660  
García Ponce José Camilo &nbsp; - &nbsp; &nbsp; 319210536

---

### Uso

Compilar desde `Encerrado/`

```
javac -d . *.java Estructuras/*.java
```

Ejecutar desde `Encerrado/`

```
java Encerrado/Proyecto2 #version
```

## Explicación

<div align="justify">
El juego de encerrado consiste en lograr que las piezas del oponente ya no puedan moverse.
</div>

## Como jugar

<div align="justify">
Para iniciar, se compila el código y se ejecuta. En los argumentos se debe indicar la versión:

- Versión 1:

  Las posiciones del tablero tendrán numeritos en circulos de colores. Es recomendable usar esta versión solo en sistemas operativos Unix.

- Versión 2:

  Las posiciones del tablero no tendrán numeritos en circulos de colores. Es recomendable usar esta versión solo en sistemas operativos Windows.

A continuación, se le preguntará al usuario la configuración del juego:

- Primero en jugar
- Configuración del tablero
- Inteligencia de la CPU
- Color del jugador

Cada posición en el tablero tendrá un número asociado que indica el número de la posición para así poder elegir la pieza que se moverá.
Los colores rojo y azul indican las fichas de los jugadores, y el color verde indica una posición vacía.
Los jugadores por turnos se irán alternando para mover una de sus fichas cumpliendo las siguientes reglas:

1. Una ficha no puede moverse a espacios ocupados por otras fichas.
2. Una ficha solo puede moverse a un espacio si hay una arista que lo conecte con el espacio en el que se encuentra actualmente dicha ficha.

El juego terminará cuando un jugador no pueda moverse a ninguna ficha.

</div>

## ⚠️ Advertencias de Uso

<div align="justify">

- El juego no tiene ninguna limitación de tiempo, es decir, si se usa minimax y eres muy bueno, es posible que nunca acabe el juego.
- Existe una posibilidad de que el juego no compile en algunos sistemas que no reconozcan el caracter `ñ`, por lo tanto se sugiere precaución al momento de compilar.
- Algunos sistemas operativos no muestran los números en circulitos, por lo tanto use la versión adecuada para su sistema.

</div>
