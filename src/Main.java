public class Main {

    public static void main(String[] args) throws InterruptedException {

        final Carshow carshow = new Carshow();

        ThreadGroup mainGroup = new ThreadGroup("main group");
        Thread productionThread = new Thread(null, carshow::acceptCar, "Производитель Toyota");
        productionThread.start();

        new Thread(mainGroup, carshow::sellCar, "Покупатель 1").start();
        new Thread(mainGroup, carshow::sellCar, "Покупатель 2").start();
        new Thread(mainGroup, carshow::sellCar, "Покупатель 3").start();

        productionThread.join();
        mainGroup.interrupt();
    }
}
