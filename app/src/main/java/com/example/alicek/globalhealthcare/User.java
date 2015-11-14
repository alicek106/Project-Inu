package com.example.alicek.globalhealthcare;

/**
 * Created by alicek on 2015-11-14.
 */
public class User {
    private static String m_phoneNumber;
    private static String m_name;
    private static String m_belonging;
    private static String m_empNumber;

    public static String getPhoneNumber(){
        return m_phoneNumber;
    }

    public static void setUser(String phoneNumber, String name, String belonging, String empNumber){
        m_phoneNumber = phoneNumber;
        m_name = name;
        m_belonging = belonging;
        m_empNumber = empNumber;

        System.out.println("User initialized.");
        System.out.println("phone : " + m_phoneNumber);
        System.out.println("name : " + m_name);
        System.out.println("belong : " + m_belonging);
        System.out.println("empNum : " + m_empNumber);

    }

    public static String getName(){
        return m_name;
    }
    public static String getBelonging(){
        return m_belonging;
    }
    public static String getEmpNumber(){
        return m_empNumber;
    }

}
