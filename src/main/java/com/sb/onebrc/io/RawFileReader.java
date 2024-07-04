package com.sb.onebrc.io;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.sb.onebrc.entity.RawData;

@Configuration
public class RawFileReader {
    @Bean
    FlatFileItemReader<RawData> reader(@Value("#{jobParameters['input.file']}") String inputFile) {
        FlatFileItemReader<RawData> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(inputFile));
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
    FlatFileItemReader<RawData> originalReader(@Value("#{jobParameters['input.file']}") String inputFile) {
        return new FlatFileItemReaderBuilder<RawData>()
                .name("originalRawDataReader")
                .resource(new FileSystemResource(inputFile))
                .delimited()
                .names("station", "temp")
                .targetType(RawData.class)
                .build();
    }

}
