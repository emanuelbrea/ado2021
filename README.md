# Análisis y Diseño Orientado a Objetos 💻

## Trabajo Práctico: Bolsa de Trabajo 📚

Desarrollar una aplicacion en JAVA utilizando Programacion Orientada a Objetos, y utilizando patrones de diseño.

### Contexto general

Una importante organización de RRHH nos ha solicitado el diseño y desarrollo de un sistema de Bolsa de Trabajo.

## Patrones utilizados

### 1️⃣ Patron State

Se utiliza para indicar los distintos estados de una publicacion (activa, inactiva, cerrada), y su comportamiento segun
el mismo.

### 2️⃣ Patron Singleton

Se utiliza para manejar la conexion a la base de datos

### 3️⃣ Patron Strategy

Se utiliza para manejar el envio de notificaciones, que son enviadas al crearse una postulacion. La empresa define el
medio de notificacion al crear la publicacion (EMAIL o WHATSAPP)

### 4️⃣ Patron Adapter

Se utiliza en el modulo de envio de notificaciones (pues no esta implementado completamente) y en el modulo de
exportacion de imagenes SVG

### 5️⃣ Patron facade

Se utiliza para que el modulo de exportacion sea facil de usar por el usuario, sin saber como esta implementado.

### 6️⃣ Patron factory

Se utiliza en el modulo de exportacion de imagenes (JPG, PNG, SVG). Crea los distintos objetos segun el tipo de imagen a
exportar.

## Pruebas unitarias

Se utilizo JUnit4 para hacer pruebas sobre el funcionamiento de la aplicacion.

## Bases de datos

Se utilizo SQLite para almacenar datos basicos sobre las publicaciones y postulaciones.

## Requisitos

- SDK 15 o superior
- JUnit4
- Sqlite 
