package com.bindong.model;
/**
 * C'est un model pour verifer les utilisateurs existants
 */
import java.sql.*;

import com.bindong.db.*;

public class UserModel {
public String checkUser(String uid,String p)
{
	String zhiwei = "";
	SqlHelper hp=null;
	try{
	String sql="select rszl.jobtitle from rszl,login where rszl.empid=login.empid and login.empid=? and login.passwd=?";
	String []params={uid,p};
	hp=new SqlHelper();
	ResultSet rs=hp.queryExecute(sql, params);
	if(rs.next())
	{
		zhiwei=rs.getString(1);
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		hp.close();
	}
	return zhiwei;
}
	//obtenir le nom d' un utilisateur
	public String getNameById(String uid)
	{
		String empname = null;
		SqlHelper hp=null;
		try{
		String sql="select empname from rszl where empid=?";
		String []params={uid};
		hp=new SqlHelper();
		ResultSet rs=hp.queryExecute(sql, params);
		if(rs.next())
		{
			empname=rs.getString(1);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			hp.close();
		}
		return empname;
	}
}
