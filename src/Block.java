import java.awt.*;



public class Block {
	private int side;
	private Color color;
	private double x;
	private double y;
	
	public Block(double d, double e){
		this.x = d;
		this.y = e;
		color = new Color(255, 255, 255);
		side = Options.BLOCK_SIZE;
	}
	
	
	public void paintComponent(Graphics g){
		Graphics2D g1 = (Graphics2D) g;
		
		g.setColor(color);
		if(color.equals(Color.WHITE))
			g.fillRect((int)(x),  (int)(y), side, side);
		else
			g1.fill3DRect((int)(x),  (int)(y), side, side,true);
		
	}

	public int getSide(){
		return side;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return color;
	}

}
