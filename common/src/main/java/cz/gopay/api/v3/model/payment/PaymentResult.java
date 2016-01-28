package cz.gopay.api.v3.model.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class PaymentResult {

    public enum Result {
        ACCEPTED,
        FINISHED,
        FAILED;
    }

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "result")
    private Result result;

    @XmlElement(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("PaymentResult [id=%s, result=%s, description=%s]",
                id, result, description);
    }

    public static PaymentResult create(Long paymentId) {
        PaymentResult result = new PaymentResult();
        result.setId(paymentId);
        return result;
    }

    public PaymentResult success() {
        setResult(Result.FINISHED);
        return this;
    }

    public PaymentResult accepted() {
        setResult(Result.ACCEPTED);
        return this;
    }

    public PaymentResult failed(String desc) {
        setResult(Result.FAILED);
        setDescription(desc);
        return this;
    }

}
