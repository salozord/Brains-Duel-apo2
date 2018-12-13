
package bono.brainsduel.mundo;

import bono.brainsduel.interfaz.*;

/**
 * Esta clase implementa lo que se debe hacer en un hilo de ejecucion cuando se quiere esperar la jugada del oponente.
 */
public class ThreadEsperarJugada extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Referencia a la batalla.
     */
    private Duelo duelo;

    /**
     * Ventana principal de la aplicacion.
     */
    private DueloActivity principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nuevo hilo y lo deja listo para esperar la jugada.
     * @param pBatalla Referencia de la batalla. pBatalla != null.
     */
    public ThreadEsperarJugada( Duelo pBatalla, DueloActivity pInterfaz )
    {
        super( );
        duelo = pBatalla;
        principal = pInterfaz;
    }

    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecucion del hilo que espera la jugada del oponente. <br>
     * Cuando se tiene informacion sobre la jugada del oponente entonces se actualiza la interfaz.<br>
     * Si el juego debe terminar entonces muestra quien fue el ganador y termina el encuentro y la conexion al servidor.
     */
    public void run( )
    {
        try
        {
            duelo.esperarJugada( );
        }

        catch( Exception e )
        {
            principal.mostrarError( e );
        }
    }
}
