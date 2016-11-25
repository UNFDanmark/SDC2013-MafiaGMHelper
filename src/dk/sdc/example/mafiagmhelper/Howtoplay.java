package dk.sdc.example.mafiagmhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Howtoplay extends Activity {

	Button backhow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.howtoplay);

		backhow = (Button) findViewById(R.id.b_creditsback);

		// howto aktiveres på click EA
		backhow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}
}
