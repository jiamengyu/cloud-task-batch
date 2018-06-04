package ca.itm.batch.recurring.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ca.itm.batch.recurring.RecurringPaymentTaskApplication;
import ca.itm.batch.recurring.model.PaymentInstance;

import java.util.Arrays;
import java.util.List;
 
public class PaymentInstanceReader implements ItemReader<PaymentInstance> {
	private static final Logger logger = LoggerFactory.getLogger(PaymentInstanceReader.class);
 
    private final String apiUrl;
    private final RestTemplate restTemplate;
 
    private int nextPaymentInstanceIndex;
    private List<PaymentInstance> paymentData;
 
    public PaymentInstanceReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        
        nextPaymentInstanceIndex = 0;
    }
 
    @Override
    public PaymentInstance read() throws Exception {
    	logger.info("-->Demo Only<-- ");
    	logger.info("APPLICATION ==> ItemReader per every single payment instance ---------> ");
        if (paymentDataIsNotInitialized()) {
            paymentData = fetchPaymentInstancesFromPDAR();
        }
 
        PaymentInstance nextPaymentInstance = null;
 
        if (nextPaymentInstanceIndex < paymentData.size()) {
            nextPaymentInstance = paymentData.get(nextPaymentInstanceIndex);
            nextPaymentInstanceIndex++;
        }
 
        return nextPaymentInstance;
    }
 
    private boolean paymentDataIsNotInitialized() {
        return this.paymentData == null;
    }
 
    private List<PaymentInstance> fetchPaymentInstancesFromPDAR() {
//        ResponseEntity<PaymentInstance[]> response = restTemplate.getForEntity(
//            apiUrl, 
//            PaymentInstance[].class
//        );
//        
//        PaymentInstance[] paymentData = response.getBody();
    	
    	PaymentInstance pay1 = new PaymentInstance();
    	pay1.setName("John");
    	PaymentInstance pay2 = new PaymentInstance();
    	PaymentInstance pay3 = new PaymentInstance();
    	pay2.setName("Eric");
    	pay3.setName("Patric");
    	PaymentInstance[] paymentData = {pay1, pay2, pay3 };
    	
        return Arrays.asList(paymentData);
    }
}