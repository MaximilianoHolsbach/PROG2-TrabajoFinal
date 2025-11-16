package modelo;

public enum GrupoSanguineo {

    DESCONOCIDO(0, "Desconocido"),
    G1(1, "A+"),
    G2(2, "A-"),
    G3(3, "B+"),
    G4(4, "B-"),
    G5(5, "AB+"),
    G6(6, "AB-"),
    G7(7, "0+"),
    G8(8, "0-");
    

    private final int codigo;
    private final String descripcion;

    GrupoSanguineo(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }

    public static GrupoSanguineo fromCodigo(int codigo) {
        for (GrupoSanguineo g : values()) {
            if (g.codigo == codigo) return g;
        }
        return DESCONOCIDO;
    }
}
