package com.example.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  static final String TAG = "MainActivity";
    public static ArrayList<Contact> contactsList = new ArrayList<>();
    private ContactAdapter adapter;
    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Started.");

        Intent intent = getIntent();
        ArrayList<Contact> parent = intent.getParcelableArrayListExtra("Contact");

        if( parent != null && parent.size() > 0 ) {
            contactsList = parent;

        }else {
            Contact ivan = new Contact("3005343933","Hansel Castro","Friends");
            contactsList.add(ivan);

        }


        adapter = new ContactAdapter(this,R.layout.adapterlist,contactsList);
        ListView myListView = (ListView) findViewById(R.id.ListView);
        myListView.setAdapter(adapter);
        //Assignment a view if the list is empty
        myListView.setEmptyView(findViewById(R.id.nodata));
        this.registerForContextMenu(myListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(this, CreateContact.class);
                intent.putParcelableArrayListExtra("Contact", contactsList);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //notificacion in second plan on the adapter
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public  void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo MenuInfo) {
        super.onCreateContextMenu(menu, v, MenuInfo);
        getMenuInflater().inflate(R.menu.menu_contextual, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case    R.id.ctx_delete:
                    deleteContact(info);

                    return true;
            default:
                    return super.onContextItemSelected(item);
        }

    }

    //Delete contact
    private  void deleteContact(AdapterView.AdapterContextMenuInfo info) {
        contactsList.remove(info.position);
        adapter.notifyDataSetChanged();
    }
}
