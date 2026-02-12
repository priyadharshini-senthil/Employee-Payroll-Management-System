<%@ page import="com.wipro.payroll.bean.PayrollBean" %>

<!DOCTYPE html>
<html>
<head>
    <title>Display Payroll</title>
</head>
<body>

<h2>Payroll Details</h2>

<%
    PayrollBean bean = (PayrollBean) request.getAttribute("bean");
    String msg = (String) request.getAttribute("msg");

    if (bean != null) {
%>

    Record ID: <%= bean.getRecordId() %> <br><br>
    Employee Name: <%= bean.getEmployeeName() %> <br><br>
    Designation: <%= bean.getDesignation() %> <br><br>
    Payment Date: <%= bean.getPaymentDate() %> <br><br>
    Salary: <%= bean.getSalary() %> <br><br>
    Department: <%= bean.getDepartment() %> <br><br>
    Remarks: <%= bean.getRemarks() %> <br><br>

<%
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
