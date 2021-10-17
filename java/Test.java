import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class Test {


    public static void main(String[] args) {
        List<String> out = test("2462425^4352|526462|2462425^4352|2462425^4352^4352|2462425^4352|");

//        System.out.println(test());
        String path = Objects.requireNonNull(TreeNode.class.getClassLoader().getResource("")).getPath();
        String path1 = path.substring(0, path.lastIndexOf("/"));
        path1 = path1.substring(0, path1.lastIndexOf("/"));
        path1 = path1.substring(0, path1.lastIndexOf("/") + 1);

//        System.out.println(path);
//        System.out.println(path1);
//        System.out.println(path.lastIndexOf("/"));
//        System.out.println(path1.lastIndexOf("/"));
//        System.out.println("<id column=\"id\" property=\"id\" jdbcType=\"BIGINT\"/>\n");

//        String time = "20200122";
//        StringBuffer date = new StringBuffer();
//        String tempDate = Integer.toString(20200122);
//        date.append(tempDate.substring(0, 4)).append(".").append(tempDate.substring(4, 6)).append(".").append(tempDate.substring(6, 8));
//
//        System.out.println(getMonth(1231));
//
//        System.out.println(date.toString());
//        System.out.println(time.concat("ad"));
//        System.out.println(nameEncrypt("李严"));
//        System.out.println(idEncrypt("8417598uhgfsajkf"));
//
//        System.out.println(test());
////        System.out.println(BigDecimal.ZERO.compareTo(null) == 0);
//
//        System.out.println("20210501".compareTo("20200203") > 0);
//
//        Consumer<String> sc = System.out::println;
//        sc.accept("你好");
//        System.out.println(accountEncrypt("12121664"));
//        System.out.println(String.format("VIP_VIP_%02d", 11123));
//
//        System.out.println(parserTbIndex("3002018132507575561226"));
//        System.out.println(calculateMonthInterval("201807"));
//        System.out.println(calculateMonthInterval("202012"));
//
//        BigDecimal b1 = new BigDecimal("1");
//        System.out.println(b1.add(new BigDecimal("2")));
//        long ll = 1241341L;
//        System.out.println(Long.toString(ll));
//        Boolean b = null;
//        if(b)
//        System.out.println(b);
        BigDecimal b1 = new BigDecimal(2.322323);
        System.out.println(b1.setScale(2));

        System.out.println(test2());
    }


    public static List<Integer> test2(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.subList(2,4);
        return test1();

    }

    public static List<Integer> test1 (){
//        return Integer.parseInt(DateTimeFormatter.ofPattern("uuuuMM").withResolverStyle(ResolverStyle.STRICT).format(LocalDate.now().minusMonths(5L)));
        LocalDate earliestTime = LocalDate.of(2020,01,01);
        LocalDate earlyTime = LocalDate.now().minusMonths(5);
        LocalDate startTime = earliestTime.compareTo(earlyTime) > 0 ? earliestTime : earlyTime;
        LocalDate endTime = LocalDate.now();

        List<Integer> result = new ArrayList<>();
        while(startTime.compareTo(endTime) <= 0){
            result.add(Integer.parseInt(DateTimeFormatter.ofPattern("uuuuMMXXX").withResolverStyle(ResolverStyle.STRICT).format(startTime)));
            startTime = startTime.plusMonths(1);
        }

        return result;
    }
    public static String calculateMonthInterval(String date) {

        int year = Integer.valueOf(date.substring(0, 4));
        int month = Integer.valueOf(date.substring(4));

        int yearInterval = year - 2015;
        int monthInterval = month - 07;

        return String.valueOf(yearInterval * 12 + monthInterval);
    }

    public static String parserTbIndex(String orderNo) {
        String date = orderNo.substring(3, 9);
        if (!checkIsDate(date)) {
            date = orderNo.substring(0, 6);
        }
        return date;
    }

    private static boolean checkIsDate(String dateString) {
        int year = Integer.valueOf(dateString.substring(0, 4));
        int month = Integer.valueOf(dateString.substring(4));
        if (month > 12) {
            return false;
        }
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (Math.abs(year - currentYear) > 2) {
            return false;
        }
        return true;
    }

    private static String accountEncrypt(String withdrawAccount) {
        String emailMatch = "\\w*+[@]\\w+[.]\\w*";
        String result = "未知";
        // 如果是邮箱
        if (withdrawAccount.matches(emailMatch)) {
            result = withdrawAccount.replaceAll("(\\w+)\\w{3}@(\\w+)", "$1***@$2");
        } else if (withdrawAccount.length() == 11) {// 如果是手机号
            result = withdrawAccount.replaceAll("(?<=\\w{4})\\w(?=\\w{3})", "*");
        } else {
            result = withdrawAccount.replaceAll("\\w*(?=\\w{4}$)", "*");
        }

        return result;
    }

    public static boolean test() {
        SubAccountViewDto dto = new SubAccountViewDto();

        List<SubAccountViewDto> subAccountViewDtos = new ArrayList<>();

        dto.setAccountType(1);
        dto.setTotalAmount(new BigDecimal(0));
        subAccountViewDtos.add(dto);
        dto.setAccountType(2);
        dto.setTotalAmount(new BigDecimal(0.1));
        subAccountViewDtos.add(dto);


        List<BigDecimal> oldAccWithdrawAmount = subAccountViewDtos
                .stream()
                .filter(each -> each.getAccountType() == 2 || each.getAccountType() == 3)
                .map(each -> each.getTotalAmount())
                .filter(each -> BigDecimal.ZERO.compareTo(each) < 0)
                .collect(Collectors.toList());

        return CollectionUtil.isNotEmpty(oldAccWithdrawAmount);
    }

    public static String idEncrypt(String id) {
        if (StringUtils.isEmpty(id) || (id.length() < 8)) {
            return id;
        }
        return id.replaceAll("(?<=\\w{6})\\w(?=\\w{4})", "*");
    }

    private static String nameEncrypt(String name) {
        if (StringUtils.isEmpty(name) || (name.length() < 2)) {
            return name;
        }
//        return name.substring(1,2) + name.replaceAll(name.length());
//        for(int each : name.length())name.startsWith()
        System.out.println(name.length());

        return name.replaceAll("(?<=[\\\u4E00-\\\u9FA5])[\\\u4E00-\\\u9FA5]", "*");
    }

    private static String getMonth(int monthDay) {
        // 获得数字月份()
        int month = (monthDay / 1000) == 0 ? (monthDay / 100) % 10 : monthDay / 100;
        // 获得数字日期
        int day = (monthDay % 100) < 10 ? monthDay % 10 : monthDay % 100;

        return "" + month + "月" + day + "日";
    }

    public static List<String> test(String details) {
        List<String> merchantOrderNos = new ArrayList<>();

        if (!StringUtils.isEmpty(details)) {
            String[] journals = details.split("\\|");
            for (int i = 0; i < journals.length; i++) {
                String[] elements = journals[i].split("\\^");
                merchantOrderNos.add(elements[0]);
            }
        }


        return merchantOrderNos;
    }

    public static int numDecodings(String s) throws Exception {
        throw new Exception("new");
    }

    public static void numDecodings1(String s) throws Exception {
        numDecodings("");
        return;
    }


//    public static boolean isBipartite(int[][] graph) {
//        int[] colors = new int[graph.length];
//        Arrays.fill(colors, -1);
//        for (int i = 0; i < graph.length; i++)//处理图不是联通的情况
//            if (colors[i] == -1 && !isBipartite(i, 0, colors, graph))
//                return false;
//        return true;
//    }
//    private static boolean isBipartite(int curNode, int curColor, int[] colors, int[][] graph) {
//        if (colors[curNode] != -1)
//            return colors[curNode] == curColor;
//        colors[curNode] = curColor;
//        for (int nextNode : graph[curNode])
//            if (!isBipartite(nextNode, 1 - curColor, colors, graph))
//                return false;
//        return true;
//    }


}
