package fairyShop.models.instruments;

import fairyShop.common.ExceptionMessages;

public class InstrumentImpl implements Instrument {

    private int power;

    public InstrumentImpl(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void use() {
        if (this.power - 10 < 0) {
            this.power = 0;
        } else {
            this.power -= 10;
        }
    }

    @Override
    public boolean isBroken() {
        if(this.power == 0){
            return true;
        }
        return false;
    }
}
