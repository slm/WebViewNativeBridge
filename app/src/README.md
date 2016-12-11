WebView Native Bridge for Android
===================

You can pass simple datas from webview to java natives. You don't need to enable Javascript.


##Native functions
-
This library uses links to transfer data to Java.

Url structure must be like that:

```
nativebridge://FunctionName/Paramter1/Parameter2/Parameter3/...
```

####Parameters must be valid for URL.
Example => nativebridge://showAlertDialog/Alert%20Title/Alert%20Message
####If you want to pass URL, you must decode two times.
Example => nativebridge://goUri/https%253A%252F%252Fgithub.com%252Fslm



##In Android usage:

```java

        NativeBridgeBuilder builder = new NativeBridgeBuilder()
                .addFunction("FunctionName1", new OnActionListener() {
                        @Override
                        public void onAction(NativeBridgeFunction function, String... parameters) {
                      	#TODO something
                      }
                })
                .addFunction("FunctionNameY", new OnActionListener() {
                    @Override
                    public void onAction(NativeBridgeFunction function,String... parameters) {
                        #TODO something
                    }
                });


        WebViewClient client = builder.buildWebViewClient();
        webView.setWebViewClient(client);

```

#License
-
```
The MIT License (MIT)
Copyright (c) 2016 Selim YILDIZ

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```