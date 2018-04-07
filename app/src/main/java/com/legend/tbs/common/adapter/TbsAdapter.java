package com.legend.tbs.common.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.legend.tbs.R;
import com.legend.tbs.common.CircleImageView;
import com.legend.tbs.common.model.TbsBean;
import com.legend.tbs.common.utils.Calculate;

import java.util.List;

/**
 * @author Legend
 * @data by on 2018/4/5.
 * @description
 */

public class TbsAdapter extends RecyclerView.Adapter<TbsAdapter.ViewHolder> {

    private List<TbsBean> mTbsList;
    private Context mContext;
    private static String nick="";
    private static String qqImage="";
    private static String qq="";
    public static final String GET_NICK = "http://r.pengyou.com/fcg-bin/cgi_get_portrait.fcg?uins=";



    public TbsAdapter(List<TbsBean> tbsList) {
        this.mTbsList = tbsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tbs,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tbsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qq = String.valueOf(viewHolder.fromQQ.getText());
                if (Calculate.checkApkExist(mContext,"com.tencent.mobileqq")) {
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("mqqapi://card/show_pslcard?src_type=internal&version=1&uin="+qq.substring(4)+"&card_type=person&source=qrcode")));
                } else {
                    Toast.makeText(mContext,"请安装QQ后再次尝试哦",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return viewHolder;
    }

    @Override
    public synchronized void onBindViewHolder(ViewHolder holder, int position) {
        synchronized (this) {
            TbsBean tbs = mTbsList.get(position);
            qq = Calculate.decode(tbs.getFromEncodeUin());
            Glide.with(mContext)
                    .load("http://q2.qlogo.cn/headimg_dl?dst_uin=+"+qq+"&spec=100")
                    .placeholder(R.drawable.ic_loading_public)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .error(R.drawable.ic_loading_public)
                    .into(holder.qqImage);
            holder.topicName.setText(tbs.getToNick() + " " + tbs.getTopicName());
            holder.fromNick.setText("来自：" + tbs.getFromNick());
            holder.fromQQ.setText("QQ号：" + qq);
            holder.realName.setText("未完待续" + nick.replace("\"",""));
            Log.d("昵称",nick+qqImage);
        }
        nick = "";
    }

    @Override
    public int getItemCount() {
        return mTbsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView topicName;
        TextView realName,fromNick;
        CardView tbsItem;
        AppCompatTextView fromQQ;
        CircleImageView qqImage;

        public ViewHolder(View itemView) {
            super(itemView);
            topicName = itemView.findViewById(R.id.topic_name);
            realName = itemView.findViewById(R.id.real_nick);
            tbsItem = itemView.findViewById(R.id.cardview);
            fromNick = itemView.findViewById(R.id.from_Nick);
            fromQQ = itemView.findViewById(R.id.from_qq);
            qqImage = itemView.findViewById(R.id.qq_image);
        }
    }
}
