
package bono.brainsduel.mundo;

import bono.brainsduel.interfaz.*;

/**
 * Hilo que verifica el cambio de estado de la aplicacion
 */
public class ThreadVerificarCambioEstado extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Duelo actual
     */
    private Duelo duelo;

    /**
     * Clase principal de la interfaz
     */
    private DueloActivity principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nuevo hilo y lo deja listo para conectarse al servidor.
     * @param pDuelo Batalla actual. pBatalla != null.
     */
    public ThreadVerificarCambioEstado( Duelo pDuelo, DueloActivity pPrincipal)
    {
        duelo = pDuelo;
        principal = pPrincipal;
    }

    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecucion del hilo que verifica los cambios en el estado de juego.<br>
     */
    public void run( )
    {
        while( duelo.darEstadoJuego( ) != 0 )
        {
            int estadoActual = duelo.darEstadoJuego( );
            int trofeosActual = duelo.darOponente( ).darTrofeos( ).size( );

            try
            {
                Thread.sleep( 1000 );
            }
            catch( InterruptedException e )
            {
                e.printStackTrace( );
            }

            int estadoNuevo = duelo.darEstadoJuego( );
            int trofeosNuevo = duelo.darOponente( ).darTrofeos( ).size( );

            if( estadoNuevo != estadoActual || trofeosNuevo != trofeosActual )
            {
                principal.act();
            }

            if( duelo.darEstadoJuego( ) == Duelo.ESTADO_RETO )
            {
                principal.modoPregunta( );
            }

            String mensaje = duelo.darMensaje( );
            while( mensaje != null )
            {
                if( !mensaje.contains( Duelo.JUEGO_TERMINADO ) )
                {
                    String[] n = mensaje.split( "&&" );
                    ThreadMostrarMensaje t= new ThreadMostrarMensaje( duelo, principal, n[0], n[1] );
                    t.start( );
                    mensaje = duelo.darMensaje( );
                }
                else
                {
                    //Se termina el juego.
                }
            }

        }

    }
}
