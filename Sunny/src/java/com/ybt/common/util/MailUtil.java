package com.ybt.common.util;


import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @module javaMail发送邮件工具类
 * @author bqy  @time 下午1:24:06
 */
public class MailUtil {
        /**
         * 发送邮件
         * 
         * @param mailServerHost
         *            邮件服务器地址
         * @param mailServerPort
         *            邮件服务器端口
         * @param validate
         *            是否要求身份验证
         * @param fromAddress
         *            发送邮件地址
         * @param toAddress
         *            接收邮件地址
         * @param subject
         *            邮件主题
         * @param content
         *            邮件内容
         * @param isHTML
         *            是否是html格式邮件
         * @param isSSL
         *            邮件服务器是否需要安全连接(SSL)
         * @return true:发送成功;false:发送失败
         */
        public static boolean sendMail(String mailServerHost,
                        String mailServerPort, boolean validate, String fromAddress,
                        String userPass, String toAddress, String subject, String content,
                        boolean isHTML, boolean isSSL) {
                Properties p = new Properties();
                p.put("mail.smtp.host", mailServerHost);
                p.put("mail.smtp.port", mailServerPort);
                p.put("mail.smtp.auth", validate ? "true" : "false");
                if (isSSL) {
                        p.put("mail.smtp.starttls.enable", "true");
                        p.put("mail.smtp.socketFactory.fallback", "false");
                        p.put("mail.smtp.socketFactory.port", mailServerPort);
                }
                Authenticator auth = null;
                if (validate) {
                        auth = new myAuthenticator(fromAddress, userPass);
                }

                try {
                        Session session = Session.getDefaultInstance(p, auth);
                        Message message = new MimeMessage(session);
                        Address from = new InternetAddress(fromAddress);
                        Address to = new InternetAddress(toAddress);
                        message.setFrom(from);
                        message.setRecipient(Message.RecipientType.TO, to);
                        message.setSubject(subject);
                        message.setSentDate(new Date());
                        if (isHTML) {
                                Multipart m = new MimeMultipart();
                                BodyPart bp = new MimeBodyPart();
                                bp.setContent(content, "text/html; charset=utf-8");
                                m.addBodyPart(bp);
                                message.setContent(m);
                        } else
                                message.setText(content);
                        Transport.send(message);
                        return true;
                } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                }
        }

         public static void main(String[] args) {
         System.out
         .println(MailUtil
         .sendMail(
         "smtp.dm.aliyun.com",
         "25",
         true,
         "admin@service.ybt.org",
         "hua123456zan",
         "646662872@qq.com",
         "芸备胎网-注册确认",
         "请点击下面的链接完成注册：<a href='http://www.ybt.org/checkemail?email=646662872@qq.com&token=C2F6EDBC54C8873F1351668A7360D8ED'>http://www.ybt.org/checkemail?email=646662872@qq.com&token=C2F6EDBC54C8873F1351668A7360D8ED</a>"
         + new Date(), true, true));
         }
}

class myAuthenticator extends Authenticator {
        String userName;
        String userPass;

        public myAuthenticator() {
        }

        public myAuthenticator(String userName, String userPass) {
                this.userName = userName;
                this.userPass = userPass;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPass);
        }
}