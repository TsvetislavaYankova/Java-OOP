package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PetStoreTests {

    PetStore store;
    Animal cat;
    Animal dog;
    Animal rabbit;

    @Before
    public void setUp() {
        store = new PetStore();
        cat = new Animal("angora", 9, 250);
        dog = new Animal("labrador", 25, 500);
        rabbit = new Animal("basic", 8, 35);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAnimalShouldThrow() {
        store.addAnimal(null);
    }

    @Test
    public void testAddAnimalSuccessfully(){
        store.addAnimal(cat);
        Assert.assertEquals(1, store.getCount());
        store.addAnimal(rabbit);
        Assert.assertEquals(2, store.getCount());
    }

    @Test
    public void findAnimalsWithMaxKilograms(){
        store.addAnimal(dog);
        store.addAnimal(dog);
        store.addAnimal(rabbit);
        Assert.assertEquals(2, store.findAllAnimalsWithMaxKilograms(20).size());
    }

    @Test
    public void testMostExpensiveAnimal() {
        store.addAnimal(dog);
        store.addAnimal(dog);
        store.addAnimal(rabbit);
        Assert.assertEquals(dog.getSpecie(), store.getTheMostExpensiveAnimal().getSpecie());
    }

    @Test
    public void findAnimalBySpecie() {
        store.addAnimal(dog);
        store.addAnimal(dog);
        store.addAnimal(rabbit);
        Assert.assertEquals(2, store.findAllAnimalBySpecie("labrador").size());
    }

}

