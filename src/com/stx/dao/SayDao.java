package com.stx.dao;

import java.util.List;

import com.stx.pojo.Cai;
import com.stx.pojo.Say;

public interface SayDao {
	//�����۲������ݿ�
	public void addSay(Say say);
	
	//��ѯ���������ۣ�����caiid
	public List<Say> selSayByCaiid(int caiid);
	
	//������µ�����
	public Say selNewSay();
	
	//ɾ�����ۣ�����sayid
	public void delSayBysayid(int sayid);

}
