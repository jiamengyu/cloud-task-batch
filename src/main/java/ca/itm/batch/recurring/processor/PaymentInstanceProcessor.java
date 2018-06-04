package ca.itm.batch.recurring.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import ca.itm.batch.recurring.model.PaymentInstance;

public class PaymentInstanceProcessor implements ItemProcessor<PaymentInstance, PaymentInstance>  {
	private static final Logger logger = LoggerFactory.getLogger(PaymentInstanceProcessor.class);
	
	@Override
	public PaymentInstance process(PaymentInstance item) throws Exception {
		logger.info("APPLICATION ==> Process payment instance for [{}]", item.getName() );
		// execute payment logic for one single instance.
		// TODO
		return item;
	}

}
