package com.sb.onebrc.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobCompletionNotificationListener implements JobExecutionListener {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    // private final JdbcTemplate jdbcTemplate;

    // public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
    // this.jdbcTemplate = jdbcTemplate;
    // }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
            // jdbcTemplate
            // .query("SELECT first_name, last_name FROM people", new
            // DataClassRowMapper<>(RawData.class))
            // .forEach(person -> log.info("Found <{{}}> in the database.", person));
        }
    }
}
