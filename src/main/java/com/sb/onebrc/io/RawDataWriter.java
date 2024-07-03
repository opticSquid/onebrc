package com.sb.onebrc.io;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sb.onebrc.entity.RawData;

@Configuration
public class RawDataWriter {
    @Bean
    JdbcBatchItemWriter<RawData> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<RawData>()
                .sql("INSERT INTO weather_data (station, temp) VALUES (:station, :temp)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }
}
