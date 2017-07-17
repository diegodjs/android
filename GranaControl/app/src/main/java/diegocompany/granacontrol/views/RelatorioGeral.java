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
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import diegocompany.granacontrol.R;
import diegocompany.granacontrol.adapters.TotalAdapter;
import diegocompany.granacontrol.adapters.TotalDiaAdapter;
import diegocompany.granacontrol.models.ControleDiario;
import diegocompany.granacontrol.models.DadosAlertas;
import diegocompany.granacontrol.models.Registro;
import diegocompany.granacontrol.models.Relatorio;
import diegocompany.granacontrol.models.RelatorioDia;
import diegocompany.granacontrol.utils.ActivityUtil;


public class RelatorioGeral extends ActivityUtil {

    private DatabaseReference dataRefRelatorio;
    private Spinner sAnos = null;
    private Spinner sMeses = null;
    private String id;
    private RecyclerView rvTableRelatorioDia;
    private RecyclerView rvTableRelatorio;
    private Button btGerarRelatorio = null;
    private Relatorio relatorio = new Relatorio();
    private RelatorioDia relatorioDia = new RelatorioDia();
    private String ano = null;
    private String mes = null;
    private ControleDiario controleDiario = new ControleDiario();
    private DadosAlertas dadosAlertas = null;
    private List<Registro> registros = null;

    private List<RelatorioDia> relatorioDiaList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_geral);

        setUpToolbar();
        setupNavDrawer(R.id.nav_item_relatorio_geral);

        getSupportActionBar().setTitle(R.string.relatorioGeral);

        id = profile.getId();

        rvTableRelatorioDia = (RecyclerView) findViewById(R.id.rvTableRelatorioDia);
        rvTableRelatorio = (RecyclerView) findViewById(R.id.rvTableRelatorio);
        dataRefRelatorio = database.getReference(id);

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


                relatorioDiaList = new ArrayList<>();
                dataRefRelatorio.addValueEventListener(returnDataRelatorioDia());

            }
        };
    }

    private void setRecyclerViewRelatorio(Relatorio relatorio) {

            rvTableRelatorio.setHasFixedSize(true);
            StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            rvTableRelatorio.setLayoutManager(mLayoutManager);

            TotalAdapter totalAdapter = new TotalAdapter(relatorio, RelatorioGeral.this);

            rvTableRelatorio.setAdapter(totalAdapter);
            rvTableRelatorio.setItemAnimator(new DefaultItemAnimator());

    }

    private ValueEventListener returnDataRelatorio() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dadosAlertas = dataSnapshot.child("dadosAlertas").getValue(DadosAlertas.class);

                Iterable<DataSnapshot> children = dataSnapshot.child("controle").child(ano).child(mes).getChildren();

                boolean achouRegistro = false;
                double totalEntradaMes = 0;
                double totalSaidaMes = 0;

                for (Iterator it = children.iterator(); it.hasNext(); ) {
                    achouRegistro = true;
                    DataSnapshot obj = (DataSnapshot) it.next();
                    controleDiario = obj.getValue(ControleDiario.class);
                    registros = controleDiario.getRegistros();

                    double totalEntradaDia = 0;
                    double totalSaidaDia = 0;

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

                        totalEntradaDia += entradaValor;
                        totalSaidaDia += saidaValor;
                    }

                    totalEntradaMes += totalEntradaDia;
                    totalSaidaMes += totalSaidaDia;
                }

                if (!achouRegistro){
                    relatorio = new Relatorio();
                        alert(R.string.semRegistros);
                }
                else {
                    relatorio = new Relatorio();
                    relatorio.setTotalEntrada(String.format(Locale.ROOT, "%.2f", totalEntradaMes));
                    relatorio.setTotalSaida(String.format(Locale.ROOT, "%.2f", totalSaidaMes));
                    relatorio.setTotalGeral(String.format(Locale.ROOT, "%.2f", totalEntradaMes-totalSaidaMes));

                    double alertaMensal = Double.parseDouble(dadosAlertas.getAlertaGastoMensal());
                    if (totalSaidaMes > alertaMensal) {
                        showAlertaGasto(ALERTA_MENSAL);
                    }
                }

                setRecyclerViewRelatorio(relatorio);

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        };
    }


    private void setRecyclerViewRelatorioDia(List<RelatorioDia> relatorioDiaList) {

        rvTableRelatorioDia.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rvTableRelatorioDia.setLayoutManager(mLayoutManager);

        TotalDiaAdapter totalDiaAdapter = new TotalDiaAdapter(relatorioDiaList, RelatorioGeral.this);

        totalDiaAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = view.getId();
                Intent intent = new Intent(getContext(), Diario.class);
                Bundle params = new Bundle();
                params.putBoolean("isDataEscolhida", true);
                params.putInt("diaEscolhido", i);
                params.putInt("mesEscolhido", Integer.parseInt(mes));
                params.putInt("anoEscolhido", Integer.parseInt(ano));
                intent.putExtras(params);
                startActivity(intent);
                //finishAfterTransition();
            }
        });

        rvTableRelatorioDia.setAdapter(totalDiaAdapter);
        rvTableRelatorioDia.setItemAnimator(new DefaultItemAnimator());

    }

    private ValueEventListener returnDataRelatorioDia() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.child("controle").child(ano).child(mes).getChildren();

                relatorioDiaList = new ArrayList<>();

                for (Iterator it = children.iterator(); it.hasNext(); ) {
                    DataSnapshot obj = (DataSnapshot) it.next();
                    double totalEntradaDia = 0;
                    double totalSaidaDia = 0;
                    String dia = obj.getKey();

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

                        totalEntradaDia += entradaValor;
                        totalSaidaDia += saidaValor;
                    }

                    relatorioDia = new RelatorioDia();
                    relatorioDia.setDia(dia);
                    relatorioDia.setTotalEntradaDia(String.format(Locale.ROOT, "%.2f", totalEntradaDia));
                    relatorioDia.setTotalSaidaDia(String.format(Locale.ROOT, "%.2f", totalSaidaDia));

                    relatorioDiaList.add(relatorioDia);

                }

                setRecyclerViewRelatorioDia(relatorioDiaList);

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        };
    }

}
