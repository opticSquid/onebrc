package com.sb.onebrc.io;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.sb.onebrc.entity.RawData;

@Configuration
public class RawFileReader {
    @Bean
    FlatFileItemReader<RawData> reader() {
        FlatFileItemReader<RawData> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("measurements.csv"));
        reader.setLineMapper(new DefaultLineMapper<RawData>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] { "station", "temp" });
                        setDelimiter(";");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<RawData>() {
                    {
                        setTargetType(RawData.class);
                    }
                });
            }
        });
        return reader;
    }

    @Bean
    FlatFileItemReader<RawData> originalReader() {
        return new FlatFileItemReaderBuilder<RawData>()
                .name("originalRawDataReader")
                .resource(new FileSystemResource("mesasurements.csv"))
                .delimited()
                .names("station", "temp")
                .targetType(RawData.class)
                .build();
    }

}
