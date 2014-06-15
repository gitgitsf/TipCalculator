package com.example.tipcalculator;

//import static android.support.v4.app.FragmentActivity.TAG;
import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TipActivity extends Activity {

	protected static final String TAG = "TipActivity";
	private static final String TIP_TEXT = "Tip is:  ";
	private static final int PCT10 = 10;
	private static final int PCT15 = 15;
	private static final int PCT20 = 20;
	private float tipAmount = 0;

	private EditText etBillAmount;
	private TextView tvTipAmount;
	private Button btnPct10, btnPct15, btnPct20;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip);

		etBillAmount = (EditText) findViewById(R.id.et_amount);
		tvTipAmount = (TextView) findViewById(R.id.tv_tip_amount);

//		btnPct10 = (Button) findViewById(R.id.btn_pct10);
//		btnPct15 = (Button) findViewById(R.id.btn_pct15);
//		btnPct20 = (Button) findViewById(R.id.btn_pct20);

		// if (savedInstanceState == null) {
		// getSupportFragmentManager().beginTransaction()
		// .add(R.id.container, new PlaceholderFragment())
		// .commit();
		// }
	}
 
	// *************calculateTip(View v)**********************************
	// this calculateTip() is specified in activity_tip.xml in the android:onClick attribute
    //android:onClick="calculateTip" 
	
	public void calculateTip(View v) {
		Log.d(TAG, "calculateTip(View v)");

		switch (v.getId()) {
		case R.id.btn_pct10:
			Log.d(TAG, "onClick-10% button");
			processTipPercent(PCT10); // need to divide by 100
			displayTipAmount();
			break;
		case R.id.btn_pct15:
			Log.d(TAG, "onClick-15% button");
			processTipPercent(PCT15); // need to divide by 100
			displayTipAmount();
			break;
		case R.id.btn_pct20:
			Log.d(TAG, "onClick-20% button");
			processTipPercent(PCT20); // need to divide by 100
			displayTipAmount();
			break;
		}
	}
	
	// *************processTipPercent(int pct)**********************************
	private void processTipPercent(int pct) {
		Log.d(TAG, "processPennyConversion");
		String s = etBillAmount.getText().toString();
		if (s.length() <= 0) {
			tipAmount = 0;
			return;
		}

		float inp = Float.parseFloat(s);
		if (inp <= 0.00) {
			tipAmount = 0;
			return;
		}

		try {
			tipAmount = (inp * pct / 100);
		} catch (java.lang.ArithmeticException e) {
			Log.d(TAG, "tip calculation error occurred!");
		}

	}

	// *************processChange4Coins()**********************************
	private void displayTipAmount() {
		Log.d(TAG, "displayTipAmount");
		DecimalFormat df = new DecimalFormat("0.00");
		tvTipAmount.setText(TIP_TEXT + "$" + df.format(tipAmount));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tip, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
