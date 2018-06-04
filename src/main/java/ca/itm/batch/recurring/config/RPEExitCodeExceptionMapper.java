package ca.itm.batch.recurring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.itm.batch.recurring.exception.NotSufficientFundsException;

@Configuration
public class RPEExitCodeExceptionMapper {
	private static final Logger logger = LoggerFactory.getLogger(RPEExitCodeExceptionMapper.class);

	@Bean
	ExitCodeExceptionMapper exitCodeExceptionMapper() {
		
		return exception -> {
            if (exception.getCause() instanceof NotSufficientFundsException) {
            	logger.debug("Mapping exit code to 101 per RPEException");
                return 101;
            }
            return 1;
        };
	}
}
