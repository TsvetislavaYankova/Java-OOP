package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GiftFactoryTests {

    private GiftFactory factory;

    @Before
    public void setup() {
        factory = new GiftFactory();
    }

    @Test
    public void testGetCount() {
        int count = factory.getCount();
        Assert.assertEquals(factory.getPresents().size(), count);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateGiftException() {
        factory.createGift(new Gift("bear", 12));
        factory.createGift(new Gift("bear", 12));
    }

    @Test
    public void testCreateGift() {
        factory.createGift(new Gift("ball", 12.56));
        StringBuilder text = new StringBuilder();

        text.append("Successfully added gift ");
        text.append(factory.getPresent("ball").getType());
        text.append(" with magic ");
        text.append(factory.getPresent("ball").getMagic());
        text.append(".");

        Assert.assertEquals("Successfully added gift ball with magic 12.56.", text.toString());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveGiftException() {
        factory.removeGift(" ");
    }

    @Test
    public void testRemoveGift() {
        factory.createGift(new Gift("kite", 12));
        factory.removeGift("kite");
        boolean temp = false;
        for (Gift g : factory.getPresents()) {
            if (g.getType().equals("kite")) {
                temp = true;
            }
        }
        Assert.assertEquals(false, temp);
    }

    @Test
    public void getPresentWithLeastMagic() {
        factory.createGift(new Gift("wolf", 12));
        factory.createGift(new Gift("rabbit", 11));
        Assert.assertEquals(factory.getPresent("rabbit"), factory.getPresentWithLeastMagic());
    }
}
