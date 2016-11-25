package dk.sdc.example.mafiagmhelper;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PersonAdapter extends ArrayAdapter<Person> implements
		OnClickListener {
	public static final int NAME = 0;
	public static final int EVIL = 1;
	public static final int ROLE = 2;
	public static final int NOTES = 3;

	private Context context;
	private ArrayList<Person> people;
	AlertDialog.Builder builder;
	Person person;
	Button textName;
	Button textNotes;
	boolean start;

	private PersonAdapter own = this;

	String name = "Name";

	private class Tag {
		public Person p;
		public int id;

		public Tag(Person p, int id) {
			this.p = p;
			this.id = id;
		}

		public boolean setEvil() {
			if (person.getRole().isEvil()) {

			}
			return false;
		}
	}

	public PersonAdapter(Context context, int resource,
			ArrayList<Person> objects) {
		super(context, resource, objects);
		this.context = context;
		this.people = objects;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// render person_layout

		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.person_layout, null);
		}
		final ImageButton iconEvil = (ImageButton) convertView
				.findViewById(R.id.ib_good);

		person = people.get(position);

		textName = (Button) convertView.findViewById(R.id.personName);
		textName.setTag(new Tag(person, NAME));
		textName.setOnClickListener(this);
		textName.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				final Button b = (Button) v;
				Tag t = (Tag) b.getTag();
				final Person p = t.p;
				
				AlertDialog.Builder builder1 = new AlertDialog.Builder(
						context);
				String message = "Are you sure you want to remove this player?";
				if (start) {
					if (p.isAlive()) {
						message = "Kill player?";
					} else {
						message = "Revive player?";
					}
				}
				
				builder1.setMessage(message);
				builder1.setCancelable(true);
				builder1.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								if (start) {
									p.setAlive(!p.isAlive());
									own.notifyDataSetChanged();
									dialog.cancel();
								} else { //spil ikke start
									Play.people.remove(p);
									own.notifyDataSetChanged();
									dialog.cancel();
								}
								
							}

						});
				
				builder1.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								dialog.cancel();
							}
						});

				AlertDialog alert11 = builder1.create();
				alert11.show();
				return false;

			}

		});

		iconEvil.setTag(new Tag(person, EVIL));

		if (person.isAlive()) {
			if (person.getRole().isEvil()) {
				iconEvil.setImageResource(R.drawable.thumpdownscaled);
			} else {
				iconEvil.setImageResource(R.drawable.thumpupscaled);
			}
		} else {
			if (person.getRole().isEvil()) {
				iconEvil.setImageResource(R.drawable.thumpdowndead);
			} else {
				iconEvil.setImageResource(R.drawable.thumpupdead);
			}
		}

		Button textClass = (Button) convertView.findViewById(R.id.personClass);
		textClass.setTag(new Tag(person, ROLE));
		textClass.setOnClickListener(this);

		textNotes = (Button) convertView.findViewById(R.id.personNotes);
		textNotes.setTag(new Tag(person, NOTES));
		textNotes.setOnClickListener(this);

		// layout
		textName.setText(person.getName());
		textClass.setText(person.getRole().getRoleName());
		textNotes.setText(person.getNotes());

		return convertView;

	}

	@Override
	public void onClick(View v) {
		final Button b = (Button) v;
		Tag t = (Tag) b.getTag();
		final Person p = t.p;
		final int id = t.id;

		String title = "Error";
		String text = "Error";
		if (id == NAME) {
			title = "Name";
			text = p.getName();
		} else if (id == NOTES) {
			title = "Notes";
			text = p.getNotes();
		} else if (id == ROLE) {
			title = "Roles";
		}

		builder = new AlertDialog.Builder(context);
		builder.setTitle(title);

		// role selecter
		if (id == ROLE) {
			String[] roleNames = RoleCreator.toNameArray();
			builder.setItems(roleNames, new Dialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (which == RoleCreator.roleList.size()) {
						((Activity)context).finish();
					}
					p.setRole(RoleCreator.roleList.get(which).getRole());
					own.notifyDataSetChanged();
				}
			});
			// string modifier
		} else if (id == NAME || id == NOTES) {

			// Set up the input
			final EditText input = new EditText(context);
			input.setText(text);
			// Specify the type of input expected
			input.setInputType(InputType.TYPE_CLASS_TEXT);
			builder.setView(input);

			// Set up the buttons
			builder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							String text = input.getText().toString();
							b.setText(text);
							if (id == NAME) {
								p.setName(text);
							} else if (id == NOTES) {
								p.setNotes(text);
							}
						}
					});
			builder.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
		}

		builder.show();

	}
}
