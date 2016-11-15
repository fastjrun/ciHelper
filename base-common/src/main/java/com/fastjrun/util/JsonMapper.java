package com.fastjrun.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SuppressWarnings("serial")
public class JsonMapper extends ObjectMapper {

    public JsonMapper() {
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

}
