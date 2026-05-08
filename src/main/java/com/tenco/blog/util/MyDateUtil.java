package com.tenco.blog.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Date;
import java.sql.Timestamp;

public class MyDateUtil {


    public static String timestapmFormat(Timestamp timestamp){
        // date 를 만들어 타임스태프를 넣는다
        Date currentDate = new Date(timestamp.getTime());
        // 현재 시간을 포맷하는 메서드를 실행시키고 반환
        return DateFormatUtils.format(currentDate,"yyyy-MM-dd");
    }
}
