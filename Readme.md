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
# Maven #

All artifacts are located in the maven central repository. 

http://mvnrepository.com/artifact/cz.gopay

```xml
<!-- GPAPI common -->
<dependency>
    <groupId>cz.gopay</groupId>
    <artifactId>gp-java-api-v3-common</artifactId>
    <version>3.3.2</version>
</dependency>
<!-- GPAPI Apache Http Client -->
<dependency>
   	<groupId>cz.gopay</groupId>
   	<artifactId>gp-java-api-v3-apache-http-client</artifactId>
   	<version>3.3.2</version>
</dependency>
```
## OAuth
 
 
 To be able to communicate with our gateway it's required to create an auth token.
 
 ```Java
 IGPConnector connector = HttpClientGPConnector.build(<API_URL>);
 connector.getAppToken(<CLIENT_ID>,<CLIENT_CREDENTIALS>); 
 ```
 
 The token gets cached in GPConnector object and its lifetime is 30 minutes. The method ` getAppToken(String, String)` creates token in a scope `"payment-create"`. If you would like to create a token in a different scope call method `getAppToken(<CLIENT_ID>,<CLIENT_CREDENTIALS>,<SCOPE>)` Once the token expires its required to obtain a new one by calling the method getAppToken again.
     

# Basic usage 

## Creating an instance of GPConnector

```Java
IGPConnector connector = HttpClientGPConnector.build(<API_URL>);
```
 
The connector provides methods for interacting with our gateway.
 
## Avalaible methods 
  
| Method        | API Action    |
| :------------ |:--------------|
| [createPayment](#create) | https://doc.gopay.com/en/#standard-payment |
| [paymentStatus](#status) | https://doc.gopay.com/en/#status-of-the-payment |
| [refundPayment](#refund) | https://doc.gopay.com/en/#refund-of-the-payment-(cancelation) |
| [createRecurrentPayment](#createrec) | https://doc.gopay.com/en/#recurring-payment |
| [voidRecurrency](#voidrec) | https://doc.gopay.com/en/#cancellation-of-the-recurring-payment |
| [voidAuthorization](#voidauth) | https://doc.gopay.com/en/#cancellation-of-the-pre-authorized-payment |
| [capturePayment](#capt) | https://doc.gopay.com/en/#charge-of-pre-authorized-payment |
 
 
###### Create a payment <a id="create">

```Java
BasePayment payment = PaymentFactory.createBasePaymentBuilder()
    .order(<ORDER_NUMBER>, <AMOUNT>, Currency.EUR, <DESCRIPTION>)
    .addItem(<ITEM_NAME>, <AMOUNT>, <FEE>, <COUNT>)
    .addAdditionalParameter(<Key>, <VALUE>)
    .withCallback(<RETURN_URL>, <NOTIFY_URL>)
    .payer(<Payer>)
    .inLang(Lang.EN)
    .toEshop(<GO_ID>)
    .build();
try {
    Payment result = connector.createPayment(payment);
} catch (GPClientException e) {
    
}
 ```

###### Payment status <a id="status">

```Java
try {
    Payment payment = connector.paymentStatus(<PAYMENT_ID>);
} catch (GPClientException e) {
     //
}
```
 
###### Payment refund <a id="refund">

```Java
try {
      PaymentResult result = connector.refundPayment(<PAYMENT_ID>, <AMOUNT>);
} catch (GPClientException e) {
      //
}
```
 
###### Create preauthorized payment 

```Java
Payment payment = PaymentFactory.createPaymentBuilder().preauthorize()...
try {
    connector.createPayment(payment);
} catch (GPClientException ex) {
    //
}
```

###### Void authorization <a id="voidauth">

```Java
try {
    PaymentResult voidAuthorization = connector.voidAuthorization(<ID>);
} catch (GPClientException ex) {
    //
}
```

###### Recurrent payment <a id="createrec">

```Java
Calendar calendar = Calendar.getInstance();
calendar.set(Calendar.YEAR, 2016);
calendar.set(Calendar.MONTH, 2);
calendar.set(Calendar.DAY_OF_MONTH, 1);
Recurrence r = Recurrence.build(calendar.getTime())
    .withTimeInterval(RecurrenceCycle.WEEK, 1)
    .inState(Recurrence.RecurrenceState.STARTED);
payment.setRecurrence(r);
 
try {
    connector.createPayment(payment);
} catch {GPClientException e) {
    //
}
```

###### Capture payment <a id="capt">

```Java
try {
    PaymentResult capture = connector.capturePayment(<ID>);
} catch (GPClientException ex) {
    //
}
```

###### Void recurrency <a id="voidrec">

 ```Java
try {
    PaymentResult voidRecurrency = connector.voidRecurrency(<ID>);
} catch (GPClientException ex) {
     //
}
```

###### Charge recurrent payment on demand <a id="dmrec">

```Java
try {
    NextPaymentBuilder builder = PaymentFactory.createNextPaymentBuilder();
    ...
    PaymentResult voidRecurrency = connector.createRecurrentPayment(<ID>, builder.build());
} catch (GPClientException ex) {
     //
}
```



All methods above throw checked exception GPClientException on a failure.

```Java
try {
    HttpClientGPConnector.build(<API_URL>).getAppToken(<CLIENT_ID>,<CLIENT_CREDENTIALS>)
        .createPayment(payment);
} catch (GPClientException e) {
    for (ErrorElement err : e.getError().getErrorMessages()) {
        int code = err.getErrorCode();
        String message = err.getMessage();
        String field = err.getField();
        
    }
}
```
 
 For more code samples check out [unit tests]( https://github.com/gopaycommunity/gopay-java-api/blob/master/common/src/test/java/cz/gopay/api/test/payment/AbstractPaymentTests.java)
 
 
 

    
# Framework support #

If Apache HTTP Client does not suit you, the api supports two frameworks 

 - Resteasy
 - Apache CXF

Each integration has its own maven module. 
 
 - Creating Resteasy connector
 
    ```Java
    IGPConnector connector = ResteasyGPConnector.build(<API_URL>);
    ```
 
     Requires Resteasy connector:
 
     ```xml
     <dependency>
        <groupId>cz.gopay</groupId>
        <artifactId>gp-java-api-v3-resteasy</artifactId>
        <version>3.3.2</version>
    </dependency>
    ```

 - Creating Apache CXF connector
 
    ```Java
    IGPConnector connector = CXFGPConnector.build(<API_URL>);
    ```
    
     Requires apache cxf connector:
     
   ```xml
     <dependency>
         <groupId>cz.gopay</groupId>
         <artifactId>gp-java-api-v3-apache-cxf</artifactId>
         <version>3.3.2</version>
     </dependency>
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
            .addAdditionalParameter(<Key>, <VALUE>)
            .withCallback(<RETURN_URL>, <NOTIFY_URL>)
            .payer(<Payer>)
            .inLang(Lang.EN)
            .toEshop(<GO_ID>)
            .build();
    ```

 - Payer builder
  
    ```Java
    Payer payer = new PayerBuilder()
            .withAllowedPaymentInstruments(Arrays.asList(PaymentInstrument.BANK_ACCOUNT))
            .addAllowedSwift(<SWIFT>).build();
      ```         

## Contributing

Contributions from others would be very much appreciated! Send pull request/ issue. Thanks!

## License

Copyright (c) 2016 GoPay.com. MIT Licensed, see LICENSE for details.

