package com.stx.dao;

import java.util.List;

import com.stx.pojo.Cai;

public interface CaiDao {
	//�������Ĳ��ײ��뵽���ݿ���
	public void addCai(Cai cai);
	
	//��ѯ�ҵĲ��ף�����userd
	public List<Cai> getMyCai(int userid);
	
	//ɾ���ˣ�����cai.id
	public void delCai(int id);
	
	//��ѯ���ף�����cai.id
	public Cai selCaiById(int id);
	
	//�޸Ĳ��ף�����
	public void EditCai(Cai cai);
	
	//�޸��Ķ���
	public void addReadNum(int id);
}
