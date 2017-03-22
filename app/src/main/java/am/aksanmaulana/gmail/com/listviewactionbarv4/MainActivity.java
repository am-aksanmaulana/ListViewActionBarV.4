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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
        lvData.setOnItemClickListener(new myAdapterView());
    }

    private class myAdapterView implements AdapterView.OnItemClickListener {
        //int mSelectedItem;
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
            String itemsData = (String) lvData.getItemAtPosition(position);
            message = itemsData;

            positionList = position;
            adapter.notifyDataSetChanged();

            Log.i("shadow", message);
        }
    }

    //action bar code
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mAdd:
                Intent intent3 = new Intent(this, Main2Activity.class);
                startActivityForResult(intent3, ADD_REQUEST);

                Log.i("shadow", "klick menu add");
                return true;

            case R.id.mEdit:
                Intent intent2 = new Intent(this, Main2Activity.class);
                intent2.putExtra("messageEdit", message);
                startActivityForResult(intent2, EDIT_REQUEST);

                Log.i("shadow", "klick menu edit");
                return true;

            case R.id.mDelete:
                arrData.remove(positionList);
                adapter.notifyDataSetChanged();

                Log.i("shadow", "klick menu delete");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

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
