package cz.gopay.api.v3.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cz.gopay.api.v3.util.GPTimestampAdapter;

@XmlRootElement(name = "error")
public class APIError {

    @XmlElement(name = "date_issued")
    @XmlJavaTypeAdapter(GPTimestampAdapter.class)
    private Date dateIssued;

    @XmlElement(name = "errors",required = true)
    private List<ErrorElement> errorMessages;

    public APIError() {
    }

    public APIError(Date dateIssued) {
        this.dateIssued = dateIssued;
    }

    public static APIError create() {
        return new APIError(new Date());
    }

    public APIError addError(String errorName, int errorCode, String message,
            String description) {
        if (errorMessages == null) {
            errorMessages = new ArrayList<>();
        }

        errorMessages.add(new ErrorElement(errorName, errorCode, message, description));

        return this;
    }

    public APIError addError(String errorName, int errorCode, String field,
            String message, String description) {
        if (errorMessages == null) {
            errorMessages = new ArrayList<>();
        }

        errorMessages.add(new ErrorElement(errorName, errorCode, field, message, description));
        return this;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(Date dateOccured) {
        this.dateIssued = dateOccured;
    }

    public List<ErrorElement> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<ErrorElement> errors) {
        this.errorMessages = errors;
    }

}
