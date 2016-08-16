# JavaMail API的简单使用
### JAVA邮件发送的大致过程是这样的的：

1、构建一个继承自javax.mail.Authenticator的具体类，并重写里面的getPasswordAuthentication()方法。此类是用作登录校验的，以确保你对该邮箱有发送邮件的权利。

2、构建一个properties文件，该文件中存放SMTP服务器地址等参数。

3、通过构建的properties文件和javax.mail.Authenticator具体类来创建一个javax.mail.Session。Session的创建，就相当于登录邮箱一样。剩下的自然就是新建邮件。

4、构建邮件内容，一般是javax.mail.internet.MimeMessage对象，并指定发送人，收信人，主题，内容等等。

5、使用javax.mail.Transport工具类发送邮件。