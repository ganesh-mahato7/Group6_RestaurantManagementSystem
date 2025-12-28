package controller;

import dao.BillDao;
import model.Bill;

public class PlaceOrderController {

    private final BillDao billDao = new BillDao();

    // Generate bill using the new save method
    public boolean generateBill(Bill bill) {
        // This saves both the bill and its items
        return billDao.save(bill);
    }

    // Generate a unique bill number
    public String generateBillNumber() {
        return "BILL-" + System.currentTimeMillis();
    }
}
