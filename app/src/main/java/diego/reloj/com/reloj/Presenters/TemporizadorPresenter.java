package diego.reloj.com.reloj.Presenters;

import diego.reloj.com.reloj.Entidades.AccionBoton;
import diego.reloj.com.reloj.Entidades.Milisegundos;
import diego.reloj.com.reloj.Interactors.TemporizadorInteractor;
import diego.reloj.com.reloj.Interfaces.TemporizadorContract;

public class TemporizadorPresenter implements TemporizadorContract.RequiredPresenterOps, TemporizadorContract.PresenterOps {
    private TemporizadorContract.RequiredViewOps vista;
    private TemporizadorContract.InteractorOps interactor;

    public TemporizadorPresenter(TemporizadorContract.RequiredViewOps vista) {
        this.vista = vista;
        interactor = new TemporizadorInteractor(this);

    }

    @Override
    public void actualizarProgressBar(int segundos) {
        vista.actualizarProgressBar(segundos);
    }

    @Override
    public void actualizarContador(String contador) {
        vista.actualizarContador(contador);
    }

    @Override
    public void actualizarCampoMinutos(String tiempo) {
        vista.actualizarCampoMinutos(tiempo);
    }

    @Override
    public void actualizarCampoSegundos(String tiempo) {
        vista.actualizarCampoSegundos(tiempo);
    }

    @Override
    public void mostrarNotificacion(String titulo, String contenido, int prioridad, int icono) {
        vista.mostrarNotificacion(titulo,contenido,prioridad,icono);
    }

    @Override
    public void reproducirSonido() {
        vista.reproducirSonido();
    }

    @Override
    public void iniciarTemporizador(Milisegundos tiempo) {
        interactor.iniciarTemporizador(tiempo);
    }

    @Override
    public void cambiarTiempoTemporizador(AccionBoton accion, String valor) {
        interactor.cambiarTiempoTemporizador(accion, valor);
    }
}
