# Microservicios Producto
Curso de Udemy - Microservicios con Spring Boot y Spring Cloud Netflix Eureka

# Configurando múltiples instancias
Realizamos esta configuración en el **application.properties** solo en los microservicios que queremos escalar
de forma automática, donde queremos tener varias instancias de forma automática

```
- Asignando puerto de forma aleatoria, lo asigna la aplicación
server.port=${PORT:0}

- Generando un random para eureka instance
eureka.instance.instance-id=${spring.application.name}:${spring.application.instance_id:${random.value}}

- Esta configuración es opcional, siempre y cuando el servidor de eureka esté en el mismo servidor que los clientes
eureka.client.service-url.defaultZone=http://127.0.0.1:8761/eureka
```
