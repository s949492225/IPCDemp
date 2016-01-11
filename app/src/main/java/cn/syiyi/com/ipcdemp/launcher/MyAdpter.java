package cn.syiyi.com.ipcdemp.launcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.syiyi.com.ipcdemp.R;

/**
 * IPCDemp...........
 * cn.syiyi.com.ipcdemp...........
 * Created by lintao.song on 2016/1/11.
 */
public class MyAdpter extends BaseAdapter {
    private List<String> mData;
    private Context mContext;
    LayoutInflater inflater;

    public MyAdpter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
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
        View view = inflater.inflate(R.layout.item, null, false);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(mData.get(position));
        return view;
    }
}
