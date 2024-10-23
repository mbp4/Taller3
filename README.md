# Taller3

link al repositorio: https://github.com/mbp4/Taller3.git 
 
En el ejercicio propuesto se nos solicitaba realizar una aplicación en el entorno de desarrollo de Android Studio que proporcionase un saludo personalizado, la posibilidad de guardar el nombre del usuario, la posibilidad de cambiar el fondo de la aplicación, botones para navegar entre las pantallas y una base de datos. 

## EXPLICACIÓN 

### Pantalla de inicio

En esta parte se nos pedia crear una pantalla que diera una bienvenida dependiendo de la hora en la que se este utilizando la aplicación y un boton que nos lleve a la actividad prinicipal, su pseudocódigo sería: 

```
CLASE MainActivity HEREDA DE ComponentActivity {

    SOBREESCRIBIR función onCreate(Bundle?) {

        habilitarModoPantallaCompleta()

        CONFIGURAR Contenido {

            ESTRUCTURA_EN Scaffold {

                MODIFICADOR ocuparTodaPantalla()

                Greeting(modificador con padding y centrado)
            }
        }
    }
}

FUNCIÓN composable Greeting (modificador) {

    CONTEXTO = obtenerContextoActual()

    PREFERENCIAS = obtenerSharedPreferences("BackgroundPrefs")

    COLOR_GUARDADO = obtenerColorGuardado(preferencias, Color.BLANCO)

    COLOR_DE_FONDO = recordarEstadoConColorGuardado(COLOR_GUARDADO)

    CREAR COLUMNA {
        OCUPAR TodaPantalla
        FONDO = COLOR_DE_FONDO
        ALINEAR HORIZONTALMENTE al centro
        ORGANIZAR VERTICALMENTE al centro

        HORA_ACTUAL = obtenerHoraActual()

        SI HORA_ACTUAL < 13 ENTONCES 
            BIENVENIDA = "Buenos días"
        SI HORA_ACTUAL < 20 ENTONCES 
            BIENVENIDA = "Buenas tardes"
        SI NO
            BIENVENIDA = "Buenas noches"

        MOSTRAR_TEXTO(BIENVENIDA)

        ESPACIO(altura = 16.dp)

        CREAR_BOTON {
            AL_HACER_CLICK iniciarActividad(Inicio::class)
            MOSTRAR_TEXTO("APLICACIÓN")
        }
    }
}

FUNCIÓN obtenerColorParaCompose(color: Entero): ComposeColor {
    DEVOLVER nuevo ComposeColor (
        ROJO_DEL_COLOR / 255,
        VERDE_DEL_COLOR / 255,
        AZUL_DEL_COLOR / 255,
        ALFA_DEL_COLOR / 255
    )
}
```
En esta activity usamos la principal función para poder mostrar lo programado en las siguientes funciones. 

Las funcion de Greeting contiene unas variables las cuales son las encargadas de almacenar el color en el caso de que el usuario haya decidido cambiar el fondo de la aplicación. Si continuamos desglosando la funcion nos encontraremos un column para poder organizar los elementos de manera vertical. 

Dentro del column encontramos una variable que se encarga de coger la hora en ese momento y dependiendo de esta se mostrará un mensaje de bienvenida u otro. 

Por último, encontramos un boton que nos permite navegar a la siguiente pantalla, en este caso la inicial.

### Actividad principal 

En esta parte se nos pedia crear una pantalla que diera la opcion de que el usuario introduzca un nombre, que este se guarde, un botón que te permita navegar hasta la pantalla de configuracion y un botón que simule una tarea en segundo plano relacionada con la red su psudocódigo sería: 

```
Clase Inicio:
    Al iniciar la actividad:
        Llamar a la función 'GreetingPreview' para mostrar la pantalla de inicio.

Función lectorNombre(context):
    Declarar variables para almacenar el nombre ingresado y los nombres cargados.
    Obtener las preferencias almacenadas localmente (incluyendo el color de fondo).
    
    Crear un contenedor (columna) con las siguientes propiedades:
        Rellenar toda la pantalla.
        Establecer el color de fondo basado en las preferencias.
        Centrar el contenido horizontal y verticalmente.

    Crear un campo de texto para ingresar el nombre.
    
    Añadir un botón para guardar el nombre en SQLite:
        Al hacer clic en el botón:
            Llamar a la función 'guardarEnSQLite' con el nombre ingresado.

    Añadir un botón para cargar nombres desde SQLite:
        Al hacer clic en el botón:
            Llamar a la función 'cargarDeSQLite' para cargar los nombres y mostrar en pantalla.

    Mostrar los nombres cargados en un campo de texto.
    
    Añadir un botón que redirige a la pantalla de configuración.

Función guardarEnSQLite(context, nombre):
    Abrir la base de datos en modo escritura.
    Preparar los valores con el nombre ingresado.
    Insertar los valores en la base de datos.
    Mostrar un mensaje indicando que se ha guardado el nombre.

Función cargarDeSQLite(context, nombresState):
    Abrir la base de datos en modo lectura.
    Consultar todos los nombres guardados en la base de datos.
    Almacenar los nombres obtenidos en una lista.
    Actualizar el estado con los nombres concatenados en un mensaje de bienvenida.

Función GreetingPreview:
    Obtener el contexto actual.
    Llamar a la función 'lectorNombre' para mostrar la vista previa de la pantalla.

Función BotonConfiguracion:
    Crear un botón:
        Al hacer clic en el botón:
            Redirigir a la pantalla de configuración.
```
En esta activity usamos la principal función para poder mostrar lo programado en las siguientes funciones. 

La funcion de lectorNombre contiene unas variables las cuales son las encargadas de almacenar el color en el caso de que el usuario haya decidido cambiar el fondo de la aplicación, una variable para guardar el nombre en el caso de que este sea modificado y una lista de nombres para mostrar los almacenados en la base. Si continuamos desglosando la funcion nos encontraremos un column para poder organizar los elementos de manera vertical.

Lo siguiente que encontramos es un TextField que permite al usuario meter nombres en la base de datos.

Después encontramos una variable que almacena el nombre y haciendo uso de textView se muestra un mensaje de bienvenida con todos los usuarios registrados. En este caso usamos AndroidView para resolver problemas a la hora de utilizar el textView, esto se verá cuando se pulse en el botón de carga. 

También nos encontramos un boton que permite al usuario navegar a la pantalla de configuracion. 

### Pantalla de configuración

En esta parte se nos pedia crear una pantalla que diera al usuario la opcion de cambair el fondo de la aplicación y volver al inicio de la aplicacion, su psudocódigo sería: 

```
CLASE Config HEREDA DE ComponentActivity {

    SOBREESCRIBIR función onCreate(Bundle?) {

        super.onCreate(savedInstanceState)

        CONFIGURAR Contenido {
            MostrarPreview2()
        }
    }
}

FUNCIÓN MostrarPreview2() {
    Configur() 
}

FUNCIÓN Configur() {
    CONTEXTO = LocalContext.current

    PREFERENCIAS = contexto.obtenerSharedPreferences("BackgroundPrefs", MODE_PRIVATE)
   
    COLOR_GUARDADO = preferencias.getInt("backgroundColor", Color.BLANCO)

    COLOR_DE_FONDO = recordar { estadoMutable(getComposeColor(COLOR_GUARDADO)) }

    CREAR COLUMNA {
        OCUPAR TodaPantalla
        FONDO = COLOR_DE_FONDO
        ALINEAR HORIZONTALMENTE al centro
        ORGANIZAR VERTICALMENTE al centro

        CrearBotón {
            contexto.iniciarActividad(MainActivity::class)
        }
        MostrarTexto("INICIO")

        ESPACIO(altura = 16.dp)

        CrearBotón {
            COLOR_DE_FONDO.valor = ComposeColor.BLANCO
            guardarColorFondo(contexto, Color.BLANCO)
        }
        MostrarTexto("Blanco")

        ESPACIO(altura = 16.dp)

        CrearBotón {
            COLOR_DE_FONDO.valor = ComposeColor(0xFFaec6cf) 
            guardarColorFondo(contexto, Color.rgb(174, 198, 207))
        }
        MostrarTexto("Azul")

        ESPACIO(altura = 16.dp)

        CrearBotón {
            COLOR_DE_FONDO.valor = ComposeColor(0xFFfdfd96) 
            guardarColorFondo(contexto, Color.rgb(253, 253, 150))
        }
        MostrarTexto("Amarillo")
    }
}

FUNCIÓN guardarColorFondo(contexto: Context, color: Int) {
    PREFERENCIAS = contexto.obtenerSharedPreferences("BackgroundPrefs", MODE_PRIVATE)

    con(preferencias.editar()) {
        ponerInt("backgroundColor", color)
        aplicar() // Aplicamos los cambios
    }
}
```
En esta activity usamos la principal función para poder mostrar lo programado en las siguientes funciones. 

Las funcion de Configur contiene unas variables las cuales son las encargadas de almacenar el color en el caso de que el usuario haya decidido cambiar el fondo de la aplicación. Si continuamos desglosando la funcion nos encontraremos un column para poder organizar los elementos de manera vertical.

Lo siguiente que vemos es un botón que permite al usuario volver a la pantalla de inicio.

Después varios botones que permiten al usuario cambiar el color del fondo a la aplicación.

Por último, encontramos una función que guarda el estado del color de fondo y aplicar los cambios necesarios.

## SQL Datos

En esta parte se crea una clase en la que se crea la base: 

```
Clase SQLDatos extiende SQLiteOpenHelper:
    Definir nombre de la base de datos como "UserData.db".
    Definir versión de la base de datos como 1.

    Crear constante SQL para crear la tabla:
        La tabla tendrá los siguientes campos:
            ID: campo entero, clave primaria.
            nombre: campo de texto para almacenar el nombre del usuario.

    Crear constante SQL para eliminar la tabla:
        Eliminar la tabla si existe.

Función onCreate(db):
    Cuando la base de datos se crea por primera vez:
        Ejecutar la sentencia SQL que crea la tabla "UserEntry".

Función onUpgrade(db, oldVersion, newVersion):
    Cuando se actualiza la versión de la base de datos:
        Eliminar la tabla existente ejecutando la sentencia SQL correspondiente.
        Llamar a la función onCreate para crear una nueva tabla.

```
En esta clase se hacen unos objetos que se puedan utilizar en todas la clases para hacer accesos. 

Además de esto se encuentra dos funciones: 

 -> Un método que creará la base de datos. 

 -> Un método que actualizará la base de datos.

A parte de esto neceistaremos una clase que se encarga de crear objetos con los atributos que tendrán cada uno de los objetos que se van a meter en nuestra base de datos. Esto será nuestra clase SQLInicio.

## PROCESO DE DESARROLLO

Para la realizacion del proyecto se ha optado por usar Jetpack Compose ya que fue lo más visto durante las clases por lo que me resulto más sencillo que usar la opcion de layout, ya que aunque parece más simple necesitaba entender su funciónamiento para poder realizar la entrega de la mejor manera. 

Al usar Composable los errores que han aparecido a la hora de programar han sido en la parte del background y a la hora de guardar el nombre: 

 -> Al cambiar el fondo de la aplicación se lograba hacer en la pantalla de configuración únicamente, es decir, el color de fondo no se guardaba al navegar entre activities, para esto se       ha hecho uso de variables las cuales se encargaban de tener un estado el cual iba a cambiar dependiendo de la elección del usuario.

 -> Cuando el usuario introduzca un nombre nuevo a la base de datos, lo hace mediante el boton añadir a SQL, y para ver todos los datos guardados pulsaremos el botón de cargar desde base de datos.


