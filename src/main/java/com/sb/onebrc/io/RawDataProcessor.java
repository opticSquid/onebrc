package com.sb.onebrc.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RawDataProcessor<I> implements ItemProcessor<I, I> {
    private static final Logger log = LoggerFactory.getLogger(RawDataProcessor.class);

    @Override
    @Nullable
    public I process(@NonNull I item) throws Exception {
        log.debug("Item Processed:{}", item.toString());
        return item;
    }

}
