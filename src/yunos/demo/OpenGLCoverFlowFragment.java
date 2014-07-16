package yunos.demo;

import yunos.tv.widget.CoverFlowOpenGL;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class OpenGLCoverFlowFragment extends BaseTVDemoFragment {
    private static int[] SAMPLE_IMAGES = new int[] { R.drawable.cover0,
		R.drawable.cover1, R.drawable.cover2, R.drawable.cover3,
		R.drawable.cover4, R.drawable.cover5, R.drawable.cover6,
		R.drawable.cover7, R.drawable.cover8, R.drawable.cover9,
		R.drawable.cover10, R.drawable.cover11, R.drawable.cover12,
		R.drawable.cover13, R.drawable.cover14, R.drawable.cover15,
		R.drawable.cover16, R.drawable.cover17, R.drawable.cover18,
		R.drawable.cover19, R.drawable.cover20, R.drawable.cover21,
		R.drawable.cover22, R.drawable.cover23, R.drawable.cover24,
		R.drawable.cover25 };

    private CoverFlowOpenGL mCoverFlow;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(getActivity(), (String) msg.obj, 1000);
        }
    };
    
    public void onViewCreated(View view, Bundle savedInstanceState) {
//    	mCoverFlow = (CoverFlowOpenGL) view.findViewById(R.id.gl_coverflow);
    	mCoverFlow = (CoverFlowOpenGL)((ViewGroup)view).getChildAt(0);
    	setupOpenGLCoverFLow();
    }

    public void setupOpenGLCoverFLow() {
//        mCoverFlow = new CoverFlowOpenGL(getActivity());
        mCoverFlow.setCoverFlowListener(new CoverFlowOpenGL.CoverFlowListener() {
            @Override
            public int getCount(CoverFlowOpenGL view) {
                return SAMPLE_IMAGES.length;
            }

            @Override
            public Bitmap getImage(CoverFlowOpenGL anotherCoverFlow, int position) {
                return BitmapFactory.decodeResource(getResources(), SAMPLE_IMAGES[position]);
            }

            @Override
            public void tileOnTop(CoverFlowOpenGL view, int position) {
                // you can control what will happen when one image is in middle
                mHandler.obtainMessage(0, String.format("Image %d is on top.", position)).sendToTarget();
            }

            @Override
            public void topTileClicked(CoverFlowOpenGL view, int position) {
                // you can control what will happen when the image in middle is clicked
                mHandler.obtainMessage(0, String.format("Image %d is clicked", position)).sendToTarget();
            }
        });
        mCoverFlow.preLoadCache(0, SAMPLE_IMAGES.length - 1);

        //mCoverFlow.setSelection(0);
//        mCoverFlow.setBackgroundTexture(R.drawable.tui_window_master_bg);

//        setContentView(mCoverFlow);
    }
}
