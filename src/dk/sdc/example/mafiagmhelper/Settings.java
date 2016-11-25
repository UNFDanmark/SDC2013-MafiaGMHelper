package dk.sdc.example.mafiagmhelper;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Settings extends Activity {
	
		public static ArrayList<String> wakeList = new ArrayList<String>();
		public static void genWakeList() {
			wakeList.clear();
			for (int i=1; i < RoleCreator.roleList.size(); i++) {
				RoleCreator rc = RoleCreator.roleList.get(i);
				for (int j=0; j < rc.getRolesInGame(); j++) {
					String name = rc.getName();
					if (rc.isFirstNight()) {
						name = "(first night)"+ name;
					}
					if (rc.isInGroup()) {
						wakeList.add(name);
						break;
					}
					wakeList.add(rc.getName() +" "+ (j+1));
				}
			}
		}
		public static int selected = 1;

		private Button settingsPlay;
		private Button settingsAdd;
		private ListView listView;
		private RoleAdapter adapter;
		
		
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.settings);
			
			//import af play og add knappen fra layout E
			settingsPlay = (Button) findViewById(R.id.b_settingsplay);
			settingsAdd = (Button) findViewById(R.id.b_new);
			listView = (ListView) findViewById(R.id.lv_settings);
			adapter = new RoleAdapter(this, 
			        R.layout.role_layout, RoleCreator.roleList);
			
			//mark selected
			listView.setAdapter(adapter);
			
			
			//play knappen aktiveres på click E
			settingsPlay.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					genWakeList();
					Intent sendbacktoplay = new Intent(Settings.this, Play.class);
					startActivity(sendbacktoplay);

				}
			});
			
			
			//add knappen aktiveres på click 
			settingsAdd.setOnClickListener(new OnClickListener() {				
										
				@Override
				public void onClick(View v) {
					RoleCreator.roleList.add(new RoleCreator(Role.class, "New role", false, false, false));
					((ArrayAdapter)listView.getAdapter()).notifyDataSetChanged();
					/*Intent sendtoAddrole = new Intent(Settings.this, AddRole.class);
					startActivityForResult(sendtoAddrole, 0);*/
				}
			});
			
	
		}

		@Override
		protected void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			((ArrayAdapter)listView.getAdapter()).notifyDataSetChanged();
		}
		
		
		
}
	

