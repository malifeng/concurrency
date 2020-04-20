package com.mlf.concurrency.example.syncContainer;

import com.mlf.concurrency.annocations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;


@Slf4j
@NotThreadSafe
public class VectorExample3 {
    private static Vector<Integer> vector = new Vector<>();


    // java.util.ConcurrentModificationException
    public static void test1(Vector<Integer> v1){
        for (Integer i:v1) {
            if(i.equals(3)){
                v1.remove(i);
            }
        }
    }

    // java.util.ConcurrentModificationException
    public static void test2(Vector<Integer> v2){
        Iterator<Integer> iterator = v2.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if(next.equals(3)){
                v2.remove(next);
            }
        }
    }

    public static void test3(Vector<Integer> v3){
        for (int i = 0; i < v3.size(); i++) {
            if(v3.get(i).equals(3)){
                v3.remove(i);
            }
        }
    }

    public static void main(String[] args)  {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test3(vector);
    }

}
