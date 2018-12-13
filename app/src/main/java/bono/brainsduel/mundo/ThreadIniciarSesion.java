
package bono.brainsduel.mundo;

import bono.brainsduel.interfaz.*;

/**
 * Esta clase implementa lo que se debe hacer en un hilo de ejecucion cuando se quiere conectar al cliente con el servidor.
 */
public class ThreadIniciarSesion extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Referencia a la batalla.
     */
    private Duelo duelo;

    /**
     * Ventana principal de la aplicacion
     */
    private DueloActivity interfaz;

    /**
     * Alias que utilizara el jugador.
     */
    private String alias;

    /**
     * Password del jugador.
     */
    private String password;

    /**
     * Direccion para localizar al servidor.
     */
    private String servidor;

    /**
     * Puerto a traves del cual se realizara la conexion con el servidor.
     */
    private int puerto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nuevo hilo y lo deja listo para conectarse al servidor.
     * @param pBatalla Batalla actual. pBatalla != null.
     * @param pDireccionServidor Direccion para localizar al servidor. pDireccionServidor != null.
     * @param pPuertoServidor Puerto a traves del cual se realizara la conexion con el servidor. pPuertoServidor != null && pPuertoServidor != "".
     * @param pAlias Alias que utilizara el jugador. pAlias != null && pAlias != "".
     * @param pPassword Contrasena que utilizara el jugador. pPassword != null && pPassword != "".
     */
    public ThreadIniciarSesion( Duelo pBatalla, DueloActivity interfaz, String pDireccionServidor, int pPuertoServidor, String pAlias, String pPassword )
    {
        duelo = pBatalla;
        password = pPassword;
        this.interfaz = interfaz;
        servidor = pDireccionServidor;
        puerto = pPuertoServidor;
        alias = pAlias;
    }

    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecucion del hilo que realiza la conexion con el servidor, dispara la seleccion de los pokemon e incializa la batalla.<br>
     * Cuando se tiene la conexion y la informacion de la batalla entonces se actualiza la interfaz.
     */
    public void run( )
    {
        try
        {
            duelo.iniciarSesion( servidor, puerto, alias, password );
            interfaz.act();
            duelo.esperarJugada( );

        }
        catch( Exception e )
        {
            interfaz.mostrarError( e );
        }

    }
}
