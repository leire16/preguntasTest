CREATE TABLE sesion_tema (
    sesion_id INTEGER NOT NULL,
    tema_id   INTEGER NOT NULL,
    PRIMARY KEY (sesion_id, tema_id),
    FOREIGN KEY (sesion_id) REFERENCES sesiones_test(id) ON DELETE CASCADE
);
 
 
 quitar columna tema_id de tabla sesion_test