package com.vbiso.utils;

/**
 * 验证码生成工具类
 */
public class InviteCodeUtil {
    private static final int random=10;
    public static String inviteCode(){
        StringBuffer stringBuffer=new StringBuffer(7);
        for(int i=0;i<6;i++){
            int buffer = (int) (Math.random()*random);
            stringBuffer.append(buffer);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args){
        String s = inviteCode();
        System.out.println(s);
    }
}
