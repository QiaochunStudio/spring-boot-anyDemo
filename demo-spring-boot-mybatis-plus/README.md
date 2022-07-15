# Spring Boot and MyBatis Plus

## 排序

- 单条件

- 多条件

- 数据库字段

```java
public class UserService {

    public void orderBy1() {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(UserEntity::getId);
    }

    public void orderBy2() {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList(UserEntity::getUsername, UserEntity::getPassword));
    }

    public void orderBy3() {
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.orderByDesc(Arrays.asList("username", "password"));
    }

}
```

## 注意事项

### 1、在 xml 中写 SQL，MyBatis-Plus 不会自动拼接逻辑删除

#### 使用框架

代码片段：

```java
List<GoodsEntity> list = goodsDao.queryByName("Redmi");
```

执行的SQL

```mysql
SELECT * FROM t_goods WHERE deleted_state=0 AND (name LIKE ?)
```

> 为了方便查看，查询的字段使用星号（ * ）作替换

#### 在 xml 中写的SQL

```xml
<select id="queryByName" resultType="com.hjt.demospringbootmybatisplus.entity.GoodsEntity">
    select
        *
    from
        t_goods t
    where
        t.name like concat('%', #{name}, '%')
</select>
```

执行的SQL

```mysql
select * from t_goods t where t.name like concat('%', ?, '%')
```
