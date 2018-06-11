package com.oracle.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.oracle.comm.SSH;

public class SSHUtils {

	static SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	
	//获取卖品编号头
	public static String getGoodsSn(){
		return SSH.GOODS_SN_PERFIX+sdf.format(new Date());
	}
	//获取非卖品编号头
	public static String getDittySn(){
		return SSH.DITTY_SN_PERFIX+sdf.format(new Date());
	}
	
	//获取采购编号
	public static String getCaigoSn(){
		return SSH.CAIGO_SN_PERFIX+UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	//获取订单编号
	public static String getOrderSn(){
		return SSH.ORDER_SN_PERFIX+UUID.randomUUID().toString().replaceAll("-", "");
	}
	//获取财务详情编号
	public static String getAssetSn(){
		return "A"+UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 获取仓库编号
	 * @return 订单Sn
	 */
	public static String getEntrepotSn(){
		return SSH.ENTREPOT_SN_PERFIX+UUID.randomUUID().toString().replaceAll("-", "");
	}
}
