package am.aksanmaulana.gmail.com.listviewactionbarv4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    EditText etData;
    String message, strData;
    TextView tvInfo;

    boolean editData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        etData = (EditText) findViewById(R.id.etData);
        tvInfo = (TextView) findViewById(R.id.tvInfo);

        Intent intent2 = getIntent();
        message = intent2.getStringExtra("messageEdit"); // pesan, kalau ada

        // check, activity 2 itu untuk edit atau tambah data
        if(message!=null){
            tvInfo.setText("EDIT ITEMS DATA");
            etData.setText(message);
            editData = true;
        }else{
            tvInfo.setText("ADD ITEMS DATA");
        }
    }

    public void klickButton(View v){
        etData = (EditText) findViewById(R.id.etData);
        strData =  etData.getText().toString();

        if(!editData) { // untuk yang tambah data
            Intent intent2 = getIntent();
            intent2.putExtra("stringAddData", strData);
            setResult(RESULT_OK, intent2);
            finish();
        }else{ // untuk yang edit data
            Intent intent2 = new Intent(this, MainActivity.class);
            intent2.putExtra("stringUpdateData", strData);
            setResult(RESULT_OK, intent2);
            finish();
        }
    }
}
