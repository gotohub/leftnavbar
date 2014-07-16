package yunos.demo;

import java.util.ArrayList;
import java.util.List;

import yunos.tv.AuiResourceFetcher;
import yunos.tv.widget.AbsSpinner;
import yunos.tv.widget.DataCache;
import yunos.tv.widget.PokerGroupView;
import yunos.tv.widget.PokerItemAdapter;
import yunos.tv.widget.ReflectedBitmapAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.SpinnerAdapter;
//import yunos.tv.widget.AbsSpinner;
//import yunos.tv.widget.PokerGroupView;
//import yunos.tv.widget.PokerItemAdapter;

public class PokerGroupFragment extends BaseTVDemoFragment{
	private static final String TAG = "TEST";
	PokerGroupView mPokerGroupView;
	ArrayList<String> mList;
	
	//PokerFlow imageId list and text list
	final int[] POKER_FLOW_IMAGE_ID_LIST = { R.drawable.pokerflow0, R.drawable.pokerflow1, R.drawable.pokerflow2,
			R.drawable.pokerflow3, R.drawable.pokerflow4, R.drawable.pokerflow5, R.drawable.pokerflow6, R.drawable.pokerflow7,
			R.drawable.pokerflow8, R.drawable.pokerflow9 };
	final String[] POKER_FLOW_TEXT_LIST = {"后天", "三个臭皮匠", "鬼马小精灵", "无耻混蛋", "在一起", "角斗士", "霍比特人", "变形金刚", "无敌破坏", "叶问" };
	
	
	
	int getPokerFlowItemImageId(int position){
		return POKER_FLOW_IMAGE_ID_LIST[position];
	}
	
	int getPokerFlowItemCount(){
		return POKER_FLOW_IMAGE_ID_LIST.length;
	}
	
	String getPokerFlowItemText(int position){
		return POKER_FLOW_TEXT_LIST[position];
	}
	
	
	ReflectedBitmapDataBase mReflectedBitmapDataBase = new ReflectedBitmapDataBase();
	
	final int[] POKER_GROUP_VIEW_IMAGE_ID_LIST = { POKER_FLOW_IMAGE_ID_LIST[0],  POKER_FLOW_IMAGE_ID_LIST[0], R.drawable.pokeritem2,
	        R.drawable.pokeritem3, R.drawable.pokeritem4, R.drawable.pokeritem5, R.drawable.pokeritem6,
	        R.drawable.pokeritem7,R.drawable.pokeritem8};
	
	String[]POKER_GROUP_VIEW_TEXT_LIST = {"最近播放","看吧","热门","电影","电视剧","高清专题","少儿","我的收藏","搜索"};
	

	
	int getPokerGroupViewImageId(int position){		
		return POKER_GROUP_VIEW_IMAGE_ID_LIST[position];
	}
	
	int getPokerGroupViewCount(){
		return POKER_GROUP_VIEW_IMAGE_ID_LIST.length;
	}
	
	String getPokerGroupViewText(int position){
		return POKER_GROUP_VIEW_TEXT_LIST[position];
	}
	
	
	
	
	Button btn1, btn2, btn3, btn4;
	
	private SpinnerAdapter createAdapter() {
			BaseAdapter mAdapter = new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageView;
				if(convertView == null){
					imageView = new ImageView(parent.getContext()){
						@Override
						protected void onDraw(Canvas canvas) {
//							canvas.setDrawFilter(pfd);
							super.onDraw(canvas);
						}
					};
					imageView.setLayoutParams(new AbsSpinner.LayoutParams(267, 400/*200, 300*/));
				}else {
					imageView = (ImageView) convertView;
				}
				
				int imageId = getPokerFlowItemImageId(position);
				Drawable d = getResources().getDrawable(imageId);
				imageView.setScaleType(ScaleType.FIT_XY);
				imageView.setImageDrawable(d);
				BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
				drawable.setAntiAlias(true);
				return imageView;
			}
			
			@Override
			public long getItemId(int position) {
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				return getResources().getDrawable(getPokerFlowItemImageId(position));
			}
			
			@Override
			public int getCount() {
				return getPokerFlowItemCount();
			}
		};
			return mAdapter;
	}
	private static final int MAX_COUNT = 20;
	
	private ArrayList<Integer> mPokerFlowImageIdList = new ArrayList<Integer>(MAX_COUNT);
	
	void removePokerFlowImageIdItem(int position){
		mPokerFlowImageIdList.remove(position);
	}
	
	void addPokerFlowImageIdItem(int imageId){
		mPokerFlowImageIdList.add(imageId);
	}
	
	
	class ReflectedBitmapDataBase extends ReflectedBitmapAdapter{
		
		
		protected final DataCache<Integer, Bitmap> mLruCache = new DataCache<Integer, Bitmap>(MAX_COUNT);
		
		private ArrayList<Integer> mPositionList = new ArrayList<Integer>(MAX_COUNT);
		
		float mImageReflectionHeightRatio = 0.2f;
		
		public void clear(){
			mLruCache.clear();
			mPositionList.clear();
		}
		
		public void removeItem(int position){
			int count = mPositionList.size();
			Integer object;
			int Key;
			Bitmap bitmap;
			
			for(int i = 0; i < count; i++){
				object = mPositionList.get(i);
				if(object != null){
					Key = object.intValue();
					if(Key > position){
						bitmap = mLruCache.objectForKey(Key);
						
						mLruCache.putObjectForKey(Key - 1, bitmap);
					}else if( Key == position){
						mLruCache.putObjectForKey(Key, null);
					}
				}
			}
			
			//减少一个
			mPositionList.remove(count - 1);
		}
		
		//从低到高的排队插入
		private void addPosition(int position){
			Integer object;
			int value;
			int count = mPositionList.size();
			
			int index = 0;
			
			//mPositionList.clear();
			
			for(int i = 0; i < count; i++){
				object = mPositionList.get(i);
				if(object != null){
					value = object.intValue();
					
					if(value > position){
						index = i;
						break;
					}else if(value == position){
						return;
					}
				}
			}
			
			//排队方式插入
			mPositionList.add(index, position);
			
			return;
		}

		private Bitmap createReflectBitmap(final Bitmap originalImage){	
		    
		    int defaultHeight = originalImage.getWidth();
		    int defaultWidth  = originalImage.getHeight();
		    
		    
	        int yShift = 0;
	        
	        int reflectedImageHeight = (int)(defaultHeight * mImageReflectionHeightRatio);        
	        
 	
	        yShift = 0;
	        
	        return createReflectBitmap(yShift, reflectedImageHeight, originalImage);
		}
		
		private Bitmap createReflectBitmap(int yShift, int reflectedImageHeight, final Bitmap originalImage) {
			
			//default data
	        final int width = originalImage.getWidth();        
	        
	        
	        final Matrix matrix = new Matrix();
	        
	        //check 
	        if(width <= 0 || reflectedImageHeight <= 0){
	        	Log.v(TAG, "Create Reflect Bitmap width or height is <= 0" + " width = " + width + " height = " + originalImage.getHeight());
	        	return null;
	        }
	        
	        //create all reflection image
	        final Bitmap bitmapWithReflection = Bitmap.createBitmap(width, reflectedImageHeight,
	                Config.ARGB_8888);        
	        
	        final Canvas canvas = new Canvas(bitmapWithReflection);
	        
	        //get reflecting and shift image matrix
	        matrix.reset();
	        matrix.preScale(1, -1);
	    	matrix.postTranslate(0, originalImage.getHeight() + yShift);
	        //matrix.postTranslate(0, yShift);
			
			//draw reflecting image
	        canvas.drawBitmap( originalImage, matrix, null);
	        
	        //create lineargradient shader paint
	        final Paint paint = new Paint();
	        final LinearGradient shader = new LinearGradient(0, 0, 0,
	                bitmapWithReflection.getHeight(), 0x70FFFFFF, 0x00FFFFFF, TileMode.CLAMP);        
	        paint.setShader(shader);
	        
	        //draw alpha gradient rect
	       // canvas.drawRect(0, 0, width, bitmapWithReflection.getHeight(), paint);      
	        
	        
	        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
	        
	        //draw alpha gradient rect
	        canvas.drawRect(0, 0, width, bitmapWithReflection.getHeight(), paint);        
	        
	        return bitmapWithReflection;
	    }
		
		
		private Bitmap loadBitmap(int id){
			Resources res = getResources();
			Bitmap bmp = BitmapFactory.decodeResource(res, id);
			return bmp;
		}
		
		public Bitmap updateLruCache(int position, int imageId){			
			Bitmap originalBitamp = loadBitmap(imageId);		
			
			return updateLruCache(position, originalBitamp);
		}
		
		private Bitmap updateLruCache(int position, Bitmap originalBitamp){
			addPosition(position);
			
			Bitmap bm = null;
			if(originalBitamp != null){
				bm = createReflectBitmap(originalBitamp);
			}
			
			if(bm != null){
				mLruCache.putObjectForKey(position, bm);	
			}
			return bm;
		}
		
		
		
		private Bitmap findReflectBitmap(int position){			
    		//else get reflecting bitmap from mLruCache
			return mLruCache.objectForKey(position);		 
		}   
		
		
		@Override
		@Deprecated
		public View getView(int position, View convertView, ViewGroup parent) {
			return null;
		}
		
		/**
		 * Get a bitmap that displays the data at the specified position in the data set on horizontal coordinate
		 * 
		 * @param position The position of the item within the adapter's data set of the item whose bitmap we want
		 * @return a bitmap corresponding to the data at the specified position on horizontal cooridinate.
		 */
		public Bitmap getReflectedBitmapHorizontal(int position){			
			return findReflectBitmap(position);
		}
		
		/**
		 * Get a bitmap that displays the data at the specified position in the data set on vertical coordinate
		 * 
		 * @param position   The position of the item within the adapter's data set of the item whose bitmap we want
		 * @param columnIndex  
		 * @return a bitmap corresponding to the data at the specified position on vertical coordinate.
		 */
		public Bitmap getReflectedBitmapVertical(int position, int columnIndex){
			return null;
		}

	}
	
	

	
	class PokerItem extends PokerItemAdapter{
		
		LayoutInflater mInflater;
		Context mContext;
		final int pokerItemRes = R.layout.simple_text1;
		int mImageId;
		String mText;

		
		public PokerItem(Context context, int ImageId, String text) {
			mContext = context;
			mInflater = AuiResourceFetcher.getLayoutInflater(context);
			
			mImageId = ImageId;
			mText    =  text;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if(convertView == null){
				imageView = new ImageView(parent.getContext());
				imageView.setLayoutParams(new AbsSpinner.LayoutParams(267, 400/*200, 300*/));
			}else {
				imageView = (ImageView) convertView;
			}
			//int imageId = getPokerGroupViewImageId(position);
			
			int imageId = mPokerFlowImageIdList.get(position);
			
			Drawable d = getResources().getDrawable(imageId);
//			if(d instanceof BitmapDrawable){
//				((BitmapDrawable) d).setAntiAlias(true);
//			}
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setImageDrawable(d);
			return imageView;
		}
		
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		mPokerGroupView = (PokerGroupView)view.findViewById(R.id.list);
		btn1 = (Button)view.findViewById(R.id.btn1);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				clearData();
			}
		});
		
		btn2 = (Button)view.findViewById(R.id.btn2);
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setupData();
			}
		});
		
		btn3 = (Button)view.findViewById(R.id.btn3);
		btn3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//保存的记录最少为6条
				//删除第2条记录
				mPokerGroupView.removeItem(2);
				
				//删除第二条记录的倒影
				mReflectedBitmapDataBase.removeItem(2);
				
				removePokerFlowImageIdItem(2);
				
				//删除这条记录的文字
				removePokerGroupViewTextItem(2);
				
				mPokerGroupView.notifyDataSetChanged();
			}
		});
		
		btn4 = (Button)view.findViewById(R.id.btn4);
		btn4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				int index   = 2;
				int imageId = getPokerGroupViewImageId(index);
				String text    = getPokerGroupViewText(index);		
				//添加
				mPokerGroupView.addPokerItem(new PokerItem(getActivity(), imageId, text));
				addPokerFlowImageIdItem(imageId);
				
				//添加倒影
				int count = mPokerGroupViewTextlist.size();
				mReflectedBitmapDataBase.updateLruCache(count, imageId);				
				
				//添加文字
				addPokerGroupViewTextItem(text);
				mPokerGroupView.setTextArrayOnPokerGroup(mPokerGroupViewTextlist);
				
				
				mPokerGroupView.notifyDataSetChanged();
			}
		});
		
		setupData();
	}
	
	private void clearData(){
		mPokerGroupView.cleartItems();
		
		//清空倒影
		mReflectedBitmapDataBase.clear();
	}
	
	private void setupData(){
		int imageId;
		String text = "";
		int index;
		
		
		index = 0;
		imageId = getPokerGroupViewImageId(index);
		text    = getPokerGroupViewText(index);	
		mPokerGroupView.addPokerFlow(createAdapter());
		addPokerFlowImageIdItem(imageId);
		addPokerGroupViewTextItem(text);
		mReflectedBitmapDataBase.updateLruCache(index, imageId);

		
		
		index = 1;
		text    = getPokerGroupViewText(index);	
		mPokerGroupView.addPokerFlow(createAdapter());
		addPokerFlowImageIdItem(imageId);
		addPokerGroupViewTextItem(text);	
		mReflectedBitmapDataBase.updateLruCache(index, imageId);
		

		index = 2;
		imageId = getPokerGroupViewImageId(index);
		text    = getPokerGroupViewText(index);		
		mPokerGroupView.addPokerItem(new PokerItem(getActivity(), imageId, text));
		addPokerFlowImageIdItem(imageId);
		addPokerGroupViewTextItem(text);
		mReflectedBitmapDataBase.updateLruCache(index, imageId);
		
		
		index = 3;
		imageId = getPokerGroupViewImageId(index);
		text    = getPokerGroupViewText(index);	
		mPokerGroupView.addPokerItem(new PokerItem(getActivity(), imageId, text));
		addPokerFlowImageIdItem(imageId);
		addPokerGroupViewTextItem(text);
		mReflectedBitmapDataBase.updateLruCache(index, imageId);
		
		index = 4;
		imageId = getPokerGroupViewImageId(index);
		text    = getPokerGroupViewText(index);	
		mPokerGroupView.addPokerItem(new PokerItem(getActivity(), imageId, text));
		addPokerFlowImageIdItem(imageId);
		addPokerGroupViewTextItem(text);
		mReflectedBitmapDataBase.updateLruCache(index, imageId);
		
		index = 5;
		imageId = getPokerGroupViewImageId(index);
		text    = getPokerGroupViewText(index);	
		mPokerGroupView.addPokerItem(new PokerItem(getActivity(), imageId, text));
		addPokerFlowImageIdItem(imageId);
		addPokerGroupViewTextItem(text);
		mReflectedBitmapDataBase.updateLruCache(index, imageId);
		
		index = 6;
		imageId = getPokerGroupViewImageId(index);
		text    = getPokerGroupViewText(index);	
		mPokerGroupView.addPokerItem(new PokerItem(getActivity(), imageId, text));
		addPokerFlowImageIdItem(imageId);
		addPokerGroupViewTextItem(text);
		mReflectedBitmapDataBase.updateLruCache(index, imageId);
		
		index = 7;
		imageId = getPokerGroupViewImageId(index);
		text    = getPokerGroupViewText(index);	
		mPokerGroupView.addPokerItem(new PokerItem(getActivity(), imageId, text));
		addPokerFlowImageIdItem(imageId);
		addPokerGroupViewTextItem(text);
		mReflectedBitmapDataBase.updateLruCache(index, imageId);
		
		index = 8;
		imageId = getPokerGroupViewImageId(index);
		text    = getPokerGroupViewText(index);	
		mPokerGroupView.addPokerItem(new PokerItem(getActivity(), imageId, text));
		addPokerFlowImageIdItem(imageId);
		addPokerGroupViewTextItem(text);
		mReflectedBitmapDataBase.updateLruCache(index, imageId);
		
		
		
//		mPokerGroupView.addPokerItem(new PokerItem(this, "text7"));
		mPokerGroupView.setupPokerGroupAdapter();
		mPokerGroupView.setSelector(getResources().getDrawable(R.drawable.tui_bg_focus));
		mPokerGroupView.setSelectorBorderWidth(10);
		mPokerGroupView.setSelectorBorderHeight(10);
		mPokerGroupView.setDrawSelectorOnTop(true);			
		
		setupPokerGroupListener();		
		
		//show text
		addPokerGroupViewText();
		

		//show reflecting
		addPokerGroupViewReflectingImage();
		
		
		//is show shadow
		mPokerGroupView.setDrawShadowImage(true);		
	}
	
	//
	private void saveData(){
		//save
		//mPokerFlowImageIdList
		
		//txt
		//mPokerGroupViewTextlist
	}
	
	
	ArrayList<String> mPokerGroupViewTextlist = new ArrayList<String>();
	
	private void removePokerGroupViewTextItem(int position){
		/*
		ArrayList<String> listCopy = new ArrayList<String>();
		listCopy.clear();
		listCopy.addAll(mPokerGroupViewTextlist);
		int count = listCopy.size();
		
		for(int i = 0; i < count; i++){
			if(i != position){
				mPokerGroupViewTextlist.add(listCopy.get(i));
			}
		}		
		*/
		
		mPokerGroupViewTextlist.remove(position);
		
		mPokerGroupView.setTextArrayOnPokerGroup(mPokerGroupViewTextlist);
	}
	
	private void addPokerGroupViewTextItem(String str){
		/*
		ArrayList<String> listCopy = new ArrayList<String>();
		listCopy.clear();
		listCopy.addAll(mPokerGroupViewTextlist);
		int count = listCopy.size();
		
		for(int i = 0; i < count; i++){
			if(i == position){
				mPokerGroupViewTextlist.add(str);
			}
			mPokerGroupViewTextlist.add(listCopy.get(i));			
		}		
		*/
		mPokerGroupViewTextlist.add(str);
		//mPokerGroupView.setTextArrayOnPokerGroup(mPokerGroupViewTextlist);
	}
	
	private void addPokerGroupViewText(){
		//添加显示的文字		
		//添加水平方向的文字
		mPokerGroupView.setTextArrayOnPokerGroup(mPokerGroupViewTextlist);
		
		
		ArrayList<String> list = new  ArrayList<String>();
		

		for(int i = 0; i < getPokerFlowItemCount(); i++){
			list.add(getPokerFlowItemText(i));
		}

		//添加竖直方向的文字
		mPokerGroupView.setTextArrayOnPokerFlow(0, list);
		
		
		list = new  ArrayList<String>();
		
		for(int i = 0; i < getPokerFlowItemCount(); i++){
			list.add(getPokerFlowItemText(i));
		}
		//添加竖直方向的文字
		mPokerGroupView.setTextArrayOnPokerFlow(1, list);
	}
	
	private void addPokerGroupViewReflectingImage(){
		mPokerGroupView.setDrawReflectedImage(true);
		
		//do not use inner database
		//set ReflectedBitmapAdapter
		int imageId = 0;
		
		//设置默认的倒影图片
		/*
		for(int i = 0; i < getPokerGroupViewCount(); i++ ){
			imageId = getPokerGroupViewImageId(i);
			
			mReflectedBitmapDataBase.updateLruCache(i, imageId);
		}
		*/
		
		mPokerGroupView.setReflectedBitmapAdapter(mReflectedBitmapDataBase);
		
		//pokerflow上下切换的时候，需要更新此pokerflow的倒影
		//这是插入的第0个pokerflow的倒影
		//上下切换选中pokerflow的一个页面的时候，就更新倒影
		//add listener
		mPokerGroupView.setOnPokerFlowItemSelectedListener(0, new PokerGroupView.OnPokerFlowItemSelectedListener() {

			 @Override
			 public void onNothingSelected(AdapterView<?> arg0, int arg1) {

			 }

			 @Override
			 public void onItemFlowSelected(AdapterView<?> arg0, View arg1, int arg2, int postion, long arg4, long arg5) {
				 //更新reflecting image
				 int imageId = getPokerFlowItemImageId(postion);				 
				 mReflectedBitmapDataBase.updateLruCache(0, imageId);
				 
				 Log.d(TAG, "on Poker Flow 0 selected position = " + postion);
			 }			 
		 });
		
		
		//pokerflow上下切换的时候，需要更新此pokerflow的倒影
		//这是插入的第0个pokerflow的倒影
		//上下切换选中pokerflow的一个页面的时候，就更新倒影
		mPokerGroupView.setOnPokerFlowItemSelectedListener(1, new PokerGroupView.OnPokerFlowItemSelectedListener() {
			 @Override
			 public void onNothingSelected(AdapterView<?> arg0, int arg1) {

			 }

			 @Override
			 public void onItemFlowSelected(AdapterView<?> arg0, View arg1, int arg2, int postion, long arg4, long arg5) {
				 int imageId = getPokerFlowItemImageId(postion);			 
				 mReflectedBitmapDataBase.updateLruCache(1, imageId);
				 
				 Log.d(TAG, "on Poker Flow 1 selected position = " + postion);
			 }			 
		 });
	}

	private void setupPokerGroupListener() {
		mPokerGroupView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d(TAG, "on group item click position = " + position);
			}
			
		});
		mPokerGroupView.setOnPokerFlowItemClickListener(0, new PokerGroupView.OnPokerFlowItemClickListener(){

			@Override
			public void onPokerFlowItemClick(AdapterView<?> parent, View view,
					int positionOfPokerGroup, int positionOfAdapter,
					long idOfPokerGroup, long idOfPositionOfAdapter) {
				Log.d(TAG, "on first PokerFlow item click position = " + ", positionOfPokerGroup = " + positionOfPokerGroup
						+", positionOfAdapter = " + positionOfAdapter + ", idOfPokerGroup = "+ idOfPokerGroup
						+", idOfPositionOfAdapter = " + idOfPositionOfAdapter);
			}
			
		});
		
		mPokerGroupView.setOnPokerFlowItemClickListener(1, new PokerGroupView.OnPokerFlowItemClickListener(){

			@Override
			public void onPokerFlowItemClick(AdapterView<?> parent, View view,
					int positionOfPokerGroup, int positionOfAdapter,
					long idOfPokerGroup, long idOfPositionOfAdapter) {
				Log.d(TAG, "on second PokerFlow item click position = " + ", positionOfPokerGroup = " + positionOfPokerGroup
						+", positionOfAdapter = " + positionOfAdapter + ", idOfPokerGroup = "+ idOfPokerGroup
						+", idOfPositionOfAdapter = " + idOfPositionOfAdapter);
			}
			
		});
		
	
	}
}
