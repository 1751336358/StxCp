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
			//1���½�����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024*1024);	//������Ӳ��д���ݵĻ������Ĵ�С���˴���1M
			factory.setRepository(new File(this.getServletContext().getRealPath("/tmp")));	//�����ļ���ʱ����Ŀ¼
			//2���ļ��ϴ�������
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(1024*1024*1000);	//���õ����ϴ��ļ��Ĵ�С����������Χ������FileUploadBase.FileSizeLimitExceededException�쳣
		//	upload.setSizeMax(0);	//�����ϴ��ļ����������ֵ
			upload.setProgressListener(new ProgressListenerImpl());	//���ü�����������������
			if(!upload.isMultipartContent(request)){	//�ж�ʱ�����multipart/form-data�ϴ���
				//���մ�ͳ��ʽ��ñ����ύ����
				//request.getParameter("username");
				return;
			}
			upload.setHeaderEncoding("utf-8");	//�����ϴ����ļ������� ��������
			//3�����ý���������request���õ������ϴ��ļ����浽list����
			List<FileItem> list = upload.parseRequest(request);
			//4������list���õ��ϴ���ÿ������(ÿ���ļ����浽list�� )
			
			for(FileItem item : list){
				if(item.isFormField()){	//����ύ���Ǳ��ֶ�
					String inputName = item.getFieldName();
					String inputValue = item.getString();
					inputValue = new String(inputValue.getBytes("iso-8859-1"),"utf-8");//����request�ı�������Ч��
					System.out.println(">>>>>>>>>>>>>>>>>"+inputName+"="+inputValue);	//
					map.put(inputName, inputValue);	//�����ı��ֶα��浽map����
					
				}else{	//����ύ�����ϴ����ļ�
					String fileName = item.getName();	//�õ��ļ���
				
					System.out.println(">>>>>>>>>>>>>>>>>nameReal��"+fileName);
					InputStream in = item.getInputStream();
					fileName = generateFileName(fileName);
					System.out.println("fileName"+fileName);
					map.put("imgpath", fileName);
					//�ж��û��Ƿ�û���ϴ��ļ���������Ϊ��
					if(fileName == null || fileName.trim().equals("")){
						continue;	//������һ��ѭ���������һ�������� 
					}
					//�˴����������û��ϴ��ļ�������
					String ext = fileName.substring(fileName.lastIndexOf("."));	//��ȡ�ϴ��ļ��ĺ�׺��
					System.out.println("�ļ��ϴ������ͣ�"+ext);
					/*if(ext == ".gif"){
						//��ϵͳ��֧�ָ����͵��ļ��ϴ�
						//request.getRequestDispatcher("").forward(request, response);
						return;
					}*/
					int len = 0;
					byte []buffer = new byte[1024];
				//	fileName = this.generateFileName(fileName);	//���ϴ����ļ�����Ψһ���ļ���
					
				//	String savepath = this.getServletContext().getRealPath("/WEB-INF/upload");
					String savepath = "F:\\StxCp";	//�ļ��ı���·��
				//	savepath = this.generateSavePath(savepath, fileName);//���ϴ��ļ���ɢ�����Ŀ¼
					map.put("imgpath", fileName);	//���ļ�·�����浽map����
					FileOutputStream out = new FileOutputStream(savepath+File.separator+fileName);
					while((len=in.read(buffer))>0){
						out.write(buffer, 0, len);
					}
					out.close();
					in.close();
					item.delete();	//�ϴ����ļ����Զ���������ʱĿ¼�У����ļ��ϴ����ʱ���Զ�ɾ����ʱ�ļ����ϴ����ļ�
				}
			}
			map.put("time", new Date().toLocaleString());
			System.out.println("-----------------------");
			System.out.println("map���ϵĴ�С��"+map.size());
			System.out.println("���ɵ�sql�����");
			String sql = MapToSql.mapToSql(map);
			System.out.println(sql);
			System.out.println("-----------------------");
			
			//����JdbcUtils�����浽���ݿ�
			Connection conn = JdbcUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = null;
			ps.executeUpdate();
			JdbcUtils.release(conn, ps, rs);
			
		}catch (FileUploadBase.FileSizeLimitExceededException e) {	//�ϴ��ļ���С����ָ����С
			System.out.println("�ϴ����ļ�̫��");
		
		} 
		catch (FileUploadException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.println("�ϴ��ɹ�");
		response.sendRedirect("gotoIndex.jsp");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
		
	}

	//���ϴ��ļ�����Ψһ���ļ���
	public String generateFileName(String fileName){
		//UUID_fileName
		return UUID.randomUUID().toString()+"_"+fileName;
	}
	
	
	
	
}

//������,�����ļ��ϴ�����
class ProgressListenerImpl implements ProgressListener{
	
	/*
	 * @pBytesRead:�Ѿ��ϴ��˶�������
	 * @pContentLength:��������
	 * @pItems:������ǵڼ���Items
	 * */
	public static double hasUpload = 0;
	public static double total = 0;
	public static int item = 0;
	public static double present = 0.0;
	private long megaBytes = -1;
//	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		// 1M���һ�Σ��ĵ�Դ�룩
		
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
	//	System.out.println("��ǰ�ѽ���"+pBytesRead+",������"+pContentLength+",�����ϴ���"+pItems+"���ļ�");
		
	}
}

