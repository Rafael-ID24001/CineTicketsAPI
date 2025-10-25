
---

# Configuración Para Desarrollo Local

### Configurar SDK
<img src="resources/project-structure.png" alt="Config. Estructura del Proyecto" width="300"/>
<img src="resources/java17-proyecto.png" alt="Config. SDK de proyecto" width="700"/>
<img src="resources/platform-sdk.png" alt="Config. Platform SDK" width="700"/>

<br>
<br>

### Configurar Gradle
<img src="resources/config-gradle.png" alt="Config. Gradle Settings" width="300"/>
<img src="resources/gradle-sdk.png" alt="Config. Gradle" width="700"/>

---

# Configuración Conexion DB oracle

Agregar el SID configurado en la base de datos oracle, el usuario y contraseña.
En la ruta: ``` src/main/resources/application.properties```
```
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/{TU_SID}
spring.datasource.username={TU_USUARIO}
spring.datasource.password={TU_CONTRASENIA}
```