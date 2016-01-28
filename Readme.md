# GoPay Java API #

Java API pro platební bránu gopay.

Detailní popis api: [https://doc.gopay.com](https://doc.gopay.com)

# Implementace #

Pro interakci s  platební bránou je potřeba vytvořit instanci GPConnectoru a získat OAuth access token. K dispozici jsou 3 různé implementace - Resteasy, Apache CXF a Apache Http Client 


 - ### Resteasy ###
 
    `IGPConnector connector = ResteasyGPConnector.build("API_URL");`
    

 - ### Apache CXF ###
 

    `IGPConnector connector = CXFGPConnector.build("API_URL");`

 - ### Apache Http client ###


    `IGPConnector connector = HttpClientGPConnector.build("API_URL");`

## Použití ##

- ### Přístupový token ###
     
        connector.getAppToken("CLIENT_ID","CLIENT_SECRET");

- ### Založení platby ###


* #### Standartni platba

  * ##### Vytvoření objektu Payer
  
            Payer payer = new PayerBuilder()
            .withAllowedPaymentInstruments(Arrays.asList(PaymentInstrument.BANK_ACCOUNT))
            .addAllowedSwift("FIOBCZPP").build();
                


  * ##### Vytvoření standartní platby 

            BasePayment payment = PaymentFactory.createBasePaymentBuilder()
                .withCallback("RETURN_URL", "NOTIFY_URL")
                .order("123", 10L, Currency.EUR, "description")
                .inLang(Lang.EN)
                .addAdditionalParameter("AKey2", "AValue")
                .addItem("An item", 1L, 1L, 1L)
                .toEshop("GO_ID")
                .payer(payer).build();

  * #### Odeslání požadavku

            Payment response = connector.createPayment(payment);

* #### Předautorizovaná platba
            
           PaymentFactory.createPaymentBuilder().preauthorize()...

* #### Storno platby
           
           connector.refundPayment("PAYMENT_ID", "AMOUNT");
           
* #### Opakovaná platba 

           Calendar calendar = Calendar.getInstance();
           calendar.set(Calendar.YEAR, 2016);
           calendar.set(Calendar.MONTH, 2);
           calendar.set(Calendar.DAY_OF_MONTH, 1);
           Recurrence r = Recurrence.build(calendar.getTime())
                     .withTimeInterval(RecurrenceCycle.WEEK, 1)
                     .inState(Recurrence.RecurrenceState.STARTED);
           payment.setRecurrence(r);

* #### Status platby

           Payment response = connector.paymentStatus(PAYMENT_ID);

* #### Zrušení stržení platby

           PaymentResult response = connector.capturePayment(PAYMENT_ID);

* #### Zrušení předautorizované platby

           PaymentResult response = connector.voidRecurrency(PAYMENT_ID);

* #### Zrušení předautorizované platby 

           PaymentResult response = connector.voidAuthorization(PAYMENT_ID);
