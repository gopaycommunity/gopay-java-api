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
public class PaymentFactory {
    
    
    public static BasePaymentBuilder createBasePaymentBuilder() {
        return new BasePaymentBuilder();
    }
    
    public static NextPaymentBuilder createNextPaymentBuilder() {
        return new NextPaymentBuilder();
    }
    
}
