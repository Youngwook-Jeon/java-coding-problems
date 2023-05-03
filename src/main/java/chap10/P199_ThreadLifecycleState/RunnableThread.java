package chap10.P199_ThreadLifecycleState;

public class RunnableThread {

    public void runnableThread() {
        Thread thread = new Thread(() -> {
        });
        // NEW에서 RUNNABLE로 바뀜. RUNNABLE에서 스레드는 실행 중이거나 실행 준비가 된 상태.
        // 실행 준비가 되면 JVM 스레드 스케줄러가 스레드 실행에 필요한 자원과 시간을 할당할 때까지 기다림.
        // 프로세서를 사용할 수 있으면 스케줄러는 즉시 스레드 실행.
        thread.start();

        // RUNNABLE
        System.out.println("RunnableThread: " + thread.getState());
    }

    public static void main(String[] args) {
        RunnableThread rt = new RunnableThread();
        rt.runnableThread();
    }
}
