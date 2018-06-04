package ca.itm.batch.recurring.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 *  
 */
@Component
public class RPEStepExecutionListener extends StepExecutionListenerSupport {
	private static final Logger logger = LoggerFactory.getLogger(RPEStepExecutionListener.class);
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		logger.info("STEP EXEC ==> Recurring Payment Execution StepExecutionListener  ---- START");
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
	    logger.info("STEP EXEC ==> Recurring Payment Execution StepExecutionListener  ---- DONE ");
	    return null;
	}	
}