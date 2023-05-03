package chap10.P199_ThreadLifecycleState;

public class NewThread {

    public void newThread() {
        Thread thread = new Thread(() -> {
        });
        // start() 메서드를 호출하기 전까지 NEW 상태가 변하지 않음
        System.out.println("NewThread: " + thread.getState()); // NEW
    }

    public static void main(String[] args) {
        NewThread nt = new NewThread();
        nt.newThread();
    }
}
