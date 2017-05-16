# PoliTweetsCL
Observatorio de politica en Twitter 

## Instalación

Descargar e instalar todos los componentes siguiendo el [tutorial](https://docs.google.com/document/u/1/d/1eHUXDvpaAZhkgGLIUwHyHlAmpD5M-fd7syQIu58ez9E/pub) 

Clonar repositorio :
```
git clone https://github.com/TBD-2017-1/PoliTweets.git
```

Instalar la base de datos en MySQL:
Ingresar a Mysql con el comando: 
```
$ mysql -u root -p
```
Instalar la BD descargada desde el git con el comando: 
```
$ SOURCE <path>/src/SQL/Politweets-all.sql;
```
donde `<path>` es la direccion donde lo instalaron en su computador


Comprobar la base de datos con los comandos:
```
$ USE PoliTweets;
$ SHOW TABLES;
```

Correr el comando: 
``
$ asadmin start-domain
``

Ingresar a: `localhost:4848`

Seleccionar pestaña jdbc >> jdbc connection pools

Crear una nueva conection pool de nombre `jbdc_politweets_pool`

En sus propiedades adicionales (abajo) ingresar
Pasword: (tu password)
User: (tu user)(en mi caso puse root)
URL: partidospartidos

Click en Ping (arriba) para comprobar

Seleccionar pestaña jdbc resources

Crear nuevo

Ingresar en JNDI Name jdbc/politweets

Referenciar al pool de politweets
En la carpeta donde descargaron el git del backend hacer en la terminal:
```
$ gradle war
```

Luego en cambiar la dirección a /build/libs
```
$ cd build/libs/
```

Ejecutar comando:
```
$ asadmin deploy backend.war	
```
o como sea que se llame el archivo .war que aparece en esa carpeta


Ir a glassfish a la sección Aplications y debería aparecer una nueva base de datos backend


Dar click en el botón “launch” y luego en alguna de las dos direcciones que aparecen en la nueva página que se abrió


Al final de la URL de esa página agregar `/partidos`

## USO
Para usar el servicio es necesario ocupar el subproyecto [FrontEnd](https://github.com/TBD-2017-1/FrontEnd.git)



