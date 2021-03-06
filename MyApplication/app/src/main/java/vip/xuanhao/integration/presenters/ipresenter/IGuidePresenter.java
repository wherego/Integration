package vip.xuanhao.integration.presenters.ipresenter;

import java.util.List;

import vip.xuanhao.integration.views.adapters.GuideAdapter;

/**
 * Created by Xuanhao on 2016/9/22.
 */

public interface IGuidePresenter extends IBasePresenter {

    /**
     * 引导图片资源
     *
     * @return
     */
    List<Integer> getGuideData();

    int getDataSize();

    GuideAdapter getAdapter();

    void jump();
}
