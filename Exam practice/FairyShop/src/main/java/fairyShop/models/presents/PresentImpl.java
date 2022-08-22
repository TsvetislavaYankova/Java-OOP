package fairyShop.models.presents;

import fairyShop.common.ExceptionMessages;

public class PresentImpl implements Present {

    private String name;
    private int energyRequired;

    public PresentImpl(String name, int energyRequired) {
        if (name == null || name.trim().length() < 1) {
            throw new NullPointerException(ExceptionMessages.PRESENT_NAME_NULL_OR_EMPTY);
        }
        if (energyRequired < 0) {
            throw new IllegalArgumentException(ExceptionMessages.PRESENT_ENERGY_LESS_THAN_ZERO);
        }
        this.name = name;
        this.energyRequired = energyRequired;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergyRequired() {
        return this.energyRequired;
    }

    @Override
    public boolean isDone() {
        if (this.energyRequired == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void getCrafted() {
        if (this.energyRequired - 10 < 0) {
            this.energyRequired = 0;
        } else {
            this.energyRequired -= 10;
        }
    }
}
