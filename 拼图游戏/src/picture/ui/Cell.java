package picture.ui;

import javax.swing.Icon;
import javax.swing.JButton;
/**ͼƬС����**/
public class Cell extends JButton {
/**�һ�source**/
	//-----����ͼƬ��С����
	public Cell(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
		//����С�����С
		this.setSize(186,186);
	}
	
	public void move(String direction){
		switch(direction){
		case "UP"://�ո����ϣ����������ķ�������
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
		default://�����������
			break;
		}
	}


	

}
