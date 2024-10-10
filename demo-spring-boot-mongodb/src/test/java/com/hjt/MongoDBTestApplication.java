package com.hjt;

import com.hjt.entity.Person;
import com.hjt.entity.User;
import com.hjt.repository.impl.UserRepositoryImpl;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.result.UpdateResult;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author :hjt
 * @date : 2023/1/17
 */

@SpringBootApplication
@SpringBootTest(classes = MongoDBTestApplication.class)
@RunWith(SpringRunner.class)
public class MongoDBTestApplication {
    public static final Logger log = LoggerFactory.getLogger(MongoDBTestApplication.class);

    @Resource
    private UserRepositoryImpl userRepository;

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public  void test(){
        User user = new User();
        user.setId(1l);
        user.setUserName("hjt");
        user.setPassWord("1234");
        userRepository.saveUser(user);
    }

    /**
     * 插入文档
     * @throws Exception
     */
    @Test
    public void insert() throws Exception {
        Person person =new Person();
        person.setUserName("张三");
        person.setPassWord("123456");
        person.setCreateTime(new Date());
        mongoTemplate.insert(person);
    }

    @Test
    public void insertBatch() throws Exception {
        List<Person> personList = new ArrayList<>();
        Person person1 =new Person();
        person1.setUserName("张三666");
        person1.setPassWord("123456");
        person1.setCreateTime(new Date());
        personList.add(person1);

        Person person2 =new Person();
        person2.setUserName("李四7777");
        person2.setPassWord("123456");
        person2.setCreateTime(new Date());
        personList.add(person2);

        mongoTemplate.insert(personList, "persons");
    }


    @Test
    public void save() throws Exception {
        Person person =new Person();
        person.setId("13");
        person.setUserName("八八1223");
        person.setPassWord("123456444");
        person.setAge(405);
        person.setCreateTime(new Date());
//        mongoTemplate.save(person);
        //自定义存储文档
        mongoTemplate.save(person, "custom_person");
    }


    /**
     * 更新文档，匹配查询到的文档数据中的第一条数据
     * @throws Exception
     */
    @Test
    public void updateFirst() throws Exception {
        //更新对象
        Person person =new Person();
        person.setId("13");
        person.setUserName("张三123");
        person.setPassWord("123456888");
        person.setCreateTime(new Date());

        //更新条件
        Query query= new Query(Criteria.where("id").is(person.getId()));

        //更新值
        Update update= new Update().set("userName", person.getUserName()).set("passWord", person.getPassWord());
        //更新查询满足条件的文档数据（第一条）
        UpdateResult result =mongoTemplate.updateFirst(query,update, Person.class);

        //更新查询满足条件的文档数据（全部）
        //UpdateResult result = mongoTemplate.updateMulti(query, update, Person.class);
        if(result!=null){
            System.out.println("更新条数：" + result.getMatchedCount());
        }
    }


    /**
     * 根据条件查询集合中符合条件的文档，返回第一条数据
     */
    @Test
    public void findOne() {
        String userName = "张三";
        Query query = new Query(Criteria.where("userName").is(userName));
        Person result = mongoTemplate.findOne(query, Person.class);
        System.out.println("查询结果：" + result.toString());
    }

    /**
     * 根据条件查询集合中符合条件的文档
     */
    @Test
    public void findByCondition() {
        String userName = "张三";
        Query query = new Query(Criteria.where("userName").is(userName));
        List<Person> result = mongoTemplate.find(query, Person.class);
        Person result1 = mongoTemplate.findOne(query, Person.class);
        System.out.println("查询结果：" + result.toString());
        System.out.println("查询结果11：" + result1.toString());
    }

    /**
     * 根据【AND】关联多个查询条件，查询集合中的文档数据
     */
    @Test
    public void findByAndCondition() {

        String userName = "张三";
        String password = "123456";
        // 创建条件
        Query query = new Query();
        if(StringUtils.isNotBlank(userName)){
            query.addCriteria(Criteria.where("userName").is(userName));
        }
        if(StringUtils.isNotBlank(password)){
            query.addCriteria(Criteria.where("passWord").is(password));
        }
        List<Person> result = mongoTemplate.find(query, Person.class);
        System.out.println("查询结果：" + result.toString());
    }


    /**
     * 根据【OR】关联多个查询条件，查询集合中的文档数据
     */
    @Test
    public void findByOrCondition() {
        // 创建条件
        Criteria criteriaUserName = Criteria.where("userName").is("张三");
        Criteria criteriaPassWord = Criteria.where("passWord").is("123456");
        // 创建条件对象，将上面条件进行 OR 关联
        Criteria criteria = new Criteria().orOperator(criteriaUserName, criteriaPassWord);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        List<Person> result = mongoTemplate.find(query, Person.class);
        System.out.println("查询结果：" + result.toString());
    }

    @Test
    public void findByInCondition() {
        // 设置查询条件参数
        List<Long> ids = Arrays.asList(1l, 10l, 11l);
        // 创建条件
        Criteria criteria = Criteria.where("id").in(ids);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        List<Person> result = mongoTemplate.find(query, Person.class);
        System.out.println("查询结果：" + result.toString());
    }

    /**
     * 根据【逻辑运算符】查询集合中的文档数据
     */
    @Test
    public void findByOperator() {
        // 设置查询条件参数
        int min = 20;
        int max = 35;
        Criteria criteria = Criteria.where("age").gte(min).lte(max);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        List<Person> result = mongoTemplate.find(query, Person.class);
        System.out.println("查询结果：" + result.toString());
    }

    /**
     * 根据模糊查询【正则表达式】查询集合中的文档数据
     */
    @Test
    public void findByRegex() {
        // 设置查询条件参数
        String regexName = "张";
        Query query = new Query();
        if(StringUtils.isNotBlank(regexName)){
            // i 用户忽略大小写
            query.addCriteria(Criteria.where("userName").regex(".*"+ Pattern.quote(regexName)+".*","i"));
        }
        List<Person> result = mongoTemplate.find(query, Person.class);
        System.out.println("查询结果：" + result.toString());
    }


    /**
     * 根据条件查询集合中符合条件的文档，获取其文档列表并排序
     */
    @Test
    public void findByConditionAndSort() {
        String userName = "张三";
        Query query = new Query(Criteria.where("userName").is(userName)).with(Sort.by(Sort.Direction.DESC,"age"));
        List<Person> result = mongoTemplate.find(query, Person.class);
        System.out.println("查询结果：" + result.toString());
    }


    /**
     * 根据单个条件查询集合中的文档数据，并按指定字段进行排序与限制指定数目
     */
    @Test
    public void findByConditionAndSortLimit() {
        String userName = "张三";
        //从第一行开始，查询2条数据返回
        Query query = new Query(Criteria.where("userName").is(userName)).with(Sort.by("createTime")).limit(2);
        List<Person> result = mongoTemplate.find(query, Person.class);
        System.out.println("查询结果：" + result.toString());
    }

    @Test
    public void countNumber() {
        // 设置查询条件参数
        String regexName = "张";
        Query query = new Query();
        if(StringUtils.isNotBlank(regexName)){
            // i 用户忽略大小写
            query.addCriteria(Criteria.where("userName").regex(".*"+ Pattern.quote(regexName)+".*","i"));
        }
        long count = mongoTemplate.count(query, Person.class);
        System.out.println("统计结果：" + count);
    }

    /**
     * 创建升序索引
     */
    @Test
    public void createAscendingIndex() {
        // 设置字段名称
        String field = "userName";
        // 创建索引
        mongoTemplate.getCollection("persons").createIndex(Indexes.ascending(field));
    }



}
