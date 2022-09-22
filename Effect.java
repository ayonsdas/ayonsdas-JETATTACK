import java.awt.Graphics2D;

public interface Effect {
	
	void draw(Graphics2D gr, boolean maintain);
	
	/** Advances the racer forward in the race by amount
	 *  @param amount the number of pixels to advance
	 */
	void tick();
    
	/** Retrieves the shape's bounding rectangle
	 *  @return the bounding rectangle that encompasses this shape
	 */
	
	public boolean inUse();
}