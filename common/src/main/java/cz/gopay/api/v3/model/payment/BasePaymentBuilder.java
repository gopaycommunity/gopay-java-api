package cz.gopay.api.v3.model.payment;

import cz.gopay.api.v3.model.payment.support.Callback;
import cz.gopay.api.v3.model.payment.support.Payer;
import cz.gopay.api.v3.model.payment.support.PayerContact;
import cz.gopay.api.v3.model.payment.support.PayerPaymentCard;
import cz.gopay.api.v3.model.payment.support.PaymentInstrument;
import cz.gopay.api.v3.model.payment.support.Recurrence;
import cz.gopay.api.v3.model.payment.support.RecurrenceCycle;
import cz.gopay.api.v3.model.payment.support.Target;

import java.util.Date;

/**
 *
 * @author František Sichinger
 */
public class BasePaymentBuilder extends AbstractPaymentBuilder<BasePayment,BasePaymentBuilder> {

    private String lang;
    private Callback callback;
    private Boolean preauth;
    private Recurrence recurrence;
    private Payer payer;
    private Target target;
    
    @Override
    public BasePayment build() {
        BasePayment payment = new BasePayment();
        if (!items.isEmpty()) {
            payment.setItems(items);
        }
        payment.setLang(lang);
        if (!additionalParams.isEmpty()) {
            payment.setAdditionalParams(additionalParams);
        }
        payment.setOrderDescription(orderDescription);
        payment.setAmount(amount);
        payment.setCurrency(currency);
        payment.setOrderNumber(orderNumber);
        payment.setRecurrence(recurrence);
        payment.setCallback(callback);
        if (preauth != null) {
            payment.setPreAuthorization(preauth);
        }
        payment.setTarget(target);
        payment.setPayer(payer);
        return payment;
    }

    public BasePaymentBuilder preauthorize() {
        preauth = true;
        return this;
    }

    public BasePaymentBuilder withCallback(String returnUrl, String notificationUrl) {
        return withCallback(Callback.of(returnUrl, notificationUrl));
    }

    public BasePaymentBuilder withCallback(Callback callback) {
        this.callback = callback;
        return this;
    }

    public BasePaymentBuilder inLang(String lang) {
        this.lang = lang;
        return this;
    }

    public BasePaymentBuilder onDemandRecurrence(Date recurrenceDateTo) {
        recurrence = Recurrence.build(recurrenceDateTo).onDemand();
        return this;
    }

    public BasePaymentBuilder setRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
        return this;
    }
    
    public BasePaymentBuilder timeIntervalRecurrence(RecurrenceCycle recurrenceCycle, Integer recurrencePeriod,
            Date recurrenceDateTo) {
        recurrence = Recurrence.build(recurrenceDateTo).withTimeInterval(recurrenceCycle, recurrencePeriod);
        return this;
    }
    
    public BasePaymentBuilder payer(Payer payer) {
        this.payer = payer;
        return this;
    }
    
    public BasePaymentBuilder withPayerContact(PayerContact contact) {
        if (this.payer == null) {
            this.payer = Payer.build();
        }
        this.payer.setContact(contact);
        return this;
    }
    
    public BasePaymentBuilder withPaymentInstrument(PaymentInstrument paymentInstrument) {
        if (this.payer == null) {
            this.payer = Payer.build();
        }
        this.payer.setPaymentInstrument(paymentInstrument);
        return this;
    }

    public BasePaymentBuilder withDefaultPaymentInstrument(PaymentInstrument paymentInstrument) {
        if (this.payer == null) {
            this.payer = Payer.build();
        }
        this.payer.setDefaultPaymentInstrument(paymentInstrument);
        return this;
    }

    public BasePaymentBuilder toEshop(Long goId) {
        this.target = Target.createEShop(goId);
        return this;
    }

    public BasePaymentBuilder toEWallet(String email) {
        this.target = Target.createEWallet(email);
        return this;
    }
    
    public BasePaymentBuilder withDefaultBnplType(String type) {
        if (this.payer == null) {
            this.payer = Payer.build();
        }
        this.payer.setDefaultBnplType(type);
        return this;
    }
    
    @Override
    protected BasePaymentBuilder getInstance() {
        return this;
    }

}
