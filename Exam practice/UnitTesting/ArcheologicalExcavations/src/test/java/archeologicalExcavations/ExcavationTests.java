package archeologicalExcavations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExcavationTests {

    Excavation excavation;
    Archaeologist arch1;
    Archaeologist arch2;

    @Before
    public void setUp(){
        excavation = new Excavation("Area", 1);
       arch1 = new Archaeologist("Zik", 5);
       arch2 = new Archaeologist("Vik", 6);
    }

    @Test(expected = NullPointerException.class)
    public void testValidName(){
        Excavation e = new Excavation(null, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCapacity(){
        Excavation e = new Excavation("Cabana", -2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArcheologist(){
        excavation.addArchaeologist(arch1);
        excavation.addArchaeologist(arch2);
    }

    @Test
    public void verifyAddedArcheologist(){
        excavation.addArchaeologist(arch1);
        Assert.assertEquals(1, excavation.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArcheologistForSecondTime(){
        excavation = new Excavation("Zona", 2);
        excavation.addArchaeologist(arch1);
        excavation.addArchaeologist(arch1);
    }

    @Test
    public void verifyRemovedArcheologist(){
        excavation.addArchaeologist(arch1);
        excavation.removeArchaeologist(arch1.getName());
        Assert.assertEquals(0, excavation.getCount());
    }
}
