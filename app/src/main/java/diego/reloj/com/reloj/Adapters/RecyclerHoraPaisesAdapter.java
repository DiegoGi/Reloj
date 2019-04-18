package diego.reloj.com.reloj.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import diego.reloj.com.reloj.Entidades.Paises;
import diego.reloj.com.reloj.R;
import diego.reloj.com.reloj.Widgets.RelojAnalogo;

public class RecyclerHoraPaisesAdapter  extends RecyclerView.Adapter<RecyclerHoraPaisesAdapter.ViewHolderHoraPaises>{
private ArrayList<Paises> listaPaises;



    public RecyclerHoraPaisesAdapter(ArrayList<Paises> listaPaises) {
        this.listaPaises = listaPaises;
    }

    @NonNull
    @Override
    public ViewHolderHoraPaises onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.elemento_pais,null,false);

        return new ViewHolderHoraPaises(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderHoraPaises viewHolderHoraPaises, int i) {
        viewHolderHoraPaises.raReloj.cambiarZonaHoraria(listaPaises.get(i).getZonaHoraria());
        viewHolderHoraPaises.tvPais.setText(listaPaises.get(i).getNombre());
    }

    @Override
    public int getItemCount() {
        return listaPaises.size();
    }

    public class ViewHolderHoraPaises extends RecyclerView.ViewHolder {
        TextView tvPais;
        RelojAnalogo raReloj;

        public ViewHolderHoraPaises( View itemView) {
            super(itemView);
            tvPais=itemView.findViewById(R.id.tvPais);
            raReloj=itemView.findViewById(R.id.raReloj);
        }
    }
}
