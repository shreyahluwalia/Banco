package com.example.banco;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{
	private Button newgame;
	private Button settings;
	private Button about;
	private Button ruleButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Defining
		newgame = (Button)findViewById(R.id.newgame);
		settings = (Button)findViewById(R.id.settings);
		about = (Button)findViewById(R.id.about);
		ruleButton = (Button)findViewById(R.id.ruleButton);
		//End
		newgame.setOnClickListener(this);
		settings.setOnClickListener(this);
		about.setOnClickListener(this);
		ruleButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==newgame.getId()){
			AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
			alert.setTitle("New Game");
			final EditText input = new EditText(MainActivity.this);
			input.setInputType(InputType.TYPE_CLASS_TEXT);
			input.setHint("Leave blank for single player");
			alert.setView(input);
			alert.setMessage("Enter the number of players")
			.setPositiveButton("Play", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					if(input.getText().toString().length()==0){
						AppConstants.nop = 1;
					}
					else{
						AppConstants.nop = Integer.parseInt(input.getText().toString());
					}
					Intent i = new Intent(MainActivity.this,Name.class);
					startActivity(i);
					finish();
				}
			});
			alert.setNegativeButton("Cancel", null);
			alert.show();
		}
		if(v.getId()==settings.getId()){
			Intent i = new Intent(MainActivity.this,Settings.class);
			startActivity(i);
			finish();
		}
		if(v.getId()==about.getId()){
			AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
			alert.setTitle("About")
			.setMessage("Created by Rachit Girdhar")
			.setPositiveButton("Cool", null);
			alert.show();
		}
		if(v.getId()==ruleButton.getId()){
			Intent i = new Intent(MainActivity.this,Rules.class);
			startActivity(i);
			finish();
		}
	}

}
