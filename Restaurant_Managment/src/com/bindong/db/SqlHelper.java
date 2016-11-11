package com.bindong.db;
/**
 * Cette classe communique directment avec la base de donnee,
 * etablir la connection de base de donnee
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class SqlHelper {
	//definir les objets necessaires
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url="jdbc:sqlserver://127.0.0.1:1433;database=restaurant";
	String user="sa";
	String passwd="bonjour";
	int sum=0;
	
	public SqlHelper()
	{
		try {
			//charger du drive
			Class.forName(driver);
			
			//obtenir la connection
			con=DriverManager.getConnection(url,user,passwd);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//[]params£¬Vuln¨¦rabilit¨¦s d'injection peuvent ¨ºtre ¨¦vit¨¦es par la method ?
	public ResultSet queryExecute(String sql,String []params)
	{
		try {
			ps=con.prepareStatement(sql);
			//L'affectation pour sql
			for(int i=0;i<params.length;i++)
			{
				ps.setString(i+1,params[i]);
			}
			
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return rs;
	}
	public int queryExecute(String sql)
	{
		try {
			ps=con.prepareStatement(sql);
			
			rs=ps.executeQuery();
			if(rs.next())
			{
				sum=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		//return collection de resultats
		return sum;
	}
	
	
	public boolean updateExecete(String sql,String []params)
	{
		boolean b=true;
		try {
			ps=con.prepareStatement(sql);
			
			for(int i=0;i<params.length;i++)
			{
				ps.setString(i+1, params[i]);
			}
		
			if(ps.executeUpdate()!=1)
			{
				b=false;
			}
		} catch (Exception e) {
			b=false;
			e.printStackTrace();
			// TODO: handle exception
		}
		finally
		{
			this.close();
		}
		
		return b;
		
	}
	//fermer la ressource
	public void close()
	{
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
