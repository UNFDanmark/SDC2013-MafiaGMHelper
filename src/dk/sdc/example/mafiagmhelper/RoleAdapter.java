package dk.sdc.example.mafiagmhelper;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

public class RoleAdapter extends ArrayAdapter<RoleCreator> {
	private Context context;
	private AlertDialog.Builder builder;

	private RoleAdapter own = this;

	public RoleAdapter(Context context, int resource,
			ArrayList<RoleCreator> objects) {
		super(context, resource, objects);
		this.context = context;
	}

	public View getView(int pos, View convertView, ViewGroup parent) {
		//render role_layout
		if (convertView == null) {
			LayoutInflater vi =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.role_layout, null);
		}
        RoleCreator rc = RoleCreator.roleList.get(pos);
        boolean inGroup = rc.isInGroup();
        
        Button textName = (Button) convertView.findViewById(R.id.roleName);
        textName.setText(rc.getName());
        
        ImageButton moveUp = (ImageButton) convertView.findViewById(R.id.up);
        ImageButton moveDown = (ImageButton) convertView.findViewById(R.id.down);
        Button plus = (Button) convertView.findViewById(R.id.right);
        Button minus = (Button) convertView.findViewById(R.id.left);
        TextView value = (TextView) convertView.findViewById(R.id.value);
        value.setText(rc.getRolesInGame() +"");
        CheckBox active = (CheckBox)convertView.findViewById(R.id.active);
        
        textName.setTag((Integer) pos);
        moveUp.setTag((Integer) pos);
        moveDown.setTag((Integer) pos);
        minus.setTag((Integer) pos);
        plus.setTag((Integer) pos);
        
        if (pos <= 1) {
        	moveUp.setVisibility(View.INVISIBLE);
        } else {
        	moveUp.setVisibility(View.VISIBLE);
        }
        if (pos >= RoleCreator.roleList.size()-1 || pos == 0) {
        	moveDown.setVisibility(View.INVISIBLE);
        } else {
        	moveDown.setVisibility(View.VISIBLE);
        }
        if (pos == 0/* || inGroup*/) { //villager + check
        	plus.setVisibility(View.GONE);
        	value.setVisibility(View.GONE);
        	minus.setVisibility(View.GONE);
        } else {
        	plus.setVisibility(View.VISIBLE);
        	value.setVisibility(View.VISIBLE);
        	minus.setVisibility(View.VISIBLE);
        }
        //if (pos == 0 || !inGroup) {
        	active.setVisibility(View.GONE);
        /*} else {
        	active.setVisibility(View.VISIBLE);
        }*/
        
        textName.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				if ((Integer) v.getTag() == 0) {
					return; //cannot modify/delete villager
				}
				String[] roleNames = {"Modify", "Delete", "Cancel"};
				builder = new AlertDialog.Builder(
						context);
				builder.setTitle(RoleCreator.roleList.get((Integer) v.getTag()).getName());
				builder.setItems(roleNames, new Dialog.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						int pos = (Integer) v.getTag();
						if (which == 0) { //modify
							Settings.selected = pos;
							//new activity
							Intent sendtoAddrole = new Intent(context, AddRole.class);
							((Activity)context).startActivityForResult(sendtoAddrole, 0);
						} else if (which == 1) { //delete
							RoleCreator.roleList.remove(pos);
							own.notifyDataSetChanged();
						}
						//dialog.cancel();
					}
				});
				builder.show();
			}
		});
        
        moveUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int pos = (Integer) v.getTag();
				RoleCreator tmp  = RoleCreator.roleList.get(pos);
				RoleCreator.roleList.set(pos, RoleCreator.roleList.get(pos-1));
				RoleCreator.roleList.set(pos-1, tmp);
				own.notifyDataSetChanged();
			}
		});
        
        moveDown.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int pos = (Integer) v.getTag();
				RoleCreator tmp  = RoleCreator.roleList.get(pos);
				RoleCreator.roleList.set(pos, RoleCreator.roleList.get(pos+1));
				RoleCreator.roleList.set(pos+1, tmp);
				own.notifyDataSetChanged();
			}
		});
        plus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int pos = (Integer) v.getTag();
				RoleCreator rc = RoleCreator.roleList.get(pos);
				rc.setRolesInGame(rc.getRolesInGame() +1);
				own.notifyDataSetChanged();
			}
			
		});
		 minus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int pos = (Integer) v.getTag();
				RoleCreator rc = RoleCreator.roleList.get(pos);
				if (rc.getRolesInGame() == 0) {
					return;
				}
				rc.setRolesInGame(rc.getRolesInGame() -1);
				own.notifyDataSetChanged();
				
			}
		 });
        /*
        TextView textName = (TextView) convertView.findViewById(R.id.personName);
        textName.setText(person.getName());
        
        TextView textEvil = (TextView) convertView.findViewById(R.id.personEvil);
        textEvil.setText(person.getMafiaClass().isEvil() ? "Evil" : "Good");
        
        TextView textClass = (TextView) convertView.findViewById(R.id.personClass);
        textClass.setText(person.getMafiaClass().getRoleName());
        
        TextView textNotes = (TextView) convertView.findViewById(R.id.personNotes);
        textNotes.setText(person.getNotes());
        */
        
        
        return convertView;
	}
}
