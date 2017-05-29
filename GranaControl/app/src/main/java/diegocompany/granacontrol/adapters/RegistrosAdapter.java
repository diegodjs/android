package diegocompany.granacontrol.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import diegocompany.granacontrol.R;

import java.util.List;

import diegocompany.granacontrol.models.Registro;


public class RegistrosAdapter extends RecyclerView.Adapter<RegistrosAdapter.ViewHolder>
        implements View.OnClickListener {

    private List<Registro> mData;
    private View.OnClickListener listener;
    private Context context;

    public RegistrosAdapter(List<Registro> myData, Context context) {
        this.mData = myData;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        Button btRemoveRegistro;
        TextView tvEntrada;
        TextView tvSaida;
        TextView tvDescricao;


        ViewHolder(View v) {
            super(v);
            btRemoveRegistro = (Button) v.findViewById(R.id.buttonRemoveRegistro);
            tvEntrada = (TextView) v.findViewById(R.id.textViewEntrada);
            tvSaida = (TextView) v.findViewById(R.id.textViewSaida);
            tvDescricao = (TextView) v.findViewById(R.id.textViewDescricao);
        }
    }

    @Override
    public RegistrosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_layout, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.btRemoveRegistro.setId(position);
        holder.btRemoveRegistro.setOnClickListener(listener);
        holder.tvEntrada.setText(mData.get(position) == null ? "" : mData.get(position).getEntrada());
        holder.tvSaida.setText(mData.get(position) == null ? "" : mData.get(position).getSaida());
        holder.tvDescricao.setText(mData.get(position) == null ? "" : mData.get(position).getDescricao());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }
}
