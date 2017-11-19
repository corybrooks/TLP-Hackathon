package com.example.rickjames.eraticators.controller;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rickjames.eraticators.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;


public class GraphActivity extends AppCompatActivity {

    private Button startDateButton;
    private EditText startDateText;
    private Button endDateButton;
    private EditText endDateText;
    private Button graphRatsButton;
    private GraphView graph;
    private Map<String,Integer> map;
    private Map<String,Integer> monthMap;
    private DataPoint[] dataPoints;
    private DataPoint[] monthDataPoints;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference daysRef = database
            .getReference().child("DAYS_TABLE");
    //private Query query = daysRef.startAt(startDateText.getText().toString()).endAt(endDateText.getText().toString());

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        graph = (GraphView) findViewById(R.id.graph);

        startDateButton = (Button) findViewById(R.id.startDate);
        startDateText = (EditText) findViewById(R.id.startDateText);
        endDateButton = (Button) findViewById(R.id.endDate);
        endDateText = (EditText) findViewById(R.id.endDateText);
        graphRatsButton = (Button) findViewById(R.id.graphRats);
        final Map<String, Integer> ratCounter = new HashMap<String, Integer>();
        final Map<String, Integer> monthCounter = new HashMap<String, Integer>();
        final boolean[] doneCounting = {false};


        daysRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(GraphActivity.this, "Got snapshot from database", Toast.LENGTH_LONG).show();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    String key = d.getKey();
                    String newDate = key.substring(6) + "-" + key.substring(0,2) + "-" + key.substring(3,5);
                    String month = key.substring(0,2);

                    //ratCounter.put(key, (int) (long) d.getChildrenCount());
                    ratCounter.put(newDate, (int) (long) d.getChildrenCount());
                }
                doneCounting[0] = true;
                while(!doneCounting[0]) {

                }
                map = new TreeMap<String,Integer>(ratCounter);
                monthMap = new TreeMap<String,Integer>(monthCounter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        graphRatsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                graph.removeAllSeries();
                graph.getGridLabelRenderer().setLabelsSpace(10);
                if (startDateText.getText().toString().equals("")  && endDateText.getText().toString().equals("")) {
                    Toast.makeText(GraphActivity.this, "Both dates are empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (startDateText.getText().toString().equals("")) {
                    Toast.makeText(GraphActivity.this, "Start date is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (endDateText.getText().toString().equals("")) {
                    Toast.makeText(GraphActivity.this, "End date is empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!startDateText.getText().toString().equals("")  && !endDateText.getText().toString().equals("")) {
                    Toast.makeText(GraphActivity.this, "Valid dates", Toast.LENGTH_LONG).show();
                    return;
                }
                //graph.getGridLabelRenderer().setPadding(1);
                String startText = startDateText.getText().toString();
                String newStart = startText.substring(6) + "-" + startText.substring(0,2) + "-" + startText.substring(3,5);

                String endText = endDateText.getText().toString();
                String newEnd = endText.substring(6) + "-" + endText.substring(0,2) + "-" + endText.substring(3,5);

                SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
                ArrayList<DataPoint> dataPointsAL = new ArrayList<DataPoint>();
                Date startDate = null;
                Date endDate = null;
                try {
                    startDate = sd1.parse(newStart);
                    endDate = sd1.parse(newEnd);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(GraphActivity.this, "what days work.." + ": " + map.size(), Toast.LENGTH_LONG).show();
                while( map == null) {

                }
                Map<Integer, Integer> monthCounter = new HashMap<Integer, Integer>();
                for (String key: map.keySet()) {
                    Date curDate = null;
                    try {
                        curDate = sd1.parse(key);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (!curDate.before(startDate) && !curDate.after(endDate)) {
                        dataPointsAL.add(new DataPoint(curDate, map.get(key)));
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(curDate);
                        int month = cal.get(Calendar.MONTH) + 1;
                        if (monthCounter.containsKey(month)) {
                            monthCounter.put(month, monthCounter.get(month)  +  map.get(key));
                        } else {
                            monthCounter.put(month, map.get(key));
                        }
                    }

                }
                ArrayList<DataPoint> monthDataPointsAL = new ArrayList<DataPoint>();
                for(Integer month: monthCounter.keySet()) {
                    monthDataPointsAL.add(new DataPoint(month, monthCounter.get(month)));
                }
                monthDataPoints = new DataPoint[monthDataPointsAL.size()];
                monthDataPoints = dataPointsAL.toArray(monthDataPoints);







                dataPoints = new DataPoint[dataPointsAL.size()];
                dataPoints = dataPointsAL.toArray(dataPoints);
                //Toast.makeText(GraphActivity.this, "Done finding days" + ": " + dataPoints.length, Toast.LENGTH_LONG).show();


                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);
                series.setSpacing(50);
                graph.addSeries(series);
                graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(GraphActivity.this));
                graph.getGridLabelRenderer().setNumHorizontalLabels(dataPoints.length);
                graph.getGridLabelRenderer().setHorizontalLabelsAngle(90);
                //graph.getGridLabelRenderer().setLabelsSpace(150);
                graph.getGridLabelRenderer().setPadding(30);
                graph.getGridLabelRenderer().setLabelHorizontalHeight(150);


                graph.getViewport().setMinX(startDate.getTime());
                graph.getViewport().setMaxX(endDate.getTime());
                
                //graph.getViewport().setMinX(monthDataPoints[0].getX());
                //graph.getViewport().setMaxX(monthDataPoints[monthDataPoints.length - 1].getX());


                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setXAxisBoundsManual(true);
                graph.getViewport().setScalable(true);
                graph.getViewport().setScalableY(true);
                graph.getViewport().setScrollable(true);
                graph.getViewport().setScrollableY(true);
                graph.getGridLabelRenderer().setHumanRounding(true);


                /*BarGraphSeries <DataPoint> = new BarGraphSeries<>( new DataPoint[] {
                        for(String key: ratCounter.keySet()) {
                            new DataPoint(key, HashMap.get(key));
                        }
                });*/
            }
        });

        final Calendar myCalendar = Calendar.getInstance();
        final String[] whichDate = {null};
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                if (whichDate[0] == "START") {
                    startDateText.setText(sdf.format(myCalendar.getTime()));
                }
                else {
                    endDateText.setText(sdf.format(myCalendar.getTime()));
                }

            }

        };
        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichDate[0] = "START";
                new DatePickerDialog(GraphActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichDate[0] = "END";
                new DatePickerDialog(GraphActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }
}
