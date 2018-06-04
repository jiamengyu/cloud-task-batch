package ca.itm.batch.recurring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.dataflow.rest.client.DataFlowTemplate;
import org.springframework.cloud.dataflow.rest.client.VndErrorResponseErrorHandler;
import org.springframework.cloud.dataflow.rest.client.support.ExecutionContextJacksonMixIn;
import org.springframework.cloud.dataflow.rest.client.support.ExitStatusJacksonMixIn;
import org.springframework.cloud.dataflow.rest.client.support.JobExecutionJacksonMixIn;
import org.springframework.cloud.dataflow.rest.client.support.JobInstanceJacksonMixIn;
import org.springframework.cloud.dataflow.rest.client.support.JobParameterJacksonMixIn;
import org.springframework.cloud.dataflow.rest.client.support.JobParametersJacksonMixIn;
import org.springframework.cloud.dataflow.rest.client.support.StepExecutionHistoryJacksonMixIn;
import org.springframework.cloud.dataflow.rest.client.support.StepExecutionJacksonMixIn;
import org.springframework.cloud.dataflow.rest.job.StepExecutionHistory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SCDFClientConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(SCDFClientConfig.class);
	
	@Value("${eps.dataflow.server.url}")
	String SCDF_SERVER_URL = "https://dataflow-server-xgz0.apps.pcf.devfg.rbc.com/";
	
	@Value("${eps.dataflow.server.id}")
	String SCDF_SERVER_USER = "taskadmin";
	
	@Value("${eps.dataflow.server.pwd}")
	String SCDF_SERVER_PWD = "password";

	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	
	@Bean
	public RestTemplate restTemplate() {
		
		//RestTemplate restTemplate = new RestTemplate();
		RestTemplate restTemplate = restTemplateBuilder.basicAuthorization(SCDF_SERVER_USER, SCDF_SERVER_PWD).build();
		
		restTemplate.setErrorHandler(new VndErrorResponseErrorHandler(restTemplate.getMessageConverters()));
		
		for ( HttpMessageConverter<?> converter: restTemplate.getMessageConverters() ) {
			if( converter instanceof MappingJackson2HttpMessageConverter ) {
				final MappingJackson2HttpMessageConverter jacksonConverter =
						(MappingJackson2HttpMessageConverter) converter;
				jacksonConverter.getObjectMapper()
					.registerModule(new Jackson2HalModule())
					.addMixIn(JobExecution.class, JobExecutionJacksonMixIn.class)
					.addMixIn(JobParameters.class, JobParametersJacksonMixIn.class)
					.addMixIn(JobParameter.class, JobParameterJacksonMixIn.class)
					.addMixIn(JobInstance.class, JobInstanceJacksonMixIn.class)
					.addMixIn(ExitStatus.class, ExitStatusJacksonMixIn.class)
					.addMixIn(StepExecution.class, StepExecutionJacksonMixIn.class)
					.addMixIn(ExecutionContext.class, ExecutionContextJacksonMixIn.class)
					.addMixIn(StepExecutionHistory.class, StepExecutionHistoryJacksonMixIn.class);
			}
		}
			
		return restTemplate;
	}
	
	@Bean
	public DataFlowTemplate dataFlowTemplate(RestTemplate restTemplate) throws URISyntaxException {
		final String SCDF_SERVER_URI = this.SCDF_SERVER_URL;
		
		DataFlowTemplate dataFlowTemplate = new DataFlowTemplate(
													new URI( SCDF_SERVER_URI ), restTemplate);
		
		LOGGER.debug("DataFlowTemplate client created for server {}", SCDF_SERVER_URI);
		
		return dataFlowTemplate;
	}

}

