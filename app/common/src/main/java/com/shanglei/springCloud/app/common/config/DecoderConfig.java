package com.shanglei.springCloud.app.common.config;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.shanglei.springCloud.core.wrapper.GlobalResponseWrapper;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;

@Slf4j
/*@Configuration*/
public class DecoderConfig implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {

        Gson gson = new Gson();

        try {
            GlobalResponseWrapper responseWrapper = gson.fromJson(response.body().asReader(), type);
            return responseWrapper;
        } catch (JsonIOException e) {
            if (e.getCause() != null && e.getCause() instanceof IOException){
                throw IOException.class.cast(e.getCause());
            }
            throw e;
        }
    }


}
