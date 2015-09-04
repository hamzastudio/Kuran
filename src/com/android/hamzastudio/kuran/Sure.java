package com.android.hamzastudio.kuran;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.content.res.Resources;

public class Sure {
	private String[] mNazivSura=new String[114];
	private String[] mPrevodSura=new String[114];
	private String[] mBrojajeta= new String[114];
	private int[] mReferenca={R.array.sura_1,R.array.sura_2,R.array.sura_3,R.array.sura_4,
			R.array.sura_5,R.array.sura_6,R.array.sura_7,R.array.sura_8,
			R.array.sura_9,R.array.sura_10,R.array.sura_11,R.array.sura_12,R.array.sura_13,
			R.array.sura_14,R.array.sura_15,R.array.sura_16,R.array.sura_17,R.array.sura_18,
			R.array.sura_19,R.array.sura_20,R.array.sura_21,R.array.sura_22,R.array.sura_23,
			R.array.sura_24,R.array.sura_25,R.array.sura_26,R.array.sura_27,R.array.sura_28,
			R.array.sura_29,R.array.sura_30,R.array.sura_31,R.array.sura_32,R.array.sura_33,
			R.array.sura_34,R.array.sura_35,R.array.sura_36,R.array.sura_37,R.array.sura_38,
			R.array.sura_39,R.array.sura_40,R.array.sura_41,R.array.sura_42,R.array.sura_43,
			R.array.sura_44,R.array.sura_45,R.array.sura_46,R.array.sura_47,R.array.sura_48,
			R.array.sura_49,R.array.sura_50,R.array.sura_51,R.array.sura_52,R.array.sura_53,
			R.array.sura_54,R.array.sura_55,R.array.sura_56,R.array.sura_57,R.array.sura_58,
			R.array.sura_59,R.array.sura_60,R.array.sura_61,R.array.sura_62,R.array.sura_63,
			R.array.sura_64,R.array.sura_65,R.array.sura_66,R.array.sura_67,R.array.sura_68,
			R.array.sura_69,R.array.sura_70,R.array.sura_71,R.array.sura_72,R.array.sura_73,
			R.array.sura_74,R.array.sura_75,R.array.sura_76,R.array.sura_77,R.array.sura_78,
			R.array.sura_79,R.array.sura_80,R.array.sura_81,R.array.sura_82,R.array.sura_83,
			R.array.sura_84,R.array.sura_85,R.array.sura_86,R.array.sura_87,R.array.sura_88,
			R.array.sura_89,R.array.sura_90,R.array.sura_91,R.array.sura_92,R.array.sura_93,
			R.array.sura_94,R.array.sura_95,R.array.sura_96,R.array.sura_97,R.array.sura_98,
			R.array.sura_99,R.array.sura_100,R.array.sura_101,R.array.sura_102,R.array.sura_103,
			R.array.sura_104,R.array.sura_105,R.array.sura_106,R.array.sura_107,R.array.sura_108,
			R.array.sura_109,R.array.sura_110,R.array.sura_111,R.array.sura_112,R.array.sura_113,
			R.array.sura_114};
	private ArrayList<Sura> mSuras;
	private static Sure sSure;
	private Context sAppContext;
	private Sure(Context appContext){
		sAppContext=appContext;
		mSuras= new ArrayList<Sura>();
		Resources res= appContext.getResources();
		mNazivSura= res.getStringArray(R.array.lista_sura);
		mPrevodSura= res.getStringArray(R.array.lista_prevod_sura);
		mBrojajeta=res.getStringArray(R.array.broj_ajeta);
		for (int i=0;i<114;i++){
			Sura s=new Sura();
			s.setNaziv(mNazivSura[i]);
			s.setPrevod(mPrevodSura[i]);
			s.setBrojajeta(mBrojajeta[i]);
			s.setReferenca(mReferenca[i]);
			mSuras.add(s);
		}
	}
		
	public static Sure get(Context c) {
	if (sSure == null) {
	sSure = new Sure(c.getApplicationContext());
	}
	return sSure;
	}
	public ArrayList<Sura> getSuras(){
		return mSuras;
	}
	public Sura getSura(UUID id) {
		for (Sura c : mSuras) {
		if (c.getId().equals(id))
		return c;
		}
		return null;
		}
}
