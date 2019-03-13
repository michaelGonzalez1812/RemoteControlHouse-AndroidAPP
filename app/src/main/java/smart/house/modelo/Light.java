package smart.house.modelo;

public class Light {

    private String name;
    private boolean on;

    public Light(String name, boolean on) {
        this.name = name;
        this.on = on;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //TODO: verificar si no se necesita get en vez de is
    public boolean isOn() {
        return on;
    }

    public void setOn(boolean value) {
        this.on = value;
    }
}
