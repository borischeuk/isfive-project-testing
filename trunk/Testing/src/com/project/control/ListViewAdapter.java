package com.project.control;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.testing.R;
import com.project.model.VocabList;

public class ListViewAdapter extends ArrayAdapter<VocabList> {

	private boolean bmk;

	// bmk == TRUE: in bookmark view
	// bmk == FALSE: in normal view
	public ListViewAdapter(Context context, List<VocabList> vocabListList, boolean bmk) {
	        super(context, 0, vocabListList);
	        this.bmk = bmk;
	        
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	   
	    VocabList vocab = getItem(position);    

	    if (convertView == null) {
	       convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
	    }

	    TextView bar = (TextView) convertView.findViewById(R.id.bar);
	    TextView tvName = (TextView) convertView.findViewById(R.id.item);
	    
	    /*
	    if (position %2 == 0) {
	    	vocab.setBmk(true);
	    }
	    */
	    // change bar color if it is bookmarked in normal view
	    if (bmk == false && vocab.getBmk() == true) {
	    	bar.setBackgroundColor(Color.rgb(71, 163, 255));
	    }
	    tvName.setText(vocab.getVocab());

	    return convertView;
	}
	
}