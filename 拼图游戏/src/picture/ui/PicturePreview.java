package picture.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**ͼƬԤ����**/
public class PicturePreview extends JPanel {
	//��д�������������
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//ָ����ǰͼƬ·������ȡ��ӦͼƬ�����Ƶ�Ԥ���������
		
		String filename="picture\\"+PictureCanvas.pictureID+".jpg";
		ImageIcon icon=new ImageIcon(filename);
		Image image=icon.getImage();
		g.drawImage(image, 15, 20, 560, 560, this);
	
	}

}
