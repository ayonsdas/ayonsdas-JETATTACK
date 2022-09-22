import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;

public class GameViewer
{
	
	static String name1;
	static String name2;
	
	static JButton restart;
	
	static ActionListener timeKeep;
	static Timer t;
	
	static boolean canUndoU;
	static boolean canUndoR;
	
	static JFrame frame;
	static JPanel panel;
	
	static GraphicsComponent game;
	
	public static void main(String[] args)
	{
		canUndoU = true;
		canUndoR = true;
		frame = new JFrame();
		panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setFocusable(true);
		final int FRAME_HEIGHT = 800;
		final int FRAME_WIDTH = 1200;
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Graphics Examples");
		
		// Draw shapes
		name1 = getName("Please enter the name of Player 1", false, false);
		name2 = getName("Please enter the name of Player 2", true, false);
		game = new GraphicsComponent(frame, name1, name2);
		
		KeyListener keyListener = new KeyListener() {
			public void keyPressed(KeyEvent event) {
				switch(event.getKeyCode()) {
					case KeyEvent.VK_A:
						game.get1().lPressed();
						break;
					case KeyEvent.VK_D:
						game.get1().rPressed();
						break;
					case KeyEvent.VK_LEFT:
						game.get2().lPressed();
						break;
					case KeyEvent.VK_RIGHT:
						game.get2().rPressed();
						break;
				}
			}
			
			public void keyReleased(KeyEvent event) {
				switch(event.getKeyCode()) {
					case KeyEvent.VK_W:
						game.fire(game.get1());
						break;
					case KeyEvent.VK_S:
						game.specialFire(game.get1());
						break;
					case KeyEvent.VK_A:
						game.get1().lReleased();
						break;
					case KeyEvent.VK_D:
						game.get1().rReleased();
						break;
					case KeyEvent.VK_UP:
						game.fire(game.get2());
						break;
					case KeyEvent.VK_DOWN:
						game.specialFire(game.get2());
						break;
					case KeyEvent.VK_LEFT:
						game.get2().lReleased();
						break;
					case KeyEvent.VK_RIGHT:
						game.get2().rReleased();
						break;
					case KeyEvent.VK_SPACE:
						if(!game.isGameOver() && canUndoU && canUndoR)
							game.pauseSwap();
						break;
					case KeyEvent.VK_R:
						if(game.isPaused() && canUndoU && canUndoR)
						{
							name1 = getName("Please enter the name of Player 1", false, false);
							name2 = getName("Please enter the name of Player 2", true, false);
							game.start(frame, name1, name2);
						}
						break;
					case KeyEvent.VK_U:
						if(!game.isGameOver() && (game.isPaused() && canUndoR))
						{
							if(canUndoU)
								game.setState(3);
							else
								game.setState(1);
							canUndoU = !canUndoU;
						}
						break;
					case KeyEvent.VK_L:
						if(!game.isGameOver() && (game.isPaused() && canUndoU))
						{
							if(game.hasRecords())
							{
								if(canUndoR)
								{
									game.updateCurrentChecker(getName("Which Player do you want to view the Stats of?", false, true));
									game.setState(2);
								}
								else
									game.setState(1);
								canUndoR = !canUndoR;
							}
							else
								JOptionPane.showMessageDialog(frame, "No Records currently available.");
						}
				}
			}
			
			public void keyTyped(KeyEvent event) {
			}
		};
		panel.addKeyListener(keyListener);
		
		
		//frame.add(restart);
		frame.add(game);
		
		// Show it
		frame.setVisible(true);
		
		timeKeep = new TimerListener(game);
		
		t = new Timer(0, timeKeep);
		t.start();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static class TimerListener implements ActionListener
	{
		
		private GraphicsComponent gc;
		
		public TimerListener(GraphicsComponent gc)
		{
			this.gc = gc;
		}
		
		public void actionPerformed(ActionEvent event)
		{
			gc.advance();
		}
		
	}
	
	public static String getName(String prompt, boolean checker, boolean checker2) 
	{
		String input;
		String val;
		while (true) 
		{
			try 
			{
				input = JOptionPane.showInputDialog(null, prompt);
				val = input;
				if(val.length() == 0)
					throw new Exception("Please input a name");
				if(checker)
					if(val.equals(name1))
						throw new Exception("Please input a name different from Player 1");
				if(checker2)
					if(!game.getRecords().players().contains(val))
						throw new Exception("Player not found in the records. Please try again");
				if(val.length() > 15)
					throw new Exception("Please input a name no longer than 15 characters long");
				if (val.indexOf(' ') != -1) 
					throw new Exception("Name must not contain whitespace");
				return val;
			}
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}
}