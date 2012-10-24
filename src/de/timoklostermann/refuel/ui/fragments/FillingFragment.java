package de.timoklostermann.refuel.ui.fragments;

import android.R;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class FillingFragment extends ListFragment {
	
	private String[] test = {"1","2","3"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListAdapter myListAdapter = new ArrayAdapter<String> (getActivity(),android.R.layout.simple_list_item_1,test);
		setListAdapter(myListAdapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(android.R.id.list, container, false);
	}
	
	 @Override
	 public void onListItemClick(ListView l, View v, int position, long id) {
	  // TODO Open EditFillingActivity

	 }
}
