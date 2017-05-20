package diegocompany.granacontrol;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Informacoes extends AppCompatActivity {

    private NumberPicker npAnoInicial;
    private Spinner spinnerMes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes);

        npAnoInicial = (NumberPicker) findViewById(R.id.pickerAnoInicial);
        npAnoInicial.setMinValue(Calendar.getInstance().get(Calendar.YEAR));
        npAnoInicial.setMaxValue(2020);
        npAnoInicial.setWrapSelectorWheel(true);

        spinnerMes = (Spinner) findViewById(R.id.spinnerMes);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMes.setAdapter(adapter);

        Button btAvancar = (Button) findViewById(R.id.btAvancar);
        btAvancar.setOnClickListener(onClickAvancar());

        getSupportActionBar().setTitle("Informações Iniciais");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private View.OnClickListener onClickAvancar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                npAnoInicial = (NumberPicker) findViewById(R.id.pickerAnoInicial);
                spinnerMes = (Spinner) findViewById(R.id.spinnerMes);
                TextView tGrana = (TextView) findViewById(R.id.etGrana);
                TextView tAlerta = (TextView) findViewById(R.id.etAlerta);

                String grana = tGrana.getText().toString();
                String alerta = tAlerta.getText().toString();

                Log.d("TAG", npAnoInicial.getValue() + " - " + spinnerMes.getSelectedItem()
                        + " - " + grana  + " - " + alerta);

                Intent intent = new Intent(getContext(), Diario.class);
                Bundle params = new Bundle();
                params.putInt("anoInicial", npAnoInicial.getValue());
                params.putInt("mesInicial", retornaMes(spinnerMes.getSelectedItem().toString()));

                intent.putExtras(params);
                startActivity(intent);
            }
        };
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Context getContext() {
        return this;
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private int retornaMes(String mes) {
        switch (spinnerMes.getSelectedItem().toString()) {
            case "Janeiro":
                return 1;
            case "Fevereiro":
                return 2;
            case "Março":
                return 3;
            case "Abril":
                return 4;
            case "Maio":
                return 5;
            case "Junho":
                return 6;
            case "Julho":
                return 7;
            case "Agosto":
                return 8;
            case "Setembro":
                return 9;
            case "Outubro":
                return 10;
            case "Novembro":
                return 11;
            case "Dezembro":
                return 12;
            default:
                return 0;
        }
    }
}
