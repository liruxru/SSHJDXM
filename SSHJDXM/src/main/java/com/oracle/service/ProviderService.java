package com.oracle.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.ProviderDAO;
import com.oracle.entity.Provider;

@Service("providerService")
public class ProviderService {
	@Resource(name="ProviderDAO")
	private ProviderDAO providerDAO;

	public ProviderDAO getProviderDAO() {
		return providerDAO;
	}
	public void setProviderDAO(ProviderDAO providerDAO) {
		this.providerDAO = providerDAO;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Provider> list(){
		List<Provider> list =providerDAO.findAll();
		return list;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(Provider provider) {
		if(provider.getId()!=null){
			//provider.setCreatedate(new Timestamp(new Date().getTime()));
			provider.setModitfydate(new Timestamp(new Date().getTime()));
		}
		if(provider.getCreatedate()==null){
			provider.setCreatedate(new Timestamp(new Date().getTime()));
		}
//		provider.setModitfydate(new Timestamp(new Date().getTime()));
		providerDAO.attachDirty(provider);
		return true;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Provider findById(Integer id) {
		Provider provider = providerDAO.findById(id);
		return provider;
	}
	public boolean delete(Provider provider) {
		providerDAO.delete(provider);
		return true;
	}
	
	
	

}
