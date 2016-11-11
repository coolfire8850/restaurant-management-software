package com.bindong.view;

/**
 * L'interface graphique pour la gestion de l'emploi
 */
import com.bindong.model.*;
import com.bindong.tools.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class EmpInfo extends JPanel implements ActionListener {
	// definir les objets necessaires
	JPanel p1, p2, p3, p4, p5;
	JLabel p1_l1, p3_l1;
	JTextField p1_jtf;
	JButton p1_jb, p4_jb1, p4_jb2, p4_jb3, p4_jb4;
	JTable jtable;
	JScrollPane jsp;
	EmpModel em = null;
	RowSorter<TableModel> sorter;
	boolean details;

	public EmpInfo() {
		

		// panel nord
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1_l1 = new JLabel("Enter the information for searching:");
		p1_l1.setFont(MyTools.f1);
		p1_jtf = new JTextField(20);
		// p1_jb=new JButton("Search");
		// p1_jb.addActionListener(this);
		// p1_jb.setFont(MyTools.f4);
		p1.add(p1_l1);
		p1.add(p1_jtf);
		// p1.add(p1_jb);

		// milieu

		p2 = new JPanel(new BorderLayout());
		String[] params = { "1" };
		String sql = "select empid,empname,sex,jobtitle,address,edu from rszl where 1=?";
		em = new EmpModel();
		em.query(sql, params);
		jtable = new JTable(em);
		

		sorter = new TableRowSorter<TableModel>(em);
		jtable.setRowSorter(sorter);

		p1_jtf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {

				if (jtable != null) {
					String text = p1_jtf.getText();
					if (text == null || text.length() == 0) {
						((TableRowSorter<TableModel>) jtable.getRowSorter())
								.setRowFilter(null);
					} else {
						if (jtable.getRowSorter() != null) {// 当Jtable中无数据时，jtable.getRowSorter()是null。
							((TableRowSorter<TableModel>) jtable.getRowSorter())
									.setRowFilter(RowFilter.regexFilter(text));
						}
					}
				}
			}
		});
		jsp = new JScrollPane(jtable);

		p2.add(jsp);

		// sud
		p5 = new JPanel(new BorderLayout());
		p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		sql = "select count(*) from rszl";
		em = new EmpModel();
		int sum = em.getNum(sql);

		
		p3_l1 = new JLabel("The total number of records:" + sum);

		
		p3.add(p3_l1);
		p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4_jb1 = new JButton("Details");
		p4_jb1.addActionListener(this);
		p4_jb1.setFont(MyTools.f4);
		p4_jb2 = new JButton("Add");
		p4_jb2.addActionListener(this);
		p4_jb2.setFont(MyTools.f4);
		p4_jb3 = new JButton("Modify");
		p4_jb3.addActionListener(this);
		p4_jb3.setFont(MyTools.f4);
		p4_jb4 = new JButton("Delete");
		p4_jb4.addActionListener(this);
		p4_jb4.setFont(MyTools.f4);
		p4.add(p4_jb1);
		p4.add(p4_jb2);
		p4.add(p4_jb3);
		p4.add(p4_jb4);
		p5.add(p3, "West");
		p5.add(p4, "East");
		
		this.setLayout(new BorderLayout());
		this.add(p1, "North");
		this.add(p2, "Center");
		this.add(p5, "South");
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根

		if (arg0.getSource().equals(p1_jb)) {

			if (p1_jtf.getText().trim().equals("")) {
				String[] params = { "1" };
				String sql = "select empid,empname,sex,jobtitle,address,edu from rszl where 1=?";
				em = new EmpModel();
				em.query(sql, params);
				jtable.setModel(em);
			} else {
				String params[] = { p1_jtf.getText().trim(),
						p1_jtf.getText().trim() };
				String sql = "select empid,empname,sex,jobtitle,address,edu from rszl where empid like ? or empname like ?";
				em = new EmpModel();
				em.query(sql, params);

				jtable.setModel(em);
			}
		} else if (arg0.getSource().equals(p4_jb1)) {
			int rowNum = this.jtable.getSelectedRow();
			// System.out.println(rowNum);
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "Please choose one row!");
			} else {
				String empId = (String) this.jtable.getValueAt(rowNum, 0);
				String sql = "select*from rszl where empid=?";
				String[] params = { empId };
				em = new EmpModel();

				em.query(sql, params);
				jtable.setModel(em);
				sorter = new TableRowSorter<TableModel>(em);
				jtable.setRowSorter(sorter);
				details = true;
			}

		} else if (arg0.getSource().equals(p4_jb2)) {

			new AddEmpDialog(this, "Add", true);

			refreshTable();

		} else if (arg0.getSource().equals(p4_jb3)) {

			int rowNum = this.jtable.getSelectedRow();

			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "Please choose one row!");
			}

			else {
				if (details)
					new UpdEmpDialog(this, "Modify", true, em, rowNum);
				else {
					details = false;
					refreshTable();
					new UpdEmpDialog(this, "Modify", true, em, rowNum);
				}

			}

			refreshTable();
		} else if (arg0.getSource().equals(p4_jb4)) {
			int rowNum = this.jtable.getSelectedRow();

			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "Please choose one row!");
			} else {
				String empId = (String) this.jtable.getValueAt(rowNum, 0);
				String sql = "select * from login where empid=?";
				String[] params = { empId };
				if(em.checkIdExistence(sql, params))
					JOptionPane.showMessageDialog(null,
							"Sorry,this staff id existe in the user table!");
				  
				else {
					sql = "delete from rszl where empid=?";
					int result = JOptionPane.showConfirmDialog(null,
							"Do you want to delete it?", "Confirm,please",
							JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.OK_OPTION){
						if (em.UpdateModel(sql, params))
							JOptionPane.showMessageDialog(null,
									"Congratulation!Delete with success!");
					}
					
				}
				

			}

			refreshTable();
		}
	}

	public void refreshTable() {
		String[] params = { "1" };
		String sql = "select empid,empname,sex,jobtitle,address,edu from rszl where 1=?";
		em = new EmpModel();
		em.query(sql, params);
		jtable.setModel(em);
		sorter = new TableRowSorter<TableModel>(em);
		jtable.setRowSorter(sorter);
	}

}
