package cz.gopay.api.v3.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class GPTimestampAdapter extends XmlAdapter<String, Date> {

  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

  @Override
  public String marshal(Date v) throws Exception {
    return dateFormat.format(v);
  }

  @Override
  public Date unmarshal(String v) throws Exception {
    return dateFormat.parse(v);
  }

}
