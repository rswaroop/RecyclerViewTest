package swaroop.recyclerView;

import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyBrowser extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        if (url.startsWith("local:")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                webView.evaluateJavascript(jsFormParsing(), new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                    }
                });
            } else {
                webView.loadUrl(jsFormParsing());
            }
            return true;
        }

        return super.shouldOverrideUrlLoading(webView, url);
    }

    private String jsFormParsing() {
        return "javascript:" +
                "var inputs, index;" +
                "var data = { array: [] };" +
                "inputs = document.getElementsByTagName('input');" +
                "for (index = 0; index < inputs.length; ++index) {" +
                "var field = inputs[index];" +
                "" +
                "data.array.push({\"name\": field.name ,\"value\": field.value});" +
                "}" +
                "var json = JSON.stringify(data);" +
                "parseForm.saveData(json); ";
    }
}
