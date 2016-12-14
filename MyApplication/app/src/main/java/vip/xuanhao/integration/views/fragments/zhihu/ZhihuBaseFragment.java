package vip.xuanhao.integration.views.fragments.zhihu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.race604.drawable.wave.WaveDrawable;
import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

import butterknife.BindView;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.presenters.BasePresenter;
import vip.xuanhao.integration.views.BaseFragment;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.zhihu.IZhiHuBaseView;
import vip.xuanhao.integration.views.ui.ExpandFooterView;
import vip.xuanhao.integration.views.ui.ExpandHeaderView;

/**
 * Created by Xuanhao on 2016/12/14.
 */

public abstract class ZhihuBaseFragment<T extends BasePresenter> extends BaseFragment<T> implements IZhiHuBaseView,
        BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener, IOnRecycleViewItemClickListener {

    @BindView(R.id.zhihu_news_content)
    RecyclerView zhihuNewsContent;
    @BindView(R.id.zhihu_news_header)
    ExpandHeaderView zhihuNewsHeader;
    @BindView(R.id.zhihu_news_footer)
    ExpandFooterView zhihuNewsFooter;
    @BindView(R.id.img_loading)
    ImageView imageView;
    private WaveDrawable mWaveDrawable;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_zhihu_news;
    }

    @Override
    public void initView() {
        mWaveDrawable = new WaveDrawable(mContext, R.drawable.christmastree);
        imageView.setImageDrawable(mWaveDrawable);
        mWaveDrawable.setIndeterminate(true);
        mWaveDrawable.start();
    }

    @Override
    public void initEvent() {
        zhihuNewsHeader.setOnRefreshListener(this);
        zhihuNewsFooter.setOnLoadListener(this);
    }


    @Override
    public void hiddenLoading() {
        mWaveDrawable.stop();
        imageView.setVisibility(View.GONE);
    }

    @Override
    public void stopRefresh() {
        zhihuNewsHeader.stopRefresh();
    }

    @Override
    public void stopLoad() {
        zhihuNewsFooter.stopLoad();
    }
}
