package com.wu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wu.domain.Department;
import com.wu.domain.User;
import com.wu.domain.UserGroup;
import com.wu.service.inter.CRUDDepService;
import com.wu.service.inter.CRUDService;
import com.wu.tools.SendMail;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CRUDService crudService;
    @Autowired
    private CRUDDepService cruddepService;
    @Autowired
    private User user;
    private Department dep;
    //查询所有用户
    @RequestMapping(value = "/findalluser")
    public void findAllUser(HttpServletResponse response){
    	response.setContentType("text/html;charset=utf-8");
        List<User> userList=crudService.FindAllUser();
        String json=JSON.toJSONString(userList);
    }
    @RequestMapping(value = "/findalluserfenye")
    public void findAllUserFenye(int limit,int page,HttpServletResponse response) throws IOException{
    	response.setContentType("text/html;charset=utf-8");
    	System.out.println(limit+"----"+page);
    	int count=crudService.FindAllUserCount();
        List<User> userList=crudService.FindAllUserLimit(page,limit);
        UserGroup ug=new UserGroup();
        ug.setCode(0);
        ug.setMsg(" ");
        ug.setCount(count);
        ug.setData(userList);
   	    String sst=JSONArray.toJSONString(ug);
        System.out.println(sst);
        response.getWriter().write(sst);
    }
    @RequestMapping(value = "/finduserbyname")
    public void finduserbyname(String username,HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=utf-8");
    	User userByName=crudService.FindUserByName(username);
        if(userByName!=null && !"".equals(userByName.toString())) {
        	String userjson=JSONArray.toJSONString(userByName);
        	response.getWriter().write(userjson);
        }else {
        	response.getWriter().write("");
        }
    }
  //查询所有部门
    @RequestMapping(value = "/findalldep")
    public void findalldep(HttpServletResponse response) throws IOException{
    	response.setContentType("text/html;charset=utf-8");
        List<Department> deplist=cruddepService.FindAllDep();
        if(deplist!=null && !deplist.isEmpty()) {
        	String depjson=JSONArray.toJSONString(deplist);
        	response.getWriter().write(depjson);
        }else {
        	response.getWriter().write("");
        }
    }
    //ID查询用户
    @RequestMapping(value = "/finduserbyid")
    public void finduserbyid(int id,HttpServletResponse response) throws IOException{
    	response.setContentType("text/html;charset=utf-8");
        User user=crudService.FindUserById(id);
        if(user!=null && !("".equals(user))) {
        	user.setPassword("");
        	String hp=user.getHead_portrait();
        	if(hp!=null && hp.length()!=0) {
        		int hpindex=hp.lastIndexOf("/");
            	hp=hp.substring(hpindex);
            	user.setHead_portrait(hp);
        	}
        	String userjson=JSONArray.toJSONString(user);
        	response.getWriter().write(userjson);
        }else {
        	response.getWriter().write("");
        }
    }
    @RequestMapping(value = "/updateuserpsd")
    public void updateuserpsd(User user,HttpServletResponse response) throws IOException{
        	int result=crudService.UpdateUserPsd(user);
        	if(result>0) {
        		response.getWriter().write("success");
        	}else {
        		response.getWriter().write("error");
        	} 	
    }
    //用户详情更新
    @RequestMapping(value = "/updateuserinfo")
    public void updateuserinfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	request.setCharacterEncoding("UTF-8");	
    	String path="D:/wu/src/main/webapp/img/head-portrait/";
        	File file=new File(path);
        	if(!file.exists()) {
        		file.mkdirs();
        	}
				DiskFileItemFactory diskFileItemFactory=new DiskFileItemFactory();
				ServletFileUpload servletFileUpload=new ServletFileUpload(diskFileItemFactory);
				servletFileUpload.setHeaderEncoding("UTF-8");
				List<FileItem> items=servletFileUpload.parseRequest(request);
				for(FileItem item:items) {
					if(item.isFormField()) {
						switch (item.getFieldName()) {
						case "id":
							user.setId(Integer.parseInt(item.getString("UTF-8")));
							break;
						case "address":
							user.setAddress(item.getString("UTF-8"));
							break;
						case "sex":
							user.setSex(item.getString("UTF-8"));
							break;
						case "department":
							user.setDepartment(item.getString("UTF-8"));
							break;
						case "birthday":
							user.setBirthday(item.getString("UTF-8"));
							break;
						case "phone":
							user.setPhone(item.getString("UTF-8"));
							break;
						default:
							break;
						}
						}else {
						String filename=item.getName();
	        			String uuid=UUID.randomUUID().toString().replace("-", "");
	        			filename=uuid+"-"+filename;
	        			user.setHead_portrait(path+filename);
	        			item.write(new File(file, filename));
	        			item.delete();
					}
				}
			System.out.println(user);
        	int result=crudService.UpdateUserInfo(user);
        	if(result>0) {
        		response.getWriter().write("success");
        	}else {
        		response.getWriter().write("error");
        	}
	        			
    }
    @RequestMapping(value = "/finduserbyemail")
    public void finduserbyemail(String email,HttpServletResponse response) throws IOException{
        User userByEmail=crudService.FindUserByEmail(email);
        if(userByEmail!=null && !"".equals(userByEmail.toString())) {
        	response.getWriter().write("success");
        }else {
        	response.getWriter().write("error");
        }
    }
    @RequestMapping(value = "/loginuser")
    public void  loginuser(User user,HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=utf-8");
    	User loginuser=crudService.LoginUser(user);
        if(loginuser!=null && !("".equals(loginuser.toString()))) {
        	loginuser.setPassword("");
        	String loginuserjson=JSONArray.toJSONString(loginuser);
        	response.getWriter().write(loginuserjson);
        }else {
        	response.getWriter().write("");
        }
    }
    @RequestMapping(value = "/loginemail")
    public void  loginemail(String email,String captcha ,HttpServletRequest request,HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	if(!"".equals(email) || !"".equals(captcha)) {
    		Object captchas= request.getSession().getAttribute("captcha");
    		if(captchas!=null) {
    			System.out.println(captchas);
                StringBuilder sb=new StringBuilder();
                sb.append(email);
                sb.append(captcha);
                if(captchas.equals(sb.toString())) {
                	user=crudService.LoginEmail(email);
                	if(user.getUsername()!=null) {
                		response.getWriter().write(user.getUsername());
                	}else {
                		response.getWriter().write("");
                	}  	
                }else {
                	response.getWriter().write("");
                }
    		}else {
    			response.getWriter().write("");
    		}
            
    	}
    }
    @RequestMapping(value = "/emailcaptcha")
    public void  emailcaptcha(String email,HttpServletRequest request,HttpServletResponse response) throws IOException{
        if(email!=null && email.length()!=0) {
        	StringBuilder sb=new StringBuilder();
            Random random=new Random();
            for(int i=0;i<6;i++) {
            	int ran=random.nextInt(10);
            	sb.append(ran);
            }
            StringBuilder sbcap=new StringBuilder();
            sbcap.append(email);
            sbcap.append(sb.toString());
            request.getSession().setAttribute("captcha", sbcap.toString());
            request.getSession().setMaxInactiveInterval(60);
            try {
    			SendMail.sendmail(sb.toString(), email);
    		} catch (GeneralSecurityException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
    	
    }
    @RequestMapping(value = "/insertuser")
    public void insertuser(User user,HttpServletResponse response) throws IOException{
    	   System.out.println(user);
    	   int result=crudService.InsertUser(user);
    	   if(result>0) {
    		   int inforesult=crudService.InsertUserInfo(user.getId());
    		   if(inforesult>0) {
    			   response.getWriter().write("success");
    		   }else {
    			   response.getWriter().write("error");
    		   }  
    	   }else {
    		   response.getWriter().write("error");
    	   }   
    }
    @RequestMapping(value = "/insertuserinfo")
    public void insertuseinfor(User user,HttpServletResponse response) throws IOException{

    }
    
}
