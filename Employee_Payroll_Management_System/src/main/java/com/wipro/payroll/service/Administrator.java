package com.wipro.payroll.service;

import java.util.Date;
import java.util.List;

import com.wipro.payroll.bean.PayrollBean;
import com.wipro.payroll.dao.PayrollDAO;
import com.wipro.payroll.util.InvalidInputException;

public class Administrator {

    PayrollDAO dao = new PayrollDAO();

    public String addRecord(PayrollBean bean) {

        try {

            if (bean == null || bean.getEmployeeName() == null || bean.getPaymentDate() == null)
                throw new InvalidInputException();

            if (bean.getEmployeeName().length() < 2)
                return "INVALID EMPLOYEE NAME";

            if (dao.recordExists(bean.getEmployeeName(), bean.getPaymentDate()))
                return "ALREADY EXISTS";

            String id = dao.generateRecordID(bean.getEmployeeName(), bean.getPaymentDate());
            bean.setRecordId(id);

            return dao.createRecord(bean);

        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        }
    }

    public PayrollBean viewRecord(String name, Date date) {
        return dao.fetchRecord(name, date);
    }

    public List<PayrollBean> viewAllRecords() {
        return dao.fetchAllRecords();
    }
}
