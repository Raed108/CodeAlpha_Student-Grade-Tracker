package dd;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class StudentGradeTracker extends Application{
     public ArrayList<Double> StudentGrades=new ArrayList<>();
     public Button b1;
     public Button b2;
     public Button b3;
     public TextField t1;
     public Label l1;
     public Label l2;
     public Label l3;

    public static void main(String[] args) {
        launch(args);
    }

    public void calculate(){
        double sum=0;
        double max=0;
        double min=StudentGrades.get(0);
        for(int i=0;StudentGrades.size()>i;i++){
            sum += StudentGrades.get(i);
            if(StudentGrades.get(i)>max)
                max=StudentGrades.get(i);
            if(StudentGrades.get(i)<min)
                min=StudentGrades.get(i);
        }
        double avg = sum/StudentGrades.size();
        l1.setStyle("-fx-text-fill: black;");
        l1.setText("Average is "+ avg +" ,  Highest grade is "+max+ " ,  Lowest grade is "+min);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox(20);
        Scene scene=new Scene(root,800,600);
        primaryStage.setTitle("Students Grades Tracker");
        Label label1=new Label("Students Grades Analyzer");
        label1.setFont(Font.font("Arial",FontWeight.BOLD, 50));
        t1=new TextField();
        l2=new Label("Number of students is "+StudentGrades.size());
        l2.setFont(Font.font(20));
        t1.setMaxWidth(200);
        t1.setPromptText("Student grade");
        root.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            if (t1.isFocused()) {
                t1.getParent().requestFocus();
            }
        });
        l1=new Label();
        l3=new Label();
        l3.setFont(Font.font(20));
        b3=new Button("Insert grade");
        b3.setOnAction(event -> {
            String g= t1.getText();
            if(t1.getText().isEmpty()){
                l1.setText("Warning:You must enter a grade first.");
                l1.setStyle("-fx-text-fill: red;");
            }
            else{
                try {
                    if(Double.parseDouble(t1.getText())<0){
                        t1.clear();
                        l1.setText("Warning:You must add a valid a grade.");
                        l1.setStyle("-fx-text-fill: red;");
                    }
                    else{
                        double g1=Double.parseDouble(g);
                        StudentGrades.add(g1);
                        t1.clear();
                        l1.setText("");
                        l3.setText("Students grades "+ StudentGrades.toString());
                        l2.setText("Number of students is "+ StudentGrades.size());
                    }
                }
                catch (NumberFormatException e){
                    t1.clear();
                    l1.setText("Warning:You must add a valid a grade.");
                    l1.setStyle("-fx-text-fill: red;");
                }
            }
        });
        b1=new Button("Analyze");
        b1.setOnAction(event -> {
            if (StudentGrades.isEmpty()){
                l1.setText("Warning:You haven't entered any grade yet.");
                l1.setStyle("-fx-text-fill: red;");
            }
            else
                calculate();
        });
        b2=new Button("Clear");
        b2.setOnAction(event -> {
            StudentGrades.clear();
            l1.setText("");
            t1.clear();
            l2.setText("Number of students is 0");
            l3.setText("");
        });
        b1.setStyle("-fx-background-color: #00cc00 ; -fx-text-fill: white;");
        b2.setStyle("-fx-background-color: #ff3333 ; -fx-text-fill: white;");
        b3.setStyle("-fx-background-color: #3333ff ; -fx-text-fill: white;");
        b1.setOnMouseEntered(event -> {
            b1.setStyle("-fx-background-color: #009900 ; -fx-text-fill: white;");
        });
        b1.setOnMouseExited(event -> {
            b1.setStyle("-fx-background-color: #00cc00 ; -fx-text-fill: white;");
        });
        b2.setOnMouseEntered(event -> {
            b2.setStyle("-fx-background-color: #e60000 ; -fx-text-fill: white;");
        });
        b2.setOnMouseExited(event -> {
            b2.setStyle("-fx-background-color: #ff3333 ; -fx-text-fill: white;");
        });
        b3.setOnMouseEntered(event -> {
            b3.setStyle("-fx-background-color: #0000b3 ; -fx-text-fill: white;");
        });
        b3.setOnMouseExited(event -> {
            b3.setStyle("-fx-background-color: #3333ff ; -fx-text-fill: white;");
        });
        l1.setFont(Font.font("Arial",FontWeight.BOLD, 20));
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(2.0);
        dropShadow.setOffsetY(2.0);
        b1.setEffect(dropShadow);
        b2.setEffect(dropShadow);
        b3.setEffect(dropShadow);
        root.requestFocus();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(label1,t1,b1,b2,b3,l1,l2,l3);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);
    }
}
