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
	private boolean hasAddActionListener= false;//�ж��Ƿ�Ϊ������ӵ������
	private Rectangle nullCell;
	
	//���췽��
	public PictureCanvas(){
	
		setLayout(null);//֡���֣��Զ������겼��
		
		//------����9������ť��С������ӵ�ƴͼ��
		cell=new Cell[9];
		for(int i=0;i<3;i++){//����
			for(int j=0;j<3;j++){//����
				ImageIcon icon=new ImageIcon("picture\\"+pictureID+"_"+(i*3+j+1)+".gif");//����ͼƬ
				
				cell[i*3+j]=new Cell(icon);//����ͼƬС����
				
				cell[i*3+j].setLocation(j*150+20,i*150+20);//����С������ʾ��λ��
				
				add(cell[i*3+j]);//��С������ӵ�ƴͼ����ʾ
			}
		}
		
		remove(cell[8]);//ɾ����9��С����
		nullCell = new Rectangle(150*2+20,150*2+20,150,150);//���ÿհ׸�
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
	public void Start(){
		//ΪС����������������
		if( !hasAddActionListener){//���û������Ӽ���
			for(int i=0;i<8;i++){
				cell[i].addMouseListener(this);
			}
			hasAddActionListener=true;//�������������״̬
		}
		//����ֹͣ�������ǵ�һ��С��������Ͻǽ�Զʱ
		while(cell[0].getBounds().x<=170 && cell[0].getBounds().y <=170){
			//��ȡ�շ���λ��
			int nullX=nullCell.getBounds().x;//�ո�x����
			int nullY=nullCell.getBounds().y;//�ո�y����
			
			int direction = (int)(Math.random()*4);//����0~3�����
			switch(direction){
			
			case 0://�շ�������෽�񻥻�
				nullX -= 150;
			    cellMove(nullX,nullY,"��");//�շ������ķ�������
				break;
				
			case 1://�շ������Ҳ෽�񻥻�
				nullX += 150;
		        cellMove(nullX,nullY,"��");
				break;
				
			case 2://�շ������ϲ෽�񻥻�
			 	nullY -= 150;
		        cellMove(nullX,nullY,"��");
				break;
				
			case 3://�շ������²෽�񻥻�
				nullY += 150;
		        cellMove(nullX,nullY,"��");
				break;
			}
		}
	}
	//�ƶ�С����
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
				if((y-20)/150*3+(x-20)/150!=i)//�����ж�ÿ�������Ƿ��������λ��
					//iΪ0ʱ��(20-20)/150*3+(20-20)/150
					//iΪ1ʱ��(20-20)/150*3+(20+150-20)/150=1
					//iΪ2ʱ��(20-20)/150*3+(20+150*2-20)/150=2
					
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
		Cell button=(Cell)e.getSource();//��ȡ��ǰ����ķ���
		
		int clickX=button.getBounds().x;//�õ����������������
		int clickY=button.getBounds().y;
		int nullX=nullCell.getBounds().x;//�õ��շ��������
		int nullY=nullCell.getBounds().y;	
		
		if(clickX==nullX&&clickY-nullY==150)//������շ�������ķ���ʱ
			button.move("��");
		else if(clickX==nullX&&clickY-nullY==-150)//������շ�������ķ���ʱ
			button.move("��");
		else if(clickX-nullX==150&clickY==nullY)//������շ�������ķ���ʱ
			button.move("��");
		else if(clickX-nullX==-150&&clickY==nullY)//������շ�������ķ���ʱ
			button.move("��");
		else
			return;//������Ͳ������κδ���
		nullCell.setLocation(clickX,clickY);//�շ����ƶ�������ķ�������
		this.repaint();//�ػ�����
		
		//------���²���
		stepNum++;
		PictureMainFrame.step.setText("����:"+stepNum);
		
		if(this.isFinish()){//�ж���Ϸ�Ƿ����
			JOptionPane.showMessageDialog(this,"��ϲ�����ƴͼ��������");//������Ϣ�Ի���
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
