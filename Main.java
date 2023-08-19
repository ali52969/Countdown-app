import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        // Create a JFrame to hold the buttons and labels
        JFrame frame = new JFrame("Countdown app");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        AtomicInteger buttonCount = new AtomicInteger(); // Counter for the number of buttons created

        // Create a JButton
        JButton addEvent = new JButton("Add Event");
        addEvent.setBounds(20, 20, 100, 30);

        // Add an action listener to the "Add Event" button
        addEvent.addActionListener(e -> {
            buttonCount.getAndIncrement(); // Increment the button counter

            String inputEvent = JOptionPane.showInputDialog(frame, "Enter new countdown event");
            int input = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter time in seconds for the event"));

            // Display the input in a message dialog
            JOptionPane.showMessageDialog(frame, inputEvent + "_event added!");

            // Create a new button with the event information
            JButton newButton = new JButton(inputEvent);
            newButton.setBounds(20, 20 + (buttonCount.get() * 80), 100, 30);

            // Create a JLabel to display the remaining time
            JLabel timeLabel = new JLabel("Time Left: ");
            timeLabel.setBounds(140, 20 + (buttonCount.get() * 80), 200, 30);

            AtomicInteger countdownSeconds = new AtomicInteger(input); // Number of seconds for the countdown

            // Start a countdown timer
            Timer timer = new Timer(1000, timerEvent -> {
                countdownSeconds.getAndDecrement();

                // Calculate remaining hours, minutes, and seconds
                int hours = countdownSeconds.get() / 3600;
                int minutes = (countdownSeconds.get() % 3600) / 60;
                int seconds = countdownSeconds.get() % 60;

                // Update the time label with the remaining time
                timeLabel.setText("Time Left: " + hours + "h " + minutes + "m " + seconds + "s");

                if (countdownSeconds.get() == 0) {
                    JOptionPane.showMessageDialog(frame, inputEvent + " completed!");
                    ((Timer) timerEvent.getSource()).stop(); // Stop the timer
                }
            });
            timer.start(); // Start the timer

            // Add an action listener to the new button
            newButton.addActionListener(event -> {
                JOptionPane.showMessageDialog(frame, "Time Left: " + countdownSeconds.get() + " seconds");
            });

            // Add the new button and time label to the frame
            frame.getContentPane().add(newButton);
            frame.getContentPane().add(timeLabel);
            frame.repaint(); // Refresh the frame to show the new button and label
        });

        // Add the "Add Event" button to the frame
        frame.getContentPane().add(addEvent);

        // Set the frame size and make it visible
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
