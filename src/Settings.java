/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Adjust size and color of Snake(head, tail) and Food
 * @author Owner
 */
public class Settings extends GridPane {
    
    // values to be added to color/size lists, which will be used by the Snake and Food class
    private int r;
    private int g;
    private int b;
    private double a;
    private double size;
    
    // color lists
    private ArrayList<Color> headColors;
    private ArrayList<Color> tailColors;
    private ArrayList<Color> foodColors;
    
    // size lists
    private ArrayList<Double> headSizes;
    private ArrayList<Double> tailSizes;
    private ArrayList<Double> foodSizes;
    
    // color options
    private CheckBox colorGrad;
    private Slider colorStepSlider;
    
    // site options
    private CheckBox sizeGrad;
    private Slider sizeStepSlider;
    
    // game options
    private CheckBox headUnique;
    private CheckBox sequence;
    private CheckBox frozen;
    private CheckBox headUniqueSize;
    private CheckBox sequenceSize;
    private CheckBox frozenSize;

    private Game gameApp;
    private MainMenu menu;
    private Stage stage;

    // color sliders
    private Slider rSlider;
    private Slider gSlider;
    private Slider bSlider;
    private Slider aSlider;
    
    // color text fields
    private TextField rField;
    private TextField gField;
    private TextField bField;
    private TextField aField;
    
    // category radio buttons
    private RadioButton headRadio;
    private RadioButton tailRadio;
    private RadioButton foodRadio;
    
    // previews for color/size for head, tail, and food
    private SettingsPreview head;
    private SettingsPreview tail;
    private SettingsPreview food;
            
    public Settings(Stage stage, MainMenu menu, Game gameApp) {
    
        this.gameApp = gameApp;
        this.menu = menu;
        this.stage = stage;
        
        headColors = new ArrayList<Color>();
        tailColors = new ArrayList<Color>();
        foodColors = new ArrayList<Color>();
        headSizes = new ArrayList<Double>();
        tailSizes = new ArrayList<Double>();
        foodSizes = new ArrayList<Double>();
        
        // set initial colors and size
        r = 255;
        g = 0;
        b = 0;
        a = 1;
        size = 6;
        
        /**************************
         * CATEGORY RADIO BUTTONS *
         **************************/
        
        // category label
        Label categoryLabel = new Label("Category");
        categoryLabel.setStyle("-fx-font: 18 monospace; -fx-alignment: center; -fx-underline: true;");
        add(categoryLabel, 0, 0);
        
        // category radio buttons
        HBox categories = new HBox();
        ToggleGroup group = new ToggleGroup();
        headRadio = new RadioButton("HEAD");
        headRadio.setToggleGroup(group);
        tailRadio = new RadioButton("TAIL");
        tailRadio.setToggleGroup(group);
        foodRadio = new RadioButton("FOOD");
        foodRadio.setToggleGroup(group);
        categories.getChildren().addAll(headRadio, tailRadio, foodRadio);
        // set head as beginning category
        headRadio.fire();
        categories.setSpacing(6);
        add(categories, 0, 1);
        
        /*****************
         * COLOR OPTIONS *
         *****************/
        // color options label
        Label colorLabel = new Label("Color Options");
        colorLabel.setStyle("-fx-font: 18 monospace; -fx-alignment: center; -fx-underline: true;");
        add(colorLabel, 0, 2);
        
        // color gradient options
        HBox colorGradient = new HBox();
        colorGrad = new CheckBox("Gradient");
        colorStepSlider = new Slider(2, 20, 2);
        TextField colorSliderField = new TextField("2");
        colorSliderField.setMaxWidth(40);
        colorSliderField.setEditable(false);
        
        // adjust color slider field according to color step slider
        colorStepSlider.valueProperty().addListener(l -> {
            colorSliderField.setText("" + (int)colorStepSlider.getValue());
        });
        
        colorGradient.getChildren().addAll(colorGrad, colorStepSlider);
        colorGradient.setSpacing(6);
        add(colorGradient, 0, 3);
        add(colorSliderField, 3, 3);
        
        // color buttons (add, remove)
        HBox colorButtons = new HBox();
        Button colorAdd = new Button("Add");
        Button colorRemove = new Button("Remove");
        colorButtons.getChildren().addAll(colorAdd, colorRemove);
        colorButtons.setSpacing(6);
        add(colorButtons, 0, 4);
        
        /****************
         * COLOR SELECT *
         ****************/
        
        // color select sliders
        rSlider = new Slider(0, 255, 255);
        gSlider = new Slider(0, 255, 0);
        bSlider = new Slider(0, 255, 0);
        aSlider = new Slider(0, 1, 1);
        
        // add sliders
        add(rSlider, 0, 5);
        add(gSlider, 0, 6);
        add(bSlider, 0, 7);
        add(aSlider, 0, 8);
        
        // color select textfields
        rField = new TextField("255");
        rField.setMaxWidth(40);
        rField.setEditable(false);
        gField = new TextField("0");
        gField.setMaxWidth(40);
        gField.setEditable(false);
        bField = new TextField("0");
        bField.setMaxWidth(40);
        bField.setEditable(false);
        aField = new TextField("100");
        aField.setMaxWidth(40);
        aField.setEditable(false);
        
        // add labels and textfields
        add(new Label("R: "), 2, 5);
        add(rField, 3, 5);
        add(new Label("G: "), 2, 6);
        add(gField, 3, 6);
        add(new Label("B: "), 2, 7);
        add(bField, 3, 7);
        add(new Label("A: "), 2, 8);
        add(aField, 3, 8);
        
        /****************
         * SIZE OPTIONS *
         ****************/
        // size options label
        Label sizeLabel = new Label("Size Options");
        sizeLabel.setStyle("-fx-font: 18 monospace; -fx-alignment: center; -fx-underline: true;");
        add(sizeLabel, 0, 9);
        
        // size gradient options
        HBox sizeGradient = new HBox();
        sizeGrad = new CheckBox("Gradient");
        sizeStepSlider = new Slider(2, 20, 2);
        TextField sizeSliderField = new TextField("2");
        sizeSliderField.setMaxWidth(40);
        sizeSliderField.setEditable(false);
        
        // adjust size slider field according to size step slider
        sizeStepSlider.valueProperty().addListener(l -> {
            sizeSliderField.setText("" + (int)sizeStepSlider.getValue());
        });
        
        sizeGradient.getChildren().addAll(sizeGrad, sizeStepSlider);
        sizeGradient.setSpacing(6);
        add(sizeGradient, 0, 10);
        add(sizeSliderField, 3, 10);
        
        // size buttons (add, remove)
        HBox sizeButtons = new HBox();
        Button sizeAdd = new Button("Add");
        Button sizeRemove = new Button("Remove");
        sizeButtons.getChildren().addAll(sizeAdd, sizeRemove);
        sizeButtons.setSpacing(6);
        add(sizeButtons, 0, 11);
        
        /***************
         * SIZE SELECT *
         ***************/
        Slider sizeSlider = new Slider(0, 40, 6);
        
        add(sizeSlider, 0, 12);
        
        // size select textfield
        TextField sizeField = new TextField("6");
        sizeField.setMaxWidth(40);
        sizeField.setEditable(false);
        
        // add labels and textfields
        add(new Label("#: "), 2, 12);
        add(sizeField, 3, 12);
        
        /****************
         * GAME OPTIONS *
         ****************/
        // game options label
        Label gameLabel = new Label("Game Options");
        gameLabel.setStyle("-fx-font: 18 monospace; -fx-alignment: center; -fx-underline: true;");
        add(gameLabel, 0, 13);
        
        // game options checkboxes
        HBox gameOptions = new HBox();
        headUnique = new CheckBox("Head Unique");
            headUnique.setSelected(true);
        sequence = new CheckBox("Sequence");
        frozen = new CheckBox("Frozen");
        gameOptions.getChildren().addAll(headUnique, sequence, frozen);
        gameOptions.setSpacing(6);
        add(gameOptions, 0, 14);
        add(new Label("(Color)"), 3, 14);
        
        // game options checkboxes (size)
        HBox gameOptionsSize = new HBox();
        headUniqueSize = new CheckBox("Head Unique");
            headUniqueSize.setSelected(true);
        sequenceSize = new CheckBox("Sequence");
        frozenSize = new CheckBox("Frozen");
        gameOptionsSize.getChildren().addAll(headUniqueSize, sequenceSize, frozenSize);
        gameOptionsSize.setSpacing(6);
        add(gameOptionsSize, 0, 15);
        add(new Label("(Size)"), 3, 15);
        
        /*********************
         * CATEGORY PREVIEWS *
         *********************/
        // add previews for HEAD, TAIL, FOOD
        head = new SettingsPreview("HEAD", headColors, headSizes);
        tail = new SettingsPreview("TAIL", tailColors, tailSizes);
        food = new SettingsPreview("FOOD", foodColors, foodSizes);
        // add previews to options
        add(head, 4, 0, 1, 30);
        add(tail, 5, 0, 1, 30);
        add(food, 6, 0, 1, 30);
        
        /*************************
         * ACTIONS AND LISTENERS *
         *************************/
        
        // add color to list
        colorAdd.setOnAction(e -> {
            if(headRadio.isSelected())
                head.addColor();
            else if(tailRadio.isSelected())
                tail.addColor();
            else
                food.addColor();
        });
        
        // remove color from list
        colorRemove.setOnAction(e -> {
            if(headRadio.isSelected())
                head.removeColor();
            else if(tailRadio.isSelected())
                tail.removeColor();
            else
                food.removeColor();
        });
        
        // add size to list
        sizeAdd.setOnAction(e -> {
            if(headRadio.isSelected())
                head.addSize();
            else if(tailRadio.isSelected())
                tail.addSize();
            else
                food.addSize();
        });
        
        // remove size from list
        sizeRemove.setOnAction(e -> {
            if(headRadio.isSelected())
                head.removeSize();
            else if(tailRadio.isSelected())
                tail.removeSize();
            else
                food.removeSize();
        });
        
        // adjust color textfields and color previews when color slider is moved
        rSlider.valueProperty().addListener(new ColorListener());
        gSlider.valueProperty().addListener(new ColorListener());
        bSlider.valueProperty().addListener(new ColorListener());
        aSlider.valueProperty().addListener(new ColorListener());
        
        // adjust size textfield and size preview when size slider is moved
        sizeSlider.valueProperty().addListener(ov -> {
            size = sizeSlider.getValue();
            sizeField.setText("" + (int)size);
            if(headRadio.isSelected())
                head.previewSize();
            else if(tailRadio.isSelected())
                tail.previewSize();
            else
                food.previewSize();
        });

    }

    // adjust color textfields and color previews when color slider is moved
    private class ColorListener implements InvalidationListener
    {
        @Override
        public void invalidated(Observable ov)
        {
            r = (int)rSlider.getValue();
            rField.setText("" + r);
            g = (int)gSlider.getValue();
            gField.setText("" + g);
            b = (int)bSlider.getValue();
            bField.setText("" + b);
            a = aSlider.getValue();
            aField.setText("" + (int)(a * 100));
            if(headRadio.isSelected())
                head.previewColor();
            else if(tailRadio.isSelected())
                tail.previewColor();
            else
                food.previewColor();
        }
    }
    
    private class SettingsPreview extends GridPane
    {
        private Label title;
        private Rectangle[] colorPreview;
        private Label[] sizePreview;
        private int colorPosition;
        private int sizePosition;
        private int amount; // amount of previews (color and size)
        
        private ArrayList<Color> colorsList;
        private ArrayList<Double> sizesList;
        
        // previous values used to calculate gradient
        // double for colors for better accuracy despite color still being assigned as int
        private double prevR;
        private double prevG;
        private double prevB;
        private double prevA;
        private double prevSize;
        
        public SettingsPreview(){}
        
        public SettingsPreview(String title, ArrayList<Color> colorsList, ArrayList<Double> sizesList)
        {
            // set title for previews
            this.title = new Label(title);
            this.title.setStyle("-fx-font: 12 monospace;");
            add(this.title, 1, 0);
            
            // set number placeholders above sizes
            Label placeHolder = new Label("##");
            placeHolder.setStyle("-fx-font: 12 monospace");
            add(placeHolder, 0, 0);
            
            // set amount of previews
            amount = 24;
            
            // assign array lists
            this.colorsList = colorsList;
            this.sizesList = sizesList;
            
            colorPreview = new Rectangle[amount];
            sizePreview = new Label[amount];
            
            for(int i = 0; i < amount; i++) {
                
                Rectangle color = new Rectangle(30, 15);
                Label size = new Label("?");
                
                
                
                colorPreview[i] = color;
                sizePreview[i] = size;
                
                size.setStyle("-fx-font: 12 monospace;");
                
                color.setStroke(Color.BLACK);
                color.setStrokeWidth(1);
                
                color.setFill(Color.rgb(0, 0, 0, 0));
                
                if(i == 0) {
                    color.setStrokeWidth(2);
                    size.setUnderline(true);
                }
            
                // i + 1 since title gets 0th position
                add(color, 1, i + 1);
                add(size, 0, i + 1);
                
            }
            
            setVgap(-1);
            setHgap(6);
            setPadding(new Insets(0, 0, 0, 12));
            
            // current color and size
            colorPosition = 0;
            sizePosition = 0;
        }
        
        public void previewColor()
        {
            // in case there are more colors added then there are previews (use modulus)
            colorPreview[colorPosition % amount].setFill(Color.rgb(r, g, b, a));
        }
        
        public void previewSize()
        {            
            // in case there are more sizes added then there are previews (use modulus)
            sizePreview[sizePosition % amount].setText("" + (int)size);
        }
        
        public void addColor()
        {
            int howMany = 1;
            
            // variables to calculate gradient
            boolean gradientSet = false;
            double gradR = 0;
            double gradG = 0;
            double gradB = 0;
            double gradA = 0;
            
            // store colors to compare to previous when calculating gradient, and
            // to restore original color to be set as the last color of gradient
            int tempR = r;
            int tempG = g;
            int tempB = b;
            double tempA = a;
            
            // adjust how many according to colorStepSlider
            if(colorGrad.isSelected())
                howMany = (int)colorStepSlider.getValue();
            
            // set previous values to last color in list, else set them to current r, g, b, a values
            if(!colorsList.isEmpty()) {
                prevR = colorsList.get(colorsList.size() - 1).getRed() * 255;
                prevG = colorsList.get(colorsList.size() - 1).getGreen() * 255;
                prevB = colorsList.get(colorsList.size() - 1).getBlue() * 255;
                prevA = colorsList.get(colorsList.size() - 1).getOpacity();
            }
            else {
                prevR = r;
                prevG = g;
                prevB = b;
                prevA = a;
            }
            
            for(int i = 1; i < howMany; i++) {
                
                // calculate gradient value to either add or subtract from previous value
                if(!gradientSet) {
                    gradR = Math.abs(r - prevR) / howMany;
                    gradG = Math.abs(g - prevG) / howMany;
                    gradB = Math.abs(b - prevB) / howMany;
                    gradA = Math.abs(a - prevA) / howMany;
                    gradientSet = true;
                }
                
                // if value is same as previous value, set to value, else
                // subtract [gradient value * i] from previous value (if previous is larger)
                // add [gradient value * i] to previous value (if previous is smaller)
                r = tempR == prevR ? tempR : Math.max(tempR, prevR) == prevR ? (int)(prevR - gradR * i) : (int)(prevR + gradR * i);
                g = tempG == prevG ? tempG : Math.max(tempG, prevG) == prevG ? (int)(prevG - gradG * i) : (int)(prevG + gradG * i);
                b = tempB == prevB ? tempB : Math.max(tempB, prevB) == prevB ? (int)(prevB - gradB * i) : (int)(prevB + gradB * i);
                a = tempA == prevA ? tempA : Math.max(tempA, prevA) == prevA ? (prevA - gradA * i) : (prevA + gradA * i);

                // update preview
                previewColor();
                colorPreview[colorPosition % amount].setStrokeWidth(1);
                colorPosition++;
                colorPreview[colorPosition % amount].setStrokeWidth(2);

                // add to list
                colorsList.add(Color.rgb(r, g, b, a));
            }
            
            // restore colors
            r = tempR;
            g = tempG;
            b = tempB;
            a = tempA;
            
            previewColor();
            colorPreview[colorPosition % amount].setStrokeWidth(1);
            colorPosition++;
            colorPreview[colorPosition % amount].setStrokeWidth(2);

            // add to list
            colorsList.add(Color.rgb(r, g, b, a));
            
        }
        
        public void removeColor()
        {
            // how many colors to remove from list and how many previews to reset
            int howMany = 1;
            
            // adjust how many according to colorStepSlider
            if(colorGrad.isSelected())
                howMany = (int)colorStepSlider.getValue();
            
            
            for(int i = 0; i < howMany; i++) {
                // reset color preview
                // in case there are more colors than amount of previews
                // reset to the preview of the previous set (i.e., colorPosition - amount)
                if(colorPosition >= amount) {
                    colorPreview[colorPosition % amount].setFill(colorsList.get(colorPosition - amount));
                }
                else
                    colorPreview[colorPosition % amount].setFill(Color.rgb(0, 0, 0, 0));

                colorPreview[colorPosition % amount].setStrokeWidth(1);
                colorPosition = colorPosition == 0 ? 0 : colorPosition - 1;
                colorPreview[colorPosition % amount].setStrokeWidth(2);

                // remove from list if not empty
                if(!colorsList.isEmpty())
                    colorsList.remove(colorPosition);
            }
        }
        
        public void addSize()
        {
            int howMany = 1;
            
            // variables to calculate gradient
            boolean gradientSet = false;
            double gradSize = 0;
            
            // store size to restore last size of gradient
            double tempSize = size;
            
            if(sizeGrad.isSelected())
                howMany = (int)sizeStepSlider.getValue();
            
            // set previous value to last size in list, else set them to current size value
            if(!sizesList.isEmpty()) {
                prevSize = sizesList.get(sizesList.size() - 1);
            }
            else {
                prevSize = size;
            }
            
            for(int i = 1; i < howMany; i++) {
                
                // calculate gradient value to either add or subtract from previous value
                if(!gradientSet) {
                    gradSize = Math.abs(size - prevSize) / howMany;
                    gradientSet = true;
                }
                
                // if value is same as previous value, set to value, else
                // subtract [gradient value * i] from previous value (if previous is larger)
                // add [gradient value * i] to previous value (if previous is smaller)
                size = tempSize == prevSize ? tempSize : Math.max(tempSize, prevSize) == prevSize ? prevSize - gradSize * i : prevSize + gradSize * i;
                
                previewSize();
                sizePreview[sizePosition % amount].setUnderline(false);
                sizePosition++;
                sizePreview[sizePosition % amount].setUnderline(true);
                sizesList.add(size);
            }
            
            size = tempSize;
            
            previewSize();
            sizePreview[sizePosition % amount].setUnderline(false);
            sizePosition++;
            sizePreview[sizePosition % amount].setUnderline(true);
            sizesList.add(size);
            
        }
        
        public void removeSize()
        {
            // how many to remove from list and how many previews to reset
            int howMany = 1;
            
            // adjust how many according to StepSlider
            if(sizeGrad.isSelected())
                howMany = (int)sizeStepSlider.getValue();
            
            for(int i = 0; i < howMany; i++) {
                // reset size preview
                // in case there are more sizes added then there are previews (use modulus)
                if(sizePosition >= amount) {
                    sizePreview[sizePosition % amount].setText("" + ((int)sizesList.get(sizePosition - amount).doubleValue()));
                }
                else
                    sizePreview[sizePosition % amount].setText("?");

                
                sizePreview[sizePosition % amount].setUnderline(false);
                sizePosition = sizePosition == 0 ? 0 : sizePosition - 1;
                sizePreview[sizePosition % amount].setUnderline(true);
                if(!sizesList.isEmpty())
                    sizesList.remove(sizePosition);
            }
        }
        
    }
    
    /****************************************
     * PUBLIC GETTERS FOR LISTS AND OPTIONS *
     ****************************************/
    
    public ArrayList<Color> getHeadColors()
    {
        return headColors;
    }
    
    public ArrayList<Double> getHeadSizes()
    {
        return headSizes;
    }
    
    public ArrayList<Color> getTailColors()
    {
        return tailColors;
    }
    
    public ArrayList<Double> getTailSizes()
    {
        return tailSizes;
    }
    
    public ArrayList<Color> getFoodColors()
    {
        return foodColors;
    }
    
    public ArrayList<Double> getFoodSizes()
    {
        return foodSizes;
    }
    
    public boolean headUnique()
    {
        return headUnique.isSelected();
    }
    
    public boolean sequence()
    {
        return sequence.isSelected();
    }
    
    public boolean frozen()
    {
        return frozen.isSelected();
    }
    
    public boolean headUniqueSize()
    {
        return headUniqueSize.isSelected();
    }
    
    public boolean sequenceSize()
    {
        return sequenceSize.isSelected();
    }
    
    public boolean frozenSize()
    {
        return frozenSize.isSelected();
    }

    // return to main menu
    public void handleKey(KeyEvent ke)
    {
        if(ke.getCode() == KeyCode.ESCAPE) {
            gameApp.getScene().setRoot(menu);
            stage.setScene(gameApp.getScene());
        }
    }
}
