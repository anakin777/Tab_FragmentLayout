## 使用方式

### Project build.gradle
```
repositories {
        ...
        maven { url "https://jitpack.io" }
    }

```
### Module build.gradle
```
repositories {
        implementation 'com.github.anakin777:Tab_FragmentLayout:v1.0.1'
    }

```

### 布局文件

```
<cn.lxl.tflayout.TabFragmentLayout
        android:id="@+id/tfl"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:tabBarHeight="..." //标签栏高度
        app:tabTextSize="..."  //字体大小
        app:tabIconSize="..."  //图标大小
        app:tabTextColor="..." //字体颜色
        app:msgTextSize="..."/>//消息数字大小
```


### activity中使用
</br>
kotlin

```
//Tab_Bean --> Tab对象
var tabs = ArrayList<Tab_Bean>()
//构造函数包含：名称，选中图标，未选中图标，消息条数，绑定的Fragment
var tab1 = Tab_Bean("首页",R.mipmap.ic_launcher,R.mipmap.ic_launcher,5,MainFragment.newInstance("首页"))
var tab2 = Tab_Bean("推荐",R.mipmap.ic_launcher,R.mipmap.ic_launcher,0,MainFragment.newInstance("推荐"))
var tab3 = Tab_Bean("个人中心",R.mipmap.ic_launcher,R.mipmap.ic_launcher,5,MainFragment.newInstance("个人中心"))
tabs.add(tab1)
tabs.add(tab2)
tabs.add(tab3)
//设置tabs
tfl.setMenus(tabs,this)
//tabs点击监听
tfl.setOnTabClickListener {
//it --> position
    when(it){
        0 -> {
            Logger.v("首页")
        }
        1 -> {
            Logger.v("推荐")
        }
        2 -> {
            Logger.v("个人中心")
        }
    }
    //更新tabs
    tfl.updateMenu(tabs,it)
}
```


java

```
//Tab_Bean --> Tab对象
ArrayList<Tab_Bean> tabs = new ArrayList<Tab_Bean>()
//构造函数包含：名称，选中图标，未选中图标，消息条数，绑定的Fragment
Tab_Bean tab1 = new Tab_Bean("首页",R.mipmap.ic_launcher,R.mipmap.ic_launcher,5,MainFragment.newInstance("首页"))
Tab_Bean tab2 = new Tab_Bean("推荐",R.mipmap.ic_launcher,R.mipmap.ic_launcher,0,MainFragment.newInstance("推荐"))
Tab_Bean tab3 = new Tab_Bean("个人中心",R.mipmap.ic_launcher,R.mipmap.ic_launcher,5,MainFragment.newInstance("个人中心"))
tabs.add(tab1)
tabs.add(tab2)
tabs.add(tab3)
//设置tabs
tfl.setMenus(tabs,this)
//tabs点击监听
tfl.setOnTabClickListener(new TabFragmentLayout.OnTabClickListener() {
            @Override
            public void onClick(position i) {
                ...
            }
        });
```
