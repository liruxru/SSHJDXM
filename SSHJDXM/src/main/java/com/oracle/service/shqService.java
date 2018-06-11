package com.oracle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.AreaDAO;
import com.oracle.dao.CityDAO;
import com.oracle.dao.ProvinceDAO;
import com.oracle.entity.Area;
import com.oracle.entity.City;
import com.oracle.entity.Province;

@Service
public class shqService {

	@Autowired
	private ProvinceDAO provinceDAO;
	@Autowired
	private CityDAO cityDAO;
	@Autowired
	private AreaDAO areaDAO;
	
	
	public AreaDAO getAreaDAO() {
		return areaDAO;
	}
	public void setAreaDAO(AreaDAO areaDAO) {
		this.areaDAO = areaDAO;
	}
	public CityDAO getCityDAO() {
		return cityDAO;
	}
	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}
	public ProvinceDAO getProvinceDAO() {
		return provinceDAO;
	}
	public void setProvinceDAO(ProvinceDAO provinceDAO) {
		this.provinceDAO = provinceDAO;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Province> finAllProvince(){
		List<Province> listProvinces=provinceDAO.findAll();
		return listProvinces;
	}
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<City> getShiBySheng(String shengCode) {
		List<City> lsCities=cityDAO.findByProvincecode(shengCode);
		return lsCities;
	}
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Area> getQuByShi(String shiCode) {
		List<Area> lsaAreas=areaDAO.findByCitycode(shiCode);
		return lsaAreas;
	}

	
}
