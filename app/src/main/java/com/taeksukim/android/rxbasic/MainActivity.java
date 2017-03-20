package com.taeksukim.android.rxbasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.Toast;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 실제 Task를 처리하는 객체.(발행자)
        Observable<String> simpleObservable =
                Observable.create(new Observable.OnSubscribe<String>() {

                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        // 네트웍을 통해서 데이터를 긁어온다
                        // 반복문을 돌면서 -----------------------
                        // for (네트웍에서 가져온 데이터) {
                        subscriber.onNext("Hello RxAndroid !!");
                        subscriber.onNext("Hello RxAndroid !! 1");
                        subscriber.onNext("Hello RxAndroid !! 2");
                        subscriber.onNext("Hello RxAndroid !! 3");
                        // }
                        //-------------------------------------
                        subscriber.onCompleted();
                    }
                });

        // 옵저버 (구독자)를 등록해주는 함수
        simpleObservable
                .subscribe(new Subscriber<String>() {// Observer(구독자)
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "complete!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "error: " + e.getMessage());
                    }

                    @Override
                    public void onNext(String text) {
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                });

    }
}


