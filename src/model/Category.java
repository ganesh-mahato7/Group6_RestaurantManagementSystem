package model;

public final class Category {

    private int id;
    private String name;

    // ================= CONSTRUCTORS =================
    public Category() { }

    public Category(int id, String name) {
        setId(id);
        setName(name);
    }

    public Category(String name) {
        setName(name);
    }

    // ================= GETTERS =================
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // ================= SETTERS =================
    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("Category ID cannot be negative");
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        this.name = name.trim();
    }

    // ================= UTILITY =================
    @Override
    public String toString() {
        return name; // useful for JComboBox display
    }
}
