import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyThread implements Callable<String> {
    private Counter counter;

    public MyThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public String call() throws Exception {
        return "I`m " + Thread.currentThread().getName() + ". Hello!" + ". Number of messages: " + counter.getCount();
    }
}
