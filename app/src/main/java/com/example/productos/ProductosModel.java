package com.example.productos;
import android.content.Context;
import android.content.Intent;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import cz.msebera.android.httpclient.Header;

public class ProductosModel implements ProductosInterface.Model{

    private ProductosInterface.Presenter presenter;

    public ProductosModel(ProductosInterface.Presenter presenter){
        this.presenter=presenter;
    }
    @Override
    public void Login(String user, String pass) {
        String URL = "http://192.168.1.65:8000/auth/jwt/create/";
        if(user.equals("") && pass.equals("")){
            //presenter.showError("Campo Vacio");
        }else{
            RequestParams params = new RequestParams();
            params.put("username",user);
            params.put("password",pass);
            new AsyncHttpClient().post(URL, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String str = new String(responseBody);
                    String token;
                    System.out.println(str);

                    try {
                        JSONObject arr =new JSONObject(new String(responseBody));
                        token = arr.getString("access");
                        System.out.println("Token "+token);
                        System.out.println(token);
                        if(statusCode == 200){
                            //Siguiente_Vista(token);
                            presenter.showResult(token, statusCode);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    System.out.println("error "+error + " Status: " + statusCode);

                    String str = new String(responseBody);
                    System.out.println("response"+ str);

                    if(statusCode == 400){
                        String TEXT = "El usuario o la contraseña son incorrectos";
                        presenter.showError(TEXT, statusCode);
                    }
                    if(statusCode == 401){
                        String TEXT = "El usuario o la contraseña son incorrectos";
                        presenter.showError(TEXT, statusCode);
                    }
                    if(statusCode == 404){
                        String TEXT = "Error Not found";
                        presenter.showError(TEXT, statusCode);
                    }
                    if(statusCode == 500){
                        String TEXT = "500 Internal Server Error";
                        presenter.showError(TEXT, statusCode);
                    }
                }
            });
        }
    }

    public void Tipo_Usuario(String token){
        String URL = "http://192.168.1.65:8000/auth/jwt/create/";
    }
}
