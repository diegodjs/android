package diegocompany.granacontrol.views;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

import diegocompany.granacontrol.R;

public class Informacoes extends AppCompatActivity {

    private NumberPicker npAnoInicial = null;
    private Spinner sMes = null;
    private SeekBar sbGranaConta = null;
    private SeekBar sbAlerta = null;
    private TextView tvGranaConta = null;
    private TextView tvAlerta = null;
    private Button btAvancar = null;
    private LinearLayout linearLayout = null;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes);

        getSupportActionBar().setTitle(R.string.informacoesIniciais);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle args = getIntent().getExtras();
        usuario = args.getString("usuario");

        npAnoInicial = (NumberPicker) findViewById(R.id.pickerAnoInicial);
        npAnoInicial.setMinValue(Calendar.getInstance().get(Calendar.YEAR));
        npAnoInicial.setMaxValue(Calendar.getInstance().get(Calendar.YEAR)+3);
        npAnoInicial.setWrapSelectorWheel(true);

        sMes = (Spinner) findViewById(R.id.spinnerMes);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sMes.setAdapter(adapter);

        btAvancar = (Button) findViewById(R.id.buttonAvancar);
        btAvancar.setOnClickListener(onClickAvancar());

        linearLayout = (LinearLayout) findViewById(R.id.linearInformacoes);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            linearLayout.setTransitionName("viewLogin");
        }

        tvGranaConta = (TextView) findViewById(R.id.editTextGranaConta);
        sbGranaConta = (SeekBar) findViewById(R.id.seekbarGranaConta);
        sbGranaConta.setOnSeekBarChangeListener(seekBarChange(tvGranaConta));

        tvAlerta = (TextView) findViewById(R.id.editTextAlerta);
        sbAlerta = (SeekBar) findViewById(R.id.seekbarAlerta);
        sbAlerta.setOnSeekBarChangeListener(seekBarChange(tvAlerta));
    }


    private  SeekBar.OnSeekBarChangeListener seekBarChange(final TextView tv) {
        return new SeekBar.OnSeekBarChangeListener() {
            double progress = 0.0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue * 0.10;

                String resultado = String.format(Locale.ROOT, "%.2f", progress);
                tv.setText(resultado);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };
    }

    private View.OnClickListener onClickAvancar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //npAnoInicial = (NumberPicker) findViewById(R.id.pickerAnoInicial);
                //sMes = (Spinner) findViewById(R.id.spinnerMes);
                TextView tGrana = (TextView) findViewById(R.id.editTextGranaConta);
                TextView tAlerta = (TextView) findViewById(R.id.editTextAlerta);

                String grana = tGrana.getText().toString();
                String alerta = tAlerta.getText().toString();

                Log.d("TAG", npAnoInicial.getValue() + " - " + sMes.getSelectedItem()
                        + " - " + grana  + " - " + alerta);

                Intent intent = new Intent(getContext(), Diario.class);
                Bundle params = new Bundle();
                params.putInt("anoInicial", npAnoInicial.getValue());
                params.putInt("mesInicial", sMes.getSelectedItemPosition()+1);
                params.putString("usuario", usuario);

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

}
