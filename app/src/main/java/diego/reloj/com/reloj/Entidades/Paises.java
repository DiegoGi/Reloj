package diego.reloj.com.reloj.Entidades;

public class Paises {
    private String Nombre;
    private String ZonaHoraria;

    public Paises(String nombre, String zonaHoraria) {
        Nombre = nombre;
        ZonaHoraria = zonaHoraria;
    }


    public String getNombre() {
        return Nombre;
    }

    public String getZonaHoraria() {
        return ZonaHoraria;
    }
}
