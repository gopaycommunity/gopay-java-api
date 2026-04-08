package cz.gopay.api.v3.model.payment.support;

/**
 * Format of the QR code image returned by GET /api/payments/payment/{id}/qr-payment
 */
public enum QrFormat {
    png,
    svg
}
