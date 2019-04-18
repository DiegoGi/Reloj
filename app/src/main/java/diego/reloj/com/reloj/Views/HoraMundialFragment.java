package diego.reloj.com.reloj.Views;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import diego.reloj.com.reloj.Adapters.RecyclerHoraPaisesAdapter;
import diego.reloj.com.reloj.Entidades.Paises;
import diego.reloj.com.reloj.Interfaces.HoraMundialContract;
import diego.reloj.com.reloj.R;
import diego.reloj.com.reloj.Widgets.RelojAnalogo;


/**
 * A simple {@link Fragment} subclass.
 */
public class HoraMundialFragment extends Fragment implements HoraMundialContract.RequiredViewOps {
    private RecyclerView rvListaPaises;
    private RecyclerHoraPaisesAdapter horaPaisesAdapter;
    ArrayList<Paises> listaPaises;


    public HoraMundialFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_hora_mundial, container, false);
        listaPaises=new ArrayList<Paises>();
       agregarPaises();

        rvListaPaises=view.findViewById(R.id.rvListaPaises);
        horaPaisesAdapter=new RecyclerHoraPaisesAdapter(listaPaises);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2);

        rvListaPaises.setLayoutManager(layoutManager);
        rvListaPaises.setAdapter(horaPaisesAdapter);


        return view;
    }

    private void agregarPaises() {
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));
        listaPaises.add(new Paises("Venezuela","America/Caracas"));

    }

}
