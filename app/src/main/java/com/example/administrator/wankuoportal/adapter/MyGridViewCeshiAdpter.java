package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.global.ProdctBean;

import java.util.List;


/**
 * GridView加载数据的适配器
 * 首页顶部分类
 * @author Administrator
 *
 */
public class MyGridViewCeshiAdpter extends BaseAdapter {

	private Context context;
	private List<ProdctBean> lists;//数据源

	public MyGridViewCeshiAdpter(Context context, List<ProdctBean> lists) {
		this.context = context;
		this.lists = lists;

	}

	/**
	 * 先判断数据及的大小是否显示满本页lists.size() > (mIndex + 1)*mPagerSize
	 * 如果满足，则此页就显示最大数量lists的个数
	 * 如果不够显示每页的最大数量，那么剩下几个就显示几个
	 */
	@Override
	public int getCount() {
		return lists.size();
	}

	@Override
	public ProdctBean getItem(int arg0) {
		return lists.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0 ;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_view, null);
			holder.tv_name = (TextView)convertView.findViewById(R.id.item_name);
			holder.iv_nul = (ImageView)convertView.findViewById(R.id.item_image);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder)convertView.getTag();
		}
		holder.tv_name.setText(lists.get(position).getName() + "");
		holder.iv_nul.setImageResource(lists.get(position).getUrl());

		return convertView;
	}
	static class ViewHolder{
		private TextView tv_name;
		private ImageView iv_nul;
	}
}
