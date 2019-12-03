package com.bawei.zhangao123.view;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.zhangao123.R;
import com.bawei.zhangao123.presenter.Prductentity;
import com.bawei.zhangao123.util.NetUtils;

import java.util.List;

/**
 * author: 张奥
 * data: 2019-09-29
 * function:
 */
public class MyAdapter extends BaseAdapter  {
    private List <Prductentity.ShopGridDataBean> grid;

    public MyAdapter(List <Prductentity.ShopGridDataBean> grid) {

        this.grid = grid;
    }

    @Override
    public int getCount() {
        return grid.size();
    }

    @Override
    public Object getItem(int position) {
        return grid.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHoldets viewHoldets;
        if(convertView==null){
            viewHoldets = new ViewHoldets();
            convertView=View.inflate( parent.getContext(),R.layout.viewa,null );
            viewHoldets.imageView=convertView.findViewById( R.id.imgs );
            viewHoldets.textView=convertView.findViewById( R.id.name );
            convertView.setTag( viewHoldets );
        }else{
            viewHoldets= (ViewHoldets) convertView.getTag();
        }
           viewHoldets.textView.setText( grid.get( position ).getTitle() );
        NetUtils.getInstance().getPhoto( grid.get( position ).getImageurl(), new NetUtils.MyCallBack() {
            @Override
            public void getJson(String json) {

            }

            @Override
            public void getBitmap(Bitmap bitmap) {
                viewHoldets.imageView.setImageBitmap( bitmap );
            }
        } );
        return convertView;
    }

    private class ViewHoldets {
        ImageView imageView;
        TextView textView;
    }
}
