package cz.gopay.api.v3.model.eet;

import cz.gopay.api.v3.util.GPDateAdapter;

import java.util.Date;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by František Sichinger on 23.2.17.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EETReceiptFilter {
	
	@XmlElement(name = "date_from")
	@XmlJavaTypeAdapter(GPDateAdapter.class)
	private Date dateFrom;
	
	@XmlElement(name = "date_to")
	@XmlJavaTypeAdapter(GPDateAdapter.class)
	private Date dateTo;
	
	@XmlElement(name = "id_provozovny")
	private Integer idProvoz;
	
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
	
	public Integer getIdProvoz() {
		return idProvoz;
	}
	
	public void setIdProvoz(Integer idProvoz) {
		this.idProvoz = idProvoz;
	}
	
	@Override
	public String toString() {
		return String.format("EETReceiptFilter[dateFrom=%s, dateTo=%s, idProvoz=%s]", dateFrom, dateTo, idProvoz);
	}
	
	public static EETReceiptFilter create(Integer idProvoz, Date dateFrom, Date dateTo) {
		EETReceiptFilter filter = new EETReceiptFilter();
		filter.dateFrom = dateFrom;
		filter.dateTo = dateTo;
		filter.idProvoz = idProvoz;
		return filter;
	}
}
