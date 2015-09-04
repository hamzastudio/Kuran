package com.android.hamzastudio.kuran;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class PretragaFragment extends Fragment {
	private EditText wordView;
	private ArrayList<Sura> mSure;
	private String[] mSura;
	private ListView searchView; 
	private ArrayList<Search> search;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mSure=Sure.get(getActivity()).getSuras();
		search=new ArrayList<Search>();
		
	}
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup parent, 
			Bundle savedInstanceState ){
		View v=inflater.inflate(R.layout.fragment_pretraga, parent, false);
		wordView=(EditText)v.findViewById(R.id.rijec_pretrage);
		searchView=(ListView)v.findViewById(R.id.lista_pretrage);
		
		wordView.setOnEditorActionListener(new OnEditorActionListener (){
        	@Override
        	public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
        		if (actionId==EditorInfo.IME_ACTION_DONE){
        			if (v.length()!=0){
        				if(search!=null)search.clear();
        			String word=v.getText().toString(); 		
        			Resources res=getActivity().getResources();
        			for (int i=0;i<mSure.size();i++){
        				mSura=res.getStringArray(mSure.get(i).getReferenca());
        				for(int j=0;j<mSura.length;j++){
        					
        					if(mSura[j].contains(word)){
        						Search pretraga=new Search();
        						pretraga.setNazivSure(mSure.get(i).getNaziv());
        						pretraga.setTextAjeta(mSura[j]);
        						pretraga.setKojiAjet(""+(j+1));
        						search.add(pretraga);
        					}
        				}
        			}
        			SearchAdapter adapter = new SearchAdapter(search);
            		searchView.setAdapter(adapter);
        			return false;
        			}
        		}
        		
        		return true;
        	}
        	
        	
		});

	return v;}
	
	private class SearchAdapter extends ArrayAdapter<Search>{
		
		public SearchAdapter(ArrayList<Search> search) {
		super(getActivity(), 0, search);
			}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		// If we weren't given a view, inflate one
		if (convertView == null) {
		convertView = getActivity().getLayoutInflater()
		.inflate(R.layout.list_pretraga, null);
		}
		// Configure the view for this Sura
		Search c = getItem(position);
		TextView ajetTextView =
		(TextView)convertView.findViewById(R.id.pretraga_list_TextAjeta);
		ajetTextView.setText(c.getTextAjeta()+" ("+c.getNazivSure()+" - "+c.getKojiAjet()+")");

		return convertView;
		}
	}
}
