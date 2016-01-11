package cn.syiyi.com.ipcdemp.launcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.syiyi.com.ipcdemp.R;
import cn.syiyi.com.ipcdemp.ibinder.advanced.AdvancedIbinderAC;
import cn.syiyi.com.ipcdemp.ibinder.base.BaseIbinderAC;
import cn.syiyi.com.ipcdemp.ibinder.google.GIbinderAC;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mData = new ArrayList<>();
        mData.add("Ibinder接口,最基础，最繁琐");
        mData.add("Ibinder接口进阶,仿系统的封装EIT造型，代码量大，可定制");
        mData.add("Ibinder谷歌模板adil生成,代码量最少，限制较大");
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setAdapter(new MyAdpter(this, mData));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        Intent intent = new Intent(MainActivity.this, BaseIbinderAC.class);
                        MainActivity.this.startActivity(intent);
                    }
                    break;
                    case 1: {
                        Intent intent = new Intent(MainActivity.this, AdvancedIbinderAC.class);
                        MainActivity.this.startActivity(intent);
                    }
                    break;
                    case 2: {
                        Intent intent = new Intent(MainActivity.this, GIbinderAC.class);
                        MainActivity.this.startActivity(intent);
                    }
                    break;
                }
            }
        });
    }
}
