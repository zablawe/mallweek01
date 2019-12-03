package com.bawei.zhangao123.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * author: 张奥
 * data: 2019-12-03
 * function:网络封装工具类
 */
public class NetUtils {

    /* 单例模式*/
    private static NetUtils netUtils=new NetUtils();

    private NetUtils(){

    }

    public static NetUtils getInstance() {
        return netUtils;
    }
 /*  接口回调*/
    public interface MyCallBack{
        void getJson(String json);
        void getBitmap(Bitmap bitmap);
    }
     /*  网络判断*/
    public boolean hasNet(Context context){
      ConnectivityManager manager = (ConnectivityManager) context.getSystemService( Context.CONNECTIVITY_SERVICE );
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return true;
        }else{
            return false;
        }
    }
    @SuppressLint("StaticFieldLeak")
    public void getJsonDate(final String httpUrl, final MyCallBack myCallBack){
        new AsyncTask <Void, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                myCallBack.getJson( s );
            }

            @Override
            protected String doInBackground(Void... voids) {
                String json="";
                InputStream inputStream=null;
                try {
                    URL url = new URL( httpUrl );
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod( "GET" );
                        /*  设置加载  连接 读取超时*/
                        connection.setReadTimeout( 5000 );
                        connection.setConnectTimeout( 5000 );
                        connection.connect();
                        /* 判断网路请求码*/
                        if(connection.getResponseCode()==200){
                             inputStream = connection.getInputStream();
                             json=io2String(inputStream);
                        }else{
                            Log.i( "tag", "请求失败" );
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /*  关流处理异常*/
                finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return json;
            }
        }.execute(  );
    }
    @SuppressLint("StaticFieldLeak")
    public void getPhoto(final String httpUrl, final MyCallBack myCallBack){
        new AsyncTask <Void, Void, Bitmap>() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                myCallBack.getBitmap( bitmap );
            }

            @Override
            protected Bitmap doInBackground(Void... voids) {
                String json="";
                InputStream inputStream=null;
                Bitmap bitmap=null;
                try {
                    URL url = new URL( httpUrl );
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod( "GET" );
                    connection.setReadTimeout( 5000 );
                    connection.setConnectTimeout( 5000 );
                    connection.connect();
                    if(connection.getResponseCode()==200){
                        inputStream = connection.getInputStream();
                      bitmap= BitmapFactory.decodeStream( inputStream );
                    }else{
                        Log.i( "tag", "请求失败" );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return bitmap;
            }
        }.execute(  );
    }

      /* 流转字符串*/
    public String io2String(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[1024];
        int len=-1;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while ((len=inputStream.read( bytes ))!=-1){
            byteArrayOutputStream.write( bytes,0,len );
        }
        byte[] bytes1 = byteArrayOutputStream.toByteArray();
        String json = new String( bytes1 );
        return json;
    }
}
