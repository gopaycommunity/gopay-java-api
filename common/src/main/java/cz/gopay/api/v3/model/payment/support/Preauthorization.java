package cz.gopay.api.v3.model.payment.support;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Preauthorization {

    public enum PreAuthState {
        REQUESTED,
        AUTHORIZED,
        CAPTURED,
        CANCELED
    }

    @XmlElement(name = "requested")
    private Boolean requested;

    @XmlElement(name = "state")
    private PreAuthState preAuthState;

    public Boolean getRequested() {
        return requested;
    }

    public void setRequested(Boolean requested) {
        this.requested = requested;
    }

    public PreAuthState getPreAuthState() {
        return preAuthState;
    }

    public void setPreAuthState(PreAuthState preAuthState) {
        this.preAuthState = preAuthState;
    }

    @Override
    public String toString() {
        return String.format("Preauthorization [requested=%s, preAuthState=%s]", requested, preAuthState);
    }

    public static Preauthorization build() {
        Preauthorization result = new Preauthorization();
        return result;
    }

    public Preauthorization requested(Boolean requested) {
        this.requested = requested;
        return this;
    }

    public Preauthorization inState(PreAuthState state) {
        this.preAuthState = state;
        return this;
    }
}
