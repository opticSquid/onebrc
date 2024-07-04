package com.sb.onebrc.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// @Component
// @EnableScheduling
public class OnebrcLauncher {
    private static final Logger log = LoggerFactory.getLogger(OnebrcLauncher.class);
    private final JobLauncher jobLauncher;
    private final Job oneBillionRowChallengeJob;
    @Value("${onebrc.file}")
    private String file;

    public OnebrcLauncher(JobLauncher jobLauncher, Job oneBillionRowChallengeJob) {
        this.jobLauncher = jobLauncher;
        this.oneBillionRowChallengeJob = oneBillionRowChallengeJob;
    }

    // @Scheduled(cron = "${onebrc.run.cron}", zone = "GMT")
    public void runJob() {
        launchJob();
    }

    public void launchJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("file", file)
                    .toJobParameters();
            jobLauncher.run(oneBillionRowChallengeJob, jobParameters);
        } catch (Exception ex) {
            log.error("could not launch 1 brc job", ex);
        }

    }
}
