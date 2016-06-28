package com.fubang.fubangzhibo.fragment;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.fubang.fubangzhibo.AppConstant;
import com.fubang.fubangzhibo.R;
import com.fubang.fubangzhibo.adapters.AnchorAdapter;
import com.fubang.fubangzhibo.api.ApiService;
import com.fubang.fubangzhibo.entities.AnchorEntity;
import com.fubang.fubangzhibo.entities.AnchorListEntity;
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
@EFragment(R.layout.fragment_anchor)
public class AnchorFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener, Callback<AnchorListEntity>{

    @ViewById(R.id.anchor_listview)
    PullToRefreshListView listView;

    private AnchorAdapter adapter;
    private List<AnchorEntity> data = new ArrayList<>();
    private Call<AnchorListEntity> call;

    @Override
    public void before() {
        Log.d("123",getArguments().getString(AppConstant.HOME_TYPE));
    }

    @Override
    public void initView() {
        adapter = new AnchorAdapter(data,getContext());
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
                        return new Converter<ResponseBody, AnchorListEntity>() {
                            @Override
                            public AnchorListEntity convert(ResponseBody value) throws IOException {
                                AnchorListEntity entity = null;
                                try {
                                    entity = new AnchorListEntity();
                                    List<AnchorEntity> list = new ArrayList<AnchorEntity>();
                                    JSONArray array = new JSONArray(value.string());
                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject object = array.getJSONObject(i);
                                        AnchorEntity anchorEntity = new AnchorEntity();
                                        anchorEntity.setAnchorName(object.getString("anchorName"));
                                        list.add(anchorEntity);
                                    }
                                    entity.setAnchorEntities(list);
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                                return entity;
                            }
                        };
                    }
                }).build();
        ApiService service = retrofit.create(ApiService.class);
        call = service.getAnchorEntity(1);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<AnchorListEntity> call, Response<AnchorListEntity> response) {
        listView.onRefreshComplete();
        call.cancel();
        data.clear();
        List<AnchorEntity> list = response.body().getAnchorEntities();
        Log.d("123",list.size()+"------------rich");
        data.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<AnchorListEntity> call, Throwable t) {
        Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        Call<AnchorListEntity> call2 = call.clone();
        call2.enqueue(this);
    }
}
