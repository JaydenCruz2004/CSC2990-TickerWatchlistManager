package com.example.csc2990_tickerwatchlistmanager;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InfoWebFragment extends Fragment {

    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_web, container, false);
        webView = view.findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        // Default page
        webView.loadUrl("https://seekingalpha.com");

        return view;
    }

    public void loadUrlForTicker(String ticker) {
        String url = "https://seekingalpha.com/symbol/" + ticker.toUpperCase();
        webView.loadUrl(url);
    }
}
