# AnhMarvelApi
Prueba de Código Mobile PwC Digital
<h1>ADVERTENCIA: </h1>
<p>Para que la aplicación funcione y se pueda comunicar con la API, es necesario que las dos claves de Marvel, tanto la pública como la privada, estén en un archivo de recursos .xml, bajo el nombre de "MY_PUBLIC_KEY" y "MY_PRIVATE_KEY".
Como la API de Marvel usa tres parámetros -timestamp, clave pública y hash(timestamp+clave privada+clave pública), es imprescindible usarlas en cada consulta.</p>
<p>La aplicación está configurada para procesar esos parámetros y mandarlos.</p>
Para evitar el problema de seguridad que supone el hardcoding de las claves, estas han sido puestas en un archivo llamado config.xml. La aplicación las rescata en varias partes de la siguiente manera:</p>
            <p>
            getString(R.string.MY_PUBLIC_KEY),
            </p>
            <p>
            getString(R.string.MY_PRIVATE_KEY)
            </p>
<p>Este archivo config.xml no ha sido subido a GitHub para evitar que las claves queden expuestas en un repositorio público.</p>
<h2>Hacer funcionar la aplicación de nuevo</h2>
<p>Para que la aplicación funcione hay dos alternativas: crear un archivo config.xml en la carpeta de recursos y añadir las claves pública y privada propias, o recibir por privado el archivo config.xml que ya estaba siendo utilizado.</p>
