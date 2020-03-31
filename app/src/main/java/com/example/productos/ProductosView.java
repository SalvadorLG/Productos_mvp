package com.example.productos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProductosView extends AppCompatActivity
            implements ProductosInterface.View, fragmentsLogin.FragmentLoginListener,fragmentAdmin.FragmentAdminListener{

    private fragmentsLogin fragmentsLogin;
    private fragmentAdmin fragmentAdmin;
    private ProductosInterface.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentsLogin = new fragmentsLogin();
        fragmentAdmin =  new fragmentAdmin();
        presenter = new ProductosPresenter(this);
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments,fragmentsLogin).commit();
    }

    @Override
    public void showResult(String token, int statusCode) {
        //tvAlCuadrado.setText(result);
        //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        //CharSequence Token = token;
        int status = statusCode;
        presenter.Tipo_Usuario(token);

        Bundle bundle = new Bundle();
        bundle.putString("token",token);
        bundle.putInt("status",statusCode);
        fragmentAdmin.setArguments(bundle);
        //fragmentAdmin.Actualizar_resultado(token, status);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedorFragments,fragmentAdmin);
        transaction.commit();
    }

    @Override
    public void showError(String error, int statusCode) {
        fragmentAdmin.Actualizar_resultado(error, statusCode);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedorFragments,fragmentAdmin);
        transaction.commit();
    }

    //@Override
    //public void onFragmentInteraction(Uri uri) {

    //}

    @Override
    public void onInputASent(String username, String password){
        //System.out.println(input);
        //Toast.makeText(getApplicationContext(), input, Toast.LENGTH_SHORT).show();
        presenter.Login(username.toString(), password.toString());
    }

    @Override
    public void onInputBSent(CharSequence input) {
    }
}
