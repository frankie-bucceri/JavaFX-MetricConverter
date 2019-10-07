/* JavaFXMetricConverter
*/

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class JavaFXMetricConverter extends Application
{
   // Fields
   private TextField kiloTextField;
   private Label resultLabel;
   private RadioButton milesButton;
   private RadioButton feetButton;
   private RadioButton inchesButton;
   
   public static void main(String[] args)
   {
      launch(args);
   } // End main
   
   @Override
   public void start(Stage pStage)
   {
      // Label for prompt
      Label promptLabel = new Label("Enter a distance in km: ");
      // Text field for input from user
      kiloTextField = new TextField();
      // Radiobuttons
      milesButton = new RadioButton("Convert to miles");
      feetButton = new RadioButton("Convert to feet");
      inchesButton = new RadioButton("Convert to inches");
      // One has to be selected by default- select milesButton control as def.
      milesButton.setSelected(true);
      
      
      // Group the radio buttons into a toggle group
      ToggleGroup radioGroup = new ToggleGroup();
      milesButton.setToggleGroup(radioGroup);
      feetButton.setToggleGroup(radioGroup);
      inchesButton.setToggleGroup(radioGroup);
      
      // Create a button to perform te conversions
      Button calcButton = new Button("Convert");
      // Register the event handler with button
      calcButton.setOnAction(new CalcButtonHandler());
      
      // Label to display the results
      resultLabel = new Label();
      
      // Put prompt label and text field in a HBox
      HBox promptHbox = new HBox(10, promptLabel, kiloTextField);
      // Put the radio button in an HBox
      HBox radioHbox = new HBox(10, milesButton, feetButton, inchesButton);
      
      // Put everything in a Vbox
      VBox vbox = new VBox(10, promptHbox, radioHbox, calcButton, resultLabel);
      // Set vbox alignment
      vbox.setAlignment(Pos.CENTER);
      vbox.setPadding(new Insets(10));
      
      // Create scene and display it
      Scene scene = new Scene(vbox);
      pStage.setScene(scene);
      pStage.setTitle("Metric Converter using JavaFX");
      pStage.show();
   } // End start

   // Event handler class for calcButton
   class CalcButtonHandler implements EventHandler<ActionEvent>
   {
      @Override
      public void handle(ActionEvent event)
      {
         // Constant conversion factors
         final double MILESFACTOR = 0.6214;
         final double FEETFACTOR = 3281.0;
         final double INCHESFACTOR = 39370.0;
         
         // Variable for result
         double result = 0;
         
         // Get the kilometers
         double kilometers = Double.parseDouble(kiloTextField.getText());
         // Perform computation
         if (milesButton.isSelected())
         result = kilometers * MILESFACTOR;
         if (feetButton.isSelected())
         result = kilometers * FEETFACTOR;
         if (inchesButton.isSelected())
         result = kilometers * INCHESFACTOR;
         
         // Display the result
         resultLabel.setText(String.format("%.2f", result));
         
      } // End handle()
   } // End inner class
} // End class