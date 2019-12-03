package com.bawei.zhangao123.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bawei.zhangao123.R;
import com.bawei.zhangao123.base.BaseFragment;
import com.bawei.zhangao123.contract.InfoContract;
import com.bawei.zhangao123.presenter.Prductentity;
import com.bawei.zhangao123.util.NetUtils;

import java.util.ArrayList;
import java.util.List;


public class ShowFragment extends BaseFragment implements InfoContract.IView {

    private Button button;
    private GridView gridView;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    ArrayList <Prductentity.ShopGridDataBean> grid = new ArrayList <>();
    @Override
    protected int inniLayout() {
        return R.layout.fragment_show;
    }

    @Override
    protected void inniView(View view) {
        gridView = view.findViewById( R.id.grid );
        button = view.findViewById( R.id.btn_intent );
        linearLayout1 = view.findViewById( R.id.lin1 );
        linearLayout2 = view.findViewById( R.id.lin2 );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );

    }

    @Override
    protected void inniDate() {
      if( NetUtils.getInstance().hasNet( getActivity() )){
             linearLayout1.setVisibility( View.VISIBLE );
             linearLayout2.setVisibility( View.GONE );
      }else{
          linearLayout1.setVisibility( View.GONE );
          linearLayout2.setVisibility( View.VISIBLE );
      }
    }

    @Override
    public void seccess(Prductentity prductentity) {
        Toast.makeText( getActivity(),prductentity.getCode(),Toast.LENGTH_SHORT ).show();

        List <Prductentity.ShopGridDataBean> grids= prductentity.getShopGridData();
        grid.addAll( grids );
        Log.e( "grids", "shuju "+grid );
        gridView.setAdapter( new MyAdapter(grid) );
    }

    @Override
    public void failure(Throwable throwable) {
          Toast.makeText( getActivity(),"请求失败",Toast.LENGTH_SHORT ).show();
    }


}
