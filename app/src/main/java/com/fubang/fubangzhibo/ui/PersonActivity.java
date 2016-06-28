package com.fubang.fubangzhibo.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fubang.fubangzhibo.R;
import com.zhuyunjian.library.StartUtil;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_person)
public class PersonActivity extends BaseActivity {
    @ViewById(R.id.person_calias)
    TextView caliasTv;
    @ViewById(R.id.person_id)
    TextView idTv;
    @ViewById(R.id.person_level)
    TextView levelTv;
    @ViewById(R.id.person_deposit)
    TextView depositTv;
    @ViewById(R.id.person_kbi)
    TextView kbiTv;
    @ViewById(R.id.person_cidiograph)
    TextView cidiographTv;
    @ViewById(R.id.person_score)
    TextView scoreTv;
    @ViewById(R.id.person_back_btn)
    ImageView backImage;
    @Override
    public void initView() {
        caliasTv.setText(StartUtil.getUserName(this));
        idTv.setText(StartUtil.getUserId(this));
        levelTv.setText(StartUtil.getUserLevel(this));
        depositTv.setText(StartUtil.getUserDeposit(this));
        kbiTv.setText(StartUtil.getUserKbi(this));
        cidiographTv.setText(StartUtil.getUserCidiograph(this));
        scoreTv.setText(StartUtil.getUserScore(this));
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
