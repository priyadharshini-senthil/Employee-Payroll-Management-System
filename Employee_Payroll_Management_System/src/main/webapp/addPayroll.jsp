<!DOCTYPE html>
<html>
<head>
    <title>Add Payroll</title>
</head>
<body>

<h2>Add Payroll Record</h2>

<form action="<%=request.getContextPath()%>/MainServlet" method="post">



    <input type="hidden" name="operation" value="newRecord"/>

    Employee Name:
    <input type="text" name="employeeName" required />
    <br><br>

    Designation:
    <input type="text" name="designation" required />
    <br><br>

    Payment Date:
    <input type="date" name="paymentDate" required />
    <br><br>

    Salary:
    <input type="number" name="salary" required />
    <br><br>

    Department:
    <input type="text" name="department" />
    <br><br>

    Remarks:
    <input type="text" name="remarks" />
    <br><br>

    <input type="submit" value="Add Payroll" />

</form>

</body>
</html>
