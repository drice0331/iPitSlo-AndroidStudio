package com.thepit.ipitslo.ui;

import com.thepit.ipitslo.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends BaseActivity {
	
	private static final String PREFIX = "com.thepit.ipitslo.ui.";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}
	
	public void onActivityClick(View view) {
		try {
			String name = PREFIX;
			name = name.concat((String)view.getTag());
			Class<?> clazz = Class.forName(name);
			Intent intent = new Intent(this, clazz);
			startActivity(intent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
