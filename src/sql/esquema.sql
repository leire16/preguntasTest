-- 1. TEMAS
CREATE TABLE temas (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre          TEXT NOT NULL,
    pregunta_inicio INTEGER,   -- rango original del PDF, útil para importar
    pregunta_fin    INTEGER
);

-- 2. PREGUNTAS
CREATE TABLE preguntas (
    id              INTEGER PRIMARY KEY AUTOINCREMENT,
    tema_id         INTEGER NOT NULL,
    numero_original INTEGER,      -- nº en el PDF (1-500), útil para depurar/re-importar
    enunciado       TEXT NOT NULL,
    FOREIGN KEY (tema_id) REFERENCES temas(id) ON DELETE CASCADE
);

-- 3. RESPUESTAS (las 4 opciones de cada pregunta)
CREATE TABLE respuestas (
    id            INTEGER PRIMARY KEY AUTOINCREMENT,
    pregunta_id   INTEGER NOT NULL,
    letra         TEXT NOT NULL CHECK (letra IN ('a','b','c','d')),
    texto         TEXT NOT NULL,
    es_correcta   INTEGER NOT NULL DEFAULT 0 CHECK (es_correcta IN (0,1)),
    FOREIGN KEY (pregunta_id) REFERENCES preguntas(id) ON DELETE CASCADE
);

-- 4. SESIONES DE TEST (cada vez que el usuario hace un test)
CREATE TABLE sesiones_test (
    id                  INTEGER PRIMARY KEY AUTOINCREMENT,
    fecha               TEXT NOT NULL DEFAULT (datetime('now')),
    tipo                TEXT NOT NULL CHECK (tipo IN ('TEMA','COMPLETO','REPASO')),
    num_preguntas       INTEGER NOT NULL,
    num_aciertos        INTEGER NOT NULL DEFAULT 0,
    num_fallos          INTEGER NOT NULL DEFAULT 0,
    duracion_segundos   INTEGER NOT NULL DEFAULT 0,
)


-- 5. DETALLE DE RESPUESTAS DADAS EN CADA SESIÓN
CREATE TABLE respuestas_usuario (
    id                      INTEGER PRIMARY KEY AUTOINCREMENT,
    sesion_id               INTEGER NOT NULL,
    pregunta_id             INTEGER NOT NULL,
    -- Respuesta elegida por el usuario (NULL si no respondió)
    respuesta_id            INTEGER,
    -- Respuesta correcta de la pregunta
    respuesta_correcta_id   INTEGER NOT NULL,
    es_correcta             INTEGER NOT NULL CHECK (es_correcta IN (0,1)),
    FOREIGN KEY (sesion_id) REFERENCES sesiones_test(id) ON DELETE CASCADE,
    FOREIGN KEY (pregunta_id) REFERENCES preguntas(id),
    FOREIGN KEY (respuesta_id) REFERENCES respuestas(id),
    FOREIGN KEY (respuesta_correcta_id) REFERENCES respuestas(id)
)


-- 6. ESTADO DE CADA PREGUNTA (para el botón "Repaso ❌")
CREATE TABLE estado_pregunta (
    pregunta_id     INTEGER PRIMARY KEY,
    veces_preguntada INTEGER NOT NULL DEFAULT 0,
    veces_fallada    INTEGER NOT NULL DEFAULT 0,
    ultima_fecha     TEXT,
    dominada         INTEGER NOT NULL DEFAULT 0,  -- 1 = ya no aparece en repaso
    FOREIGN KEY (pregunta_id) REFERENCES preguntas(id) ON DELETE CASCADE
);

CREATE TABLE sesion_tema (
    sesion_id INTEGER NOT NULL,
    tema_id   INTEGER NOT NULL,
    PRIMARY KEY (sesion_id, tema_id),
    FOREIGN KEY (sesion_id) REFERENCES sesiones_test(id) ON DELETE CASCADE
);

-- Índices útiles
CREATE INDEX idx_preguntas_tema ON preguntas(tema_id);
CREATE INDEX idx_respuestas_pregunta ON respuestas(pregunta_id);
CREATE INDEX idx_respuestas_usuario_sesion ON respuestas_usuario(sesion_id);
CREATE INDEX idx_estado_fallada ON estado_pregunta(veces_fallada);