package com.monster.commons.beans;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 合并常用的Bean工具类方法-并提升
 *
 * @author 刘照鸿
 * @version 1.0
 * @date 2022-7-17
 * @since JDK1.8
 */
public abstract class SpringPlusBeanUtils extends BeanUtils {
    /**
     * 将List<T>复制为List<V>
     * @param sources 源
     * @param taggers 目标
     * @param <T> 源类型
     * @param <V> 目标类型
     * @return List<V> 目标类型的集合
     */
    public static <T, V> List<V> copyProperties(List<T> sources, Supplier<V> taggers) {
        if (sources == null) {
            return new ArrayList<>();
        }
        return sources.stream().map(t -> {
            V v = taggers.get();
            BeanUtils.copyProperties(t, v);
            return v;
        }).collect(Collectors.toList());
    }
    /**
     * 将T复制为V
     * @param sources 源
     * @param taggers 目标
     * @param <T> 源类型
     * @param <V> 目标类型
     * @return 目标类型
     */
    public static <T, V> V copyProperties(T sources, Supplier<V> taggers) {
        V v = taggers.get();
        if (sources == null) {
            return v;
        }
        BeanUtils.copyProperties(sources, v);
        return v;
    }
}
