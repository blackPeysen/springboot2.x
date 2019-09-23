package com.org.peysen.bootemail.commandLineRunner;

import com.org.peysen.bootemail.entity.MailBean;
import com.org.peysen.bootemail.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description: 实现CommandLineRunner接口，在项目启动时发送测试邮件
 * Created by mengmeng.Pei
 * 2019/9/23 10:03
 */

//@Component
public class EmailLineRunner implements CommandLineRunner {

    @Autowired
    private MailUtil mailUtil;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("------------- SpringBoot集成JavaMail实现邮件发送开始 -------------");
        MailBean mailBean = MailBean.getMailBean();
        /*
         * 简易邮件发送测试
         */
//        mailBean.setSubject("SpringBoot集成JavaMail实现邮件发送");
//        //发送激活邮件链接，点击激活
//        mailBean.setText("SpringBoot集成JavaMail实现简易邮件发送功能,点击激活:http://localhost:8080/email/validation?token=peysen");
//        mailUtil.sendMail(mailBean);

        /**
         * HTML邮件正文发送测试
         */
       String html =
              "<html>"
              + "<body>"
              + "<p><h1>SpringBoot集成JavaMail实现正文为HTML的邮件发送功能</h1></p>"
              + "<a href=\"http://localhost:8080/email/validation?token=peysen\">点击激活</a>"
              + "</body>"
              + "</html>";
       mailBean.setSubject("SpringBoot集成JavaMail实现邮件发送");
       mailBean.setText(html);
       mailUtil.sendMailHtml(mailBean);

            /**
             * 附件邮件发送测试
             */
//      String filePath="/Users/shanglishuai/Downloads/Jietu20180727-144621@2x.jpg";
//      FileSystemResource file = new FileSystemResource(new File(filePath));
//      String attachmentFilename = filePath.substring(filePath.lastIndexOf(File.separator));
//      mailBean.setSubject("SpringBoot集成JavaMail实现邮件发送");
//      mailBean.setText("SpringBoot集成JavaMail实现附件邮件发送");
//      mailBean.setFile(file);
//      mailBean.setAttachmentFilename(attachmentFilename);
//      mailUtil.sendMailAttachment(mailBean);


            /**
             * 内联资源邮件发送测试
             */
//      String filePath="/Users/shanglishuai/Downloads/Jietu20180727-144621@2x.jpg";
//      String contentId = UUID.randomUUID().toString().replace("-", "");
//      /*
//       * <img src=\'cid:" + contentId + "\' >
//       * cdi:是固定的，其后连接内联资源的的ID（保证唯一即可）
//       */
//      String content = "<html><body>内联资源邮件发送：<img src=\'cid:" + contentId + "\' ></body></html>";
//      FileSystemResource file = new FileSystemResource(new File(filePath));
//      mailBean.setSubject("SpringBoot集成JavaMail实现邮件发送");
//      mailBean.setText(content);
//      mailBean.setFile(file);
//      mailBean.setContentId(contentId);
//      mailUtil.sendMailInline(mailBean);


        System.out.println("------------- SpringBoot集成JavaMail实现邮件发送结束 -------------");
    }

}
