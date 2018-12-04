package com.utils.gyymz.base;


import android.os.Build;
import android.provider.Settings;
import android.view.View;

import com.lsjr.net.R;
import com.utils.gyymz.base.BaseAppCompatActivity;
import com.utils.gyymz.mvp.base.BasePresenter;
import com.utils.gyymz.mvp.base.BaseView;
import com.utils.gyymz.utils.UIUtils;
import com.utils.gyymz.wiget.NavigationBarView;

import java.lang.reflect.ParameterizedType;


/**
 * Created by qiyue on 2016/4/5.
 */
public abstract class MVPBaseActivity<P extends BasePresenter> extends BaseAppCompatActivity implements BaseView {
    public P mPresenter;

    @Override
    protected void initPresenter() {
        mPresenter = getDelegateClass();
        if (mPresenter != null) mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
    }

    protected abstract P getDelegateClass();

    @Override
    protected void initTitle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.System.canWrite(this);
        }
//        setTopLeftButton(R.mipmap.return_icon).
//                setTitleTextColor(UIUtils.getColor(R.color.white)).
//                setBackgroundColor(UIUtils.getColor(R.color.black));
        setImmersionBarBlack();
    //    getToolBarView().setBackgroundColor(getResources().getColor(R.color.app_status_bar_color));
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initView() {

    }


    protected NavigationBarView showNavigationBarCanBack() {
        showNavigationBarView().setBackgroundRes(NavigationBarView.NavigationViewType.LEFT_IV, R.drawable.return_icon)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
        return getNavigationBarView();
    }

}
