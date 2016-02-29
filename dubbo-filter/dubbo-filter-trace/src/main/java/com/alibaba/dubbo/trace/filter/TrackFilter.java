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
package com.alibaba.dubbo.trace.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.trace.Trace;

import java.util.Map;

import static com.alibaba.dubbo.trace.Trace.TRACE_ID;

/**
 * CacheFilter
 *
 * @author william.liangf
 */
@Activate( value = Constants.TRACE_KEY)
public class TrackFilter implements Filter {


    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Map<String, String> attachments = invocation.getAttachments();
        if (!attachments.containsKey(TRACE_ID)) {
            String traceId = Trace.getTraceId();
            attachments.put(TRACE_ID, traceId);
        } else {
            String traceId = attachments.get(TRACE_ID);
            Trace.applyTraceId(traceId);
        }


        return invoker.invoke(invocation);
    }

}
