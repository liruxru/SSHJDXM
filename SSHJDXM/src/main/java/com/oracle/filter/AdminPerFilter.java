package com.oracle.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.entity.Adminroles;
import com.oracle.entity.Admins;
import com.oracle.entity.Permission;

public class AdminPerFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		HttpSession session=request.getSession();
		Admins admin=(Admins)session.getAttribute("admin");
		String strRequestUrl=request.getServletPath();
		if (admin==null) {
			response.sendRedirect(request.getContextPath()+"/page/adminLogin.jsp");
			return;
		}
		if (strRequestUrl.equals("/admin/home")||strRequestUrl.equals("/admin/home.action")) {
			arg2.doFilter(request, response);
			return;
		}
		
		if (strRequestUrl.endsWith(".action")) {
			strRequestUrl=strRequestUrl.substring(0, strRequestUrl.indexOf("."));
		}
		
		Pattern pattern=Pattern.compile(strRequestUrl);
		for (Adminroles adminroles : admin.getAdminroleses()) {
			for (Permission p : adminroles.getPermissions()) {
				Matcher m=pattern.matcher(p.getUrl());
				if (m.find()) {
					arg2.doFilter(request, response);
					return;
				}
			}
		}
		response.sendRedirect(request.getContextPath()+"/page/errorpage/perError.jsp");
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		

	}

}
