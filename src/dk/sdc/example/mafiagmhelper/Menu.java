package dk.sdc.example.mafiagmhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menu extends Activity {
	Button credits;
	Button howto;
	Button play;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		// import af knapper fra layout - EA
		credits = (Button) findViewById(R.id.b_credits);
		howto = (Button) findViewById(R.id.b_howto);
		play = (Button) findViewById(R.id.b_settings);

		// play aktiveres på click EA
		play.setOnClickListener(new OnClickListener() {
			Intent sendtosettings = new Intent(Menu.this, Settings.class);

			// Send til play (gamle settings)
			@Override
			public void onClick(View v) {
				startActivity(sendtosettings);

			}
		});
		// howto aktiveres på click EA
		howto.setOnClickListener(new OnClickListener() {
			Intent sendtohowto = new Intent(Menu.this, Howtoplay.class);

			// Send til howto
			@Override
			public void onClick(View v) {
				startActivity(sendtohowto);

			}
		});

		// credits aktiveres på click EA
		credits.setOnClickListener(new OnClickListener() {
			Intent sendtocredits = new Intent(Menu.this, Credits.class);

			// Send til credits
			@Override
			public void onClick(View v) {
				startActivity(sendtocredits);

			}
		});
	}
}
