package hmJava3.hm5;

import java.util.concurrent.CountDownLatch;

public class MainClass {

    public static final int CARS_COUNT = 4;
    public static CountDownLatch carsReady = new CountDownLatch(CARS_COUNT);
    public static CountDownLatch startController = new CountDownLatch(1);
    public static CountDownLatch finishController = new CountDownLatch(CARS_COUNT);

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT/2), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10),carsReady,startController,finishController);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            carsReady.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        startController.countDown();
        try {
            finishController.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
