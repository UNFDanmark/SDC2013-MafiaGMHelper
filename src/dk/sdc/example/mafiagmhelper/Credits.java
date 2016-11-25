package dk.sdc.example.mafiagmhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Credits extends Activity {

	Button creditsback;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.credits);

		creditsback = (Button) findViewById(R.id.b_creditsback);

		// back aktiveres på click EA
		creditsback.setOnClickListener(new OnClickListener() {
			//Intent sendtomenu = new Intent(getApplicationContext(), Menu.class);

			// Send til menu
			@Override
			public void onClick(View v) {
				//startActivity(sendtomenu);
				finish();
			}
		});
	}
}