/*
 * Copyright 1999-2012 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.examples.trace;

import com.alibaba.dubbo.examples.trace.api.TraceService;
import com.alibaba.dubbo.trace.Trace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

/**
 * TraceConsumer
 * 
 * @author william.liangf
 */
public class TraceConsumer {
    public static final Logger logger = LoggerFactory.getLogger(TraceConsumer.class);
    public static void main(String[] args) throws Exception {
        String config = TraceConsumer.class.getPackage().getName().replace('.', '/') + "/trace-consumer.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        context.start();

        String traceId = UUID.randomUUID().toString();

        Trace.applyTraceId(traceId);

        logger.info("trace id = {}", traceId);

        TraceService traceService = (TraceService)context.getBean("traceService");

        String next = traceService.findCache(traceId);

        logger.info("got trace id = {}", next);

        logger.info("got trace id = trace id ? {}", next.equals(traceId));
    }

}
