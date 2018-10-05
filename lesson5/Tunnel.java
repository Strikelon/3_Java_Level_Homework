package hmJava3.hm5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private int widthTunnel;
    private Semaphore widthController;

    public Tunnel(int widthTunnel) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.widthTunnel=widthTunnel;
        this.widthController = new Semaphore(this.widthTunnel);
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                widthController.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                widthController.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
