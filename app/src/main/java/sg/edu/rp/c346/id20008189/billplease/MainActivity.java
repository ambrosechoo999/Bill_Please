package sg.edu.rp.c346.id20008189.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
EditText amount;
EditText numPax;
ToggleButton svs;
ToggleButton gst;
TextView eachPays;
Button split;
EditText discount;
RadioGroup rgMode;
Button reset;
TextView tvBills;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount=findViewById(R.id.etAmt);
        numPax=findViewById(R.id.etDiscount);
        svs=findViewById(R.id.btnSvs);
        gst=findViewById(R.id.btnGst);
        eachPays=findViewById(R.id.tvPay);
        split=findViewById(R.id.btnSplit);
        discount=findViewById(R.id.etDiscount);
        rgMode=findViewById(R.id.rgMode);
        reset=findViewById(R.id.btnReset);
        tvBills=findViewById(R.id.tvBills);
        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText().toString().trim().length()!=0 && numPax.getText().toString().trim().length()!=0){
                    double origAmt= Double.parseDouble(amount.getText().toString());
                    double newAmt=0.0;
                    if(!svs.isChecked() && !gst.isChecked()){
                        newAmt=origAmt;
                    }else if (svs.isChecked() &&!gst.isChecked()){
                        newAmt=origAmt*1.1;
                    }else if (
                        !svs.isChecked() &&gst.isChecked()){
                        newAmt=origAmt*1.07;
                    }
                    else{
                        newAmt=origAmt*1.17;
                    }if(discount.getText().toString().trim().length()!=0){
                        newAmt*=1-Double.parseDouble(discount.getText().toString())/100;

                        }
                    String mode="in cash";
                    if(rgMode.getCheckedRadioButtonId()==R.id.rbPaynow) {
                    mode="via Paynow to 912345678";

                    }
                    tvBills.setText("Total Bill: $"+String.format("%.2f",newAmt));
                    int numPerson= Integer.parseInt(numPax.getText().toString());
                    if(numPerson!=1){
                        eachPays.setText("Each Pays: $"+ String.format("%.2f",newAmt/numPerson)+mode);
                    }else{
                        eachPays.setText("Each Pays: $" +newAmt+mode);
                    }
                }
            }
        });
    }
}