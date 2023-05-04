package chap10.P199_ThreadLifecycleState;

// 스레드의 타임아웃 주기 없이 기다리는 WAITING 상태의 예
public class WaitingThread {

    private static final Thread t1 = new CodeT1();

    public void waitingThread() {
        t1.start();
    }

    private static class CodeT1 extends Thread {

        @Override
        public void run() {
            Thread t2 = new Thread(new CodeT2());
            t2.start();

            try {
                t2.join();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static class CodeT2 implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            System.out.println("WaitingThread t1: " + t1.getState() + " \n");
        }
    }

    public static void main(String[] args) {
        WaitingThread waitingThread = new WaitingThread();
        waitingThread.waitingThread();
    }
}
