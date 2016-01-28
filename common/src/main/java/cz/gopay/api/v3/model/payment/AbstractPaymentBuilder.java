/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.gopay.api.v3.model.payment;

import cz.gopay.api.v3.Builder;
import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.payment.support.AdditionalParam;
import cz.gopay.api.v3.model.payment.support.OrderItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author František Sichinger
 */
public abstract class AbstractPaymentBuilder<T, U extends AbstractPaymentBuilder<T,U>> implements Builder<T> {

    protected Long amount;

    protected Currency currency;

    protected String orderNumber;

    protected String orderDescription;

    protected List<OrderItem> items = new ArrayList<>();

    protected List<AdditionalParam> additionalParams = new ArrayList<>();

    protected abstract U getInstance();

    public U order(String orderNumber, Long amount, Currency currency, String orderDescription) {
        this.orderNumber = orderNumber;
        this.amount = amount;
        this.currency = currency;
        this.orderDescription = orderDescription;
        return getInstance();
    }

    public U addItems(Collection<OrderItem> items) {
        this.items.addAll(items);
        return getInstance();
    }

    public U addItem(String name, Long amount, Long fee, Long count) {
        this.items.add(OrderItem.of(name, amount, fee, count));
        return getInstance();
    }

    public U addItem(OrderItem item) {
        this.items.add(item);
        return getInstance();
    }

    public U addAdditionalParameters(Collection<AdditionalParam> params) {
        this.additionalParams.addAll(params);
        return getInstance();
    }

    public U addAdditionalParameter(String key, String value) {
        return addAdditionalParameter(AdditionalParam.of(key, value));
    }

    public U addAdditionalParameter(AdditionalParam param) {
        this.additionalParams.add(param);
        return getInstance();
    }
}
