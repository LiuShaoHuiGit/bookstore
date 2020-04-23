package com.book.utils;

import com.book.pojo.BookUser;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;


public class BookUserUtils {


    /**
     * map 转对象
     * @param map
     * @param clazz
     * @return
     */
    public static Object map2Object(Map<String, String> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * MD5加密
     * @param passWord
     * @return
     */
    public static String getMD5(String passWord){
        return DigestUtils.md5DigestAsHex(passWord.getBytes());
    }
    /**
     * 获取UUID数值
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
    /**
     * 用户登录正则判断是否是email
     * @param bookUser
     * @return
     */
    public static BookUser uesrRex(BookUser bookUser){
        String emailRex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        if(bookUser.getUserName().matches(emailRex)){
            bookUser.setEmail(bookUser.getUserName());
            bookUser.setUserName(null);
        }
        return bookUser;
    }
    /**
     * 获取六位认证码
     * @return
     */
    public static int BookUserCode(){
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0 ;i<6 ;i++){
            stringBuffer.append((int)(Math.random()*9+1));
        }
        return Integer.valueOf(stringBuffer.toString());
    }
    /**
     *
     * @return
     */
    public static int getBookUserEmail(String toEmail) throws Exception{
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com"); //// 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp"); // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true"); // 需要验证用户名密码

        // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        //使用JavaMail发送邮件的5个步骤
        //创建定义整个应用程序所需的环境信息的 Session 对象
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、授权码
                return new PasswordAuthentication("2809579471@qq.com", "nbgjsluzfhdkdgjb");
            }
        });
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport("smtp");
        //3、使用邮箱的用户名和授权码连上邮件服务器
        ts.connect("smtp.qq.com", "2809579471@qq.com", "nbgjsluzfhdkdgjb");
        //4、创建邮件
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("2809579471@qq.com"));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        //邮件的标题
        message.setSubject("只包含文本的简单邮件");
        int num  = BookUserCode();
        //邮件的文本内容
        message.setContent("<h1>你好！您的验证码为："+num+"</h1>", "text/html;charset=UTF-8");
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
        return num;
    }
}
