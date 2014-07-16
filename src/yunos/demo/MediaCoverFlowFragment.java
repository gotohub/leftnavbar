package yunos.demo;

import yunos.tv.widget.MediaCoverFlow;
import yunos.tv.widget.Gallery.OnScrollingListener;
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
 * 展示的是MediaCoverFlow的demo，主要用于音乐、播放器等应用
 */
public class MediaCoverFlowFragment extends BaseTVDemoFragment{
	
	protected static final String TAG = MediaCoverFlowFragment.class.getSimpleName();
	private PaintFlagsDrawFilter pfd;
	private TextView textView;
	private MediaCoverFlow coverFlow1;
	private SpinnerAdapter coverImageAdapter;
	private static final int[] DEFAULT_RESOURCE_LIST = { R.drawable.cover0,
			R.drawable.cover1, R.drawable.cover2, R.drawable.cover3,
			R.drawable.cover4, R.drawable.cover5, R.drawable.cover6,
			R.drawable.cover7, R.drawable.cover8, R.drawable.cover9,
			R.drawable.cover10, R.drawable.cover11, R.drawable.cover12,
			R.drawable.cover13, R.drawable.cover14, R.drawable.cover15,
			R.drawable.cover16, R.drawable.cover17, R.drawable.cover18,
			R.drawable.cover19, R.drawable.cover20, R.drawable.cover21,
			R.drawable.cover22, R.drawable.cover23, R.drawable.cover24,
			R.drawable.cover25 };
	private String[] title_array;
	
	private static int MAX_CACHE_COUNT = 30;
	private LruCache<Integer, Drawable> mcache = new LruCache<Integer, Drawable>(MAX_CACHE_COUNT);
	
//	Button btn1, btn2, btn3, btn4;
	private void setupCoverFlow(MediaCoverFlow coverFlow) {
		
		coverImageAdapter = new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageView;
				if(convertView == null){
					imageView = new ImageView(parent.getContext()){
						@Override
						protected void onDraw(Canvas canvas) {
							super.onDraw(canvas);
						}
					};
					imageView.setLayoutParams(new MediaCoverFlow.LayoutParams(200, 200));
					imageView.setScaleType(ScaleType.FIT_XY);
				}else {
					imageView = (ImageView) convertView;
				}
				Drawable d = generateDrawable(position);
				imageView.setImageDrawable(d);
				BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
				//设置抗锯齿
				drawable.setAntiAlias(true);
				
				//开启硬件加速时，需要加入以下code，才能使抗锯齿生效
	            /*
	            Matrix localMatrix = new Matrix();
	            Bitmap bitmap = drawable.getBitmap();

	            int boundWidth  = imageView.getWidth();//getWidth() - getPaddingLeft() - getPaddingRight();
	            int boundHeight = imageView.getHeight();//getHeight() - getPaddingTop() - getPaddingBottom();

	            float sx = 1.0f*boundWidth/bitmap.getWidth();
	            float sy = 1.0f*boundHeight/bitmap.getHeight();    

	            localMatrix.setScale(sx, sy);

	            Paint paint = drawable.getPaint();
	            BitmapShader shader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
	            shader.setLocalMatrix(localMatrix);
	            paint.setShader(shader);
	            */
				return imageView;
			}
			
			@Override
			public long getItemId(int position) {
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				//如果要绘制标题文字，需要在该接口中返回对应的String.
				return title_array[position] + "(" + position + ")";
			}
			
			@Override
			public int getCount() {
				return DEFAULT_RESOURCE_LIST.length;
			}
		};

		coverFlow1.setGravity(Gravity.CENTER_VERTICAL);
		//设置切换时的动画时间
		coverFlow1.setAnimationDuration(500);
		//设置item之间的间隔，水平方向的
		coverFlow1.setSpacing(20);
		//设置标题文字和item之间的间隔
		coverFlow1.setCoverFlowTextSpacing(10);
		//设置选择框的上下左右padding，可以为负值
		coverFlow1.setSelectorPadding(10, 10, 10, 10);
		coverFlow1.setAdapter(coverImageAdapter);
		coverFlow1.setSelection(5);
		//设置分割线，类似书架的效果
		coverFlow1.setDividerDrawable(getResources().getDrawable(R.drawable.tui_shelf_all));
		setupListeners(coverFlow1);
		coverFlow1.setDrawShadowImage(false);
	}
	
	protected Drawable generateDrawable(int position) {
		Drawable d = null;
		d = mcache.get(position);
		if(d == null){
			d = getResources().getDrawable(DEFAULT_RESOURCE_LIST[position]);
			mcache.put(position, d);
		}
		return d;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		textView = (TextView) view.findViewById(R.id.statusText);
		coverFlow1 = (MediaCoverFlow) view.findViewById(R.id.coverflow);
		title_array = getResources().getStringArray(R.array.coverflow_demo_title_array);
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
    private void setupListeners(final MediaCoverFlow mCoverFlow) {
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
