package dk.sdc.example.mafiagmhelper;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddRole extends Activity {

	private RadioButton good;
	private RadioButton evil;
	private RadioGroup goodevil;
	private Button cancel;
	private Button save;
	private CheckBox firstNight;
	private CheckBox individually;
	private EditText editRoleName;
	private RoleCreator rc;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addrole);
		
		rc = RoleCreator.roleList.get(Settings.selected);

		goodevil = (RadioGroup) findViewById(R.id.radiog_goodevil);

		good = (RadioButton) findViewById(R.id.radiob_good);
		evil = (RadioButton) findViewById(R.id.radiob_evil);
		firstNight = (CheckBox) findViewById(R.id.cb_fnight);
		individually = (CheckBox) findViewById(R.id.cb_group);
		cancel = (Button) findViewById(R.id.b_cancel);
		save = (Button) findViewById(R.id.b_saverole);
		editRoleName = (EditText) findViewById(R.id.et_rolename);
		LinearLayout ll = (LinearLayout) findViewById(R.id.buttonButtoms);
		
		
		editRoleName.setText(rc.getName());
		firstNight.setChecked(rc.isFirstNight());
		individually.setChecked(!rc.isInGroup());
		good.setChecked(!rc.isEvil());
		evil.setChecked(rc.isEvil());
		
		
		
		/*DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int h = dm.heightPixels;
		int w = dm.widthPixels;
		
		ll.setY((float) h/4);
		*/
		cancel.setOnClickListener(new OnClickListener() {
			
			// Send til settings
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				rc.setRoleName(editRoleName.getText().toString());
				//evil
				rc.setInGroup(!individually.isChecked());
				rc.setFirstNight(firstNight.isChecked());
				boolean evil = true;
				if (goodevil.getCheckedRadioButtonId() == R.id.radiob_good) {
					evil = false;
				}
				rc.setEvil(evil);
				finish();
			}
		});

	}
	

	public void onRadioButtonClicked(View view) {
		/*// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();

		// Check which radio button was clicked
		switch (view.getId()) {
		case R.id.radiob_good:
			if (checked)
				boolEvil = false;
			break;
		case R.id.radiob_evil:
			if (checked)
				boolEvil = true;
			break;
		}*/
		

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		
	}
}
