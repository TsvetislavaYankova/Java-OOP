package zoo.entities.animals;

public class AquaticAnimal extends BaseAnimal {
    //Can only live in WaterArea!
    private final static double INITIAL_KG_AQUATIC_ANIMAL = 2.5;

    public AquaticAnimal(String name, String kind, double price) {
        super(name, kind, INITIAL_KG_AQUATIC_ANIMAL, price);
    }

    @Override
    public void eat() {
        double newKg = this.getKg() + 7.5;
        super.setKg(newKg);
    }
}
