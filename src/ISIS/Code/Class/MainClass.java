package ISIS.Code.Class;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.JFrame;

/**
 * 
 * @author ISIS
 */

public class MainClass extends JFrame {
  AudioClip click;

  public static void main(String[] args) {
    new MainClass();
  }

  public MainClass() {
    this.setSize(400, 400);
    this.setTitle("Mouse Clicker");
    this.addMouseListener(new Clicker());

    URL urlClick = MainClass.class.getResource("/ISIS/libertador.wav");
    click = Applet.newAudioClip(urlClick);

    this.setVisible(true);
  }

  private class Clicker extends MouseAdapter {
    public void mouseClicked(MouseEvent e) {
      click.play();
    }
  }
}