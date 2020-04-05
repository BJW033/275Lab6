import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;


/*
 * Given to Brandon Wu by Prof. Harvey
 */
public class Controller extends Application{
	//data fields hold Model and View
	private Model model;
	private View view;

	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
	public void start(Stage theStage) {
        view = new View(theStage);
		model = new Model(view.getWidth(), view.getHeight(), 
                view.getImageWidth(), view.getImageHeight());

        new AnimationTimer() {
            public void handle(long currentNanoTime)
            {
            	view.gc.getCanvas().getScene().setOnKeyPressed(ke-> {  
                    if (ke.getCode() == KeyCode.W) {
                        System.out.println("W Pressed");
                        model.setDirection(Direction.NORTH);
                    }
                    if (ke.getCode() == KeyCode.S) {
                        System.out.println("S Pressed");
                        model.setDirection(Direction.SOUTH);
                    }
                    if (ke.getCode() == KeyCode.D) {
                    	model.setDirection(Direction.EAST);
                        System.out.println("D Pressed");
                    }
                    if (ke.getCode() == KeyCode.A) {
                        System.out.println("A Pressed");
                        model.setDirection(Direction.WEST);
                    }
                    if (ke.getCode() == KeyCode.DIGIT1) {
                        System.out.println("confuse");
                        model.setBassMode(BassMode.CONFUSE);
                    }
                    if (ke.getCode() == KeyCode.DIGIT2) {
                        System.out.println("default");
                        model.setBassMode(BassMode.DEFAULT);
                    }
                    if (ke.getCode() == KeyCode.DIGIT3) {
                        System.out.println("attac");
                        model.setBassMode(BassMode.ATTAC);
                    }
                });
                //increment the x and y coordinates, alter direction if necessary
                model.updateLocationandDirection();
                //input the x coordinates, y coordinates, and Direction 
                
                
                view.update(model.getX(), model.getY(), model.getDirection(),model.getBassMode());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        theStage.show();
    }

	



    
}





