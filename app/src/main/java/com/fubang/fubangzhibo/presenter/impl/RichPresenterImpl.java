package com.fubang.fubangzhibo.presenter.impl;

import android.util.Log;

import com.fubang.fubangzhibo.entities.RichListEntity;
import com.fubang.fubangzhibo.model.ModelFactory;
import com.fubang.fubangzhibo.presenter.RichPresenter;
import com.fubang.fubangzhibo.view.RichView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　　┃　＞　　　＜　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃...　⌒　...　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃   神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┗━━━┓
 * 　　　　　　　　　┃　　　　　　　┣┓
 * 　　　　　　　　　┃　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * <p/>
 * 项目名称：MyApplication
 * 类描述：
 * 创建人：dell
 * 创建时间：2016-06-24 17:15
 * 修改人：dell
 * 修改时间：2016-06-24 17:15
 * 修改备注：
 */
public class RichPresenterImpl implements RichPresenter {
    private RichView richView;

    public RichPresenterImpl(RichView richView) {
        this.richView = richView;
    }

    @Override
    public void getRich() {
        Log.d("123","getrich");
        ModelFactory.getInstance().getRichModelImpl().getRichEntityData(new Callback<RichListEntity>() {
            @Override
            public void onResponse(Call<RichListEntity> call, Response<RichListEntity> response) {
                richView.successRich(response.body());
            }

            @Override
            public void onFailure(Call<RichListEntity> call, Throwable t) {
                richView.failedRich();
            }
        });
    }
}
