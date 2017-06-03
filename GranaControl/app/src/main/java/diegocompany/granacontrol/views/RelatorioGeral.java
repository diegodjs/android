package diegocompany.granacontrol.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import diegocompany.granacontrol.R;
import diegocompany.granacontrol.adapters.RegistrosAdapter;
import diegocompany.granacontrol.adapters.TotalAdapter;
import diegocompany.granacontrol.models.ControleDiario;
import diegocompany.granacontrol.models.Registro;
import diegocompany.granacontrol.models.Relatorio;
import diegocompany.granacontrol.utils.ActivityUtil;

public class RelatorioGeral extends ActivityUtil {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dataRefRelatorio;
    private Spinner sAnos = null;
    private Spinner sMeses = null;
    private String usuario;
    private RecyclerView rvTableRelatorio;
    private Button btGerarRelatorio = null;
    private Relatorio relatorio = new Relatorio();
    private String ano = null;
    private String mes = null;
    private ControleDiario controleDiario = new ControleDiario();
    private List<Registro> registros = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_geral);

        getSupportActionBar().setTitle(R.string.informacoesIniciais);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle args = getIntent().getExtras();
        usuario = args.getString("usuario");

        rvTableRelatorio = (RecyclerView) findViewById(R.id.rvTableRelatorio);
        dataRefRelatorio = database.getReference(usuario);

        sAnos = (Spinner) findViewById(R.id.spinnerAnos);
        ArrayAdapter<CharSequence> adapterAnos = ArrayAdapter.createFromResource(this,
                R.array.anos, android.R.layout.simple_spinner_item);
        adapterAnos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sAnos.setAdapter(adapterAnos);

        sMeses = (Spinner) findViewById(R.id.spinnerMeses);
        ArrayAdapter<CharSequence> adapterMeses = ArrayAdapter.createFromResource(this,
                R.array.meses, android.R.layout.simple_spinner_item);
        adapterMeses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sMeses.setAdapter(adapterMeses);

        btGerarRelatorio = (Button) findViewById(R.id.buttonGerarRelatorio);
        btGerarRelatorio.setOnClickListener(onClickGerarRelatorio());

        sMeses.setSelection(Calendar.getInstance().get(Calendar.MONTH));
    }

    private void setRecyclerViewRelatorio(Relatorio relatorio) {

        rvTableRelatorio.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rvTableRelatorio.setLayoutManager(mLayoutManager);

        TotalAdapter totalAdapter = new TotalAdapter(relatorio, RelatorioGeral.this);

        rvTableRelatorio.setAdapter(totalAdapter);
        rvTableRelatorio.setItemAnimator(new DefaultItemAnimator());
    }

    private View.OnClickListener onClickGerarRelatorio() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("TAG", sAnos.getSelectedItem() + " - " + sMeses.getSelectedItem());
                ano = String.valueOf(sAnos.getSelectedItem());
                mes = String.valueOf(sMeses.getSelectedItemPosition()+1);

                relatorio = new Relatorio();
                relatorio.setAno(ano);
                relatorio.setMes(mes);
                dataRefRelatorio.addValueEventListener(returnDataRelatorio());
                setRecyclerViewRelatorio(relatorio);
            }
        };
    }

    private ValueEventListener returnDataRelatorio() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.child("controle").child(ano).child(mes).getChildren();

                double totalEntrada = 0;
                double totalSaida = 0;
                double totalGeral = 0;
                boolean achouRegistro = false;

                for (Iterator it = children.iterator(); it.hasNext(); ) {
                    achouRegistro = true;
                    DataSnapshot obj = (DataSnapshot) it.next();
                    controleDiario = obj.getValue(ControleDiario.class);
                    registros = controleDiario.getRegistros();

                    for (Registro registro : registros) {
                        String entrada = registro.getEntrada();
                        String saida = registro.getSaida();
                        double entradaValor = 0d;
                        double saidaValor = 0d;

                        if (entrada != null && !"".equals(entrada)) {
                            entradaValor = Double.parseDouble(entrada);
                        }

                        if (saida != null&& !"".equals(saida)) {
                            saidaValor = Double.parseDouble(saida);
                        }

                        totalEntrada += entradaValor;
                        totalSaida += saidaValor;

                    }
                }

                if (!achouRegistro){
                    relatorio = new Relatorio();
                }
                else {
                    relatorio = new Relatorio();
                    relatorio.setTotalEntrada(String.valueOf(totalEntrada));
                    relatorio.setTotalSaida(String.valueOf(totalSaida));
                    relatorio.setTotalGeral(String.valueOf(totalEntrada-totalSaida));
                }


                setRecyclerViewRelatorio(relatorio);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        };
    }


}
