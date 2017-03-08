package cz.gopay.api.v3.model.eet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by František Sichinger on 7.2.17.
 */
@XmlRootElement
public class EETCode {
	
	@XmlElement(name = "fik")
	private String fik;
	
	@XmlElement(name = "bkp")
	private String bkp;
	
	@XmlElement(name = "pkp")
	private String pkp;
	
	@Override
	public String toString() {
		return String.format("EETCode [fik=%s, bkp=%s, pkp=%s]", fik, bkp, pkp);
	}
	
	public String getFik() {
		return fik;
	}
	
	public void setFik(String fik) {
		this.fik = fik;
	}
	
	public String getBkp() {
		return bkp;
	}
	
	public void setBkp(String bkp) {
		this.bkp = bkp;
	}
	
	public String getPkp() {
		return pkp;
	}
	
	public void setPkp(String pkp) {
		this.pkp = pkp;
	}
}
