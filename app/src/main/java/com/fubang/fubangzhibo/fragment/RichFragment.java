package com.fubang.fubangzhibo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fubang.fubangzhibo.AppConstant;
import com.fubang.fubangzhibo.R;
import com.fubang.fubangzhibo.adapters.RichAdapter;
import com.fubang.fubangzhibo.api.RichService;
import com.fubang.fubangzhibo.entities.RichEntity;
import com.fubang.fubangzhibo.entities.RichListEntity;
import com.fubang.fubangzhibo.presenter.impl.RichPresenterImpl;
import com.fubang.fubangzhibo.view.RichView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_rich)
public class RichFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener, Callback<RichListEntity> {
    @ViewById(R.id.rich_listview)
    PullToRefreshListView listView;

    private RichAdapter adapter;
    private List<RichEntity> data = new ArrayList<>();
    private Call<RichListEntity> call;

    @Override
    public void before() {
        Log.d("123",getArguments().getString(AppConstant.HOME_TYPE));
    }

    @Override
    public void initView() {
        adapter = new RichAdapter(data,getContext());
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        listView.setOnRefreshListener(this);
        listView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        return new Converter<ResponseBody, RichListEntity>() {
                            @Override
                            public RichListEntity convert(ResponseBody value) throws IOException {
                                RichListEntity entity = null;
                                try {
                                    entity = new RichListEntity();
                                    List<RichEntity> list = new ArrayList<RichEntity>();
                                    JSONArray array = new JSONArray(value.string());
                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject object = array.getJSONObject(i);
                                        RichEntity richEntity = new RichEntity();
                                        richEntity.setRichName(object.getString("richName"));
                                        richEntity.setAnchorName(object.getString("anchorName"));
                                        richEntity.setNusermoney(object.getString("nusermoney"));
                                        list.add(richEntity);
                                    }
                                    entity.setRichs(list);
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                                return entity;
                            }
                        };
                    }
                }).build();
        RichService service = retrofit.create(RichService.class);
        call = service.getRichEntity(1);
        call.enqueue(this);
     }



    @Override
    public void onResponse(Call<RichListEntity> call, Response<RichListEntity> response) {
        listView.onRefreshComplete();
        call.cancel();
        data.clear();
        List<RichEntity> list = response.body().getRichs();
        Log.d("123",list.size()+"------------rich");
        data.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<RichListEntity> call, Throwable t) {
        Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        Call<RichListEntity> call2 = call.clone();
        call2.enqueue(this);
    }
}
