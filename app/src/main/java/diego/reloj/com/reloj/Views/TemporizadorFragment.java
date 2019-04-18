package diego.reloj.com.reloj.Views;


import android.app.PendingIntent;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URI;

import diego.reloj.com.reloj.Entidades.AccionBoton;
import diego.reloj.com.reloj.Entidades.Milisegundos;
import diego.reloj.com.reloj.Interfaces.TemporizadorContract;
import diego.reloj.com.reloj.Presenters.TemporizadorPresenter;
import diego.reloj.com.reloj.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TemporizadorFragment extends Fragment implements TemporizadorContract.RequiredViewOps {
    private Milisegundos milisegundos;

    private ProgressBar temporizador;
    private TemporizadorContract.PresenterOps presentador;
    private Button btnIniciar;
    private TextView tvContador;
    private ImageButton btnAumMinuto;
    private ImageButton btnDisMinuto;
    private ImageButton btnAumSegundo;
    private ImageButton btnDisSegundo;
    private TextView tvMinutos;
    private TextView tvSegundos;
    private SoundPool sonidoPool;

    public TemporizadorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_temporizador, container, false);
        presentador = new TemporizadorPresenter(this);

        temporizador = view.findViewById(R.id.pgTemporizador);

        tvContador = view.findViewById(R.id.tvContador);
        tvMinutos = view.findViewById(R.id.tvMinutos);
        tvSegundos = view.findViewById(R.id.tvSegundos);

        btnAumMinuto = view.findViewById(R.id.btnAumMin);
        btnAumMinuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presentador.cambiarTiempoTemporizador(AccionBoton.AUMENTAR_MINUTOS, tvMinutos.getText().toString());
            }
        });

        btnDisMinuto = view.findViewById(R.id.btnDisMin);
        btnDisMinuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presentador.cambiarTiempoTemporizador(AccionBoton.DISMINUIR_MINUTOS, tvMinutos.getText().toString());
            }
        });

        btnAumSegundo = view.findViewById(R.id.btnAumSeg);
        btnAumSegundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presentador.cambiarTiempoTemporizador(AccionBoton.AUMENTAR_SEGUNDOS, tvSegundos.getText().toString());
            }
        });

        btnDisSegundo = view.findViewById(R.id.btnDisSegun);
        btnDisSegundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presentador.cambiarTiempoTemporizador(AccionBoton.DISMINUIR_SEGUNDOS, tvSegundos.getText().toString());
            }
        });

        btnIniciar = view.findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                milisegundos = new Milisegundos();
                milisegundos.setMinutos(Integer.parseInt(tvMinutos.getText().toString()));
                milisegundos.setSegundos(Integer.parseInt(tvSegundos.getText().toString()));
                temporizador.setMax((int) milisegundos.getMilisegundos());
                presentador.iniciarTemporizador(milisegundos);
            }
        });

         /* AudioAttributes atributoAudio = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        sonidoPool = new SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(atributoAudio)
                .build();
      sonidoPool=new SoundPool(6, AudioManager.STREAM_MUSIC,0);
        audio= sonidoPool.load(getContext(),R.raw.alerta,1);*/

        return view;
    }

    @Override
    public void actualizarProgressBar(int segundo) {
        temporizador.setProgress(segundo);
    }

    @Override
    public void actualizarContador(String contador) {
        tvContador.setText(contador);
    }

    @Override
    public void actualizarTextoBoton(String valor) {
        btnIniciar.setText(valor);
    }

    @Override
    public void actualizarCampoMinutos(String tiempo) {
        tvMinutos.setText(tiempo);
    }

    @Override
    public void actualizarCampoSegundos(String tiempo) {
        tvSegundos.setText(tiempo);
    }

    @Override
    public void mostrarNotificacion(String titulo, String contenido, int prioridad, int icono) {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "v")
                .setSmallIcon(icono)
                .setContentTitle(titulo)
                .setContentText(contenido)
                .setPriority(prioridad)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
    }

    @Override
    public void reproducirSonido() {
      //  sonidoPool.play(audio ,1,1,1,0,0);
        final MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(),R.raw.alerta);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (mp == mediaPlayer) {
                    mp.start();
                }
            }
        });


    }

}
