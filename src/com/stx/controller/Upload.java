package com.stx.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.stx.util.JdbcUtils;
import com.stx.util.MapToSql;


public class Upload extends HttpServlet {

	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		/*-------------------*/
		try {
			//1、新建工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024*1024);	//设置向硬盘写数据的缓冲区的大小，此处是1M
			factory.setRepository(new File(this.getServletContext().getRealPath("/tmp")));	//设置文件临时保存目录
			//2、文件上传处理器
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(1024*1024*1000);	//设置单个上传文件的大小，若超出范围，会抛FileUploadBase.FileSizeLimitExceededException异常
		//	upload.setSizeMax(0);	//设置上传文件总量的最大值
			upload.setProgressListener(new ProgressListenerImpl());	//设置监听器，制作进度条
			if(!upload.isMultipartContent(request)){	//判断时会否是multipart/form-data上传表单
				//按照传统方式获得表单的提交数据
				//request.getParameter("username");
				return;
			}
			upload.setHeaderEncoding("utf-8");	//设置上传的文件的中文 乱码问题
			//3、调用解析器解析request，得到所有上传文件保存到list集合
			List<FileItem> list = upload.parseRequest(request);
			//4、迭代list，得到上传的每项数据(每个文件保存到list中 )
			
			for(FileItem item : list){
				if(item.isFormField()){	//如果提交的是表单字段
					String inputName = item.getFieldName();
					String inputValue = item.getString();
					inputValue = new String(inputValue.getBytes("iso-8859-1"),"utf-8");//设置request的编码是无效的
					System.out.println(">>>>>>>>>>>>>>>>>"+inputName+"="+inputValue);	//
					map.put(inputName, inputValue);	//将传的表单字段保存到map集合
					
				}else{	//如果提交的是上传的文件
					String fileName = item.getName();	//得到文件名
				
					System.out.println(">>>>>>>>>>>>>>>>>nameReal："+fileName);
					InputStream in = item.getInputStream();
					fileName = generateFileName(fileName);
					System.out.println("fileName"+fileName);
					map.put("imgpath", fileName);
					//判断用户是否没有上传文件，表单数据为空
					if(fileName == null || fileName.trim().equals("")){
						continue;	//继续下一轮循环，获得下一个输入项 
					}
					//此处可以拦截用户上传文件的类型
					String ext = fileName.substring(fileName.lastIndexOf("."));	//截取上传文件的后缀名
					System.out.println("文件上传的类型："+ext);
					/*if(ext == ".gif"){
						//本系统不支持该类型的文件上传
						//request.getRequestDispatcher("").forward(request, response);
						return;
					}*/
					int len = 0;
					byte []buffer = new byte[1024];
				//	fileName = this.generateFileName(fileName);	//给上传的文件赋予唯一的文件名
					
				//	String savepath = this.getServletContext().getRealPath("/WEB-INF/upload");
					String savepath = "F:\\StxCp";	//文件的保存路径
				//	savepath = this.generateSavePath(savepath, fileName);//将上传文件打散到多个目录
					map.put("imgpath", fileName);	//将文件路径保存到map集合
					FileOutputStream out = new FileOutputStream(savepath+File.separator+fileName);
					while((len=in.read(buffer))>0){
						out.write(buffer, 0, len);
					}
					out.close();
					in.close();
					item.delete();	//上传的文件会自动保存在临时目录中，当文件上传完成时会自动删除临时文件中上传的文件
				}
			}
			map.put("time", new Date().toLocaleString());
			System.out.println("-----------------------");
			System.out.println("map集合的大小是"+map.size());
			System.out.println("生成的sql语句是");
			String sql = MapToSql.mapToSql(map);
			System.out.println(sql);
			System.out.println("-----------------------");
			
			//利用JdbcUtils将保存到数据库
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = null;
			ps.executeUpdate();
			JdbcUtils.release(conn, ps, rs);
			
		}catch (FileUploadBase.FileSizeLimitExceededException e) {	//上传文件大小超出指定大小
			System.out.println("上传的文件太大");
		
		} 
		catch (FileUploadException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.println("上传成功");
		response.sendRedirect("gotoIndex.jsp");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
		
	}

	//给上传文件赋予唯一的文件名
	public String generateFileName(String fileName){
		//UUID_fileName
		return UUID.randomUUID().toString()+"_"+fileName;
	}
	
	
	
	
}

//接听器,监听文件上传进度
class ProgressListenerImpl implements ProgressListener{
	
	/*
	 * @pBytesRead:已经上传了多少数据
	 * @pContentLength:总数据量
	 * @pItems:处理的是第几个Items
	 * */
	public static double hasUpload = 0;
	public static double total = 0;
	public static int item = 0;
	public static double present = 0.0;
	private long megaBytes = -1;
//	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		// 1M输出一次（文档源码）
		
		long mBytes = pBytesRead / 1000000;
	       if (megaBytes == mBytes) {
	           return;
	       }
	       megaBytes = mBytes;
	       System.out.println("We are currently reading item " + pItems);
	       if (pContentLength == -1) {
	           System.out.println("So far, " + pBytesRead + " bytes have been read.");
	       } else {
	           System.out.println("So far, " + pBytesRead + " of " + pContentLength
	                              + " bytes have been read.");
	       }
	       hasUpload = pBytesRead / (1024*1024);
	       total = pContentLength / (1024*1024);
	       item = pItems;
	//	System.out.println("当前已解析"+pBytesRead+",总量是"+pContentLength+",正在上传第"+pItems+"个文件");
		
	}
}

