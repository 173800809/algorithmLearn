import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 在java 8
 *  Map的 ForEach内部只能处理逻辑 （不能修改外部变量, 也不能 return 或者 break)
 *
 *
 * @Author lee1.li
 * @Date 12:27 下午 2021/4/22
 **/
public class Test03ForJava8_map {


    public static void main(String[] args) {
        testErgodicWay1();
        testErgodicWay2();
        testErgodicWay3();
    }


    /**
     * 遍历Map的方式一
     * 通过Map.keySet遍历key和value
     */
    public static void testErgodicWay1() {
        System.out.println("---------------------Before JAVA8 ------------------------------");
        for (String key : map().keySet()) {
            System.out.println("map.get(" + key + ") = " + map().get(key));
        }
        System.out.println("---------------------JAVA8 ------------------------------");
        map().keySet().forEach(key -> System.out.println("map.get(" + key + ") = " + map().get(key)));
    }

    /**
     * 遍历Map第二种
     * 通过Map.entrySet使用Iterator遍历key和value
     */
    public static void testErgodicWay2() {
        System.out.println("---------------------Before JAVA8 ------------------------------");
        Iterator<Map.Entry<String, Object>> iterator = map().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("---------------------JAVA8 ------------------------------");
        map().entrySet().iterator().forEachRemaining(item -> System.out.println("key:value=" + item.getKey() + ":" + item.getValue()));
    }

    /**
     * 遍历Map第三种
     * 通过Map.entrySet遍历key和value，在大容量时推荐使用
     */
    public static void testErgodicWay3() {
        System.out.println("---------------------Before JAVA8 ------------------------------");
        for (Map.Entry<String, Object> entry : map().entrySet()) {
            System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("---------------------JAVA8 ------------------------------");
        map().entrySet().forEach(entry -> System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue()));
    }

    /**
     * 遍历Map第四种
     * 通过Map.values()遍历所有的value，但不能遍历key
     */
    public static void testErgodicWay4() {
        System.out.println("---------------------Before JAVA8 ------------------------------");
        for (Object value : map().values()) {
            System.out.println("map.value = " + value);
        }
        System.out.println("---------------------JAVA8 ------------------------------");
        map().values().forEach(System.out::println); // 等价于map.values().forEach(value -> System.out.println(value));
    }

    /**
     * 遍历Map第五种
     * 通过k,v遍历，Java8独有的
     */
    public static void testErgodicWay5() {
        System.out.println("---------------------Only JAVA8 ------------------------------");
        map().forEach((k, v) -> System.out.println("key:value = " + k + ":" + v));
    }

    private static Map<String, Object> map(){
        Map<String, Object> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4",4);
        map.put("key5",5);
        map.put("key5",'h');

        return map;
    }
}
