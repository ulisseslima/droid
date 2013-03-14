package com.dvlcube.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author wonka
 * @since 13/03/2013
 */
public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 170370901502148206L;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		title += "áéíóú";
		System.out.println("testing " + title);
		response.getWriter().write(title);
	}
}
