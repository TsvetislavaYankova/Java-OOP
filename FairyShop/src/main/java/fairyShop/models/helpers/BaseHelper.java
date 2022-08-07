package fairyShop.models.helpers;

import fairyShop.common.ExceptionMessages;
import fairyShop.models.instruments.Instrument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseHelper implements Helper {

    private String name;
    private int energy;
    private Collection<Instrument> instruments = new ArrayList<>();

    public BaseHelper(String name, int energy) {
        if (name == null || name.equals("")) {
            throw new NullPointerException(ExceptionMessages.HELPER_NAME_NULL_OR_EMPTY);
        }
        if (energy < 0) {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_ENERGY_LESS_THAN_ZERO);
        }
        this.name = name;
        this.energy = energy;
    }

    public void work() {
        if (this.energy - 10 < 0) {
            this.energy = 0;
        } else {
            this.energy -= 10;
        }
    }

    public void addInstrument(Instrument instrument) {
        this.instruments.add(instrument);
    }

    @Override
    public boolean canWork() {
        if (this.energy > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return this.instruments;
    }

}
