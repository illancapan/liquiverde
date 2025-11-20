# LiquiVerde Backend

Plataforma de retail inteligente que ayuda a los consumidores a optimizar sus compras, considerando ahorro económico y sostenibilidad.

Este repositorio contiene el backend desarrollado en **Java Spring Boot 17**, con **PostgreSQL** como base de datos y **Docker** para facilitar la instalación.

---

## 📌 Tecnologías utilizadas

- **Backend:** Java 17, Spring Boot 3+, Gradle
- **Base de datos:** PostgreSQL 15
- **Gestión de migraciones:** Liquibase
- **Docker:** docker-compose para levantar contenedores
- **Testing:** JUnit 5, Mockito

---

## 🔧 Requisitos previos

- Docker y Docker Compose
- Git

> No es necesario tener Java ni Gradle instalados si se utiliza Docker.

---

## 🚀 Levantar el proyecto con Docker

Clonar el repositorio:

```bash
git clone https://github.com/illancapan/liquiverde.git
cd liquiverde
```
Levantar la base de datos y backend:

```bash
docker-compose up --build
```
Esto hará lo siguiente:

Levantar un contenedor PostgreSQL con la base de datos liquiverde_db.

Aplicar migraciones y cargar datos de ejemplo con Liquibase.
Levantar el backend en http://localhost:8080.

Verificar que los contenedores estén corriendo:

```bash
docker ps
```
No es necesario que se cree la base de datos manualmente, todo se inicializa automáticamente.

## 🌐 API Endpoints
Gestión de Productos
Escanear producto por código de barras
```bash
GET /productos/scan/{barcode}
```
Ejemplo con datos de prueba:
```bash
curl http://localhost:8080/productos/scan/1111111111111
```

Respuesta JSON:
```bash
{
"id": 1,
"nombre": "Jabón ecológico",
"marca": "EcoPlus",
"codigoBarras": "1111111111111",
"precio": 4.99,
"puntajeSostenibilidad": 8.3
}

```
Buscar productos por nombre
GET /productos/buscar?nombre=Botella

Ejemplo:
```bash
curl "http://localhost:8080/productos/buscar?nombre=Botella"
```


Respuesta JSON:
```bash
[
    {
        "id": 2,
        "nombre": "Botella reutilizable",
        "marca": "GreenLife",
        "codigoBarras": "2222222222222",
        "precio": 9.99,
        "puntajeSostenibilidad": 9.1
    }
]
```

Optimización de listas de compras

Optimizar lista de compras
```bash
POST /listas/optimizar
```

Request JSON de ejemplo (usando IDs de productos del changelog):
```bash
{
    "productos": [1, 2, 3, 4],
    "presupuestoMax": 20
}
```

Respuesta JSON de ejemplo:
```bash
{
  "listaOptimizada": [
    {"id": 2, "nombre": "Botella reutilizable", "precio": 9.99, "puntajeSostenibilidad": 9.1},
    {"id": 1, "nombre": "Jabón ecológico", "precio": 4.99, "puntajeSostenibilidad": 8.3}
  ],
  "ahorroTotal": 5.02,
  "impactoAmbiental": 17.4
}
```
Los valores de ahorroTotal e impactoAmbiental se calculan según tu algoritmo de optimización y scoring de sostenibilidad.

## 📊 Algoritmos implementados

Mochila multi-objetivo: Optimiza la selección de productos según presupuesto, impacto ambiental y social.

Scoring de sostenibilidad: Cada producto recibe un puntaje basado en ahorro, impacto ambiental y social.

## 📂 Dataset de ejemplo

Los productos de prueba están definidos en los changelogs de Liquibase (db/changelog/change/).

Incluye tablas de productos con atributos económicos, ambientales y sociales.

## 🧪 Testing

Pruebas unitarias con JUnit 5 y Mockito:
```bash
ProductoServiceImplTest
ProductoControllerTest
```

Ejecutar tests:
```bash
./gradlew test
```
## 🛠 Docker y Liquibase

El proyecto utiliza multi-stage Docker build para generar un JAR optimizado.
≈
PostgreSQL se inicializa con datos de ejemplo usando volúmenes persistentes.

Liquibase aplica todos los changelogs automáticamente al levantar el backend.

## 🤖 Uso de IA

El 100% del código y la estructura del backend fueron desarrollados por mí. ChatGPT proporcionó asistencia puntual para redactar el README, organizar ejemplos de endpoints y documentar los algoritmos de manera clara, pero todas las decisiones de diseño, implementación y optimización fueron realizadas con mis indicaciones y supervisión directa.