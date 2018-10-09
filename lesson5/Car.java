package hmJava3.hm5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class Car implements Runnable{
    private static int CARS_COUNT;
    private volatile static AtomicBoolean isWinner = new AtomicBoolean(false);
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private CountDownLatch carsReady;
    private CountDownLatch startController;
    private CountDownLatch finishController;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CountDownLatch carsReady, CountDownLatch startController, CountDownLatch finishController) {
        this.race = race;
        this.speed = speed;
        this.carsReady = carsReady;
        this.startController = startController;
        this.finishController = finishController;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            carsReady.countDown();
            startController.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if(isWinner.compareAndSet(false,true)){
            System.out.println(this.name + " - WIN");
        }
        finishController.countDown();
    }
}
