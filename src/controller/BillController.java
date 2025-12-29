package controller;

import dao.BillDao;
import model.Bill;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BillController {

    private final BillDao billDao = new BillDao();

    // Generate a unique bill number
    public String generateBillNumber() {
        return "BILL-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    // Save a bill with its items
    public boolean generateBill(Bill bill) throws Exception {
        return billDao.save(bill);
    }

    // Fetch all bills for displaying in the table (without sub_total)
    public List<Bill> getAllBillsForTable() throws Exception {
        return billDao.getAllBillsForTable(); // Make sure this exists in BillDao
    }
}
