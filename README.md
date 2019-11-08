# 如何实现MVVM模式中的数据双向绑定

## 内容导读

本文介绍了如何在MVVM模式中实现数据的双向绑定，主要分为以下4个步骤：

> 第1步：在build.gradle中启用数据绑定
> 第2步：创建支持数据绑定的实体类
> 第3步：在布局文件中配置数据绑定
> 第4步：在Activity中配置数据绑定

## 操作步骤

**第1步：在build.gradle中启用数据绑定**

在Module的build.gradle文件中添加数据绑定配置：`dataBinding { enabled = true }`，如下(注意位置)：

```gradle
apply plugin: 'com.android.application'

android {
    compileSdkVersion 28
    defaultConfig {
        applicationId "com.haoxueren.mvvmdemo"
        ...
    }
    buildTypes {
        ...
    }

    dataBinding {
        enabled = true // enable dataBinding
    }
}

dependencies {
    ...
}
```

**第2步：创建支持数据绑定的实体类**

注意：原来属性的类型现在成了ObservableField的泛型类型。
```
import android.databinding.ObservableField;

public class Person {

    public ObservableField<String> name = new ObservableField<>();

    public ObservableField<Integer> age = new ObservableField<>();
}
```

**第3步：在布局文件中配置数据绑定**

在布局文件中添加`<data></data>`标签，用于指定当前布局所要绑定的实体类(type)以及实体类名(name)：
```
<data>
  <variable
    name="实体类的对象名"
    type="要绑定的实体类" />
</data>
```
改造后的布局文件如下：
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <!-- 指定要绑定的实体类及实体类的对象名 -->
    <data>
        <variable
            name="person"
            type="com.haoxueren.Person" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <EditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@={person.name}" />

    </LinearLayout>

</layout>
```

**第4步：在Activity中配置数据绑定**

1. 使用`DataBindingUtil#setContentView()`代替`Activity#setContentView()`。
2. 调用`binding.setPerson(person)`关联数据，代码如下：
```java
public class MainActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        Person person = new Person();
        person.name.set("haoxueren");
        binding.setPerson(person);
    }
}
```

至此，我们已经完成了MVVM模式中数据双向绑定的所有配置，此时，无论是实体对象还是UI控件中，任意一方的值改变均会同步给另一方。

## 总结

本文为大家介绍了MVVM中实现双向数据绑定的一种方式。

示例代码：https://github.com/haoxueren/MVVMDemo
