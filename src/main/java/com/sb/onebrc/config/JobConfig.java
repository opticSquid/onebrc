package com.sb.onebrc.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.sb.onebrc.entity.RawData;
import com.sb.onebrc.io.RawDataProcessor;
import com.sb.onebrc.listener.JobCompletionNotificationListener;
import com.sb.onebrc.listener.ReadWriteStepExecutionListner;

@Configuration
public class JobConfig {
    private final JobRepository jobRepository;
    @Value("${onebrc.chunksize}")
    private Integer chunkSize;

    @Value("${onebrc.concurreny}")
    private Integer concurrenyLimit;

    public JobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    Job oneBillionRowChallengeJob(Step readAndInsert, JobCompletionNotificationListener listener) {
        return new JobBuilder("1 BRC", jobRepository)
                .listener(listener)
                .start(readAndInsert)
                .build();
    }

    TaskExecutor asyncExecutor(String name) {
        SimpleAsyncTaskExecutor tx = new SimpleAsyncTaskExecutor(name);
        tx.setConcurrencyLimit(concurrenyLimit);
        tx.setVirtualThreads(true);
        return tx;
    }

    @Bean
    Step readAndInsert(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
            FlatFileItemReader<RawData> reader,
            JdbcBatchItemWriter<RawData> writer, ReadWriteStepExecutionListner readWriteStepExecutionListner) {
        return new StepBuilder("read and write data", jobRepository)
                .<RawData, RawData>chunk(chunkSize, transactionManager)
                .reader(reader)
                .writer(writer)
                .listener(readWriteStepExecutionListner)
                .faultTolerant()
                .taskExecutor(asyncExecutor("oneBrc"))
                .build();
    }
}
