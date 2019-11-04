解决了：数据表查询中，需要分组后，组内按某些字段排序后取出每组第一个结果，然后将这些结果与另一张表连接查询




需求：

表：test1

![KI6sZd.png](https://s2.ax1x.com/2019/10/31/KI6sZd.png)

表：user

![KI6hQS.png](https://s2.ax1x.com/2019/10/31/KI6hQS.png)



解释：

> 简单的测试例子，没有主键等规范的设计

user表是用户表，nickname 昵称，headimgurl 头像，openid是和test1表做的外键关联

test1表是记录表，score 分数，time 时间，start_time 开始时间，标识这个记录是什么时候插入的（无用）



例如：昵称为jide的人，头像是11111，openid是test1，从test1表可知，他做了两次测试，分别都是70分，不过一次是25秒，一次是13秒。



我们现在要做的就是，查出分数最高，并且用时也少的用户记录。如果一个用户又多次记录只找出他成绩最好的一次。

就是要现对test1表进行分组，按照openid分组，分组后按照score逆序排序，time正序排序，取出每组的第一条数据，将这些数据拿出来做一个表b。

然后将user表和b表进行左连接，条件是openid相等，然后写上其他条件，最后将连接后查询出来的内容再按照score逆序，time正序排序

```sql
# 子查询：对test1表分组，组内按照score逆序，time正序排序，取出每组第一条数据
select a.* from test1 a left join test1 b on a.openid = b.openid and a.score >= b.score and a.time < b.time GROUP BY a.openid

# 总查询：user表和b表（上面的查询结果）左连接，openid相等，添加其他条件，按照score逆序，time正序排列。
select u.nickname, u.headimgurl, t.score, t.time 
from user u left join 
(select a.* from test1 a left join test1 b on a.openid = b.openid and a.score >= b.score and a.time < b.time GROUP BY a.openid) t
on u.openid = t.openid
where t.score > 50
and DATE_FORMAT( t.start_time, '%Y%m' ) = DATE_FORMAT( CURDATE( ), '%Y%m' ) 
ORDER BY
	t.score DESC,
	t.time ASC
limit 0,10
```



[![KIRwtA.md.png](https://s2.ax1x.com/2019/10/31/KIRwtA.md.png)](https://imgchr.com/i/KIRwtA)

​																											**查询结果在这↑**

