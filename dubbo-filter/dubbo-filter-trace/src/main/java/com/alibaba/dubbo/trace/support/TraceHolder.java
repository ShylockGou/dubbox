package com.alibaba.dubbo.trace.support;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by shylock on 16-2-26.
 */
public class TraceHolder {
    private InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();

    public TraceHolder() {
    }

    public void put(String key, Object val) {
        if(key == null) {
            throw new IllegalArgumentException("key cannot be null");
        } else {
            HashMap map = (HashMap)this.inheritableThreadLocal.get();
            if(map == null) {
                map = new HashMap();
                this.inheritableThreadLocal.set(map);
            }

            map.put(key, val);
        }
    }

    public Object get(String key) {
        HashMap hashMap = (HashMap)this.inheritableThreadLocal.get();
        return hashMap != null && key != null?(String)hashMap.get(key):null;
    }

    public void remove(String key) {
        HashMap map = (HashMap)this.inheritableThreadLocal.get();
        if(map != null) {
            map.remove(key);
        }

    }

    public void clear() {
        HashMap hashMap = (HashMap)this.inheritableThreadLocal.get();
        if(hashMap != null) {
            hashMap.clear();
            this.inheritableThreadLocal.remove();
        }

    }

    public Set getKeys() {
        HashMap hashMap = (HashMap)this.inheritableThreadLocal.get();
        return hashMap != null?hashMap.keySet():null;
    }

    public Map getCopyOfContextMap() {
        HashMap hashMap = (HashMap)this.inheritableThreadLocal.get();
        return hashMap != null?new HashMap(hashMap):null;
    }
}
