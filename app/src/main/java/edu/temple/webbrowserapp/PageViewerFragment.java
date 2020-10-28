package edu.temple.webbrowserapp;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class PageViewerFragment extends Fragment {

    View l;
    WebView webView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public PageViewerFragment() {

    }

    private PageViewerFragment.updateURL listener;

    public void addPageListener(PageViewerFragment.updateURL listener){
        this.listener = listener;
    }
    public interface updateURL{
        //用来改全网址
        void SetURL(String url);

    }



    public static PageViewerFragment newInstance(String param1, String param2) {
        PageViewerFragment fragment = new PageViewerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        l = inflater.inflate(R.layout.fragment_page_viewer, container, false);

        webView = (WebView) l.findViewById(R.id.WebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(webViewClient);

        return  l;
    }

    private WebViewClient webViewClient=new WebViewClient(){
        public void onPageFinished(WebView view, String url) {

        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (listener!=null){ listener.SetURL(url);}
        }
    };



    public  void sppp(String url){
        if(url.contains("http")) {
            webView.loadUrl(url);
            webView.getSettings().setJavaScriptEnabled(true);//可以和javaScript交互
            webView.setWebViewClient(new WebViewClient());

        }else if (url.contains(".com")){
            webView.loadUrl("http://"+url);
            webView.getSettings().setJavaScriptEnabled(true);//可以和javaScript交互
            webView.setWebViewClient(new WebViewClient());
        }else{
            webView.loadUrl("http://"+url+".com/");
            webView.getSettings().setJavaScriptEnabled(true);//可以和javaScript交互
            webView.setWebViewClient(new WebViewClient());
        }
    }

    public  void gobackview(){
        webView.goBack();
    }

    public  void goforwardView(){
        webView.goForward();
    }



}