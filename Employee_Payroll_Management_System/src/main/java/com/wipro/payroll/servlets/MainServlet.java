package com.wipro.payroll.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.wipro.payroll.bean.PayrollBean;
import com.wipro.payroll.service.Administrator;
@WebServlet("/MainServlet")

public class MainServlet extends HttpServlet {

    Administrator admin = new Administrator();
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        try {

            if ("newRecord".equals(operation)) {

                PayrollBean bean = new PayrollBean();
                bean.setEmployeeName(request.getParameter("employeeName"));
                bean.setDesignation(request.getParameter("designation"));
                bean.setDepartment(request.getParameter("department"));
                bean.setRemarks(request.getParameter("remarks"));
                bean.setSalary(Integer.parseInt(request.getParameter("salary")));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(request.getParameter("paymentDate"));
                bean.setPaymentDate(date);

                String result = admin.addRecord(bean);

                if ("FAIL".equals(result) || "INVALID INPUT".equals(result))
                    response.sendRedirect("error.html");
                else
                    response.sendRedirect("success.html");
            }

            else if ("viewRecord".equals(operation)) {

                String name = request.getParameter("employeeName");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(request.getParameter("paymentDate"));

                PayrollBean bean = admin.viewRecord(name, date);

                if (bean == null)
                    request.setAttribute("msg",
                       "No matching records exists! Please try again!");
                else
                    request.setAttribute("bean", bean);

                RequestDispatcher rd =
                        request.getRequestDispatcher("displayPayroll.jsp");
                rd.forward(request, response);
            }

            else if ("viewAllRecords".equals(operation)) {

                List<PayrollBean> list = admin.viewAllRecords();

                if (list.isEmpty())
                    request.setAttribute("msg", "No records available!");
                else
                    request.setAttribute("list", list);

                RequestDispatcher rd =
                        request.getRequestDispatcher("displayAllPayrolls.jsp");
                rd.forward(request, response);
            }

        } catch (Exception e) {
            response.sendRedirect("error.html");
        }
    }
}
