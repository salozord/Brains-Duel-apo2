
package bono.brainsduel.mundo;

/**
 * Excepcion que se lanza para indicar un problema en la batalla.
 */
public class BrainsDuelException extends Exception
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializacion.
     */
    private static final long serialVersionUID = 113229143589381951L;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva excepcion de este tipo con el mensaje indicado.
     * @param pMensaje Mensaje que describe la causa de la excepcion. pMensaje != null && pMensaje != "".
     */
    public BrainsDuelException( String pMensaje )
    {
        super( pMensaje );
    }
}
