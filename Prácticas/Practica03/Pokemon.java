package edd.src.Estructuras;

public class Pokemon {
    String nombre;
    String tipo;
    int nivel;
    int hp;

    public Pokemon(String nombre, String tipo, int nivel, int hp) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.hp = hp;
    }
    public Pokemon(){
        this.nombre = "Pikachu";
        this.tipo = "Electrico";
        this.nivel = 1;
        this.hp = 100;
    }

    public String toString() {
        return "nombre=" + nombre ;
    }
    
}
