package com.wipro.payroll.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wipro.payroll.bean.PayrollBean;
import com.wipro.payroll.util.DBUtil;

public class PayrollDAO {

    public String createRecord(PayrollBean bean) {

        try {
            Connection con = DBUtil.getDBConnection();

            String sql = "INSERT INTO PAYROLL_TB VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bean.getRecordId());
            ps.setString(2, bean.getEmployeeName());
            ps.setString(3, bean.getDesignation());
            ps.setDate(4, new java.sql.Date(bean.getPaymentDate().getTime()));
            ps.setInt(5, bean.getSalary());
            ps.setString(6, bean.getDepartment());
            ps.setString(7, bean.getRemarks());

            int i = ps.executeUpdate();

            if (i > 0)
                return bean.getRecordId();
            else
                return "FAIL";

        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

    public PayrollBean fetchRecord(String name, Date date) {

        try {
            Connection con = DBUtil.getDBConnection();

            String sql = "SELECT * FROM PAYROLL_TB WHERE EMPLOYEENAME=? AND PAYMENT_DATE=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setDate(2, new java.sql.Date(date.getTime()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PayrollBean bean = new PayrollBean();
                bean.setRecordId(rs.getString(1));
                bean.setEmployeeName(rs.getString(2));
                bean.setDesignation(rs.getString(3));
                bean.setPaymentDate(rs.getDate(4));
                bean.setSalary(rs.getInt(5));
                bean.setDepartment(rs.getString(6));
                bean.setRemarks(rs.getString(7));
                return bean;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String generateRecordID(String name, Date date) {

        try {
            Connection con = DBUtil.getDBConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT PAYROLL_SEQ.NEXTVAL FROM DUAL");

            int seq = 0;
            if (rs.next())
                seq = rs.getInt(1);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String datePart = sdf.format(date);

            String firstTwo = name.substring(0, 2).toUpperCase();

            return datePart + firstTwo + String.format("%02d", seq);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean recordExists(String name, Date date) {
        return fetchRecord(name, date) != null;
    }

    public List<PayrollBean> fetchAllRecords() {

        List<PayrollBean> list = new ArrayList<>();

        try {
            Connection con = DBUtil.getDBConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PAYROLL_TB");

            while (rs.next()) {
                PayrollBean bean = new PayrollBean();
                bean.setRecordId(rs.getString(1));
                bean.setEmployeeName(rs.getString(2));
                bean.setDesignation(rs.getString(3));
                bean.setPaymentDate(rs.getDate(4));
                bean.setSalary(rs.getInt(5));
                bean.setDepartment(rs.getString(6));
                bean.setRemarks(rs.getString(7));
                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
