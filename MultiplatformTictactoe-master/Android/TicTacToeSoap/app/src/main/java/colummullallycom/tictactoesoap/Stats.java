package colummullallycom.tictactoesoap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ConnectSoap.Soap;

public class Stats extends AppCompatActivity {

    Button rtbt;
    TextView stbx;
    String uName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        rtbt = (Button) findViewById(R.id.ReturnBtn);
        stbx = (TextView) findViewById(R.id.statText);
        Soap db = new Soap();
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        String uName = b.getString("Code");
        String stat=db.getStats(uName);
        stbx.setText(stat);
    }

    protected void onStart() {
        super.onStart();
        rtbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Stats.this, Menu.class);
                Bundle bundle = new Bundle();
                bundle.putString("Code", uName);
                myIntent.putExtras(bundle);
                startActivity(myIntent);
            }
        });
    }


}
