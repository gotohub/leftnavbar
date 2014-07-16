package yunos.demo;

import yunos.tv.AuiResourceFetcher;
import yunos.tv.app.LeftNavBar;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TextNavActivity extends Activity{
	
    private LeftNavBar mLeftNavBar;
    
    private final static String TAG = "TextNavActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main11);
		setupTabs();
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
	/**
	 * //需要在setContentView之后初始化LeftNavBar
	 * LeftNavBar.TYPE_LEFT_NAVBAR 打开左边导航栏界面
	 * LeftNavBar.TYPE_TOP_NAVBAR  打开顶部导航栏界面
	 * **/
    private LeftNavBar getLeftNavBar() {
    	
        if (mLeftNavBar == null) {
            mLeftNavBar = new LeftNavBar(this, LeftNavBar.TYPE_LEFT_NAVBAR);
            mLeftNavBar.showNumberIndicator(3);
            mLeftNavBar.setOnClickHomeListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                	Intent intent = new Intent(TextNavActivity.this, MainActivity.class);
//                	TextNavActivity.this.startActivity(intent);
                	
                	Toast.makeText(getBaseContext(), "@@@@@@@@@@@@@@@", Toast.LENGTH_SHORT).show();
                }
            });
            
            mLeftNavBar.setOnClickOptionListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
                	Intent intent = new Intent(TextNavActivity.this, TopTextNavActivity.class);
                	TextNavActivity.this.startActivity(intent);
				}
				
			});
            
            mLeftNavBar.setOnClickSettingsLisetener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	startActivity(new Intent(TextNavActivity.this,SettingActivity.class));
                }
            });
            /***
             * 开启监听TabIndex模式，会自动屏蔽ActionBar.Tab点击
             * 
             **
             */
//            mLeftNavBar.setOnClickTabIndexListener(new LeftNavBar.OnClickTabIndexListener() {
//				
//				@Override
//				public void onClickTabIndex(Tab tab, int index) {
//					switch(index){
//					case 0:
//						Intent intent = new Intent(TextNavActivity.this, TextActivity.class);
//						TextNavActivity.this.startActivity(intent);
//						break;
//					case 1:
//						Intent intent1 = new Intent(TextNavActivity.this, PokerGroupActivity.class);
//						TextNavActivity.this.startActivity(intent1);
//						break;
//					case 2:
//						Intent intent2 = new Intent(TextNavActivity.this, ResourcesDemo.class);
//						TextNavActivity.this.startActivity(intent2);
//						break;
//					}
//				}
//			});
        }
        return mLeftNavBar;
    }
	
    private void setupTabs() {
        ActionBar bar = getLeftNavBar();
        bar.removeAllTabs();
        
        ActionBar.Tab tab = bar.newTab().setText(R.string.tab_a).setIcon(R.drawable.tab_a);
        TextFragment tf1 = new TextFragment();
        tf1.setLayoutResId(R.layout.simple_text1, R.id.main1);
        tab.setTabListener(tf1);
        bar.addTab(tab, true);
        
        ActionBar.Tab tab9 = bar.newTab().setText(R.string.tab_l).setIcon(R.drawable.tab_d);
        PrefsFragement prefs = new PrefsFragement();
        prefs.setLayoutResId(R.xml.preferences, R.id.main1);
        tab9.setTabListener(prefs);
        bar.addTab(tab9, false);
        
        
        ActionBar.Tab tab8 = bar.newTab().setText(R.string.tab_h).setIcon(R.drawable.tab_d);
        PokerGroupFragment pgf = new PokerGroupFragment();
        pgf.setLayoutResId(R.layout.poker_flow_layout, R.id.main1);
        tab8.setTabListener(pgf);
        bar.addTab(tab8, false);
        
        ActionBar.Tab tab5 = bar.newTab().setText(R.string.tab_e).setIcon(R.drawable.tab_d);
        CoverFlowFragment tf5 = new CoverFlowFragment();
        tf5.setLayoutResId(R.layout.cover_flow_main, R.id.main1);
        tab5.setTabListener(tf5);
        bar.addTab(tab5, false);
        
        ActionBar.Tab tab11 = bar.newTab().setText(R.string.tab_j).setIcon(R.drawable.tab_d);
        MediaCoverFlowFragment tf8 = new MediaCoverFlowFragment();
        tf8.setLayoutResId(R.layout.media_cover_flow_main, R.id.main1);
        tab11.setTabListener(tf8);
        bar.addTab(tab11, false);
        
/*        ActionBar.Tab tab10 = bar.newTab().setText(R.string.tab_i).setIcon(R.drawable.tab_d);
        OpenGLCoverFlowFragment tf10 = new OpenGLCoverFlowFragment();
        tf10.setLayoutResId(R.layout.opengl_cover_flow_main, R.id.main1);
        tab10.setTabListener(tf10);
        bar.addTab(tab10, false);*/
        
        
        ActionBar.Tab tab2 = bar.newTab().setText(R.string.tab_b).setIcon(R.drawable.tab_b);
        TextFragment tf2 = new TextFragment();
        tf2.setLayoutResId(R.layout.simple_text2, R.id.main1);
        tab2.setTabListener(tf2);
        bar.addTab(tab2, false);
		
		
        ActionBar.Tab tab3 = bar.newTab().setText(R.string.tab_c).setIcon(R.drawable.tab_c);
        TextFragment tf3 = new TextFragment();
        tf3.setLayoutResId(R.layout.simple_text3, R.id.main1);
        tab3.setTabListener(tf3);
        bar.addTab(tab3, false);

        ActionBar.Tab tab4 = bar.newTab().setText(R.string.tab_d).setIcon(R.drawable.tab_d);
        TextFragment tf4 = new TextFragment();
        tf4.setLayoutResId(R.layout.simple_text4, R.id.main1);
        tab4.setTabListener(tf4);
        bar.addTab(tab4, false);
        
        ActionBar.Tab tab6 = bar.newTab().setText(R.string.tab_f).setIcon(R.drawable.tab_d);
        NumberPickerFragment tf6 = new NumberPickerFragment();
        tf6.setLayoutResId(R.layout.number_picker_demo, R.id.main1);
        tab6.setTabListener(tf6);
        bar.addTab(tab6, false);
        
        ActionBar.Tab tab7 = bar.newTab().setText(R.string.tab_g).setIcon(R.drawable.tab_d);
        PageIndicatorsViewFragment pf = new PageIndicatorsViewFragment();
        pf.setLayoutResId(R.layout.pageindicator_main, R.id.main1);
        tab7.setTabListener(pf);
        bar.addTab(tab7, false);
        
    }
}
