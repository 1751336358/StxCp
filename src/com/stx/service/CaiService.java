package com.stx.service;

import java.util.List;

import com.stx.pojo.Cai;
import com.stx.pojo.User;

public interface CaiService {
	//�������Ĳ��ײ��뵽���ݿ���
	public void addCai(Cai cai);
	
	//��ѯ�ҵĲ��ף�����userd
	public List<Cai> getMyCai(int userid);
	
	//ɾ���ˣ�����cai.id
	public void delCai(int id);
	
	//��ѯ���ף�����cai.id
	public Cai selCaiById(int id);
	
	//�޸Ĳ���
	public void EditCai(Cai cai);
	
	//�޸��Ķ���
	public void addReadNum(int id);
}