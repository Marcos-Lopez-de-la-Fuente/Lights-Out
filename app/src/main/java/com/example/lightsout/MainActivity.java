package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    // Attributes
    private GridLayout gridParent; // GridLayout parent
    private Drawable items; // Elements in gridLayoutMain

    // GestureDetector.OnGestureListener
    private int swipeMinDistance;
    private int swipeThresholdVelocity;
    private GestureDetectorCompat gestureDetectorCompat;


    // Buttons to examples
    private Button threeXThree;
    private Button fourXFour;
    private Button fiveXFive;
    private Button sixXSix;


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Set atributes
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        this.setGridParent(this.findViewById(R.id.gridParent));
        this.setItems(this.getDrawable(R.drawable.item));

        this.setSwipeMinDistance(80);
        this.setSwipeThresholdVelocity(50);
        this.setGestureDetectorCompat(new GestureDetectorCompat(this.getApplicationContext(), this));


        this.setThreeXThree(this.findViewById(R.id.threeXThree));
        this.setFourXFour(this.findViewById(R.id.fourXFour));
        this.setFiveXFive(this.findViewById(R.id.fiveXFive));
        this.setSixXSix(this.findViewById(R.id.sixXSix));


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Buttons to examples
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        this.getThreeXThree().setOnClickListener(view ->
                this.generateGridLayout(3)
        );

        this.getFourXFour().setOnClickListener(view ->
                this.generateGridLayout(3)
        );

        this.getFiveXFive().setOnClickListener(view ->
                this.generateGridLayout(3)
        );

        this.getSixXSix().setOnClickListener(view ->
                this.generateGridLayout(3)
        );

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Configure GridLayout and its TextViews
     *
     * @param sizeColumnsAndRows Size chosen by the user
     */
    public void generateGridLayout(int sizeColumnsAndRows) {


        GridLayout gridLayout = this.createGridLayoutAndParams();

        // Setting size of GridLayout
        this.attributesGridLayout(gridLayout, sizeColumnsAndRows);

        // Fill GridLayout with TextViews
        this.fillGridLayout(gridLayout, sizeColumnsAndRows);
    }


    public GridLayout createGridLayoutAndParams() {

        GridLayout gridLayout = this.createGridLayout();
        GridLayout.LayoutParams layoutParams = this.createGridLayoutParams();

        this.getGridParent().addView(gridLayout, layoutParams);

        return gridLayout;
    }


    public GridLayout createGridLayout() {

        GridLayout gridLayout = new GridLayout(this);

        gridLayout.setBackground(this.getDrawable(R.drawable.backgrid));

        return gridLayout;
    }


    public GridLayout.LayoutParams createGridLayoutParams() {


        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(
                GridLayout.spec(5, 1),
                GridLayout.spec(0, 3)
        );

        layoutParams.width = GridLayout.LayoutParams.MATCH_PARENT;
        layoutParams.setMargins(0, 20, 0, 0);

        return layoutParams;
    }


    /**
     * Configure columns and rows of the GridLayout
     *
     * @param gridLayout
     * @param sizeColumnsAndRows Size chosen by the user
     */
    public void attributesGridLayout(GridLayout gridLayout, int sizeColumnsAndRows) {
        gridLayout.setColumnCount(sizeColumnsAndRows);
        gridLayout.setRowCount(sizeColumnsAndRows);
    }


    public void fillGridLayout(GridLayout gridLayout, int sizeColumnsAndRows) {
        for (int i = 0; i < sizeColumnsAndRows; i++) {
            for (int j = 0; j < sizeColumnsAndRows; j++) {
                this.createTextViewIntoGridLayout(gridLayout, j, i, sizeColumnsAndRows);
            }
        }
    }


    /**
     * Fill all the boxes of the GridLayout with TextViews
     *
     * @param gridLayout
     * @param column             Column of GridLayout to create the new TextView
     * @param row                Row of GridLayout to create the new TextView
     * @param sizeColumnsAndRows Size chosen by the user
     */
    public void createTextViewIntoGridLayout(GridLayout gridLayout, int column, int row, int sizeColumnsAndRows) {
        // TextView to Add
        TextView textView = this.createTextViewToGridLayout(sizeColumnsAndRows);

        // LayoutParams of GridLayout
        GridLayout.LayoutParams layoutParams = this.createLayoutParamsToGridLayout(column, row, sizeColumnsAndRows);

        gridLayout.addView(textView, layoutParams);
    }

    /**
     * Create TextView with attributes of fill GridLayout
     *
     * @param sizeColumnsAndRows Size chosen by the user
     * @return TextView configured
     */
    public TextView createTextViewToGridLayout(int sizeColumnsAndRows) {
        TextView textView = new TextView(this);

        textView.setBackground(this.getItems());
        textView.setGravity(Gravity.CENTER);

        // Por ahora no usaré el siguiente método (Cuando toque estilizar el programa jugaré con este método y el choicePaddingText())
        //textView.setTextSize(this.choiceTextSize(sizeColumnsAndRows));
        int textPadding = this.choicePaddingText(sizeColumnsAndRows);

        textView.setPadding(0, textPadding, 0, textPadding);

        return textView;
    }


    /**
     * Choose the size of the text according to the size chosen by the user
     *
     * @param sizeColumnsAndRows Size chosen by the users
     * @return Size of the text
     */
    public int choiceTextSize(int sizeColumnsAndRows) {
        Map<Integer, Integer> texSize = Map.of(
                3, 45,
                4, 40,
                5, 30,
                6, 20
        );

        return texSize.get(sizeColumnsAndRows);
    }


    public int choicePaddingText(int sizeColumnsAndRows) {
        Map<Integer, Integer> paddingText = Map.of(
                3, 100,
                4, 70,
                5, 50,
                6, 40
        );

        return paddingText.get(sizeColumnsAndRows);
    }


    /**
     * Create LayoutParams with attributes to TextView, to fill GridLayout
     *
     * @param column             Column of GridLayout to create the new TextView
     * @param row                Row of GridLayout to create the new TextView
     * @param sizeColumnsAndRows Size chosen by the user
     * @return LayoutParams to add the new TextView
     */
    public GridLayout.LayoutParams createLayoutParamsToGridLayout(int column, int row, int sizeColumnsAndRows) {
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(
                GridLayout.spec(row, 1f),
                GridLayout.spec(column, 1f)
        );

        int margins = this.choiceMarginSize(sizeColumnsAndRows);
        layoutParams.setMargins(margins, margins, margins, margins);

        return layoutParams;
    }


    /**
     * Choose the margin of the TextViews according to the size chosen by the user
     *
     * @param sizeColumnsAndRows Size chosen by the user
     * @return Margin of the TextView
     */
    public int choiceMarginSize(int sizeColumnsAndRows) {
        Map<Integer, Integer> marginSize = Map.of(
                3, 25,
                4, 20,
                5, 15,
                6, 10
        );

        return marginSize.get(sizeColumnsAndRows);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GestureDetector.OnGestureListener
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean userFling = false;

        float distanceLeft = e1.getX() - e2.getX();
        float distanceRight = e2.getX() - e1.getX();
        float distanceTop = e1.getY() - e2.getY();
        float distanceBottom = e2.getY() - e1.getY();


        if (Math.abs(distanceLeft) > Math.abs(distanceTop)) {
            if (this.flingOptimal(distanceLeft, velocityX)) {
                this.flingDirectionsControler(StateDirection.LEFT);
                userFling = true;

            } else if (this.flingOptimal(distanceRight, velocityX)) {
                this.flingDirectionsControler(StateDirection.RIGHT);
                userFling = true;

            }
        } else {
            if (this.flingOptimal(distanceTop, velocityY)) {
                this.flingDirectionsControler(StateDirection.TOP);
                userFling = true;

            } else if (this.flingOptimal(distanceBottom, velocityY)) {
                this.flingDirectionsControler(StateDirection.BOTTOM);
                userFling = true;
            }
        }


        return false;
    }


    // Necessary to detect the method onFling
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.gestureDetectorCompat.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }


    /**
     * Check if the distance and velocity from method onFling() are optimal to make a action
     *
     * @param distance Previously calculated distance
     * @param velocity Velocity from method onFling()
     * @return If is optimal to make a action
     */
    public boolean flingOptimal(float distance, float velocity) {
        return (distance > this.getSwipeMinDistance() && Math.abs(velocity) > this.getSwipeThresholdVelocity());
    }


    /**
     * Controller of Fling from user
     *
     * @param stateDirection Direction choice of user
     */
    public void flingDirectionsControler(StateDirection stateDirection) {
        Map<StateDirection, String> direction = Map.of(
                StateDirection.TOP, "TOP",
                StateDirection.BOTTOM, "BOTTOM",
                StateDirection.LEFT, "LEFT",
                StateDirection.RIGHT, "RIGHT"
        );

        Toast.makeText(getApplicationContext(), direction.get(stateDirection), Toast.LENGTH_SHORT).show();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Getters and Setters
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public GridLayout getGridParent() {
        return gridParent;
    }

    public void setGridParent(GridLayout gridParent) {
        this.gridParent = gridParent;
    }

    public Drawable getItems() {
        return items;
    }

    public void setItems(Drawable items) {
        this.items = items;
    }

    public int getSwipeMinDistance() {
        return swipeMinDistance;
    }

    public void setSwipeMinDistance(int swipeMinDistance) {
        this.swipeMinDistance = swipeMinDistance;
    }

    public int getSwipeThresholdVelocity() {
        return swipeThresholdVelocity;
    }

    public void setSwipeThresholdVelocity(int swipeThresholdVelocity) {
        this.swipeThresholdVelocity = swipeThresholdVelocity;
    }

    public GestureDetectorCompat getGestureDetectorCompat() {
        return gestureDetectorCompat;
    }

    public void setGestureDetectorCompat(GestureDetectorCompat gestureDetectorCompat) {
        this.gestureDetectorCompat = gestureDetectorCompat;
    }

    public Button getThreeXThree() {
        return threeXThree;
    }

    public void setThreeXThree(Button threeXThree) {
        this.threeXThree = threeXThree;
    }

    public Button getFourXFour() {
        return fourXFour;
    }

    public void setFourXFour(Button fourXFour) {
        this.fourXFour = fourXFour;
    }

    public Button getFiveXFive() {
        return fiveXFive;
    }

    public void setFiveXFive(Button fiveXFive) {
        this.fiveXFive = fiveXFive;
    }

    public Button getSixXSix() {
        return sixXSix;
    }

    public void setSixXSix(Button sixXSix) {
        this.sixXSix = sixXSix;
    }
}