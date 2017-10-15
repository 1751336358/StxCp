package com.stx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.stx.dao.impl.IndexDaoImpl;
import com.stx.pojo.Cai;
import com.stx.service.IndexService;


//���һ��Service
@Service("indexService")
public class IndexServiceImpl implements IndexService {

	
	
	//��ѯ���Ų���
	public List<Cai> hotCai(){
		return indexDao.hotCai();
	}
	
	//����dept.id��ѯÿ����ϵ���Ƽ�����
	public List<Cai> getTuijianCai(int id){
		return indexDao.getTuijianCai(id);
	}
	
	//���ʱ���վ��ʱ��
	public void insertTime(String time){
		indexDao.insertTime(time);
	}
	
	//��վ�ķ�����
	public int getFangwenNum(){
		return indexDao.getFangwenNum();
	}
	
	//���ݹؼ���key��ѯ��صĲ���
	public List<Cai> selCaiByKey(String key){
		return indexDao.selCaiByKey(key);
	}
	@Resource(name="indexDao")
	private IndexDaoImpl indexDao;//ע��

	public IndexDaoImpl getIndexDao() {
		return indexDao;
	}

	public void setIndexDao(IndexDaoImpl indexDao) {
		this.indexDao = indexDao;
	}



	

	
	
}
