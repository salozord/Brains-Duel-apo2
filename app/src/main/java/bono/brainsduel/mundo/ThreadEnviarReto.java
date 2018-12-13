
package bono.brainsduel.mundo;

/**
 * Hilo que verifica el cambio de estado de la aplicacion
 */
public class ThreadEnviarReto extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Duelo actual
     */
    private Duelo duelo;
    

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nuevo hilo y lo deja listo para conectarse al servidor.
     * @param pDuelo Batalla actual. pBatalla != null.
     */
    public ThreadEnviarReto( Duelo pDuelo )
    {
        duelo = pDuelo;
    }

    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecucion del hilo que verifica los cambios en el estado de juego.<br>
     */
    public void run( )
    {
        duelo.enviarMensajeReto();

    }
}
