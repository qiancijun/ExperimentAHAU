package com.qiancijun.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.qiancijun.exception.WrongDateException;

public class DateUtil {
	private static int days[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public static Date checkDate(String date) throws WrongDateException, ParseException {
		String tmp[] = date.split("-");
		if (tmp[0].length() != 4 || tmp[1].length() > 2 || tmp[2].length() > 2) {
			throw new WrongDateException(date + "日期格式错误");
		}
		int year = Integer.parseInt(tmp[0]);
		int month = Integer.parseInt(tmp[1]);
		int day =  Integer.parseInt(tmp[2]);
		if (month == 0 || month > 12) throw new WrongDateException(date + "月份不正确");
		if (day == 0) throw new WrongDateException(date + "日期不能为0");
		if (month != 2) {
			if (day > days[month]) throw new WrongDateException(date + "日期错误");
		} else {
			int leap = (year % 100 == 0 && year % 4 == 0 || year % 400 == 0) ? 1 :0;
			if (day >28 + leap) throw new WrongDateException(date + "日期形式不对");
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date res = sdf.parse(date);
		return res;
	}
}
