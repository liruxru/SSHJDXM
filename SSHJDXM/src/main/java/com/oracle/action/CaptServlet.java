package com.oracle.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@ParentPackage("json-default")
@Namespace("/user/web")
@Controller
public class CaptServlet extends HttpServlet {
	
	
	
	@Action(value="CaptServlet",results={
			
	})
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/jpeg");
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");
		int length = 4;
		String valcode = "";
		Random rd = new Random();
		for (int i = 0; i < length; i++)
			valcode += rd.nextInt(10);
		HttpSession session = request.getSession();
		session.setAttribute("valcode", valcode);
		int width = 80;
		int height = 25;
		BufferedImage img = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		for (int i = 0; i < 50; i++) {
			g.setColor(new Color(rd.nextInt(100) + 155, rd.nextInt(100) + 155,rd.nextInt(100) + 155));
			g.drawLine(rd.nextInt(width), rd.nextInt(height),
					rd.nextInt(width), rd.nextInt(height));
		}
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, width - 1, height - 1);
		Font[] fonts = { new Font("����", Font.BOLD, 18),
				new Font("����", Font.BOLD, 18), new Font("����", Font.BOLD, 18),
				new Font("��Բ", Font.BOLD, 18) };
		for (int i = 0; i < length; i++) {
			g.setColor(new Color(rd.nextInt(150), rd.nextInt(150), rd.nextInt(150)));
			g.setFont(fonts[rd.nextInt(fonts.length)]);
			g.drawString(valcode.charAt(i) + "", width / valcode.length() * i + 2, 18);
		}
		g.dispose();
		ImageIO.write(img, "jpeg", response.getOutputStream());
	}

}
