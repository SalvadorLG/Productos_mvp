package com.example.productos;

public class ProductosPresenter implements ProductosInterface.Presenter{

    private ProductosInterface.View view;
    private ProductosInterface.Model model;

    public ProductosPresenter(ProductosInterface.View view){
        this.view = view;
        model = new ProductosModel(this);
    }

    @Override
    public void showResult(String token, int statusCode) {
        if(view!=null){
            view.showResult(token, statusCode);
        }
    }

    @Override
    public void Login(String user, String pass) {
        if(view!=null){
            model.Login(user, pass);
        }
    }

    @Override
    public void Tipo_Usuario(String token){
        if (view!=null){
            model.Tipo_Usuario(token);
        }
    }

    @Override
    public void showError(String error, int statusCode) {
        if(view!=null){
            view.showError(error,statusCode);
        }
    }
}
