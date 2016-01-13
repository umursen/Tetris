import java.awt.*;

import javax.swing.*;

public class NextPiecePanel extends JPanel{
	ApplicationWindow ownerFrame;

	public NextPiecePanel(ApplicationWindow ownerFrame){
		super();
		this.ownerFrame = ownerFrame;
		setLayout(null);
		setBackground(Color.white);		
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g1 = (Graphics2D) g;
		g1.setColor(Color.RED);
		Piece nextPiece = ownerFrame.game.blockboard.nextPiece;
		if(nextPiece!=null){
			switch (nextPiece.getPieceType()){
			case('Z'): 
				g1.setColor(Color.BLUE);
				g1.fill3DRect(45,30,40,40,true);
				g1.fill3DRect(85,30,40,40,true);
				g1.fill3DRect(85,70,40,40,true);
				g1.fill3DRect(125,70,40,40,true); 
				break;
			case('S'):
				g1.setColor(Color.GREEN);
				g1.fill3DRect(85,30,40,40,true);
				g1.fill3DRect(125,30,40,40,true);
				g1.fill3DRect(45,70,40,40,true);
				g1.fill3DRect(85,70,40,40,true);
				break;
			case('J'):
				g1.setColor(Color.MAGENTA);
				g1.fill3DRect(45,30,40,40,true);
				g1.fill3DRect(45,70,40,40,true);
				g1.fill3DRect(85,70,40,40,true);
				g1.fill3DRect(125,70,40,40,true);
				break;
			case('L'):
				g1.setColor(Color.ORANGE);
				g1.fill3DRect(45,30,40,40,true);
				g1.fill3DRect(85,30,40,40,true);
				g1.fill3DRect(125,30,40,40,true);
				g1.fill3DRect(45,70,40,40,true);
				break;
			case('i'):
				g1.setColor(Color.cyan);
				g1.fill3DRect(45,50,40,40,true);
				g1.fill3DRect(85,50,40,40,true);
				g1.fill3DRect(125,50,40,40,true);
				break;
			case('T'):
				g1.setColor(new Color(255,102,178));
				g1.fill3DRect(45,30,40,40,true);
				g1.fill3DRect(85,30,40,40,true);
				g1.fill3DRect(125,30,40,40,true);
				g1.fill3DRect(85,70,40,40,true);
				break;
			case('I'):
				g1.setColor(Color.YELLOW);
				g1.fill3DRect(25,50,40,40,true);
				g1.fill3DRect(65,50,40,40,true);
				g1.fill3DRect(105,50,40,40,true);
				g1.fill3DRect(145,50,40,40,true);
				break;
			case('O'):
				g1.setColor(Color.RED);
				g1.fill3DRect(65,30,40,40,true);
				g1.fill3DRect(105,30,40,40,true);
				g1.fill3DRect(65,70,40,40,true);
				g1.fill3DRect(105,70,40,40,true);
				break;
			case('j'):
				g1.setColor(Color.YELLOW);
				g1.fill3DRect(105,30,40,40,true);
				g1.fill3DRect(65,70,40,40,true);
				g1.fill3DRect(105,70,40,40,true);
				break;
			case('r'):
				g1.setColor(Color.RED);
				g1.fill3DRect(65,30,40,40,true);
				g1.fill3DRect(65,70,40,40,true);
				g1.fill3DRect(105,70,40,40,true);
				break;

			}
		}
	}
}
