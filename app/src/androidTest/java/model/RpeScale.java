package model;

import java.security.InvalidParameterException;

public enum RpeScale {
    SIX_HALF(6.5),
    SEVEN(7),
    SEVEN_HALF(7.5),
    EIGHT(8),
    EIGHT_HALF(8.5),
    NINE(9),
    NINE_HALF(9.5),
    TEN(10);

    private final double value;

    RpeScale(double v) {
        this.value = v;
    }

    public double value() {
        return value;
    }

    public static RpeScale getRpe(double v) {
        switch ((int) (v * 2)) {
            case 13:
                return SIX_HALF;
            case 14:
                return SEVEN;
            case 15:
                return SEVEN_HALF;
            case 16:
                return EIGHT;
            case 17:
                return EIGHT_HALF;
            case 18:
                return NINE;
            case 19:
                return NINE_HALF;
            case 20:
                return TEN;
            default:
                throw new InvalidParameterException();
        }
    }
}
