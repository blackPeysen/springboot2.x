package com.org.peysen.bootrabbitmq.util;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.lang3.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    public static final String YEAR_FORMAT = "yyyy";
    public static final String SHORT_EN_FORMAT_MONTH = "yyyyMM";
    public static final String SHORT_FORMAT_MONTH = "yyyy-MM";
    public static final String SHORT_FORMAT = "yyyyMMdd";
    public static final String SHORT_EN_FORMAT = "yyyy-MM-dd";
    public static final String MINUTE_FORMAT = "yyyyMMddHHmm";
    public static final String EN_FORMAT_A = "yyyyMMddHHmmss";
    public static final String EN_FORMAT_B = "yyyy/MM/dd HH:mm:ss";
    public static final String EN_FORMAT_C = "yyyyMMddHHmmss.SSS";
    public static final String EN_FORMAT_D = "yyyyMMdd HHmmss";
    public static final String EN_FORMAT_E = "yyyyMMddHHmmssSSS";
    public static final String EN_NOSECOND_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String BASE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String CHINESE_FORMAT = "yyyy年MM月dd日HH时mm分ss秒";
    public static final String CHINESE_FORMAT_NOSECOND = "yyyy年MM月dd日HH时mm分";
    public static final String SHORT_C_FORMAT = "yyyy年MM月dd日";

    public static final String END_DAY_FOMAT_A = "yyyy/MM/dd 23:59:59";
    public static final String END_DAY_FOMAT_B = "yyyy-MM-dd 23:59:59";
    public static final String CN_SHORT_FORMAT = "MM月dd日";
    public static final String CN_CUT_FORMAT = "MM月dd日  HH:mm";
    public static final String CN_NOSECOND_FORMAT = "yyyy年MM月dd日  HH:mm";
    /**
     * 月份格式 yyyy-MM
     */
    public static final String MONTH_FORMATE = "yyyy-MM";

    public final static int ERA = 0;
    public final static int YEAR = 1;
    public final static int MONTH = 2;
    public final static int WEEK_OF_YEAR = 3;
    public final static int WEEK_OF_MONTH = 4;
    public final static int DATE = 5;
    public final static int DAY_OF_MONTH = 5;
    public final static int DAY_OF_YEAR = 6;
    public final static int DAY_OF_WEEK = 7;
    public final static int DAY_OF_WEEK_IN_MONTH = 8;
    public final static int AM_PM = 9;
    public final static int HOUR = 10;
    public final static int HOUR_OF_DAY = 11;
    public final static int MINUTE = 12;
    public final static int SECOND = 13;
    public final static int MILLISECOND = 14;

    public static Date now() {
        return new Date();
    }

    public static String formatDate() {
        return formatDate(BASE_FORMAT, now());
    }

    public static String formatDateEnSHORT() {
        return formatDate(SHORT_EN_FORMAT, now());
    }

    public static String formatDateSHORT() {
        return formatDate(SHORT_FORMAT, now());
    }

    public static String formatDateA() {
        return formatDate(EN_FORMAT_A, now());
    }

    public static String formatDateA(Date date) {
        return formatDate(EN_FORMAT_A, date);
    }

    public static String formatDateD() {
        return formatDate(EN_FORMAT_D, now());
    }

    public static String formatDateE() {
        return formatDate(EN_FORMAT_E, now());
    }

    public static String formatDateD(Date date) {
        return formatDate(EN_FORMAT_D, date);
    }

    public static String formatDate(String formatter, Date date) {
    	if(date == null)
    		return null;
        SimpleDateFormat format = new SimpleDateFormat(formatter);
        return format.format(date);
    }
    
    public static String formatDate(String date, String fromFormartter, String toFormartter) {
    	if(StringUtils.isBlank(date))
    		return null;
    	return formatDate(toFormartter, parse(date, fromFormartter));
    }
    
    public static String formatStartDate(String date, String fromFormartter, String toFormartter) {
    	if(StringUtils.isBlank(date))
    		return null;
    	return formatDate(toFormartter, startOfDay(parse(date, fromFormartter)));
    }
    
    public static String formatEndDate(String date, String fromFormartter, String toFormartter) {
    	if(StringUtils.isBlank(date))
    		return null;
    	return formatDate(toFormartter, endOfDay(parse(date, fromFormartter)));
    }

    public static void main(String[] artgs) {
        /*String date = "2017-09-23";
        Date parse = parse(date, SHORT_EN_FORMAT);
        System.err.println(parse.getTime());*/
    	String a = formatDate(EN_FORMAT_D, now());
    	System.err.println(a);
    }

    /**
     * 获取当前月份的倒数第N天:
     * 当n=1:31号
     */
    public static String getBottomDay(int n) {
        int year=getDateYear();
        int month=getDateMonth();
        SimpleDateFormat df=new SimpleDateFormat(BASE_FORMAT);
        Calendar c = Calendar.getInstance();
        c.set(year,month,1,23,59,59);
        c.add(Calendar.DAY_OF_MONTH, -n);
        return df.format(c.getTime());
    }

    //获取当前时间的年份
    public static int getDateYear(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    //获取当前时间的月份
    public static int getDateMonth(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int month = cal.get(Calendar.MONTH);
        return month+1;
    }

    public static Date yesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static Date lastMinute() {
        return lastMinute(new Date());
    }

    public static Long oneMinute() {
        return 60000L;
    }

    public static Long minutes(int num) {
        return num * oneMinute();
    }

    public static Long oneHour() {
        return 60 * oneMinute();
    }

    public static Long hours(int num) {
        return num * oneHour();
    }

    public static Date lastMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -1);
        return calendar.getTime();
    }

    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static Date addMonths(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    public static Date addMinutes(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(BASE_FORMAT);
        return format.format(date);
    }

    public static String formatDateShort(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(SHORT_FORMAT);
        return format.format(date);
    }

    public static Date addFileds(Date date, int filed, int val) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(filed, val);
        return calendar.getTime();
    }

    public static Date startOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        calendar2.set(Calendar.HOUR_OF_DAY, 0);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.MILLISECOND, 0);
        return calendar2.getTime();
    }

    public static Date endOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        calendar2.set(Calendar.HOUR_OF_DAY, 23);
        calendar2.set(Calendar.MINUTE, 59);
        calendar2.set(Calendar.SECOND, 59);
        calendar2.set(Calendar.MILLISECOND, 999);
        return calendar2.getTime();
    }

    public static Date startOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        calendar2.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar2.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar2.set(Calendar.DATE, 1);
        return startOfDay(calendar2.getTime());
    }

    public static Date startOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        calendar2.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar2.set(Calendar.MONTH, 0);
        calendar2.set(Calendar.DATE, 1);
        return startOfDay(calendar2.getTime());
    }

    public static Date endOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 11);
        return endOfMonth(calendar.getTime());
    }

    public static Date endOfMonth(Date date) {
        Date tempDate = startOfMonth(date);
        tempDate = addMonths(tempDate, 1);
        tempDate = addDays(tempDate, -1);
        tempDate = endOfDay(tempDate);
        return tempDate;
    }

    public static Date parse(String dateStr) {
        return parse(dateStr, BASE_FORMAT);
    }

    public static Date parse(String dateStr, String formatStr) {
        return parse(dateStr, formatStr, null);
    }

    public static Date parse(String dateStr, String formatStr, Date defaultDate) {
    	if(StringUtils.isBlank(dateStr)) {
    		return null;
    	}
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            return defaultDate;
        }
    }

    public static Date set(Date srcDate, int field, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(srcDate);
        calendar.set(field, value);
        return calendar.getTime();
    }

    public static int get(Date srcDate, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(srcDate);
        return calendar.get(field);
    }

    /**
     * 获取指定月份上个月的月份 YYYYMM
     *
     * @param month
     * @return
     */
    public static String getLastMonth(String month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parse(month + "01", SHORT_EN_FORMAT));
        cal.add(Calendar.MONTH, -1);
        return formatDate(SHORT_EN_FORMAT, cal.getTime()).substring(0, 6);
    }

    /**
     * 获取系统当前月份字符串 （格式：yyyy-MM）
     *
     * @return String 当前月份yyyy-MM
     * @throws ParseException
     */
    public static String getMonthToNowStr() {
        String dateStr = "";
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(MONTH_FORMATE);
        dateStr = format.format(calendar.getTime());
        return dateStr;
    }

    /**
     * 获取当前系统时间的上个月份字符串 （格式：yyyy-MM）
     *
     * @return
     */
    public static String getLastMonthToNowStr() {
        String dateStr = "";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        SimpleDateFormat format = new SimpleDateFormat(MONTH_FORMATE);
        dateStr = format.format(calendar.getTime());
        return dateStr;
    }

    /**
     * 获取上月月份（参数如：2012-09, 返回值： 2012-10 ）
     *
     * @param curDate 日期（如：2012-09、2012-09-01）
     * @return String 上月月份 yyyy-MM
     */
    public static String getLastMonths(String curDate) {
        String firstMonthChar = curDate.substring(5, 6);
        String year = curDate.substring(0, 4);
        String nextDate = "";
        if ("0".equals(firstMonthChar)) {// 月小于10
            String month = curDate.substring(6, 7);
            int iMonth = new Integer(month).intValue();
            int iNextIMonth = iMonth - 1;
            if (iNextIMonth <= 0) {
                nextDate = new Integer(year) - 1 + "-"
                        + new Integer(12).toString();

            } else {
                nextDate = year + "-0" + new Integer(iNextIMonth).toString();

            }
        } else {// 月大于10
            String month = curDate.substring(5, 7);
            int iMonth = new Integer(month).intValue();
            int iNextIMonth = iMonth - 1;
            nextDate = year + "-" + new Integer(iNextIMonth).toString();
        }
        return nextDate;
    }

    /**
     * 获取当前系统时间的前一天
     *
     * @return
     */
    public static String getYesterday() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat format = new SimpleDateFormat(SHORT_FORMAT);
        String str = format.format(calendar.getTime());
        return str;
    }

    /**
     * 取得传入的日期(yyyy-MM-dd)的后几日日期串
     *
     * @param str 传入的日期字符串,n:后几日
     * @return String yyyy-MM-dd
     */
    public static String getStringAfterNDay(String str, int n) {
        Calendar ca = parseStringToCalendar(str);
        if (ca == null) {
            return null;
        }
        ca.add(Calendar.DATE, n);
        String strDate = ca.get(Calendar.YEAR) + "-";
        int intMonth = ca.get(Calendar.MONTH) + 1;
        if (intMonth < 10) {
            strDate += "0" + intMonth + "-";
        } else {
            strDate += intMonth + "-";
        }
        int intDay = ca.get(Calendar.DATE);
        if (intDay < 10) {
            strDate += "0" + intDay;
        } else {
            strDate += intDay;
        }
        return strDate;
    }

    // 将一个日期字符串转化成Calendar
    // 字符串格式为yyyy-MM-dd
    public static Calendar parseStringToCalendar(String strDate) {
        Date date = parseStringToUtilDate(strDate);
        if (date == null) {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca;
    }


    public static Date parseDateToDate(Date date, String format) {

        String formatDate = formatDate(date);
        return parseStringToUtilDate(formatDate, format);
    }

    public static Date parseStringToUtilDate(String strDate, String format) {

        Date date = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(format);
            if (!"".equals(strDate)) {
                date = df.parse(strDate);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return date;
    }

    // 将一个日期字符串转化成java.utils.Date日期
    // 字符串格式为yyyy-MM-dd
    public static Date parseStringToUtilDate(String strDate) {

        Date date = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if (!"".equals(strDate)) {
                date = df.parse(strDate);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return date;
    }

    /**
     * @param date 时间字符串 yyyy-MM-dd或yyyyMM或yyyy-MM
     * @param n    对date月份的操作月数 为正数 就是向后加N个月 为负数就是向前+n个月
     * @return 返回月份格式为yyyyMM
     */
    public static String getStringOperMonth(String date, int n) {
        if (date == null || date.equals("")) {
            return null;
        }
        date = date.replaceAll("-", "");
        StringBuffer sb = new StringBuffer(date);
        sb.insert(4, "-");
        date = date.length() == 6 ? sb.append("-01").toString() : sb.insert(7,
                "-").toString();
        Calendar calendar = parseStringToCalendar(date);
        calendar.add(Calendar.MONTH, n);
        Date theDate = calendar.getTime();
        String time = new SimpleDateFormat("yyyyMM").format(theDate);
        return time;
    }

    public static String getStringOperMonth1(String date, int n) {
        if (date == null || date.equals("")) {
            return null;
        }
        date = date.replaceAll("-", "");
        StringBuffer sb = new StringBuffer(date);
        sb.insert(4, "-");
        date = date.length() == 6 ? sb.append("-01").toString() : sb.insert(7,
                "-").toString();
        Calendar calendar = parseStringToCalendar(date);
        calendar.add(Calendar.MONTH, n);
        Date theDate = calendar.getTime();
        String time = new SimpleDateFormat("yyyy-MM-dd").format(theDate);
        return time;
    }

    public static String getStringOperMonth(String date, int n,
                                            String formatString) {
        if (date == null || date.equals("")) {
            return null;
        }
        date = date.replaceAll("-", "");
        StringBuffer sb = new StringBuffer(date);
        sb.insert(4, "-");
        date = date.length() == 6 ? sb.append("-01").toString() : sb.insert(7,
                "-").toString();
        Calendar calendar = parseStringToCalendar(date);
        calendar.add(Calendar.MONTH, n);
        Date theDate = calendar.getTime();
        String time = new SimpleDateFormat(formatString).format(theDate);
        return time;
    }

    public static String getSelfFormat(String val, String valFormat,
                                       String format) {

        SimpleDateFormat dfTemp = new SimpleDateFormat(valFormat);
        SimpleDateFormat dfTemp2 = new SimpleDateFormat(format);
        Date dt = null;
        try {
            dt = dfTemp.parse(val);
        } catch (ParseException e) {
            return null;
        }
        return dfTemp2.format(dt);
    }

    // 获取已知日期前一天日期 yyyy-mm-dd
    public static String getSpecifiedDayBefore(String specifiedDay) {// 可以用new
        // Date().toLocalString()传递参数
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
                .getTime());
        return dayBefore;
    }

    public static String formatDayString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
        }
        SimpleDateFormat format = new SimpleDateFormat("dd");
        return format.format(date);
    }

    public static String formatDayMMString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
        }
        SimpleDateFormat format = new SimpleDateFormat("MM");
        return format.format(date);
    }

    public static String formatDayStringMM(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

    public static Date formatStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
        }
        return date;
    }

    public static String getYOYDate(String date) {
        return formatDate(SHORT_FORMAT, addMonths(formatStringToDate(date), -1));
    }

    /**
     * 获取前一天日期
     *
     * @return
     */
    public static Date beforeYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -2);
        return calendar.getTime();
    }

    /**
     * 获取环比月份
     */
    public static String getHuanMonth(String month, String formatter) {
        return DateUtil.formatDate(formatter,
                DateUtil.addMonths(DateUtil.parse(month, formatter), -1));
    }

    /**
     * 获取同比月份
     */
    public static String getTongMonth(String month, String formatter) {
        return DateUtil.formatDate(formatter, DateUtil.addFileds(
                DateUtil.parse(month, formatter), Calendar.YEAR, -1));
    }

    /**
     * 获取环比日期
     */
    public static String getHuanDate(String month, String formatter) {
        return DateUtil.formatDate(formatter,
                DateUtil.addDays(DateUtil.parse(month, formatter), -1));
    }

    /**
     * 获取同比日期
     */
    public static String getTongDate(String month, String formatter) {
        return DateUtil.formatDate(formatter,
                DateUtil.addMonths(DateUtil.parse(month, formatter), -1));
    }

    /**
     * 校验日期合法性
     */
    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 传入一个日期格式的日期，返回为日期格式所在年份第几天
     *
     * @param date
     * @return
     */
    public static int orderDate(Date date, Byte type) {
        int dateSum = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(date);
        int year = Integer.valueOf(dateStr.substring(0, 4));
        int month = Integer.valueOf(dateStr.substring(5, 7));
        int day = Integer.valueOf(dateStr.substring(8, 10));
        if (type == 2) {
            for (int i = 1; i < month; i++) {
                switch (i) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        dateSum += 31;
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        dateSum += 30;
                        break;
                    case 2:
                        if (((year % 4 == 0) & (year % 100 != 0)) | (year % 400 == 0)) {
                            dateSum += 29;
                        } else {
                            dateSum += 28;
                        }
                        break;
                    default:

                }
            }
            dateSum = dateSum + day;
        } else if (type == 3) {
            dateSum = day;
        }

        return dateSum;
    }

    /**
     * 传入一个日期格式的日期和类型，返回当年或当月共多少天；type=2:年，type=3:月
     *
     * @param date
     * @param type
     * @return
     */
    public static int returnAlldaynum(Date date, Byte type) {
        int dateSum = 0;
        int monthSum = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(date);
        int year = Integer.valueOf(dateStr.substring(0, 4));
        int month = Integer.valueOf(dateStr.substring(5, 7));
        if (type == 2) {
            month = 13;
        }
        for (int i = 1; i < month; i++) {
            switch (i) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    dateSum += 31;
                    monthSum = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    dateSum += 30;
                    monthSum = 30;
                    break;
                case 2:
                    if (((year % 4 == 0) & (year % 100 != 0)) | (year % 400 == 0)) {
                        dateSum += 29;
                        monthSum = 29;
                    } else {
                        dateSum += 28;
                        monthSum = 28;
                    }
                    break;
                default:

            }
        }
        if (type == 3) {
            dateSum = monthSum;
        }
        return dateSum;
    }

    /**
     * @param num
     * @return
     * @Description 获取当前时间 num 个月前或后的时间数据
     * @author MengQiu.Liu
     * @date 2018/2/25
     */
    public static Date getAnyMonthToday(int num) {

        Date dNow = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dNow);
        calendar.add(Calendar.MONTH, num);
        Date dResult = calendar.getTime();
        return dResult;
    }

    /**
     * 获得分表名
     *
     * @param tableName
     * @return
     */
    public static String getTableNameWithYearAndMonth(String tableName) {
        Calendar cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        String mon = null;
        if (month < 10) {
            mon = "0" + month;
        } else {
            mon = month + "";
        }
        return tableName + "_" + year + mon;
    }

    public static String getTableNameWithYearAndMonth(String tableName,Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        String mon = null;
        if (month < 10) {
            mon = "0" + month;
        } else {
            mon = month + "";
        }
        return tableName + "_" + year + mon;
    }
    /**
     * Date 转 XmlGregorianCalendar
     *
     * @param date
     * @return
     */
    public static XMLGregorianCalendar dateToXml(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gc;
    }


    /**
     * XMLGregorianCalendar 转 Date
     *
     * @param gc
     * @return
     */
    public Date xmlToDate(XMLGregorianCalendar gc) {
        GregorianCalendar ca = gc.toGregorianCalendar();
        return ca.getTime();
    }
}
