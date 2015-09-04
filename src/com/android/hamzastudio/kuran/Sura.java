package com.android.hamzastudio.kuran;

import java.util.UUID;

public class Sura {
	private UUID mId;
	private String mNaziv;
	private String mPrevod;
	private String mBrojajeta;
	private int mReferenca;
	public Sura (){
		mId=UUID.randomUUID();
	}
	public UUID getId() {
		return mId;
	}
	public String getNaziv() {
		return mNaziv;
	}
	public String getPrevod() {
		return mPrevod;
	}
	public String getBrojajeta() {
		return mBrojajeta;
	}
	public int getReferenca() {
		return mReferenca;
	}
	@Override
	public String toString(){
		return mNaziv;
	}
	public void setNaziv(String mNaziv) {
		this.mNaziv = mNaziv;
	}
	public void setPrevod(String mPrevod) {
		this.mPrevod = mPrevod;
	}
	public void setBrojajeta(String mBroj_ajeta) {
		this.mBrojajeta = mBroj_ajeta;
	}
	public void setReferenca(int mReferenca) {
		this.mReferenca = mReferenca;
	}
}
