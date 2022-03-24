package Proyectos.Wizard;

/**
 * Enumeración para los campos de un {@link Carta}.
 */
public enum CampoCarta {

    /** El valor de la carta. */
    VALOR,
    /** El color de la carta. */
    COLOR;

    /**
     * Regresa una representación en cadena del campo.
     * @return una representación en cadena del campo.
     */
    @Override public String toString() {
        switch (this) {
            case VALOR:
                return "Valor";
            case COLOR:
                return "Color";
            default:
                return "Algo salio mal";
        }
    }
}
