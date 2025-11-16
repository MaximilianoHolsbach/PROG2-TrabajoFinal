USE clinica53;

-- INSERTAR LAS 10 HISTORIAS CLÍNICAS

INSERT INTO HistoriaClinica (NroHistoria, GrupoSanguineo, Antecedentes, MedicacionActual, Observaciones)
VALUES
('30123456', 1, 'Hipertensión Arterial', 'Losartán 50mg', 'Control de presión arterial bimensual.'),
('32987654', 2, 'Asma bronquial', 'Salbutamol SOS', 'Evitar alérgenos conocidos.'),
('28456789', 3, 'Diabetes Mellitus Tipo 2', 'Metformina 850mg c/12hs', 'Requiere control de glucemia y dieta estricta.'),
('35123123', 8, 'Ninguno de relevancia', 'Ninguna', 'Paciente sano, chequeo anual.'),
('38765432', 0, 'Alergia a la penicilina', 'Loratadina ocasional', 'Antecedente de anafilaxia. Marcar en rojo.'),
('40123456', 5, 'Hipotiroidismo', 'Levotiroxina 75mcg', 'Control de TSH semestral.'),
('25123987', 6, 'Cirugía de apendicitis (2015)', 'Ninguna', 'Buena recuperación postquirúrgica.'),
('42789012', 2, 'Miopía', 'Ninguna', 'Usa lentes de contacto.'),
('33456789', 4, 'Gastritis crónica', 'Omeprazol 20mg ayunas', 'Evitar comidas irritantes y alcohol.'),
('36987123', 3, 'Ansiedad generalizada', 'Sertralina 50mg', 'En seguimiento por psiquiatría.'); 

-- INSERTAR LOS 10 PACIENTES

INSERT INTO pacientes (Nombre, Apellido, DNI, FechaNacimiento, ID_HistoriaClinica)
VALUES
('Juan', 'Pérez', '30123456', '1980-05-15', 1),
('María', 'Gómez', '32987654', '1985-11-20', 2),
('Carlos', 'Rodríguez', '28456789', '1975-02-10', 3),
('Ana', 'López', '35123123', '1990-07-30', 4),
('Luis', 'Martínez', '38765432', '1995-01-05', 5),
('Laura', 'Fernández', '40123456', '1998-03-12', 6),
('Miguel', 'Sánchez', '25123987', '1970-09-18', 7),
('Sofía', 'Díaz', '42789012', '2000-12-01', 8),
('Diego', 'Torres', '33456789', '1988-06-25', 9),
('Elena', 'Ruiz', '36987123', '1992-04-14', 10);

