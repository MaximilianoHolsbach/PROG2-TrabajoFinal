package modelo;

public abstract class Base {
    private int id;
    private boolean eliminado = false;
    

    // Getters, setters, constructor vacío y completo
    public Base() {
    }

    public Base(int id, boolean eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    
}

