package com.monster.commons.generate.util;

/**
 * @Author: LiuZhaoHong
 * @Date: 2021/8/14
 * @Version: 1.0
 */
public class SymbolUtil {

    /**
     * 根据指定换行数量换行
     * @param num
     * @return
     */
    public static String lineFeedByNum(int num) {
        return getSpecifiedNumberSymbol("\r\n", num);
    }

    /**
     * 换一行
     * @return
     */
    public static String lineFeedByOne() {
        return lineFeedByNum(1);
    }

    /**
     * 换两行
     * @return
     */
    public static String intervalLineFeed() {
        return lineFeedByNum(2);
    }

    /**
     * 指定数量的空格
     * @param num
     * @return
     */
    public static String spaceByNum(int num) {
        return getSpecifiedNumberSymbol(" ", num);
    }

    /**
     * 一个空格
     * @return
     */
    public static String spaceByOne() {
        return spaceByNum(1);
    }

    /**
     * 两个空格
     * @return
     */
    public static String spaceByTwo() {
        return spaceByNum(2);
    }

    /**
     * 获取指定数量的Tab
     * @param num
     * @return
     */
    public static String getTabByNum(int num) {
        return getSpecifiedNumberSymbol("\t", num);
    }

    /**
     * 一个Tab
     * @return
     */
    public static String getTabByOne() {
        return getTabByNum(1);
    }

    /**
     * 两个Tab
     * @return
     */
    public static String getTabByTwo() {
        return getTabByNum(2);
    }

    /**
     * 获取指定数量的"
     * @param num
     * @return
     */
    public static String getQuotationMarksByNum(int num) {
        return getSpecifiedNumberSymbol("\"", num);
    }

    /**
     * 一个"
     * @return
     */
    public static String getQuotationMarksByOne() {
        return getQuotationMarksByNum(1);
    }

    /**
     * 获取指定数量的制表符
     * @param str
     * @param num
     * @return
     */
    public static String getSpecifiedNumberSymbol(String str, int num) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < num; i++) {
            s.append(str);
        }
        return s.toString();
    }

}
