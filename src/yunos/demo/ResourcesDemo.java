package yunos.demo;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ResourcesDemo extends ListActivity {
    String[][] mDemos = {
    		{"Spirit Font", "yunos.demo.resource.FontDemo"},
    		{"IconDemo", "yunos.demo.resource.IconDemo"},
    };
    
    public static void startActivity(Context context, String activityName)
    {
        try
        {
            Intent intent = new Intent();
            intent.setClassName("yunos.demo", activityName);
            context.startActivity(intent);
        }
        catch(Exception ex)
        {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spirit_list_demo);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spirit_list_item);
		for(int i = 0; i < mDemos.length; i++)
		    adapter.add(mDemos[i][0]);
		setListAdapter(adapter);
		
		this.getListView().setOnItemClickListener(new OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id)
            {
                if(position >= 0 && position < mDemos.length)
                    startActivity(ResourcesDemo.this, mDemos[position][1]);
            }
        });
		
	}
}
