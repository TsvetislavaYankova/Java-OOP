package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {

    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        toys = new ToyRepository();
        houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;
        switch (type) {
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            case "LongHouse":
                house = new LongHouse(name);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }
        houses.add(house);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        switch (type) {
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
        }
        toys.buyToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy t = toys.findFirst(toyType);
        if (t == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND, toyType));
        }
        House h = findHouseByName(houseName);
        h.buyToy(t);
        toys.removeToy(t);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }


    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;

        switch (catType) {
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }
        House house = findHouseByName(houseName);

        if (house.getClass().getSimpleName().equals("LongHouse") && cat.getClass().getSimpleName().equals("ShorthairCat")) {
            return ConstantMessages.UNSUITABLE_HOUSE;
        }
        if (house.getClass().getSimpleName().equals("ShortHouse") && cat.getClass().getSimpleName().equals("LonghairCat")) {
            return ConstantMessages.UNSUITABLE_HOUSE;
        }
        house.addCat(cat);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
    }

    @Override
    public String feedingCat(String houseName) {
        House h = findHouseByName(houseName);
        h.getCats().stream().forEach(Cat::eating);
        return String.format(ConstantMessages.FEEDING_CAT, h.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House h = findHouseByName(houseName);
        Double houseValue = h.getCats().stream().mapToDouble(Cat::getPrice).sum() +
                h.getToys().stream().mapToDouble(Toy::getPrice).sum();
        return String.format(ConstantMessages.VALUE_HOUSE, houseName, houseValue);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (House house : this.getHouses()) {
            sb.append(house.getStatistics())
                    .append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    private House findHouseByName(String houseName) {
        return this.getHouses().stream().filter(house -> house.getName().equals(houseName)).findFirst().orElse(null);
    }

    public ToyRepository getToys() {
        return toys;
    }

    public Collection<House> getHouses() {
        return houses;
    }
}
