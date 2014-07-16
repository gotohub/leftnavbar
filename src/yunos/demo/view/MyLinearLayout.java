package yunos.demo.view;

import yunos.demo.R;
import yunos.tv.widget.MediaCoverFlow;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {

	public MyLinearLayout(Context context) {
		super(context);
	}

	public MyLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		MediaCoverFlow  mMediaCoverFlow = (MediaCoverFlow) findViewById(R.id.grid_item);
		Log.i("aabb", "======onKeyDown====1=========");
		if (mMediaCoverFlow == null) {
			Log.i("aabb", "======onKeyDown====2========");
			return super.onKeyDown(keyCode, event);
		}
//		 switch (keyCode) {
//         case KeyEvent.KEYCODE_DPAD_LEFT:
//         case KeyEvent.KEYCODE_DPAD_RIGHT:
//            return true;
//         default:
//            break;
//         }
		return mMediaCoverFlow.onKeyDown(keyCode, event);
	}
	
	
}
