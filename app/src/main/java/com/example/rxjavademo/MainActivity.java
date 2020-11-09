package com.example.rxjavademo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableError;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        button=findViewById(R.id.fetchData);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("rxJava", "onClick: ");
                loadJson();
            }
        });
    }
    public void loadJson()
    {
        Log.d("rxJava", "loadJson: ");
        Api api=RetroClass.getApi();

        Observable<List<Hero>> observable=api.getHeros().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<List<Hero>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Hero> value) {
                Log.d("rxJava", "onNext: ");

                String [] heroesNames=new String[value.size()];
                for(int i=0;i<value.size();i++)
                {
                    heroesNames[i]="Name: "+value.get(i).getName()+"\nReal Name: "+value.get(i).getRealname()+"\nFirst Appearance: "
                            +value.get(i).getFirstappearance()+"\nCreated By: "+value.get(i).getCreatedby()+"\nPublisher: "+value.get(i).getPublisher()
                            +"\nBio: "+value.get(i).getBio() ;


                }
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,heroesNames));

            }

            @Override
            public void onError(Throwable e) {
                Log.e("rxJava", "onError: "+e.getMessage() );

            }

            @Override
            public void onComplete() {

            }
        });
    }
}