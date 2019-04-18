package diego.reloj.com.reloj.Adapters;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import diego.reloj.com.reloj.R;
import diego.reloj.com.reloj.Views.AlarmReceiverActivity;

public class ListViewAlarmasAdapter extends BaseAdapter {
    private  static LayoutInflater inflater=null;
    Context contexto;
    Object[][] alarmasConfiguradas;

    public ListViewAlarmasAdapter(Context contexto, Object[][] alarmasConfiguradas) {
        this.contexto = contexto;
        this.alarmasConfiguradas = alarmasConfiguradas;
        inflater=(LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return alarmasConfiguradas.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Calendar calendario = Calendar.getInstance();
        final  View vista=inflater.inflate(R.layout.elemento_alarma,null);
        final TextView tvHora=vista.findViewById(R.id.tvHora);
        TextView tvDiasAlarma=vista.findViewById(R.id.tvDiasAlarma);
        Switch swAlarma=vista.findViewById(R.id.swAlarma);
        tvHora.setText((String)alarmasConfiguradas[position][0]);
        tvHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TimePickerDialog tiempo=new  TimePickerDialog(contexto, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        calendario.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendario.set(Calendar.MINUTE, minute);
                        SimpleDateFormat timeformat=new SimpleDateFormat("hh:mm a");
                        String formatedDate = timeformat.format(calendario.getTime());
                        tvHora.setText(formatedDate);
                    }
                },calendario.get(Calendar.HOUR_OF_DAY),calendario.get(Calendar.MINUTE),false);
               tiempo.show();
            }
        });

        tvDiasAlarma.setText((String)alarmasConfiguradas[position][1]);
        swAlarma.setChecked((boolean) alarmasConfiguradas[position][2]);

        swAlarma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               PendingIntent alarmIntent;
                alarmIntent = PendingIntent.getActivity(contexto, 0, new Intent(contexto, AlarmReceiverActivity.class), 0);

                Calendar calendar = Calendar.getInstance();
              //  calendar.set(Calendar.HOUR_OF_DAY, 18);
               // calendar.set(Calendar.MINUTE, 31);

                AlarmManager alarmMgr = (AlarmManager)contexto.getSystemService(Context.ALARM_SERVICE);
                alarmMgr.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()+10000,alarmIntent);
                Toast.makeText(contexto,"Lo cambio :D",Toast.LENGTH_LONG).show();
            }

        });

        return vista;
    }


}
