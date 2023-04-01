



/**
 *
 * @author Adam Whaley and
 */

public class ExoticPet extends Pet {


    private String species;


    public ExoticPet(String name, String species, String sex, int age, double weight, int ID, double price) {
        super(name, species, sex, age, weight, ID, price);
    }

    @Override
    public int compareTo(Pet other) {
        return Double.compare(getPrice(), other.getPrice());

    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
