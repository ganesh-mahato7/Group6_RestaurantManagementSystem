package model;

public final class Product {

    private int id;             // primary key
    private String name;
    private int categoryId;     // category foreign key
    private String categoryName; // for display in GUI
    private double price;

    // ================= CONSTRUCTORS =================

    // Default constructor
    public Product() {
    }

    // Constructor for INSERT (id auto-generated)
    public Product(String name, int categoryId, double price) {
        setName(name);
        setCategoryId(categoryId);
        setPrice(price);
    }

    // Constructor for SELECT / UPDATE with category ID
    public Product(int id, String name, int categoryId, double price) {
        setId(id);
        setName(name);
        setCategoryId(categoryId);
        setPrice(price);
    }

    // Constructor for SELECT / UPDATE with category name (for display)
    public Product(int id, String name, int categoryId, String categoryName, double price) {
        setId(id);
        setName(name);
        setCategoryId(categoryId);
        setCategoryName(categoryName);
        setPrice(price);
    }

    // ================= GETTERS =================

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public double getPrice() {
        return price;
    }

    // ================= SETTERS =================

    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("Product ID cannot be negative");
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Product name cannot be empty");
        this.name = name.trim();
    }

    public void setCategoryId(int categoryId) {
        if (categoryId < 0) throw new IllegalArgumentException("Category ID cannot be negative");
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName != null ? categoryName.trim() : null;
    }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Product price cannot be negative");
        this.price = price;
    }

    // ================= UTILITY =================

    @Override
    public String toString() {
        return name; // useful for JComboBox
    }
}
