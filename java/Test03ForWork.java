import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Test03ForWork {
    static double dd;
    public static void main(String[] args) {
        MakeInvoiceVo makeInvoiceVo = new MakeInvoiceVo();
        List<String> withdrawNoList = new ArrayList<>();
        List<MakeInvoiceVo.InvoiceVo> invoiceVoList = new ArrayList<>();

        withdrawNoList.add("withdraw测试");
        MakeInvoiceVo.InvoiceVo ttt = new MakeInvoiceVo.InvoiceVo();
        ttt.setInvoiceAmount("111.11");
        ttt.setInvoiceNo("invoiceNo");
        ttt.setWithdrawName("测试提现名称");

        invoiceVoList.add(ttt);

        makeInvoiceVo.setInvoiceVoList(invoiceVoList);
        makeInvoiceVo.setWithdrawNoList(withdrawNoList);
        CommonRequest<MakeInvoiceVo> commonRequest = new CommonRequest<>();
        commonRequest.setParam(makeInvoiceVo);

        List<Long> l = new ArrayList<>();
        l.add(100L);
        l.add(200L);
        l.add(300L);
        String str ="ceshi";

        JSONObject jsonResult = JSONObject.fromObject(commonRequest);
//        System.out.println(jsonResult);
//        System.out.println(l);

//        new Thread(() -> System.out.println("lambda写法")).start();

        MakeInvoice makeInvoice = new MakeInvoice();
        makeInvoice.setWithdrawAmount(new BigDecimal("5"));
        makeInvoice.setInvoicesTotalAmount(new BigDecimal("2.5"));
        makeInvoice.setOfflineAmount(new BigDecimal("2.7"));

//        System.out.println(isWithdrawBillHasSuccessRelated(makeInvoice));

        System.out.println(getIntervalDayFormat(new Date(), 86));

        System.out.println(getIntervalDayInyyyyMMddFormat(new Date(), 86));

        List<MakeInvoice> makeInvoices = new ArrayList<>();

        MakeInvoice makeInvoice1 = new MakeInvoice();
        makeInvoice1.setInvoiceMakeTime(new Date(2020,11,11));
        MakeInvoice makeInvoice2 = new MakeInvoice();
        makeInvoice2.setInvoiceMakeTime(new Date(2020,11,10));
        MakeInvoice makeInvoice3 = new MakeInvoice();
        makeInvoice3.setInvoiceMakeTime(new Date(2020,11,15));

        makeInvoices.add(makeInvoice1);
        makeInvoices.add(makeInvoice2);
        makeInvoices.add(makeInvoice3);

        MakeInvoice makeInvoice11 = makeInvoices.get(2);
        makeInvoice11.setInvoiceMakeTime(new Date(0,1,5));

        List<MakeInvoice> makeInvoices1 = new ArrayList<>();
        makeInvoices.sort(Comparator.comparing(MakeInvoice::getInvoiceMakeTime));
//        makeInvoices.stream().forEach(MakeInvoice.setInvoiceMakeTime::new Date());

        BigDecimal bgd1 = new BigDecimal("2.2");
        BigDecimal bgd2 = new BigDecimal("1.1");
        BigDecimal bgd3 = new BigDecimal("1");

        System.out.println(bgd1.subtract(bgd2.add(bgd3)).compareTo(new BigDecimal("1")) > 0);

        Map<String, String> t2 = new HashMap<>();
        t2.put("1","sdj");
        String t3 = "121";
        t2.put("1","sjl");
        System.out.println(t2.put(t3,"j"));

        BigDecimal hasRelateWithdrawAmount = new BigDecimal("2");
        BigDecimal getAmount = new BigDecimal("1");
        BigDecimal invoiceTotalRemainAmount = new BigDecimal("2");


        System.out.println(hasRelateWithdrawAmount.add(getAmount).compareTo(invoiceTotalRemainAmount.add(new BigDecimal("1"))) > 0);

        System.out.println(new BigDecimal("1.500000000000000").setScale(2, RoundingMode.HALF_DOWN).toPlainString());

        MakeInvoice makeInvoice5 = new MakeInvoice();
        makeInvoice5.setMakeInvoiceNo("1");
        System.out.println(makeInvoice5.getTemp());
        System.out.println(StringUtils.isBlank(null));

        int b = 1;
        System.out.println((double) b);

        System.out.println(Pattern.matches("^[1-9]?n+/.+n+","1023"));

        BigDecimal bigDecimal = new BigDecimal("0.000000");
        System.out.println(bigDecimal.compareTo(BigDecimal.ZERO) == 0);
        dd = 0.010d;
        int tt = BigDecimal.valueOf(dd).scale();
        MakeInvoice makeInvoice6 = new MakeInvoice();
        makeInvoice6.setMakeInvoiceNo("1");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<MakeInvoice>> violations = validator.validate(makeInvoice6);
        System.out.println(makeInvoice6.getInvoiceMakeTime());
    }

    public static Date ExcelDoubleToDate(String strDate) {
        double dVal = Double.parseDouble(strDate);
        Calendar calendar = Calendar.getInstance();
        Date date = new Date((long) ((dVal - 25569) * 24 * 3600 * 1000));
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date parseDate(String dateStr, String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static boolean isWithdrawBillHasSuccessRelated(MakeInvoice makeInvoice){
        BigDecimal withdrawAmount = makeInvoice.getWithdrawAmount();
        BigDecimal invoicesTotalAmount = makeInvoice.getInvoicesTotalAmount();
        BigDecimal offlineAmount = makeInvoice.getOfflineAmount();

        return withdrawAmount.subtract(invoicesTotalAmount.add(offlineAmount)).abs().compareTo(new BigDecimal("1")) <= 0;

    }

    public static Date getIntervalDayFormat(Date date, Integer interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 0 - interval);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static String getIntervalDayInyyyyMMddFormat(Date date, Integer interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 0 - interval);

        return convertMonthInyyyyMMddFormat(calendar.getTime());
    }

    public static String convertMonthInyyyyMMddFormat(final Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }

//        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        return String.valueOf(calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH));
    }



//    private static String urlTimeToFormalTime(String urlTime) {
//        if (urlTime.length() != 23) {
//            return "";
//        }
//
//        StringBuffer bufferUrlTime = new StringBuffer(urlTime);
//
//        bufferUrlTime.replace(10, 11, " ");
//        bufferUrlTime.replace(13, 16, ":");
//        bufferUrlTime.replace(16, 19, ":");
//
//        return bufferUrlTime.toString();
//    }
//    static private boolean isBigDecimal(String number) {
//        try {
//            BigDecimal bd = new BigDecimal(number);
//
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
//    private static boolean checkTimeFormat(String time) {
//        boolean convertSuccess = true;
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        try {
//            format.setLenient(false);
//            format.parse(time);
//        } catch (ParseException e) {
//            convertSuccess = false;
//        }
//
//        return convertSuccess;
//    }
}
