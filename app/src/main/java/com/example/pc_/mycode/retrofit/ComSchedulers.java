package com.example.pc_.mycode.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pc- on 2017/5/23.
 */
public class ComSchedulers {



    public static ComSchedulers newInstance(){
        return new ComSchedulers();
    }



    private final static Observable.Transformer ioTransformer = new Observable.Transformer() {
        @Override
        public Object call(Object o) {
            return ((Observable) o).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static <T> Observable.Transformer<T, T> applyIoSchedulers() {
        return (Observable.Transformer<T, T>) ioTransformer;
    }

//    public   Observable.Transformer    applyIoSchedulers() {
//        return  new Observable.Transformer() {
//            @Override
//            public Object call(Object o) {
//                return ((Observable) o).subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }


}
