package colummullallycom.tictactoesoap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import ConnectSoap.Soap;

public class Invite extends AppCompatActivity {
    String uName;
    ListView invitels;
    Soap db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        uName = (String) b.get("Code");
        invitels=(ListView)findViewById(R.id.iList);
        db= new Soap();

        String[] values=db.getOptions(uName);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        invitels.setAdapter(adapter);
    }
    @Override
    protected void onStart(){
        super.onStart();
        invitels.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  joiner    = (String) invitels.getItemAtPosition(position);
                db.invite(uName,joiner);
                Intent myIntent=new Intent(Invite.this,Menu.class);
                Bundle bundle= new Bundle();
                bundle.putString("Code",uName);
                myIntent.putExtras(bundle);
                startActivity(myIntent);



            }
        });
    }
}


