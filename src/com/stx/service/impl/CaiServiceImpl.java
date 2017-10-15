package com.stx.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.stx.dao.impl.CaiDaoImpl;
import com.stx.pojo.Cai;
import com.stx.service.CaiService;
import com.stx.util.HtmlFilter;
import com.stx.util.TimeFormat;


//���һ��Service
@Service("caiService")
public class CaiServiceImpl implements CaiService {

	//�������Ĳ��ײ��뵽���ݿ���
	public void addCai(Cai cai){
		
		cai.setTime(TimeFormat.getLocalTime());	//������Ӳ��׵�ʱ��
		cai.setName(HtmlFilter.filter(cai.getName()));
		cai.setZhuliao(HtmlFilter.filter(cai.getZhuliao()));
		cai.setFuliao(HtmlFilter.filter(cai.getFuliao()));
		cai.setDetail(HtmlFilter.filter(cai.getDetail()));
		caiDao.addCai(cai);
	}
	
	//��ѯ�ҵĲ��ף�����userd
	public List<Cai> getMyCai(int userid){
		return caiDao.getMyCai(userid);
	}
	
	//ɾ���ˣ�����cai.id
	public void delCai(int id){
		//�鵽������׵���Ϣ��ɾ��ͼƬ
		Cai cai = caiDao.selCaiById(id);
		String path = "F:"+File.separator+"StxCp"+File.separator+cai.getImgpath();
		File file = new File(path);
		file.delete();
		//ɾ������
		caiDao.delCai(id);
	}
	
	//��ѯ���ף�����cai.id
	public Cai selCaiById(int id){
		return caiDao.selCaiById(id);
	}
	
	//�޸Ĳ���
	public void EditCai(Cai cai){
		cai.setTime(TimeFormat.getLocalTime());	//������Ӳ��׵�ʱ��
		cai.setName(HtmlFilter.filter(cai.getName()));
		cai.setZhuliao(HtmlFilter.filter(cai.getZhuliao()));
		cai.setFuliao(HtmlFilter.filter(cai.getFuliao()));
		cai.setDetail(HtmlFilter.filter(cai.getDetail()));
		
		caiDao.EditCai(cai);
	}
	
	//�޸��Ķ���
	public void addReadNum(int id){
		caiDao.addReadNum(id);
	}
	@Resource(name="caiDao")
	private CaiDaoImpl caiDao;//ע��

	public CaiDaoImpl getCaiDao() {
		return caiDao;
	}

	public void setCaiDao(CaiDaoImpl caiDao) {
		this.caiDao = caiDao;
	}

	

	
	
}
