package com.bindong.model;
/**
 * c'est le model pour afficher les informations des emplois
 */
import com.bindong.db.*;

import java.sql.*;
import java.util.*;

import javax.swing.table.*;

public class EmpModel extends AbstractTableModel {
	public Vector<String> colums;
	public Vector<Vector> rows;
    //pour ajouter , modifier les donnees
	public boolean UpdateModel(String sql, String[] params) {
		SqlHelper hp = new SqlHelper();
		return hp.updateExecete(sql, params);
	}
    //obtenir le nombre d'enregistrements
	public int getNum(String sql) {
		SqlHelper hp = new SqlHelper();
		int sum = hp.queryExecute(sql);
		return sum;
	}
	//afficher les donnees
	public void query(String sql, String[] params) {
		

		colums = new Vector<String>();
		rows = new Vector<Vector>();
		
		SqlHelper hp = new SqlHelper();
		ResultSet rs = hp.queryExecute(sql, params);
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				//obtenir le nom de coloumn
				this.colums.add(rsmd.getColumnName(i + 1));
			}
			while (rs.next()) {
				Vector<String> temp = new Vector<String>();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					temp.add(rs.getString(i + 1));
				}
				rows.add(temp);
				// temp.add(rs.getString(1));
				// temp.add(rs.getString(2));
				// temp.add(rs.getString(3));
				// temp.add(rs.getString(4));
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			hp.close();
		}
	}
	
	public boolean checkIdExistence(String sql,String[] params){
		boolean a = true;
		try {
			SqlHelper	hp =new SqlHelper();
			ResultSet rs=hp.queryExecute(sql, params);
			if(rs.next())
				a= true;
			else {
				a = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			a = false;
		}
		return a;
	}
	// obtenir les id et les valeurs correspondants
	public HashMap<String, String> getPairValues(String sql, String[] params){
		HashMap<String, String> map = new HashMap<String, String>();
		SqlHelper hp = new SqlHelper();
		ResultSet rs = hp.queryExecute(sql, params);
		try {
			while(rs.next()){
				map.put(rs.getString(1), rs.getString(2));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return this.colums.size();
	}

	public int getRowCount() {
		// TODO 自动生成的方法存根
		return this.rows.size();
	}

	@Override
	public String getColumnName(int arg0) {
		// TODO 自动生成的方法存根
		return this.colums.get(arg0).toString();
	}

	public Object getValueAt(int arg0, int arg1) {
		// TODO 自动生成的方法存根
		return ((Vector) rows.get(arg0)).get(arg1);
	}

}
