package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {

    House house;
    Cat cat;
    Cat kitty;

    @Before
    public void setUp(){
        house = new House("Paradise", 1);
        cat = new Cat("Muki");
        kitty = new Cat("Puma");
    }

    @Test(expected = NullPointerException.class)
    public void testValidName(){
        House h = new House(null, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCapacity(){
        House h = new House("Versus", -2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCat(){
        house.addCat(cat);
        house.addCat(kitty);
    }

    @Test
    public void verifyAddedCat(){
        house.addCat(cat);
        Assert.assertEquals(1, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCat(){
        house.removeCat(kitty.getName());
    }

    @Test
    public void verifyRemovedCat(){
        house.addCat(kitty);
        house.removeCat(kitty.getName());
        Assert.assertEquals(0, house.getCount());
    }

    @Test
    public void testHungryCat(){
        kitty.setHungry(true);
        house.addCat(kitty);
        house.catForSale("Puma");
        Assert.assertFalse(kitty.isHungry());
    }

    @Test
    public void testStatistics(){
        house.addCat(kitty);
        Assert.assertEquals("The cat Puma is in the house Paradise!", house.statistics());
    }
}
