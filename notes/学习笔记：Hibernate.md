# 前置

## 外部依赖

> JAXB（maven：`javax.xml.bind:jaxb-api:2.4.0-b180830.0359`）  
> > CSXB（maven：`com.sun.xml.bind:jaxb-impl:2.4.0-b180830.0438`）  
> > ~~IStack（maven：`com.sun.istack/istack-commons-runtime:3.0.7`）~~

## 模版

> JDBCUrl：`jdbc:mysql://localhost:3306/my_database?serverTimezone=GMT%2B8`  
> > 注意需要设置时区，编码是不必要的

******

# 第一天 Hibernate入门和基本操作

## web内容回顾

**JavaEE三层结构**

1. web层（视图层）（struts2框架）
2. service层（服务层）（spring框架）
3. dao层（持久层）（hibernate框架）
   * 对数据库进行增删改查操作

**MVC思想**

1. model：模型
2. view：视图
3. controller：控制器

## Hibernate概述

**什么是框架**

框架帮助我们实现程序的一部分内容。

**什么是Hibernate框架**

1. Hibernate框架应用在JavaEE三层中的dao层。
2. 在dao层里做对数据库的增删改查操作，可以使用Hibernate实现，它的低层代码就是jdbc，对其进行了封装。使用Hibernate的好处就是，不需要写复杂的sql语句了。
3. Hibernate是一个开源的轻量级的框架。

**什么是orm思想**

1. Hibernate使用orm思想对数据库进行增删改查操作
2. JavaBean有一种更正确的叫法，实体类
3. orm：object relational mapping，对象关系映射
   * 让实体类和数据库表进行一一对应（类和表，类的属性和表列）
   * 不需要直接操作数据库表，而是操作实体类
   * 使用配置文件的方式完成对应操作
   * （也可以使用注解的方式完成配置？）

## Hibernate入门案例

**搭建hibernate环境**

1. 导入Hibernate的jar包

2. 创建实体类

> Hibernate要求实体类有一个唯一的属性。
> > 使用Hibernate时，不需要自己手动创建数据库表。

3. 配置实体类和数据库表的映射关系
   * 创建xml格式的配置文件
   * 映射配置文件的名称和位置没有固定要求
   * 建议：在实体类所在包里面创建
   * 名称格式：`BeanName.hbm.xml`
   * 配置是xml格式，在配置文件中首先要引入xml约束

4. 创建Hibernate的核心配置文件
   * 位置：必须在src下面
   * 名称：必须是`hibernate.cfg.xml`

**实现添加操作**

1. 加载hibernate核心配置文件
2. 创建SessionFactory对象
3. 使用SessionFactory创建session对象
4. 开启事务
5. 写具体的事务操作
6. 提交事物
7. 关闭资源

预期效果：

* 是否生成表
* 表中是否有记录

## Hibernate配置文件详解

**实体类配置文件**

> 现在已经可以使用注解代替。

1. class标签的name属性：实体类的全路径
2. id标签和property的name属性：实体类属性名称
3. column属性可以不写，生成同名的字段
4. property标签的type属性，设置生成表字段的类型，自动对应类型

**核心配置文件**

1. 名称和位置都是固定的

## Hibernate核心api的使用

`Configuration`

* `cfg.configure()`：加载src根目录下面的核心配置文件。
* `cfg.addAnnotatedClass(Class clazz)`：加上通过注解方式进行持久化的类。

`SessionFactory`

* `cfg.buildSessionFactory()`：根据核心配置文件中的数据库配置部分以及映射配置文件部分，在数据库里面创建表
* 创建的过程是非常耗资源的。一个项目只需要创建一个SessionFactory对象。
* 具体实现：写工具类，然后设置一个静态代码块实现。

`Session`

* 类似于JDBC中的`Connection`
* 可以调用session里面的不同的方法来实现增删查改操作。
* 例如：添加save方法，修改update方法，删除delete方法，根据id查询get方法
* 单线程对象

`Transaction`

* `session.beginTransaction()`：开启事务
* `transaction.commit()`：提交事务
* `transaction.rollback()`：回滚事务

******

# 第二天 Hibernate概念和api使用

## 实体类编写规则

* 实体类里面的属性都是私有的，带有公共的getter&setter方法
* 要求实体类里面有一个属性作为唯一值（一般都使用id值）
* 实体类属性建立不使用基本数据类型，而是包装类
  * 用来表示null或没有的概念

## 主键生成策略

1. Hibernate要求实体类里面有一个属性作为谓一值，对应表的主键，有不同的生成策略
2. 生成策略主要有：increment、identity、sequence、native、uuid和hilo
   * native：根据使用的数据库自动选择策略
   * uuid：自动生成

声明native生成策略：

* `<generator class="native"/>`
* `@Column @Id @GeneratedValue`

声明uuid生成策略：

* `<generator class="uuid"/>`
* `@Column @Id @GeneratedValue(generator="uuid") @GenericGenerator(name="uuid",strategy = "uuid")`

使用native生成策略时，最好指明`strategy = GenerationType.IDENTITY`。

## 实体类操作

**增删改差操作**

* 增加操作：`session.sava(entity)`
* 更新操作：`session.update(entity)`（首先要查找到实体）
  * 所有值（除了id）都会进行修改
  * id值设置与否没有关系
* 删除操作：`session.delete(entity)`
* 查找操作：`session.get(clazz,id)`

**实体类对象的状态**

* 瞬时态
  * 对象里面没有id值，与session也没有关联
  * 通过`session.save()`方法转换到持久态
* 持久态
  * 对象里面有id值，与session也有关联
* 托管态
  * 对象里面有id值，但与session没有关联

**演示操作实体类对象的方法**

* saveOrUpdate()方法：既能添加，也能修改

## 一级缓存

**什么是缓存**

1. 数据存到数据库里面，数据库本身是文件系统，使用流的方式操作文件，效率并不高
   * 把数据存到系统内存里面，不需要使用流的方式，直接读取内存中的数据
   * 把数据放到内存中，提高读取效率

**Hibernate缓存**

1. 其所提供的一种优化方式
2. 第一类 一级缓存
   * 默认是打开的
   * 使用范围：session的范围
   * 存储数据必须是持久态的数据
3. 第二类 二级缓存
   * 目前已经不使用了，替代技术：redis
   * 默认不打开，需要配置
   * 使用范围：sessionFactory的范围

* 第一步执行get方法后，发送sql语句查询数据库
* 第二次执行get方法后，没有发送sql语句，而是查询一级缓存的内容

* 处于持久态时，会自动更新数据库

* 原理概述
  * 把返回user持久态对象放到一级缓存中
  * 把user对象放到一级缓存对应的快照区里面
  * 修改一级缓存的内容，不会修改对应的快照区的内容

## 事务操作

**事物相关概念**

1. 什么是事务
2. 事务特性
   * 原子性、隔离性、一致性
3. 不考虑隔离性产生的问题
   * 脏读、不可重复读、虚读
4. 设置事务隔离级别
   * mysql默认隔离级别repeatable read

**事务代码的规范写法**

```
Session session = null;
Transaction transaction = null;
try {
	session = HibernateUtils.getSessionFactory().openSession();
	//开启事务
	transaction = session.beginTransaction();
	//事务操作

	//提交事务
	transaction.commit();
} catch(Exception e) {
	//回滚事务
	transaction.rollback();

} finally {
	session.close();
}
```

**Hibernate绑定session**

1. Hibernate已经帮我们实现了与本地线程绑定的session，只需进行配置
2. 获取与本地线程绑定的session
   1. 在核心配置文件中配置
   2. 调用sessionFactory里面的方法得到（`sf.getCurrentSession()`）
   3. 当获取后，关闭后会报错，不需要手动关闭了
   4. 使用`sf.openSession()`方法获得，需要关闭，不会报错

## 其他API

**Query对象**

* 不需要写sql语句，而要写hql语句
* 操作的是实体类和实体类中的属性
* 查询所有的hql语句：`from EntityName`

**Criteria对象**

* 创建Criteria对象（已过时，使用CriteriaLoader）
* 调用对象里面的方法得到结果

**SQLQuery对象**

* 返回list集合，默认里面每部分使用对象数组结构
* 使用`query.addEntity(clazz)`方法解决

******

# 第三天 Hibernate配置一对多和多对多

## 列表功能实现

## 表与表之间关系回顾

1. 一对多
   * 分类和商品的关系
   * 客户和联系人是一对多关系
     * 客户：与公司有业务往来的公司或者其他团体
     * 联系人：公司或者其他团体里面的员工或成员
     * 客户是一，联系人是多
   * 一对多建表
     * 通过外键建立关系
     * 在多的那一方创建字段作为外键，指向一的一方的主键
2. 多对多
   * 订单和商品的关系
   * 用户和角色是多对多关系
   * 多对多建表
     * 创建第三张表来维护
     * 至少要有两个字段作为外键，指向两个表的主键
3. 一对一

## 一对多操作

1. 一对多映射配置
   * 创建两个实体类
   * 让两个实体类之间互相表示
     * 在客户实体类里面表示多个联系人：一个客户里面有多个联系人（使用Set集合）
     * 在联系人实体类里面表示所属客户：一个联系人只能属于一个客户
   * 添加配置文件，或者使用注解
     * 一般一个实体类对应一个映射文件
   * 引入映射文件到核心配置文件，或者加入注解类到SessionFactory
2. 一对多级联保存
   * 添加一个客户，为这个客户添加多个联系人
   * 简化写法（一般根据客户添加联系人）
     1. 在客户映射文件中进行配置（set标签的cascade属性）（或者使用注解）
     2. 创建客户和联系人对象，然后把联系人放到客户set集合中即可
     3. 调用`session.save()`方法
3. 一对多级联删除
   * 删除某一客户，这个客户中的所有联系人也要删除
   * 具体操作
     1. 在客户映射文件中进行配置（set标签的cascade属性，增加一个值，用逗号隔开）（或者使用注解）
     2. 在代码中直接删除客户
4. inverse属性
   * 因为Hibernate是双向维护外键的，导致性能不高
   * 解决方式：让其中的一方不维护外键
   * `<set inverse="true">`：放弃关系维护
   * 或者在某一方不写JoinColumn注解

## 多对多操作

1. 多对多映射配置
   * 创建实体类
   * 两个实体类之间互相进行表示
     * 用户里面表示所有角色，使用set集合
     * 一个角色有多个用户，使用set集合
   * 配置映射关系
   * 引入映射文件到核心配置文件，或者加入注解类到SessionFactory
2. 多对多级联保存
   * 具体操作
     1. 在客户映射文件中进行配置（set标签的cascade属性，增加一个值，用逗号隔开）（或者使用注解）
     2. 写代码实现
3. 对多多级联删除
4. 维护第三张表
   * 维护多对多关系，是通过维护第三张表来进行的
   * 让某个用户有某个角色
   * 让某个用户没有某个角色

* 使用注解方式配置多对多关系
  * 单向：在主控方加入@ManyToMany注解即可。
  * 双向：两个实体间互相关联的属性必须标记为@ManyToMany，并相互指定targetEntity属性。有且只有一个实体的@ManyToMany注解需要指定mappedBy属性，指向targetEntity的集合属性名称。

******

# 第四天 Hibernate查询操作

## Hibernate的查询方式

1. 对象导航查询
   1. 根据id查询某个客户，再来查询某个联系人
2. OID查询
   1. 根据id查询某一条记录，返回对象
3. hql查询
   1. Query对象，写hql语句实现查询
4. QBC查询
   1. Criteria对象（已弃用）
5. 本地sql查询
   1. SQLQuery对象，使用普通sql实现查询

## 对象导航查询

1. 查询某个客户里面所有的联系人的过程

## OID查询

1. 根据id查询记录
   1. 调用`session.get()`方法实现

## HQL 查询

1. 区别：普通sql操作数据库表和字段，而hql操作实体类和实体类的属性
2. 常用的hql语句
3. 使用hql进行查询操作时，使用Query对象实现
   1. 创建Query对象，写hql语句
   2. 调用Query对象里面的方法得到结果


| 名称          | HQL                                  | 备注                                                                                                 |
|:--------------|:-------------------------------------|:-----------------------------------------------------------------------------------------------------|
| 查询所有      | `from Client`                         | 最好指定泛型。                                                                                        |
| 条件查询      | `from Client where id=?1 and name=?2` | 使用`query.setParameter()`设置参数，且索引需要对应。                                                    |
| 模糊查询      | `from Client where name like ?1`      |                                                                                                      |
| 排序查询      | `from Client order by id asc`         | 可选升序/降序（asc/desc），默认升序。                                                                  |
| 分页查询      | `from Client`                         | 不能在hql里面使用limit关键字，而是使用方法`query.setFirstResult().setMaxResults()。`                    |
| 投影查询      | `select id,name from Client`          | 就是查询部分字段的值。                                                                                |
| 聚集函数的使用 | `select count(*) from Client`         | 常用的聚集函数：sum,sub,avg,count,max,min。可以考虑使用`query.uniqueResult()`返回唯一结果。最好指定泛型。 |

## QBC查询

1. 使用hql查询需要写hql语句，使用qbc时则写方法
2. ~~使用qbc，使用Criteria对象实现（已弃用）~~
3. 使用qbc，使用`session.getCriteriaBuilder()`方法的相关操作实现

**查询所有**

```
//NOTE 首先要获取三个重要的对象
var cb = session.getCriteriaBuilder();
var query = cb.createQuery(Client.class);
var rClient = query.from(Client.class);
//NOTE 等价于from Client
query.select(rClient);
return session.createQuery(query).list();
```

**条件查询**

```
//NOTE 等价于from Client where id=1 and name='渡鸦'
query.select(rClient).where(
	cb.equal(rClient.get("id"), 1),
	cb.equal(rClient.get("name"), "渡鸦")
);
```

**模糊查询**

```
//NOTE 等价于from Client where name like '%渡鸦%'
query.select(rClient).where(
	cb.like(rClient.get("name"), "%鸦%")
);
```

**排序查询**

```
//NOTE 等价于from Client order by name
query.select(rClient).orderBy(
	cb.asc(rClient.get("name"))
);
```

**分页查询**

```
query.select(rClient);
//Criteria本身不具备分页功能
var result = session.createQuery(query).setFirstResult(0).setMaxResults(5).list();
```

**聚合函数的使用**

```
query.select(cb.count(rClient.get("*")));
```

~~统计查询（已弃用）~~

```
criteria.setProjection(Projections.rowCount());
var result = criteria.uniqueResult();
```

**离线查询**

```
//创建对象
DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Client.class);
//最终查询时才需要用到session
var criteria = detachedCriteria.getExecutableCriteria(session);
return criteria.list();
```

1. servlet调用service，service调用dao
2. 在dao里面对数据库执行crud操作
3. 在dao里面使用hibernate框架，调用session里面的方法实现功能

## HQL多表查询

**mysql的多表查询**

1. 内连接
   * `select * from t_client c,t_linkMan l where c.id = l.client_id`
   * `select * from t_client c inner join t_linkMan l on c.id = l.client_id`
2. 左外连接（返回左表中的所有数据）
   * `select * from t_client c left outer join t_linkMan l on c.id = l.client_id`
3. 右外连接（返回右表中的所有数据）
   * `select * from t_client c right outer join t_linkMan l on c.id = l.client_id`

**HQL实现多表查询**

| 名称        | HQL                                               | 备注                                                         |
|:------------|:--------------------------------------------------|:-------------------------------------------------------------|
| 内连接      | `from Client c inner join  c.linkManSet`           | 返回的是对象数组列表的形式。                                  |
| 左外连接    | `from Client c left outer join c.linkManSet`       |                                                              |
| 右外连接    | `from Client c right outer join c.linkManSet`      |                                                              |
| 迫切内连接  | `from Client c inner join fetch c.linkManSet`      | 使用内连接返回对象数组列表，而使用迫切内连接则返回实体对象列表。 |
| 迫切左外连接 | `from Client c left outer join fetch c.linkManSet` | 类似于迫切内连接                                              |

## Hibernate的检索策略

**检索策略的概念**

1. 立即查询
   * 根据id查询，调用get方法，一调用就马上发送语句查询数据库。
2. 延迟查询
   * 根据id查询，调用load方法，不会立刻发送语句查询数据。只有得到对象里面的值的时候才会发送语句查询数据库。

延迟查询又分为两类

1. 类级别延迟：根据id查询返回实体类对象，调用load方法不会马上发送语句
2. 关联级别延迟：例如查询某个客户，再查询这个客户的所有联系人

**关联级别延迟操作**

1. 到映射文件中进行配置
   * 根据客户得到所有的联系人，在客户映射文件中配置
2. 使用注解的方式
   * 使用@OneToMany/@ManyToOne/@ManyToMany的fetch属性
   * 或者：使用@Fetch和@LazyCollection

> 使用配置文件：一对多和多对多检索策略：`<set>`的lazy和fetch属性

| fetch（默认select） | lazy（默认true） | 策略                                                  |
|:-------------------|:-----------------|:-----------------------------------------------------|
| join               | false            | 采用迫切左外连接检索。                                 |
| join               | true             | 采用迫切左外连接检索。                                 |
| join               | extra            | 采用迫切左外连接检索。                                 |
| select             | false            | 采用立即检索。                                        |
| select             | true             | 采用延迟检索。                                        |
| select             | extra            | 采用延迟检索（以及懒惰）。                             |
| subselect          | false/true/extra | 嵌套子查询（检索多个实体对象时）。lazy属性决定检索策略。 |

## Hibernate批量抓取

1. 查询所有客户，返回list集合，遍历list集合，得到每个客户，再得到每个客户下的所有联系人。
   * 使用对象导航查询会用性能问题。
   * 这种需求可以用批量抓取替代。
2. 在客户的映射文件中，对set标签进行配置
   * 配置batch-size属性，值越大性能越高。
3. 或者：使用@BatchSize，可以对实体类也可以对实体类的集合属性

******

