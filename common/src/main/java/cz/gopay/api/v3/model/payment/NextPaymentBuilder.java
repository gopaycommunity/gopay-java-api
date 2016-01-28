/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.gopay.api.v3.model.payment;

/**
 *
 * @author František Sichinger
 */
public class NextPaymentBuilder extends AbstractPaymentBuilder<NextPayment, NextPaymentBuilder> {

    @Override
    protected NextPaymentBuilder getInstance() {
        return this;
    }

    @Override
    public NextPayment build() {
        NextPayment payment = new NextPayment();
        payment.setItems(items);
        payment.setCurrency(currency);
        payment.setOrderDescription(orderDescription);
        payment.setOrderNumber(orderNumber);
        payment.setAdditionalParams(additionalParams);
        return payment;
    }
    
}
