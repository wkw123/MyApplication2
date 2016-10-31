package com.example.wkw.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wkw.myapplication.bean.NewsBean;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    TextView tv;
    private List<NewsBean> newsBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        tv = (TextView) findViewById(R.id.tv);
        newsBeanList = new ArrayList<>();
        /**
         * 请不要添加key参数.
         */
        Parameters params = new Parameters();
        params.add("type", "shehui");
        params.add("key", "df20b5a77b92b4df5d05816765403f70");
        /**
         * 请求的方法 参数: 第一个参数 当前请求的context;
         * 				  第二个参数 接口id;
         * 				  第三个参数 接口请求的url;
         * 				  第四个参数 接口请求的方式;
         * 				  第五个参数 接口请求的参数,键值对com.thinkland.sdk.android.Parameters类型;
         * 				  第六个参数请求的回调方法,com.thinkland.sdk.android.DataCallBack;
         *
         */
        JuheData.executeWithAPI(mContext, 235, "http://v.juhe.cn/toutiao/index",
                JuheData.GET, params, new DataCallBack() {
                    /**
                     * 请求成功时调用的方法 statusCode为http状态码,responseString为请求返回数据.
                     */
                    @Override
                    public void onSuccess(int statusCode, String responseString) {
                        // TODO Auto-generated method stub
                        try {
                            String name = "result";
                            JSONObject jsonObject =new JSONObject(responseString);
                            String result = jsonObject.getString(name);
                            jsonObject = new JSONObject(result);
//                            result = jsonObject.getString("data");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
//                            private String title;
//                            private String date;
//                            private String category;
//                            private String authot_name;
//                            private String thumbnail_pic_s;
//                            private String url;
//                            private String thumbnail_pic_s03;
                            for (int i = 0; i < jsonArray.length(); i ++){
                                JSONObject object = jsonArray.getJSONObject(i);
//                                Log.i("mains","Object" + i  + "");
                                NewsBean newsBean = new NewsBean();
                                Log.i("mains","Object" + object.toString()  + "");
                                newsBean.setTitle(object.getString("title"));
                                newsBean.setDate(object.getString("date"));
                                newsBean.setCategory(object.getString("category"));
                                newsBean.setAuthor_name(object.getString("author_name"));
                                newsBean.setThumbnail_pic_s(object.getString("thumbnail_pic_s"));
                                newsBean.setUrl(object.getString("url"));
                                newsBean.setThumbnail_pic_s03(object.getString("thumbnail_pic_s03"));
                                newsBeanList.add(newsBean);
                                Log.i("mains",i  + "");
                            }
                            Log.i("mains","jsonArray.length()" + jsonArray.length()  + "");
                            tv.append(newsBeanList.size() + "");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    /**
                     * 请求完成时调用的方法,无论成功或者失败都会调用.
                     */
                    @Override
                    public void onFinish() {
                        // TODO Auto-generated method stub
                        Toast.makeText(getApplicationContext(), "finish",
                                Toast.LENGTH_SHORT).show();
                    }

                    /**
                     * 请求失败时调用的方法,statusCode为http状态码,throwable为捕获到的异常
                     * statusCode:30002 没有检测到当前网络.
                     * 			  30003 没有进行初始化.
                     * 			  0 未明异常,具体查看Throwable信息.
                     * 			  其他异常请参照http状态码.
                     */
                    @Override
                    public void onFailure(int statusCode,
                                          String responseString, Throwable throwable) {
                        // TODO Auto-generated method stub
                        tv.append(throwable.getMessage() + "\n");
                    }
                });

    }

}
