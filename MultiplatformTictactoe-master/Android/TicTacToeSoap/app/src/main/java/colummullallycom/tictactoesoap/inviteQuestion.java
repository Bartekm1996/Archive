package colummullallycom.tictactoesoap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ConnectSoap.Soap;

public class inviteQuestion extends AppCompatActivity {

    private TextView texty;
    private Button accept;
    private Button deny;
    String uName;
    String joiner;
    Soap db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_question);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        db=new Soap();
        uName = (String) b.get("Code");
        joiner = (String) b.get("Code1");
        deny=(Button) findViewById(R.id.button16);
        accept=(Button) findViewById(R.id.button15);
        texty=(TextView) findViewById(R.id.textView4);
        texty.setText("Play against "+joiner+"?");
    }

    protected void onStart() {
            super.onStart();
            deny.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent myIntent=new Intent(inviteQuestion.this,Menu.class);
                    Bundle bundle= new Bundle();
                    bundle.putString("Code",uName);
                    myIntent.putExtras(bundle);
                    startActivity(myIntent);
                }
            });
            accept.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View V){
                db.beginGame(uName,joiner);
                Intent myIntent=new Intent(inviteQuestion.this,Game.class);
                Bundle bundle= new Bundle();
                bundle.putString("Code",uName);
                bundle.putString("Code1",joiner);
                bundle.putString("Code3", "X");
                myIntent.putExtras(bundle);
                startActivity(myIntent);
            }
        });
    }
}
