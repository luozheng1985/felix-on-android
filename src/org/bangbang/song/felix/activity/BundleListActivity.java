package org.bangbang.song.felix.activity;

import org.bangbang.song.felix.FelixWrapper;
import org.bangbang.song.felixonandroid.R;
import org.osgi.framework.launch.Framework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BundleListActivity extends Activity {

	private ListView mBundles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bundle_list);
		
		mBundles = ((ListView)findViewById(R.id.listbundle));
		
		Framework f = FelixWrapper.getInstance(this).getFramework();
		org.osgi.framework.Bundle[] bundles = f.getBundleContext().getBundles();
		
		ArrayAdapter<org.osgi.framework.Bundle> adapter = new ArrayAdapter<org.osgi.framework.Bundle>(this, android.R.layout.simple_list_item_1, bundles);
		mBundles.setAdapter(adapter);
		mBundles.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				org.osgi.framework.Bundle b = (org.osgi.framework.Bundle) parent.getAdapter().getItem(position);
				Intent detail = new Intent(BundleListActivity.this, BundleDetailActivity.class);
				detail.putExtra(BundleDetailActivity.EXTRA_BUNDLE_ID, b.getBundleId());
				startActivity(detail);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
