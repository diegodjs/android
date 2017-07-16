package diegocompany.granacontrol.utils;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;

import diegocompany.granacontrol.R;
import diegocompany.granacontrol.views.Alertas;
import diegocompany.granacontrol.views.Diario;
import diegocompany.granacontrol.views.Login;
import diegocompany.granacontrol.views.RelatorioGeral;

/**
 * Created by Dih on 30/05/2017.
 */

public class ActivityUtil extends AppCompatActivity {

    public final String TAG = "TAG_GRANA_CONTROL";

    public Profile profile = Profile.getCurrentProfile();

    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference dataRefDiario = null;

    public final int ALERTA_DIARIO = 1;
    public final int ALERTA_MENSAL = 2;

    protected DrawerLayout drawerLayout;

    private Intent intent = new Intent();

    public ActivityUtil () {
        if (profile != null) {
            if (dataRefDiario == null) {
                dataRefDiario = database.getReference(profile.getId());
            }
        }
    }

    public Context getContext() {
        return this;
    }

    public SeekBar.OnSeekBarChangeListener seekBarChange(final TextView tv) {
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

    public String geraId() {

        Calendar ca = Calendar.getInstance();

        return String.valueOf(ca.get(Calendar.YEAR))
                + String.valueOf(ca.get(Calendar.MONTH)+1)
                + String.valueOf(ca.get(Calendar.DAY_OF_MONTH))
                + String.valueOf(ca.get(Calendar.HOUR_OF_DAY))
                + String.valueOf(ca.get(Calendar.MINUTE))
                + String.valueOf(ca.get(Calendar.SECOND))
                + String.valueOf(ca.get(Calendar.MILLISECOND));
    }

    public void showAlertaGasto(int tipoAlerta) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        if (tipoAlerta == ALERTA_DIARIO) {
            builder.setView(inflater.inflate(R.layout.dialog_alerta_diario, null));
        }
        else {
            builder.setView(inflater.inflate(R.layout.dialog_alerta_mensal, null));
        }

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void alert(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    public void alert(int resId, int duration) {
        Toast.makeText(this, resId, duration).show();
    }





    /* DRAWER */

    // Configura a Toolbar
    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    // Configura o nav drawer
    protected void setupNavDrawer(int itemSelected) {
        // Drawer Layout
        final ActionBar actionBar = getSupportActionBar();
        // Ícone do menu do Nav Drawer
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null && drawerLayout != null) {
            navigationView.setCheckedItem(itemSelected);

            if (profile != null) {
                // Atualiza a imagem e os textos do header
                setNavViewValues(navigationView, profile.getName(), "", R.drawable.saving_peq);
            }

            // Trata o evento de clique no menu
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // Seleciona a linha
                            menuItem.setChecked(true);
                            // Fecha o menu
                            closeDrawer();
                            // Trata o evento do menu
                            onNavDrawerItemSelected(menuItem);
                            return true;
                        }
                    });
        }
    }

    // Atualiza os dados do header do Navigation View
    public static void setNavViewValues(NavigationView navView, String nome, String email, int img) {
        View headerView = navView.getHeaderView(0);
        TextView tNome = (TextView) headerView.findViewById(R.id.tNome);
        TextView tEmail = (TextView) headerView.findViewById(R.id.tEmail);
        ImageView imgView = (ImageView) headerView.findViewById(R.id.img);
        tNome.setText(nome);
        tEmail.setText(email);
        imgView.setImageResource(img);
    }

    // Trata o evento do menu lateral
    private void onNavDrawerItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_item_alertas:
                intent.setClass(getContext(), Alertas.class);
                startActivity(intent);
                finishAfterTransition();
                break;
            case R.id.nav_item_controle_diario:
                intent.setClass(getContext(), Diario.class);
                startActivity(intent);
                finishAfterTransition();
                break;
            case R.id.nav_item_relatorio_geral:
                intent.setClass(getContext(), RelatorioGeral.class);
                startActivity(intent);
                finishAfterTransition();
                break;
            case R.id.nav_item_settings:
                intent.setClass(getContext(), Login.class);
                startActivity(intent);
                finishAfterTransition();
                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Trata o clique no botão que abre o menu
                if (drawerLayout != null) {
                    openDrawer();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    // Abre o menu lateral
    protected void openDrawer() {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    // Fecha o menu lateral
    protected void closeDrawer() {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer == null )
        {
            super.onBackPressed();
        }
        else {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

    }
}
