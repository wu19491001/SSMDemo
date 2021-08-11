package wu;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.javassist.expr.Instanceof;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.DigestUtils;

import com.mysql.cj.xdevapi.JsonArray;
import com.wu.domain.Department;
import com.wu.domain.User;
import com.wu.domain.UserGroup;
import com.wu.service.impl.CRUDDepServiceImpl;
import com.wu.service.impl.CRUDServiceImpl;
import com.wu.service.inter.CRUDDepService;
import com.wu.service.inter.CRUDService;
import com.wu.tools.SendMail;
import com.wu.tools.Tools;

public class test {
	ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
 @Test
 public void ss() {
	 CRUDDepService crud=context.getBean("cruddepServiceImpl", CRUDDepServiceImpl.class);
	 List<Department> deplist=crud.FindAllDep();
	 System.out.println(deplist);
 }
 @Test
 public void login() {
	 CRUDService crud=context.getBean("crudServiceImpl", CRUDServiceImpl.class);
	 User user=context.getBean(User.class);
	 User user1=context.getBean(User.class);
	 user.setId(10005);
     user1=crud.FindUserById(user.getId()); 
     System.out.println(user1);
     System.out.println(user.getId());
 }
 @Test
 public void find() {
	 CRUDService crud=context.getBean("crudServiceImpl", CRUDServiceImpl.class);
	 User user=context.getBean(User.class);
	user=crud.FindUserByName("admin");
     System.out.println(user);
 }
 @Test
 public void st() {
	 String bx="123";
	 String slat=Tools.toolReverse(bx);
	 System.out.println(slat);
	 StringBuilder sb=new StringBuilder();
	 sb.append(bx);
	 sb.append(slat);
	 String bsmd=DigestUtils.md5DigestAsHex(sb.toString().getBytes());
	 System.out.println(bsmd);
 }
 @Test
 public void sendmail() {
	 String toemail="853634648@qq.com";
	 String msg="562154562";
	 try {
		SendMail.sendmail(msg, toemail);
	} catch (GeneralSecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
 }
 @Test
 public void demo() {
	 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 String da=df.format(new Date());
	 System.out.println(da);
  }
 @Test
 public void demo1() {
	 String ss="45421.561234.jpg";
	 int s=ss.lastIndexOf(".");
	 System.out.println(s);
	 String st=ss.substring(s);
	 System.out.println(st);
  }
 @Test
 public void demo2() {
	List<Integer> prime=new ArrayList<>();
	for(int i=2;i<=100;i++) {
		boolean pflag=true;
		for(int j=2;j<i;j++) {
			if(i%j==0) {
				pflag=false;
				break;
			}
		}
		if(pflag) {
			prime.add(i);
		}
	}
	for(int p=0;p<prime.size();p++) {
		System.out.println(p+"---"+prime.get(p));
	}
}
 @Test
 public void demo3() {
	 User u=new User();
	 u.setId(12);
	 u.setUsername("adamin");
	 String s=new String();
	 System.out.println(u);
 }
 @Test
 public void demo4() {
 	String ynd="5000000000";
 	String hl="0.0002825";
 	 BigDecimal bigDecimal=new BigDecimal(ynd);
 	 bigDecimal=bigDecimal.divide(new BigDecimal(hl),2,RoundingMode.HALF_UP);
	 System.out.println(bigDecimal.setScale(2));
 }

}

