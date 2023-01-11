package cz.gopay.api.v3.model.common;

import java.util.EnumSet;

/**
 *
 * @author Zbynek Novak | novak.zbynek@gmail.com
 * @author Pavel Valenta (pavel.valenta@gopay.cz) - pouze doplnen enum o PLN, HUF, USD, GBP
 *
 * splnuje ISO 4217
 *
 */
public enum Currency {
    CZK(203),
    EUR(978),
    PLN(985),
    HUF(348),
    USD(840),
    GBP(826),
    BGN(975),
    RON(946);
        
    public static final String CODE_CZK = String.valueOf(CZK);
    public static final String CODE_EUR = String.valueOf(EUR);
    public static final String CODE_PLN = String.valueOf(PLN);
    public static final String CODE_HUF = String.valueOf(HUF);
    public static final String CODE_USD = String.valueOf(USD);
    public static final String CODE_GBP = String.valueOf(GBP);
    public static final String CODE_BGN = String.valueOf(BGN);
    public static final String CODE_RON = String.valueOf(RON);
    
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
