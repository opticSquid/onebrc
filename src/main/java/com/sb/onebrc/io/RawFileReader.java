package com.sb.onebrc.io;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.sb.onebrc.entity.RawData;

@Configuration
public class RawFileReader {
    @Bean
    FlatFileItemReader<RawData> reader() {
        FlatFileItemReader<RawData> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("measurements.csv"));
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
}
