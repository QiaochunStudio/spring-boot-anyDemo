package com.hjt.javaDemo.number;

import java.util.regex.*;

public class NumberExtractor {
    public static int extractNumber(String input) {
        // 定义正则表达式，匹配一个或多个数字
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            // 如果找到数字，将其转换为整数并返回
            return Integer.parseInt(matcher.group());
        } else {
            // 如果没有找到数字，返回默认值1000
            return 1000;
        }
    }

    public static void main(String[] args) {
        String input1 = "abc123def";
        String input2 = "xyz";

        int number1 = extractNumber(input1);
        int number2 = extractNumber(input2);

        System.out.println("Number 1: " + number1); // 输出: Number 1: 123
        System.out.println("Number 2: " + number2); // 输出: Number 2: 1000
    }
}

