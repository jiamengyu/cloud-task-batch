package ca.itm.batch.recurring.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.HttpStatusCodeException;

import ca.itm.batch.recurring.exception.NotSufficientFundsException;
import ca.itm.batch.recurring.model.PaymentInstance;
import ca.itm.batch.recurring.processor.PaymentInstanceProcessor;
import ca.itm.batch.recurring.processor.PaymentInstanceReader;
import ca.itm.batch.recurring.processor.PaymentInstanceWriter;

@Configuration
public class RecurringPaymentBatchConfiguration {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Step executePaymentInstance() {
    return  stepBuilderFactory.get("executePaymentInstance")
    		.<PaymentInstance, PaymentInstance> chunk(1)
    		.faultTolerant()
    		   .skip(NotSufficientFundsException.class)
    		   .retry(HttpStatusCodeException.class)
    		   .retryLimit(2)
    		.reader(itemReader())
    		.processor(itemProcessor())
    		.writer(itemWriter())
    		.listener(RPEStepExecutionListener())
    		.build();
  }

  @Bean
  public Job job(Step executePaymentInstance) throws Exception {
    return jobBuilderFactory.get("paymentExecution")
        .incrementer(new RunIdIncrementer())
        .start( executePaymentInstance )
        .build();
  }
  
  @Bean
  public ItemReader<PaymentInstance> itemReader() {
      return new PaymentInstanceReader(null, null );
  }
  
  @Bean
  public ItemProcessor<PaymentInstance, PaymentInstance> itemProcessor() {
      return new PaymentInstanceProcessor();
  }
  
  @Bean
  public ItemWriter<PaymentInstance> itemWriter() {
      return new PaymentInstanceWriter();
  }
  
  @Bean
  public StepExecutionListener RPEStepExecutionListener() {
    return new ca.itm.batch.recurring.processor.RPEStepExecutionListener();
  }
}