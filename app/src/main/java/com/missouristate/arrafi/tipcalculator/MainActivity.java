package com.missouristate.arrafi.tipcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private TipCalculator tipCalc;
    public NumberFormat money = NumberFormat.getCurrencyInstance( );
    private EditText billEditText;
    private EditText tipEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tipCalc = new TipCalculator( 0.17f, 100.0f );
        billEditText = findViewById(R.id.editText_EnterBill);
        tipEditText = findViewById(R.id.edtTXT_EnterPercent);
    }

    public void btn_CalculateTotal(View view) {
        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();

        TextView tipTextView = findViewById(R.id.textView_TipTotal);
        TextView totalTextView = findViewById(R.id.textView_TotalBill);
        try {
            // convert billString and tipString to floats
            float billAmount = Float.parseFloat( billString );
            int tipPercent = Integer.parseInt( tipString );
            // update the Model
            tipCalc.setBill( billAmount );
            tipCalc.setTip( .01f * tipPercent );
            // ask Model to calculate tip and total amounts
            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();
            // update the View with formatted tip and total amounts
            tipTextView.setText( money.format( tip ) );
            totalTextView.setText( money.format( total ) );
        } catch( NumberFormatException nfe ) {
            // pop up an alert view here
        }
    }
}
