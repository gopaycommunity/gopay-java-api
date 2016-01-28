package cz.gopay.api.v3.model.common;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author Zbynek Novak | novak.zbynek@gmail.com
 * @author pvalenta //FIXME - refactoring az prejdeme na JAVA 7 - tato verze to jiz podporuje
 *         3numeric ISO cody a 2 ALPHA cody v Locale
 *
 */
public enum CountryCode {
  CZE(1), // Czech Republic
  SVK(2), // Slovakia
  AFG(100), // Afghánistán
  ALA(101), // Ålandy
  ALB(102), // Albánie
  DZA(103), // Alžírsko
  ASM(104), // Americká Samoa
  VIR(105), // Americké Panenské ostrovy
  AND(106), // Andorra
  AGO(107), // Angola
  AIA(108), // Anguilla
  ATA(109), // Antarktida
  ATG(110), // Antigua a Barbuda
  ARG(111), // Argentina
  ARM(112), // Arménie
  ABW(113), // Aruba
  AUS(114), // Austrálie
  AZE(115), // Ázerbájdžán
  BHS(116), // Bahamy
  BHR(117), // Bahrajn
  BGD(118), // Bangladéš
  BRB(119), // Barbados
  BEL(120), // Belgie
  BLZ(121), // Belize
  BLR(122), // Bělorusko
  BEN(123), // Benin
  BMU(124), // Bermudy
  BTN(125), // Bhútán
  BOL(126), // Bolívie
  BES(127), // Bonaire, Svatý Eustach a Saba
  BIH(128), // Bosna a Hercegovina
  BWA(129), // Botswana
  BVT(130), // Bouvetův ostrov
  BRA(131), // Brazílie
  IOT(132), // Britské indickooceánské území
  VGB(133), // Britské Panenské ostrovy
  BRN(134), // Brunej
  BGR(135), // Bulharsko
  BFA(136), // Burkina Faso
  BDI(137), // Burundi
  COK(138), // Cookovy ostrovy
  CUW(139), // Curaçao
  TCD(140), // Čad
  MNE(141), // Černá Hora
  CHN(143), // Čína
  DNK(144), // Dánsko
  COD(145), // Demokratická republika Kongo
  DMA(146), // Dominika
  DOM(147), // Dominikánská republika
  DJI(148), // Džibutsko
  EGY(149), // Egypt
  ECU(150), // Ekvádor
  ERI(151), // Eritrea
  EST(152), // Estonsko
  ETH(153), // Etiopie
  FRO(154), // Faerské ostrovy
  FLK(155), // Falklandy (Malvíny)
  FJI(156), // Fidži
  PHL(157), // Filipíny
  FIN(158), // Finsko
  FRA(159), // Francie
  GUF(160), // Francouzská Guyana
  ATF(161), // Francouzská jižní a antarktická území
  PYF(162), // Francouzská Polynésie
  GAB(163), // Gabon
  GMB(164), // Gambie
  GHA(165), // Ghana
  GIB(166), // Gibraltar
  GRD(167), // Grenada
  GRL(168), // Grónsko
  GEO(169), // Gruzie
  GLP(170), // Guadeloupe
  GUM(171), // Guam
  GTM(172), // Guatemala
  GIN(173), // Guinea
  GNB(174), // Guinea-Bissau
  GGY(175), // Guernsey
  GUY(176), // Guyana
  HTI(177), // Haiti
  HMD(178), // Heardův ostrov a McDonaldovy ostrovy
  HND(179), // Honduras
  HKG(180), // Hongkong
  CHL(181), // Chile
  HRV(182), // Chorvatsko
  IND(183), // Indie
  IDN(184), // Indonésie
  IRQ(185), // Irák
  IRN(186), // Írán
  IRL(187), // Irsko
  ISL(188), // Island
  ITA(189), // Itálie
  ISR(190), // Izrael
  JAM(191), // Jamajka
  JPN(192), // Japonsko
  YEM(193), // Jemen
  JEY(194), // Jersey
  ZAF(195), // Jihoafrická republika
  SGS(196), // Jižní Georgie a Jižní Sandwichovy ostrovy
  KOR(197), // Jižní Korea
  SSD(198), // Jižní Súdán
  JOR(199), // Jordánsko
  CYM(200), // Kajmanské ostrovy
  KHM(201), // Kambodža
  CMR(202), // Kamerun
  CAN(203), // Kanada
  CPV(204), // Kapverdy
  QAT(205), // Katar
  KAZ(206), // Kazachstán
  KEN(207), // Keňa
  KIR(208), // Kiribati
  CCK(209), // Kokosové ostrovy
  COL(210), // Kolumbie
  COM(211), // Komory
  COG(212), // Kongo
  CRI(213), // Kostarika
  CUB(214), // Kuba
  KWT(215), // Kuvajt
  CYP(216), // Kypr
  KGZ(217), // Kyrgyzstán
  LAO(218), // Laos
  LSO(219), // Lesotho
  LBN(220), // Libanon
  LBR(221), // Libérie
  LBY(222), // Libye
  LIE(223), // Lichtenštejnsko
  LTU(224), // Litva
  LVA(225), // Lotyšsko
  LUX(226), // Lucembursko
  MAC(227), // Macao
  MDG(228), // Madagaskar
  HUN(229), // Maďarsko
  MKD(230), // Makedonie
  MYS(231), // Malajsie
  MWI(232), // Malawi
  MDV(233), // Maledivy
  MLI(234), // Mali
  MLT(235), // Malta
  IMN(236), // Ostrov Man
  MAR(237), // Maroko
  MHL(238), // Marshallovy ostrovy
  MTQ(239), // Martinik
  MUS(240), // Mauricius
  MRT(241), // Mauritánie
  MYT(242), // Mayotte
  UMI(243), // Menší odlehlé ostrovy USA
  MEX(244), // Mexiko
  FSM(245), // Mikronésie
  MDA(246), // Moldavsko
  MCO(247), // Monako
  MNG(248), // Mongolsko
  MSR(249), // Montserrat
  MOZ(250), // Mosambik
  MMR(251), // Myanmar
  NAM(252), // Namibie
  NRU(253), // Nauru
  DEU(254), // Německo
  NPL(255), // Nepál
  NER(256), // Niger
  NGA(257), // Nigérie
  NIC(258), // Nikaragua
  NIU(259), // Niue
  NLD(260), // Nizozemsko
  NFK(261), // Norfolk
  NOR(262), // Norsko
  NCL(263), // Nová Kaledonie
  NZL(264), // Nový Zéland
  OMN(265), // Omán
  PAK(266), // Pákistán
  PLW(267), // Palau
  PSE(268), // Palestinská autonomie
  PAN(269), // Panama
  PNG(270), // Papua-Nová Guinea
  PRY(271), // Paraguay
  PER(272), // Peru
  PCN(273), // Pitcairnovy ostrovy
  CIV(274), // Pobřeží slonoviny
  POL(275), // Polsko
  PRI(276), // Portoriko
  PRT(277), // Portugalsko
  AUT(278), // Rakousko
  REU(279), // Réunion
  GNQ(280), // Rovníková Guinea
  ROU(281), // Rumunsko
  RUS(282), // Rusko
  RWA(283), // Rwanda
  GRC(284), // Řecko
  SPM(285), // Saint-Pierre a Miquelon
  SLV(286), // Salvador
  WSM(287), // Samoa
  SMR(288), // San Marino
  SAU(289), // Saúdská Arábie
  SEN(290), // Senegal
  PRK(291), // Severní Korea
  MNP(292), // Severní Mariany
  SYC(293), // Seychely
  SLE(294), // Sierra Leone
  SGP(295), // Singapur
  SVN(297), // Slovinsko
  SOM(298), // Somálsko
  ARE(299), // Spojené arabské emiráty
  GBR(300), // Spojené království
  USA(301), // Spojené státy americké
  SRB(302), // Srbsko
  CAF(303), // Středoafrická republika
  SDN(304), // Súdán
  SUR(305), // Surinam
  SHN(306), // Svatá Helena, Ascension a Tristan da Cunha
  LCA(307), // Svatá Lucie
  BLM(308), // Svatý Bartoloměj
  KNA(309), // Svatý Kryštof a Nevis
  MAF(310), // Svatý Martin (francouzská část)
  SXM(311), // Svatý Martin (nizozemská část)
  STP(312), // Svatý Tomáš a Princův ostrov
  VCT(313), // Svatý Vincenc a Grenadiny
  SWZ(314), // Svazijsko
  SYR(315), // Sýrie
  SLB(316), // Šalamounovy ostrovy
  ESP(317), // Španělsko
  SJM(318), // Špicberky a Jan Mayen
  LKA(319), // Šrí Lanka
  SWE(320), // Švédsko
  CHE(321), // Švýcarsko
  TJK(322), // Tádžikistán
  TZA(323), // Tanzanie
  THA(324), // Thajsko
  TWN(325), // Tchaj-wan
  TGO(326), // Togo
  TKL(327), // Tokelau
  TON(328), // Tonga
  TTO(329), // Trinidad a Tobago
  TUN(330), // Tunisko
  TUR(331), // Turecko
  TKM(332), // Turkmenistán
  TCA(333), // Turks a Caicos
  TUV(334), // Tuvalu
  UGA(335), // Uganda
  UKR(336), // Ukrajina
  URY(337), // Uruguay
  UZB(338), // Uzbekistán
  CXR(339), // Vánoční ostrov
  VUT(340), // Vanuatu
  VAT(341), // Vatikán
  VEN(342), // Venezuela
  VNM(343), // Vietnam
  TLS(344), // Východní Timor
  WLF(345), // Wallis a Futuna
  ZMB(346), // Zambie
  ESH(347), // Západní Sahara
  ZWE(348), // Zimbabwe
  UNK(345);// UNMIK - ISO numeric code 900 - United Nations Interim Administration Mission in Kosovo

  private final int position;

  private static Map<String, Locale> localeMap;

  private CountryCode(int position) {
    this.position = position;
  }

  public String toIso3() {
    return String.valueOf(this);
  }

  public String toIso2() {
    return CountryCode.fromIso3ToIso2(String.valueOf(this));
  }

  private static void initCountryCodeMapping() {
    String[] countries = Locale.getISOCountries();
    localeMap = new HashMap<String, Locale>(countries.length);
    for (String country : countries) {
      Locale locale = new Locale("", country);
      localeMap.put(locale.getISO3Country().toUpperCase(), locale);
    }
  }

  public static String fromIso3ToIso2(String iso3CountryCode) {
    if (localeMap == null) {
      initCountryCodeMapping();
    }

    Locale loc = localeMap.get(iso3CountryCode);
    String result = loc.getCountry();

    if (result == null) {
      throw new IllegalArgumentException("not supported country [" + iso3CountryCode + "]");
    }

    return result;
  }

  public static CountryCode fromCodeStrict(String countryCode) {
    if (countryCode == null) {
      return null;
    }

    for (CountryCode country : CountryCode.values()) {
      if (String.valueOf(country).equalsIgnoreCase(countryCode)) {
        return country;
      }
    }
    return null;
  }

  public static CountryCode fromAlpha2Code(String code) {
    Locale locale = new Locale("", code);
    CountryCode result = fromCodeStrict(locale.getISO3Country());

    if (result == null) {
      throw new IllegalArgumentException("not supported country");
    }

    return result;

  }

  public static CountryCode fromAlpha2CodeNonFatal(String code) {
    try {
      Locale locale = new Locale("", code);
      CountryCode result = fromCodeStrict(locale.getISO3Country());

      return result;

    } catch (Exception e) {
      return null;
    }
  }

  // FIXME - odstranit vyreseno obecne fromIso3ToIso2
  public String toAlpha2Code() {
    com.neovisionaries.i18n.CountryCode c = com.neovisionaries.i18n.CountryCode.getByCode(this.name());

    return c.getAlpha2();
  }

  /**
   * Represents ISO 3166-1 numeric codes for country
   */
  public Integer getNumericCode() {

    com.neovisionaries.i18n.CountryCode c = com.neovisionaries.i18n.CountryCode.getByCode(this.toString());

    return Integer.valueOf(c.getNumeric());
  }

  public static CountryCode getByNumericalCode(Integer numericCode) {

    numericCode = changeOldISOCodesToNewISOCode(numericCode);

    return resolveCountryCode(numericCode);
  }

  private static boolean isNumericCodeDeprecated(Integer numericCode) {
    if (numericCode != null) {
      if (numericCode.intValue() == 900) { // user-assigned UNMIK
        return true;
      }
      if (numericCode.intValue() == 720) { // South Yemen ma ISO 887
        return true;
      }
      if (numericCode.intValue() == 886) { // Yemen republic ma ISO 887
        return true;
      }
      if (numericCode.intValue() == 0) { // 0
        return true;
      }
      return false;
    } else {
      throw new IllegalStateException("ISO numeric value for CountryCode canont be null!");
    }
  }

  private static CountryCode resolveCountryCode(Integer numericCode) {

    if (isNumericCodeDeprecated(numericCode)) {
      if (numericCode.intValue() == 0) { // 0
        return null;
      }
      if (numericCode.intValue() == 900) { // user-assigned UNMIK
        return CountryCode.UNK;
      }
      if (numericCode.intValue() == 720 || numericCode.intValue() == 886) { // South Yemen ma ISO
                                                                            // 887
        return CountryCode.YEM;
      }
      throw new IllegalStateException("CountryCode from numeric value[" + numericCode + "] cannot be resolved");
    } else {
      com.neovisionaries.i18n.CountryCode c = com.neovisionaries.i18n.CountryCode.getByCode(numericCode);
      return CountryCode.fromCodeStrict(c.getAlpha3());
    }
  }

  /**
   * Dle Wiki http://en.wikipedia.org/wiki/ISO_3166-1_numeric dochazi k odebirani a zmenam ISO kodu
   * na jine kody. Musim tedy menit take.
   *
   * @param numericCode
   * @return numericCode, if numericCode is old or removed, method returns new ISO or null(value
   *         null still not implemented)
   */
  private static Integer changeOldISOCodesToNewISOCode(Integer numericCode) {

    if (numericCode != null) {
      // Germany use before unification 278 and 280 - now is 276
      if (numericCode.intValue() == 278 || numericCode.intValue() == 280) {
        return Integer.valueOf(276);
      }

      // Ethiopia used numeric code 230 before Eritrea split - now is 231
      if (numericCode.intValue() == 230) {
        return Integer.valueOf(231);
      }

      // Sudan used numeric code 736 before South Sudan split - now is 729
      if (numericCode.intValue() == 736) {
        return Integer.valueOf(729);
      }
    }
    return numericCode;
  }

  public int getPosition() {
    return position;
  }

}
