<!DOCTYPE html>
<html>
<head>
    <title>View All Payrolls</title>
</head>
<body>

<h2>View All Payroll Records</h2>

<form action="<%=request.getContextPath()%>/MainServlet" method="post">


    <input type="hidden" name="operation" value="viewAllRecords"/>

    <input type="submit" value="View All Records" />

</form>

</body>
</html>
