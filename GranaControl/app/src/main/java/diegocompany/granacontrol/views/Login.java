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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
                else if ("".equals(senha) ){

                    alert(R.string.insiraSenha);
                    return;
                }

                intent = new Intent(getContext(), Informacoes.class);
                params = new Bundle();
                params.putString("usuario", usuario.toLowerCase());
                intent.putExtras(params);
                startActivity(intent);

                tUsuario.setText("");
                tSenha.setText("");
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

}
