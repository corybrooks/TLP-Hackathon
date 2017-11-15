package com.example.rickjames.eraticators.controller;

import android.app.DatePickerDialog;
import android.os.Bundle;
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

    private EditText startDateText;
    private EditText endDateText;
    private GraphView graph;
    private Map<String,Integer> map;
    private DataPoint[] dataPoints;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference daysRef = database
            .getReference().child("DAYS_TABLE");
    //private Query query = daysRef.startAt(startDateText.getText().toString()).endAt(endDateText.getText().toString());

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        graph = findViewById(R.id.graph);

        Button startDateButton = findViewById(R.id.startDate);
        startDateText = findViewById(R.id.startDateText);
        Button endDateButton = findViewById(R.id.endDate);
        endDateText = findViewById(R.id.endDateText);
        Button graphRatsButton = findViewById(R.id.graphRats);
        final Map<String, Integer> ratCounter = new HashMap<>();
        final boolean[] doneCounting = {false};


        daysRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(GraphActivity.this, "snapshot children" + ": " + dataSnapshot.getChildrenCount(), Toast.LENGTH_LONG).show();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    String key = d.getKey();
                    String newDate = key.substring(6) + "-" + key.substring(0,2) + "-" + key.substring(3,5);
                    //ratCounter.put(key, (int) (long) d.getChildrenCount());
                    ratCounter.put(newDate, (int) (long) d.getChildrenCount());
                }
                doneCounting[0] = true;
                while(!doneCounting[0]) {

                }
                map = new TreeMap<>(ratCounter);
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
                //graph.getGridLabelRenderer().setPadding(1);
                String startText = startDateText.getText().toString();
                String newStart = startText.substring(6) + "-" + startText.substring(0,2) + "-" + startText.substring(3,5);

                String endText = endDateText.getText().toString();
                String newEnd = endText.substring(6) + "-" + endText.substring(0,2) + "-" + endText.substring(3,5);

                SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                ArrayList<DataPoint> dataPointsAL = new ArrayList<>();
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
                for (String key: map.keySet()) {
                    Date curDate = null;
                    try {
                        curDate = sd1.parse(key);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (!curDate.before(startDate) && !curDate.after(endDate)) {
                        dataPointsAL.add(new DataPoint(curDate, map.get(key)));
                    }

                }

                dataPoints = new DataPoint[dataPointsAL.size()];
                dataPoints = dataPointsAL.toArray(dataPoints);
                Toast.makeText(GraphActivity.this, "Done finding days" + ": " + dataPoints.length, Toast.LENGTH_LONG).show();
                BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);
                graph.addSeries(series);
                graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(GraphActivity.this));
                graph.getGridLabelRenderer().setNumHorizontalLabels(dataPoints.length);


                graph.getViewport().setMinX(startDate.getTime());
                graph.getViewport().setMaxX(endDate.getTime());
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
