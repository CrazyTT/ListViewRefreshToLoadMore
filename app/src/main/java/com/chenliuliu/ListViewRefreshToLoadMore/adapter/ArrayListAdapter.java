package com.chenliuliu.ListViewRefreshToLoadMore.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class ArrayListAdapter<T> extends BaseAdapter {

    protected ArrayList<T> mList;
    protected Context mContext;

    public ArrayListAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        if (mList != null)
            return mList.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    abstract public View getView(int position, View convertView, ViewGroup parent);

    public void setList(ArrayList<T> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void addList(ArrayList<T> list) {
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    public ArrayList<T> getList() {
        return mList;
    }

    public void setList(T[] list) {
        ArrayList<T> arrayList = new ArrayList<T>(list.length);
        for (T t : list) {
            arrayList.add(t);
        }
        setList(arrayList);
    }
}