package picture.ui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**ƴͼ��**/
public class PictureCanvas extends JPanel implements MouseListener {
	//��̬����
	public static int pictureID=1;//Ĭ��ͼƬID
	public static int stepNum=0;//�ƶ�����Ĭ��0

	//��Ա����
	private Cell[] cell;//С����
	private boolean hasAddActionListener= false;//�ж��Ƿ�Ϊ������Ӽ���
	private Rectangle nullCell;
	
	//���췽��
	public PictureCanvas(){
		//����ƴͼ������
		this.setLayout(null);
		
		//����9��С������ӵ�ƴͼ��
		cell=new Cell[9];
		for(int i=0;i<3;i++){//����
			for(int j=0;j<3;j++){//����
				//����ͼƬ
				
				ImageIcon icon=new ImageIcon("picture\\"+pictureID+"_"+(i*3+j+1)+".gif");
				//����ͼƬС����
				cell[i*3+j]=new Cell(icon);
				//����С������ʾ��λ��
				cell[i*3+j].setLocation(j*186+20,i*186+20);
				//��С������ӵ�ƴͼ����ʾ
				this.add(cell[i*3+j]);
			}
		}
		
		this.remove(cell[8]);//ɾ����9��С����
		nullCell = new Rectangle(350,470,186,186);
	}
	
	//���¼���ͼƬ
	public void reLoadPicture(){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				ImageIcon icon=new ImageIcon("picture\\"+pictureID+"_"+(i*3+j+1)+".gif");
				cell[i*3+j].setIcon(icon);
			}
		}
	}
	
	
	//-------����С����
	public void start(){
		//ΪС����������������
		if(!hasAddActionListener){//���û������Ӽ���
			for(int i=0;i<8;i++){
				cell[i].addMouseListener(this);
			}
			hasAddActionListener=true;//�������������״̬
		}
		//����ֹͣ�������ǵ�һ��С���������Ͻ�4��������
		while(cell[0].getBounds().x<=186+20 && cell[0].getBounds().y<=186+20){
			//��ȡ�շ���λ��
			int nullX=nullCell.getBounds().x;//�ո�x����
			int nullY=nullCell.getBounds().y;//�ո�y����
			
			int direction = (int)(Math.random()*4);//����0~3�����
			switch(direction){
			case 0://�շ�������෽�񻥻�
				nullX -=186;
			    cellMove(nullX,nullY,"RIGHT");//�շ������ķ�������
				break;
			case 1://�շ������Ҳ෽�񻥻�
				nullX +=186;
		        cellMove(nullX,nullY,"LEFT");
				break;
			case 2://�շ������ϲ෽�񻥻�
			 	nullY -=186;
		        cellMove(nullX,nullY,"UP");
				break;
			case 3://�շ������²෽�񻥻�
				nullY +=186;
		        cellMove(nullX,nullY,"D");
				break;
			}
		}
	}
	
	private void cellMove(int nullX, int nullY, String direction) {
		// TODO Auto-generated method stub
		for(int i=0;i<8;i++){
			if(cell[i].getBounds().x==nullX && cell[i].getBounds().y==nullY){//�ҵ���շ����غϵ�С����
				cell[i].move(direction);//��ǰ�����ƶ�
				nullCell.setLocation(nullX, nullY);//�շ����ƶ�
				break;
			}
		}
	}
	
	//----isFinish���������ж�ƴͼ�Ƿ�ƴ�ϳɹ���
	public boolean isFinish(){//�ж�ƴͼ�Ƿ�ƴ�ϳɹ�
			for(int i=0;i<8;i++){
				int x=cell[i].getBounds().x;
				int y=cell[i].getBounds().y;
				if(y/100*3+x/100!=i)//�����ж�ÿ�������Ƿ��������λ��
					return false;
			}
			return true;
		}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {//��Ҫ��ע��갴�²���
		// TODO Auto-generated method stub
		Cell button=(Cell)e.getSource();//ȷ������ķ���
		
		int x1=button.getBounds().x;//�õ����������������
		int y1=button.getBounds().y;
		int nullX=nullCell.getBounds().x;//�õ��շ��������
		int nullY=nullCell.getBounds().y;	
		
		if(x1==nullX&&y1-nullY==100)//���бȽϣ����������������н���
			button.move("UP");
		else if(x1==nullX&&y1-nullY==-100)
			button.move("DOWN");
		else if(x1-nullX==100&y1==nullY)
			button.move("LEFT");
		else if(x1-nullX==-100&&y1==nullY)
			button.move("RIGHT");
		else
			return;//������Ͳ������κδ���
		nullCell.setLocation(x1,y1);//�շ����ƶ�������ķ�������
		this.repaint();//�ػ�����
		
		if(this.isFinish()){//�ж���Ϸ�Ƿ����
			JOptionPane.showMessageDialog(this,"��ϲ�����ƴͼ��");//���� ��Ϣ�Ի���
			for(int i=0;i<8;i++)
				cell[i].removeMouseListener(this);//�������ɣ���������¼�����굥��������������
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
