package com.example.banco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Rules extends Activity{
	private Button backtoMenu;
	private Intent i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rules);
		backtoMenu = (Button)findViewById(R.id.backtoMenu);
		backtoMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				i = new Intent(Rules.this,MainActivity.class);
				startActivity(i);
				finish();
			}
		});
	}
}
