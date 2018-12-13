
package bono.brainsduel.mundo;

import bono.brainsduel.interfaz.*;

import java.io.IOException;

/**
 * Esta clase implementa lo que se debe hacer en un hilo de ejecucion cuando se quiere conectar al cliente con el servidor.
 */
public class ThreadRegistrar extends Thread
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
    private DueloActivity principal;

    /**
     * Nombre que utilizara el jugador.
     */
    private String nombre;

    /**
     * Apellidos que utilizara el jugador.
     */
    private String apellidos;

    /**
     * Alias que utilizara el jugador.
     */
    private String alias;

    /**
     * Direccion para localizar al servidor.
     */
    private String servidor;

    /**
     * Puerto a traves del cual se realizara la conexion con el servidor.
     */
    private int puerto;

    /**
     * Password del jugador.
     */
    private String password;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nuevo hilo y lo deja listo para conectarse al servidor.
     * @param interfaz Ventana principal de la aplicacion. pInterfaz != null.
     * @param pNombreJugador Nombre que utilizara el jugador. pNombreJugador != null.
     * @param pApellidosJugador Apellidos que utilizara el jugador. pApellidosJugador != null.
     * @param pDireccionServidor Direccion para localizar al servidor. pDireccionServidor != null.
     * @param pPuertoServidor Puerto a traves del cual se realizara la conexion con el servidor. pPuertoServidor != null.
     * @param pAlias Alias que utilizara el jugador. pAlias != null.
     * @param pPassword Contrasena que utilizara el jugador. pPassword != null && pPassword != "".
     */
    public ThreadRegistrar( Duelo pBatalla, DueloActivity interfaz, String pDireccionServidor, int pPuertoServidor, String pAlias, String pNombreJugador, String pApellidosJugador, String pPassword )
    {
        duelo = pBatalla;
        principal = interfaz;
        servidor = pDireccionServidor;
        puerto = pPuertoServidor;
        alias = pAlias;
        nombre = pNombreJugador;
        apellidos = pApellidosJugador;
        password = pPassword;
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
            duelo.registrar( servidor, puerto, alias, nombre, apellidos, password );
            principal.act();
            duelo.esperarJugada( );

        }
        catch( BrainsDuelException | IOException | InterruptedException e )
        {
            principal.mostrarError( e );
        }

    }
}
