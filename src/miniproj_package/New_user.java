package miniproj_package;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miniproj_package.DbConnection;

/**
 * Servlet implementation class New_user
 */
public class New_user extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public New_user() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String name=request.getParameter("name");
		String addr=request.getParameter("addr");
		int mob=Integer.parseInt(request.getParameter("mob"));
		String email=request.getParameter("email");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
			
		Connection con=DbConnection.connection();
		
		try{
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from lender");
			int i=0;
			String uname[]=new String[30];
			while(rs.next()){
				uname[i]=rs.getString(5);
				i++;
			}
			for(i=0;i<30;i++){
				if(uname[i]==username){
					response.setContentType("text/html");
					PrintWriter out=response.getWriter();
					out.println("<html><body><center>");
					out.println("<br><br>Username already exists ! Please choose another one.");
					out.println("<br><br><a href='rentcar.html'>Register again</a>");
					break;
				} else{
					continue;
				}
			}
            if(i==30){
            	PreparedStatement pstmt=con.prepareStatement("insert into lender values(?,?,?,?,?,?,?,?,?,?,?)");
            	pstmt.setString(1, name);
                pstmt.setString(2, addr);
                pstmt.setInt(3, mob);
                pstmt.setString(4, email);
                pstmt.setString(5, username);
                pstmt.setString(6, password);
                pstmt.setString(7, null);
                pstmt.setString(8, null);
                pstmt.setInt(9, -99);
                pstmt.setString(10, null);
                pstmt.setInt(11, -99);
                
                int j=pstmt.executeUpdate();
                
                System.out.println(j+"Registered successfully !");               
                System.out.println("Redirecting to login page");
             
           		response.sendRedirect("login.html");    	
            }           	    
		} catch(Exception e){
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
