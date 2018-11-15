package com.example.danilo.festafimdeano.views;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.content.Context;

import com.example.danilo.festafimdeano.R;
import com.example.danilo.festafimdeano.SecurityPreferences.SecurityPreferences;
import com.example.danilo.festafimdeano.constants.FimDeAnoConstants;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mViewHolder.checkParticipate = (CheckBox) findViewById(R.id.check_participatin);

        this.mViewHolder.checkParticipate.setOnClickListener(this);
        this.mSecurityPreferences = new SecurityPreferences(this);

        this.loadDataFromActivity();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.check_participatin) {
            if (this.mViewHolder.checkParticipate.isChecked()) {
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRM_WILL_GO);


            } else {
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRM_WONT_GO);
            }

        }
    }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            String presence = extras.getString(FimDeAnoConstants.PRESENCE);
            if (presence.equals(FimDeAnoConstants.CONFIRM_WILL_GO)) {
                this.mViewHolder.checkParticipate.setChecked(true);
            } else {
                this.mViewHolder.checkParticipate.setChecked(false);
            }
        }

    }


    private static class ViewHolder {

        CheckBox checkParticipate;
    }
}

