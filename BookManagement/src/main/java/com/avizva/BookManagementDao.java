package com.avizva;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.* ;
public class BookManagementDao {
	JdbcTemplate template ;
	public static final String TABLE_BOOKS = " BMSBOOKS " ;
	public static final String TABLE_USER = " BMSUSERS " ;
	public static final String TABLE_AUTHOR = " BMSAUTHORS " ;
	public class BooksMapper implements RowMapper<Books> {
		public Books mapRow(ResultSet rs, int rowNum) throws SQLException {
			Books b = new Books() ;
			 	b.setBookname(rs.getString(1));    
	            b.setBookid(rs.getInt(2)); 
	            b.setPrice(rs.getInt(3));
	            b.setYear(rs.getInt(4));
	            return b ;
		}
		
	}
	public class UserMapper implements RowMapper<Users> {

		public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
			Users u = new Users() ;
			u.setEmail(rs.getString(1)) ;
			u.setName(rs.getString(2));
			u.setAdmin(rs.getBoolean(3)) ;
			u.setPassword(rs.getString(4));
			return u ;
		}
		 
	}
	public class AuthorMapper implements RowMapper<Author> {

		public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
			Author a = new Author(); 
			a.setId(rs.getInt(1));
			a.setName(rs.getString(2));
			return a;
		}
		
	}
	public class PairMapper implements RowMapper<Pair> {

		public Pair mapRow(ResultSet rs, int rowNum) throws SQLException {
			Pair p = new Pair() ;
			Books b = new Books() ;
			Author a = new Author() ;
			b.setBookname(rs.getString(1));    
            b.setPrice(rs.getInt(2));
            b.setYear(rs.getInt(3));
			a.setName(rs.getString(4));
			p.setA(a);
			p.setB(b);
			return p;
		}
		
	}
	public void setTemplate(JdbcTemplate temp){
		this.template = temp ;
	}
	public int setUser(Users u){
		String sql = "INSERT INTO " + TABLE_USER +  " VALUES ( '" + u.getEmail() + "' , '" + u.getName() + "' , "
				+ u.getAdmin() + " , '" + u.getPassword() + "' ) " ;
		return template.update(sql);
	}
	public int saveBooks(Books b){
		String sql = "INSERT INTO " + TABLE_BOOKS + "(bookname , price , year , authorid)" 
				+" VALUES ( '" 
				+ b.getBookname() + "' , "
				+ b.getPrice() + " , " 
				+ b.getYear() + " ," 
				+ b.getAuthorid() +" ) " ;
		
		return template.update(sql);
	}
	
	public Users getUser(String email){
		String sql = "SELECT * FROM " + TABLE_USER + "WHERE email = ?" ;
		return template.queryForObject(sql, new Object[]{email} ,
				new UserMapper());
	}
	public int addAuthor(Author auth){
		String sql = "INSERT INTO " + TABLE_AUTHOR + " (authorname) VALUES ( '" + auth.getName() + "' )" ;
		return template.update(sql) ;
	}
	public List<Author> getAllAuthors(){
		String sql = "SELECT * FROM " + TABLE_AUTHOR ;
		return template.query(sql, new AuthorMapper()) ;	
	}
	public List<Pair> searchBookName(String name){
		String sql = "SELECT bookname , price , year , authorname FROM " +
				TABLE_BOOKS  + " INNER JOIN " + TABLE_AUTHOR + " ON " + 
				"BMSBOOKS.authorid =  BMSAUTHORS.authorid "
				+" WHERE bookname like '%" + name + "%'" ;
		return template.query(sql, new PairMapper()) ;
	}
	public List<Pair> searchAuthorName(String name){
		String sql = "SELECT bookname , price , year , authorname FROM " +
				TABLE_BOOKS  + " INNER JOIN " + TABLE_AUTHOR + " ON " + 
				"BMSBOOKS.authorid =  BMSAUTHORS.authorid "
				+" WHERE authorname like '%" + name + "%'" ;
		return template.query(sql, new PairMapper()) ;
	}
	public List<Books> getAllBooks(){
		return template.query("SELECT * FROM " + TABLE_BOOKS , new BooksMapper()) ;
	}
	public List<Users> getAllUsers(){
		return template.query("SELECT * FROM " + TABLE_USER , new UserMapper()) ;
	}
	public List<Users> getAllAdmins(){
		return null ;
	}
}