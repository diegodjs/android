package diegocompany.granacontrol.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import diegocompany.granacontrol.R;
import diegocompany.granacontrol.models.DadosAlertas;
import diegocompany.granacontrol.utils.ActivityUtil;

public class Alertas extends ActivityUtil {

    private DatabaseReference dataRefAlertas = null;
    private SeekBar sbAlertaDiario = null;
    private SeekBar sbAlertaMensal = null;
    private TextView tvAlertaDiario = null;
    private TextView tvAlertaMensal = null;
    private Button btSalvar = null;
    private String id;
    private DadosAlertas dadosAlertas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertas);

        setUpToolbar();
        setupNavDrawer(R.id.nav_item_alertas);

        getSupportActionBar().setTitle(R.string.configuracaoAlertas);

        btSalvar = (Button) findViewById(R.id.buttonSalvar);
        btSalvar.setOnClickListener(onClickSalvar());


        id = profile.getId();

        //databaseAlertas = FirebaseDatabase.getInstance();
        dataRefAlertas = database.getReference(id);

        dataRefAlertas.addListenerForSingleValueEvent(returnDataAlertas());


        tvAlertaDiario = (TextView) findViewById(R.id.editTextAlertaDiario);
        sbAlertaDiario = (SeekBar) findViewById(R.id.seekbarAlertaDiario);
        sbAlertaDiario.setOnSeekBarChangeListener(seekBarChange(tvAlertaDiario));

        tvAlertaMensal = (TextView) findViewById(R.id.editTextAlertaMensal);
        sbAlertaMensal = (SeekBar) findViewById(R.id.seekbarAlertaMensal);
        sbAlertaMensal.setOnSeekBarChangeListener(seekBarChange(tvAlertaMensal));


    }

    private View.OnClickListener onClickSalvar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView tAlertaDiario = (TextView) findViewById(R.id.editTextAlertaDiario);
                TextView tAlertaMensal= (TextView) findViewById(R.id.editTextAlertaMensal);

                String alertaDiario = tAlertaDiario.getText().toString();
                String alertaMensal = tAlertaMensal.getText().toString();

                if ("".equals(alertaDiario) || "".equals(alertaMensal)) {
                    alert(R.string.avisoValor);
                }

                dadosAlertas = new DadosAlertas();
                dadosAlertas.setAlertaGastoDiario(alertaDiario);
                dadosAlertas.setAlertaGastoMensal(alertaMensal);

                dataRefAlertas.child("dadosAlertas").setValue(dadosAlertas);

                alert(R.string.alertasConfigurados);

            }
        };
    }

    private ValueEventListener returnDataAlertas() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dadosAlertas = dataSnapshot.child("dadosAlertas").getValue(DadosAlertas.class);

                if (dadosAlertas != null) {
                    tvAlertaDiario.setText(dadosAlertas.getAlertaGastoDiario());
                    tvAlertaMensal.setText(dadosAlertas.getAlertaGastoMensal());
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        };
    }

}
