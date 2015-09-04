package com.android.hamzastudio.kuran;
import android.support.v4.app.Fragment;

public class KuranActivity extends SingleFragmentActivity {
	@Override
	protected Fragment createFragment() {
		return new SureListFragment();
	}
}
