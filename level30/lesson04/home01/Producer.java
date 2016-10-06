package com.javarush.test.level30.lesson04.home01;


import java.util.concurrent.TransferQueue;

public class Producer implements Runnable {

    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run() {

        for (int N = 1; N <= 9; N++) {
            if (Thread.currentThread().isInterrupted()) return;
            System.out.format("Элемент 'ShareItem-%d' добавлен %n", N);
            queue.offer(new ShareItem("ShareItem-" + N, N));
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException ignore) {

            }
            if (queue.hasWaitingConsumer()) {
                System.out.println("Consumer в ожидании!");
            }

        }

    }
}
