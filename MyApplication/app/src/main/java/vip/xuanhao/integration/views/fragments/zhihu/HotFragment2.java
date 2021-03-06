package vip.xuanhao.integration.views.fragments.zhihu;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

import vip.xuanhao.integration.presenters.ipresenter.zhihu.impl.HotPresenter;
import vip.xuanhao.integration.views.Iviews.zhihu.IHotViewView;

/**
 * Created by Xuanhao on 2016/12/14.
 */

public class HotFragment2 extends ZhihuBaseViewFragment<HotPresenter> implements IHotViewView {


    @Override
    public void onLoad(BaseFooterView baseFooterView) {
        presenter.getDataSource();
    }

    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        presenter.getDataSource();
    }

    @Override
    public void initView() {
        super.initView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        zhihuNewsContent.setLayoutManager(linearLayoutManager);
        zhihuNewsContent.setItemAnimator(new DefaultItemAnimator());
        zhihuNewsContent.setAdapter(presenter.getHotAdapter(mContext, this));
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initData() {
        presenter.getDataSource();
    }

    @Override
    public void onItemClick(View view, int position) {
        presenter.onItemClick(mContext, view, position);
    }

    @Override
    public void update() {
        presenter.notifyUpdate();
    }
}
