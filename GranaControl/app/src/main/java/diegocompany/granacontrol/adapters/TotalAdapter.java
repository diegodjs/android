package diegocompany.granacontrol.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import diegocompany.granacontrol.R;
import diegocompany.granacontrol.models.Registro;
import diegocompany.granacontrol.models.Relatorio;


public class TotalAdapter extends RecyclerView.Adapter<TotalAdapter.ViewHolder>
        implements View.OnClickListener {

    private Relatorio mData;
    private View.OnClickListener listener;
    private Context context;

    public TotalAdapter(Relatorio myData, Context context) {
        this.mData = myData;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEntradaTotal;
        TextView tvSaidaTotal;
        TextView tvTotalGeral;


        ViewHolder(View v) {
            super(v);
            tvEntradaTotal = (TextView) v.findViewById(R.id.textViewEntradaTotal);
            tvSaidaTotal = (TextView) v.findViewById(R.id.textViewSaidaTotal);
            tvTotalGeral = (TextView) v.findViewById(R.id.textViewTotalGeral);
        }
    }

    @Override
    public TotalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_layout_relatorio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvEntradaTotal.setText(mData.getTotalEntrada());
        holder.tvSaidaTotal.setText(mData.getTotalSaida());
        holder.tvTotalGeral.setText(mData.getTotalGeral());

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }
}
