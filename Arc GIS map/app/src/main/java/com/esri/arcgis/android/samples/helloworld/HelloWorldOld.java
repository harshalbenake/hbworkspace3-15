/* Copyright 2012 ESRI
 *
 * All rights reserved under the copyright laws of the United States
 * and applicable international laws, treaties, and conventions.
 *
 * You may freely redistribute and use this sample code, with or
 * without modification, provided you include the original copyright
 * notice and use restrictions.
 *
 * See the Sample code usage restrictions document for further information.
 *
 */

package com.esri.arcgis.android.samples.helloworld;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;

/**
 * The HelloWorld app is the most basic Map app for the ArcGIS Runtime SDK for Android. It shows how to define a MapView
 * in the layout XML of the activity. Within the XML definition of the MapView, MapOptions attributes are used to
 * populate that MapView with a basemap layer showing streets, and also the initial extent and zoom level are set. By
 * default, this map supports basic zooming and panning operations. This sample also demonstrates calling the MapView
 * pause and unpause methods from the Activity onPause and onResume methods, which suspend and resume map rendering
 * threads. A reference to the MapView is set within the onCreate method of the Activity which can be used at the
 * starting point for further coding.
 */

public class HelloWorldOld extends Activity{
    MapView mMapView;

    // Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // After the content of this Activity is set, the map can be accessed programmatically from the layout.
        mMapView = (MapView) findViewById(R.id.map);

        GraphicsLayer graphicsLayer = new GraphicsLayer();

        // create a point marker symbol (red, size 10, of type circle)
        SimpleMarkerSymbol simpleMarker = new SimpleMarkerSymbol(Color.RED, 5, SimpleMarkerSymbol.STYLE.CIRCLE);

// create a point at x=-302557, y=7570663 (for a map using meters as units; this depends         // on the spatial reference)
        Point pointGeometry = new Point(-302557, 7570663);

// create a graphic with the geometry and marker symbol
        Graphic pointGraphic = new Graphic(pointGeometry, simpleMarker);

// add the graphic to the graphics layer
         graphicsLayer.addGraphic(pointGraphic);
// Create and add a GraphicsLayer


        // create a line symbol (green, 3 thick and a dash style)
        SimpleLineSymbol lineSymbol = new SimpleLineSymbol(Color.BLUE, 5, SimpleLineSymbol.STYLE.SOLID);

// create the line geometry
        Polyline lineGeometry = new Polyline();
        lineGeometry.startPath(-302557, 7570663);
        lineGeometry.lineTo(-302959, 7570868);
        lineGeometry.lineTo(-303042, 7571220);
        lineGeometry.lineTo(-302700, 7571803);
        lineGeometry.lineTo(-304043, 7576654);
        lineGeometry.lineTo(-300544, 7585289);
        lineGeometry.lineTo(-294365, 7592435);
        lineGeometry.lineTo(-290122, 7594445);
        lineGeometry.lineTo(-285283, 7595488);

        System.out.println("lineGeometry.getDimension(): "+lineGeometry.getDimension());
        System.out.println("lineGeometry.calculateLength2D(): "+lineGeometry.calculateLength2D());
        System.out.println("lineGeometry.getSegmentCount(): "+lineGeometry.getSegmentCount(0));


        Point pointNew = new Point(-302959, 7570868);
        Envelope envelope =new Envelope(pointNew);
//        System.out.println("Proximity2DResult: "+mMapView.getSpatialReference().getGCS());
// create the graphic using the geometry and the symbol
        Graphic lineGraphic = new Graphic(lineGeometry, lineSymbol);
// add the graphic to the graphics layer
        graphicsLayer.addGraphic(lineGraphic);

//      Point point=new Point(-302557, 7570663);
//      mMapView.zoomToScale(point,1500);




      /*  Unit unit = spatialRef.getUnit();
        double adjustedAccuracy = bufferDist;
        if (unit.getUnitType() == UnitType.ANGULAR) {
            adjustedAccuracy = metersToDegrees(bufferDist);
        } else {
            unit = Unit.create(LinearUnit.Code.METER);
        }
// get the result polygon from the buffer operation
        Polygon p = GeometryEngine.buffer(geom[0], spatialRef,
                adjustedAccuracy, unit);
// Render the polygon on the result graphic layer
        SimpleFillSymbol sfs = new SimpleFillSymbol(Color.GREEN);
        sfs.setOutline(new SimpleLineSymbol(Color.RED, 4,
                com.esri.core.symbol.SimpleLineSymbol.STYLE.SOLID));
        sfs.setAlpha(25);
        Graphic g = new Graphic(p, sfs);
        resultGeomLayer.addGraphic(g);*/



        Point pointStart = new Point(-302557, 7570663);
        Point pointEnd = new Point(-302959, 7570868);

        double angle=GetAngleOfLineBetweenTwoPoints(pointStart,pointEnd);

        System.out.println("angle: "+angle);



        mMapView.addLayer(graphicsLayer);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Call MapView.pause to suspend map rendering while the activity is paused, which can save battery usage.
        if (mMapView != null) {
            mMapView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Call MapView.unpause to resume map rendering when the activity returns to the foreground.
        if (mMapView != null) {
            mMapView.unpause();
        }
    }

    public double GetAngleOfLineBetweenTwoPoints(Point p1, Point p2) {
        double xDiff = p2.getX() - p1.getX(); double yDiff = p2.getY() - p1.getY();
        return Math.toDegrees(Math.atan2(yDiff, xDiff));
    }

}