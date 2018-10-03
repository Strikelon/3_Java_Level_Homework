package hmJava3.hm4;

public class hm4_1 {

    public static Object monitor = new Object();
    public static char turn = 'A';

    public static void main(String[] args){

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    try {
                        synchronized (monitor) {
                            while (turn != 'A') {
                                monitor.wait();
                            }
                            System.out.print("A");
                            turn = 'B';
                            monitor.notifyAll();
                        }
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    try {
                        synchronized (monitor) {
                            while (turn != 'B') {
                                monitor.wait();
                            }
                            System.out.print("B");
                            turn = 'C';
                            monitor.notifyAll();
                        }
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    try {
                        synchronized (monitor) {
                            while (turn != 'C') {
                                monitor.wait();
                            }
                            System.out.print("C");
                            turn = 'A';
                            monitor.notifyAll();
                        }
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();

    }
}
