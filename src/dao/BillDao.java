package dao;

import database.MySqlConnection;
import model.Bill;
import model.BillItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BillDao {

    private final MySqlConnection db = new MySqlConnection();

    // Save the Bill and its items
    public boolean save(Bill bill) {
        try (Connection conn = db.openConnection()) {
            conn.setAutoCommit(false);

            // Save Bill 
            String sqlBill = "INSERT INTO bills (bill_number, customer_name, phone, email, sub_total, discount, tax, grand_total, created_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            int billId;
            try (PreparedStatement ps = conn.prepareStatement(sqlBill, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, bill.getBillNumber());
                ps.setString(2, bill.getCustomerName());
                ps.setString(3, bill.getPhone());
                ps.setString(4, bill.getEmail());
                ps.setDouble(5, bill.getSubTotal());
                ps.setDouble(6, bill.getDiscount());
                ps.setDouble(7, bill.getTax());
                ps.setDouble(8, bill.getGrandTotal());
                ps.setInt(9, bill.getCreatedBy());

                int affectedRows = ps.executeUpdate();
                if (affectedRows == 0) {
                    conn.rollback();
                    return false;
                }

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        billId = rs.getInt(1);
                    } else {
                        conn.rollback();
                        return false;
                    }
                }
            }

            // Save Bill Items
            String sqlItem = "INSERT INTO bill_items (bill_id, product_name, price, quantity, total) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement psItem = conn.prepareStatement(sqlItem)) {
                for (BillItem item : bill.getItems()) {
                    psItem.setInt(1, billId);
                    psItem.setString(2, item.getProductName());
                    psItem.setDouble(3, item.getPrice());
                    psItem.setInt(4, item.getQuantity());
                    psItem.setDouble(5, item.getTotal());
                    psItem.addBatch();
                }
                psItem.executeBatch();
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
