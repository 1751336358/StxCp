package com.stx.dao;

import java.util.List;

import com.stx.pojo.Cai;

public interface IndexDao {
	
	//��ѯ���Ų���
	public List<Cai> hotCai();
	
	//����dept.id��ѯÿ����ϵ���Ƽ�����
	public List<Cai> getTuijianCai(int id);
	
	//���ʱ���վ��ʱ��
	public void insertTime(String time);
	
	//��վ�ķ�����
	public int getFangwenNum();
	
	//���ݹؼ���key��ѯ��صĲ���
	public List<Cai> selCaiByKey(String key);
}
