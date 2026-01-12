package cz.gopay.api.v3.model.payment.support;

import cz.gopay.api.v3.util.GPDateAdapter;

import java.util.Date;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Recurrence {

    public enum RecurrenceState {
        REQUESTED,
        STARTED,
        STOPPED
    }

    @XmlElement(name = "recurrence_cycle")
    private RecurrenceCycle recurrenceCycle;

    @XmlElement(name = "recurrence_period")
    private Integer recurrencePeriod;

    @XmlElement(name = "recurrence_date_to")
    @XmlJavaTypeAdapter(GPDateAdapter.class)
    private Date recurrenceDateTo;

    @XmlElement(name = "recurrence_state")
    private RecurrenceState recurrenceState;

    public RecurrenceCycle getRecurrenceCycle() {
        return recurrenceCycle;
    }

    public void setRecurrenceCycle(RecurrenceCycle recurrenceCycle) {
        this.recurrenceCycle = recurrenceCycle;
    }

    public Integer getRecurrencePeriod() {
        return recurrencePeriod;
    }

    public void setRecurrencePeriod(Integer recurrencePeriod) {
        this.recurrencePeriod = recurrencePeriod;
    }

    public Date getRecurrenceDateTo() {
        return recurrenceDateTo;
    }

    public void setRecurrenceDateTo(Date recurrenceDateTo) {
        this.recurrenceDateTo = recurrenceDateTo;
    }

    public RecurrenceState getRecurrenceState() {
        return recurrenceState;
    }

    public void setRecurrenceState(RecurrenceState recurrenceState) {
        this.recurrenceState = recurrenceState;
    }

    @Override
    public String toString() {
        return String.format(
                "Recurrence [recurrenceCycle=%s, recurrencePeriod=%s, recurrenceDateTo=%s, recurrenceState=%s]",
                recurrenceCycle, recurrencePeriod, recurrenceDateTo, recurrenceState);
    }

    public static Recurrence build(Date recurrenceDateTo) {
        Recurrence result = new Recurrence();
        result.setRecurrenceDateTo(recurrenceDateTo);
        return result;
    }

    public Recurrence onDemand() {
        this.recurrenceCycle = RecurrenceCycle.ON_DEMAND;
        return this;
    }

    public Recurrence withTimeInterval(RecurrenceCycle recurrenceCycle, Integer recurrencePeriod) {
        this.recurrenceCycle = recurrenceCycle;
        this.recurrencePeriod = recurrencePeriod;
        return this;
    }

    public Recurrence inState(RecurrenceState state) {
        this.recurrenceState = state;
        return this;
    }

}
