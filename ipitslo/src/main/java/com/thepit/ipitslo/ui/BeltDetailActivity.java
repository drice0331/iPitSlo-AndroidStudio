package com.thepit.ipitslo.ui;

import android.os.Bundle;

import com.thepit.ipitslo.R;
import com.thepit.ipitslo.model.BeltEntry;

import roboguice.util.Ln;

/**
 * Created by DrIce on 4/21/15.
 */
public class BeltDetailActivity extends BaseActivity {

    protected BeltEntry beltEntry;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belt_detail);

        beltEntry = (BeltEntry)getIntent().getSerializableExtra("Entry");
        Ln.d("name - " + beltEntry.getName() + " color - " + beltEntry.getColor());

    }
}