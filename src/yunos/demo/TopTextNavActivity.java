package yunos.demo;

import yunos.tv.AuiResourceFetcher;
import yunos.tv.app.LeftNavBar;
import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.view.View;

public class TopTextNavActivity extends Activity{
	
    private LeftNavBar mLeftNavBar;
    boolean showTitle = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main11);	
		setupTabs();
	}
	
	//需要在setContentView之后初始化LeftNavBar，LeftNavBar.TYPE_TOP_NAVBAR type表示打开顶部导航栏
    private LeftNavBar getLeftNavBar() {
        if (mLeftNavBar == null) {
            mLeftNavBar = new LeftNavBar(this, LeftNavBar.TYPE_TOP_NAVBAR);
//            mLeftNavBar.setOnClickHomeListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                	
//                }
//            });
            mLeftNavBar.showNumberIndicator(2, 2);
            mLeftNavBar.showNumberIndicator(3, 3);
            mLeftNavBar.setOnClickOptionListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					finish();
				}
			});
        }
        return mLeftNavBar;
    }
    
    
	@Override
	public Theme getTheme() {
		//为了让activity显示yunos风格的theme，请增加这些代码
		return !AuiResourceFetcher.isYunos() ? AuiResourceFetcher.getTheme(this) : super.getTheme();
	}
	
	@Override
	public Resources getResources() {
		//为了让activity获取yunos 的resource(包括图片,文字等资源)，请增加该代码
		return !AuiResourceFetcher.isYunos() ? AuiResourceFetcher.getResources(getBaseContext()) : super.getResources();
	}
    
	
    private void setupTabs() {
    	ActionBar bar = getLeftNavBar();
    	bar.removeAllTabs();
    	if(!showTitle){
	        ActionBar.Tab tab8 = bar.newTab().setText(R.string.tab_h);
	        PokerGroupFragment pgf = new PokerGroupFragment();
	        pgf.setLayoutResId(R.layout.poker_flow_layout, R.id.main1);
	        tab8.setTabListener(pgf);
	        bar.addTab(tab8, false);
	        
	        ActionBar.Tab tab = bar.newTab().setText(R.string.tab_a);
	        TextFragment tf1 = new TextFragment();
	        tf1.setLayoutResId(R.layout.simple_text1, R.id.main1);
	        tab.setTabListener(tf1);
	        bar.addTab(tab, true);
	        
	        ActionBar.Tab tab5 = bar.newTab().setText(R.string.tab_e);
	        CoverFlowFragment tf5 = new CoverFlowFragment();
	        tf5.setLayoutResId(R.layout.cover_flow_main, R.id.main1);
	        tab5.setTabListener(tf5);
	        bar.addTab(tab5, false);
	        
	        ActionBar.Tab tab6 = bar.newTab().setText(R.string.tab_j);
	        MediaCoverFlowFragment tf6 = new MediaCoverFlowFragment();
	        tf6.setLayoutResId(R.layout.media_cover_flow_main, R.id.main1);
	        tab6.setTabListener(tf6);
	        bar.addTab(tab6, false);
	        
	        ActionBar.Tab tab9 = bar.newTab().setText(R.string.tab_l);
	        PrefsFragement prefs = new PrefsFragement();
	        prefs.setLayoutResId(R.xml.preferences, R.id.main1);
	        tab9.setTabListener(prefs);
	        bar.addTab(tab9, false);
	        
    		((LeftNavBar) bar).setLogo(R.drawable.tui_ic_huashulogo);
    	} else {
    		bar.setTitle(R.string.top_text_nav_activity);
    		((LeftNavBar) bar).setLogo(R.drawable.tui_ic_huashulogo);
    	}
    }

}
