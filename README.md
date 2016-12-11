# WebViewNativeBridge

This library allows you to send simple data from WebView to Java. Sends the data via url.

####Url Structure
```
nativebridge://functionName/paramter1/parameter2/parameter3
```
* Your urls must be start with nativebridge://
* Your functionName and parameters must be valid for url standards.
* If you want to pass url as parameter, you must url decode two times.

Examples:
```
nativebridge://quit  #no parameter
nativebridge://showToast/Toast%20Test! # parameter1 = Toast Test!
nativebridge://showAlertDialog/Alert%20Title/Alert%20Message # parameter1 = Alert Title , parameter2 = Alert Message
nativebridge://goUri/https%253A%252F%252Fgithub.com%252Fslm #parameter1 = https:/github.com/slm | url parameter decoded two times
```

####In Android Usage:

```
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
                });
      WebViewClient client = builder.buildWebViewClient();
      webView.setWebViewClient(client);

```
If you use already a custom WebViewClient you can use that library like that.

```
...

final NativeBridgeBuilder.SimpleChecker checker =  builder.buildChecker();

#In WebViewClient
             ...
             ...
             @Override
             public boolean shouldOverrideUrlLoading(WebView view, String url) {
                 return checker.check(url);
             }
             ...
             ...

```


####License
```
The MIT License (MIT)
Copyright (c) 2016 Selim YILDIZ

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

```

