package com.example.banco;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Settings extends Activity{
	private EditText iPlayerPot;
	private EditText iBankPot;
	private RadioButton yesOut;
	private RadioButton noOut;
	private Button backtoMenu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		//Define
		iPlayerPot = (EditText) findViewById(R.id.iPlayerPot);
		iBankPot = (EditText)findViewById(R.id.iBankPot);
		yesOut = (RadioButton)findViewById(R.id.yes_out);
		noOut = (RadioButton)findViewById(R.id.no_out);
		backtoMenu = (Button)findViewById(R.id.backtoMenu);
		//End
		backtoMenu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try{
					AppConstants.PlayerPot = Integer.parseInt(iPlayerPot.getText().toString());
					AppConstants.BankPot = Integer.parseInt(iBankPot.getText().toString());
				}catch(NumberFormatException nfe){
					AlertDialog.Builder alert = new AlertDialog.Builder(Settings.this);
					alert.setTitle("Settings")
					.setMessage("You can only put integer values in the fields")
					.setPositiveButton("Okay", null);
					alert.show();

				}
				Intent i = new Intent(Settings.this,MainActivity.class);
				startActivity(i);
				finish();
			}
		});
	}
}

