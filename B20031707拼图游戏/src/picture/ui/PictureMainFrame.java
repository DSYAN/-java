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
	
	private String[] items={"蜡笔小新","樱桃小丸子","懒洋洋","章鱼哥","美少女战士"};
	private JComboBox<String> box;//下拉框
	private PictureCanvas canvas;//拼图区
	private PicturePreview preview;//预览区
	public  static JTextField step;//步数
	private JButton start;//开始按钮
	
	
	//空参数构造方法
	public PictureMainFrame(){
		super();
		
		init();//界面初始化
		
		addComponent();//添加组件
		
		addPreviewImage();//添加预览图片和拼图图片
		
		addActionListener();//为组件添加事件监听
	}
	
	
	   /*********************界面初始化****************/	
	private void init(){
			
		setTitle("拼图游戏");//设置窗口标题
		setSize(1000,600);//设置窗口大小
	    setLocation(300,150);//设置窗口位置
	    setResizable(false);//设置窗口大小固定
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗口默认关闭操作
		
	}
	
      /**********************添加组件*****************/
	private void addComponent(){
		
		//-----创建上半区显示面板包括按钮区与游戏状态
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1,2));//设置布局为表格布局，1行2列
		this.add(panel,BorderLayout.NORTH);//设置panel在主界面上方
				
		//----创建左边按钮区面板
		JPanel leftPannel=new JPanel();
		panel.add(leftPannel,BorderLayout.WEST);//按钮区面板放到左边
		leftPannel.setBorder(new TitledBorder("按钮区"));//添加边框及标题
		leftPannel.setBackground(Color.PINK);//设置leftPannel背景色
		
		box = new JComboBox<String>(items);//添加下拉框
		start = new JButton("Start");//添加start按钮
		start.setBackground(Color.WHITE);
		
       /**添加组件到按钮区面板**/
		leftPannel.add(new JLabel("选择图片"));
		leftPannel.add(box);
		leftPannel.add(start);
		
		
		//----创建右边游戏状态面板
		JPanel rightPannel=new JPanel();
		panel.add(rightPannel,BorderLayout.EAST);//游戏状态面板放到右边
		rightPannel.setBorder(new TitledBorder("游戏状态"));//添加边框及标题
		rightPannel.setBackground(Color.PINK);//设置rightPannel背景色
		
		step = new JTextField("步数：0");
		step.setBackground(Color.WHITE);
		step.setEditable(false);//设置文本框不能被编辑
	
		rightPannel.add(step,BorderLayout.WEST);
			
	}
	
	/**********************添加预览区和拼图区面板**********************/
	private void addPreviewImage(){
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1,2));//设置布局为表格布局(一行两列)
		
		canvas = new PictureCanvas();
		canvas.setBorder(new TitledBorder("拼图区"));
		preview = new PicturePreview();
		preview.setBorder(new TitledBorder("预览区"));
		
		panel.add(canvas,BorderLayout.WEST);
		panel.add(preview,BorderLayout.EAST);
		canvas.setBackground(Color.WHITE);
		preview.setBackground(Color.WHITE);
		this.add(panel,BorderLayout.CENTER);//面板居中显示在主界面中
	
	}
	
	/**********************为组件添加事件监听**********************/
	private void addActionListener(){
		
	//----下拉框(重载按alt+/)
		box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				//获取当前图片ID
				int num=box.getSelectedIndex();//默认从0开始
				PictureCanvas.pictureID=num+1;
				//更新预览区
				
				preview.repaint();//重新绘制预览区界面
				
				canvas.reLoadPicture();//更新拼图区
			
				//更新状态区
				int stepNum=PictureCanvas.stepNum=0;//步数清零
				step.setText("步数："+stepNum);
				
			}
		});
	
        //------为start按钮添加监听
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PictureCanvas.stepNum=0;//步数清零
				step.setText("步数："+PictureCanvas.stepNum);//更新步数
				canvas.Start();//对小方格顺序打乱
			}
		});
	}

		

	
		

	

	
}
