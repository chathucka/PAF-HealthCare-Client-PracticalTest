package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Appointment;

/**
 * Servlet implementation class AppointmentAPI
 */
@WebServlet("/AppointmentAPI")
public class AppointmentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Appointment appointment = new Appointment();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AppointmentAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("rawtypes")
	private static Map  getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");

			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
			
		}

		return map;

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userID = request.getParameter("userID");
		String doctorID = request.getParameter("doctorID");
		String appointmentDate = request.getParameter("appointmentDate");
		String appointmentTime = request.getParameter("appointmentTime");
		String tokenNo = request.getParameter("tokenNo");
		String payType = request.getParameter("payType");
		String amount = request.getParameter("amount");
		String out = appointment.insertAppointment(userID, doctorID, appointmentDate, appointmentTime, tokenNo, payType, amount);

		response.getWriter().write(out);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		@SuppressWarnings("rawtypes")
		Map paras =  getParasMap(request);
		
		String appointmentID = paras.get("hIDappointmentIDSave").toString();
		String userID = paras.get("userID").toString();
		String doctorID = paras.get("doctorID").toString();
		String appointmentDate = paras.get("appointmentDate").toString();
		String appointmentTime = paras.get("appointmentTime").toString();
		String tokenNo = paras.get("tokenNo").toString();
		String payType = paras.get("payType").toString();
		String amount = paras.get("amount").toString();
		String out = appointment.updateAppointment(appointmentID, userID, doctorID, appointmentDate, appointmentTime, tokenNo, payType, amount);
		
		
		response.getWriter().write(out);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		@SuppressWarnings("rawtypes")
		Map paras =  getParasMap(request);

		String appointmentID = paras.get("appointmentID").toString();
		String out = appointment.deleteAppointment(appointmentID);
		
	
		response.getWriter().write(out);
	}

}
