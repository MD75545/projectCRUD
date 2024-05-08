<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="updateProduct" method="post">
	
	<input type="hidden" name="uId" value="<%= request.getAttribute("uId") %>"><br>
    Product_Name:<input type="text" name="prod_name"><br>
    
    Product_Category:<input type="text" name="category"><br>
    
    Product_Price:<input type="number" name="price"><br>
    
    Product_Manufacturing:<input type="Date" name="manufacturing_date"><br>
    
    <input type="submit" value="addRecord">
</form>

</body>
</html>