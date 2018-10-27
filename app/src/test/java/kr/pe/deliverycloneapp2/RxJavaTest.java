package kr.pe.deliverycloneapp2;

import io.reactivex.Observable;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RxJavaTest {
    String result = "";
    @Test
    public void returnAValue(){
        result = "";
        Observable<String> observer = Observable.just("Hello"); // provides datea
        observer.subscribe(s -> result=s); // Callable as subscriber
        assertTrue(result.equals("Hello"));
    }
}
