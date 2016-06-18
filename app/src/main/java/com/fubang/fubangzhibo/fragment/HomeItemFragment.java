package com.fubang.fubangzhibo.fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fubang.fubangzhibo.AppConstant;
import com.fubang.fubangzhibo.R;
import com.fubang.fubangzhibo.adapters.HomeRoomAdapter;
import com.fubang.fubangzhibo.dao.RoomListEntityDao;
import com.fubang.fubangzhibo.entities.RoomEntity;
import com.fubang.fubangzhibo.entities.RoomListEntity;
import com.fubang.fubangzhibo.entities.test.BannerEntity;
import com.fubang.fubangzhibo.entities.test.BannerService;
import com.fubang.fubangzhibo.entities.test.Banners;
import com.fubang.fubangzhibo.entities.test.UrlConstants;
import com.fubang.fubangzhibo.presenter.RoomListPresenter;
import com.fubang.fubangzhibo.presenter.impl.RoomListPresenterImpl;
import com.fubang.fubangzhibo.utils.DbUtil;
import com.fubang.fubangzhibo.view.RoomListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PulltoRefreshHeadGridView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_home_item)
public class HomeItemFragment extends BaseFragment implements RoomListView,PullToRefreshBase.OnRefreshListener2{
    @ViewById(R.id.home_ptlist)
    PullToRefreshGridView ptRefreshGv;
    private String type ;
    private List<RoomListEntity> list = new ArrayList<>();
    private HomeRoomAdapter adapter;
    private RoomListPresenterImpl presenter;
    private int count = 20;
    private int page = 1;
    private int group = 0;

//    private View headView;
//    private ConvenientBanner<String> chooseView;
//    private Call<BannerEntity> callFirst;
    private List<String> listFirst = new ArrayList<>();
    @Override
    public void before() {
        EventBus.getDefault().register(this);
        type = getArguments().getString(AppConstant.HOME_TYPE);
        presenter = new RoomListPresenterImpl(this,count,page,group);
    }

    @Override
    public void initView() {
        ptRefreshGv.setMode(PullToRefreshBase.Mode.BOTH);
        ptRefreshGv.setOnRefreshListener(this);
        //广告头
//        initView1();
        GridView gridView = new GridView(getContext());
        gridView.getAdapter();
    }

//    private void initView1() {
//        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        headView = inflater.inflate(R.layout.choose_head,null);
//        chooseView = (ConvenientBanner) headView.findViewById(R.id.choose_frag_viewpager);
//
////        chooseRecycler = (RecyclerView) headView.findViewById(R.id.choose_frag_recycler);
//    }

    @Override
    public void initData() {
//        initData1();
        List<RoomListEntity> listEntities = DbUtil.getSession()
                    .getRoomListEntityDao()
                    .queryBuilder()
                    .limit(20)
                    .list();
        list.addAll(listEntities);
        adapter = new HomeRoomAdapter(list,getContext());
        EventBus.getDefault().post(list,"roomList");
//        ptRefreshGv.addHeaderView(headView);
        ptRefreshGv.setAdapter(adapter);
        presenter.getRoomList();

    }

    @Override
    public void successRoomList(RoomEntity entity) {
        ptRefreshGv.onRefreshComplete();
        if (page == 1){
            list.clear();
        }
        List<RoomListEntity> roomListEntities = entity.getRoomlist();
        DbUtil.getSession().getRoomListEntityDao().insertOrReplaceInTx(roomListEntities);
        list.addAll(roomListEntities);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void faidedRoomList() {
        Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page = 1;
        new RoomListPresenterImpl(this,count,page,group).getRoomList();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        ptRefreshGv.onRefreshComplete();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    //    private void initData1() {
//        if (callFirst != null)
//            callFirst.cancel();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(UrlConstants.BANNERS_FIRST_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        callFirst = retrofit.create(BannerService.class).getBannerList();
//        callFirst.enqueue(new Callback<BannerEntity>() {
//            @Override
//            public void onResponse(Call<BannerEntity> call, Response<BannerEntity> response) {
//                BannerEntity entity = response.body();
//                List<Banners> banners = entity.getBanners().getBanners();
//                listFirst.clear();
//                for (int i = 0; i < banners.size(); i++) {
//                    listFirst.add(banners.get(i).getImage_url());
//                }
//                chooseView.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
//                    @Override
//                    public LocalImageHolderView createHolder() {
//                        return new LocalImageHolderView();
//                    }
//                },listFirst).setPageIndicator(new int[]{R.mipmap.ic_page_indicator,R.mipmap.ic_page_indicator_focused})
//                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                ;
//                chooseView.startTurning(2000);
////                chooseView.setImageUris(listFirst);
////                chooseView.setEffect(EffectConstants.CUBE_EFFECT);//更改图片切换的动画效果
////                pageAdapter.notifyDataSetChanged();
//            }

//            @Override
//            public void onFailure(Call<BannerEntity> call, Throwable t) {
//                t.printStackTrace();
////                Toast.makeText(getActivity(), "网络问题", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//    public class LocalImageHolderView implements Holder<String> {
//        private SimpleDraweeView simpleDraweeView;
//        @Override
//        public View createView(Context context) {
//            simpleDraweeView = new SimpleDraweeView(context);
//            GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
//            GenericDraweeHierarchy hierarchy = builder.build();
//            hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
//            simpleDraweeView.setHierarchy(hierarchy);
//            return simpleDraweeView;
//        }
//
//        @Override
//        public void UpdateUI(Context context, int position, String data) {
//            simpleDraweeView.setImageURI(Uri.parse(data));
//        }
//    }
}
