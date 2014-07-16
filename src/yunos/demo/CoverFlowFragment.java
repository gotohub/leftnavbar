
package yunos.demo;

import yunos.tv.widget.CoverFlow;
import yunos.tv.widget.Gallery.OnScrollingListener;
import yunos.tv.widget.ReflectingImageAdapter;
import yunos.tv.widget.ResourceImageAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

/**
 * 展示CoverFlow的demo，带阴影的效果，用于壁纸、应用推荐栏等场景
 * ResourceImageAdapter没有做缓存，数据较多时可能会导致OOM，建议使用自定义的Adapter，管理好图片缓存
 */
public class CoverFlowFragment extends BaseTVDemoFragment{
	public static final String TAG = "CoverFlowFragment";
	private TextView textView;
	private CoverFlow coverFlow1;
	private SpinnerAdapter coverImageAdapter;
	private static final int[] DEFAULT_RESOURCE_LIST = { R.drawable.cover0,
			R.drawable.cover1, R.drawable.cover2, R.drawable.cover3,
			R.drawable.cover4, R.drawable.cover5, R.drawable.cover6,
			R.drawable.cover7, R.drawable.cover8, R.drawable.cover9,
			R.drawable.cover10/*, R.drawable.cover11, R.drawable.cover12,
			R.drawable.cover13, R.drawable.cover14, R.drawable.cover15,
			R.drawable.cover16, R.drawable.cover17, R.drawable.cover18,
			R.drawable.cover19, R.drawable.cover20, R.drawable.cover21,
			R.drawable.cover22, R.drawable.cover23, R.drawable.cover24,
			R.drawable.cover25*/ };
	
	private void setupCoverFlow(CoverFlow coverFlow) {
		
		coverImageAdapter = new ResourceImageAdapter(getActivity());
		((ResourceImageAdapter)coverImageAdapter).setResources(DEFAULT_RESOURCE_LIST);
		
		coverFlow1.setGravity(Gravity.CENTER_VERTICAL);
		//设置切换时的动画时间
		coverFlow1.setAnimationDuration(500);
		//设置item之间的间隔，水平方向的
		coverFlow1.setSpacing(-100);
		//设置item带阴影效果
		coverFlow1.setWithReflection(true);
		//设置选择框的上下左右padding，可以为负值
		coverFlow1.setSelectorPadding(10, 10, 10, -50);
		//设置自动滚动间隔时间，默认是5秒钟滚动一次
		coverFlow1.setAutoScrollDelay(2000);
		
		coverFlow1.setAdapter(coverImageAdapter);
		coverFlow1.setSelection(5);
		
		setupListeners(coverFlow1);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		textView = (TextView) view.findViewById(R.id.statusText);
		coverFlow1 = (CoverFlow) view.findViewById(R.id.coverflow);
//		title_array = getResources().getStringArray(R.array.coverflow_demo_title_array);
        setupCoverFlow(coverFlow1);
        Button btn1 = (Button)view.findViewById(R.id.btn1);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int rd = (int) ((DEFAULT_RESOURCE_LIST.length-1) * Math.random());
				coverFlow1.scrollToChild(rd);
			}
		});
		
		Button btn2 = (Button)view.findViewById(R.id.btn2);
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				coverFlow1.setAnimationDuration(5000);
				coverFlow1.scrollToChild(0);
			}
		});
		
		Button btn3 = (Button)view.findViewById(R.id.btn3);
		btn3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				coverFlow1.stopAutoScroll();
				coverFlow1.scrollToChild(DEFAULT_RESOURCE_LIST.length - 1);
			}
		});
		
		Button btn5 = (Button)view.findViewById(R.id.btn5);
		btn5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				coverFlow1.startAutoScroll();
			}
		});
		
		Button btn6 = (Button)view.findViewById(R.id.btn6);
		btn6.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				coverFlow1.stopAutoScroll();
			}
		});
	}
	
	/**
     * Sets the up listeners.
     * 
     * @param mCoverFlow
     *            the new up listeners
     */
    private void setupListeners(final CoverFlow mCoverFlow) {
        mCoverFlow.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView< ? > parent, final View view, final int position, final long id) {
                textView.setText("Item clicked! : " + id);
            }

        });
        mCoverFlow.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView< ? > parent, final View view, final int position, final long id) {
                textView.setText("Item selected! : " + id);
            }

            @Override
            public void onNothingSelected(final AdapterView< ? > parent) {
                textView.setText("Nothing clicked!");
            }
        });
        mCoverFlow.setOnScrollingListener(new OnScrollingListener() {
			
			@Override
			public boolean OnScroll(boolean scrolling) {
				Log.d(TAG, "-----------------------------scrolling = " + scrolling);
				return false;
			}
		});
    }
}
