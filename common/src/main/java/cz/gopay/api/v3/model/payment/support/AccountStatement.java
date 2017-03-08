package cz.gopay.api.v3.model.payment.support;

import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.common.StatementGeneratingFormat;
import cz.gopay.api.v3.util.GPDateAdapter;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountStatement {
	
	@XmlElement(name="date_from")
	@XmlJavaTypeAdapter(GPDateAdapter.class)
	private Date dateFrom;
	
	@XmlElement(name="date_to")
	@XmlJavaTypeAdapter(GPDateAdapter.class)
	private Date dateTo;
	
	@XmlElement(name="goid")
	private Long goID;
	
	@XmlElement(name="currency")
	private Currency currency;
	
	private StatementGeneratingFormat format;
	
	public StatementGeneratingFormat getFormat() {
		return format;
	}
	
	public void setFormat(StatementGeneratingFormat format) {
		this.format = format;
	}
	
	public Date getDateFrom() {
		return dateFrom;
	}
	
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	
	public Date getDateTo() {
		return dateTo;
	}
	
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	
	public Long getGoID() {
		return goID;
	}
	
	public void setGoID(Long goID) {
		this.goID = goID;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	@Override
	public String toString() {
		return String.format("AccountStatement=[dateFrom=%s,dateTo=%s,goId=%s,currency=%s,format=%s]",dateFrom,dateTo,goID,currency,format);
	}
}
