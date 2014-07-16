package yunos.demo;

import yunos.tv.widget.MediaController;
import yunos.tv.widget.VideoView;
import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class PlayerActivity extends Activity {
	
	VideoView videoView;
	String url;
	int programId = 0;
	int seekMsec = 0;

	private ProgressDialog dialog;
	MediaController controller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		
		dialog = new ProgressDialog(this);
		dialog.setTitle("Loading");
		dialog.setMessage("Waiting");
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		videoView = (VideoView)findViewById(R.id.videoview); 
		controller = new MediaController(this);
		controller.setTitle("test");
		TextView playMode = new TextView(this);
		playMode.setText("test");
		playMode.setTextSize(12);
//		controller.setPlayMode(playMode);
		videoView.setMediaController(controller);
		url = getIntent().getStringExtra("cdn");
		seekMsec = getIntent().getIntExtra("playtime", 0);
		programId = getIntent().getIntExtra("programId", 0);
		play(url);
		
		controller.setPrevNextListeners(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(PlayerActivity.this, "点击了下一集", Toast.LENGTH_SHORT).show();
			}
		}, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(PlayerActivity.this, "点击了上一集", Toast.LENGTH_SHORT).show();
			}
		});
		
		controller.setBackListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(PlayerActivity.this, "点击了返回", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private void play(String url){
		url = "http://hot.vrs.sohu.com/ipad990616_4574254445102_211748.m3u8";
		if(url == null || url.equals("")){
			Toast.makeText(PlayerActivity.this, "播放失败，播放地址为空", Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		dialog.show();
		videoView.setVideoURI(Uri.parse(url));
		videoView.setOnPreparedListener(videoPreparedListener);
		videoView.setOnErrorListener(videoErrorListener);
		videoView.setOnCompletionListener(videoOncompletionListener);
		videoView.start();
		if(seekMsec != 0){
			Toast.makeText(this, "正在继续上一次播放时间:" + seekMsec, Toast.LENGTH_SHORT).show();
			videoView.seekTo(seekMsec);
		}
	}
	
	MediaPlayer.OnCompletionListener videoOncompletionListener = new MediaPlayer.OnCompletionListener(){

		@Override
		public void onCompletion(MediaPlayer mp) {
			finish();
		}
	};
	
	MediaPlayer.OnPreparedListener videoPreparedListener = new MediaPlayer.OnPreparedListener(){

		@Override
		public void onPrepared(MediaPlayer mp) {
			dialog.dismiss();
		}
	};
	
	OnErrorListener videoErrorListener = new OnErrorListener(){

		@Override
		public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
			dialog.dismiss();
			Toast.makeText(PlayerActivity.this, "播放失败", Toast.LENGTH_SHORT).show();
			return true;
		}
	};
	
	@Override
	public void onPause(){
		int playTime = videoView.getCurrentPosition();
		videoView.stopPlayback();
		super.onPause();
	}
	
}
