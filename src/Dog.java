

/**
 *
 * @author Adam Whaley and
 */
public class Dog extends Pet {


    public Dog(String name, String breed, String sex, int age, double weight, int ID, double price) {
       super(name, breed, sex, age, weight, ID, price);
    }

    @Override
    public int compareTo(Pet other) {
        return Double.compare(getPrice(), other.getPrice());
    }


}
