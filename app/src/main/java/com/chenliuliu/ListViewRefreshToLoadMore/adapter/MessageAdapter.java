package com.chenliuliu.ListViewRefreshToLoadMore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chenliuliu.ListViewRefreshToLoadMore.R;
import com.chenliuliu.ListViewRefreshToLoadMore.beans.RowsEntity;


/**
 * Created by liuliuchen on 15/11/17.
 */
public class MessageAdapter extends ArrayListAdapter<RowsEntity> {
    public MessageAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_message, parent, false);
            vh = new ViewHolder();
            vh.mTxtTitle = (TextView) convertView.findViewById(R.id.txt_message_title);
            vh.mImageView = (ImageView) convertView.findViewById(R.id.img_test);
            vh.mTxtBody = (TextView) convertView.findViewById(R.id.txt_message_body);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        bindViews(vh, position);
        return convertView;
    }

    private void bindViews(ViewHolder holder, final int position) {
        holder.mTxtTitle.setText(mList.get(position).getTitle());
        holder.mTxtBody.setText(android.text.Html.fromHtml(mList.get(position).getContent()));
        Glide.with(mContext).load("http://g.hiphotos.baidu.com/image/pic/item/4b90f603738da977772000d7b651f8198618e33b.jpg").override(500, 500).placeholder(R.mipmap.ic_launcher).error(R.drawable.back_no_data_message).into(holder.mImageView);
    }

    class ViewHolder {
        TextView mTxtTitle;
        ImageView mImageView;
        TextView mTxtBody;
    }
}
