package picture.ui;

import javax.swing.Icon;
import javax.swing.JButton;
/**图片小方格**/
public class Cell extends JButton {
/**右击source**/
	//-----带有图片的小方格
	public Cell(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
		//设置小方格大小
		this.setSize(186,186);
	}
	
	public void move(String direction){
		switch(direction){
		case "UP"://空格向上，与它互换的方格向下
			this.setLocation(this.getBounds().x, this.getBounds().y-186);
			break;
		case "DOWN":
			this.setLocation(this.getBounds().x, this.getBounds().y+186);
			break;
		case "LEFT":
			this.setLocation(this.getBounds().x-186, this.getBounds().y);
			break;
		case "RIGHT":
			this.setLocation(this.getBounds().x+186, this.getBounds().y);
			break;
		default://其他情况不动
			break;
		}
	}


	

}
