package bono.brainsduel.interfaz;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import bono.brainsduel.R;
import bono.brainsduel.mundo.Duelo;

import static bono.brainsduel.interfaz.DueloActivity.OPCIONES;
import static bono.brainsduel.interfaz.DueloActivity.RTA;
import static bono.brainsduel.interfaz.DueloActivity.TITULO;
import static bono.brainsduel.mundo.Duelo.PREGUNTA;
import static bono.brainsduel.mundo.Duelo.RESPUESTA;

public class PreguntaActivity extends AppCompatActivity implements View.OnClickListener
{

    private String[] opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);
        opciones = new String[4];
        actualizar();
    }

    public void actualizar()
    {
        TextView textoPregunta = (TextView) findViewById(R.id.txtPregunta);
        Button butOp1 = (Button) findViewById(R.id.butOpc1);
        Button butOp2 = (Button) findViewById(R.id.butOpc2);
        Button butOp3 = (Button) findViewById(R.id.butOpc3);
        Button butOp4 = (Button) findViewById(R.id.butOpc4);

        butOp1.setOnClickListener(this);
        butOp2.setOnClickListener(this);
        butOp3.setOnClickListener(this);
        butOp4.setOnClickListener(this);

        textoPregunta.setText("");
        butOp1.setText("");
        butOp2.setText("");
        butOp3.setText("");
        butOp4.setText("");

        Intent intento = getIntent();
        String pregunta = intento.getStringExtra(PREGUNTA);
        opciones = intento.getStringArrayExtra(OPCIONES);
        String titulo = intento.getStringExtra(TITULO);

        setTitle(titulo);

        textoPregunta.setText(pregunta);
        butOp1.setText(opciones[0]);
        butOp2.setText(opciones[1]);
        butOp3.setText(opciones[2]);
        butOp4.setText(opciones[3]);

    }

    @Override
    public void onClick(View v)
    {
        TextView textoPregunta = (TextView) findViewById(R.id.txtPregunta);
        Button butOp1 = (Button) findViewById(R.id.butOpc1);
        Button butOp2 = (Button) findViewById(R.id.butOpc2);
        Button butOp3 = (Button) findViewById(R.id.butOpc3);
        Button butOp4 = (Button) findViewById(R.id.butOpc4);
        Uri dato = Uri.parse("no importa");

        Intent resultado = new Intent(null, dato);
        desactivarBotones();
        switch (v.getId())
        {
            case R.id.butOpc1:
            {
                resultado.putExtra(RESPUESTA, opciones[0]);
                setResult(RESULT_OK, resultado);
                //finish();
                textoPregunta.setText("");
                butOp1.setText("");
                butOp2.setText("");
                butOp3.setText("");
                butOp4.setText("");

                finishAndRemoveTask();
                break;
            }
            case R.id.butOpc2:
            {

                resultado.putExtra(RESPUESTA, opciones[1]);
                setResult(RESULT_OK, resultado);
                //finish();
                textoPregunta.setText("");
                butOp1.setText("");
                butOp2.setText("");
                butOp3.setText("");
                butOp4.setText("");
                finishAndRemoveTask();
                break;
            }
            case R.id.butOpc3:
            {
                resultado.putExtra(RESPUESTA, opciones[2]);
                setResult(RESULT_OK, resultado);
                //finish();
                textoPregunta.setText("");
                butOp1.setText("");
                butOp2.setText("");
                butOp3.setText("");
                butOp4.setText("");
                finishAndRemoveTask();
                break;
            }
            case R.id.butOpc4:
            {
                resultado.putExtra(RESPUESTA, opciones[3]);
                setResult(RESULT_OK, resultado);
                //finish();
                textoPregunta.setText("");
                butOp1.setText("");
                butOp2.setText("");
                butOp3.setText("");
                butOp4.setText("");
                finishAndRemoveTask();
                break;
            }
        }
    }

    public void desactivarBotones()
    {
        Button butOp1 = (Button) findViewById(R.id.butOpc1);
        Button butOp2 = (Button) findViewById(R.id.butOpc2);
        Button butOp3 = (Button) findViewById(R.id.butOpc3);
        Button butOp4 = (Button) findViewById(R.id.butOpc4);

        butOp1.setEnabled(false);
        butOp2.setEnabled(false);
        butOp3.setEnabled(false);
        butOp4.setEnabled(false);
    }

    public void mostrarError(Exception e)
    {
        String mensaje = e.getMessage();
        Snackbar.make(findViewById(R.id.corLayoutPregunta), mensaje, Snackbar.LENGTH_LONG)
                .setAction("Dismiss", null).show();
    }
}