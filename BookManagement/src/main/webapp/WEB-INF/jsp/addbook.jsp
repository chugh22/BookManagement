<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1>Enter Details</h1>   <br>
<form action = "saveBooks">
BookName :
<input type = "text" name = "bookname">
<br>
Price : 
<input type = "number" name = "price" >
<br>
Year :
<input type = "number" name = "year" >
<select name="author_id">
<c:forEach items = "${list}" var = "obj">
<option value = "${obj.id}"><c:out value = "${obj.name}"/></option> 
</c:forEach>
</select>
<br> 
<input type = "submit"  value = "Register">
</form>
</body>
</html>

