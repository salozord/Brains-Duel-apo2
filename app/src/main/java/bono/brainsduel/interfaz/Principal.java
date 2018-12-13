package bono.brainsduel.interfaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import bono.brainsduel.R;

public class Principal extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_activity);
    }

    public void iniciarSesion(View vista)
    {
        Intent menuSesion = new Intent(this, LoginActivity.class);
        startActivity(menuSesion);

    }

    public void registrarse(View vista)
    {
        Intent menuReg = new Intent(this, RegistroActivity.class);
        startActivity(menuReg);
    }
}
