package com.github.slmyldz.nativebridge;

/**
 * Created by slmyldz on 10.12.2016.
 */

public class NativeBridgeFunction {
    String name;
    OnActionListener listener;
    public NativeBridgeFunction(String name, OnActionListener listener){
        this.name = name;
        this.listener = listener;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListener(OnActionListener listener) {
        this.listener = listener;
    }

    public OnActionListener getListener() {
        return listener;
    }

    public String getName() {
        return name;
    }
}
