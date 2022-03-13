package com.hotel.booking.reactiveJava;


import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.List;

public class reactiveJava {
    static Logger logger = LoggerFactory.getLogger(reactiveJava.class);
    public static void createObservableWithJust()
    {
        Observable<Integer> observable=Observable.just(1,2,3,4,5);
        observable.subscribe(item-> logger.info(String.valueOf(item)));
    }
    public static void createObservableWithFromIterable()
    {
        List<Integer> list= Arrays.asList(6,7,8,9,10);
        Observable<Integer> observable=Observable.fromIterable(list);
        observable.subscribe(item->  logger.info(String.valueOf(item)));
    }
    public static void creatObservableUsingCreate()
    {
        Observable<Integer> observable=Observable.create(emitter -> {
            emitter.onNext(11);
            emitter.onNext(12);
            emitter.onNext(13);
            emitter.onNext(14);
            emitter.onNext(null);//stop here
            emitter.onNext(15);
            //emitter.onNext(null);
            emitter.onComplete();

        });
        observable.subscribe(item->  logger.info(String.valueOf(item)),error-> logger.error(error.getMessage()),
                ()->logger.info("Completed"));
    }
    public static void main(String[] args) {
        createObservableWithJust();
        createObservableWithFromIterable();
        creatObservableUsingCreate();
    }
}
