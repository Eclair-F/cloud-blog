package com.qw.provideruser.services;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * @Program: cloud-blog
 * @ClassName SendMail
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-23 23:41
 * @Version 1.0
 **/
@Service
public class SendMail {

    public static boolean sendMail(String email, String uuid) {
        try {
            //发件人地址
            String senderAddress = "lvqiangwei@aliyun.com";
            //收件人地址
            String recipientAddress = email;
            //发件人账户名
            String senderAccount = "lvqiangwei@aliyun.com";
            //发件人账户密码
            String senderPassword = "eclair00";

            //1、连接邮件服务器的参数配置
            Properties props = new Properties();
            //设置用户的认证方式
            props.setProperty("mail.smtp.auth", "true");
            //设置传输协议
            props.setProperty("mail.transport.protocol", "smtp");
            //设置发件人的SMTP服务器地址
            //    props.setProperty("mail.smtp.host", "smtp.qq.com");
            props.setProperty("mail.smtp.host", "smtp.aliyun.com");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.ssl.enable", "true");


            //2、创建定义整个应用程序所需的环境信息的 Session 对象
            Session session = Session.getInstance(props);
            //设置调试信息在控制台打印出来
            session.setDebug(true);
            //3、创建邮件的实例对象
            //创建一封邮件的实例对象
            MimeMessage msg = new MimeMessage(session);


            String nick = javax.mail.internet.MimeUtility.encodeText("绿蔷薇");

            //设置发件人地址
            msg.setFrom(new InternetAddress(nick + " <" + senderAddress + ">"));
            /**
             * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
             * MimeMessage.RecipientType.TO:发送
             * MimeMessage.RecipientType.CC：抄送
             * MimeMessage.RecipientType.BCC：密送
             */
            msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipientAddress));
            //设置邮件主题
            msg.setSubject("邮件主题", "UTF-8");
            //设置邮件正文
            String content = "<html><head></head><body><h1>这是一封激活邮件,请在三小时内激活，激活请点击以下链接</h1><h3><a href='http://www.lvqiangwei.top:8601/user/active/"
                    + uuid + "'>http://www.lvqiangwei.top:8601/user/active/" + uuid
                    + "</href></h3></body></html>";
            msg.setContent(content, "text/html;charset=UTF-8");
            //设置邮件的发送时间,默认立即发送
            msg.setSentDate(new Date());


            //4、根据session对象获取邮件传输对象Transport
            Transport transport = session.getTransport();
            //设置发件人的账户名和密码
            transport.connect(senderAccount, senderPassword);
            //发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(msg, msg.getAllRecipients());

            //如果只想发送给指定的人，可以如下写法
            //  transport.sendMessage(msg, new Address[]{new InternetAddress("674531472@qq.com")});

            //5、关闭邮件连接
            transport.close();
            return true;
        } catch (UnsupportedEncodingException | MessagingException e) {
            return false;
        }

    }

}
