package com.haoxueren.mvvmdemo;


import android.databinding.ObservableField;

public class Person {

    public ObservableField<String> name = new ObservableField<>();

    public ObservableField<Integer> age = new ObservableField<>();
}
