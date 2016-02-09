# GoPay Java API #


Detailed guide: [https://doc.gopay.com](https://doc.gopay.com)


# Requirements

 - Java 7+

# Building

```bash
git clone https://github.com/gopaycommunity/gopay-java-api.git
cd gopay-java-api
mvn package
```

 - Building a specific module
```bash
git clone https://github.com/gopaycommunity/gopay-java-api.git
cd gopay-java-api/<module-name>
mvn package
```
 
# Basic usage 

  - ## Creating an instance of GPConnector

   ```Java
   IGPConnector connector = HttpClientGPConnector.build(<API_URL>);
   ```
   
   The connector provides methods for interacting with our gateway.
   
  - ## Avalaible methods 
  
   | Method        | API Action        |
   | :------------ |:--------------|
   | createPayment | https://doc.gopay.com/en/#standard-payment |
   | paymentStatus | https://doc.gopay.com/en/#status-of-the-payment |
   | refundPayment | https://doc.gopay.com/en/#refund-of-the-payment-(cancelation) |
   | createRecurrentPayment | https://doc.gopay.com/en/#recurring-payment |
   | voidRecurrency | https://doc.gopay.com/en/#cancellation-of-the-recurring-payment |
   | voidAuthorization | https://doc.gopay.com/en/#cancellation-of-the-pre-authorized-payment |
   | capturePayment | https://doc.gopay.com/en/#charge-of-pre-authorized-payment |
 
  All methods above throw checked exception GPClientException on a failure.
 
 ```Java
 try {
     IGPConnector connector = HttpClientGPConnector.build(<API_URL>).getAppToken(<CLIENT_ID>,<CLIENT_CREDENTIALS>).createPayment(payment);
 } catch (GPClientException e) {
     GPExceptionHandler.handleException(e);
     //
 }
 ```
 
 For more code samples check out [unit tests]( https://github.com/gopaycommunity/gopay-java-api/blob/master/common/src/test/java/cz/gopay/api/test/payment/AbstractPaymentTests.java)
 
 
  - ## OAuth
  
     To be able to communicate with our gateway it's required to create an auth token.
     ```Java
     IGPConnector connector = HttpClientGPConnector.build(<API_URL>);
     connector.getAppToken(<CLIENT_ID>,<CLIENT_CREDENTIALS>); 
     ```
     The token gets cached in GPConnector object and its lifetime is 30 minutes. The method ` getAppToken(String, String)` creates token in a scope `"payment-create"`. If you would like to create a token in a different scope call method `getAppToken(<CLIENT_ID>,<CLIENT_CREDENTIALS>,<SCOPE>)` Once the token expires its required to obtain a new one by calling the method getAppToken.
     
    
# Framework support #

If Apache HTTP Client does not suit you, the api supports two frameworks 

 - RestEasy
 - Apache CXF

Each integration has its own maven module. 
 
 - Creating RestEasy connector
 
    ```Java
    IGPConnector connector = ResteasyGPConnector.build(<API_URL>);
    ```
  
 - Creating Apache CXF connector
 
    ```Java
    IGPConnector connector = CXFGPConnector.build(<API_URL>);
    ```
 
 If you would like to create your own GPConnector, inherit from a class AbstractGPConnector and override a method 
 `T createRESTClientProxy(String apiUrl, Class<T> proxy)`.The proxy parameter is either an interface PaymentClient or AuthClient. You must create implementations of both and return instance of correct class. 
 
 
- ### Model builders
  
 You can use several builder objects to achieve better code readability.
  
  - Payment builder

    ```Java
      BasePayment payment = PaymentFactory.createBasePaymentBuilder()
                .order(<ORDER_NUMBER>, <AMOUNT>, Currency.EUR, <DESCRIPTION>)
                .addItem(<ITEM_NAME>, <AMOUNT>, <FEE>, <COUNT>)
                .inLang(Lang.EN)
                .toEshop(<GO_ID>)
                .addAdditionalParameter(<Key>, <VALUE>)
                .withCallback(<RETURN_URL>, <NOTIFY_URL>)
                .payer(<Payer>).build();
    ```

* ##### Payer builder
  
    ```Java
            Payer payer = new PayerBuilder()
            .withAllowedPaymentInstruments(Arrays.asList(PaymentInstrument.BANK_ACCOUNT))
            .addAllowedSwift(<SWIFT>).build();
       ```         

* #### Create a payment

```Java
HttpClientGPConnector.build(<API_URL>).getAppToken(<CLIENT_ID>,<CLIENT_CREDENTIALS>).createPayment(payment);
```

* #### Create preauthorized payment

 ```Java
PaymentFactory.createPaymentBuilder().preauthorize()...
 ```
 
* #### Payment refund


```Java
HttpClientGPConnector.build(<API_URL>).getAppToken(<CLIENT_ID>,<CLIENT_CREDENTIALS>)
            .refundPayment(<PAYMENT_ID>, <AMOUNT>);
 ```

 
* #### Recurrent payment


```Java
Calendar calendar = Calendar.getInstance();
calendar.set(Calendar.YEAR, 2016);
calendar.set(Calendar.MONTH, 2);
calendar.set(Calendar.DAY_OF_MONTH, 1);
Recurrence r = Recurrence.build(calendar.getTime())
     .withTimeInterval(RecurrenceCycle.WEEK, 1)
     .inState(Recurrence.RecurrenceState.STARTED);
payment.setRecurrence(r);
 ```
 
## Contributing

Contributions from others would be very much appreciated! Send pull request/ issue. Thanks!

## License

Copyright (c) 2016 GoPay.com. MIT Licensed, see LICENSE for details.
