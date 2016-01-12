package com.chenliuliu.ListViewRefreshToLoadMore.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chenliuliu.ListViewRefreshToLoadMore.R;


/**
 * Created by liuliuchen on 15/11/5.
 */
public class ListViewLoadMore extends ListView implements OnScrollListener {

    View footView;

    private LinearLayout llLoadind;

    private TextView tvLoadFull;
    private TextView tvLoadNotice;

    int lastItem;

    int totalItemCount;

    boolean isLoading = false;

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void setIsRefreshing(boolean isRefreshing) {
        this.isRefreshing = isRefreshing;
    }

    boolean isRefreshing = false;//是否正在刷新

    IsLoadingListener isLoadingListener;

    private boolean isLoadFull = false;

    public boolean isLoadFull() {
        return isLoadFull;
    }

    public void setLoadFull(boolean isLoadFull) {
        this.isLoadFull = isLoadFull;
        setIsRefreshing(false);
        if (isLoadFull) {
            tvLoadNotice.setVisibility(View.GONE);
            tvLoadFull.setVisibility(View.VISIBLE);
        } else {
            tvLoadNotice.setVisibility(View.VISIBLE);
            tvLoadFull.setVisibility(View.GONE);
        }
    }

    public ListViewLoadMore(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public ListViewLoadMore(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ListViewLoadMore(Context context) {
        super(context);
        initView(context);
    }

    void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        footView = inflater.inflate(R.layout.foot_layout, null);
        addFooterView(footView);
        llLoadind = (LinearLayout) footView.findViewById(R.id.foot_layout);
        llLoadind.setVisibility(View.GONE);
        tvLoadFull = (TextView) footView.findViewById(R.id.txt_loadfull);
        tvLoadNotice = (TextView) footView.findViewById(R.id.txt_loadnotice);
        tvLoadNotice.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRefreshing) {
                    return;
                }
                isLoading = true;
                llLoadind.setVisibility(View.VISIBLE);
                tvLoadFull.setVisibility(View.GONE);
                tvLoadNotice.setVisibility(View.GONE);
                isLoadingListener.onLoad();
            }
        });
        setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (lastItem == totalItemCount && scrollState == SCROLL_STATE_IDLE) {
            if (!isLoading && isLoadFull == false && isRefreshing == false) {
                isLoading = true;
                llLoadind.setVisibility(View.VISIBLE);
                tvLoadFull.setVisibility(View.GONE);
                tvLoadNotice.setVisibility(View.GONE);
                isLoadingListener.onLoad();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
        if (totalItemCount < 2) {//当listview里面没有item的时候，隐藏footview
            this.footView.setVisibility(View.GONE);
            this.footView.setPadding(0, -footView.getHeight(), 0, 0);
        } else {
            this.footView.setVisibility(View.VISIBLE);
            this.footView.setPadding(0, 0, 0, 0);
        }
    }

    public void setOnLoadingListener(IsLoadingListener isLoadingListener) {
        this.isLoadingListener = isLoadingListener;
    }

    public interface IsLoadingListener {
        public void onLoad();
    }

    public void complateLoad() {
        isLoading = false;
        llLoadind.setVisibility(View.GONE);
    }
}
