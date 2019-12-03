package com.bawei.zhangao123.model;

import android.graphics.Bitmap;
import android.util.Log;

import com.bawei.zhangao123.contract.InfoContract;
import com.bawei.zhangao123.presenter.Prductentity;
import com.bawei.zhangao123.util.NetUtils;
import com.google.gson.Gson;

/**
 * author: 张奥
 * data: 2019-09-29
 * function:
 */
public class NewDate  implements InfoContract.iModel {

    @Override
    public void getModelDate(String url, final modelCallBack modelCallBack) {
        NetUtils.getInstance().getJsonDate( url, new NetUtils.MyCallBack() {
            @Override
            public void getJson(String json) {
                Log.i( "json", "getJson: "+json );
                Prductentity prductentity = new Gson().fromJson( json, Prductentity.class );
                modelCallBack.seccess( prductentity );

            }

            @Override
            public void getBitmap(Bitmap bitmap) {

            }
        } );
    }
}
