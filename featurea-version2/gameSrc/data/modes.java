package data;

import featurea.Mode;
import featurea.Mode.Shift;

public class modes {

    public static final Mode stop() {
        return new Mode(new Shift[]{new Shift(1000, values.stopvelocity, 0)}, 0, 0);
    }

    public static final Mode bursting() {
        return new Mode(new Shift[]{new Shift(1000, values.burstingvelocity, 0)}, 0, 0);
    }

    public static final Mode waitmode() {
        return new Mode(new Shift[]{new Shift(1000, values.waitvelocity, 0)}, 0, 0);
    }

    public static final Mode strike() {
        return new Mode(new Shift[]{new Shift(1000, values.strikevelocity, 0)}, 0, 0);
    }

    public static final Mode run() {
        return new Mode(new Shift[]{new Shift(6000, values.runvelocity, 0.000025f)}, 0, 0);
    }

    public static final Mode move() {
        return new Mode(new Shift[]{new Shift(1000, values.movevelocity, 0)}, 0, 0);
    }

    public static final Mode rollBack() {
        return new Mode(new Shift[]{new Shift(1000, values.rollbackvelocity, -0.008f)}, 0, 0);
    }
    
    public static final Mode levelFailed() {
        return new Mode(new Shift[]{new Shift(1000, 0.55, 0)}, 0, 0);
    }
}