<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
<h1>User List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>email</th><th>Name</th><th>Admin</th><th>password</th></tr>  
   <c:forEach var="user" items="${list}">   
   <tr>    
   <td>${user.email}</td>  
   <td>${user.name}</td>  
   <td>${user.admin}</td> 
   <td>${user.password}</td>   
   </tr>  
   </c:forEach>  
   </table>  
   <br/>  
   