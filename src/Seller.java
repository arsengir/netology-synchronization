public class Seller {

    private final Carshow carshow;
    private final int TIME_REGISTER_SALE = 1000;

    public Seller(Carshow carshow) {
        this.carshow = carshow;
    }

    public synchronized void acceptCar() {
        carshow.getCars().add(new Car());
        System.out.println(Thread.currentThread().getName() + " выпустил 1 авто");
        notify();
    }

    public synchronized Car sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (carshow.getCars().size() == 0) {
                System.out.println("Машин нет");
                wait();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Уважаемый " + Thread.currentThread().getName() + ". К сожалению автосалон закрывается.");
                    return null;
                }
            }
            carshow.incCountSales();
            Thread.sleep(TIME_REGISTER_SALE);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
        } catch (InterruptedException e) {
            System.out.println("Уважаемый " + Thread.currentThread().getName() + ". К сожалению автосалон закрывается.");
            return null;
        }
        return carshow.getCars().remove(0);
    }
}
