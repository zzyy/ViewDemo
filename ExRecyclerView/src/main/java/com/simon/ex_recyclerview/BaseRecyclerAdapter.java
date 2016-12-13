package com.simon.ex_recyclerview;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    }

    public void addData(List<T> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return mData.get(position);
    }


    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (this.mLayoutInflater == null) {
            this.mLayoutInflater = LayoutInflater.from(parent.getContext());
        }

        View convertView = mLayoutInflater.inflate(mLayoutResId, parent, false);
        BaseRecyclerHolder viewHolder = new BaseRecyclerHolder(convertView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position) {
        initItemClickListener(holder);
        onBindViewHolder(holder, position, mData.get(position));
    }

    public abstract void onBindViewHolder(BaseRecyclerHolder holder, int position, T itemData);

    @Override
    public int getItemCount() {
        return mData.size();
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

    /**
     * 点击事件的listener
     */
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.mItemClickListener = listener;
    }

}
