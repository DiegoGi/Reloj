package diego.reloj.com.reloj.Entidades;

public class Milisegundos {
    private int minutos;
    private int segundos;

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public void menosUnSegundo() {
        if (segundos > 0) {
            segundos -= 1;
        } else if (minutos > 0) {
            minutos -= 1;
            segundos = 60;
        }
    }

    public long getMilisegundos() {
        long milisegundos = 0;

        milisegundos += minutos * 60000;
        milisegundos += segundos * 1000;

        return milisegundos;
    }

    public String toString() {
        StringBuilder texto = new StringBuilder();
        texto.append((minutos < 10) ? "0" + minutos : minutos);
        texto.append(":");
        texto.append((segundos < 10) ? "0" + segundos : segundos);

        return texto.toString();
    }

}
