package com.mlf.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.mlf.concurrency.annocations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
@NotThreadSafe
public class immutableExample2 {
    public static final Integer a = 1;
    public static final String b = "2";
    public static  Map<Integer,Integer> map = Maps.newConcurrentMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1,3);
        log.info("{}",map.get(1));
    }

    private void test(final int a){
//        a=1
    }
}
