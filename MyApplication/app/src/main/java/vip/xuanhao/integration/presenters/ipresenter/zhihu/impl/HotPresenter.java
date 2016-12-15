package vip.xuanhao.integration.presenters.ipresenter.zhihu.impl;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import vip.xuanhao.integration.model.domain.HotListBean;
import vip.xuanhao.integration.model.network.net.NetManager;
import vip.xuanhao.integration.presenters.BasePresenter;
import vip.xuanhao.integration.presenters.ipresenter.zhihu.IHotPresenter;
import vip.xuanhao.integration.utils.RxUtils;
import vip.xuanhao.integration.views.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.Iviews.zhihu.IHotView;
import vip.xuanhao.integration.views.adapters.zhihu.HotAdapter;


/**
 * Created by Xuanhao on 2016/12/14.
 */

public class HotPresenter extends BasePresenter<IHotView> implements IHotPresenter {

    private NetManager netManager;
    private HotAdapter hotAdapter;
    private List<HotListBean.RecentBean> mList = new ArrayList<>();

    @Inject
    public HotPresenter(NetManager netManager) {
        this.netManager = netManager;
    }


    public HotAdapter getHotAdapter(Context mContext, IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener) {
        if (hotAdapter == null) {
            hotAdapter = new HotAdapter(mContext, mList, iOnRecycleViewItemClickListener);
        }
        return hotAdapter;
    }

    public void getDataSource() {
        Subscription subscription = netManager
                .getZhiHuApiService()
                .getHotList()
                .compose(RxUtils.<HotListBean>rxSchedulerHelper())
                .doOnNext(new Action1<HotListBean>() {
                    @Override
                    public void call(HotListBean hotListBean) {
                        if (!mList.isEmpty()) {
                            mList.clear();
                        }
                    }
                })
                .subscribe(new Observer<HotListBean>() {
                    @Override
                    public void onCompleted() {
                        view.update();
                        view.stopLoad();
                        view.stopRefresh();
                        view.hiddenLoading();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HotListBean hotListBean) {
                        mList.addAll(hotListBean.getRecent());
                    }
                });
        addSubscriber(subscription);
    }


    public void notifyUpdate() {
        if (hotAdapter != null) {
            hotAdapter.notifyDataSetChanged();
        }
    }
}
