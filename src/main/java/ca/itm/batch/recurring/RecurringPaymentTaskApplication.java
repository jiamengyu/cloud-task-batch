package ca.itm.batch.recurring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

@SpringBootApplication
@EnableTask
@EnableBatchProcessing
public class RecurringPaymentTaskApplication {
	private static final Logger logger = LoggerFactory.getLogger(RecurringPaymentTaskApplication.class);
	
	public static void main(String[] args) {
		//TimeZone.setDefault(TimeZone.getTimeZone("America/Toronto"));
		String appName = SpringApplication.run(RecurringPaymentTaskApplication.class, args).getId();
		logger.info("Task [{}] is Completed...", appName);
	}

}
