package com.android.hamzastudio.kuran;

import java.util.UUID;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;


public class SuraFragment extends Fragment {
	public static final String EXTRA_SURA_ID="com.android.hamzastudio.kuran.sura_id";
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private Sura mSura;
	private String[] mAjeti;
	private int mCurrentIndex=0;
	private int mBrojAjetaZaPrikaz=1;
	private TextView mPrikazAjeta;
	private EditText mBrojAjeta;
	private EditText mBrojPrikazaAjeta;
	private GestureDetector gestureDetector;
	public static SuraFragment newInstance(UUID suraId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_SURA_ID, suraId);
		SuraFragment fragment = new SuraFragment();
		fragment.setArguments(args);
		return fragment;
		}
    private void updateAjet() {
    	String viseajeta=new String();
    	if (mCurrentIndex>=0) {
    	for (int i=0; i<mBrojAjetaZaPrikaz; i++){
    		if(mCurrentIndex+i<mAjeti.length) {
    		viseajeta=viseajeta+"{("+(mCurrentIndex+i+1)+")} "+mAjeti[mCurrentIndex+i]+" ";
    	} }
        mPrikazAjeta.setText(viseajeta);
    	}
    	else {
    		for (int i=0;i<(mBrojAjetaZaPrikaz+mCurrentIndex);i++){
    			viseajeta=viseajeta+"{("+(i+1)+")} "+mAjeti[i];
    		}
    		mPrikazAjeta.setText(viseajeta);
    	}
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		UUID suraId = (UUID)getArguments().getSerializable(EXTRA_SURA_ID);
				mSura = Sure.get(getActivity()).getSura(suraId);
				gestureDetector = new GestureDetector(this.getActivity(), new MyGestureDetector());
				getActivity().setTitle(mSura.getNaziv()+" ("+mSura.getPrevod()+")");
				Resources res=getActivity().getResources();
				mAjeti= res.getStringArray(mSura.getReferenca());

				
	}
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup parent, 
			Bundle savedInstanceState ){
		View v=inflater.inflate(R.layout.activity_sura_ajet, parent, false);
		mPrikazAjeta=(TextView)v.findViewById(R.id.prikaz_ajeta);
		updateAjet();
		
		
		mPrikazAjeta.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return gestureDetector.onTouchEvent(event);
			}
		});

        mBrojAjeta=(EditText)v.findViewById(R.id.ajet_broj);
    
        mBrojAjeta.setOnEditorActionListener(new OnEditorActionListener() {
        	@Override
        	public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
        		
        		if (actionId==EditorInfo.IME_ACTION_DONE){
        			if (v.length()!=0){
        			String ajet=v.getText().toString();      		
        			mCurrentIndex=Integer.parseInt(ajet)-1;
        			
        			if(mCurrentIndex<mAjeti.length && mCurrentIndex !=-1){
        			updateAjet(); return false;}
        			else {
        				Toast.makeText(getActivity(), "Sura ima "+mAjeti.length+" ajeta, poèev od 1", Toast.LENGTH_SHORT).show();
        				
        			}
        			}
        			
        		}
        		
        	return true;}
        });
        mBrojPrikazaAjeta=(EditText)v.findViewById(R.id.broj_ajeta_prikaz);
        mBrojPrikazaAjeta.setOnEditorActionListener(new OnEditorActionListener() {
        	@Override
        	public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
        		
        		if (actionId==EditorInfo.IME_ACTION_DONE){
        			if (v.length()!=0){
        			String broj=v.getText().toString();      		
        			mBrojAjetaZaPrikaz=Integer.parseInt(broj);
        			updateAjet();
        			return false;
        			}
        		}
        	return true;}
        });
		
		return v;
	}
	class MyGestureDetector extends SimpleOnGestureListener {

		@Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;
                // right to left swipe
                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                	next();
                }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    previews();
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }
		
			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY){
				int y=(int) distanceY;
				
						mPrikazAjeta.scrollBy(0, y);
				return false;
			}

            @Override
        public boolean onDown(MotionEvent e) {
              return true;
        }
    }
    private void previews() {
       	if (mCurrentIndex>0) {
            mCurrentIndex = mCurrentIndex - mBrojAjetaZaPrikaz;
            updateAjet();
        	}
        }
    private void next() {
    	if((mCurrentIndex+mBrojAjetaZaPrikaz)<mAjeti.length){
            mCurrentIndex = mCurrentIndex + mBrojAjetaZaPrikaz;
            updateAjet();
            }
    }
}
