package catHouse.entities.houses;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public abstract class BaseHouse implements House {

    private String name;
    private int capacity;
    private Collection<Toy> toys;
    private Collection<Cat> cats;

    public BaseHouse(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        toys = new ArrayList<>();
        cats = new ArrayList<>();
    }

    @Override
    public int sumSoftness() {
        return toys.stream().mapToInt(Toy::getSoftness).sum();
    }

    @Override
    public void addCat(Cat cat) {
        if (cats.size() == this.getCapacity()) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_CAT);
        }
        if (this.getClass().getSimpleName().equals("LongHouse") && cat.getClass().getSimpleName().equals("ShorthairCat")) {
            return;
        }
        if (this.getClass().getSimpleName().equals("ShortHouse") && cat.getClass().getSimpleName().equals("LonghairCat")) {
            return;
        }
        this.cats.add(cat);
    }

    @Override
    public void removeCat(Cat cat) {
        cats.remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {
        this.getToys().add(toy);
    }

    @Override
    public void feeding() {
        for (Cat cat : this.getCats()) {
            cat.eating();
        }
    }

    @Override
    public String getStatistics() {
        StringBuilder output = new StringBuilder()
                .append(String.format("%s %s:", this.name, this.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append("Cats: ");

        if (this.getCats().size() == 0) {
            output.append("none");
        } else {
            output.append(this.getCats().stream().map(cat -> cat.getName()).collect(Collectors.joining(" ")));
        }

        output
                .append(System.lineSeparator())
                .append("Toys: ")
                .append(this.getToys().size())
                .append(" Softness: ")
                .append(this.sumSoftness());

        return output.toString();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().equals("")) {
            throw new NullPointerException(ExceptionMessages.HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Cat> getCats() {
        return this.cats;
    }

    @Override
    public Collection<Toy> getToys() {
        return this.toys;
    }

    public int getCapacity() {
        return this.capacity;
    }

}
