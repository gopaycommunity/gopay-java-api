package test.utils;

import org.junit.jupiter.api.Test;

public abstract class PaymentTest extends AbstractPaymentTests {

    @Test
    public void testHttpClientCreatePayment() {
        testConnectorCreatePayment(getConnector());
    }

    @Test
    public void testHttpClientPaymentRecurrency() {
        testPaymentRecurrency(getConnector());
    }

    @Test
    public void testHttpClientPaymentStatus() {
        testPaymentStatus(getConnector());
    }
    
    @Test
    public void testHttpClientPaymentPreAuthorization() {
        testPaymentPreAuthorization(getConnector());
    }

    //@Test
    /*public void testHttpClientVoidAuthorization() {
        testPaymentVoidAuthorization(getConnector());
    }*/

    //@Test
    /*public void testPaymentRefund() {
        testPaymentRefund(getConnector());
    }*/
    
    
   // @Test
    /*public void testEETREceiptFindByFilter() {
        testEETREceiptFindByFilter(getConnector());
    }*/
    
   // @Test
    /*public void testEETReceiptFindByPayment() {
        testEETReceiptFindByPayment(getConnector());
    }*/
    
    
    @Test
    public void testPaymentInstrumentRoot(){
        testPaymentInstrumentRoot(getConnector());
    }
    
    @Test
    public void testGenerateStatementHttp() {
        testGenerateStatement(getConnector());
    }
    
}
