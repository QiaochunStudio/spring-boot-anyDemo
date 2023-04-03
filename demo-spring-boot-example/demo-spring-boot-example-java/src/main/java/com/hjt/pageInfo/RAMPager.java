package com.hjt.pageInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author hjt
 * @date:2023/3/23
 */
public class RAMPager<T> {
   private List<T> data;
   private int pageSize;

   /**
    * @param data     原始数据
    * @param pageSize 每页条数
    */
   public RAMPager(List<T> data, int pageSize) {
      this.data = data;
      this.pageSize = pageSize;
   }

   /**
    * 获取某页数据，从第1页开始
    *
    * @param pageNum 第几页
    * @return 分页数据
    */
   public List<T> page(int pageNum) {
      if (pageNum < 1) {
         pageNum = 1;
      }
      int from = (pageNum - 1) * pageSize;
      int to = Math.min(pageNum * pageSize, data.size());
      if (from > to) {
         from = to;
      }
      return data.subList(from, to);
   }

   /**
    * 获取总页数
    */
   public int getPageCount() {
      if (pageSize == 0) {
         return 0;
      }
      return data.size() % pageSize == 0 ? (data.size() / pageSize) : (data.size() / pageSize + 1);
   }

   /**
    * 元素迭代器
    */
   public Iterator<List<T>> iterator() {
      return new Itr();
   }

   private class Itr implements Iterator<List<T>> {
      int page = 1;

      Itr() {
      }

      public boolean hasNext() {
         return page <= getPageCount();
      }

      public List<T> next() {
         int i = page;
         if (i > getPageCount())
            return new ArrayList<>();

         page = i + 1;
         return RAMPager.this.page(i);
      }
   }

   public static void main(String[] args) {
      List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
      System.out.println("原始数据是：" + list);

      int pageSize = 5;
      System.out.println("每页大小是：" + pageSize);

      RAMPager<Integer> pager = new RAMPager<>(list, pageSize);
      System.out.println("总页数是: " + pager.getPageCount());

      System.out.println("<- - - - - - - - - - - - - ->");

      // 无需感知页码情况下使用
      Iterator<List<Integer>> iterator = pager.iterator();
      while (iterator.hasNext()) {
         List<Integer> next = iterator.next();
         System.out.println("next: " + next);
      }

      System.out.println("<- - - - - - - - - - - - - ->");
      // 需要指定页码情况使用，页码从第一页开始，且小于等于总页数！
      for (int i = 1; i <= pager.getPageCount(); i++) {
         List<Integer> page = pager.page(i);
         System.out.println("第 " + i + " 页数据是:" + page);
      }
   }
}


