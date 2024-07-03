package com.sb.onebrc.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class ReadWriteStepExecutionListner implements StepExecutionListener {
    private static final Logger log = LoggerFactory.getLogger(ReadWriteStepExecutionListner.class);

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("NUmber of rows read: {}", stepExecution.getReadCount());
        return null;
    }
}
