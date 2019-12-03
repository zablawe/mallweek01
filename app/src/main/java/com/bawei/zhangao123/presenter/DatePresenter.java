package com.bawei.zhangao123.presenter;

import com.bawei.zhangao123.contract.InfoContract;
import com.bawei.zhangao123.model.NewDate;

/**
 * author: 张奥
 * data: 2019-09-29
 * function:
 */
public class DatePresenter implements InfoContract.IPresenter {

    private NewDate newDate;
   private  InfoContract.IView iView;
    @Override
    public void getListPresenter(String url) {
        newDate = new NewDate();
        newDate.getModelDate( url, new InfoContract.iModel.modelCallBack() {
            @Override
            public void seccess(Prductentity prductentity) {
                iView.seccess( prductentity );
            }
        } );
    }
}
