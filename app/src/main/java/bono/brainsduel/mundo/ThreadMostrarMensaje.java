package bono.brainsduel.mundo;

import bono.brainsduel.interfaz.*;

/**
 * Se encuentra de mostrar un mensaje en la interfaz
 * @author Cupi2
 *
 */
public class ThreadMostrarMensaje extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal de la interfaz
     */
    private DueloActivity principal;
    
    /**
     * Parte 1 del mensaje
     */
    private String parte1;
    
    /**
     * Parte 2 del mensaje
     */
    private String parte2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nuevo hilo y lo deja listo para conectarse al servidor.
     * @param pDuelo Batalla actual. pBatalla != null.
     * @param pParte1 Parte 1 del mensaje
     * @param pParte2  Parte 2 del mensaje
     */
    public ThreadMostrarMensaje( Duelo pDuelo, DueloActivity pPrincipal, String pParte1, String pParte2 )
    {
        principal = pPrincipal;
        parte1 = pParte1;
        parte2 = pParte2;
    }

    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecucion del hilo que verifica los cambios en el estado de juego.<br>
     */
    public void run( )
    {
        principal.mostrarMensaje( parte1, parte2 );
    }
}
