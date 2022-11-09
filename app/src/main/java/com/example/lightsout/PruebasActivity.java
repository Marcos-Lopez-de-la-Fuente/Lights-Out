package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.Random;

public class PruebasActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private Button threeXThree;
    private Button fourXFour;
    private Button fiveXFive;
    private Button sixXSix;

    // Attributes
    private GridLayout gridParent;


    private GridLayout gridMain;
    private boolean gridChosen = false;
    private Drawable itemActivate;
    private Drawable itemDisable;

    private ImageButton[][] buttonsIntoGridMain;


    private int swipeMinDistance;
    private int swipeThresholdVelocity;
    private GestureDetectorCompat gestureDetectorCompat;


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Exercices", "PruebasActivity.java");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas);


        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        this.setThreeXThree(this.findViewById(R.id.threeXThree));
        this.setFourXFour(this.findViewById(R.id.fourXFour));
        this.setFiveXFive(this.findViewById(R.id.fiveXFive));
        this.setSixXSix(this.findViewById(R.id.sixXSix));


        this.setGridParent(this.findViewById(R.id.gridParent));


        this.setItemActivate(this.getDrawable(R.drawable.item_activate));
        this.setItemDisable(this.getDrawable(R.drawable.item_disable));


        this.setSwipeMinDistance(80);
        this.setSwipeThresholdVelocity(50);
        this.setGestureDetectorCompat(new GestureDetectorCompat(this.getApplicationContext(), this));


        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        this.getThreeXThree().setOnClickListener(view -> {
            this.generateGridLayout(3);
            this.setOnClickAllButtons();
            this.randomActivateButtons();
        });

        this.getFourXFour().setOnClickListener(view -> {
            this.generateGridLayout(4);
            this.setOnClickAllButtons();
            this.randomActivateButtons();
        });

        this.getFiveXFive().setOnClickListener(view -> {
            this.generateGridLayout(5);
            this.setOnClickAllButtons();
            this.randomActivateButtons();
        });

        this.getSixXSix().setOnClickListener(view -> {
            this.generateGridLayout(6);
            this.setOnClickAllButtons();
            this.randomActivateButtons();
        });

    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void randomActivateButtons() {
        for (int i = 0; i < this.buttonsIntoGridMain.length; i++) {
            for (int j = 0; j < this.buttonsIntoGridMain.length; j++) {

                if (new Random().nextBoolean()) {
                    this.buttonsIntoGridMain[i][j].setBackground(this.getItemActivate());
                }

            }
        }
    }


    public void setOnClickAllButtons() {
        for (int i = 0; i < this.buttonsIntoGridMain.length; i++) {
            for (int j = 0; j < this.buttonsIntoGridMain[i].length; j++) {

                int row = i;
                int column = j;
                this.buttonsIntoGridMain[i][j].setOnClickListener(view ->
                        this.clickAButton(row, column)
                );
            }
        }
    }


    public void clickAButton(int row, int column) {
        this.changeBackgroundButton(row, column);

        if (row == 0 && column == 0) {
            this.changeBackgroundButton(row, column + 1);
            this.changeBackgroundButton(row + 1, column);
            this.changeBackgroundButton(row + 1, column + 1);

        } else if (row == 0 && column == this.gridMain.getColumnCount() - 1) {
            this.changeBackgroundButton(row, column - 1);
            this.changeBackgroundButton(row + 1, column - 1);
            this.changeBackgroundButton(row + 1, column);

        } else if (row == this.gridMain.getRowCount() - 1 && column == 0) {
            this.changeBackgroundButton(row - 1, column);
            this.changeBackgroundButton(row - 1, column + 1);
            this.changeBackgroundButton(row, column + 1);

        } else if (row == this.gridMain.getRowCount() - 1 && column == this.gridMain.getColumnCount() - 1) {
            this.changeBackgroundButton(row - 1, column - 1);
            this.changeBackgroundButton(row - 1, column);
            this.changeBackgroundButton(row, column - 1);

        } else if (row == 0) {
            this.changeBackgroundButton(row, column - 1);
            this.changeBackgroundButton(row, column + 1);
            this.changeBackgroundButton(row + 1, column - 1);
            this.changeBackgroundButton(row + 1, column);
            this.changeBackgroundButton(row + 1, column + 1);

        } else if (row == this.gridMain.getRowCount() - 1) {
            this.changeBackgroundButton(row - 1, column - 1);
            this.changeBackgroundButton(row - 1, column);
            this.changeBackgroundButton(row - 1, column + 1);
            this.changeBackgroundButton(row, column - 1);
            this.changeBackgroundButton(row, column + 1);

        } else if (column == 0) {
            this.changeBackgroundButton(row - 1, column);
            this.changeBackgroundButton(row - 1, column + 1);
            this.changeBackgroundButton(row, column + 1);
            this.changeBackgroundButton(row + 1, column);
            this.changeBackgroundButton(row + 1, column + 1);

        } else if (column == this.gridMain.getColumnCount() - 1) {
            this.changeBackgroundButton(row - 1, column - 1);
            this.changeBackgroundButton(row - 1, column);
            this.changeBackgroundButton(row, column - 1);
            this.changeBackgroundButton(row + 1, column - 1);
            this.changeBackgroundButton(row + 1, column);

        } else {
            this.changeBackgroundButton(row - 1, column - 1);
            this.changeBackgroundButton(row - 1, column);
            this.changeBackgroundButton(row - 1, column + 1);
            this.changeBackgroundButton(row, column - 1);
            this.changeBackgroundButton(row, column + 1);
            this.changeBackgroundButton(row + 1, column - 1);
            this.changeBackgroundButton(row + 1, column);
            this.changeBackgroundButton(row + 1, column + 1);

        }
    }

    public void changeBackgroundButton(int row, int column) {
        if (this.buttonsIntoGridMain[row][column].getBackground() == this.getItemActivate()) {
            this.buttonsIntoGridMain[row][column].setBackground(this.getItemDisable());
        } else {
            this.buttonsIntoGridMain[row][column].setBackground(this.getItemActivate());
        }
    }


    /**
     * Configure GridLayout and its TextViews
     *
     * @param sizeColumnsAndRows Size chosen by the user
     */
    public void generateGridLayout(int sizeColumnsAndRows) {

        this.createGridLayoutAndParams();


        // Setting size of GridLayout
        this.atributesGridLayout(sizeColumnsAndRows);

        // Fill GridLayout with Buttons
        this.fillGridLayout(sizeColumnsAndRows);

    }

    public void createGridLayoutAndParams() {

        this.createGridLayout();

        GridLayout.LayoutParams layoutParams = this.createGridLayoutParams();

        this.gridParent.addView(this.gridMain, layoutParams);
    }

    public void createGridLayout() {
        if (this.gridChosen) {
            this.gridParent.removeView(this.gridMain);
        }
        this.gridChosen = true;

        this.gridMain = new GridLayout(this);
        this.gridMain.setBackground(getDrawable(R.drawable.backgrid));
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
     * @param sizeColumnsAndRows Size chosen by the user
     */
    public void atributesGridLayout(int sizeColumnsAndRows) {
        this.gridMain.setColumnCount(sizeColumnsAndRows);
        this.gridMain.setRowCount(sizeColumnsAndRows);
    }


    public void fillGridLayout(int sizeColumnsAndRows) {
        this.setButtonsIntoGridMain(new ImageButton[sizeColumnsAndRows][sizeColumnsAndRows]);

        for (int i = 0; i < sizeColumnsAndRows; i++) {
            for (int j = 0; j < sizeColumnsAndRows; j++) {
                this.createButtonIntoGridLayout(j, i, sizeColumnsAndRows);
            }
        }
    }


    /**
     * Fill all the boxes of the GridLayout with Buttons
     *
     * @param column             Column of GridLayout to create the new Button
     * @param row                Row of GridLayout to create the new Button
     * @param sizeColumnsAndRows Size chosen by the user
     */
    public void createButtonIntoGridLayout(int column, int row, int sizeColumnsAndRows) {

        // TextView to add
        ImageButton button = createButtonToGridLayout(sizeColumnsAndRows);
        button.setScaleType(ImageView.ScaleType.FIT_XY);

        // LayoutParams of GridLayout
        GridLayout.LayoutParams layoutParams = createLayoutParamsToGridLayout(column, row, sizeColumnsAndRows);


        this.gridMain.addView(button, layoutParams);
        this.getButtonsIntoGridMain()[row][column] = button;
    }


    /**
     * Create TextView with attributes to fill GridLayout
     *
     * @param sizeColumnsAndRows Size chosen by the user
     * @return TextView configured
     */
    public ImageButton createButtonToGridLayout(int sizeColumnsAndRows) {
        ImageButton button = new ImageButton(this);

        button.setBackground(this.getItemDisable());


        int buttonPadding = this.choicePaddingButton(sizeColumnsAndRows);
        button.setPadding(0, buttonPadding, 0, buttonPadding);


        return button;
    }


    public int choicePaddingButton(int sizeColumnsAndRows) {
        Map<Integer, Integer> paddingButton = Map.of(
                3, 100,
                4, 70,
                5, 50,
                6, 40
        );

        return paddingButton.get(sizeColumnsAndRows);
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


    ////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Controller of Flings from user
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


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // Detect direction from Fling
        boolean response = false;

        float distanceLeft = e1.getX() - e2.getX();
        float distanceRight = e2.getX() - e1.getX();
        float distanceTop = e1.getY() - e2.getY();
        float distanceBottom = e2.getY() - e1.getY();


        if (Math.abs(distanceLeft) > Math.abs(distanceTop)) {
            if (this.flingOptimal(distanceLeft, velocityX)) {
                this.flingDirectionsControler(StateDirection.LEFT);
                response = true;

            } else if (this.flingOptimal(distanceRight, velocityX)) {
                this.flingDirectionsControler(StateDirection.RIGHT);
                response = true;

            }
        } else {
            if (this.flingOptimal(distanceTop, velocityY)) {
                this.flingDirectionsControler(StateDirection.TOP);
                response = true;

            } else if (this.flingOptimal(distanceBottom, velocityY)) {
                this.flingDirectionsControler(StateDirection.BOTTOM);
                response = true;
            }
        }

        return response;
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Necessary to detect the method onFling
        if (this.gestureDetectorCompat.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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

    public GridLayout getGridParent() {
        return gridParent;
    }

    public void setGridParent(GridLayout gridParent) {
        this.gridParent = gridParent;
    }

    public GridLayout getGridMain() {
        return gridMain;
    }

    public void setGridMain(GridLayout gridMain) {
        this.gridMain = gridMain;
    }

    public boolean isGridChosen() {
        return gridChosen;
    }

    public void setGridChosen(boolean gridChosen) {
        this.gridChosen = gridChosen;
    }

    public Drawable getItemActivate() {
        return itemActivate;
    }

    public void setItemActivate(Drawable itemActivate) {
        this.itemActivate = itemActivate;
    }

    public Drawable getItemDisable() {
        return itemDisable;
    }

    public void setItemDisable(Drawable itemDisable) {
        this.itemDisable = itemDisable;
    }

    public ImageButton[][] getButtonsIntoGridMain() {
        return buttonsIntoGridMain;
    }

    public void setButtonsIntoGridMain(ImageButton[][] buttonsIntoGridMain) {
        this.buttonsIntoGridMain = buttonsIntoGridMain;
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
}