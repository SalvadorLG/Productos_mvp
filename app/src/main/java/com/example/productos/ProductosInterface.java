package com.example.productos;

public interface ProductosInterface {

    interface View{
        void showResult(String result, int statusCode);
        void showError (String error, int statusCode);
    }

    interface Presenter{
        void showResult(String result, int statusCode);
        void Login(String user, String pass);
        void Tipo_Usuario(String token);
        void showError(String error, int statusCode);
    }

    interface Model{
        void Login(String user, String data);
        void Tipo_Usuario(String token);
    }
}
