<%@ page import="java.util.List" %>
<%@ page import="com.wipro.payroll.bean.PayrollBean" %>

<!DOCTYPE html>
<html>
<head>
    <title>All Payroll Records</title>
</head>
<body>

<h2>All Payroll Records</h2>

<%
    List<PayrollBean> list = (List<PayrollBean>) request.getAttribute("list");
    String msg = (String) request.getAttribute("msg");

    if (list != null && !list.isEmpty()) {

        for (PayrollBean bean : list) {
%>

            <hr>
            Record ID: <%= bean.getRecordId() %> <br>
            Employee Name: <%= bean.getEmployeeName() %> <br>
            Designation: <%= bean.getDesignation() %> <br>
            Payment Date: <%= bean.getPaymentDate() %> <br>
            Salary: <%= bean.getSalary() %> <br>
            Department: <%= bean.getDepartment() %> <br>
            Remarks: <%= bean.getRemarks() %> <br>

<%
        }

    } else {
%>

    <h3><%= msg %></h3>

<%
    }
%>

<br>
<a href="menu.html">Back to Menu</a>

</body>
</html>
