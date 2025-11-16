package servicio;

import modelo.Paciente;

public interface PacienteService extends GenericService<Paciente>{
    Paciente buscarPorDni(String dni);
    void insertarConHistoria(Paciente paciente);
}
