package diegocompany.granacontrol;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button btEntrar = null;
    private LinearLayout linearLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btEntrar = (Button) findViewById(R.id.buttonEntrar);
        btEntrar.setOnClickListener(onClickEntrar());

        linearLayout = (LinearLayout) findViewById(R.id.linearLogin);

    }

    private View.OnClickListener onClickEntrar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), Informacoes.class);

                ActivityOptions options = retornaOption((View)linearLayout);

                startActivity(intent, options.toBundle());



/*
                TextView tUsuario = (TextView) findViewById(R.id.editTextUsuario);
                TextView tSenha = (TextView) findViewById(R.id.editTextSenha);
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
                */
            }
        };
    }

    public ActivityOptions retornaOption(View v) {
        ActivityOptions options = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions
                    .makeSceneTransitionAnimation(this, v, "viewLogin");
        }
        return options;
    }

    private Context getContext() {
        return this;
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
