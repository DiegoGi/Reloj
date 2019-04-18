package diego.reloj.com.reloj.Widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Calendar;
import java.util.TimeZone;

import diego.reloj.com.reloj.R;

public class RelojAnalogo extends View {
    private Paint lapiz;
    private Boolean estaInicializado = false;
    private int ancho, alto = 0;
    private int minimo = 0;
    private int tamanoTexto = 0;
    private Rect rect;
    private int radio = 0;
    private int manecillaHora, menecilla;
    private int color = getResources().getColor(R.color.colorIcono);
    private String zonaHoraria="America/Bogota";
    public RelojAnalogo(Context context) {
        super(context);
    }

    public RelojAnalogo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RelojAnalogo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void cambiarZonaHoraria(String zonaHoraria){
        this.zonaHoraria=zonaHoraria;
    }

    @Override
    protected void onDraw(Canvas lienzo) {
        if (!estaInicializado) {
            inicializarLienzo();
        }
        dibujarCirculo(lienzo);
        dibujarCentro(lienzo);
        dibujarNumeros(lienzo);
        dibujarManecilla(lienzo);


        postInvalidateDelayed(500);
    }

    private void dibujarManecilla(Canvas lienzo, double loc, boolean esHora) {
        double angulo = Math.PI * loc / 30 - Math.PI / 2;
        int handRadius = esHora ? radio - menecilla - manecillaHora : radio - menecilla;
        lienzo.drawLine(ancho / 2, alto / 2,
                (float) (ancho / 2 + Math.cos(angulo) * handRadius),
                (float) (alto / 2 + Math.sin(angulo) * handRadius)
                , lapiz);
    }

    private void dibujarManecilla(Canvas lienzo) {
        lapiz.reset();
        lapiz.setColor(color);
        lapiz.setStrokeWidth(6);
        lapiz.setStyle(Paint.Style.STROKE);
        lapiz.setAntiAlias(true);

        Calendar calendario=Calendar.getInstance();
        calendario.setTimeZone(TimeZone.getTimeZone(zonaHoraria));
                float hora=calendario.get(Calendar.HOUR_OF_DAY);
        hora=hora>12?hora-12:hora;
        dibujarManecilla(lienzo,(hora+calendario.get(Calendar.MINUTE)/60)* 5f,true);
        dibujarManecilla(lienzo,(calendario.get(Calendar.MINUTE)),false);
        dibujarManecilla(lienzo,(calendario.get(Calendar.SECOND)),false);
    }

    private void dibujarCentro(Canvas lienzo) {
        lapiz.setStyle(Paint.Style.FILL);
        lienzo.drawCircle(ancho / 2, alto / 2, (minimo / 80) , lapiz);

    }

    private void dibujarNumeros(Canvas lienzo) {
        int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        lapiz.reset();
        lapiz.setColor(color);
        lapiz.setAntiAlias(true);
        lapiz.setTextSize(tamanoTexto);

        for (int numero : numeros) {
            String temporal = String.valueOf(numero);
            lapiz.getTextBounds(temporal, 0, temporal.length(), rect);
            double angulo = Math.PI / 6 * (numero - 3);
            int x = (int) (ancho / 2 + Math.cos(angulo) * radio - rect.width() / 2);
            int y = (int) (alto / 2 + Math.sin(angulo) * radio + rect.height() / 2);

            lienzo.drawText(temporal, x, y, lapiz);
        }

    }

    private void inicializarLienzo() {
        lapiz = new Paint();
        ancho = getWidth();
        alto = getHeight();
        minimo = Math.min(ancho, alto);
        tamanoTexto = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics());
        rect = new Rect();
        radio = minimo / 2 - 25;
        menecilla = minimo / 20;
        manecillaHora = minimo / 7;

    }

    private void dibujarCirculo(Canvas lienzo) {
        lapiz.reset();
        lapiz.setColor(color);
        lapiz.setStrokeWidth(5);
        lapiz.setStyle(Paint.Style.STROKE);
        lapiz.setAntiAlias(true);

        lienzo.drawCircle(ancho / 2, alto / 2, (minimo / 2) - 10, lapiz);
    }


}
