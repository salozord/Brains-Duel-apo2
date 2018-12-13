package bono.brainsduel.interfaz;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import bono.brainsduel.R;
import bono.brainsduel.mundo.Duelo;
import bono.brainsduel.mundo.ThreadRegistrar;

import static bono.brainsduel.interfaz.LoginActivity.SESION;
import static bono.brainsduel.interfaz.LoginActivity.VALORES;
import static bono.brainsduel.mundo.Duelo.REGISTRO;

public class RegistroActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setTitle("Registarse");
    }

    public void registro(View vista)
    {
        EditText textoServer = (EditText) findViewById(R.id.txtServidorReg);
        EditText textoPuerto = (EditText) findViewById(R.id.txtPuertoReg);
        EditText textoAlias = (EditText) findViewById(R.id.txtAliasReg);
        EditText textoNombre = (EditText) findViewById(R.id.txtNombre);
        EditText textoApellidos = (EditText)  findViewById(R.id.txtApellidos);
        EditText textoContra = (EditText) findViewById(R.id.txtContraReg);
        EditText textoConfirmaContra = (EditText) findViewById(R.id.txtContraConReg);

        String server = textoServer.getText().toString();
        String temp = textoPuerto.getText().toString();
        String alias = textoAlias.getText().toString();
        String nombre = textoNombre.getText().toString();
        String apellidos = textoApellidos.getText().toString();
        String contra = textoContra.getText().toString();
        String confirmContra = textoConfirmaContra.getText().toString();

        if(server != null && !server.equals("") && nombre != null && !nombre.equals("")&& apellidos != null  && !apellidos.equals("") && temp != null && !temp.equals("") && alias != null && !alias.equals("") && contra != null && !contra.equals("") && confirmContra != null && !confirmContra.equals(""))
        {
            if(contra.equals(confirmContra))
            {
                iniciar(server, temp, alias, nombre, apellidos, contra);

            }
            else
            {
                Snackbar.make(findViewById(R.id.corLayoutReg), "Las contraseñas no coinciden", Snackbar.LENGTH_LONG)
                        .setAction("Dismiss", null).show();
            }
        }
        else
        {
            Snackbar.make(findViewById(R.id.corLayoutReg), "Debe llenar todos los campos", Snackbar.LENGTH_LONG)
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
        Snackbar.make(findViewById(R.id.corLayoutReg), mensaje, Snackbar.LENGTH_LONG)
                .setAction("Dismiss", null).show();
    }

    public void iniciar(String server, String temp, String alias, String nombre, String apellidos, String contra)
    {
        //hijo = DueloActivity.getInstance();

        String[] valores = {server, temp, alias ,nombre, apellidos, contra};
        Intent intento = new Intent(this, DueloActivity.class);
        intento.putExtra(SESION, 1);
        intento.putExtra(VALORES, valores);
        startActivity(intento);
    }
}
