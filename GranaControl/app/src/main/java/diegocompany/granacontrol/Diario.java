package diegocompany.granacontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import diegocompany.granacontrol.adapters.RegistrosAdapter;
import diegocompany.granacontrol.models.Registro;

public class Diario extends AppCompatActivity {

    private RecyclerView rvTable;
    private List<Registro> registros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);

        getSupportActionBar().setTitle(R.string.controleDiario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle args = getIntent().getExtras();
        int anoInicial = args.getInt("anoInicial");
        int mesInicial = args.getInt("mesInicial");

        getSupportActionBar().setTitle(getSupportActionBar().getTitle() + " - " + mesInicial + "/" +anoInicial);

        registros = new ArrayList<Registro>();
        Registro registro1 = new Registro(false, "10000000000000,00", "", "Recebimentooooooooooooo");
        registros.add(registro1);
        Registro registro2 = new Registro(false, "", "10,0000000000", "Estacionamentoooooooooo");
        registros.add(registro2);
        Registro registro3 = new Registro(false, "", "10,00", "Estacionamento");
        registros.add(registro3);
        Registro registro4 = new Registro(false, "", "10,00", "Estacionamento");
        registros.add(registro4);
        Registro registro5 = new Registro(false, "", "10,00", "Estacionamento");
        registros.add(registro5);
        Registro registro6 = new Registro(false, "", "10,00", "Estacionamento");
        registros.add(registro6);
        Registro registro7 = new Registro(false, "", "10,00", "Estacionamento");
        registros.add(registro7);
        Registro registro8 = new Registro(false, "", "10,00", "Estacionamento");
        registros.add(registro8);
        Registro registro9 = new Registro(false, "", "10,00", "Estacionamento");
        registros.add(registro9);

        rvTable = (RecyclerView) findViewById(R.id.rvTable);
        setRecyclerView();


    }

    private void setRecyclerView(){
        rvTable.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rvTable.setLayoutManager(mLayoutManager);

        RegistrosAdapter registrosAdapter = new RegistrosAdapter(registros, Diario.this);
        registrosAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rvTable.setAdapter(registrosAdapter);
        rvTable.setItemAnimator(new DefaultItemAnimator());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
