package com.bigstomach.tongjihealthcare.convert;


import java.util.Calendar;

public class CustomMapping {
    public static String getSex(String idNumber) {
        String sex="";
        String sexNum = idNumber.substring(16, 17);
        if (Integer.parseInt(sexNum) % 2 != 0) {
            sex = "男";
        } else {
            sex= "2";//女
        }
        return sex;
    }
    public static Integer getAge(String idNumber) {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        String year = idNumber.substring(6, 10);
        int iCurrYear = cal.get(Calendar.YEAR);
        age = iCurrYear - Integer.parseInt(year);
        return age;
    }
}
