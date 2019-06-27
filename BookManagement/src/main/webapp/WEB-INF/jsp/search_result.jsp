<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
<h1>User List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>BookName</th><th>Price</th><th>Year</th><th>AuthorName</th></tr>  
   <c:forEach var="user" items="${list}">   
   <tr>    
   <td>${user.b.bookname}</td>  
   <td>${user.b.price}</td>  
   <td>${user.b.year}</td> 
   <td>${user.a.name}</td>   
   </tr>  
   </c:forEach>  
   </table>  
   <br/>  
   