import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    // Возвращаем количество процессоров, доступных JVM
    public static final int NUM_OF_THREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        Counter counter = new Counter();
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);

        switch (menu()) {
            case 1:
                useSubmit(counter, executorService);
                break;
            case 2:
                useInvokeAny(counter, executorService);
                break;
            case 3:
                useInvokeAll(counter, executorService);
                break;
        }
    }

    public static int menu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Menu:\n" +
                "1 - Use submit()\n" +
                "2 - Use invokeAll()\n" +
                "3 - Use invokeAny()\n" +
                "Select an option (number 1, 2 or 3): ");
        return input.nextInt();
    }

    public static List<MyThread> fillTaskList(Counter counter) {
        List<MyThread> taskList = new ArrayList<>();
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            taskList.add(new MyThread(counter));
        }
        return taskList;
    }

    public static void useInvokeAll(Counter counter, ExecutorService executorService) {
        List<MyThread> taskList = fillTaskList(counter);
        List<Future<String>> resultList = null;

        try {
            resultList = executorService.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        printResult(resultList);
    }

    public static void useInvokeAny(Counter counter, ExecutorService executorService) {
        List<MyThread> taskList = fillTaskList(counter);
        String resultList = null;

        try {
            resultList = executorService.invokeAny(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println(resultList);
    }

    public static void useSubmit(Counter counter, ExecutorService executorService) {

        List<Future<String>> resultList = new ArrayList<>();

        for (int i = 0; i < NUM_OF_THREADS; i++)
            resultList.add(executorService.submit(new MyThread(counter)));

        executorService.shutdown();
        printResult(resultList);
    }

    public static void printResult(List<Future<String>> resultList) {
        for (Future<String> res : resultList) {
            try {
                System.out.println(res.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
