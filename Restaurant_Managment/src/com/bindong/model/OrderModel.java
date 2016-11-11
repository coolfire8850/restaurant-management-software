package com.bindong.model;
/**
 *  C'est un model qui sert a manipuler les operations 
 *  pour la commande
 */
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import com.bindong.db.SqlHelper;

public class OrderModel {
	/*obtenir le valeur de status par le id de table, 0 represente en train de manger
	 * 1 represente est reserve, 2 represent disponible
	*/
	public String geStatusById(String deskid)
	{
		String deskstatus = null;
		SqlHelper hp=null;
		try{
		String sql="select deskstatus from status where deskid=?";
		String []params={deskid};
		hp=new SqlHelper();
		ResultSet rs=hp.queryExecute(sql, params);
		if(rs.next())
		{
			deskstatus=rs.getString(1);
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
		return deskstatus;
	}
	//mise a jour l'etat de la table
	public boolean UpdateModel(String sql,String []params)
	{
		SqlHelper hp=new SqlHelper();
		return hp.updateExecete(sql,params);
	}
	
	//obtenir tous les id de statu pour les tables
	public String query(String sql,String[]params)
	{
		
		String deskid="";
		SqlHelper hp=new SqlHelper();
		ResultSet rs= hp.queryExecute(sql, params);
		
		try
		{
		if(rs.next())
		
			{
				deskid=rs.getString(1);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			hp.close();
		}
		return deskid;
	}
}
