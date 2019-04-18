package diego.reloj.com.reloj.Interfaces;

import diego.reloj.com.reloj.Entidades.AccionBoton;
import diego.reloj.com.reloj.Entidades.Milisegundos;

public interface TemporizadorContract {
    //operaciones de la vista disponibles al presentador
    interface RequiredViewOps{
        void actualizarProgressBar(int segundo);
        void actualizarContador(String contador);
        void actualizarTextoBoton(String valor);
        void actualizarCampoMinutos(String tiempo);
        void actualizarCampoSegundos(String tiempo);

        void mostrarNotificacion(String titulo, String contenido, int prioridad, int icono);

        void reproducirSonido();
    }

    //operaciones  del presentador  disponibles al interactor
    interface RequiredPresenterOps{
        void actualizarProgressBar(int segundos);
        void actualizarContador(String contador);

        void actualizarCampoMinutos(String tiempo);

        void actualizarCampoSegundos(String tiempo);

        void mostrarNotificacion(String titulo, String contenido, int prioridad, int icono);

        void reproducirSonido();
    }

    //operaciones del presentador disponibles a la vista
    interface PresenterOps{
        void iniciarTemporizador(Milisegundos tiempo);

        void cambiarTiempoTemporizador(AccionBoton aumentarMinutos, String valor);
    }

    //operaciones del interactor disponibles al presentador
    interface InteractorOps{
        void iniciarTemporizador(Milisegundos tiempo);

        void cambiarTiempoTemporizador(AccionBoton accion, String valor);
    }



}
