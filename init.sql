CREATE TABLE categorias (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE productos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    fecha_de_agregado DATE,
    stock INTEGER,
    precio_lista DECIMAL(10, 2),
    precio_final DECIMAL(10, 2),
    categoria_id INTEGER REFERENCES categorias(id),
    importado BOOLEAN
);

CREATE TABLE imagenes (
    id SERIAL PRIMARY KEY,
    url TEXT NOT NULL,
    producto_id BIGINT REFERENCES productos(id) ON DELETE CASCADE
);

CREATE TABLE ticket (
    id SERIAL PRIMARY KEY,
    referencia_de_pago VARCHAR(255) UNIQUE,
    usuario_id BIGINT REFERENCES usuarios(id),
    direccion TEXT,
    ciudad VARCHAR(255),
    provincia VARCHAR(255),
    codigo_postal VARCHAR(20),
    nota TEXT,
    metodo_pago VARCHAR(50),
    estado VARCHAR(20) NOT NULL,
    fecha_realizado TIMESTAMP NOT NULL,
    precio_total DECIMAL(10, 2)
);

CREATE TABLE pedidos (
    id SERIAL PRIMARY KEY,
    producto_id BIGINT,
    producto_nombre VARCHAR(255),
    precio_unitario DECIMAL(10, 2),
    cantidad INTEGER,
    ticket_id BIGINT REFERENCES ticket(id) ON DELETE CASCADE
);