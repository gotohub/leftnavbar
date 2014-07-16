package yunos.demo;

import yunos.tv.widget.IpPicker;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.app.AlertDialog;

public class NumberPickerFragment extends BaseTVDemoFragment{
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		IpPicker ip = (IpPicker) view.findViewById(R.id.ippicker);
		
		ip.setIp("192.168.0.198");
		
//		Button bt = (Button) view.findViewById(R.id.btn_alert_dialog);
//		bt.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				CharSequence title = "Alert Dialog";
//				CharSequence msg = "检测到您的分辨率已经修改，是否要保存当前修改？";
//				CharSequence ok = "保存";
//				CharSequence cancel = "取消";
//				CharSequence neutral = "Nenutral";
//				new AlertDialog.Builder(getActivity())
////					.setTitle(title)
//					.setMessage(msg)
//					.setPositiveButton(ok, null)
//					.setNegativeButton(cancel, null)
////					.setNeutralButton(neutral, null)
//					.show();
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
		final ProgressBar pb = (ProgressBar) view.findViewById(R.id.progress_horizontal);
		Button bt2 = (Button) view.findViewById(R.id.btn_progressbar);
		bt2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pb.incrementProgressBy(1);
				pb.incrementSecondaryProgressBy(2);
			}
		});
	}
}
