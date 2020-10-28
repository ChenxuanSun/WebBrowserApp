package edu.temple.webbrowserapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.net.MalformedURLException;

public class PageControlFragment extends Fragment {
    ImageButton GoButton;
    ImageButton NextButton;
    ImageButton BackButton;
    EditText editText;

    ReadURL ParentActivity;


    View l;


    public PageControlFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ReadURL) {
            ParentActivity = (ReadURL) context;
        } else {
            throw new RuntimeException("You must implement passInfoInterface to attach this fragment");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        l =  inflater.inflate(R.layout.fragment_page_control, container, false);


        GoButton = (ImageButton) l.findViewById(R.id.GoButton);
        BackButton = (ImageButton)l.findViewById(R.id.BackButton);
        NextButton = (ImageButton)l.findViewById(R.id.NextButton);
        editText = (EditText) l.findViewById(R.id.URL);


        GoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread t = new Thread();
                String URLText =  editText.getText().toString();
                ParentActivity.PutURLinWebView(URLText);
                t.start();
            }

        });

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParentActivity.GobackWeb();
            }
        });

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ParentActivity.GoForwordWeb();
            }
        });
        return l;
    }

public void updateURL(String url){
        editText.setText(url);
}

    public interface ReadURL{
            void PutURLinWebView(String URL);
            void GobackWeb();
            void GoForwordWeb();
    }

    public String getURL(){
        String sTmp=editText.getText().toString();
        if (sTmp.length()==0){
            sTmp="";
        }
        else if (sTmp.length()<8){
            sTmp="https://"+sTmp;
        }
        else if (!sTmp.substring(0,8).toLowerCase().equals("https://")){
            sTmp="https://"+sTmp;
        }
        editText.setText(sTmp);
        return sTmp;
    }

}