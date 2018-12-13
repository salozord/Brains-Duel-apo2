package bono.brainsduel.interfaz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import bono.brainsduel.R;
import bono.brainsduel.mundo.Duelo;
import bono.brainsduel.mundo.ThreadIniciarSesion;

import static bono.brainsduel.mundo.Duelo.LOGIN;

public class LoginActivity extends AppCompatActivity
{
    public static final String SESION = "SESION";
    public static final String VALORES = "VALORES";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("Login");
        setContentView(R.layout.activity_login);
    }

    public void login(View vista)
    {
        EditText textoServer = (EditText) findViewById(R.id.txtServLogin);
        EditText textoPuerto = (EditText) findViewById(R.id.txtPuertoLogin);
        EditText textoAlias = (EditText) findViewById(R.id.txtAlias);
        EditText textoContra = (EditText) findViewById(R.id.txtContrasenia);

        String server = textoServer.getText().toString();
        String temp = textoPuerto.getText().toString();
        String alias = textoAlias.getText().toString();
        String contra = textoContra.getText().toString();

        if(server != null && !server.equals("") && temp != null && !temp.equals("") && alias != null && !alias.equals("") && contra != null && !contra.equals(""))
        {
            iniciar(server, temp, alias, contra);
        }
        else
        {
            Snackbar.make(findViewById(R.id.corLayoutLogin), "Debe llenar todos los campos", Snackbar.LENGTH_LONG)
                    .setAction("Dismiss", null).show();
        }

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    public void mostrarError(Exception e)
    {
        String mensaje = e.getMessage();
        Snackbar.make(findViewById(R.id.corLayoutLogin), mensaje, Snackbar.LENGTH_LONG)
                .setAction("Dismiss", null).show();
    }

    public void iniciar(String server, String temp, String alias, String contra)
    {
        String[] valores = {server, temp, alias, contra};
        Intent intento = new Intent(this, DueloActivity.class);
        intento.putExtra(SESION, 0);
        intento.putExtra(VALORES, valores);
        startActivity(intento);
    }
}
