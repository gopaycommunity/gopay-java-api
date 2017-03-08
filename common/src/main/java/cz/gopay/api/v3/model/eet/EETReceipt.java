package cz.gopay.api.v3.model.eet;

import cz.gopay.api.v3.util.GPTimestampAdapter;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class EETReceipt {
	
	public enum REETReceiptState {
		CREATED,
		DELIVERY_FAILED,
		DELIVERED
	}
	
	public enum REETMode {
		AUTO,
		EET
	}
	
	@XmlElement(name = "payment_id")
	private Long paymentId;
	
	@XmlElement(name = "state")
	private REETReceiptState state;
	
	@XmlElement(name = "date_last_attempt")
	@XmlJavaTypeAdapter(GPTimestampAdapter.class)
	private Date dateLastAttempt;
	
	@XmlElement(name = "date_next_attempt")
	@XmlJavaTypeAdapter(GPTimestampAdapter.class)
	private Date dateNextAttempt;
	
	@XmlElement(name = "error_code")
	private Integer commErrorCode;
	
	@XmlElement(name = "kod_chyby")
	private Integer errorCode;
	
	@XmlElement(name = "kody_varovani")
	private String kodyVarovani;
	
	@XmlElement(name = "eet_mode")
	private REETMode reetMode;
	
	@XmlElement(name = "uuid_zprava")
	private String uuidZprava;
	
	@XmlElement(name = "date_odesl")
	@XmlJavaTypeAdapter(GPTimestampAdapter.class)
	private Date datOdesl;
	
	@XmlElement(name = "dic_popl")
	private String dicPopl;
	
	@XmlElement(name = "id_provoz")
	private Integer idProvoz;
	
	@XmlElement(name = "id_pokl")
	private String idPokl;
	
	@XmlElement(name = "dat_trzby")
	@XmlJavaTypeAdapter(GPTimestampAdapter.class)
	private Date datTrzby;
	
	@XmlElement(name = "porad_cis")
	private String poradCis;
	
	@XmlElement(name = "fik")
	private String fik;
	
	@XmlElement(name = "bkp")
	private String bkp;
	
	@XmlElement(name = "pkp")
	private String pkp;
	
	@XmlElement(name = "dic_poverujiciho")
	private String dicPoverujiciho;
	
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
	
	public Long getPaymentId() {
		return paymentId;
	}
	
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	
	public REETReceiptState getState() {
		return state;
	}
	
	public void setState(REETReceiptState state) {
		this.state = state;
	}
	
	public Date getDateLastAttempt() {
		return dateLastAttempt;
	}
	
	public void setDateLastAttempt(Date dateLastAttempt) {
		this.dateLastAttempt = dateLastAttempt;
	}
	
	public Date getDateNextAttempt() {
		return dateNextAttempt;
	}
	
	public void setDateNextAttempt(Date dateNextAttempt) {
		this.dateNextAttempt = dateNextAttempt;
	}
	
	public Integer getCommunicationErrorCode() {
		return commErrorCode;
	}
	
	public void setCommunication(Integer commErrorCode) {
		this.commErrorCode = commErrorCode;
	}
	
	public Integer getKodChyby() {
		return errorCode;
	}
	
	public void setKodChyby(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getKodyVarovani() {
		return kodyVarovani;
	}
	
	public void setKodyVarovani(String kodyVarovani) {
		this.kodyVarovani = kodyVarovani;
	}
	
	public REETMode getReetMode() {
		return reetMode;
	}
	
	public void setReetMode(REETMode reetMode) {
		this.reetMode = reetMode;
	}
	
	public String getUuidZprava() {
		return uuidZprava;
	}
	
	public void setUuidZprava(String uuidZprava) {
		this.uuidZprava = uuidZprava;
	}
	
	public Date getDatOdesl() {
		return datOdesl;
	}
	
	public void setDatOdesl(Date datOdesl) {
		this.datOdesl = datOdesl;
	}
	
	public String getDicPopl() {
		return dicPopl;
	}
	
	public void setDicPopl(String dicPopl) {
		this.dicPopl = dicPopl;
	}
	
	public Integer getIdProvoz() {
		return idProvoz;
	}
	
	public void setIdProvoz(Integer idProvoz) {
		this.idProvoz = idProvoz;
	}
	
	public String getIdPokl() {
		return idPokl;
	}
	
	public void setIdPokl(String idPokl) {
		this.idPokl = idPokl;
	}
	
	public Date getDatTrzby() {
		return datTrzby;
	}
	
	public void setDatTrzby(Date datTrzby) {
		this.datTrzby = datTrzby;
	}
	
	public String getPoradCis() {
		return poradCis;
	}
	
	public void setPoradCis(String poradCis) {
		this.poradCis = poradCis;
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
	
	public String getDicPoverujiciho() {
		return dicPoverujiciho;
	}
	
	public void setDicPoverujiciho(String dicPoverujiciho) {
		this.dicPoverujiciho = dicPoverujiciho;
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
	
	@Override
	public String toString() {
		return String.format("EETReceipt[paymentId=%s, state=%s, idProvoz=%s, datTrzby=%s, celkTrzba=%s]",
										 paymentId, state, idProvoz, datTrzby, celkTrzba);
	}
}
