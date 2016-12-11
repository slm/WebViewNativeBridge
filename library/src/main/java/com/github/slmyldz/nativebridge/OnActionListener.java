package com.github.slmyldz.nativebridge;

/**
 * Created by slmyldz on 10.12.2016.
 */

public interface OnActionListener {

    void onAction(NativeBridgeFunction function,String... parameters);

}
