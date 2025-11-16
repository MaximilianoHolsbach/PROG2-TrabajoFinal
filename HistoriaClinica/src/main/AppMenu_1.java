/*package main;

import dao.HistoriaClinicaDao;
import dao.PacienteDao;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import modelo.GrupoSanguineo;
import modelo.HistoriaClinica;
import modelo.Paciente;

public class AppMenu_1 {
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN  = "\u001B[32m";
    public static final String RED    = "\u001B[31m";
    public static final String PURPLE = "\u001B[35m";
    public static final String YELLOW = "\u001B[33m";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        PacienteDao pacienteService = new PacienteDao();
        HistoriaClinicaDao historiaService = new HistoriaClinicaDao();

        do {
            System.out.print("\n");
            System.out.println(GREEN+"=== Menu Principal ==="+RESET);
            System.out.println(GREEN+"===   Clinica 53   ==="+RESET);
            System.out.println("1. Crear Paciente");
            System.out.println("2. Listar Pacientes");
            System.out.println("3. Buscar por DNI");
            System.out.println("4. Actualizar Paciente");
            System.out.println("5. Eliminar Paciente");
            System.out.println("6. Recuperar Paciente");
            System.out.println("7. Listar Historias Clínicas");
            System.out.println("8. Actualizar Historia Clínica");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1 -> {
                        System.out.print("\n");
                        System.out.println("Ingrese nombre:");
                        String nombre = scanner.nextLine();
                        
                        System.out.println("Ingrese apellido:");
                        String apellido = scanner.nextLine();
                        
                        System.out.println("Ingrese DNI:");
                        String dni = scanner.nextLine();
                        
                        System.out.println("Ingrese fecha de nacimiento (YYYY-MM-DD):");
                        LocalDate fecha = LocalDate.parse(scanner.nextLine());

                        System.out.println("Ingrese número de historia clínica:");
                        String nroHistoria = scanner.nextLine();
                        
                        System.out.println("Grupo sanguíneo (A_POS, A_NEG, B_POS, B_NEG, AB_POS, AB_NEG, O_POS, O_NEG):");
                        GrupoSanguineo grupo = GrupoSanguineo.valueOf(scanner.nextLine().toUpperCase());
                        
                        System.out.println("Antecedentes:");
                        String antecedentes = scanner.nextLine();
                        
                        System.out.println("Medicación actual:");
                        String medicacion = scanner.nextLine();
                        
                        System.out.println("Observaciones:");
                        String observaciones = scanner.nextLine();

                        HistoriaClinica hc = new HistoriaClinica(
                            nroHistoria,
                            grupo,
                            antecedentes,
                            medicacion,
                            observaciones,
                            0,         // id inicial (0 o -1 si aún no está en BD)
                            false      // eliminado por defecto
                        );

                        Paciente paciente = new Paciente(
                            nombre,
                            apellido,
                            dni,
                            fecha,
                            hc,
                            0,         // id inicial
                            false      // eliminado por defecto
                        );

                        historiaService.insertar(hc);
                        pacienteService.insertar(paciente);
                        System.out.println(CYAN+"✅ Paciente creado con historia clínica."+RESET);
                    }

                    case 2 -> {
                        System.out.print("\n");
                        List<Paciente> pacientes = pacienteService.getAll();
                        pacientes.forEach(System.out::println);
                    }

                    case 3 -> {
                        System.out.print("\n");
                        System.out.println("Ingrese DNI a buscar:");
                        String dni = scanner.nextLine();
                        Paciente p = pacienteService.getByDni(dni);
                        if (p != null) System.out.println(p+"\n"+p.getHistoriaClinica());
                        
                        else System.out.println(RED+"⚠️ Paciente no encontrado."+RESET);
                    }

                    case 4 -> {
                        System.out.print("\n");
                        System.out.println("Ingrese el DNI del paciente a actualizar:");
                        String dni = scanner.nextLine();
                        Paciente p = pacienteService.getByDni(dni);
                        if (p != null) {
                            System.out.print("Nuevo nombre (actual: " + p.getNombre() + ", "+CYAN+"Enter para mantener"+RESET+"): ");
                            String input = scanner.nextLine().trim();
                            if (!input.isEmpty()) p.setNombre(input);
                            System.out.print("Nuevo apellido (actual: " + p.getApellido() + ", "+CYAN+"Enter para mantener"+RESET+"): ");
                            input = scanner.nextLine().trim();
                            if (!input.isEmpty()) p.setApellido(input);
                            pacienteService.actualizar(p);
                            System.out.println(CYAN+"✅ Paciente actualizado."+RESET);
                        } else {
                            System.out.println(RED+"⚠ Paciente no encontrado."+RESET);
                        }
                    }

                    case 5 -> {
                        System.out.print("\n");
                        System.out.println("Ingrese ID del paciente a eliminar:");
                        int id = Integer.parseInt(scanner.nextLine());
                        pacienteService.eliminar(id);
                        System.out.println(CYAN+"✅ Paciente eliminado (baja lógica)."+RESET);
                    }
                    
                    case 6 -> {
                        System.out.print("\n");
                        System.out.println("Ingrese ID del paciente a recuperar:");
                        int id = Integer.parseInt(scanner.nextLine());
                        pacienteService.recuperar(id);
                        System.out.println(CYAN+"✅ Paciente recuperado."+RESET);
                    }

                    case 7 -> {
                        System.out.print("\n");
                        List<HistoriaClinica> historias = historiaService.getAll();
                        historias.forEach(System.out::println);
                    }
                    case 8 -> {
                        System.out.print("\n");
                        System.out.println("Ingrese el NroHistoria a actualizar:");
                        String NroHistoria = scanner.nextLine();
                        HistoriaClinica hc = historiaService.getByNroHistoria(NroHistoria);
                        if (hc != null) {
                            System.out.println("Nuevo antecedente:");
                            hc.setAntecedentes(scanner.nextLine());
                            System.out.println("Nueva medicacion:");
                            hc.setMedicacionActual(scanner.nextLine());
                            System.out.println("Nueva observacion:");
                            hc.setObservaciones(scanner.nextLine());
                            historiaService.actualizar(hc);
                            System.out.println(CYAN+"✅ Historia actualizada."+RESET);
                        } else {
                            System.out.println(RED+"⚠️ Historia no encontrada."+RESET);
                        }
                    }

                    case 0 -> System.out.println(PURPLE+"👋 Saliendo del sistema..."+RESET);
                    default -> System.out.println(RED+"⚠️ Opción inválida."+RESET);
                }

            } catch (Exception e) {
                System.out.println(RED+"⚠️ Error: " + e.getMessage()+RESET);
                opcion = -1;
            }

        } while (opcion != 0);

        scanner.close();
    }
}

*/