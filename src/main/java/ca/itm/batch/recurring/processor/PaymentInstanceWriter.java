package ca.itm.batch.recurring.processor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ca.itm.batch.recurring.RecurringPaymentTaskApplication;
import ca.itm.batch.recurring.config.RecurringPaymentTaskProperties;
import ca.itm.batch.recurring.model.PaymentInstance;

public class PaymentInstanceWriter implements ItemWriter<PaymentInstance>{
	private static final Logger logger = LoggerFactory.getLogger(PaymentInstanceWriter.class);
	
	@Autowired
	private RecurringPaymentTaskProperties config;
	
	@Autowired
	private RestTemplate restTemplate; 
	
	@Override
	public void write(List<? extends PaymentInstance> items) throws Exception {
		if (items!= null && !items.isEmpty()) {
          for(PaymentInstance request : items) {
        	  logger.info("APPLICATION ==> Update payment instance execution STATUS for [{}] ", request.getName());
          }
		}
//        final String uri = config.getPaymentInstanceProviderUrl();
//        final HttpHeaders headers = new HttpHeaders();
//        if (items!= null && !items.isEmpty()) {
//            for(PaymentInstance request : items) {
//               try {
//					  @SuppressWarnings({ "unchecked", "rawtypes" })
//					  HttpEntity entity = new HttpEntity(request, headers);
//					  @SuppressWarnings("rawtypes")
//					  ResponseEntity response = restTemplate.exchange( uri, 
//							  										   HttpMethod.POST, 
//							  										   entity, 
//							  										   String.class);
//					  if (response.getStatusCode() == HttpStatus.OK) {
//	                       logger.info("\n Post Success :");
//	                  }else {
//	                	   logger.info("\n Post Failed ");
//	                  }
//
//                } catch(HttpClientErrorException ex) {
//                       logger.error("ERROR in ItemWriter : ",ex);
//                } catch(IllegalArgumentException illegalEx){
//                       logger.error("ERROR in ItemWriter : ",illegalEx);
//                }
//             }
//	     }
     }
}
