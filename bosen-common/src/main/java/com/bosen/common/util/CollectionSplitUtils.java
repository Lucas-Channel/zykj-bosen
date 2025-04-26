package com.bosen.common.util;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 集合分割工具
 * @author lucas
 */
public class CollectionSplitUtils {

    /**
     * 集合分割
     * @param collection 集合
     * @param splitSize  分割数
     */
    public static <T> List<List<T>> split(List<T> collection, int splitSize){
        if(CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }
        int maxSize = collection.size()/splitSize + 1;
        return Stream.iterate(0, o -> o + 1)
                .limit(maxSize)
                .parallel()
                .map(index -> collection.parallelStream().skip((long) index * splitSize).limit(splitSize).collect(Collectors.toList()))
                .collect(Collectors.toList());

    }

}
