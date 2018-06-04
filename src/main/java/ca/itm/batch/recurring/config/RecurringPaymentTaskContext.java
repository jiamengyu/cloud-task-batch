package ca.itm.batch.recurring.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import ca.itm.batch.recurring.exception.NotSufficientFundsException;

@Configuration
@EnableConfigurationProperties({ RecurringPaymentTaskProperties.class })
public class RecurringPaymentTaskContext {
	private static final Logger logger = LoggerFactory.getLogger(RecurringPaymentTaskContext.class);
    @Autowired
    private DataSource dataSource;
    
	@Bean
	RestTemplate restTemplate() {
	    return new RestTemplate();
	}	

	@Bean
	public RecurringPaymentTaskConfigurer getTaskConfigurer()
	{
	   return new RecurringPaymentTaskConfigurer(dataSource);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return new RecurringPaymentCommandLineRunner();
	}
	
	public static class RecurringPaymentCommandLineRunner implements CommandLineRunner {

		@Autowired
		private RecurringPaymentTaskProperties config;
		
		@Override
		public void run(String... strings) throws Exception {
			TimeZone.setDefault(TimeZone.getTimeZone("America/Toronto"));
			
			DateFormat dateFormat = new SimpleDateFormat(config.getFormat());
			logger.info("\n\n APPLICATION ==>: Recurring Payment Execution on " + dateFormat.format(new Date()) +"\n" );	
			logger.info("APPLICATION ==> testFlag: " + config.getTestFlag() );
			
			//Triger test for verifying error code only.
			if( config.getTestFlag().equalsIgnoreCase("true") ) throw new NotSufficientFundsException("Forced to fail");
		}
	}		
}
