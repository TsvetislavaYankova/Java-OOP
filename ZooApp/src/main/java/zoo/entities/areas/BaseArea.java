package zoo.entities.areas;

import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseArea implements Area {

    private String name;
    private int capacity;

    private Collection<Food> foods;
    private Collection<Animal> animals;

    public BaseArea(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        foods = new ArrayList<>();
        animals = new ArrayList<>();
    }

    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    private void setName(String name) {
        if (name == null || name.trim().equals("")) {
            throw new NullPointerException(ExceptionMessages.AREA_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int sumCalories() {
        int sumCalories = 0;
        for (Food food : foods) {
            sumCalories += food.getCalories();
        }
        return sumCalories;
    }

    @Override
    public void addAnimal(Animal animal) {
        if (animals.size() == this.capacity) {
            throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY);
        }
        if (this.getClass().getSimpleName().equals("LandArea") && animal.getClass().getSimpleName().equals("AquaticAnimal")) {
            return;
        }
        if (this.getClass().getSimpleName().equals("WaterArea") && animal.getClass().getSimpleName().equals("TerrestrialAnimal")) {
            return;
        }
        this.animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }

    @Override
    public void feed() {
        for (Animal animal : this.animals) {
            animal.eat();
        }
    }

    @Override
    public String getInfo() {
        StringBuilder output = new StringBuilder()
                .append(String.format("%s (%s):", this.name, this.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append("Animals: ");

        if (this.animals.size() == 0) {
            output.append("none");
        } else {
            output.append(this.animals.stream().map(animal -> animal.getName()).collect(Collectors.joining(" ")));
        }

        output
                .append(System.lineSeparator())
                .append("Foods: ")
                .append(this.foods.size())
                .append(System.lineSeparator())
                .append("Calories: ")
                .append(this.sumCalories());

        return output.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return this.animals;
    }

    @Override
    public Collection<Food> getFoods() {
        return this.foods;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

}
