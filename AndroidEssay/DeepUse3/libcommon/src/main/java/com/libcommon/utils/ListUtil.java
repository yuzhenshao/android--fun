package com.libcommon.utils;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author yz @date 2020-03-30
 */
public class ListUtil {
    public ListUtil() {
    }

    public static boolean isEmpty(List<?> list) {
        return null == list || list.isEmpty();
    }

    public static void removeDuplicate(List<String> list) {
        LinkedHashSet<String> set = new LinkedHashSet(list.size());
        set.addAll(list);
        list.clear();
        list.addAll(set);
    }

    public static void removeNullData(List<?> list) {
        for (int i = list.size() - 1; i >= 0; --i) {
            if (null == list.get(i)) {
                list.remove(i);
            }
        }

    }
}
