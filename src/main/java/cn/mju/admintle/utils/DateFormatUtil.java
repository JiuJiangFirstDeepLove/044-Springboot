package cn.mju.admintle.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



//Date类型转换格式数据库查找
public class DateFormatUtil {

    public static Date getTime(SimpleDateFormat sdf,Date date){
        Date time = new Date();
        try {
            time = sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }


    public static Date getToday(){
        Date yyyyMMdd = getTime(new SimpleDateFormat("yyyyMMdd"), new Date());
        return yyyyMMdd;
    }
}
