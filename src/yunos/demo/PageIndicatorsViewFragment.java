package yunos.demo;

import yunos.tv.widget.NumberIndicator;
import yunos.tv.widget.PageIndicatorsView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PageIndicatorsViewFragment extends BaseTVDemoFragment {

	Button btn1, btn2, btn3;
	PageIndicatorsView view1, view2, view3;
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		btn1 = (Button) view.findViewById(R.id.normalbutton);
		btn2 = (Button) view.findViewById(R.id.normalbuttondisable);
		btn3 = (Button) view.findViewById(R.id.warningbutton);
		view1 = (PageIndicatorsView) view.findViewById(R.id.pageindicator1);
		view2 = (PageIndicatorsView) view.findViewById(R.id.pageindicator2);
		view3 = (PageIndicatorsView) view.findViewById(R.id.pageindicator3);
		
		final NumberIndicator nIndicator = (NumberIndicator) view.findViewById(R.id.numberIndicator);
		nIndicator.setNumber(88);
		Button showBtn = (Button) view.findViewById(R.id.show_number);
		Button hideBtn = (Button) view.findViewById(R.id.hide_number);
		
		showBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(nIndicator!=null)
					nIndicator.show();
			}
			
		});
		
		hideBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(nIndicator!=null)
					nIndicator.hide();
			}
			
		});
		view1.setItemCount(10);
		view2.setItemCount(8);
		view2.setMultiSelectable(true);
		view3.setItemCount(8);
		view3.setMultiSelectable(true);

		btn1.setOnClickListener(new OnClickListener() {
			int i = 0;

			public void onClick(View v) {
				view1.setSelectIndex(i);
				i = (i + 1) % 10;
			}
		});

		btn2.setOnClickListener(new OnClickListener() {
			int i = 0;

			public void onClick(View v) {
				for (int j = 0; j < 8; j++) {
					if (view2.isSelected(j)) {
						view2.setSelected(j, false);

					}
				}
				for (int j = 0; j < i + 1; j++) {
					view2.setSelected(j, true);
				}
				i = (i + 1) % 8;
			}
		});

		btn3.setOnClickListener(new OnClickListener() {
			int i = 0;

			public void onClick(View v) {
				for (int j = 0; j < 8; j++) {
					if (view3.isSelected(j)) {
						view3.setSelected(j, false);

					}
				}
				for (int j = 0; j < i + 1; j++) {
					view3.setSelected(j, true);
				}
				i = (i + 1) % 8;
			}
		});

	}

}
