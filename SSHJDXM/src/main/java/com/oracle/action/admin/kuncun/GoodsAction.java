package com.oracle.action.admin.kuncun;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.oracle.bean.Page;
import com.oracle.comm.SH;
import com.oracle.dao.GoodsDAO;
import com.oracle.entity.Goods;
import com.oracle.entity.Goodsimg;
import com.oracle.service.EntrepotService;
import com.oracle.service.GoodsService;
import com.oracle.service.ProviderService;
import com.oracle.service.TypeService;
import com.oracle.utils.FileUtil;

@Namespace("/admin/kucun")
@ParentPackage("json-default")
@Controller
@Scope("proptype")
public class GoodsAction{

	@Resource(name="goodsService")
	private GoodsService gs;
	@Resource(name="typeService")
	private TypeService ts;
	@Resource(name="entrepotService")
	private EntrepotService es;
	@Resource(name="providerService")
	private ProviderService ps;
	private Goods goods;
	private Integer currentPage;
	private File  imgcover;
	private String imgcoverFileName;
	private String imgcoverContentType;
	private File[] imgs;
	private String[] imgsFileName;
	private String[] imgsContentType;
	private Timestamp[] createdate;
	private Integer ch[];
	public GoodsService getGs() {
		return gs;
	}
	public void setGs(GoodsService gs) {
		this.gs = gs;
	}
	public TypeService getTs() {
		return ts;
	}
	public void setTs(TypeService ts) {
		this.ts = ts;
	}	
	public EntrepotService getEs() {
		return es;
	}
	public void setEs(EntrepotService es) {
		this.es = es;
	}
	public ProviderService getPs() {
		return ps;
	}
	public void setPs(ProviderService ps) {
		this.ps = ps;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public File getImgcover() {
		return imgcover;
	}
	public void setImgcover(File imgcover) {
		this.imgcover = imgcover;
	}
	public String getImgcoverFileName() {
		return imgcoverFileName;
	}
	public void setImgcoverFileName(String imgcoverFileName) {
		this.imgcoverFileName = imgcoverFileName;
	}
	public String getImgcoverContentType() {
		return imgcoverContentType;
	}
	public void setImgcoverContentType(String imgcoverContentType) {
		this.imgcoverContentType = imgcoverContentType;
	}
	public File[] getImgs() {
		return imgs;
	}
	public void setImgs(File[] imgs) {
		this.imgs = imgs;
	}
	public String[] getImgsFileName() {
		return imgsFileName;
	}
	public void setImgsFileName(String[] imgsFileName) {
		this.imgsFileName = imgsFileName;
	}
	public String[] getImgsContentType() {
		return imgsContentType;
	}
	public void setImgsContentType(String[] imgsContentType) {
		this.imgsContentType = imgsContentType;
	}	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}	
	public Timestamp[] getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp[] createdate) {
		this.createdate = createdate;
	}	
	public Integer[] getCh() {
		return ch;
	}
	public void setCh(Integer[] ch) {
		this.ch = ch;
	}
	@Action(value="goodslist",results={
			@Result(name="success",location="/page/admin/kucun/goods/list.jsp")
	})
	public String list(){
		Integer sessionCurrent=(Integer) ActionContext.getContext().getSession().get("currentPage");
		if(currentPage==null){
			currentPage=1;
		}
		if(sessionCurrent!=null){
			currentPage=sessionCurrent;
			ActionContext.getContext().getSession().remove("currentPage");
		}
		Integer pageSize=9;
		Page page= gs.list(currentPage,pageSize);
		ActionContext.getContext().put(SH.SESSION_PAGE_KEY, page);
		ActionContext.getContext().put(SH.SESSION_GOODSLIST_KEY, page.getList());
		return "success";
		
	}
	
	@Action(value="goodsedit",results={
			@Result(name="success",location="/page/admin/kucun/goods/add.jsp"),
			@Result(name="suc",location="/page/admin/kucun/goods/edit.jsp")
	})
	public String edit(){
		if(goods!=null){
			Goods g = gs.getGoodsById(goods.getId());
			System.out.println(g.getGoodsimgs().size());
			ActionContext.getContext().getSession().put(SH.SESSION_CURRENTPAGE_KEY, currentPage);
			ActionContext.getContext().put(SH.SESSION_GOOD_KEY, g);
			ActionContext.getContext().put(SH.SESSION_EDITENTREPOTLIST_KEY, es.list());
			ActionContext.getContext().put(SH.SESSION_PROVIDERLIST_KEY, ps.list());
			ActionContext.getContext().put(SH.SESSION_TYPELIST_KEY, ts.list());
			return "suc";
		}
		ActionContext.getContext().put(SH.SESSION_EDITENTREPOTLIST_KEY, es.list());//仓库
		ActionContext.getContext().put(SH.SESSION_PROVIDERLIST_KEY, ps.list());//供应商
		ActionContext.getContext().put(SH.SESSION_TYPELIST_KEY, ts.list());//类型
		return "success";
		
	}
	
	@Action(value="goodsadd",results={
			@Result(name="success",type="redirectAction",location="goodslist"),
			@Result(name="error",type="chain",location="goodsedit")
	})
	public String add(){
		String path = ServletActionContext.getServletContext().getRealPath("/")+"upload/goods/";
		if(goods.getId()==null){
			try {
				for (int i = 0;imgsContentType!=null&&i<imgsContentType.length;i++) {
					if(imgsContentType[i]==null||imgcoverContentType==null||!"image/jpeg".equals(imgcoverContentType)||!"image/jpeg".equals(imgsContentType[i])){
						System.out.println("附图"+imgsContentType[i]);
						ActionContext.getContext().put(SH.SESSION_FILEERROR_KEY, "上传图片类型错误");
						//break o;
						return "error";
					}	
				}		    
				String suffix = FileUtil.getSuffix(imgcoverFileName);
				String newImgcover = FileUtil.getNewFileName()+suffix;
				FileUtils.copyFile(imgcover, new File(path+newImgcover));
				List<String> ls = new ArrayList<String>();
				for (int i = 0;imgs!=null&&i<imgs.length;i++) {
					String suf = FileUtil.getSuffix(imgsFileName[i]);
					String newImg= FileUtil.getNewFileName()+suf;
					FileUtils.copyFile(imgs[i], new File(path+newImg));
					ls.add(newImg);
				}
				if(gs.save(goods,newImgcover,ls)){
					return "success";
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "error";
			}
		}else{
			System.out.println("附图的创建时间"+createdate);
				try {
					String newImgcover = null;
					if(imgcover!=null){
					String suffix = FileUtil.getSuffix(imgcoverFileName);
					newImgcover = FileUtil.getNewFileName()+suffix;
					FileUtils.copyFile(imgcover, new File(path+newImgcover));
					goods.setImgcover(newImgcover);
					}else{
						Goods g = gs.getGoodsById(goods.getId());
						goods.setImgcover(g.getImgcover());
					}
					List<Goodsimg> ls = new ArrayList<Goodsimg>();
					Goodsimg fuImg = null;
					if(imgs!=null){
						for (int i = 0;i<imgs.length;i++) {
							String suf = FileUtil.getSuffix(imgsFileName[i]);
							String newImg= FileUtil.getNewFileName()+suf;
							FileUtils.copyFile(imgs[i], new File(path+newImg));
							ls.add(new Goodsimg(goods, newImg, createdate==null?new Timestamp(new Date().getTime()):createdate[i], new Timestamp(new Date().getTime())));
						}
					}
					if(gs.edit(goods,ls)){						
						return "success";
					}					
				} catch (IOException e) {
					e.printStackTrace();
					return "error";
				}
			
		}
		return "error";
//		System.out.println("ss"+imgcover);
//		System.out.println("vv"+imgcoverFileName);
//		System.out.println("zz"+imgcoverContentType);
//		return imgcoverContentType;
		
	}
	
	
	@Action(value="goodsdelete",results={
			@Result(name="suc",type="redirectAction",location="goodslist"),
			@Result(name="success",type="json",params={"root","0"}),
			@Result(name="error",type="json",params={"root","1"})
	})
	public String delete(){
		if(ch!=null&&ch.length>1){
			if(gs.deleteAll(ch)){
				return "suc";
			}
		}
		if(gs.delete(goods)){
			return "success";			
		}else{
			return "error";
		}
		
	}

}
