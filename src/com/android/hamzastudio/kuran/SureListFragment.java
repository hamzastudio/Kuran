package com.android.hamzastudio.kuran;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SureListFragment extends ListFragment {
	private ArrayList<Sura> mSuras;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setHasOptionsMenu(true);
	getActivity().setTitle(R.string.app_name);
	mSuras = Sure.get(getActivity()).getSuras();
	SuraAdapter adapter = new SuraAdapter(mSuras);
			setListAdapter(adapter);
			
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	Sura s = ((SuraAdapter)getListAdapter()).getItem(position);
	Intent i = new Intent(getActivity(), SuraAjetActivity.class);
	i.putExtra(SuraFragment.EXTRA_SURA_ID, s.getId());
	startActivity(i);
	}
	private class SuraAdapter extends ArrayAdapter<Sura> {
			public SuraAdapter(ArrayList<Sura> suras) {
			super(getActivity(), 0, suras);
				}
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
			// If we weren't given a view, inflate one
			if (convertView == null) {
			convertView = getActivity().getLayoutInflater()
			.inflate(R.layout.list_item_sura, null);
			}
			// Configure the view for this Sura
			Sura c = getItem(position);
			TextView nazivTextView =
			(TextView)convertView.findViewById(R.id.sura_list_item_nazivTextView);
			nazivTextView.setText((position+1)+". "+c.getNaziv());
			TextView prevodTextView =
			(TextView)convertView.findViewById(R.id.sura_list_item_prevodTextView);
			prevodTextView.setText(c.getPrevod());
			TextView brojCheckBox =
			(TextView)convertView.findViewById(R.id.sura_list_item_brojajeta);
			brojCheckBox.setText(c.getBrojajeta());
			return convertView;
			}
			}
	@Override
	public void onCreateOptionsMenu (Menu menu, MenuInflater inflater){
		super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.kuran, menu);
        
    }
}
