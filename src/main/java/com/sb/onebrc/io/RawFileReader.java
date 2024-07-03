package com.sb.onebrc.io;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.sb.onebrc.entity.RawData;

@Configuration
public class RawFileReader {
    @Bean
    FlatFileItemReader<RawData> reader() {
        return new FlatFileItemReaderBuilder<RawData>()
                .name("rawDataReader")
                .resource(new ClassPathResource("weather_stations.csv"))
                .delimited()
                .names("station", "temp")
                .targetType(RawData.class)
                .build();
    }
}
