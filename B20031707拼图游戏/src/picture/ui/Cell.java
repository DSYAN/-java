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
		setSize(150,150);	//����С�����С
		
	}
	
	public void move(String direction){
		switch(direction){
		case "��"://�ո����ϣ����������ķ�������
			this.setLocation(this.getBounds().x, this.getBounds().y-150);
			break;
		case "��":
			this.setLocation(this.getBounds().x, this.getBounds().y+150);
			break;
		case "��":
			this.setLocation(this.getBounds().x-150, this.getBounds().y);
			break;
		case "��":
			this.setLocation(this.getBounds().x+150, this.getBounds().y);
			break;
		default://�����������
			break;
		}
	}


	

}