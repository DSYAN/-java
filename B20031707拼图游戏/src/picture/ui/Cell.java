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
		setSize(150,150);	//设置小方格大小
		
	}
	
	public void move(String direction){
		switch(direction){
		case "上"://空格向上，与它互换的方格向下
			this.setLocation(this.getBounds().x, this.getBounds().y-150);
			break;
		case "下":
			this.setLocation(this.getBounds().x, this.getBounds().y+150);
			break;
		case "左":
			this.setLocation(this.getBounds().x-150, this.getBounds().y);
			break;
		case "右":
			this.setLocation(this.getBounds().x+150, this.getBounds().y);
			break;
		default://其他情况不动
			break;
		}
	}


	

}