package com.common.util;

import java.util.UUID;

//http://javag.iteye.com/blog/127753
public class UUIDGenerator {

    public UUIDGenerator() {
        // TODO Auto-generated constructor stub
    }

    /** 
     * 獲得一個UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){
        String s = UUID.randomUUID().toString();
        //去掉“-”符號 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    }

    /** 
     * 獲得指定數目的UUID 
     * @param number int 需要獲得的UUID數量 
     * @return String[] UUID陣列 
     */ 
    public static String[] getUUID(int number){ 
        if(number < 1){ 
            return null; 
        } 
        String[] ss = new String[number]; 
        for(int i=0;i<number;i++) { 
            ss[i] = getUUID(); 
        } 
        return ss; 
    }

    public static void main(String[] args) {
        String[] ss = getUUID(10); 
        for(int i=0;i<ss.length;i++) {
            System.out.println(ss[i]); 
        }
    }
}
