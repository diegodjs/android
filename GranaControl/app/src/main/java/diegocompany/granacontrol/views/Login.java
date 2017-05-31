package diegocompany.granacontrol.views;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import diegocompany.granacontrol.R;
import diegocompany.granacontrol.utils.ActivityUtil;

public class Login extends ActivityUtil {

    private Button btEntrar = null;
    private LinearLayout linearLayout = null;
    private TextView tUsuario = null;
    private String usuario = null;
    TextView tSenha = null;
    String senha = null;
    Intent intent = null;
    Bundle params = null;
    ActivityOptions options = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle(R.string.granaControl);

        btEntrar = (Button) findViewById(R.id.buttonEntrar);
        btEntrar.setOnClickListener(onClickEntrar());

        linearLayout = (LinearLayout) findViewById(R.id.linearLogin);
    }

    private View.OnClickListener onClickEntrar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tUsuario = (TextView) findViewById(R.id.editTextUsuario);
                tSenha = (TextView) findViewById(R.id.editTextSenha);
                usuario = tUsuario.getText().toString();
                senha = tSenha.getText().toString();

                if (usuario.indexOf(" ") > 0) {
                    alert(R.string.naoUtilizeEspaco);
                    return;
                }
                else if ("".equals(usuario) ){

                    alert(R.string.insiraUsuario);
                    return;
                }
                //else if ("".equals(senha) ){

                   // alert(R.string.insiraSenha);
                   // return;
                //}

                intent = new Intent(getContext(), Informacoes.class);
                params = new Bundle();
                params.putString("usuario", usuario.toLowerCase());
                intent.putExtras(params);
                options = retornaOption((View)linearLayout);
                startActivity(intent, options.toBundle());

                tUsuario.setText("");
                tSenha.setText("");

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_informacao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuInformacao) {
            alert(R.string.sobre);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public ActivityOptions retornaOption(View v) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions
                    .makeSceneTransitionAnimation(this, v, "viewLogin");
        }
        return options;
    }
}
