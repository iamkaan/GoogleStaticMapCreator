package com.iamkaan.orienteering101;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    int width;
    ImageView map;
    GameManager gameManager;
    double gameCenterLat, gameCenterLng;
    double smallMapCenterLat, smallMapCenterLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = (ImageView) findViewById(R.id.map);
        width = getScreenWidth();

        /**
         * burda oyunun merkezini belirlemek gerek.
         * bu sadece oyun oluşturulurken yapılıcak!
         */
        gameCenterLat = 37.421864;
        gameCenterLng = -122.084090;

        /**
         * burda da zoom yapılmış haritanın merkezi belirlenicek.
         * burası oyuncu haritayı her güncellediğinde değişicek
         */
        smallMapCenterLat = 37.428211;
        smallMapCenterLng = -122.085356;

        /**
         * oyunda bi tane game manager olucak, genel ayarları içericek.
         */
        gameManager = new GameManager(gameCenterLat, gameCenterLng);
        gameManager.setMiniMapSize(1000, 1000);
        gameManager.setZoom(16);

        gameManager.addMarker(new Marker()
                .setIconURL("http://www2.psd100.com/icon/2013/08/2701/Running-man-icon-0827112812.png")
                .setLocation(smallMapCenterLat, smallMapCenterLng));

        gameManager.addMarker(new Marker()
                .setIconURL("http://hydra-media.cursecdn.com/neverwinter.gamepedia.com/9/93/Icon_Inventory_Runestone_Serene_T1_01.png?version=7226ca2069eec585bf3a4d3a88879631")
                .setLocation(39.779014, 30.517056));

        MapCreator mapCreator = new MapCreator(gameManager);
        /**
         * burda center belirliyoruz çünkü oyunun (büyük haritanın) merkeziyle
         * zoom yapılmış haritanın merkezi aynı olmiycak her zaman.
         */
        mapCreator.setCenter(smallMapCenterLat, smallMapCenterLng);

        String url = mapCreator
                .create();

        System.out.println(url);

        Picasso.with(this)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .resize(width, width)
                .centerInside()
                .into(map);
    }

    private int getScreenWidth() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }
}
