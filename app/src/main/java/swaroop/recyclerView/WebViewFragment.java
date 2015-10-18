package swaroop.recyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebViewFragment extends Fragment {
    private final Logger mLog = LoggerFactory.getLogger(getClass());
    private DataChangeListener mDataChangeListener;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webview_fragment, null);

        WebView webView = (WebView) view.findViewById(R.id.webview);

        webView.setWebViewClient(new MyBrowser());

        webView.addJavascriptInterface(
                new swaroop.recyclerView.SaveRecords(getActivity(),
                        new SaveRecords.Callback() {
                            @Override
                            public void onDataSaved() {
                                if (mDataChangeListener != null) {
                                    mDataChangeListener.onDataChanged();
                                } else {
                                    mLog.debug("DataChangeListener is not set.");
                                }
                            }
                        }),
                "parseForm");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        webView.loadUrl("file:///android_asset/test.html");

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mDataChangeListener = (DataChangeListener) getActivity();
        } catch (ClassCastException e) {
            mLog.error("Activity must implement DataChangeListener : ", e);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mDataChangeListener = null;
    }

    public void setDataChangeListener(DataChangeListener listener) {
        mDataChangeListener = listener;
    }

    public interface DataChangeListener {
        void onDataChanged();
    }
}
