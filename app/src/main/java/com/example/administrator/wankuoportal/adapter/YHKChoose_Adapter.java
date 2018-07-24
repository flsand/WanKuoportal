package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.YHK;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class YHKChoose_Adapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<YHK.DatasBean> list;
    private Callback mCallback;

    public YHKChoose_Adapter(Context context, List<YHK.DatasBean> list) {
        this.context = context;
        this.list = list;

    }




    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolde viewHolde = null;
        LayoutInflater myInflater = LayoutInflater.from(context);

        if (convertView == null) {
            viewHolde = new ViewHolde();
            convertView = myInflater.inflate(R.layout.yhk_adapter, null);
            viewHolde.yinhang = (TextView) convertView.findViewById(R.id.yinhang);
            viewHolde.kahao = (TextView) convertView.findViewById(R.id.kahao);
            viewHolde.delete = (TextView) convertView.findViewById(R.id.delete);
            viewHolde.ima = (ImageView) convertView.findViewById(R.id.ima);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolde) convertView.getTag();
        }

        switch (list.get(position).getBankType()) {
            case 1:
                viewHolde.yinhang.setText("中国工商银行");
                Glide.with(context).load(R.drawable.gongshang).into(viewHolde.ima);
                break;
            case 2:
                viewHolde.yinhang.setText("中国农业银行");
                Glide.with(context).load(R.drawable.nongye).into(viewHolde.ima);
                break;
            case 3:
                viewHolde.yinhang.setText("中国银行");
                Glide.with(context).load(R.drawable.zhongguo).into(viewHolde.ima);
                break;
            case 4:
                viewHolde.yinhang.setText("中国建设银行");
                Glide.with(context).load(R.drawable.jianshe).into(viewHolde.ima);
                break;
            case 5:
                viewHolde.yinhang.setText("中国交通银行");
                Glide.with(context).load(R.drawable.jiaotong).into(viewHolde.ima);
                break;
            default:
                break;

        }

        viewHolde.kahao.setText("尾号" + list.get(position).getBankCardNo().substring(list.get(position).getBankCardNo().length() - 4) + "储蓄卡");

        viewHolde.delete.setOnClickListener(this);
        viewHolde.delete.setTag(position);
        viewHolde.delete.setVisibility(View.GONE);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        mCallback.click(v, (int) v.getTag());

    }



    public interface Callback {
        public void click(View v, int position);
    }

    static class ViewHolde {
        ImageView ima;
        TextView yinhang;
        TextView kahao;
        TextView delete;

    }
}
