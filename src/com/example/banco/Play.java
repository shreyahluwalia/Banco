package com.example.banco;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Play extends Activity implements android.view.View.OnClickListener{
	private int[] user_cards;
	private ImageView[] user_images;
	private boolean won = false;
	private TextView call;
	private int i;
	private int z;
	private Button in;
	private Button out;
	private ImageView banco;
	private TextView bankPot;
	private TextView playerPot;
	private ImageView secondCard;
	private SeekBar pSeekBar;
	private int BankPot;
	private int callseek;
	private Intent intent;
	private AlertDialog.Builder alert;
	private Players[] player;
	private int nop;
	private TextView nametop;
	private AlertDialog.Builder pOut;
	private boolean bOut;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);
		//Define
		z=0;
		nametop = (TextView)findViewById(R.id.nameTop);
		nop = AppConstants.nop;
		BankPot = AppConstants.BankPot;
		user_cards = new int[3];
		in = (Button)findViewById(R.id.in);
		out = (Button)findViewById(R.id.out);
		banco = (ImageView)findViewById(R.id.banco);
		bankPot = (TextView)findViewById(R.id.bankPot);
		playerPot = (TextView)findViewById(R.id.playerPot);
		secondCard = (ImageView)findViewById(R.id.secondCard);
		pSeekBar = (SeekBar)findViewById(R.id.seekBar);
		call = (TextView)findViewById(R.id.call);
		player = new Players[AppConstants.nop];
		bOut = false;
		//END
		for(i=0;i<AppConstants.nop;i++){
			player[i] = new Players();
		}
		for(i=0;i<AppConstants.nop;i++){
			player[i].name = AppConstants.names[i];
			player[i].pot = AppConstants.PlayerPot;
		}
		assignvalues();
		assignCards();
		pSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				call.setText("$"+seekBar.getProgress());
				callseek = seekBar.getProgress();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				call.setText("$"+progress);
			}
		});

		in.setOnClickListener(this);

		banco.setOnClickListener(this);

		out.setOnClickListener(this);

	}
	private void continueGame(){
		bOut = false;
		if(BankPot<1){
			alert = new AlertDialog.Builder(Play.this);
			alert.setTitle("Game Over")
			.setMessage("Would you like to play again? ")
			.setPositiveButton("Yes", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					intent = new Intent(Play.this,Play.class);
					startActivity(intent);
				}
			})
			.setNegativeButton("No", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					intent = new Intent(Play.this,MainActivity.class);
					startActivity(intent);
				}
			});
		}
		if(nop>1){
			if(player[z].pot<1){
				pOut = new AlertDialog.Builder(Play.this);
				pOut.setTitle("Banco")
				.setMessage(player[z].name+"'s out")
				.setPositiveButton("Okay", new OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						assignCards();
						secondCard.setVisibility(View.GONE);
					}
				})
				.show();
				nop--;
				bOut = true;
				assignCards();
				for(i=z;i<(AppConstants.nop-1);i++){
					player[i]=player[i+1];
				}
				AppConstants.nop--;
			}
		}
		if(nop<=1){
			alert = new AlertDialog.Builder(Play.this);
			alert.setTitle("Game Over")
			.setMessage("Would you like to play again?")
			.setPositiveButton("Yes", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					intent = new Intent(Play.this,Play.class);
					startActivity(intent);
					finish();
				}
			})
			.setNegativeButton("No", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					intent = new Intent(Play.this,MainActivity.class);
					startActivity(intent);
					finish();
				}
			})
			.show();
		}
	}
	private void assignvalues(){
		pSeekBar.setProgress(0);
		int max = 0;
		if(player[z].pot<BankPot)
			max= player[z].pot;
		else
			max = BankPot;
		pSeekBar.setMax(max);
		bankPot.setText("$"+BankPot);
		playerPot.setText("$"+player[z].pot);
	}
	private void calculate(){
		for(i=0;i<3;i++){
			if(user_cards[i]>39)
				user_cards[i]=user_cards[i]-39;
			else if(user_cards[i]>26)
				user_cards[i]=user_cards[i]-26;
			else if(user_cards[i]>13)
				user_cards[i]=user_cards[i]-13;
		}
		alert = new AlertDialog.Builder(Play.this);
		alert.setTitle("Banco");
		if((user_cards[1]>user_cards[0]&&user_cards[1]<user_cards[2])||(user_cards[1]>user_cards[2]&&user_cards[1]<user_cards[0])){
			alert.setMessage(player[z].name+" Won");
			won = true;
		}
		else{
			alert.setMessage(player[z].name+" Lost");
			won = false;
		}
		alert.setNeutralButton("Okay", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				assignCards();
				secondCard.setVisibility(View.GONE);
			}
		});
		new CountDownTimer(2000,2000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFinish() {
				if(bOut==false)
					alert.show();
			}
		}.start();
	}
	private void potRefresh(){
		if(won){
			BankPot = BankPot - callseek;
			player[z].pot = player[z].pot + callseek;
		}
		else if(!won){
			BankPot = BankPot + callseek;
			player[z].pot = player[z].pot - callseek;
		}
		assignvalues();
		continueGame();
		callseek = 0;
	}
	private void BancoCall(){
		if(player[z].pot>=BankPot){
			int id = getResources().getIdentifier("card_"+user_cards[1],"drawable", "com.example.banco");
			user_images[1].setImageResource(id);
			secondCard.setVisibility(View.VISIBLE);
			calculate();
			if(won){
				BankPot = 0;
				player[z].pot = player[z].pot + BankPot;
			}
			else if(!won){
				BankPot = BankPot + BankPot;
				player[z].pot = player[z].pot - BankPot;
			}
			assignvalues();
			continueGame();
			callseek = 0;
		}
		else{
			alert.setTitle("Banco")
			.setMessage("Not enough money in the pot to call Banco!")
			.setNeutralButton("Okay", null)
			.show();
		}
	}
	private void assignCards(){
		user_images = new ImageView[3];
		int c,r2,r,j;
		i=0;
		do
		{
			c=0;
			r2 = new Random().nextInt(52);
			r=r2+1;
			for(j=i;j>0;j--)
			{
				if(user_cards[j]==r)
					c=1;
			}
			if(c==0)
				user_cards[i]=r;
			i++;
		}while(i<3);

		//sorting cards


		user_images[0] = (ImageView) findViewById(R.id.firstCard);
		user_images[1] = (ImageView) findViewById(R.id.secondCard);	
		user_images[2] = (ImageView)findViewById(R.id.thirdCard);
		for(int i=0;i<3;i++){
			if(i==0||i==2){
				int id = getResources().getIdentifier("card_"+user_cards[i],"drawable", "com.example.banco");
				user_images[i].setImageResource(id);
			}
		}
		nametop.setText(player[z].name+"'s turn");
		assignvalues();
	}
	private void increament(){
		if(z>=(AppConstants.nop-1))
			z=0;
		else
			z++;
	}
	@Override
	public void onClick(View v) {
		alert = new AlertDialog.Builder(Play.this);
		if(v.getId()==R.id.banco){
			BancoCall();
			increament();
		}
		if(v.getId()==R.id.out){
			assignCards();
			increament();
		}
		if(v.getId()==R.id.in){
			if(callseek==0){
				alert = new AlertDialog.Builder(Play.this);
				alert.setTitle("Banco")
				.setMessage("You can't call with no money in pot")
				.setPositiveButton("Okay", null)
				.show();
			}
			else{
				int id = getResources().getIdentifier("card_"+user_cards[1],"drawable", "com.example.banco");
				user_images[1].setImageResource(id);
				secondCard.setVisibility(View.VISIBLE);
				calculate();
				potRefresh();
				increament();
			}
		}
	}
}
