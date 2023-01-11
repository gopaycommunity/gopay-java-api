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
public class AdditionalParam {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "value")
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("AdditionalParam [name=%s, value=%s]", name, value);
    }

    public static AdditionalParam of(String name, String value) {
        AdditionalParam result = new AdditionalParam();
        result.setName(name);
        result.setValue(value);

        return result;
    }

}
