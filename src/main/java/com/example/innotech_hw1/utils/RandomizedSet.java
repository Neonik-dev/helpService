package com.example.innotech_hw1.utils;

import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
public class RandomizedSet<E> {
    private final Random random = new Random();
    private final Map<E, Integer> dict = new HashMap<>();
    private final List<E> arr = new ArrayList<>();
    private int lastIndex = -1;

    @SafeVarargs
    public RandomizedSet(E... values) {
        Arrays.stream(values).forEach(this::insert);
    }

    public synchronized boolean insert(E val) {
        if (dict.containsKey(val)) {
            return false;
        }

        arr.add(val);
        dict.put(val, ++lastIndex);
        return true;
    }

    public synchronized boolean remove(E val) {
        if (!dict.containsKey(val)) {
            return false;
        }
        int curIndex = dict.get(val);
        if (curIndex != lastIndex) {
            E lastVal = arr.get(lastIndex);
            arr.set(curIndex, lastVal);
            dict.put(lastVal, curIndex);
        }
        arr.remove(lastIndex);
        dict.remove(val);
        lastIndex--;
        return true;
    }

    public synchronized E getRandom() {
        if (lastIndex == -1) {
            throw new NullPointerException("Set is empty!");
        }
        return arr.get(random.nextInt(arr.size()));
    }
}