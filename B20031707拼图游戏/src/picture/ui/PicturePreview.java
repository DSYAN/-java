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
		
		String filename="picture\\"+PictureCanvas.pictureID+".jpg";//ָ����ǰͼƬ·��
		ImageIcon icon=new ImageIcon(filename);//��ȡ��ӦͼƬ
		Image image=icon.getImage();
		g.drawImage(image, 20, 20, 450, 450, this);//��ͼ����Ƶ�Ԥ�������
	
	}

}
