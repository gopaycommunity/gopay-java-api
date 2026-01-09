package cz.gopay.api.v3.model.eet;

import cz.gopay.api.v3.model.common.Currency;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Created by František Sichinger on 30.1.17.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EET {
	
	@XmlElement(name = "celk_trzba")
	private Long celkTrzba;
	
	@XmlElement(name = "zakl_nepodl_dph")
	private Long zaklNepodlDPH;
	
	@XmlElement(name = "zakl_dan1")
	private Long zaklDan1;
	
	@XmlElement(name = "dan1")
	private Long dan1;
	
	@XmlElement(name = "zakl_dan2")
	private Long zaklDan2;
	
	@XmlElement(name = "dan2")
	private Long dan2;
	
	@XmlElement(name = "zakl_dan3")
	private Long zaklDan3;
	
	@XmlElement(name = "dan3")
	private Long dan3;
	
	@XmlElement(name = "cest_sluz")
	private Long cestSluz;
	
	@XmlElement(name = "pouzit_zboz1")
	private Long pouzitZboz1;
	
	@XmlElement(name = "pouzit_zboz2")
	private Long pouzitZboz2;
	
	@XmlElement(name = "pouzit_zboz3")
	private Long pouzitZboz3;
	
	@XmlElement(name = "urceno_cerp_zuct")
	private Long urcenoCerpZuct;
	
	@XmlElement(name = "cerp_zuct")
	private Long cerpZuct;
	
	@XmlElement(name = "dic_poverujiciho")
	private String dicPoverujiciho;
	
	@XmlElement(name = "mena")
	private Currency mena;
	
	@Override
	public String toString() {
		return String.format("EET [celkTrzba=%s, zaklNepodlDPH=%s, zaklDan1=%s, dan1=%s, zaklDan2=%s, dan2=%s, zaklDan3=%s, dan3=%s, "
				+ "cestSluz=%s, pouzitZboz1=%s, pouzitZboz2=%s, pouzitZboz3=%s, urcenoCerpZuct=%s, cerpZuct=%s, dicPoverujiciho=%s, "
						+ "mena=%s]",
							celkTrzba, zaklNepodlDPH, zaklDan1, dan1, zaklDan2, dan2, zaklDan3, dan3,
							cestSluz, pouzitZboz1, pouzitZboz2, pouzitZboz3, urcenoCerpZuct, cerpZuct, dicPoverujiciho, mena);
	}
	
	public EET() {
	}
	
	public EET(Long celkTrzba, Currency mena) {
		this.celkTrzba = celkTrzba;
		this.mena = mena;
	}
	
	public EET withDan1(Long dan1, Long zaklDan1) {
		this.dan1 = dan1;
		this.zaklDan1 = zaklDan1;
		return this;
	}
	
	public EET withDan2(Long dan2, Long zaklDan2) {
		this.dan2 = dan2;
		this.zaklDan2 = zaklDan2;
		return this;
	}
	
	public EET withDan3(Long dan3, Long zaklDan3) {
		this.dan3 = dan3;
		this.zaklDan3 = zaklDan3;
		return this;
	}
	
	public EET withPouzitZboz(Long pouzitZboz1, Long pouzitZboz2 ,Long pouzitZboz3) {
		this.pouzitZboz1 = pouzitZboz1;
		this.pouzitZboz2 = pouzitZboz2;
		this.pouzitZboz3 = pouzitZboz3;
		return this;
	}
	
	public Long getCelkTrzba() {
		return celkTrzba;
	}
	
	public void setCelkTrzba(Long celkTrzba) {
		this.celkTrzba = celkTrzba;
	}
	
	public Long getZaklNepodlDPH() {
		return zaklNepodlDPH;
	}
	
	public void setZaklNepodlDPH(Long zaklNepodlDPH) {
		this.zaklNepodlDPH = zaklNepodlDPH;
	}
	
	public Long getZaklDan1() {
		return zaklDan1;
	}
	
	public void setZaklDan1(Long zaklDan1) {
		this.zaklDan1 = zaklDan1;
	}
	
	public Long getDan1() {
		return dan1;
	}
	
	public void setDan1(Long dan1) {
		this.dan1 = dan1;
	}
	
	public Long getZaklDan2() {
		return zaklDan2;
	}
	
	public void setZaklDan2(Long zaklDan2) {
		this.zaklDan2 = zaklDan2;
	}
	
	public Long getDan2() {
		return dan2;
	}
	
	public void setDan2(Long dan2) {
		this.dan2 = dan2;
	}
	
	public Long getZaklDan3() {
		return zaklDan3;
	}
	
	public void setZaklDan3(Long zaklDan3) {
		this.zaklDan3 = zaklDan3;
	}
	
	public Long getDan3() {
		return dan3;
	}
	
	public void setDan3(Long dan3) {
		this.dan3 = dan3;
	}
	
	public Long getCestSluz() {
		return cestSluz;
	}
	
	public void setCestSluz(Long cestSluz) {
		this.cestSluz = cestSluz;
	}
	
	public Long getPouzitZboz1() {
		return pouzitZboz1;
	}
	
	public void setPouzitZboz1(Long pouzitZboz1) {
		this.pouzitZboz1 = pouzitZboz1;
	}
	
	public Long getPouzitZboz2() {
		return pouzitZboz2;
	}
	
	public void setPouzitZboz2(Long pouzitZboz2) {
		this.pouzitZboz2 = pouzitZboz2;
	}
	
	public Long getPouzitZboz3() {
		return pouzitZboz3;
	}
	
	public void setPouzitZboz3(Long pouzitZboz3) {
		this.pouzitZboz3 = pouzitZboz3;
	}
	
	public Long getUrcenoCerpZuct() {
		return urcenoCerpZuct;
	}
	
	public void setUrcenoCerpZuct(Long urcenoCerpZuct) {
		this.urcenoCerpZuct = urcenoCerpZuct;
	}
	
	public Long getCerpZuct() {
		return cerpZuct;
	}
	
	public void setCerpZuct(Long cerpZuct) {
		this.cerpZuct = cerpZuct;
	}
	
	public String getDicPoverujiciho() {
		return dicPoverujiciho;
	}
	
	public void setDicPoverujiciho(String dicPoverujiciho) {
		this.dicPoverujiciho = dicPoverujiciho;
	}
	
	public Currency getMena() {
		return mena;
	}
	
	public void setMena(Currency mena) {
		this.mena = mena;
	}
}