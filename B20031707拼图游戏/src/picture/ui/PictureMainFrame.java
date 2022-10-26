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
	
	private String[] items={"����С��","ӣ��С����","������","�����","����Ůսʿ"};
	private JComboBox<String> box;//������
	private PictureCanvas canvas;//ƴͼ��
	private PicturePreview preview;//Ԥ����
	public  static JTextField step;//����
	private JButton start;//��ʼ��ť
	
	
	//�ղ������췽��
	public PictureMainFrame(){
		super();
		
		init();//�����ʼ��
		
		addComponent();//������
		
		addPreviewImage();//���Ԥ��ͼƬ��ƴͼͼƬ
		
		addActionListener();//Ϊ�������¼�����
	}
	
	
	   /*********************�����ʼ��****************/	
	private void init(){
			
		setTitle("ƴͼ��Ϸ");//���ô��ڱ���
		setSize(1000,600);//���ô��ڴ�С
	    setLocation(300,150);//���ô���λ��
	    setResizable(false);//���ô��ڴ�С�̶�
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ô���Ĭ�Ϲرղ���
		
	}
	
      /**********************������*****************/
	private void addComponent(){
		
		//-----�����ϰ�����ʾ��������ť������Ϸ״̬
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1,2));//���ò���Ϊ��񲼾֣�1��2��
		this.add(panel,BorderLayout.NORTH);//����panel���������Ϸ�
				
		//----������߰�ť�����
		JPanel leftPannel=new JPanel();
		panel.add(leftPannel,BorderLayout.WEST);//��ť�����ŵ����
		leftPannel.setBorder(new TitledBorder("��ť��"));//��ӱ߿򼰱���
		leftPannel.setBackground(Color.PINK);//����leftPannel����ɫ
		
		box = new JComboBox<String>(items);//���������
		start = new JButton("Start");//���start��ť
		start.setBackground(Color.WHITE);
		
       /**����������ť�����**/
		leftPannel.add(new JLabel("ѡ��ͼƬ"));
		leftPannel.add(box);
		leftPannel.add(start);
		
		
		//----�����ұ���Ϸ״̬���
		JPanel rightPannel=new JPanel();
		panel.add(rightPannel,BorderLayout.EAST);//��Ϸ״̬���ŵ��ұ�
		rightPannel.setBorder(new TitledBorder("��Ϸ״̬"));//��ӱ߿򼰱���
		rightPannel.setBackground(Color.PINK);//����rightPannel����ɫ
		
		step = new JTextField("������0");
		step.setBackground(Color.WHITE);
		step.setEditable(false);//�����ı����ܱ��༭
	
		rightPannel.add(step,BorderLayout.WEST);
			
	}
	
	/**********************���Ԥ������ƴͼ�����**********************/
	private void addPreviewImage(){
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1,2));//���ò���Ϊ��񲼾�(һ������)
		
		canvas = new PictureCanvas();
		canvas.setBorder(new TitledBorder("ƴͼ��"));
		preview = new PicturePreview();
		preview.setBorder(new TitledBorder("Ԥ����"));
		
		panel.add(canvas,BorderLayout.WEST);
		panel.add(preview,BorderLayout.EAST);
		canvas.setBackground(Color.WHITE);
		preview.setBackground(Color.WHITE);
		this.add(panel,BorderLayout.CENTER);//��������ʾ����������
	
	}
	
	/**********************Ϊ�������¼�����**********************/
	private void addActionListener(){
		
	//----������(���ذ�alt+/)
		box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				//��ȡ��ǰͼƬID
				int num=box.getSelectedIndex();//Ĭ�ϴ�0��ʼ
				PictureCanvas.pictureID=num+1;
				//����Ԥ����
				
				preview.repaint();//���»���Ԥ��������
				
				canvas.reLoadPicture();//����ƴͼ��
			
				//����״̬��
				int stepNum=PictureCanvas.stepNum=0;//��������
				step.setText("������"+stepNum);
				
			}
		});
	
        //------Ϊstart��ť��Ӽ���
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PictureCanvas.stepNum=0;//��������
				step.setText("������"+PictureCanvas.stepNum);//���²���
				canvas.Start();//��С����˳�����
			}
		});
	}

		

	
		

	

	
}
