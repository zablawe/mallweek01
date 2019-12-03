package com.bawei.zhangao123.contract;

import com.bawei.zhangao123.presenter.Prductentity;

/**
 * author: 张奥
 * data: 2019-09-29
 * function:
 */
public class InfoContract {
    public interface iModel{
       void getModelDate(String url,modelCallBack modelCallBack);
        interface modelCallBack{
            void  seccess(Prductentity prductentity);
        }
    }
    public interface  IView{
        void  seccess(Prductentity prductentity);
        void  failure(Throwable throwable);
    }

    public interface  IPresenter{
        void  getListPresenter(String url);
    }
}
