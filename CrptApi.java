package org.example;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CrptApi {


    private TimeUnit timeUnit;
    private final int requestLimit;
    private final Semaphore semaphore;

    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        this.timeUnit = timeUnit;
        this.requestLimit = requestLimit;
        this.semaphore = new Semaphore(requestLimit);
    }

    public void createDocument(Object document, String signature) {
        try {
            semaphore.acquire();

            //Выполните запрос API для создания документа

            // Имитировать процесс создания документа
            Thread.sleep(timeUnit.toMillis(1));

            System.out.println(document.toString());
            System.out.println(signature);

            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        CrptApi crptApi = new CrptApi(TimeUnit.SECONDS, 5);

        for (int i = 0; i < 10; i++) {
            int number = i + 1;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    crptApi.createDocument("Document " + number, "Signature " + number);
                }
            });
            thread.start();
        }
    }

}