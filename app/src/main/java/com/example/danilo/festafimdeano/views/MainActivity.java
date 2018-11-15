package com.example.danilo.festafimdeano.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.danilo.festafimdeano.R;
import com.example.danilo.festafimdeano.SecurityPreferences.SecurityPreferences;
import com.example.danilo.festafimdeano.constants.FimDeAnoConstants;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.today = (TextView) findViewById(R.id.text_today);
        this.mViewHolder.texteDayleft = (TextView) findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfig = (Button) findViewById(R.id.button_confirm);

        this.mViewHolder.buttonConfig.setOnClickListener(this);
        this.mSecurityPreferences = new SecurityPreferences(this);
        this.mViewHolder.today.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        String daysleft = String.format("%s %s", String.valueOf(this.getDaysLeftToEndToYear()), getString(R.string.dias));
        this.mViewHolder.texteDayleft.setText(daysleft);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.VerifyPreference();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        int teste = view.getId();
        if (teste == R.id.button_confirm) {

            String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE);

            Intent details = new Intent(this, DetailsActivity.class);

            details.putExtra(FimDeAnoConstants.PRESENCE, presence);


            startActivity(details);

        }
    }

    private int getDaysLeftToEndToYear() {
        Calendar calendartoday = Calendar.getInstance();
        int today1 = calendartoday.get(Calendar.DAY_OF_YEAR);

        Calendar calendarLastDay = Calendar.getInstance();
        int dayDecenver = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayDecenver - today1;
    }

    private void VerifyPreference() {

        String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE);
        if (presence.equals(""))
            this.mViewHolder.buttonConfig.setText(R.string.nao_confirmado);
        else if (presence.equals(FimDeAnoConstants.CONFIRM_WILL_GO))
            this.mViewHolder.buttonConfig.setText((R.string.sim));
        else
            this.mViewHolder.buttonConfig.setText(R.string.nao);

    }


    private static class ViewHolder {

        TextView today;
        TextView texteDayleft;
        Button buttonConfig;
    }
}
