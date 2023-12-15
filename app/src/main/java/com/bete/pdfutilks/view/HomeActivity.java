package com.bete.pdfutilks.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.bete.pdfutilks.R;
import com.bete.pdfutilks.databinding.ActivityHomeBinding;
import com.bete.pdfutilks.newUtils.ITextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding homeBinding;
    public static String savae_path = "/sdcard/data/itext.pdf";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_home, null);
        homeBinding = ActivityHomeBinding.bind(view);
        setContentView(homeBinding.getRoot());
        ITextUtils.test();
        initView();
    }

    private void initView() {
        homeBinding.fab.setOnClickListener(v ->
        {
            boolean result = ITextUtils.test();


        });

    }


    private List<List<String>> getSpecialTableValue1() {


        List<List<String>> originalCollection = new ArrayList<>();
        originalCollection.add(Arrays.asList(new String[]{"Well", "Dyes", "Targets", "Ct", "Result-1", "Result-2", "Final Result"}));
        originalCollection.add(Arrays.asList(new String[]{"Condition-1", "FAM", "1", "2", "3", "Positve", "Positve"}));
        originalCollection.add(Arrays.asList(new String[]{"Condition-1", "Hex", "11", "12", "12", "Positve", "Positve"}));
        originalCollection.add(Arrays.asList(new String[]{"Condition-1", "Texas Red", "21", "22", "23", "Positve", "Positve"}));
        originalCollection.add(Arrays.asList(new String[]{"Condition-1", "Cy5", "41", "42", "43", "Positve", "Positve"}));
        originalCollection.add(Arrays.asList(new String[]{"Condition-1", "Cy5.5", "51", "52", "53", "Positve", "Positve"}));

        return originalCollection;
    }


}
