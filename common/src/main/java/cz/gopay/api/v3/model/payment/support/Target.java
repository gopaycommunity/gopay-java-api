package cz.gopay.api.v3.model.payment.support;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Target {

    public enum TargetType {
        ACCOUNT,
        BANK_ACCOUNT,
        COUPON;
    }

    @XmlElement(name = "type")
    private TargetType type;

    @XmlElement(name = "goid")
    private Long goId;

    public Long getGoId() {
        return goId;
    }

    public void setGoId(Long goId) {
        this.goId = goId;
    }

    public TargetType getType() {
        return type;
    }

    public void setType(TargetType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("TargetParty [type=%s, goId=%s]", type, goId);
    }

    public static Target create() {
        return new Target();
    }
    
    public static Target createEWallet(String email) {
        Target created = create();
        created.type = TargetType.ACCOUNT;
        return created;
    }

    public static Target createEShop(Long goID) {
        Target created = create();
        created.eshop(goID);
        return created;
    }
    
    public Target ewallet(String email) {
        this.type = TargetType.ACCOUNT;
        return this;
    }

    public Target eshop(Long goId) {
        this.type = TargetType.ACCOUNT;
        this.goId = goId;
        return this;
    }

}
