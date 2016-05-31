package com.fubang.fubangzhibo.ui;



import android.widget.SearchView;

import com.fubang.fubangzhibo.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_search)
public class SearchActivity extends BaseActivity {
    @ViewById(R.id.search_view)
    SearchView searchView;

    @Override
    public void initView() {
        
    }
}
