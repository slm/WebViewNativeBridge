package com.github.slmyldz.webviewnativebridge;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.slmyldz.nativebridge.NativeBridgeBuilder;
import com.github.slmyldz.nativebridge.NativeBridgeFunction;
import com.github.slmyldz.nativebridge.OnActionListener;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);


        NativeBridgeBuilder builder = new NativeBridgeBuilder()
                .addFunction("showToast", new OnActionListener() {
                        @Override
                        public void onAction(NativeBridgeFunction function, String... parameters) {
                            Toast.makeText(MainActivity.this,parameters[0],Toast.LENGTH_SHORT).show();
                        }
                })
                .addFunction("quit", new OnActionListener() {
                    @Override
                    public void onAction(NativeBridgeFunction function,String... parameters) {
                        finish();
                    }
                })
                .addFunction("showAlertDialog", new OnActionListener() {
                    @Override
                    public void onAction(NativeBridgeFunction function,String... parameters) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                                .setTitle(parameters[0])
                                .setMessage(parameters[1])
                                .setPositiveButton("OK",null);
                        builder.show();

                    }
                })
                .addFunction("goUri", new OnActionListener() {
                    @Override
                    public void onAction(NativeBridgeFunction function,String... parameters) {
                        webView.loadUrl(parameters[0]);
                    }
                });


        WebViewClient client = builder.buildWebViewClient();
        webView.setWebViewClient(client);

        final NativeBridgeBuilder.SimpleChecker checker =  builder.buildChecker();

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return checker.check(url);
            }
        });
        try {
            InputStream stream =  getAssets().open("test.html");
            webView.loadData(readInputStreamAsString(stream),"text/html","utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {

        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }

    private static String readInputStreamAsString(InputStream in)
            throws IOException {

        BufferedInputStream bis = new BufferedInputStream(in);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = bis.read();
        while(result != -1) {
            byte b = (byte)result;
            buf.write(b);
            result = bis.read();
        }
        return buf.toString();
    }

}
