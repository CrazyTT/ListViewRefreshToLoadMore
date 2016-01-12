package com.chenliuliu.ListViewRefreshToLoadMore.acticitys;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;

import com.chenliuliu.ListViewRefreshToLoadMore.R;
import com.chenliuliu.ListViewRefreshToLoadMore.adapter.MessageAdapter;
import com.chenliuliu.ListViewRefreshToLoadMore.beans.RowsEntity;
import com.chenliuliu.ListViewRefreshToLoadMore.views.ListViewLoadMore;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MessageActivity extends BaseActivity implements ListViewLoadMore.IsLoadingListener {
    @Bind(R.id.lv_message)
    public ListViewLoadMore mLvMessage;
    @Bind(R.id.swiperefresh)
    public SwipeRefreshLayout mSwiperefresh;
    public int pageNum = 1;
    @Bind(R.id.content_no_data)
    LinearLayout mContentNoData;
    private Handler mHandler;
    private ArrayList<RowsEntity> list = new ArrayList<>();
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.layout_activity_message);
        super.onCreate(savedInstanceState);
    }

    @Override
    void initViews() {
        mHandler = new Handler();
        ButterKnife.bind(this);
        mLvMessage.setOnLoadingListener(this);
        tvTitle.setText("ListViewLoadMore");
        mSwiperefresh.setColorSchemeResources(
                R.color.colorTitle, R.color.colorPrimary,
                R.color.colorAccent, R.color.colorPrimaryDark);
        mSwiperefresh.setProgressViewOffset(false, 0, 70);
        mSwiperefresh.setRefreshing(true);
        setData(true);
        messageAdapter = new MessageAdapter(this);
        messageAdapter.setList(list);
        mLvMessage.setAdapter(messageAdapter);
        mSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setData(true);
            }
        });
    }

    @Override
    public void onLoad() {
        setData(false);
    }

    public void setData(final boolean isRefresh) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isRefresh) {
                    pageNum = 1;
                    list.clear();
                    messageAdapter.setList(list);
                }
                if (pageNum < 4) {
                    for (int i = 0; i < 3; i++) {
                        RowsEntity rowsEntity = new RowsEntity(getString(R.string.string_title), getString(R.string.string_url), getString(R.string.string_data));
                        list.add(rowsEntity);
                    }
                    messageAdapter.setList(list);
                    mSwiperefresh.setRefreshing(false);
                    mContentNoData.setVisibility(View.GONE);
                    mLvMessage.setLoadFull(false);
                    mLvMessage.complateLoad();
                    pageNum++;
                } else {
                    mLvMessage.setLoadFull(true);
                    mLvMessage.complateLoad();
                }
            }
        }, 2000);
    }
}
