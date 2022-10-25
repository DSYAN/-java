package picture.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**图片预览类**/
public class PicturePreview extends JPanel {
	//重写绘制组件方法，
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//指定当前图片路径，获取对应图片并绘制到预览区面板中
		
		String filename="picture\\"+PictureCanvas.pictureID+".jpg";
		ImageIcon icon=new ImageIcon(filename);
		Image image=icon.getImage();
		g.drawImage(image, 15, 20, 560, 560, this);
	
	}

}
