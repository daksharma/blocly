package io.bloc.android.blocly.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import io.bloc.android.blocly.R;

/**
 * Created by Daksh on 1/4/16.
 */
public class BlocActivity extends Activity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocly);
    }
}
