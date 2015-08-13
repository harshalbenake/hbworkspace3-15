package com.esri.arcgis.android.samples.helloworld;

/**
 * Created by harshalbenake on 01/07/15.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapOnTouchListener;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.MultiPath;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.Polyline;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleFillSymbol;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol.STYLE;

public class DrawGraphicElements extends Activity {

    /*
     * ArcGIS Android elements
     */
    MapView mapView = null;
    ArcGISTiledMapServiceLayer tiledMapServiceLayer = null;
    GraphicsLayer graphicsLayer = null;
    MyTouchListener myListener = null;

    /*
     * Android UI elements
     */
    Button geometryButton;
    Button clearButton;
    TextView label;

    /*
     * Other elements that hold app state
     */
    String mapURL = "http://sampleserver1.arcgisonline.com/ArcGIS/rest/services/PublicSafety/PublicSafetyBasemap/MapServer";

    final String[] geometryTypes = new String[] { "Point", "Polyline",
            "Polygon" };

    int selectedGeometryIndex = -1;

    @SuppressWarnings("serial")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      setContentView(mapView);
        setContentView(R.layout.main_draw);
        /*
         * Initialize ArcGIS Android MapView, tiledMapServiceLayer, and Graphics
         * Layer
         */
//      mapView = new MapView(this);
        mapView = (MapView)findViewById(R.id.map);
        myListener = new MyTouchListener(DrawGraphicElements.this, mapView);
        mapView.setOnTouchListener(myListener);

        /*
         * Initialize Android Geometry Button
         */
        geometryButton = (Button) findViewById(R.id.geometrybutton);
        geometryButton.setEnabled(false);
        geometryButton.setOnClickListener(new View.OnClickListener() {
            /*
             * This displays an AlertDilaog as defined in onCreateDialog()
             * method. Invocation of show() causes onCreateDialog() to be called
             * internally.
             */
            public void onClick(View v) {
                showDialog(0);
            }
        });

        label = (TextView) findViewById(R.id.label);

        clearButton = (Button) findViewById(R.id.clearbutton);
        clearButton.setEnabled(false);
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                graphicsLayer.removeAll();


                clearButton.setEnabled(false);
            }
        });

        /*
         * Initialize MapView, TiledMapServiceLayer and GraphicsLayer. This
         * block will be executed when app is started the first time.
         */
//      mapView.setExtent(new Envelope(-85.61828847183895, 38.19242311866144, -85.53589100936443, 38.31361605305102),0);

        tiledMapServiceLayer = new ArcGISTiledMapServiceLayer(mapURL);
        graphicsLayer = new GraphicsLayer();

        /*
         * Use TiledMapServiceLayer's OnStatusChangedListener to listen to
         * events such as change of status. This event allows developers to
         * check if layer is indeed initialized and ready for use, and take
         * appropriate action. In this case, we are modifying state of other UI
         * elements if and when the layer is loaded.
         */
        tiledMapServiceLayer
                .setOnStatusChangedListener(new OnStatusChangedListener() {
                    /*
                     * This callback method will be invokes when status of layer
                     * changes
                     */
                    public void onStatusChanged(Object arg0, STATUS status) {
                        /*
                         * Check if layer's new status = INITIALIZED. If it is,
                         * initialize UI elements
                         */
                        if (status
                                .equals(OnStatusChangedListener.STATUS.INITIALIZED)) {
                            geometryButton.setEnabled(true);
                        }
                    }
                });

        /**
         * Add TiledMapServiceLayer and GraphicsLayer to map
         */
        mapView.addLayer(tiledMapServiceLayer);
        mapView.addLayer(graphicsLayer);
    }

    /*
     * MapView's touch listener
     */
    class MyTouchListener extends MapOnTouchListener {
        // ArrayList<Point> polylinePoints = new ArrayList<Point>();

        MultiPath poly;
        String type = "";
        Point startPoint = null;

        public MyTouchListener(Context context, MapView view) {
            super(context, view);
        }

        public void setType(String geometryType) {
            this.type = geometryType;
        }

        public String getType() {
            return this.type;
        }

        /*
         * Invoked when user single taps on the map view. This event handler
         * draws a point at user-tapped location, only after "Draw Point" is
         * selected from Spinner.
         *
         * @see
         * com.esri.android.map.MapOnTouchListener#onSingleTap(android.view.
         * MotionEvent)
         */
        public boolean onSingleTap(MotionEvent e) {
            if (type.length() > 1 && type.equalsIgnoreCase("POINT")) {
                graphicsLayer.removeAll();
                Graphic graphic = new Graphic(mapView.toMapPoint(new Point(e.getX(), e
                        .getY())),new SimpleMarkerSymbol(Color.RED,25,STYLE.CIRCLE));
                //graphic.setGeometry();
                graphicsLayer.addGraphic(graphic);

                clearButton.setEnabled(true);
                return true;
            }
            return false;

        }

        /*
         * Invoked when user drags finger across screen. Polygon or Polyline is
         * drawn only when right selected is made from Spinner
         *
         * @see
         * com.esri.android.map.MapOnTouchListener#onDragPointerMove(android
         * .view.MotionEvent, android.view.MotionEvent)
         */
        public boolean onDragPointerMove(MotionEvent from, MotionEvent to) {
            if (type.length() > 1
                    && (type.equalsIgnoreCase("POLYLINE") || type
                    .equalsIgnoreCase("POLYGON"))) {

                Point mapPt = mapView.toMapPoint(to.getX(), to.getY());

                /*
                 * if StartPoint is null, create a polyline and start a path.
                 */
                if (startPoint == null) {
                    graphicsLayer.removeAll();
                    poly = type.equalsIgnoreCase("POLYLINE") ? new Polyline()
                            : new Polygon();
                    startPoint = mapView.toMapPoint(from.getX(), from.getY());
                    poly.startPath((float) startPoint.getX(),
                            (float) startPoint.getY());

                    /*
                     * Create a Graphic and add polyline geometry
                     */
                    Graphic graphic = new Graphic(startPoint,new SimpleLineSymbol(Color.RED,5));

                    /*
                     * add the updated graphic to graphics layer
                     */
                    graphicsLayer.addGraphic(graphic);
                }

                poly.lineTo((float) mapPt.getX(), (float) mapPt.getY());

                return true;
            }
            return super.onDragPointerMove(from, to);

        }

        @Override
        public boolean onDragPointerUp(MotionEvent from, MotionEvent to) {
            if (type.length() > 1
                    && (type.equalsIgnoreCase("POLYLINE") || type
                    .equalsIgnoreCase("POLYGON"))) {

                /*
                 * When user releases finger, add the last point to polyline.
                 */
                if (type.equalsIgnoreCase("POLYGON")) {
                    poly.lineTo((float) startPoint.getX(),
                            (float) startPoint.getY());
                    graphicsLayer.removeAll();
                    graphicsLayer.addGraphic(new Graphic(poly,new SimpleFillSymbol(Color.RED)));

                }
                graphicsLayer.addGraphic(new Graphic(poly,new SimpleLineSymbol(Color.BLUE,5)));
                startPoint = null;
                clearButton.setEnabled(true);
                return true;
            }
            return super.onDragPointerUp(from, to);
        }
    }

    /*
     * Returns an AlertDialog that includes names of all layers in the map
     * service
     */
    protected Dialog onCreateDialog(int id) {
        return new AlertDialog.Builder(DrawGraphicElements.this)
                .setTitle("Select Geometry")
                .setItems(geometryTypes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        graphicsLayer.removeAll();

                        // ignore first element
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM, 0, 0);

                        // Get item selected by user.
                        String geomType = geometryTypes[which];
                        label.setText(geomType + " selected.");
                        selectedGeometryIndex = which;

                        // process user selection
                        if (geomType.equalsIgnoreCase("Polygon")) {
                            myListener.setType("POLYGON");
                            toast.setText("Drag finger across screen to draw a Polygon. \nRelease finger to stop drawing.");
                        } else if (geomType.equalsIgnoreCase("Polyline")) {
                            myListener.setType("POLYLINE");
                            toast.setText("Drag finger across screen to draw a Polyline. \nRelease finger to stop drawing.");
                        } else if (geomType.equalsIgnoreCase("Point")) {
                            myListener.setType("POINT");
                            toast.setText("Tap on screen once to draw a Point.");
                        }

                        toast.show();
                    }
                }).create();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mapView.pause();
    }
    @Override   protected void onResume() {
        super.onResume();
        mapView.unpause();
    }

}

