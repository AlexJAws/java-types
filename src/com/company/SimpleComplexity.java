package com.company;

import java.util.*;

public class SimpleComplexity {
    private static final int ITERATIONS_OVER_ITEMS = 1000 * 1000;
    private static final int ITERATIONS_OVER_COLLECTIONS = 10;
    private Long timestamp;

    void run() {
        //runLists();
        //runSets();
        runMaps();
    }

    void runMaps() {
        getMaps().forEach((name, map) -> {

            t();
            for (int i = 0; i < ITERATIONS_OVER_ITEMS; i++) {
                map.put(i, i);
            }
            System.out.println(name + " add: " + t());

            t();
            for (int i = 0; i < ITERATIONS_OVER_ITEMS; i++) {
                map.containsKey(i);
            }
            System.out.println(name + " containsKey: " + t());

            t();
            for (int i = 0; i < ITERATIONS_OVER_ITEMS / 100; i++) {
                map.containsValue(i);
            }
            System.out.println(name + " containsValue: " + tscaled(100));

            t();
            map.forEach((x, y) -> {
                y = 1;
            });
            System.out.println(name + " forEach: " + t());

            t();
            for (int i = 0; i < ITERATIONS_OVER_ITEMS / 100; i++) {
                map.remove(i);
            }
            System.out.println(name + " remove: " + tscaled(100));

            if (map instanceof HashMap) {
                t();
                for (int i = 0; i < ITERATIONS_OVER_COLLECTIONS; i++) {
                    ((HashMap) map).clone();
                }
                System.out.println(name + " clone: " + t());
            }

            System.out.println("-----------------------");
        });
    }

    void runSets() {
        getSets().forEach((name, set) -> {

            t();
            for (int i = 0; i < ITERATIONS_OVER_ITEMS; i++) {
                set.add(i);
            }
            System.out.println(name + " add: " + t());

            t();
            for (int i = 0; i < ITERATIONS_OVER_ITEMS; i++) {
                set.contains(i);
            }
            System.out.println(name + " contains: " + t());

            System.out.println("-----------------------");
        });
    }

    void runLists() {
        getLists().forEach((name, list) -> {

            t();
            for (int i = 0; i < ITERATIONS_OVER_ITEMS; i++) {
                list.add(String.valueOf(i));
            }
            System.out.println(name + " add: " + t());

            t();
            for (int i = 0; i < ITERATIONS_OVER_ITEMS / 100; i++) {
                list.get(i);
            }
            System.out.println(name + " get: " + tscaled(100));

            t();
            for (int i = 0; i < ITERATIONS_OVER_ITEMS / 10000; i++) {
                list.contains(i);
            }
            System.out.println(name + " contains: " + tscaled(10000));

            System.out.println("-----------------------");
        });
    }

    HashMap<String, List> getLists() {
        HashMap<String, List> types = new LinkedHashMap<>();
        types.put("ArrayList", new ArrayList<>());
        types.put("LinkedList", new LinkedList<>());
        types.put("Vector", new Vector());
        types.put("Stack", new Stack());
        return types;
    }

    HashMap<String, Set> getSets() {
        HashMap<String, Set> types = new LinkedHashMap<>();
        types.put("HashSet", new HashSet<>());
        types.put("LinkedHashSet", new LinkedHashSet<>());
        types.put("TreeSet", new TreeSet<>());
        return types;
    }

    HashMap<String, AbstractMap> getMaps() {
        HashMap<String, AbstractMap> types = new LinkedHashMap<>();
        types.put("HashMap", new HashMap<Integer, Object>());
        types.put("LinkedHashMap", new LinkedHashMap<Integer, Object>());
        types.put("TreeMap", new TreeMap<Integer, Object>());
        return types;
    }

    String t() {
        return tscaled(1);
    }

    String tscaled(int scale) {
        if (timestamp == null) {
            timestamp = System.nanoTime();
        } else {
            long duration = ((System.nanoTime() - timestamp) * scale);
            timestamp = null;
            if (duration > (1000 * 1000 * 1000)) {
                return String.valueOf(duration / (1000 * 1000 * 1000)) + "s";
            } else {
                return String.valueOf(duration / (1000 * 1000)) + "ms";
            }
        }
        return "";
    }
}
