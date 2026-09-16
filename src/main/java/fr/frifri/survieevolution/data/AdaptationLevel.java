package fr.frifri.survieevolution.data;

public enum AdaptationLevel {

    NONE(0),
    DETECTED(1),
    LIGHT(2),
    ADVANCED(3),
    STRONG(4),
    EXTREME(5);

    private final int value;

    AdaptationLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AdaptationLevel fromValue(int value) {
        for (AdaptationLevel level : values()) {
            if (level.value == value) {
                return level;
            }
        }

        return NONE;
    }
}