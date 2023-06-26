package com.hjt.word.test;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Issue818 {

    public static class User {

        public User(String name, String sex, String age, String phone, String email) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.phone = phone;
            this.email = email;
        }

        private String name;
        private String sex;
        private String age;
        private String phone;
        private String email;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }

    public static void main(String[] args) throws Exception {

		long startTime = System.currentTimeMillis();
        List<User> list = new ArrayList<User>();
        User user1 = new User("ada", "男", "18", "10000", "asc@126.com");
        for (int i = 0; i < 100; i++) {
            list.add(user1);
        }
        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put("table", list);
				put("year", "测试1");
				put("month", "测试2");
				put("day", "测试3");
				put("sjbh", "测试4");
				put("kpr", "测试5");
				put("skfmc", "测试6");
				put("fkfmc", "测试7");

            }
        };
        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
        Configure config = Configure.builder()
			.bind("table", policy)
			.build();
		XWPFTemplate  template = XWPFTemplate.compile("D:\\testWord\\test.docx", config).render(map);
        template.writeAndClose(new FileOutputStream("D:\\testWord\\out_test.docx"));
        System.out.println(System.currentTimeMillis() - startTime);



	}



}