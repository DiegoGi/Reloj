package diego.reloj.com.reloj.Views;


import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import diego.reloj.com.reloj.Adapters.ListViewAlarmasAdapter;
import diego.reloj.com.reloj.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RelojFragment extends Fragment {
    private ListView lvAlarmas;
    private Object[][] alarmasConfiguradas={{"01:20 PM","Lunes,Martes",false}};
    private FloatingActionButton fbtnEstablecerAlarma;

    public RelojFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_reloj, container, false);
        lvAlarmas=view.findViewById(R.id.lvAlarmas);
        fbtnEstablecerAlarma=view.findViewById(R.id.fbtnEstablecerAlarma);
        lvAlarmas.setAdapter(new ListViewAlarmasAdapter(getContext(),alarmasConfiguradas));

        fbtnEstablecerAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                establecerAlarmar(v);
            }
        });

        return view;
    }


    public void establecerAlarmar(View v){
        //addAlarma();

    }


}
