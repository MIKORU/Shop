package com.alice.shop.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class FileController extends HttpServlet {

	@RequestMapping(value="upload", method=RequestMethod.POST)
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8");
		String realDir = request.getSession().getServletContext().getRealPath("");
		String contextpath = request.getContextPath();
		String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ contextpath + "/";

		try {
		String filePath = "uploadfiles";
		String realPath = realDir+"\\"+filePath;
		//判断路径是否存在，不存在则创建
		File dir = new File(realPath);
		if(!dir.isDirectory())
		    dir.mkdir();

		if(ServletFileUpload.isMultipartContent(request)){

		    DiskFileItemFactory dff = new DiskFileItemFactory();
		    dff.setRepository(dir);
		    dff.setSizeThreshold(10240000);
		    ServletFileUpload sfu = new ServletFileUpload(dff);
		    FileItemIterator fii = null;
		    fii = sfu.getItemIterator(request);
		    String title = "";   //图片标题
		    String url = "";    //图片地址
		    String fileName = "";
			String state="SUCCESS";
			String realFileName="";
		    while(fii.hasNext()){
		        FileItemStream fis = fii.next();
		        try{
		            if(!fis.isFormField() && fis.getName().length()>0){
		                fileName = fis.getName();
						Pattern reg=Pattern.compile("[.]jpg|png|jpeg|gif$");
						Matcher matcher=reg.matcher(fileName);
						if(!matcher.find()) {
							state = "文件类型不允许！";
							break;
						}
						realFileName = new Date().getTime()+fileName.substring(fileName.lastIndexOf("."),fileName.length());
		                url = realPath+"\\"+realFileName;

		                BufferedInputStream in = new BufferedInputStream(fis.openStream());//获得文件输入流
		                FileOutputStream a = new FileOutputStream(new File(url));
		                BufferedOutputStream output = new BufferedOutputStream(a);
		                Streams.copy(in, output, true);//开始把文件写到你指定的上传文件夹
		            }else{
		                String fname = fis.getFieldName();

		                if(fname.indexOf("pictitle")!=-1){
		                    BufferedInputStream in = new BufferedInputStream(fis.openStream());
		                    byte c [] = new byte[10];
		                    int n = 0;
		                    while((n=in.read(c))!=-1){
		                        title = new String(c,0,n);
		                        break;
		                    }
		                }
		            }

		        }catch(Exception e){
		            e.printStackTrace();
		        }
		    }
		    response.setStatus(200);
		    String retxt = basePath+filePath+"/"+realFileName;
		    response.getWriter().print(retxt);
		}
		}catch(Exception s) {
			s.printStackTrace();
		}
		
	}
}
