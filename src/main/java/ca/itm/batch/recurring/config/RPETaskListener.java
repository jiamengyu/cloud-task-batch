package ca.itm.batch.recurring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RPETaskListener {
	private static final Logger logger = LoggerFactory.getLogger(RPETaskListener.class);
	
	@BeforeTask
	public void startTask(TaskExecution taskExecution) {
		logger.info("Task started with arguments: {}"+ taskExecution.getArguments().toString() );
	}

	@AfterTask
	public void afterExec(TaskExecution taskExecution) {
		
		if (taskExecution.getExitCode() != 0 ) {
			logger.error("@@@ Task Completed with ERRORS @@@");
			taskExecution.setExitMessage("FAILED -> AppOps Alert");
		} else {
			logger.info("Task Completed successfully!");
		}
	}

	@FailedTask
	public void taskFailed(TaskExecution taskExecution, Throwable throwable) {
		logger.error("Task Failed! Error Code: "+taskExecution.getExitCode());
		logger.error("Task Failed! Error Message: "+ throwable.getCause().getMessage());
	}
}
