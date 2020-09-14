import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BlockBreakerPanel extends JPanel implements KeyListener {
	
	

	ArrayList<Block> blocks = new ArrayList<Block>();
	Block ball = new Block(275, 180, 80, 80, "hiclipart.com.png");
	Block paddle = new Block(175, 480, 150, 150, "Snapshell.png");



	BlockBreakerPanel() {
		for(int i =0; i<8;i++) 
			blocks.add(new Block((i*60+2),0,60,60, "Food_DoubleCheeseburger.png"));
		for(int i =0; i<8;i++) 
			blocks.add(new Block((i*60+2),35,60,60, "Food_DoubleCheeseburger.png"));
		for(int i =0; i<8;i++) 
			blocks.add(new Block((i*60+2),70,60,60, "Food_DoubleCheeseburger.png"));
		for(int i =0; i<8;i++) 
			blocks.add(new Block((i*60+2),100,60,60, "Food_DoubleCheeseburger.png"));
		

		addKeyListener(this);
		setFocusable(true);
	}

	public void paintComponent(Graphics g) {
		blocks.forEach(block -> {
			block.draw(g, this);
		});
		ball.draw(g, this);
		paddle.draw(g, this);
	}

	public void update() {
		ball.x += ball.movX;
		
		if(ball.x > (getWidth()-10) || ball.x < 0)
			ball.movX *= -1;
		
		if(ball.y < 0 || ball.intersects(paddle))
			ball.movY *= -1;
		ball.y += ball.movY;
		
		blocks.forEach(block -> {
			if(ball.intersects(block) && !block.destroyed) {
				block.destroyed = true;
				ball.movY *= -1;
				
			}
		});
		repaint();
		
		

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		
			new Thread(() -> {
				while (true) {
					update();
					System.out.println("running");
					try {
						Thread.sleep(10);
					} catch (InterruptedException err) {
						err.printStackTrace();

					}
				}
			}).start();

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)) {
			paddle.x += 25;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
			paddle.x -= 15;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
