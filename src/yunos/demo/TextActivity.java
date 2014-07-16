package yunos.demo;

import yunos.tv.widget.AbsHorizontalListView;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.AdapterView;

public class TextActivity extends Activity{
	
//	HorizontalListView list;
	AbsHorizontalListView list, list1, list2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.horizotanl_text);
		
//		list = (HorizontalListView)findViewById(R.id.list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_text1, R.id.text1);
		adapter.add("text1");
		adapter.add("text2");
		adapter.add("text3");
		adapter.add("text4");
		adapter.add("text5");
		adapter.add("text6");
		adapter.add("text7");
		adapter.add("text8");
		adapter.add("text9");
		adapter.add("text10");
		adapter.add("text11");
		adapter.add("text12");
		adapter.add("text13");
		adapter.add("text14");
		adapter.add("text15");
		
		list = (AbsHorizontalListView)findViewById(R.id.list);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d("lawin", "-------------position = " + position);
				
			}
			
		});
		
		list1 = (AbsHorizontalListView)findViewById(R.id.list1);
		list1.setAdapter(adapter);
		
		list2 = (AbsHorizontalListView)findViewById(R.id.list2);
		list2.setAdapter(adapter);
		
		Button btn1 = (Button)findViewById(R.id.menu_back);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextActivity.this.finish();
			}
		});
		
//		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//			
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//				 Log.d("lawin", "-------position = " + position);
//			 }
//			
//		});
	}
	
	

}
