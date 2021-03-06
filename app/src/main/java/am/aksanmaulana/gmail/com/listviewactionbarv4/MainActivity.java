package am.aksanmaulana.gmail.com.listviewactionbarv4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public boolean checkList = false;
    static final int ADD_REQUEST = 23;  // untuk request add item
    static final int EDIT_REQUEST = 24; // untuk request edit item

    private ArrayList<String> arrData = new ArrayList<>();
    ListView lvData;

    ArrayAdapter adapter;
    String message;
    int positionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        lvData = (ListView) findViewById(R.id.lvData);
        adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, arrData);

        // hanya untuk dummy
        arrData.add("satu");
        arrData.add("dua");

        // set si adapter ke list view nya
        lvData.setAdapter(adapter);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemsData = (String) lvData.getItemAtPosition(position);
                message = itemsData;
                checkList = true;

                positionList = position;
                adapter.notifyDataSetChanged();

                Log.i("shadow", message);
            }
        });
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mAdd: // jika pilih menu add
                Intent intent3 = new Intent(this, Main2Activity.class);
                startActivityForResult(intent3, ADD_REQUEST);

                Log.i("shadow", "klick menu add");
                return true;

            case R.id.mEdit: // jika pilih menu edit
                if(checkList == true) {
                    Intent intent2 = new Intent(this, Main2Activity.class);
                    intent2.putExtra("messageEdit", message);
                    startActivityForResult(intent2, EDIT_REQUEST);

                    //Log.i("shadow", "klick menu edit");
                    //return true;
                }else{
                    String pesan = "Pilih satu data yang akan diubah!";
                    Toast toast = Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_SHORT);
                    toast.show();
                }
                Log.i("shadow", "klick menu edit");
                return true;

            case R.id.mDelete: // jika pilih menu delete
                if(checkList == true) {
                    arrData.remove(positionList);
                    adapter.notifyDataSetChanged();
                }else{
                    String pesan = "Pilih satu data yang akan dihapus!";
                    Toast toast = Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_SHORT);
                    toast.show();
                }

                Log.i("shadow", "klick menu delete");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        checkList = false;
        // cek request Code
        if(requestCode == ADD_REQUEST){     // jika yang dimaksud tambah data
            String receiveAddData = data.getStringExtra("stringAddData");
            arrData.add(receiveAddData);
            adapter.notifyDataSetChanged();

            Log.i("shadow", "add success!");
        }else if(requestCode == EDIT_REQUEST){ // jika yang dimakud itu ubah
            String receiveUpdateData = data.getStringExtra("stringUpdateData");
            arrData.set(positionList, receiveUpdateData);
            adapter.notifyDataSetChanged();

            Log.i("shadow", "edit success!");
        }
    }
}
