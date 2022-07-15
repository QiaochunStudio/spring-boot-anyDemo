package com.hjt.javaDemo.collection;

import com.hjt.javaDemo.collection.domain.Pig;
import com.hjt.javaDemo.collection.domain.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/***
 * 集合类demo
 * @author hjt
 */
public class StreamDemo {

    public static void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");

        System.out.println("map普通方式遍历:");
        for (String key : map.keySet()) {
            System.out.println("k=" + key + "，v=" + map.get(key));
        }

        //使用Lambda进行遍历:
        System.out.println("map拉姆达表达式遍历:");
        map.forEach((k, v) -> {
            System.out.println("k=" + k + "，v=" + v);
        });
    }

    /***
     * List也同理，不过List还可以通过双冒号运算符遍历:
     */
    public static void testList() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("bb");
        list.add("ccc");
        list.add("dddd");
        System.out.println("list拉姆达表达式遍历:");

        list.forEach(v -> {
            System.out.println(v);
        });
        System.out.println("list双冒号运算符遍历:");
        //和上面的一样
        list.stream().forEach(temp -> {
            System.out.println();
        });

        //重新转list
        list.stream().map(temp1 -> {
            //这里可以进行处理
            return temp1;
        }).collect(Collectors.toList());
    }

    /***
     * stream中的demo
     */
    public static void testStream() {
        List<String> list = Arrays.asList("张三", "李四", "王五", "xuwujing");
        System.out.println("过滤之前:" + list);
        List<String> result = new ArrayList<>();
        for (String str : list) {
            if (!"李四".equals(str)) {
                result.add(str);
            }
        }
        System.out.println("用原先的List过滤之后:" + result);


        //使用Steam方式进行过滤：
        List<String> result2 = list.stream().filter(str -> !"李四".equals(str)).collect(Collectors.toList());
        System.out.println("stream 过滤之后:" + result2);

        //Stream流的filter使用
        List<String> list1 = Arrays.asList("张三", "李四", "王五", "xuwujing");
        // list1.contains("李四");
        String result3 = list1.stream().filter(str -> "李四".equals(str)).findAny().orElse("找不到!");
        String result4 = list1.stream().filter(str -> "李二".equals(str)).findAny().orElse("找不到!");

        System.out.println("stream 过滤之后 2:" + result3);
        System.out.println("stream 过滤之后 3:" + result4);
        //stream 过滤之后 2:李四
        //stream 过滤之后 3:找不到!


    }

    /***
     * 转成新的list 并做处理
     */
    public static void testStreamMap() {
        //转换大写
        List<String> list3 = Arrays.asList("zhangSan", "liSi", "wangWu");
        System.out.println("转换大写转换之前的数据:" + list3);
        List<String> list4 = list3.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("转换大写转换之后的数据:" + list4);
        // 转换之后的数据:[ZHANGSAN, LISI,WANGWU]

        //转换数据类型
        List<String> list31 = Arrays.asList("1", "2", "3");
        System.out.println("转换数据转换之前的数据:" + list31);
        List<Integer> list41 = list31.stream().map(Integer::valueOf).collect(Collectors.toList());
        System.out.println("转换数据转换之后的数据:" + list41);
        // [1, 2, 3]

        //获取平方
        List<Integer> list5 = Arrays.asList(new Integer[]{1, 2, 3, 4, 5});
        List<Integer> list6 = list5.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println("平方的数据:" + list6);
        // [1, 4, 9, 16, 25]


    }

    /***
     * 以某某字段分裂
     */
    public static void flatMap() {
        String worlds = "The way of the future";
        List<String> list7 = new ArrayList<>();
        list7.add(worlds);
        List<String> list8 = list7.stream().flatMap(str -> Stream.of(str.split(" ")))
                .filter(world -> world.length() > 0).collect(Collectors.toList());
        System.out.println("单词:");
        list8.forEach(System.out::println);
        // 单词:
        // The
        // way
        // of
        // the
        // future
    }

    /***
     * 排序
     */
    public static void testStreamSort() {
        ArrayList<User> users = new ArrayList<>();
        User hjt = new User(1, "hjt", 12);
        User hjt1 = new User(2, "hjt1", 123);
        User hjt2 = new User(3, "hjt2", 124);
        users.add(hjt2);
        users.add(hjt1);
        users.add(hjt);
        //普通的排序取值
        List<User> list11 = users.stream().sorted((u1, u2) -> u1.getUserName().compareTo(u2.getUserName())).limit(3)
                .collect(Collectors.toList());
        System.out.println("排序之后的数据:" + list11);
        //优化排序取值
        List<User> list12 = users.stream().limit(3).sorted((u1, u2) -> u1.getUserName().compareTo(u2.getUserName()))
                .collect(Collectors.toList());
        System.out.println("优化排序之后的数据:" + list12);
        //排序之后的数据:[{"id":1,"name":"pancm1"}, {"id":2,"name":"pancm2"}, {"id":3,"name":"pancm3"}]
        //优化排序之后的数据:[{"id":1,"name":"pancm1"}, {"id":2,"name":"pancm2"}, {"id":3,"name":"pancm3"}]
    }

    /***
     * 去重
     */
    public static void testStreamDistinct() {
        String lines = "good good study day day up";
        List<String> list14 = new ArrayList<String>();
        list14.add(lines);
        List<String> words = list14.stream().flatMap(line -> Stream.of(line.split(" "))).filter(word -> word.length() > 0)
                //distinct 去重
                // sorted  排序
                .distinct().sorted().collect(Collectors.toList());
        System.out.println("去重复之后:" + words);
        //去重复之后:[day, good, study, up]
    }

    /***
     * Stream流的Match使用
     */
    public static void testStreamMatch() {
        ArrayList<User> lists = new ArrayList<>();
        User hjt = new User(1, "hjt", 12);
        User hjt1 = new User(2, "hjt1", 123);
        User hjt2 = new User(3, "hjt2", 124);
        lists.add(hjt);
        lists.add(hjt1);
        lists.add(hjt2);
        boolean all = lists.stream().allMatch(u -> u.getId() > 3);
        System.out.println("是否都大于3:" + all);
        boolean any = lists.stream().anyMatch(u -> u.getId() > 3);
        System.out.println("是否有一个大于3:" + any);
        boolean none = lists.stream().noneMatch(u -> u.getId() > 3);
        System.out.println("是否没有一个大于3的:" + none);
        //	是否都大于3:false
        //	是否有一个大于3:true
        //	是否没有一个大于3的:false
    }

    /***
     *reduce 主要作用是把 Stream 元素组合起来进行操作。
     */
    public static void testStreamReduce() {
        //示例一：字符串连接
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println("字符串拼接:" + concat);

        //求和
        // 求和, 无起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        System.out.println("无起始值求和:" + sumValue);
        // 求和, 有起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(1, Integer::sum);
        System.out.println("有起始值求和:" + sumValue);
        //	有无起始值求和:10
        //	有起始值求和:11
    }

    /***
     * Stream流的groupingBy/partitioningBy使用
     * groupingBy：分组排序；
     * partitioningBy：分区排序。
     */
    public static void testStreamImport() {
        // 示例一：分组排序
        ArrayList<Pig> pigs = new ArrayList<>();
        pigs.add(new Pig(1, "猪爸爸", 31, "M", false));
        pigs.add(new Pig(2, "猪妈妈", 28, "F", true));
        pigs.add(new Pig(3, "乔治", 2, "M", false));
        pigs.add(new Pig(4, "佩奇", 5, "F", false));
        pigs.add(new Pig(5, null, 1, "F", false));

        //1、toList 获取id的List集合
        List<Integer> idList = pigs.stream()
                .map(Pig::getId)
                .collect(Collectors.toList());
        System.out.println(idList);

        //2、toSet 获取gender的Set集合
        Set<String> genderSet = pigs.stream()
                .map(Pig::getGender)
                .collect(Collectors.toSet());

        System.out.println("set集合：" + genderSet);

        //3、toMap 将List的值转成 id -> name的Map  一下会报空指针异常 因为id为5的时候,name为null
//        Map<Integer, String> idNameMap = pigs.stream()
//                .collect(Collectors.toMap(Pig::getId,Pig::getName));
//        System.out.println("输出："+idNameMap);

        //改进版本
        Map<Integer, String> idNameMap = pigs.stream().collect(Collectors.toMap(Pig::getId, pig -> pig.getName() == null ? "" : pig.getName()));
        System.out.println("输出：" + idNameMap);  //输出：{1=猪爸爸, 2=猪妈妈, 3=乔治, 4=佩奇, 5=}
        //（1）Map遍历方法一
        //遍历map中的键
        for (Integer key : idNameMap.keySet()) {
            System.out.println("Key = " + key);
        }
//遍历map中的值
        for (String value : idNameMap.values()) {
            System.out.println("Value = " + value);
        }

        //（1）Map遍历方法二
        Iterator<Map.Entry<Integer, String>> entries = idNameMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, String> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }


        //4、解决key重复后异常
        Map<String, String> genderNameMap = pigs.stream()
                .collect(Collectors.toMap(Pig::getGender, pig -> pig.getName() == null ? "" : pig.getName(), (oldValue, newValue) -> newValue));
        System.out.println("输出genderNameMap：" + genderNameMap);  //输出genderNameMap：{F=, M=乔治}

        //5 groupingBy 分组
        //一级分组  以gender进行分组
        Map<String, List<Pig>> groupByGender = pigs.stream().collect(Collectors.groupingBy(Pig::getGender));
        System.out.println("输出groupByGender：" + groupByGender);

        //6. collectingAndThen 分组后再比较
        //按照性别分组后，再求各自中年级最小的
        Map<String, Pig> groupByGenderThenMinAge = pigs.stream().collect(Collectors.groupingBy(Pig::getGender, Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingInt(Pig::getAge)), Optional::get)));
        System.out.println("输出groupByGenderThenMinAge：" + groupByGenderThenMinAge);

        //7二级分组
        //先gender分组，后valid分组
        Map<String, Map<Boolean, List<Pig>>> groupByGenderAndValid = pigs.stream().collect(Collectors.groupingBy(Pig::getGender, Collectors.groupingBy(Pig::isValid)));
        System.out.println("输出groupByGenderAndValid：" + groupByGenderAndValid);

        //8 partitioningBy 分区
        Map<Boolean, List<Pig>> partByAge = pigs.stream().collect(Collectors.partitioningBy(a -> a.getAge() > 20));
        System.out.println("输出 partByAge ：" + partByAge);

        Map<Boolean, Long> partByAgeThenCount = pigs.stream().collect(Collectors.partitioningBy(a -> a.getAge() > 20, Collectors.counting()));
        System.out.println("输出 partByAgeThenCount：" + partByAgeThenCount);


    }

    public static void main(String[] args) {
        //测试list
//        testList();
//        testStream();
//        testStreamMap();
//        testStream();
//        testStreamSort();
//        testStreamDistinct();
//        testStreamMatch();
//        testStreamReduce();
        testStreamImport();
    }

}


