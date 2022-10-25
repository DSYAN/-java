package picture.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.annotation.Annotation;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
//��������
public class PictureMainFrame extends JFrame {
	
	private String[] items={"����С��","ӣ��С����"};
	private JComboBox<String> box;//������
	private PictureCanvas canvas;//ƴͼ��
	private PicturePreview preview;//Ԥ����
	private JTextField step;//����
	private JButton start;//��ʼ��ť
	
	
	//�ղ������췽��
	public PictureMainFrame(){
		super();
		init();//�����ʼ��
		
		addComponent();//������
		
		addPreviewImage();//���Ԥ��ͼƬ��ƴͼͼƬ
	

		
		
	//----������(���ذ�alt+/)
		box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				//��ȡ��ǰͼƬID
				int num=box.getSelectedIndex();//Ĭ�ϴ�0��ʼ
				
				//����Ԥ����
				PictureCanvas.pictureID=num+1;
				preview.repaint();//���»���Ԥ��������
				//����ƴͼ��
				canvas.reLoadPicture();
			
				//���²���
				int stepNum=PictureCanvas.stepNum=0;//��������
				step.setText("������"+stepNum);
				
			}
		});
	
//------Ϊstart��ť��Ӽ���
		start.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PictureCanvas.stepNum=0;//��������
				step.setText("������"+PictureCanvas.stepNum);//���²���
				canvas.start();//��С����˳�����
			}
		});
	}

	private void addPreviewImage(){
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1,2));//���ò���Ϊ��񲼾�(һ������)
		canvas = new PictureCanvas();
		canvas.setBorder(new TitledBorder("ƴͼ��"));//��ӱ߿�
		preview = new PicturePreview();
		preview.setBorder(new TitledBorder("Ԥ����"));
		panel.add(canvas,BorderLayout.WEST);
		panel.add(preview,BorderLayout.EAST);
		
		this.add(panel,BorderLayout.CENTER);//��������ʾ����������
	
	
	
	}
	private void addComponent(){
		//-----�����ϰ���
		JPanel panel=new JPanel();
		//panel.setBackground(Color.gray);//����panel��ɫ
		panel.setLayout(new GridLayout(1,2));//���ò���Ϊ��񲼾�
		
		//----����������
		JPanel leftPannel=new JPanel();
		panel.add(leftPannel,BorderLayout.WEST);
		leftPannel.setBorder(new TitledBorder("��ť��"));//��ӱ߿�
		leftPannel.setBackground(Color.PINK);//����leftPannel����ɫ

		
		//ButtonGroup buttonGroup=new ButtonGroup();//��Ӱ�ť��
		box = new JComboBox<String>(items);
		start = new JButton("start");

		//start.setBackground(Color.PINK);
		

		leftPannel.add(new JLabel("ѡ��ͼƬ"));
		leftPannel.add(box);
		leftPannel.add(start);
		
		
		//----�����ұ����
		JPanel rightPannel=new JPanel();
		panel.add(rightPannel,BorderLayout.EAST);
		rightPannel.setBorder(new TitledBorder("��Ϸ״̬"));//��ӱ߿�
		rightPannel.setBackground(Color.PINK);
		rightPannel.setLayout(new GridLayout(1,2));//���ò���Ϊ��񲼾�
		
		step = new JTextField("������0");
	
		rightPannel.add(step,BorderLayout.WEST);
		//�����ı����ܱ��༭
		step.setEditable(false);
		
		//----����panel���������Ϸ�
		this.add(panel,BorderLayout.NORTH);
		
	}
	private void init(){
		//���ô��ڱ���
		this.setTitle("ƴͼ��Ϸ");
		//���ô��ڴ�С
		this.setSize(1200,700);
		//���ô���λ��
		this.setLocation(300,150);
		//���ô��ڴ�С�̶�
		this.setResizable(false);
		//���ô���Ĭ�Ϲرղ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
