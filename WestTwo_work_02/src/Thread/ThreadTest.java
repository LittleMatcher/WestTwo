package Thread;

import java.util.concurrent.locks.LockSupport;

public class ThreadTest {
    static Thread thread1=null,thread2=null;
    public static void main(String[] args) {
        int []arr1=new int[]{1,3,5,7,9,11,13,15,17,19};
        int []arr2=new int[]{2,4,6,8,10,12,14,16,18,20};
        new ThreadTest().printArr(arr1,arr2);
    }
    public synchronized void printArr(int[] arr1, int[] arr2)
    {
         thread_01 t1=new thread_01(arr1);
         thread1 = new Thread(t1,"线程1");
         thread_02 t2=new thread_02(arr2);
         thread2 = new Thread(t2,"线程2");
         thread1.start();
         thread2.start();
    }

class thread_01 implements Runnable {

    int arr1[];
    public thread_01(int[] arr1) {
        this.arr1=arr1;
    }

    @Override
    public void run() {
        for (int i : arr1) {
            System.out.println(thread1.getName() + " " + i + " ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(thread2);
            LockSupport.park();
        }
    }
}
class thread_02 implements Runnable{
    int []arr2;
    public thread_02(int[] arr2) {
        this.arr2=arr2;
    }

    @Override
    public void run() {
        for(int i : arr2){
            LockSupport.park();
            System.out.println(thread2.getName()+" "+i + " ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(thread1);
        }
    }
}
}
