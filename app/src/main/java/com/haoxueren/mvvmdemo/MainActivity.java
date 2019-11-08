package com.haoxueren.mvvmdemo;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.haoxueren.mvvmdemo.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        person = new Person();
        person.name.set("haoxueren");
        binding.setPerson(person);
    }

    /**
     * 更新对象的值，请注意观察控件内容的变化
     */
    public void updatePerson(View view) {
        person.name.set("大智先生");
    }

    /**
     * 控件内容变化后，Toast对象的值
     */
    public void queryPerson(View view) {
        Toast.makeText(this, person.name.get(), Toast.LENGTH_SHORT).show();
    }
}
