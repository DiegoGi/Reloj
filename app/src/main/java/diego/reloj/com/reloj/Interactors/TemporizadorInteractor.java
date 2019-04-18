package diego.reloj.com.reloj.Interactors;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;

import diego.reloj.com.reloj.Entidades.AccionBoton;
import diego.reloj.com.reloj.Entidades.Milisegundos;
import diego.reloj.com.reloj.Interfaces.TemporizadorContract;
import diego.reloj.com.reloj.R;

public class TemporizadorInteractor implements TemporizadorContract.InteractorOps {
    private CountDownTimer temporizador;
    private TemporizadorContract.RequiredPresenterOps presenter;
    private long tiempoRestante;
    private SoundPool sonidoPool;


    public TemporizadorInteractor(TemporizadorContract.RequiredPresenterOps presenter) {
        this.presenter = presenter;

    }

    @Override
    public void iniciarTemporizador(final Milisegundos tiempo) {
        if (temporizador != null) {
            temporizador.cancel();
        }
        temporizador = new CountDownTimer(tiempo.getMilisegundos(), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                tiempoRestante = millisUntilFinished;
                actualizarContador();
                actualizarProgressBar();
            }

            @Override
            public void onFinish() {
                presenter.actualizarProgressBar(0);
                presenter.actualizarContador("00:00");
                presenter.mostrarNotificacion("Tiempo ha finalizado", "Toca para abrir", NotificationCompat.PRIORITY_DEFAULT, R.drawable.ic_notificacion_tempo);

                presenter.reproducirSonido();

            }

        }.start();
    }

    @Override
    public void cambiarTiempoTemporizador(AccionBoton accion, String valor) {
        int tiempo = Integer.parseInt(valor);

        if (accion.equals(AccionBoton.AUMENTAR_MINUTOS)) {
            tiempo = (tiempo == 59) ? 0 : tiempo + 1;
            presenter.actualizarCampoMinutos(Integer.toString(tiempo));
        } else if (accion.equals(AccionBoton.DISMINUIR_MINUTOS)) {
            tiempo = (tiempo == 0) ? 59 : tiempo - 1;
            presenter.actualizarCampoMinutos(Integer.toString(tiempo));
        }

        if (accion.equals(AccionBoton.AUMENTAR_SEGUNDOS)) {
            tiempo = (tiempo == 59) ? 0 : tiempo + 1;
            presenter.actualizarCampoSegundos(Integer.toString(tiempo));
        } else if (accion.equals(AccionBoton.DISMINUIR_SEGUNDOS)) {
            tiempo = (tiempo == 0) ? 59 : tiempo - 1;
            presenter.actualizarCampoSegundos(Integer.toString(tiempo));
        }


    }

    private void actualizarProgressBar() {
        presenter.actualizarProgressBar((int) tiempoRestante);
    }

    private void actualizarContador() {
        int minutos = (int) tiempoRestante / 60000;
        int segundos = (int) tiempoRestante % 60000 / 1000;

        StringBuilder texto = new StringBuilder();

        texto.append((minutos < 10) ? "0" + minutos : minutos);
        texto.append(":");
        texto.append((segundos < 10) ? "0" + segundos : segundos);

        presenter.actualizarContador(texto.toString());
    }

}
