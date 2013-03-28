package com.example.cookabook;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.app.LoaderManager;

public class MyList extends ListActivity {

   MyListAdapter mAdapter; //vår adapter som kommer att lägga in bilder/text i listan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new MyListAdapter(this);  //skapa adaptern
        setListAdapter(mAdapter);

    }
    
    /*@Override 
    public void onListItemClick(ListView l, View v, int position, long id) {	//man trycker på ett element i listan
    	Intent intent = new Intent(this, Activity2.class);
    	intent.putExtra("index", position);
    	startActivity(intent);						//en ny aktivitet startas
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);	//inget att bry sig om
        return true;
    }
}
