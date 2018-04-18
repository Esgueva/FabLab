package com.fablabburgos.fablabburgos.vista.noticia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fablabburgos.fablabburgos.R;
import com.fablabburgos.fablabburgos.modelo.Noticia;


public class FragmentoNoticiaDetalle extends Fragment{

    Noticia noticia;
    WebView web;


    public FragmentoNoticiaDetalle() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento_noticia_detalle, container, false);
        WebView view = v.findViewById(R.id.webNoticias);

        if (getArguments() != null) {
            noticia = (Noticia) getArguments().get("noticia");
            view.getSettings().setJavaScriptEnabled(true);
            view.getSettings().setBuiltInZoomControls(true);
            view.loadUrl(noticia.getUrl());

            view.setWebViewClient(new WebViewClient(){
                public boolean shouldOverrideUrlLoading(WebView view, String url){
                    return false;
                }
            });
        }
        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
