import java.util.*;

enum Car_type
{
    C100,
    C200,
    C300,
    C400;
}

class Car
{
    Car_type type;
    int number;

    public double getMillage() {
        return millage;
    }

    double millage;

    public int getParam() {
        return param;
    }

    int param;

    static String get_type_name(Car_type ct)
    {
        return switch(ct) {
            case C100 -> "Легковой автомобиль";
            case C200 -> "Грузовой автомобиль";
            case C300 -> "Пассажирский транспорт";
            case C400 -> "Краны";
        };
    }

    static double get_fuel_cost(Car_type ct)
    {
        return switch (ct) {
            case C100 -> 46.1;
            case C200, C400 -> 48.9;
            case C300 -> 47.5;
        };
    }

    static double get_fuel_consumption(Car_type ct){
        return switch (ct){
            case C100 -> 12.5;
            case C200 -> 12;
            case C300 -> 11.5;
            case C400 -> 20;
        };
    }

    static double get_final_cost(Car_type ct)
    {
        return (get_fuel_cost(ct) * get_fuel_consumption(ct))/100.0;
    }
}

public class Main
{
    public static void printing_cars(List<Car> cars)
    {
        Collections.sort(cars,Comparator.comparing(Car::getMillage).thenComparing((Car::getParam)));
        for (int i = 0; i<cars.size(); i++){
            System.out.println(" Тип: " + Car.get_type_name(cars.get(i).type) + " номер: "  + cars.get(i).number
                    + " пробег: " + cars.get(i).millage + " дополнительный параметр: "  + cars.get(i).param);
        }
    }
    public static void main(String[] args)
    {
        String input_cars[] = new String[]{"C100_1-100", "C200_1-120-1200", "C300_1-120-30", "C400_1-80-20", "C100_2-50", "C200_2-40-1000", "C300_2-200-45", "C400_2-10-20", "C100_3-10", "C200_3-170-1100", "C300_3-150-29", "C400_3-100-28", "C100_1-300", "C200_1-100-750", "C300_1-32-15"};

        double с100costs = 0;
        double с200costs = 0;
        double с300costs = 0;
        double с400costs = 0;
        List<Car> c100cars = new ArrayList<>();
        List<Car> c200cars = new ArrayList<>();
        List<Car> c300cars = new ArrayList<>();
        List<Car> c400cars = new ArrayList<>();
        for (int i = 0; i < input_cars.length; i++)
        {
            Car car = new Car();
            String[] type = input_cars[i].split("_");
            car.type = Car_type.valueOf(type[0]);
            String[] number = type[1].split("-");
            car.number = Integer.parseInt(number[0]);
            car.millage = Integer.parseInt(number[1]);
            try
            {
                car.param = Integer.parseInt(number[2]);
            }
            catch (Exception ex)
            { }
            double cost = Car.get_final_cost(car.type) * car.millage;
            switch (car.type) {
                case C100:
                    с100costs += cost;
                    c100cars.add(car);
                    break;
                case C200:
                    с200costs += cost;
                    c200cars.add(car);
                    break;
                case C300:
                    с300costs += cost;
                    c300cars.add(car);
                    break;
                case C400:
                    с400costs += cost;
                    c400cars.add(car);
                    break;
            }
        }
        printing_cars(c100cars);
        printing_cars(c200cars);
        printing_cars(c300cars);
        printing_cars(c400cars);
        HashMap<Car_type,Double> costs =  new HashMap<>();
        costs.put(Car_type.C100,с100costs);
        costs.put(Car_type.C200,с200costs);
        costs.put(Car_type.C300,с300costs);
        costs.put(Car_type.C400,с400costs);
        double max = Collections.max(costs.values());
        double min = Collections.min(costs.values());
        double allcosts = с100costs+с200costs+с300costs+с400costs;
        System.out.println("Общая стоимость обслуживания легковых автомобилей " +  с100costs);
        System.out.println("Общая стоимость обслуживания грузовых автомобилей " +  с200costs);
        System.out.println("Общая стоимость обслуживания пассажирских автомобилей " +  с300costs);
        System.out.println("Общая стоимость обслуживания кранов  " +  с400costs);
        System.out.println("Общая стоимость обслуживания   " +  allcosts);
        for (Map.Entry<Car_type,Double> entry : costs.entrySet()){
            if (entry.getValue().equals(max)){
                System.out.println("Максимальная стоимость обслуживания у типа" + " " + Car.get_type_name(entry.getKey()) +  " " + max);
            }
        }
        for (Map.Entry<Car_type,Double> entry : costs.entrySet()) {
            if (entry.getValue().equals(min)) {
                System.out.println("Минимальная стоимость обслуживания у типа" + " " + Car.get_type_name(entry.getKey()) + " " + min);
            }
        }
    }
}
