package cz.gopay.api.v3.model.payment.support;

import java.util.EnumSet;

/**
 *
 * Enumerates recurrence cycle for recurrent payment
 *
 * @author Zbynek Novak | novak.zbynek@gmail.com
 *
 */
public enum RecurrenceCycle {
    MONTH("MONTH"),
    WEEK("WEEK"),
    DAY("DAY"),
    ON_DEMAND("ON_DEMAND");

    final private String name;

    private RecurrenceCycle(String name) {
        this.name = name;
    }

    public static RecurrenceCycle getByName(String name) {
        RecurrenceCycle result = null;
        for (RecurrenceCycle cycle : EnumSet.allOf(RecurrenceCycle.class)) {
            if (cycle.getName().equals(name)) {
                result = cycle;
                break;
            }
        }
        return result;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
