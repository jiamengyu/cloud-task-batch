package ca.itm.batch.recurring.config;

import javax.sql.DataSource;

import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;

public class RecurringPaymentTaskConfigurer extends	DefaultTaskConfigurer{

    public RecurringPaymentTaskConfigurer(DataSource dataSource) {
        super(dataSource);
    }
    
}
