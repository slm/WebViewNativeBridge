package com.github.slmyldz.nativebridge;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URLDecoder;
import java.util.Arrays;


/**
 * Created by slmyldz on 10.12.2016.
 */

public class NativeBridgeBuilder {

    private static final String LOG = "NativeBridgeWebClient";
    ArrayMap<String,NativeBridgeFunction> functions;

    public NativeBridgeBuilder addFunction(String function_name,OnActionListener onActionListener){
        if(functions == null){
            functions = new ArrayMap<>();
        }

        if(functions.containsKey(function_name)){
            throw new RuntimeException(function_name + " defined before. You can't add multiple functions with same key.");
        }
        functions.put(function_name,new NativeBridgeFunction(function_name,onActionListener));
        return this;
    }


    public WebViewClient buildWebViewClient(){
        WebViewClient client = new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.toString().startsWith("nativebridge://")) {
                    handleNativeBridge(url);
                    return true;
                } else {
                    return super.shouldOverrideUrlLoading(view, url);
                }
            }
        };
        return  client;
    }


    public SimpleChecker buildChecker(){
        SimpleChecker checker = new SimpleChecker();
        return new SimpleChecker();
    }

    private void handleNativeBridge(String url){
            String[] params = url.replace("nativebridge://","").toString().split("/");
            if(validateParams(params)){
                NativeBridgeFunction function = functions.get(doubleDecode(params[0]));
                if(params.length>0){
                    String[] decoded_params = decodeParams(Arrays.copyOfRange(params,1,params.length));
                    function.listener.onAction(function,decoded_params);
                }else{
                    function.listener.onAction(function);
                }
            }
    }

    private String[] decodeParams(String[] params){
        for(int  i = 0 ;i<params.length;i++){
            params[i] = doubleDecode(params[i]);
        }
        return params;
    }
    private boolean validateParams(String[] params){
        if(params == null || params.length == 0 || params[0] == null){
            Log.i(LOG,"Url is empty!");
            return  false;
        }

        if(!functions.containsKey(doubleDecode(params[0]))){
            Log.i(LOG,params[0]+ " function never defined.");
            return  false;
        }

        return true;
    }



    private String doubleDecode(String string) {
        try {
            return URLDecoder.decode(URLDecoder.decode(string, "UTF-8"),
                    "UTF-8");
        } catch (Exception e) {
            return string;
        }
    }

    public class SimpleChecker {

        public boolean check(String url){
            if (url.toString().startsWith("nativebridge://")) {
                handleNativeBridge(url);
                return true;
            }else{
                return false;
            }
        }

    }



}
