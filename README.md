# 📦 RETA_2526: Gestión y Localización del Material del Taller de Informática

El objetivo de este trabajo grupal, será crear con los conocimientos adquiridos en cada módulo a lo largo del curso, una página web y aplicación de escritorio, con su correspondiente base de datos, que permitan a los usuarios hacer un seguimiento preciso del inventario del taller de informática del instituto, el proyecto se desplegará en dos máquinas virtuales de linux que deberán ser previamente preparadas para que puedan conectarse la una con la otra.

Se trabajará aplicando la metodología scrum, es decir, se harán reuniones diarias para comentar tonto el progreso como las complicaciones en el proyecto, y para asignar a cada componente del grupo tareas o metas que deberán cumplir en un plazo determinado.

## 🧭 Descripción de Las Partes del Proyecto

El sistema permitirá supervisar la entrada, salida, estado y existencias del inventario, para ello habrá que trabajar en los siguientes apartados:

**Aplicación de Escritorio: 🖥️** 

Se desarrollará una aplicación de escritorio en Java con interfaz gráfica Swing y NetBeans. La aplicación se conectará a MySQL en MV1 mediante JDBC, con patrón Singleton para la conexión y DAO para el acceso a datos. La programación será orientada a objetos. Se utilizarán hilos de ejecución para tareas paralelas cuando sea necesario.

Perfiles de usuario:

- Administrador: acceso completo (alta, baja, modificación, consulta, gestión de usuarios, todos los informes).
- Profesor: consulta del inventario, búsqueda y filtrado, localización visual (acceso al sitio web), generación de listados.

Módulos funcionales:

- Módulo de Gestión del Inventario (Administrador): alta, modificación, baja, importación/exportación CSV o Excel.
- Módulo de Consulta y Localización (Profesor): búsqueda por nombre/categoría/estado/código, botón de localización que abre el sitio web.
- Módulo de Informes: listado completo, por categoría/estado, por armario/balda. Exportación en PDF o Excel.

La aplicación informará en todo momento mediante cuadros de diálogo. El código estará documentado con JavaDoc.

**Página Web: 🌐** 

Se desarrollará un sitio web estático (HTML5, CSS3 y JavaScript) alojado en MV2 que muestre de forma gráfica la distribución física del taller, accesible desde la aplicación al pulsar el botón de localización.

El sitio web incluirá:

- Plano o mapa visual del taller con la distribución de armarios y baldas.
- Imágenes o fotografías reales de las zonas del taller.
- Resaltado visual de la ubicación concreta del componente buscado.
- Información básica del componente seleccionado (nombre, descripción, estado).
- Diseño responsive adaptable a diferentes dispositivos.
- Navegación clara entre zonas del taller.
- (Opcional) Plantillas XSLT para generar parte del contenido en HTML o exportar datos en CSV.

**Máquinas Virtuales: 💽**

Toda la infraestructura se desplegará sobre máquinas virtuales con Ubuntu Server gestionadas desde VirtualBox en el laboratorio. Esta arquitectura permite configurar y administrar los servidores de forma completa, comprendiendo qué ocurre a nivel de SO, red y servicios.

Arquitectura a desplegar:

- MV1 «Servidor de Datos»: Ubuntu Server con MySQL instalado y configurado manualmente. Firewall ufw configurado para permitir únicamente conexiones al puerto 3306 desde la IP de MV2 y las máquinas del laboratorio. La BD no estará expuesta directamente a otras redes.
- MV2 «Servidor de Servicios»: Ubuntu Server con servidor web (Apache o Nginx) y servicio SFTP (OpenSSH). Puertos 80 (web) y 22 (SSH/SFTP) accesibles desde la red del laboratorio. Firewall ufw configurado para restringir el resto de puertos.
- Configuración de red: red interna host-only o NAT con reenvío de puertos, de forma que MV1 solo sea alcanzable desde MV2 y las máquinas del laboratorio, replicando el patrón de arquitectura de dos capas.
- Gestión de usuarios del sistema: se crearán usuarios específicos en Ubuntu para cada servicio (usuario MySQL, usuario SFTP restringido), aplicando el principio de mínimo privilegio.

Al finalizar, cada MV se exportará en formato .ova, se subirá al espacio de almacenamiento del canal de Teams del equipo y se enlazará desde el repositorio GitHub.

**Base de Datos: 🗃️** 

Se diseñará e implementará una base de datos relacional MySQL alojada en MV1 (Ubuntu Server) que recoja toda la información del material del taller. El diseño deberá estar normalizado hasta 3FN, documentado con diagrama E/R y diagrama relacional, e incluir restricciones de integridad referencial. Las tablas deberán contemplar al menos las siguientes categorías:

- PC’s para prácticas (portátiles, PC)
- Componentes hardware (placas base, memorias RAM, procesadores, discos duros, chasis/torres)
- Equipos de red (switches, routers, puntos de acceso, patch panels)
 Cableado estructurado (rosetas, cable UTP/FTP, fibra óptica)
- Herramientas de soldadura y herramientas generales (multímetros, crimpadoras, destornilladores)
- Material fungible (cable UTP, alcohol isopropílico, pasta térmica, consumibles)

Cada elemento registrará como mínimo: ID, nombre, descripción, categoría/subcategoría, estado (operativo/averiado/en reparación/obsoleto), cantidad, código de armario y balda, fecha de alta y observaciones.

Se implementarán uno o varios disparadores (triggers) que automaticen operaciones relevantes del inventario.

## 👥 Integrantes del Grupo

En este proyecto trabajan:

*   **Hugo Andrea Olmo 👤**
*   **Gleb Nesterov 👤**
*   **Iván Gómez Morante 👤**
*   **David Chisca Popescu 👤**
*   **Benjamín Rivero Ubarrieta 👤**

## 🛠️ Tecnologías Utilizadas 

*   **Página Web:** HTML5, CSS3, JavaScript (Visual studio code).
*   **Aplicación de Escritorio:** Java (Netbeans).
*   **Base de Datos:** MySQL workbench.
*   **Configuración y gestión de MVs** Oracle VirtualBox.
*   **Control de Versiones:** GitHub, GitHub desktop.

