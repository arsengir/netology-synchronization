import java.util.ArrayList;
import java.util.List;

public class Carshow {
    private final int VOLUME_SALES = 10;
    private final int PRODUCTION_CAR = 2000;
    private final int TIMEOUT_SALES = 3000;

    private final Seller seller = new Seller(this);
    private List<Car> cars = new ArrayList<>();
    private int countSales = 0;

    public void sellCar() {
        while (!Thread.currentThread().isInterrupted()) {
            if (countSales == VOLUME_SALES) {
                break;
            }
            seller.sellCar();
            try {
                Thread.sleep(TIMEOUT_SALES);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void acceptCar() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(PRODUCTION_CAR);
            } catch (InterruptedException e) {
                break;
            }
            if (countSales == VOLUME_SALES) {
                break;
            }
            seller.acceptCar();
        }
    }

    List<Car> getCars() {
        return cars;
    }

    public void incCountSales() {
        countSales++;
    }
}
