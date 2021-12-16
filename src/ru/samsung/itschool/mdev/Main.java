package ru.samsung.itschool.mdev;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Процесс (программа)
        // Поток (Ветвь)
        // [+][-][+][-]
        TestThread thread1 = new TestThread("+");
        TestThread thread2 = new TestThread("-");
        thread1.start();
        thread2.start();
        Thread.sleep(500);
       //  thread1.stop(); <- НЕЛЬЗЯ!!! depricated!!!
        thread1.flag = false;
        thread1.join(); // ожидаем завершение поток
        test("First Stopped!");
    }

    public static Object key = new Object();
    public static void test(String message) {
      //  synchronized (key) {
            try {
                System.out.print("[");
                Thread.sleep(1000);
                System.out.print(message);
                Thread.sleep(1000);
                System.out.print("]");
               // key.notify(); // возобнови поток, который был в спячке
               // key.wait(); // поток попадает в режим ожидания (спячки)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        //}
    }
}
class TestThread extends Thread {
    private String m;
    public boolean flag = true;

    TestThread(String mess) {
        this.m = mess;
    }

    @Override
    public void run() {
        while(flag) {
            Main.test(this.m);
        }
    }
}
