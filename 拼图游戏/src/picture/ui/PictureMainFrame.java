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
//主界面类
public class PictureMainFrame extends JFrame {
	
	private String[] items={"蜡笔小新","樱桃小丸子"};
	private JComboBox<String> box;//下拉框
	private PictureCanvas canvas;//拼图区
	private PicturePreview preview;//预览区
	private JTextField step;//步数
	private JButton start;//开始按钮
	
	
	//空参数构造方法
	public PictureMainFrame(){
		super();
		init();//界面初始化
		
		addComponent();//添加组件
		
		addPreviewImage();//添加预览图片和拼图图片
	

		
		
	//----下拉框(重载按alt+/)
		box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				//获取当前图片ID
				int num=box.getSelectedIndex();//默认从0开始
				
				//更新预览区
				PictureCanvas.pictureID=num+1;
				preview.repaint();//重新绘制预览区界面
				//更新拼图区
				canvas.reLoadPicture();
			
				//更新步数
				int stepNum=PictureCanvas.stepNum=0;//步数清零
				step.setText("步数："+stepNum);
				
			}
		});
	
//------为start按钮添加监听
		start.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PictureCanvas.stepNum=0;//步数清零
				step.setText("步数："+PictureCanvas.stepNum);//更新步数
				canvas.start();//对小方格顺序打乱
			}
		});
	}

	private void addPreviewImage(){
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1,2));//设置布局为表格布局(一行两列)
		canvas = new PictureCanvas();
		canvas.setBorder(new TitledBorder("拼图区"));//添加边框
		preview = new PicturePreview();
		preview.setBorder(new TitledBorder("预览区"));
		panel.add(canvas,BorderLayout.WEST);
		panel.add(preview,BorderLayout.EAST);
		
		this.add(panel,BorderLayout.CENTER);//面板居中显示在主界面中
	
	
	
	}
	private void addComponent(){
		//-----创建上半区
		JPanel panel=new JPanel();
		//panel.setBackground(Color.gray);//设置panel颜色
		panel.setLayout(new GridLayout(1,2));//设置布局为表格布局
		
		//----创建左边面板
		JPanel leftPannel=new JPanel();
		panel.add(leftPannel,BorderLayout.WEST);
		leftPannel.setBorder(new TitledBorder("按钮区"));//添加边框
		leftPannel.setBackground(Color.PINK);//设置leftPannel背景色

		
		//ButtonGroup buttonGroup=new ButtonGroup();//添加按钮组
		box = new JComboBox<String>(items);
		start = new JButton("start");

		//start.setBackground(Color.PINK);
		

		leftPannel.add(new JLabel("选择图片"));
		leftPannel.add(box);
		leftPannel.add(start);
		
		
		//----创建右边面板
		JPanel rightPannel=new JPanel();
		panel.add(rightPannel,BorderLayout.EAST);
		rightPannel.setBorder(new TitledBorder("游戏状态"));//添加边框
		rightPannel.setBackground(Color.PINK);
		rightPannel.setLayout(new GridLayout(1,2));//设置布局为表格布局
		
		step = new JTextField("步数：0");
	
		rightPannel.add(step,BorderLayout.WEST);
		//设置文本框不能被编辑
		step.setEditable(false);
		
		//----设置panel在主界面上方
		this.add(panel,BorderLayout.NORTH);
		
	}
	private void init(){
		//设置窗口标题
		this.setTitle("拼图游戏");
		//设置窗口大小
		this.setSize(1200,700);
		//设置窗口位置
		this.setLocation(300,150);
		//设置窗口大小固定
		this.setResizable(false);
		//设置窗口默认关闭操作
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
