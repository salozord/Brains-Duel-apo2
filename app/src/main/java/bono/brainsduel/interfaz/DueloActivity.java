package bono.brainsduel.interfaz;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.ResponseCache;
import java.util.ArrayList;

import bono.brainsduel.R;
import bono.brainsduel.mundo.BrainsDuelException;
import bono.brainsduel.mundo.Duelo;
import bono.brainsduel.mundo.Pregunta;
import bono.brainsduel.mundo.ThreadEnviarReto;
import bono.brainsduel.mundo.ThreadEsperarJugada;
import bono.brainsduel.mundo.ThreadIniciarSesion;
import bono.brainsduel.mundo.ThreadRegistrar;
import bono.brainsduel.mundo.ThreadVerificarCambioEstado;

import static android.app.Activity.RESULT_OK;
import static bono.brainsduel.interfaz.LoginActivity.SESION;
import static bono.brainsduel.interfaz.LoginActivity.VALORES;
import static bono.brainsduel.mundo.Duelo.PREGUNTA;
import static bono.brainsduel.mundo.Duelo.RESPUESTA;

public class DueloActivity extends AppCompatActivity
{

    public static final String OPCIONES = "OPCIONES";
    public static final int RTA = 5;
    public static final String TITULO = "TITULO";
    private Intent renovador;
    private Duelo duelo;
    private boolean entro;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        try
        {
            duelo = new Duelo();
        }
        catch (BrainsDuelException e)
        {
            mostrarError(e);
        }
        entro = false;
        ThreadVerificarCambioEstado t = new ThreadVerificarCambioEstado( duelo, this );
        t.start();

        setContentView(R.layout.activity_duelo);

        inicializarInterfaz();

        Intent intento = getIntent();
        int valor = intento.getIntExtra(SESION, -1);
        String[] valores = intento.getStringArrayExtra(VALORES);

        if(valor == 0)
        {
            ThreadIniciarSesion x = new ThreadIniciarSesion(duelo, this, valores[0], Integer.parseInt(valores[1]), valores[2], valores[3]);
            x.start();
        }
        else if(valor == 1)
        {
            ThreadRegistrar y = new ThreadRegistrar(duelo, this, valores[0], Integer.parseInt(valores[1]), valores[2], valores[3], valores[4], valores[5]);
            y.start();
        }
    }

    private void inicializarInterfaz()
    {
        inicializarTrofeosYCoronas();

        TextView textoActual = findViewById(R.id.txtJugadorActual);
        TextView textoOponente = findViewById(R.id.txtOponente);
        textoActual.setText("");
        textoOponente.setText("");

        if( duelo.darEstadoJuego( ) == Duelo.ESPERANDO_OPONENTE )
        {
            actualizarImagen( Duelo.ESPERANDO_OPONENTE );
        }
        else if( duelo.darEstadoJuego( ) == Duelo.ESPERANDO_LOCAL )
        {
            actualizarImagen( Duelo.ESPERANDO_LOCAL );
        }
        else if( duelo.darEstadoJuego( ) == Duelo.ESPERANDO_RESPUESTA )
        {
            actualizarImagen( Duelo.ESPERANDO_RESPUESTA );
        }
        else if(duelo.darEstadoJuego( ) == Duelo.ESTADO_RETO)
        {
            actualizarImagen( Duelo.ESTADO_RETO );
        }
        else if(duelo.darEstadoJuego( ) == Duelo.ESTADO_TERMINADO)
        {
            actualizarImagen( Duelo.ESTADO_TERMINADO );
        }

        Button botonGirar = findViewById(R.id.btnGirar);
        TextView textoCate = findViewById(R.id.txtCategoria);
        textoCate.setText("......");
        botonGirar.setEnabled(false);
        deshabilitarReto( );
    }

    private void setText(final TextView text,final String value)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }
    private void setEnabled(final Button button,final boolean value)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.setEnabled(value);
            }
        });
    }
    private void setImageResource(final ImageView image,final int value)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                image.setImageResource(value);
            }
        });
    }

    public void actualizarInterfaz( )
    {
        String nombreJugador = duelo.darJugador( ).darAlias( );
        String nombreOponente = duelo.darOponente( ).darAlias( );
        String victoriasJugador = " (" + duelo.darJugador( ).darCantidadVictorias( ) + " - " + duelo.darJugador( ).darCantidadDerrotas( ) + ")";
        String victoriasOponente = " (" + duelo.darOponente( ).darCantidadVictorias( ) + " - " + duelo.darOponente( ).darCantidadDerrotas( ) + ")";

        TextView textoActual = findViewById(R.id.txtJugadorActual);
        TextView textoOponente = findViewById(R.id.txtOponente);

        setText(textoActual,nombreJugador + victoriasJugador);
        setText(textoOponente, nombreOponente + victoriasOponente);

        //textoActual.setText(nombreJugador + victoriasJugador);
        //textoOponente.setText(nombreOponente + victoriasOponente);

        if( duelo.darEstadoJuego( ) == Duelo.ESPERANDO_OPONENTE )
        {
            actualizarImagen( Duelo.ESPERANDO_OPONENTE );
        }
        else if( duelo.darEstadoJuego( ) == Duelo.ESPERANDO_LOCAL )
        {
            actualizarImagen( Duelo.ESPERANDO_LOCAL );
        }
        else if( duelo.darEstadoJuego( ) == Duelo.ESPERANDO_RESPUESTA )
        {
            actualizarImagen( Duelo.ESPERANDO_RESPUESTA );
        }
        else if(duelo.darEstadoJuego( ) == Duelo.ESTADO_RETO)
        {
            actualizarImagen( Duelo.ESTADO_RETO );
        }
        else if(duelo.darEstadoJuego( ) == Duelo.ESTADO_TERMINADO)
        {
            actualizarImagen( Duelo.ESTADO_TERMINADO );
        }

        int pEstado = duelo.darEstadoJuego( );

        Button botonGirar = findViewById(R.id.btnGirar);
        TextView textoCate = findViewById(R.id.txtCategoria);
        //textoCate.setText("......");
        setText(textoCate, "......");

        if(pEstado == Duelo.ESPERANDO_OPONENTE || pEstado == Duelo.ESPERANDO_RESPUESTA)
        {
            //botonGirar.setEnabled(false);
            setEnabled(botonGirar, false);
        }
        else
        {
            //botonGirar.setEnabled(true);
            setEnabled(botonGirar, true);
        }


        if( pEstado == Duelo.ESPERANDO_OPONENTE || pEstado == Duelo.ESPERANDO_RESPUESTA ||  pEstado == Duelo.ESTADO_RETO )
        {
            deshabilitarReto( );
        }
        else
        {
            habilitarReto( );
        }

        actualizarCoronas( duelo.darJugador( ).darPreguntasCorona( ) );
        actualizarTrofeos( duelo.darJugador( ).darTrofeos( ), duelo.darOponente( ).darTrofeos( ) );
    }

    private void inicializarTrofeosYCoronas()
    {
        ImageView actDepo = findViewById(R.id.trofeoDepoActual);
        ImageView actArte = findViewById(R.id.trofeoArteActual);
        ImageView actEntre = findViewById(R.id.trofeoEntreActual);
        ImageView actCienc = findViewById(R.id.trofeoCiencActual);
        ImageView actGeo = findViewById(R.id.trofeoGeoActual);
        ImageView actHist = findViewById(R.id.trofeoHistActual);

        ImageView opoDepo = findViewById(R.id.trofeoDepoOpo);
        ImageView opoArte = findViewById(R.id.trofeoArteOpo);
        ImageView opoEntre = findViewById(R.id.trofeoEntreOpo);
        ImageView opoCienc = findViewById(R.id.trofeoCiencOpo);
        ImageView opoGeo = findViewById(R.id.trofeoGeoOpo);
        ImageView opoHist = findViewById(R.id.trofeoHistOpo);

        actDepo.setImageResource(R.drawable.gris);
        actArte.setImageResource(R.drawable.gris);
        actEntre.setImageResource(R.drawable.gris);
        actCienc.setImageResource(R.drawable.gris);
        actGeo.setImageResource(R.drawable.gris);
        actHist.setImageResource(R.drawable.gris);

        opoDepo.setImageResource(R.drawable.gris);
        opoArte.setImageResource(R.drawable.gris);
        opoEntre.setImageResource(R.drawable.gris);
        opoCienc.setImageResource(R.drawable.gris);
        opoGeo.setImageResource(R.drawable.gris);
        opoHist.setImageResource(R.drawable.gris);

        ImageView coronas = findViewById(R.id.bien);
        coronas.setImageResource(R.drawable.cero);
    }

    private void actualizarTrofeos(ArrayList<String> trofAct, ArrayList<String> trofOpo)
    {
        ImageView actDepo = findViewById(R.id.trofeoDepoActual);
        ImageView actArte = findViewById(R.id.trofeoArteActual);
        ImageView actEntre = findViewById(R.id.trofeoEntreActual);
        ImageView actCienc = findViewById(R.id.trofeoCiencActual);
        ImageView actGeo = findViewById(R.id.trofeoGeoActual);
        ImageView actHist = findViewById(R.id.trofeoHistActual);

        ImageView opoDepo = findViewById(R.id.trofeoDepoOpo);
        ImageView opoArte = findViewById(R.id.trofeoArteOpo);
        ImageView opoEntre = findViewById(R.id.trofeoEntreOpo);
        ImageView opoCienc = findViewById(R.id.trofeoCiencOpo);
        ImageView opoGeo = findViewById(R.id.trofeoGeoOpo);
        ImageView opoHist = findViewById(R.id.trofeoHistOpo);

        for( String trofeo : trofAct )
        {
            if( trofeo.equals( Duelo.GEOGRAFIA ) )
                //actGeo.setImageResource(R.drawable.g);
                setImageResource(actGeo, R.drawable.g);

            if( trofeo.equals( Duelo.HISTORIA ) )
                //actHist.setImageResource(R.drawable.h);
                setImageResource(actHist, R.drawable.h);

            if( trofeo.equals( Duelo.CIENCIA ) )
                //actCienc.setImageResource(R.drawable.ci);
                setImageResource(actCienc, R.drawable.ci);

            if( trofeo.equals( Duelo.ENTRETENIMIENTO ) )
                //actEntre.setImageResource(R.drawable.e);
                setImageResource(actEntre, R.drawable.e);

            if( trofeo.equals( Duelo.ARTE ) )
                //actArte.setImageResource(R.drawable.a);
                setImageResource(actArte, R.drawable.a);

            if( trofeo.equals( Duelo.DEPORTES ) )
                //actDepo.setImageResource(R.drawable.d);
                setImageResource(actDepo, R.drawable.d);
        }

        for( String trofeo : trofOpo )
        {
            if( trofeo.equals( Duelo.GEOGRAFIA ) )
                //opoGeo.setImageResource(R.drawable.g);
                setImageResource(opoGeo, R.drawable.g);

            if( trofeo.equals( Duelo.HISTORIA ) )
                //opoHist.setImageResource(R.drawable.h);
                setImageResource(opoHist, R.drawable.h);

            if( trofeo.equals( Duelo.CIENCIA ) )
                //opoCienc.setImageResource(R.drawable.ci);
                setImageResource(opoCienc, R.drawable.ci);

            if( trofeo.equals( Duelo.ENTRETENIMIENTO ) )
                //opoEntre.setImageResource(R.drawable.e);
                setImageResource(opoEntre, R.drawable.e);

            if( trofeo.equals( Duelo.ARTE ) )
                //opoArte.setImageResource(R.drawable.a);
                setImageResource(opoArte, R.drawable.a);

            if( trofeo.equals( Duelo.DEPORTES ) )
                //opoDepo.setImageResource(R.drawable.d);
                setImageResource(opoDepo, R.drawable.d);
        }
    }

    private void actualizarCoronas(int cantidad)
    {
        ImageView imgCoronas = findViewById(R.id.bien);
        if(cantidad == 0)
        {
            //imgCoronas.setImageResource(R.drawable.cero);
            setImageResource(imgCoronas, R.drawable.cero);
        }
        else if(cantidad == 1)
        {
            //imgCoronas.setImageResource(R.drawable.una);
            setImageResource(imgCoronas, R.drawable.una);
        }
        else if(cantidad == 2)
        {
            //imgCoronas.setImageResource(R.drawable.dos);
            setImageResource(imgCoronas, R.drawable.dos);
        }
        else if(cantidad == 3)
        {
            //imgCoronas.setImageResource(R.drawable.tres);
            setImageResource(imgCoronas, R.drawable.tres);
        }
    }

    private void actualizarImagen(int pEstado)
    {
        TextView estado = findViewById(R.id.txtEstado);

        if( pEstado == Duelo.ESPERANDO_LOCAL )
        {
            setText(estado, "Gira la ruleta...");
            //estado.setText("Gira la ruleta...");
        }
        else if( pEstado == Duelo.ESPERANDO_OPONENTE )
        {
            setText(estado, "Esperando Oponente...");
            //estado.setText("Esperando Oponente...");
        }
        else if( pEstado == Duelo.ESPERANDO_RESPUESTA )
        {
            setText(estado, "Esperando turno...");
            //estado.setText("Esperando turno...");
        }
        else if(pEstado == Duelo.ESTADO_RETO)
        {
            setText(estado, "Reto...");
            //estado.setText("Reto...");
        }
        else if(pEstado == Duelo.ESTADO_TERMINADO)
        {
            setText(estado, "Juego terminado");
            //estado.setText("Juego terminado");
        }
    }

    private void habilitarReto()
    {
        Button botonReto = findViewById(R.id.btnReto);
        //botonReto.setEnabled(true);
        setEnabled(botonReto, true);
    }

    private void deshabilitarReto()
    {
        Button botonReto = findViewById(R.id.btnReto);
        //botonReto.setEnabled(false);
        setEnabled(botonReto, false);
    }

    public void girar(View v)
    {
        deshabilitarReto();
        String categoria = darCategoriaAleatoria();

        TextView txtCategoria = (TextView) findViewById(R.id.txtCategoria);
        txtCategoria.setText(categoria);

        duelo.ruletaGira(categoria);

        modoPregunta();
    }

    private String darCategoriaAleatoria()
    {
        String categoria = "";
        int r = (int)(Math.random()*7);

        if( r == 0)
        {
            categoria = Duelo.HISTORIA;
        }
        else if( r == 1 )
        {
            categoria = Duelo.CIENCIA;
        }
        else if( r == 2 )
        {
            categoria = Duelo.GEOGRAFIA;
        }
        else if( r == 3 )
        {
            categoria = Duelo.CORONA;
        }
        else if( r == 4 )
        {
            categoria = Duelo.ENTRETENIMIENTO;
        }
        else if( r == 5 )
        {
            categoria = Duelo.ARTE;
        }
        else
        {
            categoria = Duelo.DEPORTES;
        }

        return categoria;
    }

    public void modoPregunta()
    {
        while( duelo.darPreguntaActual( ) == null )
        {
            try
            {
                Thread.sleep( 600 );
            }
            catch( InterruptedException e )
            {
                e.printStackTrace( );
            }
        }

        if(!entro)
        {
            Pregunta preg = duelo.darPreguntaActual();
            String pregunta = preg.darPregunta();
            String[] opciones = preg.darOpciones();
            entro = true;
            String titulo = "";
            if(duelo.darEstadoJuego() == Duelo.ESTADO_RETO)
                titulo = "Reto";
            else
                titulo = "Pregunta: " + duelo.darCategoriaAleatoria();
            //Intent intento = new Intent(this, PreguntaActivity.class);
            renovador = null;
            renovador = new Intent(this, PreguntaActivity.class);
            renovador.putExtra(PREGUNTA, pregunta);
            renovador.putExtra(OPCIONES, opciones);
            renovador.putExtra(TITULO, titulo);
            startActivityForResult(renovador, RTA);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RTA && resultCode == RESULT_OK)
        {
            Uri dato = data.getData();
            final String respuesta = data.getStringExtra(RESPUESTA);

            ResponderPregunta x = new ResponderPregunta();
            x.execute(respuesta);

            //String r = responderPregunta( respuesta );
            //esperarJugada();
            //act();
        }
    }

    public void reto(View v)
    {
        ThreadEnviarReto t = new ThreadEnviarReto(duelo);
        t.start();
    }

    public void mostrarMensaje(String parte1, String parte2 )
    {
        Snackbar.make(findViewById(R.id.corLayoutDuelo), parte2 + ": " + parte1, Snackbar.LENGTH_LONG)
                .setAction("Dismiss", null).show();
    }

    public void esperarJugada( )
    {

        Thread t = new ThreadEsperarJugada( duelo, this );
        t.start();
    }

    public void mostrarError(Exception e)
    {
        String mensaje = e.getMessage();
        Snackbar.make(findViewById(R.id.corLayoutDuelo), mensaje, Snackbar.LENGTH_LONG)
                .setAction("Dismiss", null).show();
    }

    public String responderPregunta( String respuesta ) throws Exception
    {
        String r = duelo.responderPregunta( respuesta );
        return r;
    }

    public void mostrarGanador( )
    {
        Snackbar.make(findViewById(R.id.corLayoutDuelo), "El ganador del encuentro fue " + duelo.darNombreGanador( ) + "." + "Fin del juego", Snackbar.LENGTH_LONG)
                .setAction("Dismiss", null).show();
    }

    public void act()
    {
        actualizarInterfaz();
    }

    private class ResponderPregunta extends AsyncTask
    {
        @Override
        protected String doInBackground(Object[] obj)
        {
            String r = "";
            try
            {
                r = responderPregunta((String) obj[0]);
            }
            catch (Exception e)
            {
                mostrarError(e);
            }
            return r;
        }
        @Override
        protected void onPostExecute(Object res)
        {
            if(((String)res).equals( "Respuesta correcta" ) )
            {
                Toast.makeText(DueloActivity.this, "Respuesta Correcta", Toast.LENGTH_LONG).show();
                //esperarJugada();
                //act();
            }
            else if ( ((String)res).equals( "Respuesta incorrecta" ) )
            {
                Toast.makeText(DueloActivity.this, "Respuesta Incorrecta", Toast.LENGTH_LONG).show();
                //esperarJugada();
                //act();
            }
            else
            {
                Toast.makeText(DueloActivity.this, "Reto: "+ (String) res, Toast.LENGTH_LONG).show();
                //esperarJugada();
                act();
            }
            entro = false;
            esperarJugada();
            act();
        }
    }
}
