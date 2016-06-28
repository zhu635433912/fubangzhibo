package com.fubang.fubangzhibo.ui;



import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.fubang.fubangzhibo.R;
import com.fubang.fubangzhibo.entities.RoomListEntity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索页面
 */
@EActivity(R.layout.activity_search)
public class SearchActivity extends BaseActivity implements SearchView.OnQueryTextListener {
    @ViewById(R.id.search_view)
    SearchView searchView;
    @ViewById(R.id.search_to_search)
    TextView searchText;
    @ViewById(R.id.search_list)
    ListView searchList;

    private List<String> data = new ArrayList<>();
    private ArrayAdapter<String> adapter ;
    @Override
    public void before() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        searchList.setTextFilterEnabled(true);
        searchList.setAdapter(adapter);
        searchView.setOnQueryTextListener(this);
        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchView.setQuery(data.get(position),true);
            }
        });

        searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TestActivity_.intent(SearchActivity.this).extra("roomId",searchView.getQuery()).get());
            }
        });
    }
    @Subscriber(tag = "roomList")
    public void getRoomList(List<RoomListEntity> list){
        for (int i = 0; i < list.size(); i++) {
            data.add(list.get(i).getRoomid());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)){
            searchList.clearTextFilter();
        }else {
            searchList.setFilterText(newText);
        }
        return true;
    }
}
