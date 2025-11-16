package modelo;

import java.time.LocalDate;

public class Paciente extends Base{
    public static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";
    
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private HistoriaClinica historiaClinica;

    public Paciente() {
        super();
    }

    public Paciente(String nombre, String apellido, String dni, LocalDate fechaNacimiento, HistoriaClinica historiaClinica, int id, boolean eliminado) {
        super(id, eliminado);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.historiaClinica = historiaClinica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    @Override
    public String toString() {
                java.time.format.DateTimeFormatter fmt =
                java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String fecha = (fechaNacimiento != null)
                ? fechaNacimiento.format(fmt)
                : "N/A";
        
        return PURPLE+"Paciente"+RESET+"{" + "id = " + getId() +
                ", nombre = " + nombre + ", apellido = " + apellido + ", dni = " + dni + ", fechaNacimiento = " + fecha +"}";
    }    
}