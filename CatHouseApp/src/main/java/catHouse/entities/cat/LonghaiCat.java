package catHouse.entities.cat;

public class LonghaiCat extends BaseCat{

    //Can only live in LongHouse!

    public LonghaiCat(String name, String breed, double price) {
        super(name, breed, price);
        this.setKilograms(9);
    }

    @Override
    public void eating() {
    this.setKilograms(this.getKilograms() + 3);
    }
}
