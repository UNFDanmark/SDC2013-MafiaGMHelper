package dk.sdc.example.mafiagmhelper;

import java.util.ArrayList;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils.StringSplitter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

//import android.widget.ArrayAdapter;

public class Play extends Activity {
	public static ArrayList<Person> people = new ArrayList<Person>();
	public static boolean start = false;

	Button left;
	Button right;
	ListView listView;
	PersonAdapter adapter;
	SharedPreferences prefs;
	Button nightcyclus;
	Button playback;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);

		// import af knapper og adapter
		playback = (Button) findViewById(R.id.b_playback);		
		right = (Button) findViewById(R.id.b_right);
		left = (Button) findViewById(R.id.b_creditsback);
		listView = (ListView) findViewById(R.id.playerList);
		adapter = new PersonAdapter(this, R.layout.person_layout, people);
		listView.setAdapter(adapter);
		// import start from prefMan
		prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		loadPrefs();
		updateButtons();

		//back knap
		playback.setOnClickListener(new OnClickListener() {
				
			
				@Override
			public void onClick(View v) {
		//	Intent backtosettings = new Intent(Play.this, Settings.class);
			//startActivity(backtosettings);
				finish();
				
			}
		});
		
		right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!start) {
					// add
					people.add(new Person());
					((ArrayAdapter) listView.getAdapter())
							.notifyDataSetChanged();
				} else{
					// night
					showNightCycle(v);
				}
			}
		});

		left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!start) {
					// start
					start = true;
					savePrefs();
				} else {
					// restart
					for (int i = 0; i < people.size(); i++) {
						Person p = people.get(i);
						p.resetPerson();
						((ArrayAdapter) listView.getAdapter())
								.notifyDataSetChanged();
					}

					start = false;
					savePrefs();

				}

			}
		});

		
		

	}
	private void showNightCycle(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(Play.this);

		ListView viewlist = new ListView(getApplicationContext());

		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_list_item_1,Settings.wakeList);
		viewlist.setAdapter(adapter);

		builder.setView(viewlist);
		builder.setTitle("Night Phases");
		builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.show();
	}

	public void savePrefs() {
		Editor edit1 = prefs.edit();
		edit1.putBoolean("start", start);
		edit1.commit();
		updateButtons();
		adapter.start = start;
	}

	public void loadPrefs() {
		// start = prefs.getBoolean("start", false);
		// night = prefs.getBoolean("night", false);
	}

	public void updateButtons() {
		if (!start) {
			left.setText("Start game");
			right.setText("Add player");
			//right.setVisibility(View.VISIBLE);
		} else {
			left.setText("Restart");
			right.setText("Night");
			//right.setVisibility(View.INVISIBLE);
		}
	}
	
	

}
