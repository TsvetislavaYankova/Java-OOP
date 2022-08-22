package zoo.entities.areas;

public class LandArea extends BaseArea {
    private final static int DEFAULT_LAND_AREA_CAPACITY = 25;

    public LandArea(String name) {
        super(name, DEFAULT_LAND_AREA_CAPACITY);
    }
}
