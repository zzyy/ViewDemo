package com.zy.md.main.ui;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zy.md.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 2016/8/18.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerHolder> {
    protected List<T> mData;
    protected int mLayoutResId;
    protected LayoutInflater mLayoutInflater;
    protected ItemClickListener mItemClickListener;

    protected LoadMoreListener mLoadMoreListener;
    private View mLoadMoreLoadingView;
    private int mLoadMoreState = LOAD_MORE_IDLE;
    private static final int LOAD_MORE_IDLE = 0;
    private static final int LOAD_MORE_LOADING = 1;
    private static final int LOAD_MORE_COMPLETE = 2;


    public static final int HEADER_VIEW = 0x00000111;
    public static final int LOADING_VIEW = 0x00000222;
    public static final int FOOTER_VIEW = 0x00000333;
    public static final int CONTENT_VIEW = 0x00000444;
    public static final int EMPTY_VIEW = 0x00000555;

    public BaseRecyclerAdapter(List<T> data, @LayoutRes int layoutResId) {
        this.mData = data == null ? new ArrayList<T>() : data;
        if (layoutResId != 0) {
            this.mLayoutResId = layoutResId;
        }
    }

    public void setData(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
        hideLoadingMoreView();
    }

    public void addData(List<T> data){
        mData.addAll(data);
        notifyDataSetChanged();
        hideLoadingMoreView();
    }

    @Override
    public int getItemViewType(int position) {
        final int lastDataPosition = mData.size() - 1;

        if (position == lastDataPosition + 1){
            if (isEnableLoadMore()){
                return LOADING_VIEW;
            }
        }

        return CONTENT_VIEW;
    }

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (this.mLayoutInflater == null) {
            this.mLayoutInflater = LayoutInflater.from(parent.getContext());
        }

        if (viewType == LOADING_VIEW){
            return getLoadMoreViewHolder(parent);
        }

        View convertView = mLayoutInflater.inflate(mLayoutResId, parent, false);
        BaseRecyclerHolder viewHolder = new BaseRecyclerHolder(convertView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position) {
        final int viewType = holder.getItemViewType();

        if (viewType == LOADING_VIEW){
            callbackLoadMore();
        }else {
            initItemClickListener(holder);
            onBindViewHolder(holder, position, mData.get(position));
        }

    }

    public abstract void onBindViewHolder(BaseRecyclerHolder holder, int position, T itemData);

    @Override
    public int getItemCount() {
        int loadMoreCount = isEnableLoadMore() ? 1 : 0;

        return mData.size() + loadMoreCount;
    }


    /**
     * 在 onCreateViewHolder的 viewHolder创建后调用, 绑定item的click事件
     */
    protected void initItemClickListener(final BaseRecyclerHolder baseViewHolder) {
        if (mItemClickListener != null) {
            baseViewHolder.itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mItemClickListener.onItemClick(baseViewHolder.itemView, baseViewHolder.getLayoutPosition());
                        }
                    }
            );
        }
    }

    protected View.OnClickListener getOnItemClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null && v != null) {
                    mItemClickListener.onItemClick(v, position);
                }
            }
        };
    }


    public boolean isEnableLoadMore() {
        return mLoadMoreListener != null && mData.size() > 0/*本身有数据才让显示加载更多,避免第一进入的时候直接调用*/;
    }

    private BaseRecyclerHolder getLoadMoreViewHolder(ViewGroup parent) {
        if (mLoadMoreLoadingView == null) {
            View loadMoreView = mLayoutInflater.inflate(R.layout.def_loading, parent, false);
            return new BaseRecyclerHolder(loadMoreView);
        }

        return new BaseRecyclerHolder(mLoadMoreLoadingView);
    }

    /**
     * 是否正在加载更多
     */
    private boolean isLoadingMore(){
        return mLoadMoreState == LOAD_MORE_LOADING;
    }

    public void hideLoadingMoreView(){
        mLoadMoreState = LOAD_MORE_IDLE;
    }

    public void loadMoreComplete(){
        hideLoadingMoreView();
        mLoadMoreState = LOAD_MORE_COMPLETE;
    }


    private void callbackLoadMore(){
        if (isEnableLoadMore() && !isLoadingMore()){
            mLoadMoreListener.onLoadMore();
            mLoadMoreState = LOAD_MORE_LOADING;
        }
    }

    /**
     * 点击事件的listener
     */
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public interface LoadMoreListener {
        void onLoadMore();
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.mLoadMoreListener = loadMoreListener;
    }
}
