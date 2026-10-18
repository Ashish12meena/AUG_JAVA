class Engine {
    String type;

    Engine(String type) {
        this.type = type;
    }

    void start() {
        System.out.println("Engine started: " + type);
    }
}

class Vehicle {
    String brand;

    Vehicle(String brand) {
        this.brand = brand;
    }

    void move() {
        System.out.println(brand + " is moving.");
    }
}

class Car extends Vehicle {
    Engine engine;

    Car(String brand, Engine engine) {
        super(brand);
        this.engine = engine;
    }

    void startCar() {
        System.out.println("Car brand: " + brand);
        engine.start();
        move();
    }
}

public class CarDemo {
    public static void main(String[] args) {
        Engine myEngine = new Engine("Diesel");
        Engine petrolEngine = new Engine("Petrol");
        Car myCar = new Car("Toyota", myEngine);
        myCar.startCar();
    }
}
