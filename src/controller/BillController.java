package controller;

import dao.BillDao;
import model.Bill;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BillController {

    private final BillDao billDao = new BillDao();

    public String generateBillNumber() {
        return "BILL-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public void generateBill(Bill bill) throws Exception {
        billDao.save(bill);
    }
}
