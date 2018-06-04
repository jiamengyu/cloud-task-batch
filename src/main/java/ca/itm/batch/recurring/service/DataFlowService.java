package ca.itm.batch.recurring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.dataflow.rest.client.DataFlowTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataFlowService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataFlowService.class);
    
	@Autowired
	DataFlowTemplate dataFlowTemplate;
    
    public long runCloudTask(String taskName) {
    	
        LOGGER.info("Trigger Cloud Task via SCDF server at: {}", new Date());
		taskName = "only_cloud_task";
		
		long taskId = dataFlowTemplate.taskOperations().launch(taskName, new HashMap<String, String>(), new ArrayList<>() );

		LOGGER.info("Customize job {} run Completed. Executionid:{}, Timestamp {} ", taskName, taskId, new Date());
        return taskId;
    }
    
    
}
