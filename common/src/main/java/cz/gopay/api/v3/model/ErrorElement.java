package cz.gopay.api.v3.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Zbynek Novak | novak.zbynek@gmail.com
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
public class ErrorElement {

    @XmlElement(name = "scope")
    private ErrorScope scope;

    @XmlElement(name = "field")
    private String field;

    @XmlElement(name = "error_code")
    private int errorCode;

    @XmlElement(name = "error_name")
    private String errorName;

    @XmlElement(name = "message")
    private String message;

    @XmlElement(name = "description")
    private String description;

    public ErrorElement() {
    }

    public ErrorElement(String errorName, int errorCode, String message,
            String description) {
        super();
        this.scope = ErrorScope.G;
        this.errorCode = errorCode;
        this.errorName = errorName;
        this.message = message;
        this.description = description;
    }

    public ErrorElement(String errorName, int errorCode, String field,
            String message, String description) {
        super();
        this.scope = ErrorScope.F;
        this.field = field;
        this.errorCode = errorCode;
        this.errorName = errorName;
        this.message = message;
        this.description = description;
    }

    public ErrorScope getScope() {
        return scope;
    }

    public String getField() {
        return field;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorName() {
        return errorName;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

}
