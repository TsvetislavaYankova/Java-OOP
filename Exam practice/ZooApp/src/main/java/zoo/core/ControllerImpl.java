package zoo.core;

import zoo.common.ConstantMessages;
import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {

    private FoodRepository foodRepository;
    private Collection<Area> areas;

    public ControllerImpl() {
        foodRepository = new FoodRepositoryImpl();
        areas = new ArrayList<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {
        if (!areaType.equals("LandArea") && !areaType.equals("WaterArea")) {
            throw new NullPointerException(ExceptionMessages.INVALID_AREA_TYPE);
        }
        if (areaType.equals("LandArea")) {
            areas.add(new LandArea(areaName));
        } else {
            areas.add(new WaterArea(areaName));
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
    }

    @Override
    public String buyFood(String foodType) {
        if (!foodType.equals("Vegetable") && !foodType.equals("Meat")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_FOOD_TYPE);
        }
        if (foodType.equals("Vegetable")) {
            foodRepository.add(new Vegetable());
        } else {
            foodRepository.add(new Meat());
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {

        if (foodRepository.findByType(foodType) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_FOOD_FOUND, foodType));
        }

        Area area = getAreaByName(areaName);

        switch (foodType) {
            case "Vegetable":
                area.addFood(new Vegetable());
                break;
            case "Meat":
                area.addFood(new Meat());
                break;
        }
        foodRepository.remove(foodRepository.findByType(foodType));
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {

        if (!animalType.equals("AquaticAnimal") && !animalType.equals("TerrestrialAnimal")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_ANIMAL_TYPE);
        }

        Area area = getAreaByName(areaName);

        if (area.getAnimals().size() == area.getCapacity()) {
            return ExceptionMessages.NOT_ENOUGH_CAPACITY;
        }
        if (area.getClass().getSimpleName().equals("LandArea") && animalType.equals("AquaticAnimal")) {
            return ConstantMessages.AREA_NOT_SUITABLE;
        }
        if (area.getClass().getSimpleName().equals("WaterArea") && animalType.equals("TerrestrialAnimal")) {
            return ConstantMessages.AREA_NOT_SUITABLE;
        }

        Animal animal = null;

        switch (animalType) {
            case "AquaticAnimal":
                animal = new AquaticAnimal(animalName, kind, price);
                area.addAnimal(animal);
                break;
            case "TerrestrialAnimal":
                animal = new TerrestrialAnimal(animalName, kind, price);
                area.addAnimal(animal);
                break;
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
    }

    @Override
    public String feedAnimal(String areaName) {
        Area area = getAreaByName(areaName);
        area.feed();
        return String.format(ConstantMessages.ANIMALS_FED, area.getAnimals().size());
    }

    @Override
    public String calculateKg(String areaName) {
        Area area = getAreaByName(areaName);
        double kg = area.getAnimals().stream().mapToDouble(Animal::getKg).sum();
        return String.format(ConstantMessages.KILOGRAMS_AREA, areaName, kg);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (Area area : this.areas) {
            sb.append(area.getInfo());
        }
        return sb.toString();
    }

    private Area getAreaByName( String areaName){
        Area area = areas.stream().filter(a -> a.getName().equals(areaName)).findFirst().get();
        return area;
    }
}
