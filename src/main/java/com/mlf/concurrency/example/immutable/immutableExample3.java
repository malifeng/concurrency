package com.mlf.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mlf.concurrency.annocations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class immutableExample3 {
    public static ImmutableList list = ImmutableList.of(1,2,3,4);

    public final static ImmutableSet set = ImmutableSet.copyOf(list);

    public final static ImmutableMap<Integer, Integer> map = ImmutableMap.<Integer,Integer>builder()
            .put(1,2)
            .put(2,3)
            .put(3,4)
            .build();

    public static void main(String[] args) {
       map.put(1,4);
    }

}
