package com.esri.arcgis.android.samples.helloworld;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleLineSymbol;

import java.util.ArrayList;

// Sample Screenshot
// http://pic.twitter.com/Sty6eO3iC5
//
// See IntellIJ / Android Studio Setup (Use Map Template = maven base project for Android Studio)
// http://webcache.googleusercontent.com/search?q=cache:R0f3WeVp_3sJ:blogs.esri.com/esri/arcgis/2012/10/26/developing-with-the-arcgis-runtime-sdk-for-android-and-intellij-idea/+&cd=1&hl=en&ct=clnk&gl=us
//
// Hello World
// http://help.arcgis.com/en/arcgismobile/10.0/apis/android/help/index.html#/Hello_World_Map/011900000005000000/
//
// Drawing Example
// http://www.arcgis.com/home/item.html?id=edff48a48de2400aaa67917fdb3b8a58
public class MyActivity extends Activity{
    MapView mMapView;
    GraphicsLayer mGraphicsLayer;
    ArrayList<Point> mArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mMapView = (MapView)findViewById(R.id.map);
        //http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer
        mMapView.addLayer(new ArcGISTiledMapServiceLayer("http://server.arcgisonline.com/ArcGIS/rest/services/ESRI_StreetMap_World_2D/MapServer"));

//        ArcGISDynamicMapServiceLayer waterlinLayer = new ArcGISDynamicMapServiceLayer("http://arcgis-cloudpointv4-1216889134.us-east-1.elb.amazonaws.com/arcgis/rest/services/roanokeVillageAreas/MapServer<");
//        mMapView.addLayer(waterlinLayer);

        mGraphicsLayer = new GraphicsLayer();
        mMapView.addLayer(mGraphicsLayer);

        mArrayList = new ArrayList<Point>();

        mMapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                mGraphicsLayer.removeAll();


                Point point = mMapView.toMapPoint(new Point(motionEvent.getX(), motionEvent.getY()));
                mArrayList.add(point);


                Polyline polyline = new Polyline();

                polyline.startPath(mArrayList.get(0));
                for (int i = 1; i < mArrayList.size(); i++) {
                    polyline.lineTo(mArrayList.get(i));

                    double angle = GetAngleOfLineBetweenTwoPoints(mArrayList.get(0), mArrayList.get(i));

                    System.out.println("angle:"+angle);

                }



                Graphic graphic = new Graphic(polyline, new SimpleLineSymbol(Color.BLUE,4));

                mGraphicsLayer.addGraphic(graphic);
                return false;
            }
        });
    }

    protected void onPause() {
        super.onPause();
        if (mMapView!=null){
            mMapView.pause();
        }
    }

    protected void onResume() {
        super.onResume();
        if (mMapView!=null){
            mMapView.unpause();
        }
    }

    public double GetAngleOfLineBetweenTwoPoints(Point p1, Point p2) {
        double xDiff = p2.getX() - p1.getX(); double yDiff = p2.getY() - p1.getY();
        return Math.toDegrees(Math.atan2(yDiff, xDiff));
    }
}
