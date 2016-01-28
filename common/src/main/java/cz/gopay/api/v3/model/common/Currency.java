package cz.gopay.api.v3.model.common;

import java.util.EnumSet;

/**
 *
 * @author Zbynek Novak | novak.zbynek@gmail.com
 *
 * splnuje ISO 4217
 *
 */
public enum Currency {
    CZK(203),
    EUR(978);

    public static final String CODE_CZK = String.valueOf(CZK);
    public static final String CODE_EUR = String.valueOf(EUR);

    private Integer numericalCode;

    private Currency(Integer numericalCode) {
        this.numericalCode = numericalCode;
    }

    public Integer getNumericalCode() {
        return numericalCode;
    }

    public String getCode() {
        return String.valueOf(this);
    }

    public void setNumericalCode(int numericalCode) {
        this.numericalCode = numericalCode;
    }

    public static Currency getByCode(String code) {
        Currency result = null;
        for (Currency item : EnumSet.allOf(Currency.class)) {
            if (String.valueOf(item).equals(code)) {
                result = item;
                break;
            }
        }
        return result;
    }

    public static Currency getByNumericalCode(Integer code) {
        Currency result = null;
        for (Currency item : EnumSet.allOf(Currency.class)) {
            if (item.getNumericalCode().equals(code)) {
                result = item;
                break;
            }
        }

        return result;
    }

}
