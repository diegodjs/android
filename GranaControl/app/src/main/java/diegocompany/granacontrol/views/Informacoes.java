package diegocompany.granacontrol.views;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import diegocompany.granacontrol.R;
import diegocompany.granacontrol.utils.ActivityUtil;

public class Informacoes extends ActivityUtil {

    private SeekBar sbGranaConta = null;
    private SeekBar sbAlerta = null;
    private TextView tvGranaConta = null;
    private TextView tvAlerta = null;
    private Button btAvancar = null;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes);

        getSupportActionBar().setTitle(R.string.informacoesIniciais);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle args = getIntent().getExtras();
        usuario = args.getString("usuario");

        btAvancar = (Button) findViewById(R.id.buttonAvancar);
        btAvancar.setOnClickListener(onClickAvancar());

        tvGranaConta = (TextView) findViewById(R.id.editTextGranaConta);
        sbGranaConta = (SeekBar) findViewById(R.id.seekbarGranaConta);
        sbGranaConta.setOnSeekBarChangeListener(seekBarChange(tvGranaConta));

        tvAlerta = (TextView) findViewById(R.id.editTextAlerta);
        sbAlerta = (SeekBar) findViewById(R.id.seekbarAlerta);
        sbAlerta.setOnSeekBarChangeListener(seekBarChange(tvAlerta));
    }

    private View.OnClickListener onClickAvancar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView tGrana = (TextView) findViewById(R.id.editTextGranaConta);
                TextView tAlerta = (TextView) findViewById(R.id.editTextAlerta);

                String grana = tGrana.getText().toString();
                String alerta = tAlerta.getText().toString();

                Intent intent = new Intent(getContext(), Diario.class);
                Bundle params = new Bundle();
                params.putString("usuario", usuario);
                intent.putExtras(params);
                startActivity(intent);
            }
        };
    }
}
