package vip.xuanhao.integration.views.adapters.zhihu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vip.xuanhao.integration.R;
import vip.xuanhao.integration.model.domain.DailyListBean;
import vip.xuanhao.integration.utils.BannerHelper;
import vip.xuanhao.integration.utils.ImageLoaderHelper;
import vip.xuanhao.integration.app.widget.IOnRecycleViewItemClickListener;
import vip.xuanhao.integration.views.activitys.DetailActivity;

/**
 * Created by Xuanhao on 2016/12/14.
 */

public class DailyNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private LayoutInflater inflater;
    private IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener;
    private List<DailyListBean.StoriesBean> mList;
    private List<DailyListBean.TopStoriesBean> mTopList;

    //滚动调
    private static final int BANNER_TOP = 0;
    //内容
    private static final int CONTENT = 1;

    private static final int time = 3 * 1000;


    public DailyNewsAdapter(Context mContext, List<DailyListBean.StoriesBean> mList
            , List<DailyListBean.TopStoriesBean> mTopList, IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener) {
        this.mContext = mContext;
        this.mList = mList;
        this.mTopList = mTopList;
        this.iOnRecycleViewItemClickListener = iOnRecycleViewItemClickListener;
        this.inflater = LayoutInflater.from(mContext);
    }

    public void setiOnRecycleViewItemClickListener(IOnRecycleViewItemClickListener iOnRecycleViewItemClickListener) {
        this.iOnRecycleViewItemClickListener = iOnRecycleViewItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return BANNER_TOP;
            default:
                return CONTENT;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER_TOP:
                View view = inflater.inflate(R.layout.banner_layout_2, parent, false);
                return new BannerViewHolder(view);
            default:
                View contentView = inflater.inflate(R.layout.item_dailycontent, parent, false);
                return new ContextViewHolder(contentView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case BANNER_TOP:
                BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                bindBannerData(bannerViewHolder, position);
                break;
            default:
                ContextViewHolder contextViewHolder = (ContextViewHolder) holder;
                bindContentData(contextViewHolder, position);
                bindEvent(contextViewHolder, position);
                break;
        }
    }

    private void bindEvent(ContextViewHolder contextViewHolder, final int contentPosition) {
        contextViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnRecycleViewItemClickListener.onItemClick(v, contentPosition);
            }
        });
    }

    /**
     * 绑定内容数据
     *
     * @param position
     */
    private void bindContentData(ContextViewHolder contextViewHolder, int position) {
        contextViewHolder.tvDailyItem.setText(mList.get(position).getTitle());
        ImageLoaderHelper.loadImage(mContext, mList.get(position).getImages().get(0), contextViewHolder.imgDailyItem);
    }


    /**
     * 设置轮播数据
     *
     * @param position
     */
    private void bindBannerData(BannerViewHolder bannerViewHolder, int position) {
        bannerViewHolder.convenientBanner.setPages(new BannerHelper(), mTopList)
                .setPageIndicator(new int[]{R.drawable.dot_n, R.drawable.dot_p})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .startTurning(time);
        bannerViewHolder.convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Logger.w(mTopList.get(position).toString());
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("id", mList.get(position).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList != null && !mList.isEmpty() ? mList.size() : 0;
    }

    class ContextViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_daily_item)
        ImageView imgDailyItem;
        @BindView(R.id.tv_daily_item)
        TextView tvDailyItem;

        public ContextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.convenientBanner)
        ConvenientBanner convenientBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
