package diegocompany.granacontrol.views;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import diegocompany.granacontrol.R;
import diegocompany.granacontrol.adapters.RegistrosAdapter;
import diegocompany.granacontrol.models.ControleDiario;
import diegocompany.granacontrol.models.Registro;
import diegocompany.granacontrol.utils.ActivityUtil;

public class Diario extends ActivityUtil {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dataRefDiario;
    private RecyclerView rvTableDiario;
    private List<Registro> registros = new ArrayList<Registro>();;
    private ControleDiario controleDiario = new ControleDiario();;
    private FloatingActionButton btEntrada;
    private FloatingActionButton btSaida;
    private Button btRemove;
    private String usuario;
    private SeekBar sbGrana = null;
    private TextView tvGrana = null;
    private TextView tDescricao = null;
    private String grana = null;
    private String descricao;
    private Registro registro = null;
    private static final String TIPO_ENTRADA = "ENTRADA";
    private static final String TIPO_SAIDA = "SAIDA";
    private Calendar calendar = null;
    String ano = null;
    String mes = null;
    String dia = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);

        getSupportActionBar().setTitle(R.string.controleDiario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle args = getIntent().getExtras();

        usuario = args.getString("usuario");

        calendar = Calendar.getInstance();
        ano = String.valueOf(calendar.get(Calendar.YEAR));
        mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        dia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        getSupportActionBar().setTitle(getSupportActionBar().getTitle() + " - " + dia + "/" + mes + "/" + ano);

        btEntrada = (FloatingActionButton) findViewById(R.id.buttonEntrada);
        btEntrada.setOnClickListener(onClickEntrada());

        btSaida = (FloatingActionButton) findViewById(R.id.buttonSaida);
        btSaida.setOnClickListener(onClickSaida());

        tvGrana = (TextView) findViewById(R.id.editTextGrana);
        sbGrana = (SeekBar) findViewById(R.id.seekbarGrana);
        sbGrana.setOnSeekBarChangeListener(seekBarChange(tvGrana));

        tDescricao = (TextView) findViewById(R.id.editTextDescricao);

        dataRefDiario = database.getReference(usuario);

        rvTableDiario = (RecyclerView) findViewById(R.id.rvTableDiario);

        dataRefDiario.addValueEventListener(returnDataDiario());

        setRecyclerViewDiario(controleDiario);

    }

    private ValueEventListener returnDataDiario () {
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                controleDiario = dataSnapshot.child("controle").child(ano)
                        .child(mes)
                        .child(dia).getValue(ControleDiario.class);

                if (controleDiario == null ){
                    controleDiario = new ControleDiario();
                }

                setRecyclerViewDiario(controleDiario);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        };
    }

    private void setRecyclerViewDiario(ControleDiario controle) {

        rvTableDiario.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rvTableDiario.setLayoutManager(mLayoutManager);

        RegistrosAdapter registrosAdapter = new RegistrosAdapter(controle.getRegistros(), Diario.this);

        registrosAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = view.getId();
                delete(i);
            }
        });

        rvTableDiario.setAdapter(registrosAdapter);
        rvTableDiario.setItemAnimator(new DefaultItemAnimator());
    }

    private View.OnClickListener onClickEntrada() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registra(TIPO_ENTRADA);
            }
        };
    }

    private View.OnClickListener onClickSaida() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registra(TIPO_SAIDA);
            }
        };
    }

    private void registra(String tipoRegistro) {

        grana = tvGrana.getText().toString();
        descricao = tDescricao.getText().toString();

        if (grana == null || "".equals(grana) || "0.00".equals(grana)) {
            alert(R.string.avisoValor);
            return;
        }

        if (tipoRegistro.equals(TIPO_ENTRADA)) {
            registro = new Registro(geraId(), grana, "", descricao);
        }
        else {
            registro = new Registro(geraId(), "", grana, descricao);
        }

        sbGrana.setProgress(0);
        tvGrana.setText("");
        tDescricao.setText("");

        registros.add(registro);
        controleDiario.setRegistros(registros);

        dataRefDiario.child("controle")
                .child(ano)
                .child(mes)
                .child(dia)
                .setValue(controleDiario);
    }

    public void delete(int posicaoRegistro) {

        if (registros.size() == 0) {
            registros = controleDiario.getRegistros();
        }

        try {
            registros.remove(posicaoRegistro);
        }catch (Exception e) {
            alert(R.string.erroRemover);
        }

        controleDiario.setRegistros(registros);

        dataRefDiario.child("controle")
                .child(ano)
                .child(mes)
                .child(dia)
                .setValue(controleDiario);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_relatorio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuRelatorio) {

            Intent intent = new Intent(getContext(), RelatorioGeral.class);
            Bundle params = new Bundle();
            params.putString("usuario", usuario);

            intent.putExtras(params);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
