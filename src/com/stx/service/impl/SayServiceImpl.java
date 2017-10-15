package com.stx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.stx.dao.SayDao;
import com.stx.pojo.Say;
import com.stx.service.SayService;
import com.stx.util.TimeFormat;


//���һ��Service
@Service("sayService")
public class SayServiceImpl implements SayService {

	
	
	//�����۲������ݿ�
	public void addSay(Say say){
		String time = TimeFormat.getLocalTime();
		say.setTime(time);	//��������ʱ��
		sayDao.addSay(say);
	}
	
	//��ѯ���������ۣ�����caiid
	public List<Say> selSayByCaiid(int caiid){
		
		return sayDao.selSayByCaiid(caiid);
	}
	
	//������µ�����
	public Say selNewSay(){
		return sayDao.selNewSay();
	}
	
	//ɾ�����ۣ�����sayid
	public void delSayBysayid(int sayid){
		sayDao.delSayBysayid(sayid);
	}
	@Resource(name="sayDao")
	private SayDao sayDao;//ע��
	public SayDao getSayDao() {
		return sayDao;
	}
	public void setSayDao(SayDao sayDao) {
		this.sayDao = sayDao;
	}

	

	

	
	
}
