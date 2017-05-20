package diegocompany.granacontrol;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btEntrar = (Button) findViewById(R.id.btEntrar);
        btEntrar.setOnClickListener(onClickEntrar());
    }

    private View.OnClickListener onClickEntrar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tUsuario = (TextView) findViewById(R.id.tUsuario);
                TextView tSenha = (TextView) findViewById(R.id.tSenha);
                String usuario = tUsuario.getText().toString();
                String senha = tSenha.getText().toString();

                //if ("di".equals(usuario) && "1".equals(senha)) {
                    Intent intent = new Intent(getContext(), Informacoes.class);
                    Bundle params = new Bundle();
                    params.putString("nome", "Diego José de Souza");
                    intent.putExtras(params);
                    startActivity(intent);
                    tUsuario.setText("");
                    tSenha.setText("");
                //}
                //else {
                  //  alert("Deu Ruim!");
                //}
            }
        };
    }

    private Context getContext() {
        return this;
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
