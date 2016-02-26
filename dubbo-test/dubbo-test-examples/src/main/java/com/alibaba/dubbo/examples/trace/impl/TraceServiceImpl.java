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
package com.alibaba.dubbo.examples.trace.impl;

import com.alibaba.dubbo.examples.trace.api.TraceService;
import com.alibaba.dubbo.trace.Trace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ValidationServiceImpl
 * 
 * @author william.liangf
 */
public class TraceServiceImpl implements TraceService {


    public static final Logger logger = LoggerFactory.getLogger(TraceService.class);

    public String findCache(String id) {
        String traceId = Trace.getTraceId();

        logger.info("traceId = {}", traceId);

        return traceId;
    }

}
