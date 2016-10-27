package com.example.gustavs.hellolifecycles;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

//TODO: save image filenames to DB; crop image?; reset; keep images when switching away (pause->stop (->restart->start->resume) )
public class MainActivity extends AppCompatActivity {
    private static final int PICKFILE_1_REQUEST_CODE = 1;
    private static final int PICKFILE_2_REQUEST_CODE = 2;
    private static final int PICKFILE_3_REQUEST_CODE = 3;
    private static final int PICKFILE_4_REQUEST_CODE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate()");
        setContentView(R.layout.activity_main);
        registerForContextMenu(findViewById(R.id.top_left));
        registerForContextMenu(findViewById(R.id.top_right));
        registerForContextMenu(findViewById(R.id.bottom_left));
        registerForContextMenu(findViewById(R.id.bottom_right));
        //createAndPopulateDB();
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
        //deleteDB();
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
        Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    // Creates four different context menus
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        String menuTitle;
        int menuID;
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

    //lets user pick pictures from gallery
    public void onClickPicture(View view) {
        System.out.println("onClickPicture()");
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);//ACTION_PICK||ACTION_GET_CONTENT
        intent.setType("image/*");
        switch (view.getId()) {
            case R.id.top_left:
                startActivityForResult(intent, PICKFILE_1_REQUEST_CODE);
                break;
            case R.id.top_right:
                startActivityForResult(intent, PICKFILE_2_REQUEST_CODE);
                break;
            case R.id.bottom_left:
                startActivityForResult(intent, PICKFILE_3_REQUEST_CODE);
                break;
            case R.id.bottom_right:
                startActivityForResult(intent, PICKFILE_4_REQUEST_CODE);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("onActivityResult()");
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PICKFILE_1_REQUEST_CODE:
                    setImage(data, R.id.top_left);
                    break;
                case PICKFILE_2_REQUEST_CODE:
                    setImage(data, R.id.top_right);
                    break;
                case PICKFILE_3_REQUEST_CODE:
                    setImage(data, R.id.bottom_left);
                    break;
                case PICKFILE_4_REQUEST_CODE:
                    setImage(data, R.id.bottom_right);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // sets textview background
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setImage(Intent data, int viewID) {
        Uri yourUri = data.getData();
        InputStream inputStream;
        Drawable d;
        try {
            inputStream = getContentResolver().openInputStream(yourUri);
            d = Drawable.createFromStream(inputStream, yourUri.toString() );
            findViewById(viewID).setBackground(d);
            ((TextView) findViewById(viewID)).setTextColor(Color.TRANSPARENT);
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(), R.string.file_not_found, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
