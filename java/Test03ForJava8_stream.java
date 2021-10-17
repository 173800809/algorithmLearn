import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.maxBy;

/**
 * 知识点1(解释filter)：
 * 惰性求值(只改变Stream，而不改变原变量)：只描述Stream，操作的结果也是Stream，这样的操作称为惰性求值。惰性求值可以像建造者模式一样链式使用，最后再使用及早求值得到最终结果。
 * 及早求值(输出的就是Stream)：得到最终的结果而不是Stream，这样的操作称为及早求值。
 * 知识点2(java8 自带的常用函数式接口)
 * 函数接口            抽象方法        功能       参数         返回类型         实例
 * 1. Predicate         test(T t)      判断真假     T           boolean
 * 2. Consumer          accept(T t)    消费信息     T           void        输出一个值
 * 3. Function          R apply(T t)   将T映射为R   T1          R           获得student的名字
 * 4. Supplier          T get()        生产消息     None        T1          工厂方法
 * 5. UnaryOperator     T apply(T t)   一元操作     T1          T1          逻辑非
 * 6. BinaryOperator    apply(T t,U u) 二元操作     (T,T)       (T)         求两个数的乘积
 * <p>
 * 知识点3(Map 在Java8和之前版本的遍历方式)
 */
public class Test03ForJava8_stream {
    public static void main(String[] args) {

        System.out.println(JSONArray.fromObject(collect()));
        System.out.println(JSONArray.fromObject(filter()));
        System.out.println(JSONArray.fromObject(map()));
        System.out.println(JSONArray.fromObject(flatMap()));
        System.out.println(JSONArray.fromObject(maxAndMin()));
        System.out.println(count());
        System.out.println(JSONArray.fromObject(forEach()));
        maxAndAverage();
        Map<Date, List<Student>> specialityEnumListMap = groupingBy();
        Map<Boolean, List<Student>> booleanListMap = partitioningBy();
        List<Student> sorted = sorted();
    }

    /**
     * 及早求值
     * collect 把流转换为集合
     * 解释：将流转换为：toList()、toSet()、toMap()
     * 语句：Stream.of().collect(Collectors.toList())
     */
    private static List<Student> collect() {
        List<Student> students = Stream.of(new Student("张三", 20, 160),
                                           new Student("李", 21, 170),
                                           new Student("王", 22, 180)).collect(Collectors.toList());
        return students;
    }

    /**
     * 惰性求值
     * filter 起到过滤筛选的作用。内部是Predicate接口
     * 语句：filter(x-> 判断语句为true)
     * 注意（见下）：filter不会改变stream本身，需要将steam赋值给一个新的对象
     */
    private static List<Student> filter() {
        List<Student> students = buildStudents();
        /**
         * 注意补充：List<Student> result必须要写，因为students没有改变
         */
        List<Student> result = students.stream().filter(stu -> stu.getStature() < 175).collect(Collectors.toList());
        return result;
    }

    /**
     * 惰性求值
     * map 起到转换功能(从一个实体到另一个实体)。内部是Function接口
     * 语句：map(x -> 最终输出的参数)
     */
    private static List<String> map() {
        List<Student> students = buildStudents();
        List<String> names = students.stream().map(stu -> stu.getName()).collect(Collectors.toList());
        return names;
    }

    /**
     * 惰性求值
     * flatMap 将多个Stream合并为一个Stream
     * 语句：flatMap(x -> x.stream())
     * 注意：Arrays常用方法：asList、sort、equals、binarySearch
     * ArrayUtils常用方法：toArray、toObject、nullToEmpty、isEmpty、contains
     */
    private static List<Student> flatMap() {
        List<Student> students = buildStudents();
        List<Student> result = Stream.of(students, Arrays.asList(new Student("艾斯", 25, 183),
                                                                 new Student("雷利", 30, 176)))
                .flatMap(stu -> stu.stream()).collect(Collectors.toList());

        return result;
    }

    /**
     * 及早求值
     * max/min 在集合中求最大或最小值
     * 语句：min(Comparator.comparing(x -> 最终比较的参数))
     * max(Comparator.comparing(x -> 最终比较的参数))
     * 注意：java8新增Optional类（专门为了防止null引发的空指针异常）
     * 常见方法解释：判断是否不为空，  如果为空则新建，         如果为空则带参新建
     * 常见方法有：isPresent(), orElse(new Object()), orElseGet(() -> new Student)
     */
    private static List<Student> maxAndMin() {
        List<Student> students = buildStudents();
        Optional<Student> max = students.stream().min(comparing(stu -> stu.getAge()));
        Optional<Student> min = students.stream().max(comparing(stu -> stu.getStature()));
        List<Student> result = new ArrayList<>();
        if (max.isPresent())
            result.add(max.get());
        if (min.isPresent())
            result.add(min.get());
        return result;
    }

    /**
     * 及早求值
     * count 统计功能（一般是结合filter使用，因为现筛选再统计）
     * 语句：count()
     */
    private static long count() {
        List<Student> students = buildStudents();

        long count = students.stream().filter((stu -> stu.getAge() < 22)).count();
        return count;
    }

    /**
     * 及早求值
     * reduce 从一组值生成一个值
     * 语句：reduce(初始值, (累计值, 遍历值) -> 累计值 + 遍历值)
     * 注意： count、min、max 这些方法都是 reduce 操作（因为常用而被纳入标准库中）
     */
    private static Integer reduce() {
        Integer reduce = Stream.of(1, 2, 3, 4).reduce(0, (acc, x) -> acc + x);
        return reduce;
    }

    /**
     * 高级集合类及收集器(collect)
     * 转换成值 一种通用的、从流生成复杂值结构。（collect：只要将它传给collect方法，所有的流就都可以使用它）
     * 语句：stream().collect(java.util.stream.Collectors类中静态导入的方法)
     * 注意：orElseGet(实体类::new) 等价于 orElse(new 实体类())
     * 此实体类需要无参构造方法
     */
    private static void maxAndAverage() {
        List<Student> students1 = buildStudents();
        OutstandingClass outstandingClass1 = new OutstandingClass("一班", students1);
        List<Student> students2 = buildStudents();
        students2.remove(1);
        OutstandingClass outstandingClass2 = new OutstandingClass("二班", students2);
        System.out.println("人数最多班级：" + JSONObject.fromObject(Stream.of(outstandingClass1, outstandingClass2)
                                                                     .collect(maxBy(comparing(ostClass -> ostClass.getStudents().size())))
                                                                     .orElseGet(OutstandingClass::new)));
        System.out.println("一班平均年龄：" + students1.stream().collect(averagingInt(Student::getAge)));

    }

    /**
     * 高级集合类及收集器(collect)
     * 转换成块 常用流操作是将其分解成两个集合（Collectors.partitioningBy帮我们实现了，接收一个Predicate函数式接口）
     * 语句:collect(Collectors.partitioningBy(x-> 返回值为boolean的语句))
     */
    private static Map<Boolean, List<Student>> partitioningBy() {
        List<Student> students = buildStudents2();

        return students.stream().collect(Collectors.partitioningBy(stu -> stu.getSpecialities() == SpecialityEnum.DANCE));
    }

    /**
     * 高级集合类及收集器(collect)
     * 数据分组 常用流操作是将其分解成多个集合（(Collectors.groupingBy接收一个Function做转换)
     * 语句:collect(Collectors.groupingBy(x-> 需要分类的变量))
     */
    private static Map<Date, List<Student>> groupingBy() {
        List<Student> students = buildStudents2();
        return students.stream().collect(Collectors.groupingBy(Student::getTime));
    }

    /**
     * 可以操作原有集合方法（forEach）(这个方法的返回值为空)
     *
     * @return
     */
    private static List<Student> forEach() {
        List<Student> students = buildStudents();
        //把学生的年龄都改为11
        students.stream().forEach(each -> each.setAge(11));

        return students;
    }

    /**
     * 对值进行排序，默认是从小到大（下面没有reversed()则为默认）。不会修改原有的数组
     * 如果想从大到小需要加reversed()
     * todo : reversed()的使用比较特殊
     *  下面的排序方式就是：姓名正排(两个reversed()生效)，身高倒排(一个reversed()生效)
     *
     * @return
     */
    private static List<Student> sorted() {
        List<Student> students = buildStudents();

        return students
                .stream()
                .sorted(Comparator
                                .comparing(Student::getName).reversed().thenComparing(Student::getStature).reversed())
                .collect(Collectors.toList());
    }

    private static List<Student> buildStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("张三", 23, 160));
        students.add(new Student("李", 21, 170));
        students.add(new Student("王", 22, 181));
        students.add(new Student("王", 22, 182));
        students.add(new Student("王", 22, 183));

//        students.add(new Student("2020-06-31", 23, 160));
//        students.add(new Student("2020-05-31", 21, 170));
//        students.add(new Student("2020-05-31", 22, 181));
//        students.add(new Student("2020-05-31", 21, 180));
//        students.add(new Student("2020-05-31", 22, 183));
//        students.add(new Student("2020-05-31", 21, 187));
//        students.add(new Student("2020-05-31", 22, 171));
//        students.add(new Student("2020-07-31", 22, 182));
//        students.add(new Student("2020-08-31", 22, 183));

        return students;
    }

    private static List<Student> buildStudents2() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("张三", 23, 160, new Date(2020, 1, 02), SpecialityEnum.DANCE));
        students.add(new Student("李", 21, 170, new Date(2020, 1, 02), SpecialityEnum.SING));
        students.add(new Student("王", 22, 180, new Date(2020, 01, 03), SpecialityEnum.DANCE));
        return students;
    }
}
