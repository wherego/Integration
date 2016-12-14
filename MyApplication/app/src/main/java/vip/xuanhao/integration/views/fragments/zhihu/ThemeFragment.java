package vip.xuanhao.integration.views.fragments.zhihu;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

import vip.xuanhao.integration.presenters.ipresenter.zhihu.impl.ThemePresenter;
import vip.xuanhao.integration.views.Iviews.zhihu.IThemeView;

/**
 * Created by Xuanhao on 2016/12/14.
 */

public class ThemeFragment extends ZhihuBaseFragment<ThemePresenter> implements IThemeView {



    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);

    }
    @Override
    public void initView() {
        super.initView();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        zhihuNewsContent.setLayoutManager(gridLayoutManager);
        zhihuNewsContent.setItemAnimator(new DefaultItemAnimator());
        zhihuNewsContent.setAdapter(presenter.getAdapter(mContext));

    }

    @Override
    public void initData() {
        presenter.getDataSource();
    }

    @Override
    public void onLoad(BaseFooterView baseFooterView) {
        presenter.getDataSource();
    }

    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        presenter.getDataSource();
    }

    @Override
    public void onItemClick(View view, int position) {

    }
    @Override
    public void update() {
        presenter.notifyUpdate();
    }
}
