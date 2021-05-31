package bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dev.vo.MemberVo;

import bank.*;

import bank.vo.Account;

public class BankDAO {
	private static BankDAO dao = new BankDAO();
	private BankDAO() {}

	public static BankDAO getInstance() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	public Connection connect()
	{
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankDB","root","root11");
		}catch(Exception e) {
			System.out.print("MDAO:connect"+e);
		}
		return conn;
	}
	
	public void close(Connection conn, PreparedStatement pstmt)
	{
		if(pstmt != null)
		{
			try {
				pstmt.close();	
			}catch(Exception e){
				System.out.print("Pstmt close error"+e);
			}
		}
		if(conn != null)
		{
			try {
				conn.close();	
			}catch(Exception e){
				System.out.print("Conn close error"+e);
			}
		}
	}
	
	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
	{
		if(rs != null)
		{
			try {
				rs.close();	
			}catch(Exception e){
				System.out.print("rs close error"+e);
			}
		}
		close(conn,pstmt);
	}

	public void join(Account account) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("insert into account values(?,?,?);");
			pstmt.setString(1, account.getId());
			pstmt.setString(2, account.getPwd());
			pstmt.setString(3, account.getMoney()+"");
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.print("join error"+e);
		}finally {
			close(conn,pstmt);
		}
		
	}
	
	public boolean login(String id, String pwd) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("select * from account where id = ? and pwd = ?;");
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				result= true;
			}else
				result = false;
			
		}catch(Exception e) {
			System.out.print("login error"+e);
		}finally {
			close(conn,pstmt,rs);
		}
		return result;
	}
	
	public int deposit(String id, int money) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int moneyDB = 0;
		
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("select money from account where id=?;");
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				moneyDB= rs.getInt(1);
			}
			money = moneyDB + money;

			pstmt = conn.prepareStatement("update account set money=? where id=?;");
			pstmt.setString(1, money+"");
			pstmt.setString(2,id);
			pstmt.executeUpdate();			
		}catch(Exception e) {
			System.out.print("login error"+e);
		}finally {
			close(conn,pstmt,rs);
		}
		return money;
	}	
	
	public int withdrawal(String id, int money) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int moneyDB = 0;
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("select money from account where id=?;");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) 
				moneyDB = rs.getInt(1);
			money = moneyDB - money;
			
			pstmt = conn.prepareStatement("update money set money=? where id=?;");
			pstmt.setString(1, money+"");
			pstmt.setString(2,id);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.print("join error"+e);
		}finally {
			close(conn,pstmt,rs);
		}
		return money;
	}
	
	public int query(String id) {
		int money = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("select money from account where id=?;");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) 
			{
				money = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.print("login error"+e);
		}finally {
			close(conn,pstmt,rs);
		}
		return money;	
	}
	
	public boolean search(String id) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			conn = connect();
			pstmt = conn.prepareStatement("select id from account where id=?;");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) 
			{
				result = true;
			}
		}catch(Exception e) {
			System.out.print("login error"+e);
		}finally {
			close(conn,pstmt,rs);
		}
		return result;
		
	}
	
	public int transfer(String id, String rid, int money) {
		// TODO Auto-generated method stub
		int tmoney = this.withdrawal(id,money);
		if(tmoney < 0)
			return tmoney;
		this.deposit(rid, money);
		return tmoney;
	}

}
