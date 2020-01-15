import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * This class displays Java SE Swing Notification to both volunteers and hospitals,
 * when needed*/
public class DialogExample extends JFrame
{
    private final static String DIALOG_TITLE = "Message Dialog";
    private final static int DIALOG_ICON = JOptionPane.PLAIN_MESSAGE;
    private final JButton openPopupBtn;
	
    /**
     * This is the method that actually displays the notification
     * @param s is the corrensponding message of each case
     * e.g. in bloodBorrow method s is the message
     * which informs other hospitals about a shortage in
     * the stock of a different hospital in their region*/
    public DialogExample(String s)
    {
        this.openPopupBtn = new JButton("Open Message");

        this.openPopupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(DialogExample.this, s, DIALOG_TITLE, DIALOG_ICON);
            }
        });

        this.setTitle("Dialog Example");
        this.setSize(640,480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(this.openPopupBtn);
        this.setResizable(false);
    }   
}

