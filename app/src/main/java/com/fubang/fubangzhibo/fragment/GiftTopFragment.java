package com.fubang.fubangzhibo.fragment;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.fubang.fubangzhibo.AppConstant;
import com.fubang.fubangzhibo.R;
import com.fubang.fubangzhibo.adapters.GiftTopAdapter;
import com.fubang.fubangzhibo.api.ApiService;
import com.fubang.fubangzhibo.entities.GiftTopEntity;
import com.fubang.fubangzhibo.entities.GiftTopListEntity;
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
@EFragment(R.layout.fragment_gift_top)
public class GiftTopFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener, Callback<GiftTopListEntity> {


    @ViewById(R.id.gifttop_listview)
    PullToRefreshListView listView;

    private GiftTopAdapter adapter;
    private List<GiftTopEntity> data = new ArrayList<>();
    private Call<GiftTopListEntity> call;

    @Override
    public void before() {
        Log.d("123",getArguments().getString(AppConstant.HOME_TYPE));
    }

    @Override
    public void initView() {
        adapter = new GiftTopAdapter(data,getContext());
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
                        return new Converter<ResponseBody, GiftTopListEntity>() {
                            @Override
                            public GiftTopListEntity convert(ResponseBody value) throws IOException {
                                GiftTopListEntity entity = null;
                                try {
                                    entity = new GiftTopListEntity();
                                    List<GiftTopEntity> list = new ArrayList<GiftTopEntity>();
                                    JSONArray array = new JSONArray(value.string());
                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject object = array.getJSONObject(i);
                                        GiftTopEntity giftTopEntity = new GiftTopEntity();
                                        giftTopEntity.setAnchorName(object.getString("anchorName"));
                                        giftTopEntity.setNcount(object.getString("ncount"));
                                        list.add(giftTopEntity);
                                    }
                                    entity.setGiftTopEntitys(list);
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                                return entity;
                            }
                        };
                    }
                }).build();
        ApiService service = retrofit.create(ApiService.class);
        call = service.getGiftTopEntity(1);
        call.enqueue(this);
    }



    @Override
    public void onResponse(Call<GiftTopListEntity> call, Response<GiftTopListEntity> response) {
        listView.onRefreshComplete();
        call.cancel();
        data.clear();
        List<GiftTopEntity> list = response.body().getGiftTopEntitys();
        Log.d("123",list.size()+"------------rich");
        data.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<GiftTopListEntity> call, Throwable t) {
        Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        Call<GiftTopListEntity> call2 = call.clone();
        call2.enqueue(this);
    }
}
