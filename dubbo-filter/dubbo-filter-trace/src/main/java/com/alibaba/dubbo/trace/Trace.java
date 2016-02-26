package com.alibaba.dubbo.trace;

import com.alibaba.dubbo.trace.support.TraceHolder;

import java.util.UUID;

/**
 * Created by shylock on 16-2-26.
 */
public class Trace {

    public static final String TRACE_ID = "traceId";

    private static TraceHolder holder = new TraceHolder();

    public static String getTraceId() {
        String traceId = (String) get(TRACE_ID);
        if (traceId == null) {
            traceId = UUID.randomUUID().toString();
            put(TRACE_ID, traceId);
        }
        return traceId;
    }

    public static void applyTraceId(String traceId) {
        put(TRACE_ID, traceId);
    }

    private static void put(String name, Object value) {
        holder.put(name, value);
    }

    private static Object get(String name) {
        return holder.get(name);
    }

}
