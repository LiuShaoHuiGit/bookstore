package com.baidu.test;

import java.util.LinkedHashMap;
import java.util.Map;

public class pass {

    public static void main(String[] args) {
        int a,b,c;
        for(int  total=1;total<100;total++){
            a=total%3;
            b=total%7;
            c=total%5;
            if(a==1 && b==5 && c==0){
                System.out.println("韩信点兵的人数是:"+total);
            }
        }

    }

    public static String a (){
        return "name";
    }

}
