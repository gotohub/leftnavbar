package yunos.demo;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class PrefsFragement extends PreferenceFragment implements TabListener {
	int mLayoutResId;
	int containerViewId;

	public void setLayoutResId(int layoutResId, int containerViewId) {
		mLayoutResId = layoutResId;
		this.containerViewId = containerViewId;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(mLayoutResId);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(containerViewId, this);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(this);
	}
}
