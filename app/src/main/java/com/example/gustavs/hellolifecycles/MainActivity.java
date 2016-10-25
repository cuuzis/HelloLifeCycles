package com.example.gustavs.hellolifecycles;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;

//TODO: save image filenames to DB; load images
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate()");
        setContentView(R.layout.activity_main);
        registerForContextMenu(findViewById(R.id.top_left));
        registerForContextMenu(findViewById(R.id.top_right));
        registerForContextMenu(findViewById(R.id.bottom_left));
        registerForContextMenu(findViewById(R.id.bottom_right));
        //CreateAndPopulateDB();
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop()");
        //DeleteDB();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart()");
    }

    // Shows which item was selected in menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, item.getTitle(), duration);
        toast.show();
        return super.onContextItemSelected(item);
    }

    // Creates four different context menus
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        String menuTitle;
        Integer menuID;
        switch (v.getId()) {
            case R.id.top_left:
                menuID = R.menu.menu1;
                menuTitle = "Top Left";
                break;
            case R.id.top_right:
                menuID = R.menu.menu2;
                menuTitle = "Top Right";
                break;
            case R.id.bottom_left:
                menuID = R.menu.menu3;
                menuTitle = "Bottom Left";
                break;
            case R.id.bottom_right:
                menuID = R.menu.menu4;
                menuTitle = "Bottom Right";
                break;
            default:
                menuID = R.menu.menu1;
                menuTitle = "Some Menu";
        }
        MenuInflater inflater = getMenuInflater();
        menu.setHeaderTitle(menuTitle);
        inflater.inflate(menuID, menu);
    }
}
