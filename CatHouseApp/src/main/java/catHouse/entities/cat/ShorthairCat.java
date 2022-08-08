package catHouse.entities.cat;

import catHouse.entities.cat.BaseCat;

public class ShorthairCat extends BaseCat {

    //Can only live in ShortHouse!

    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
        this.setKilograms(7);
    }

    @Override
    public void eating() {
        this.setKilograms(this.getKilograms() + 1);
    }
}
