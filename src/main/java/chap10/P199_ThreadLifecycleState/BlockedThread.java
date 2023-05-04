package chap10.P199_ThreadLifecycleState;

// 스레드는 IO 작업이나 동기화 블록을 실행할 때 BLOCKED 상태로 들어감
public class BlockedThread {

    public void blockedThread() {
        Thread t1 = new Thread(new SyncCode());
        Thread t2 = new Thread(new SyncCode());

        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        t2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        System.out.println("BlockedThread t1: " + t1.getState() + "(" + t1.getName() + ")");
        System.out.println("BlockedThread t2: " + t2.getState() + "(" + t2.getName() + ")");

        System.exit(0);
    }

    private static class SyncCode implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread " + Thread.currentThread().getName() + " is in run() method");
            syncMethod();
        }

        public static synchronized void syncMethod() {
            System.out.println("Thread " + Thread.currentThread().getName() + " is in syncMethod() method");
            while (true) {
                // t1이 먼저 여기에 머물게 됨으로 t2는 블로킹됨.
            }
        }
    }

    public static void main(String[] args) {
        BlockedThread blockedThread = new BlockedThread();
        blockedThread.blockedThread();
    }
}
