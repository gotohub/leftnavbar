package yunos.demo;

import yunos.tv.AuiResourceFetcher;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseTVDemoFragment extends Fragment implements TabListener{
	
	protected int mLayoutResId;
	protected int containerViewId;
	
	protected void setLayoutResId(int layoutResId, int containerViewId){
		this.mLayoutResId = layoutResId;
		this.containerViewId = containerViewId;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		inflater = AuiResourceFetcher.getLayoutInflater(getActivity());
		return inflater.inflate(mLayoutResId, container, false);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.add(containerViewId, this);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(this);
	}
}
