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
		
		String filename="picture\\"+PictureCanvas.pictureID+".jpg";//指定当前图片路径
		ImageIcon icon=new ImageIcon(filename);//获取对应图片
		Image image=icon.getImage();
		g.drawImage(image, 20, 20, 450, 450, this);//把图像绘制到预览区面板
	
	}

}
