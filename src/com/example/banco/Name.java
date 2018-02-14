package com.example.banco;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Name extends Activity{
	private MarginLayoutParams params;
	private int i;
	private LinearLayout names;
	private TextView[] text;
	private EditText[] edit;
	private Button okay;
	private Intent in;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.name);
		text = new TextView[AppConstants.nop];
		edit = new EditText[AppConstants.nop];
		names = (LinearLayout)findViewById(R.id.nameLayout);
		params = new MarginLayoutParams(MarginLayoutParams.MATCH_PARENT,50);
		params.setMargins(20,20,20,20);
		for(i=0;i<AppConstants.nop;i++){
			text[i] = new TextView(Name.this);
			edit[i] = new EditText(Name.this);
		}
		for(i=0;i<AppConstants.nop;i++){
			text[i].setText("Enter the name of Player "+(i+1));
			text[i].setTextColor(Color.WHITE);
			text[i].setTextSize(20);
			text[i].setLayoutParams(params);
			names.addView(text[i]);
			edit[i].setLayoutParams(params);
			edit[i].setHint("Leave blank for default");
			edit[i].setBackgroundColor(Color.WHITE);
			names.addView(edit[i]);
		}
		okay = new Button(Name.this);
		okay.setWidth(100);
		okay.setHeight(20);
		okay.setText("Okay");
		names.addView(okay);

		okay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				for(i=0;i<AppConstants.nop;i++){
					if(edit[i].getText().toString().length()==0)
						AppConstants.names[i]="Player "+(i+1);
					else
						AppConstants.names[i]=edit[i].getText().toString();
				}
				in = new Intent(Name.this,Play.class);
				startActivity(in);
				finish();
			}
		});
	}
}
