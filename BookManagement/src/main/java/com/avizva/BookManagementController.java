package com.avizva;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.* ;
@Controller
public class BookManagementController {
	@Autowired
	BookManagementDao dao ;
	@RequestMapping("/register")
	public String register(){
		return "register" ;
	}
	@RequestMapping("/login")
	public String login(@RequestParam("email") String email ,
			@RequestParam("password") String password , Model m){
		try {
			Users u = dao.getUser(email) ;
			if(u.getPassword().equals(password)){
				m.addAttribute("user" , u) ;
				if(u.getAdmin()){
					m.addAttribute("msg" , "ADMIN") ;
					return "admin" ;
				} else {
					m.addAttribute("msg" , "User") ;
					return "login" ;
				}
				
			} else {
				m.addAttribute("message" , "Password Mismatch") ;
				return "error" ;
			}
		}catch(Exception se){
			m.addAttribute("message" , "NO USER FOUND") ;
			return "error" ;
		}
		
	}
	@RequestMapping("/saveRegister")
	public String saveRegister(@RequestParam("email") String email , 
								@RequestParam("name") String name,  
								@RequestParam("password") String password , Model m){
		try {
			Users u = new Users() ;
			u.setEmail(email);
			u.setName(name);
			u.setPassword(password);
			dao.setUser(u) ;
	 		List<Users> list = dao.getAllUsers() ;
			m.addAttribute("list",  list) ;
			return "saveRegister" ; 
		}catch(Exception e){
			m.addAttribute("message" , "Already Exist") ;
			return "error" ;
		}
		
		
	}
	@RequestMapping("/viewusers") 
	public String viewUsers(Model m){
		List<Users> list = dao.getAllUsers() ;
		m.addAttribute("list",  list) ;
		return "saveRegister" ; 
	}
	@RequestMapping("/authorreg")
	public String authorReg(){
		return "authorreg" ;
	}
	@RequestMapping("/addauthor")
	public String addAuthor(@RequestParam("name") String name){
		try {
			Author auth = new Author() ;
			auth.setName(name) ;
			dao.addAuthor(auth) ;
		} catch(Exception e){
			return "errorauthor" ;
		}
		return "admin" ;
	}
	@RequestMapping("/addbook")
	public String addBook(Model m){
		List<Author> list = dao.getAllAuthors() ;
		m.addAttribute("list" , list) ;
		return "addbook" ;
	}
	@RequestMapping("/search_result_author")
	public String searchResultAN(@RequestParam("name") String name , Model m){
		List<Pair> list = dao.searchAuthorName(name) ;
		m.addAttribute("list" , list);
		return "search_result" ;
	}
	@RequestMapping("/search_result_bookname")
	public String searchResultBN(@RequestParam("name") String name , Model m){
		List<Pair> list = dao.searchBookName(name) ;
		m.addAttribute("list" , list);
		return "search_result" ;
	}
	@RequestMapping("/search_bookname")
	public String searchbookname(){
		return "search_bookname" ;
	}
	@RequestMapping("/saveBooks")
	public String saveBooks(@RequestParam("bookname") String bookname , 
							@RequestParam("price") int price , 
							@RequestParam("year") int year , 
							@RequestParam("author_id") int author_id){
		Books b = new Books() ;
		b.setBookname(bookname);
		b.setPrice(price); 
		b.setYear(year);
		b.setAuthorid(author_id);
		dao.saveBooks(b) ;
		return "admin" ;
	}
	
}
