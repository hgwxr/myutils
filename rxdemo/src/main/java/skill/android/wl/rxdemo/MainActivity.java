package skill.android.wl.rxdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button mButton;
    private TextView contentShow;
    private ProgressBar processBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = ((Button) findViewById(R.id.load_way));
        mButton.setOnClickListener(this);
        contentShow = ((TextView) findViewById(R.id.content_show));
        processBar = ((ProgressBar) findViewById(R.id.loading_process));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.load_way:
                /*
                  rx  线程切换
                 */
//                rxThread();

                /**
                 * 网络请求嵌套
                 */
//                requestNested();
                /**
                 * 接口合并(merge)
                 */
                final long[] start={0};
               Observable<List<String>> observable1=  createObsrvable1();
                Observable<List<String>> observable2=  createObsrvable2();
//                mergeObserable(start, observable1, observable2);
//                zipObserable(start[0], observable1, observable2);
                /**
                 * 构建多级缓存（concat）
                 */
//                Flowable
                break;
        }
    }

    private void zipObserable(final long l, Observable<List<String>> observable1, Observable<List<String>> observable2) {
        Observable.zip(observable1, observable2, new BiFunction<List<String>, List<String>, List<String>>() {
            @Override
            public List<String> apply(@NonNull List<String> strings, @NonNull List<String> strings2) throws Exception {
                strings.addAll(strings2);
                return strings;
            }
        }).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
                Log.e(TAG, "dialog  accept: "+Thread.currentThread().getName()+"   "+ (System.currentTimeMillis()- l));
                processBar.setVisibility(View.VISIBLE);
                contentShow.setVisibility(View.GONE);
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(@NonNull List<String> strings) throws Exception {
                        contentShow.setText("token  is  :" + strings.toString());
                        Log.e(TAG, "content  accept: " + Thread.currentThread().getName() + "   " + (System.currentTimeMillis() - l));
                        processBar.setVisibility(View.GONE);
                        contentShow.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void mergeObserable(final long[] start, Observable<List<String>> observable1, Observable<List<String>> observable2) {
        Observable.merge(observable1,observable2)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                       start[0]=System.currentTimeMillis();
                        Log.e(TAG, "dialog  accept: "+Thread.currentThread().getName()+"   "+ (System.currentTimeMillis()-start[0]));
                        processBar.setVisibility(View.VISIBLE);
                        contentShow.setVisibility(View.GONE);
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(@NonNull List<String> strings) throws Exception {
                        contentShow.setText(strings.toString());
                        Log.e(TAG, "accept  accept: " + Thread.currentThread().getName() + "   " + (System.currentTimeMillis() - start[0]));
                        processBar.setVisibility(View.GONE);
                        contentShow.setVisibility(View.VISIBLE);
                    }
                });
    }

    private Observable<List<String>> createObsrvable2() {
        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<String>> observableEmitter) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    list.add("data2 数据 "+ i);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "subscribe 2  accept: " + Thread.currentThread().getName() + "   " + (System.currentTimeMillis() ));

                observableEmitter.onNext(list);
                observableEmitter.onComplete();
            }
        });
    }

    private Observable<List<String>> createObsrvable1() {
        return Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<String>> observableEmitter) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    list.add("data 1 数据 "+ i);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "subscribe 1 accept: " + Thread.currentThread().getName() + "   " + (System.currentTimeMillis() ));
                observableEmitter.onNext(list);
                observableEmitter.onComplete();
            }
        });
    }

    private void requestNested() {
        final long[] start = {0};
        Observable.create(new ObservableOnSubscribe<Double>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Double> observableEmitter) throws Exception {
                long l = System.currentTimeMillis();
                start[0] =l;
                Log.e(TAG, "subscribe: "+Thread.currentThread().getName()+"   "+ l);
                Thread.sleep(1000);
                double random = Math.random();
                observableEmitter.onNext(random);
                observableEmitter.onComplete();
            }
        }).flatMap(new Function<Double, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull final Double aDouble) throws Exception {

                return Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> observableEmitter) throws Exception {
                         Thread.sleep(1000);
                                  if (aDouble<0.5){
                                      observableEmitter.onError(new Throwable("erroe  msg"));
                                  }else {
                                      String token = String.valueOf(aDouble) + " token";
                                      observableEmitter.onNext(token);
                                      Log.e(TAG, "subscribe: " + Thread.currentThread().getName() + "   " + (System.currentTimeMillis() - start[0]));
                                      observableEmitter.onComplete();
                                  }
                    }
                });
            }
        }) .subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
                Log.e(TAG, "dialog  accept: "+Thread.currentThread().getName()+"   "+ (System.currentTimeMillis()-start[0]));
                processBar.setVisibility(View.VISIBLE);
                contentShow.setVisibility(View.GONE);
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        contentShow.setText("token  is  :" + s);
                        Log.e(TAG, "content  accept: " + Thread.currentThread().getName() + "   " + (System.currentTimeMillis() - start[0]));
                        processBar.setVisibility(View.GONE);
                        contentShow.setVisibility(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "error  accept: " + Thread.currentThread().getName() + "   " + (System.currentTimeMillis() - start[0]));
                        processBar.setVisibility(View.GONE);
                        contentShow.setVisibility(View.VISIBLE);
                        contentShow.setText("token  is  :" + throwable.getMessage());
                    }
                });
    }

    private void rxThread() {
        final long[] start = {0};
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<String>> observableEmitter) throws Exception {
                long l = System.currentTimeMillis();
                start[0] =l;
                Log.e(TAG, "subscribe: "+Thread.currentThread().getName()+"   "+ l);
                List<String> lists=  initList();
                Thread.sleep(1000);
                observableEmitter.onNext(lists);
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(@NonNull List<String> strings) throws Exception {
                        contentShow.setText(strings.toString());
                        Log.e(TAG, "accept: "+Thread.currentThread().getName()+"   "+ (System.currentTimeMillis()-start[0]));
                    }
                });
    }

    private List<String> initList() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            strings.add("数据"+i);
        }
        return strings;
    }
}
