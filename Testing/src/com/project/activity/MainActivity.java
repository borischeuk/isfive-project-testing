package com.project.activity;

import java.util.List;

import com.example.testing.R;
import com.j256.ormlite.dao.Dao;
import com.project.control.DatabaseHelper;
import com.project.control.ListViewAdapter;
import com.project.model.VocabList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

	ListView mainlist;
	DatabaseHelper dbHelper;
	Dao<VocabList, ?> vocabListDao;
	List<VocabList> vocabListList;
	
	ListViewAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                        
        // initialize list
        try {
        	dbHelper = new DatabaseHelper(this);
			vocabListDao = dbHelper.getDao(VocabList.class);
			vocabListList = vocabListDao.queryForAll();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // set List View`
        mainlist = (ListView)findViewById(R.id.mainlist); 
        listAdapter = new ListViewAdapter(this, vocabListList, false);
        mainlist.setAdapter(listAdapter); 
        mainlist.setOnItemClickListener(listener);
    }
    
    private ListView.OnItemClickListener listener = new ListView.OnItemClickListener() {
    	@Override
    	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    		Toast.makeText(MainActivity.this, "You Clicked at " + position, Toast.LENGTH_SHORT).show();
    	}
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
