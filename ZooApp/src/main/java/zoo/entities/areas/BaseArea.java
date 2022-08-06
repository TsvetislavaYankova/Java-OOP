package zoo.entities.areas;

import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseArea implements Area {

    private String name;
    private int capacity;

    private int currentNumberOfAnimals;
    private Collection<Food> foods;
    private Collection<Animal> animals;

    public BaseArea(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        foods = new ArrayList<>();
        animals = new ArrayList<>();
        currentNumberOfAnimals = 0;
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
        if (this.currentNumberOfAnimals >= this.capacity) {
            throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY);
        }
        if (this.getClass().getSimpleName().equals("LandArea") && animal.getClass().getSimpleName().equals("AquaticAnimal")) {
            return;
        }
        if (this.getClass().getSimpleName().equals("WaterArea") && animal.getClass().getSimpleName().equals("TerrestrialAnimal")) {
            return;
        }
        this.animals.add(animal);
        currentNumberOfAnimals++;
    }

    @Override
    public void removeAnimal(Animal animal) {
        if (this.animals.remove(animal)) {
            this.animals.remove(animal);
            currentNumberOfAnimals--;
        }
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
        String desiredString;
        StringBuilder animalNames = new StringBuilder();
        animals.forEach(animal -> animalNames.append(animal.getName()).append(" ").deleteCharAt(animalNames.length() - 1));
        if (this.animals.size() == 0) {
            desiredString = String.format("%s (%s):", this.name, this.getClass().getSimpleName()) + System.lineSeparator() +
                    "Animals: none" + System.lineSeparator() +
                    String.format("Foods: %d", this.foods.size()) + System.lineSeparator() +
                    String.format("Calories: %d", this.sumCalories());
        } else {
            desiredString = String.format("%s (%s):", this.name, this.getClass().getSimpleName()) + System.lineSeparator() +
                    "Animals: " + animalNames.toString() + System.lineSeparator() +
                    String.format("Foods: %d", this.foods.size()) + System.lineSeparator() +
                    String.format("Calories: %d", this.sumCalories());
        }
        return desiredString;
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


}
