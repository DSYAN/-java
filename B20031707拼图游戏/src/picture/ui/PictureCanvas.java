package picture.ui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**拼图类**/
public class PictureCanvas extends JPanel implements MouseListener {
	//静态变量
	public static int pictureID=1;//默认图片ID
	public static int stepNum=0;//移动步数默认0

	//成员变量
	private Cell[] cell;//小方格
	private boolean hasAddActionListener= false;//判断是否为方格添加点击监听
	private Rectangle nullCell;
	
	//构造方法
	public PictureCanvas(){
	
		setLayout(null);//帧布局，自定义坐标布局
		
		//------创建9个带按钮的小方格，添加到拼图区
		cell=new Cell[9];
		for(int i=0;i<3;i++){//行数
			for(int j=0;j<3;j++){//列数
				ImageIcon icon=new ImageIcon("picture\\"+pictureID+"_"+(i*3+j+1)+".gif");//加载图片
				
				cell[i*3+j]=new Cell(icon);//创建图片小方格
				
				cell[i*3+j].setLocation(j*150+20,i*150+20);//设置小方格显示的位置
				
				add(cell[i*3+j]);//把小方格添加到拼图区显示
			}
		}
		
		remove(cell[8]);//删除第9个小方格
		nullCell = new Rectangle(150*2+20,150*2+20,150,150);//设置空白格
	}
	
	//重新加载图片
	public void reLoadPicture(){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				ImageIcon icon=new ImageIcon("picture\\"+pictureID+"_"+(i*3+j+1)+".gif");
				cell[i*3+j].setIcon(icon);
			}
		}
	}
	
	
	//-------打乱小方格
	public void Start(){
		//为小方格添加鼠标点击监听
		if( !hasAddActionListener){//如果没有则添加监听
			for(int i=0;i<8;i++){
				cell[i].addMouseListener(this);
			}
			hasAddActionListener=true;//更新鼠标点击监听状态
		}
		//打乱停止的条件是第一个小方格距左上角较远时
		while(cell[0].getBounds().x<=170 && cell[0].getBounds().y <=170){
			//获取空方格位置
			int nullX=nullCell.getBounds().x;//空格x坐标
			int nullY=nullCell.getBounds().y;//空格y坐标
			
			int direction = (int)(Math.random()*4);//产生0~3随机数
			switch(direction){
			
			case 0://空方格与左侧方格互换
				nullX -= 150;
			    cellMove(nullX,nullY,"右");//空方格左侧的方格右移
				break;
				
			case 1://空方格与右侧方格互换
				nullX += 150;
		        cellMove(nullX,nullY,"左");
				break;
				
			case 2://空方格与上侧方格互换
			 	nullY -= 150;
		        cellMove(nullX,nullY,"下");
				break;
				
			case 3://空方格与下侧方格互换
				nullY += 150;
		        cellMove(nullX,nullY,"上");
				break;
			}
		}
	}
	//移动小方格
	private void cellMove(int nullX, int nullY, String direction) {
		// TODO Auto-generated method stub
		for(int i=0;i<8;i++){
			if(cell[i].getBounds().x==nullX && cell[i].getBounds().y==nullY){//找到与空方格重合的小方格
				cell[i].move(direction);//当前方格移动
				nullCell.setLocation(nullX, nullY);//空方格移动
				break;
			}
		}
	}
	
	//----isFinish方法用来判断拼图是否拼合成功：
	public boolean isFinish(){//判断拼图是否拼合成功
			for(int i=0;i<8;i++){
				int x=cell[i].getBounds().x;
				int y=cell[i].getBounds().y;
				if((y-20)/150*3+(x-20)/150!=i)//依次判断每个方格是否在最初的位置
					//i为0时，(20-20)/150*3+(20-20)/150
					//i为1时，(20-20)/150*3+(20+150-20)/150=1
					//i为2时，(20-20)/150*3+(20+150*2-20)/150=2
					
					return false;
			}
			return true;
		}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {//主要关注鼠标按下操作
		// TODO Auto-generated method stub
		Cell button=(Cell)e.getSource();//获取当前点击的方格
		
		int clickX=button.getBounds().x;//得到所单击方格的坐标
		int clickY=button.getBounds().y;
		int nullX=nullCell.getBounds().x;//得到空方格的坐标
		int nullY=nullCell.getBounds().y;	
		
		if(clickX==nullX&&clickY-nullY==150)//当点击空方格下面的方格时
			button.move("上");
		else if(clickX==nullX&&clickY-nullY==-150)//当点击空方格上面的方格时
			button.move("下");
		else if(clickX-nullX==150&clickY==nullY)//当点击空方格右面的方格时
			button.move("左");
		else if(clickX-nullX==-150&&clickY==nullY)//当点击空方格左面的方格时
			button.move("右");
		else
			return;//不满足就不进行任何处理
		nullCell.setLocation(clickX,clickY);//空方格移动到点击的方格坐标
		this.repaint();//重绘此组件
		
		//------更新步数
		stepNum++;
		PictureMainFrame.step.setText("步数:"+stepNum);
		
		if(this.isFinish()){//判断游戏是否完成
			JOptionPane.showMessageDialog(this,"恭喜你完成拼图啦！！！");//调出消息对话框
			for(int i=0;i<8;i++)
				cell[i].removeMouseListener(this);//如果已完成，撤消鼠标事件，鼠标单击方格不再起作用
			hasAddActionListener=false;
		}
	}
	
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
