package diegocompany.granacontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Diario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);

        Bundle args = getIntent().getExtras();
        int anoInicial = args.getInt("anoInicial");
        int mesInicial = args.getInt("mesInicial");


        TableLayout tabela = (TableLayout) this.findViewById(R.id.tabela);

        TableRow tbrowHeader = new TableRow(this);

        CheckBox cbTodos = new CheckBox(this);
        TextView tvEntrada = new TextView(this);
        TextView tvSaida = new TextView(this);
        TextView tvDescricao = new TextView(this);
        tvEntrada.setText("Entrada");
        tvSaida.setText("Saída");
        tvDescricao.setText("Descrição");

        LinearLayout llHeader = new LinearLayout(this);
        llHeader.addView(cbTodos);
        llHeader.addView(tvEntrada);
        llHeader.addView(tvSaida);
        llHeader.addView(tvDescricao);

        tbrowHeader.addView(llHeader);
        tabela.addView(tbrowHeader);





       /* for(int i=1;i<30;i++) {
            TableRow tbrow=new TableRow(this);

            for(int j=1;j<=20;j++) {
                TextView tv1=new TextView(this);
                String s1 = Integer.toString(i);
                String s2 = Integer.toString(j);
                String s3 = s1+s2;
                int id = Integer.parseInt(s3);
                tv1.setId(id);

                tv1.setText("Dynamic TextView  no:     "+id);
                tbrow.addView(tv1);
            }
            ll.addView(tbrow);
        }*/
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
