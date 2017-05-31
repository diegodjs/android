package diegocompany.granacontrol.utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Dih on 30/05/2017.
 */

public class ActivityUtil extends AppCompatActivity {

    public final String TAG = "TAG_GRANA_CONTROL";

    public Context getContext() {
        return this;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    public void alert(int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }
}
