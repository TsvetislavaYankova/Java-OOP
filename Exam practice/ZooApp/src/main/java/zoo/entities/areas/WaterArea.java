package zoo.entities.areas;

public class WaterArea extends BaseArea {
    private final static int DEFAULT_WATER_AREA_CAPACITY = 10;

    public WaterArea(String name) {
        super(name, DEFAULT_WATER_AREA_CAPACITY);
    }
}
