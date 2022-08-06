package zoo.entities.animals;

import zoo.entities.foods.BaseFood;

public class TerrestrialAnimal extends BaseAnimal {
    //Can only live in LandArea!
    private final static double INITIAL_KG_TERRESTRIAL_ANIMAL = 5.5;

    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, INITIAL_KG_TERRESTRIAL_ANIMAL, price);
    }


    @Override
    public void eat() {
        double newKg = this.getKg() + 5.7;
        super.setKg(newKg);
    }
}
