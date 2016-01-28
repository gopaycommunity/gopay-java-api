/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.gopay.api.v3.model.payment.support;

import cz.gopay.api.v3.Builder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author František Sichinger
 */
public class PayerBuilder implements Builder<Payer> {

    private PaymentInstrument paymentInstrument;
    private PaymentInstrument defaultPaymentInstrument;
    private List<PaymentInstrument> allowedPaymentInstruments = new ArrayList<>();
    private List<String> allowedSwifts = new ArrayList<>();
    private PayerContact contact;
    
    @Override
    public Payer build() {
        return new Payer();
    }
    
    public PayerBuilder withPaymentInstrument(PaymentInstrument paymentInstrument) {
        this.paymentInstrument = paymentInstrument;
        return this;
    }

    public PayerBuilder withDefaultPaymentInstrument(PaymentInstrument defaultPaymentInstrument) {
        this.defaultPaymentInstrument = defaultPaymentInstrument;
        return this;
    }

    public PayerBuilder withAllowedPaymentInstruments(List<PaymentInstrument> allowedInstruments) {
        this.allowedPaymentInstruments = allowedInstruments;
        return this;
    }

    public PayerBuilder addAllowedPaymentMethod(PaymentInstrument allowedInstrument) {
        if (this.allowedPaymentInstruments == null) {
            this.allowedPaymentInstruments = new ArrayList<>();
        }
        this.allowedPaymentInstruments.add(allowedInstrument);

        return this;
    }

    public PayerBuilder withAllowedSwifts(List<String> allowedSwifts) {
        this.allowedSwifts = allowedSwifts;
        return this;
    }

    public PayerBuilder addAllowedSwift(String allowedSwift) {
        if (this.allowedSwifts == null) {
            this.allowedSwifts = new ArrayList<>();
        }
        this.allowedSwifts.add(allowedSwift);

        return this;
    }

    public PayerBuilder withContactData(PayerContact contact) {
        this.contact = contact;
        return this;
    }
}
