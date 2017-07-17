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
import diegocompany.granacontrol.models.RelatorioDia;


public class TotalDiaAdapter extends RecyclerView.Adapter<TotalDiaAdapter.ViewHolder>
        implements View.OnClickListener {

    private List<RelatorioDia> mData;
    private View.OnClickListener listener;
    private Context context;

    public TotalDiaAdapter(List<RelatorioDia> myData, Context context) {
        this.mData = myData;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        //TextView tvDia;
        Button btDia;
        TextView tvEntradaTotalDia;
        TextView tvSaidaTotalDia;

        ViewHolder(View v) {
            super(v);
            //tvDia = (TextView) v.findViewById(R.id.textViewDia);
            btDia = (Button) v.findViewById(R.id.buttonDia);
            tvEntradaTotalDia = (TextView) v.findViewById(R.id.textViewEntradaTotalDia);
            tvSaidaTotalDia = (TextView) v.findViewById(R.id.textViewSaidaTotalDia);

        }
    }

    @Override
    public TotalDiaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_layout_relatorio_dia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.btDia.setId(mData.get(position) == null ? 0 : Integer.parseInt(mData.get(position).getDia()));
        holder.btDia.setOnClickListener(listener);
        holder.btDia.setText(mData.get(position) == null ? "" : mData.get(position).getDia());
        holder.tvEntradaTotalDia.setText(mData.get(position) == null ? "" : mData.get(position).getTotalEntradaDia());
        holder.tvSaidaTotalDia.setText(mData.get(position) == null ? "" : mData.get(position).getTotalSaidaDia());
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
