package com.android.hamzastudio.kuran;

import java.util.UUID;

import android.support.v4.app.Fragment;

public class SuraAjetActivity extends SingleFragmentActivity {
	@Override
	protected Fragment createFragment() {
		UUID suraId = (UUID)getIntent()
				.getSerializableExtra(SuraFragment.EXTRA_SURA_ID);
				return SuraFragment.newInstance(suraId);
	}

}
