package com.android.hamzastudio.kuran;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FavoritiFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
	}
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup parent, 
			Bundle savedInstanceState ){
		View v=inflater.inflate(R.layout.fragment_favoriti, parent, false);
	return v;}
}
