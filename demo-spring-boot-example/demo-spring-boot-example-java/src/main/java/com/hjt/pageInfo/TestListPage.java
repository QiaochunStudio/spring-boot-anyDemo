package com.hjt.pageInfo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hjt
 * @date:2023/3/23
 */
public class TestListPage {

    /**
     * 处理List集合数据进行分页
     *
     * @param currentPage 当前页
     * @param pageSize    每页数据个数
     * @param list        进行分页的数据
     * @param <T>
     * @return
     */
    public static <T> PageInfo<T> queryPageInfo(int currentPage, int pageSize, List<T> list) {
        int total = list.size();
        if (total > pageSize) {
            int toIndex = pageSize * currentPage;
            if (toIndex > total) {
                toIndex = total;
            }
            list = list.subList(pageSize * (currentPage - 1), toIndex);
        }
        Page<T> page = new Page<>(currentPage, pageSize);
        page.addAll(list);
        page.setPages((total + pageSize - 1) / pageSize);
        page.setTotal(total);

        PageInfo<T> pageInfo = new PageInfo<>(page);
        return pageInfo;
    }


    /**
     * 处理List集合数据进行分页
     *
     * @param currentPage 当前页
     * @param pageSize    每页数据个数
     * @param list        进行分页的数据
     * @param <T>
     * @return
     */
    public static <T> List<T> getPageInfo(int currentPage, int pageSize, List<T> list) {
        List<T> newList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            int currIdx = (currentPage > 1 ? (currentPage - 1) * pageSize : 0);
            for (int i = 0; i < pageSize && i < list.size() - currIdx; i++) {
                newList.add(list.get(currIdx + i));
            }
        }
        return newList;
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        int currentPage = 4;
        int pageSize = 3;
//        List<String> pageInfo = getPageInfo(currentPage, pageSize, list);
//        System.out.println("总条数：" + list.size());
//        System.out.print("数据：");
//        pageInfo.forEach(e -> System.out.print(e));
//        System.out.println();
//        System.out.println("当前页：" + currentPage);
//        System.out.println("每页个数：" + pageSize);
//        System.out.println("总页数：" + ((list.size() / pageSize) + 1));
//        System.out.println("===========================");

//        PageInfo<String> queryPageInfo = queryPageInfo(currentPage, pageSize, list);
//        System.out.println("总条数：" + queryPageInfo.getTotal());
//        System.out.print("数据：");
//        System.out.println(list);
////        queryPageInfo.getList().forEach(e -> System.out.print(e));
//        System.out.println();
//        System.out.println("当前页：" + queryPageInfo.getPageNum());
//        System.out.println("每页个数：" + queryPageInfo.getPageSize());
//        System.out.println("总页数：" + queryPageInfo.getPages());
        PageHelper.startPage(1, 5);
        PageInfo pageInfo = new PageInfo(list);
        System.out.println("总条数：" + list.size());
        System.out.println(pageInfo);

    }
}
