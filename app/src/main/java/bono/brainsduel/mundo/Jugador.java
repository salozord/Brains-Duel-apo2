/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_brainsDuel
 * Autor: Equipo Cupi2 2016
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package bono.brainsduel.mundo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que representa un jugador.<br>
 */
public class Jugador
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre del jugador.
     */
    private String nombreJugador;

    /**
     * Apellidos del jugador.
     */
    private String apellidosJugador;

    /**
     * Alias del jugador.
     */
    private String alias;

    /**
     * Contraseña del jugador.
     */
    private String contrasenia;

    /**
     * Ruta de la imagen del avatar del jugador.
     */
    private String imagenAvatar;

    /**
     * Cantidad de victorias del jugador.
     */
    private int cantidadVictorias;

    /**
     * Cantidad de derrotas del jugador.
     */
    private int cantidadDerrotas;

    /**
     * Lista de trofeos que tiene el jugador
     */

    private ArrayList<String> trofeos;

    /**
     * Preguntas para completar una corona
     */

    private int preguntasCorona;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo jugador. <br>
     * <b> post: </b> La lista de de pok�mon fue inicializada.
     */
    public Jugador( )
    {
        trofeos = new ArrayList<String>( );
        preguntasCorona = 0;

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del jugador.
     * @return Nombre del jugador.
     */
    public String darNombreJugador( )
    {
        return nombreJugador;
    }

    /**
     * Retorna el nombre del jugador.
     * @return Nombre del jugador.
     */
    public String darApellidosJugador( )
    {
        return apellidosJugador;
    }

    /**
     * Retorna el alias del jugador.
     * @return Alias del jugador.
     */
    public String darAlias( )
    {
        return alias;
    }

    /**
     * Retorna la contrase�a del jugador.
     * @return Contrase�a del jugador.
     */
    public String darContrasenia( )
    {
        return contrasenia;
    }

    /**
     * Retorna la ruta de la imagen del avatar del jugador.
     * @return Ruta de la imagen del jugador.
     */
    public String darImagenAvatar( )
    {
        return imagenAvatar;
    }

    /**
     * Retorna la cantidad de victorias del jugador.
     * @return Victorias del jugador.
     */
    public int darCantidadVictorias( )
    {
        return cantidadVictorias;
    }

    /**
     * Retorna la cantidad de derrotas del jugador.
     * @return Derrotas del jugador.
     */
    public int darCantidadDerrotas( )
    {
        return cantidadDerrotas;
    }

    /**
     * Modifica el nombre del jugador.
     * @param pNombre Nuevo nombre del jugador.
     */
    public void modificarNombreJugador( String pNombre )
    {
        nombreJugador = pNombre;
    }

    /**
     * Modifica los apellidos del jugador.
     * @param pApellidos Nuevos apellidos del jugador.
     */
    public void modificarApellidos( String pApellidos )
    {
        apellidosJugador = pApellidos;
    }

    /**
     * Modifica el alias del jugador.
     * @param pAlias Nuevo alias del jugador.
     */
    public void modificarAlias( String pAlias )
    {
        alias = pAlias;
    }

    /**
     * Modifica la contrase�a del jugador.
     * @param pContrasenia Nueva contrase�a del jugador.
     */
    public void modificarContrasenia( String pContrasenia )
    {
        contrasenia = pContrasenia;
    }

    /**
     * Modifica la cantidad de victorias del jugador.
     * @param pCantidadVictorias Nueva cantidad de victorias del jugador.
     */
    public void modificarCantidadVictorias( int pCantidadVictorias )
    {
        cantidadVictorias = pCantidadVictorias;
    }

    /**
     * Modifica la cantidad de derrotas del jugador.
     * @param pCantidadDerrotas Nueva cantidad de derrotas del jugador.
     */
    public void modificarCantidadDerrotas( int pCantidadDerrotas )
    {
        cantidadDerrotas = pCantidadDerrotas;
    }

    /**
     * Modifica la imagen del avatar del jugador.
     * @param pImagenAvatar Nuevo avatar del jugador.
     */
    public void modificarImagenAvatar( String pImagenAvatar )
    {
        imagenAvatar = pImagenAvatar;
    }

    /**
     * Cambia la lista de trofeos por la lista ingresada por par�metro
     * @param pTrofeos Nueva lista de trofeos
     */
    @SuppressWarnings("unchecked")
    public void cambiarTrofeos( @SuppressWarnings("rawtypes") ArrayList pTrofeos )
    {
        trofeos = pTrofeos;
    }

    /**
     * Retorna la lista de trofeos
     * @return Lista de trofeos del jugador
     */
    @SuppressWarnings("rawtypes")
    public ArrayList darTrofeos( )
    {
        return trofeos;
    }

    /**
     * Indica si el jugador gana el encuentro
     * @return True si gana el encuentro, false de lo contrario
     */
    public boolean ganaEncuentro( )
    {

        return trofeos.size( ) == 6;

    }

    /**
     * N�mero de preguntas para obtener la corona
     * @return N�mero de preguntas
     */
    public int darPreguntasCorona( )
    {
        return preguntasCorona;
    }

    /**
     * Aumenta el n�mero de preguntas para obtener un trofeo
     */
    public void aumentarPreguntasCorona( )
    {
        if( preguntasCorona == 3 )
        {
            preguntasCorona = 0;
        }
        else
        {
            preguntasCorona++;
        }

    }

    /**
     * Agrega un nuevo trofeo al jugador
     * @param pCategoria Categor�a del trofeo
     * @return Mensaje de respuesta
     */
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public String agregarTrofeo( String pCategoria )
    {
        String mensaje = null;
        if( pCategoria.equals( Duelo.CORONA ) )
        {
            String[] trofeosPosibles = { Duelo.ARTE, Duelo.ENTRETENIMIENTO, Duelo.CIENCIA, Duelo.HISTORIA, Duelo.GEOGRAFIA, Duelo.DEPORTES };
            ArrayList trofeosNoObtenidos = new ArrayList( );

            for( String trofeo : trofeosPosibles )
            {
                boolean encontrado = false;

                for( String trofeoObtenido : trofeos )
                {
                    if( trofeoObtenido.equals( trofeo ) )
                    {
                        encontrado = true;
                        break;
                    }

                }

                if( encontrado == false )
                {
                    mensaje = "�Bien! has ganado un aleatorio gracias a la Corona &&Nuevo trofeo";
                    trofeosNoObtenidos.add( trofeo );
                }
            }
            Collections.shuffle( trofeosNoObtenidos );
            trofeos.add( ( String )trofeosNoObtenidos.get( 0 ) );

        }
        else
        {
            boolean encuentra = false;
            for( int i = 0; i < trofeos.size( ) && !encuentra; i++ )
            {
                if( pCategoria.equals( trofeos.get( i ) ) )
                {
                    mensaje = "Has ganado un trofeo, de " + pCategoria + ", pero no se agregar� porque ya lo ten�as&&Nuevo trofeo";
                    encuentra = true;
                }
            }

            if( !encuentra )
            {
                mensaje = "�Bien! has ganado un trofeo de " + pCategoria + "&&Nuevo trofeo";
                trofeos.add( pCategoria );
            }
        }

        return mensaje;

    }

    /**
     * Cambia las preguntas de la corona, seg�n las ingresadas por par�metro
     * @param pNumero N�mero de preguntas
     */
    public void cambiarPreguntasCorona( int pNumero )
    {
        preguntasCorona = 3;
    }

}
