package com.pragmatouch.calculator;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import expr.Expr;
import expr.Parser;

public class main extends Activity {
	GridView mKeypadGrid;
	TextView userInputText;
	TextView memoryStatText;

	KeypadAdapter mKeypadAdapter;
	TextView mStackText;
	String input = "";

	/**
	 * Fixes orientation and removes title
	 */
	private void setFixedOrientation() {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	@Override
	public void onBackPressed() {
		// your code.
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setFixedOrientation();

		setContentView(R.layout.main);

		// Get reference to the keypad button GridView
		mKeypadGrid = (GridView) findViewById(R.id.grdButtons);

		// Get reference to the user input TextView
		userInputText = (TextView) findViewById(R.id.txtInput);
		userInputText.setText("0");

		memoryStatText = (TextView) findViewById(R.id.txtMemory);
		memoryStatText.setText("mem");

		mStackText = (TextView) findViewById(R.id.txtStack);
		// mStackText.setText ("stack");

		// Create Keypad Adapter
		mKeypadAdapter = new KeypadAdapter(this);

		// Set adapter of the keypad grid
		mKeypadGrid.setAdapter(mKeypadAdapter);

		// Set button click listener of the keypad adapter
		mKeypadAdapter.setOnButtonClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Button btn = (Button) v;
				// Get the KeypadButton value which is used to identify the
				// keypad button from the Button's tag
				KeypadButton keypadButton = (KeypadButton) btn.getTag();

				// Process keypad button
				ProcessKeypadInput(keypadButton);
			}
		});

		mKeypadGrid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

			}
		});

	}

	private void doCalc() 
	{
		Expr expr;
		final double p = 1e17; // precision
		try 
		{
			expr = Parser.parse(input);
			double v = Math.round(expr.value()*p)/p; 
			userInputText.setText("" + v);
			input = "";
		} 
		catch (Exception ex) 
		{
			userInputText.setText("ERROR");
		}
	}

	private void ProcessKeypadInput(KeypadButton keypadButton) {
		Toast.makeText(this, keypadButton.getText(), Toast.LENGTH_SHORT).show();

		switch (keypadButton) 
		{
		case RECIPROC:
			if (input.isEmpty())
			{
				input = (String) userInputText.getText();
			}
			input = "1/(" + input + ")";
			doCalc();
			break;

		case C:
			if (input.isEmpty() == false)
				input = input.substring(0, input.length() - 1);
			break;

		case CE:
			input = "";
			userInputText.setText("0");
			break;

		case CALCULATE:
			doCalc();
			break;

		default:
			input += keypadButton.getText();
			break;
		}

		mStackText.setText(input);
		mStackText.invalidate();
	}
}
