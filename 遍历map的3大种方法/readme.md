> 共有三大种方法，每种方法又可使用2种方法实现。所以可以说，有6种方式遍历map。


**遍历map的3种方式**

- keySet得到存有key的Set集合
  - 集合遍历
  - 迭代器遍历
- entrySet得到存有Map.Entry的Set集合
  - 集合遍历
  - 迭代器遍历
- map.values得到存有value的Collection集合  只能遍历value
  - 集合遍历
  - 迭代器遍历

```java
package July.map;

import org.junit.Test;

import java.util.*;


public class hx1 {
    //遍历map的4种方式
    @Test
    public void test1(){
        HashMap<Integer,String> map = new HashMap();
        map.put(1,"abc");
        map.put(2,"def");
        map.put(3,"ghi");

        //1 通过keySet得到key遍历
        System.out.println("-----------------------1:keySet得到Set集合，集合中元素类型为key的类型-------------------------");

        System.out.println("1.1:集合遍历");
       for (Object key: map.keySet()) {
            System.out.println(key.toString()+map.get(key));
        }

        System.out.println("1.2:迭代器遍历");
        Iterator<Integer> iterator = map.keySet().iterator();
       while(iterator.hasNext()){
           Integer key = iterator.next();
           System.out.println(key+map.get(key));
       }


        //2 通过entrySet得到Set集合，集合里的类型是Map.Entry<k,v>。所以也可以说通过entrySet得到entry  推荐★
        System.out.println("-----------------------2:entrySet得到Set集合，集合中元素类型为Map.Entry<k,v>的类型-------------------------");
        //map.entrySet()得到一个set集合，里面装的元素是Map.Entry类型的  entry是包含了key,value的
        /*Set<Map.Entry<Integer,String>> set2 = map.entrySet();
        System.out.println(set2);
        Iterator<Map.Entry<Integer,String>> it2 = set2.iterator();
        Map.Entry<Integer,String> o = it2.next();
        System.out.println(o.getKey()+o.getValue());*/

        System.out.println("2.1:集合遍历");
        for (Map.Entry<Integer,String> m:map.entrySet()) {
            System.out.println(m.getKey()+m.getValue());
        }

        System.out.println("2.2:迭代器遍历");
        Iterator<Map.Entry<Integer,String>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Integer,String> entry = it.next();
            System.out.println(entry.getKey()+entry.getValue());
        }


        //4 通过map.values得到values遍历value
        System.out.println("-----------------------3:values得到Collection集合，集合元素类型为value的类型-------------------------");

        System.out.println("3.1:集合遍历");
        for (String v:map.values()) {
            System.out.println(v);
        }

        System.out.println("3.2:迭代器遍历");
        Iterator<String> itvalue = map.values().iterator();
        while (itvalue.hasNext()){
            System.out.println(itvalue.next());
        }

    }
}
```

