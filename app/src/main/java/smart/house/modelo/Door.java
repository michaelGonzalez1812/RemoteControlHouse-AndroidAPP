package smart.house.modelo;

public class Door {

    private String name;
    private boolean open;

    public Door(String name, boolean open) {
        this.name = name;
        this.open = open;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
