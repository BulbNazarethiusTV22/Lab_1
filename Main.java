import java.time.LocalTime;
import java.util.concurrent.Semaphore;

class ParkingLot {
    private Semaphore semaphore;
    private final int dayCount = 5;
    private final int nightCount = 8;
    
    public ParkingLot() {
        updateCapacity();
    }
    
    private void updateCapacity() {
        int hour = LocalTime.now().getHour();
        int capacity = (hour >= 6 && hour <= 20) ? dayCount : nightCount;
        semaphore = new Semaphore(capacity, true);
    }
    
    public void parkCar(String carName) {
        try{
            updateCapacity();
            System.out.printf("%s: waiting \n", carName);
            semaphore.acquire();
            System.out.printf("%s: taking place \n", carName);
            Thread.sleep((long) (Math.random() * 5000));
            System.out.printf("%s: leaving place \n", carName);
            semaphore.release();
        } catch (InterruptedException e){
            System.err.printf("%s: Parking is interrupted", carName);
            Thread.currentThread().interrupt();
        }
    }
}

class Car implements Runnable {
    private final String carName;
    private final ParkingLot parkingLot;
    
    public Car (String carName, ParkingLot parkingLot) {
        this.carName = carName;
        this.parkingLot = parkingLot;
    }
    
    @Override
    public void run() {
        parkingLot.parkCar(carName);
    }
}

class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        Thread [] cars = new Thread[15];
        
        for(int i = 0; i < cars.length; i++) {
            cars[i] = new Thread(new Car("Car " + (i+1), parkingLot));
            cars[i].start();
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e){
                System.err.println("Main thread is interrupted");
                Thread.currentThread().interrupt();
            }
        }
        
        for (Thread car : cars) {
            try {
                car.join();
            } catch (InterruptedException e) {
                System.err.println("Main thread is interrupted whihe waiting");
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Parking is completed");
    }
}
