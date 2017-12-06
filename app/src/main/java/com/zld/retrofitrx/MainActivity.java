package com.zld.retrofitrx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.zld.retrofitrx.api.ToutiaoApi;
import com.zld.retrofitrx.bean.NewsBean;
import com.zld.retrofitrx.utils.RetrofitFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv_content);
        requestData();
    }

    private void requestData() {
        RetrofitFactory.getRetrofit().create(ToutiaoApi.class)
                .getToutiaoNews("top", "44989df28ef48c89d700d68c10c2c74c")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsBean>() {
                    @Override
                    public void accept(NewsBean newsBean) throws Exception {
                        tv.setText(newsBean.getResult().getData().get(0).getTitle());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this,"加载错误",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
