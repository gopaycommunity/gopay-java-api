package cz.gopay.api.test.validation;

import cz.gopay.api.test.utils.TestUtils;
import cz.gopay.api.v3.GPClientException;
import cz.gopay.api.v3.IGPConnector;
import cz.gopay.api.v3.impl.resteasy.ResteasyGPConnector;
import cz.gopay.api.v3.model.access.OAuth;
import cz.gopay.api.v3.model.supercash.SubType;
import cz.gopay.api.v3.model.supercash.SupercashBatch;
import cz.gopay.api.v3.model.supercash.SupercashBatchRequest;
import cz.gopay.api.v3.model.supercash.SupercashBatchResult;
import cz.gopay.api.v3.model.supercash.SupercashBatchState;
import cz.gopay.api.v3.model.supercash.SupercashCoupon;
import cz.gopay.api.v3.model.supercash.SupercashCouponRequest;
import cz.gopay.api.v3.model.supercash.SupercashPayment;
import cz.gopay.api.v3.model.supercash.SupercashRequestBuilder;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

public class SupercashTests {
	private static final Logger logger = Logger.getLogger(SupercashTests.class);
	
	@Test
	public void testCreateSupercashCoupon() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		SupercashRequestBuilder couponRequestBuilder = new SupercashRequestBuilder();
		
		SupercashCouponRequest couponRequest = couponRequestBuilder
				.forGoId(TestUtils.GOID)
				.subType(SubType.POSTPAID)
				.withAmount(100L)
				.customId("ID-123457")
				.orderNumber("1")
				.orderDescription("Supercash Coupon Test")
				.withBuyerEmail("zakaznik@example.com")
				.withBuyerPhone("+420777123456")
				.validTo(new Date(2018, 12, 31))
				.notificationUrl("http://www.example-notify.cz/supercash")
				.buildCouponRequest();
		
		SupercashCoupon coupon = null;
		
		try {
			coupon = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.createSupercashCoupon(couponRequest);
			
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		Assert.assertNotNull(coupon.getSupercashCouponId());
		System.out.println(coupon.toString());
	}
	
	@Test
	public void testCreateSupercashCouponBatch() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		SupercashRequestBuilder batchItemBuilder = new SupercashRequestBuilder();
		
		SupercashRequestBuilder batchRequestBuilder = new SupercashRequestBuilder();
		
		SupercashBatchRequest batchRequest = batchRequestBuilder
				.forGoId(TestUtils.GOID)
				.batchNotificationUrl("http://www.notify.cz/super")
				.withDefaults(batchItemBuilder
						.subType(SubType.POSTPAID)
						.withAmounts(Arrays.asList(300L, 400L, 500L, 600L, 700L, 800L, 900L, 1000L))
						.orderDescription("Supercash Coupon Batch Test")
						.buildBatchItem()
				)
				.addCoupon(batchItemBuilder
						.reset()
						.withBuyerEmail("zakaznik1@example.com")
						.customId("ID-123457")
						.withBuyerPhone("+420777666111")
						.withAmounts(Arrays.asList(100L))
						.buildBatchItem()
				)
				.addCoupon(batchItemBuilder
						.reset()
						.withBuyerEmail("zakaznik2@example.com")
						.customId("ID-123458")
						.withBuyerPhone("+420777666222")
						.withAmounts(Arrays.asList(200L))
						.buildBatchItem()
				)
				.addCoupon(batchItemBuilder
						.reset()
						.withBuyerEmail("zakaznik3@example.com")
						.customId("ID-123459")
						.withBuyerPhone("+420777666333")
						.withAmounts(Arrays.asList(300L))
						.buildBatchItem()
				)
				.buildBatchRequest();
		
		SupercashBatchResult batchResult = null;
		
		try {
			batchResult = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.createSupercashCouponBatch(batchRequest);
			
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		
		Assert.assertNotNull(batchResult.getBatchRequestId());
		System.out.println(batchResult.toString());
	}
	
	@Test
	public void testGetSupercashCouponBatchStatus() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		long batchId = 961667719;
		SupercashBatchState batchState = null;
		
		try {
			batchState = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.getSupercashCouponBatchStatus(batchId);
			
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		Assert.assertNotNull(batchState);
		System.out.println(batchState.toString());
	}
	
	@Test
	public void testGetSupercashCouponBatch() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		long batchId = 961667719;
		
		SupercashBatch supercashBatch = null;
		
		try {
			supercashBatch = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.getSupercashCouponBatch(8712700986L, batchId);
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		Assert.assertNotNull(supercashBatch);
		System.out.println(supercashBatch.toString());
	}
	
	@Test
	public void testFindSupercashCoupons() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
//		long paymentSessionIds = 3050857992L;
		Long[] paymentSessionIds = new Long[] { 3050857992L, 3050858018L };
		
		SupercashBatch supercashBatch = null;
		
		try {
			supercashBatch = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.findSupercashCoupons(8712700986L, paymentSessionIds);
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		Assert.assertNotNull(supercashBatch);
		System.out.println(supercashBatch.toString());
	}
	
	@Test
	public void testGetSupercashCoupon() {
		IGPConnector connector = ResteasyGPConnector.build(TestUtils.API_URL);
		
		long couponId = 100154175;
		
		SupercashPayment supercashPayment = null;
		
		try {
			supercashPayment = connector
					.getAppToken(TestUtils.CLIENT_ID, TestUtils.CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL)
					.getSupercashCoupon(couponId);
		} catch (GPClientException e) {
			TestUtils.handleException(e, logger);
		}
		Assert.assertNotNull(supercashPayment);
		System.out.println(supercashPayment.toString());
	}
	
	
}
