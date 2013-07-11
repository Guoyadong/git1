/*
 * 此类为客户端的主类，控制界面和同步操作
 */
//import ws.DBupdate;
//import ws.DBupdateService;
import java.io.*;
import java.sql.*;
import java.net.*;
//import java.sql.DriverManager;

import javax.swing.*; 

import com.sun.corba.se.pept.transport.Connection;

import java.awt.*; 
import java.awt.event.*; 

import java.net.MalformedURLException;
import java.net.URL;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.xml.namespace.QName;
import ws.DBupdate;
import ws.DBupdateService;
import ws.Test;

public class callt {
	connect con;
	//public static boolean runAble=false;
//	测试用内部函数
	/*
	class aaa extends Thread{
		public aaa(){
			
		}
		public void run(){
//			这一段是测试连接用的
			try{
				checkNet();
				
				//System.out.println(s);
				
				
				//BufferedWriter bw=new BufferedWriter(new FileWriter(s+"/config.ini",true));
				for(int i=0;i<3;i++){
				long time1 = System.currentTimeMillis();
				String req="lala";
				String res=null;
				//DBsynService dss=new DBsynService();
				//DBsynDelegate dd=dss.getDBsynPort();
				//runAble=true;
				System.out.println("进入wait");
				//res=dd.sayhello(req);
				System.out.println("执行结束");
				//runAble=false;
				System.out.println(res);
				//bw.write(res+"\r\n");
				//bw.flush();
				long time2 = System.currentTimeMillis();
				long time=time2-time1;
				System.out.println("响应时间："+time);
				//bw.write("响应时间："+time+"\r\n");
				//bw.flush();
				}
				
			//bw.close();
			}
			catch(Exception e){
				System.out.println("出现异常");
				//e.printStackTrace();
			}
		}
	}*/
	public boolean netIsOk=false;
	String _DBname;
	String _name;
	String _pwd;
	String _table;
	String _ip;
	String _port;
	String _Sip;
	String _Sport;
//	托盘图标名称
	public String tuopan;
//	托盘按钮
	TrayIcon trayIcon;

    PopupMenu popup; 
    MenuItem menuExit; 
    MenuItem menustart;
    MenuItem menustop;
    MenuItem menuhelp;
	
//	窗口对象
	SystemTray tray;
	JFrame JF;
	JLabel Jname,Jpwd,Jip,Jport,Jstart,Jstop,Jmin;
	JTextField JTFname,JTFip,JTFport;
	JPasswordField jPFpwd;
	JButton JBstart,JBstop,jBmin;
	JPanel JPs,JPc,jPn;

	public callt(){
//		_DBname="UFDATA_401_2013";
//		_name="sa";
//		_pwd="sa";
//		_table="RdRecords_Temp";
//		_ip="localhost";
//		_port="1433";
//		_Sip="192.168.1.128";
//		_Sport="80";
		String s=System.getProperty("user.dir");
		
		try {
			BufferedReader br=new BufferedReader(new FileReader(s+"/config.ini"));
			
			String tmp;
			while((tmp=br.readLine())!=null)
			{
				//System.out.println(tmp);
				tmp=tmp.trim();
				String temp[]=tmp.split("=");
				
				if(temp[0].equals("_DBname"))
				{
//					System.out.println(temp[0]);
//					System.out.println(temp[1]);
					_DBname=temp[1];
				}
				else if (temp[0].equals("_table")) {
					_table=temp[1];
				}
				else if (temp[0].equals("_name")) {
					_name=temp[1];
				}
				else if (temp[0].equals("_password")) {
					_pwd=temp[1];
				}
				else if (temp[0].equals("_ip")) {
					_ip=temp[1];
				}
				else if (temp[0].equals("_port")) {
					_port=temp[1];
				}
				else if (temp[0].equals("_ServerIP")) {
					_Sip=temp[1];
				}
				else if (temp[0].equals("_ServerPort")) {
					_Sport=temp[1];
				}
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		checkNet();
		con=new connect(_DBname, _table, _ip+":"+_port, _name, _pwd);
		con.setName("同步服务");
		System.out.println("Sip:"+_Sip+";Sport:"+_Sport);
		con.initService("http://"+_Sip+":"+_Sport);
	}
	
//	开始按钮监听器
	ActionListener start=new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			_name=JTFname.getText().trim();
			_pwd=new String(jPFpwd.getPassword());
			_Sip=JTFip.getText();
			_Sport=JTFport.getText();
			//System.out.println(_name+";"+_pwd+"-");
			con= new connect(_DBname, _table, _ip+":"+_port, _name, _pwd);
			checkNet();
			con.initService("http://"+_Sip+":"+_Sport);
			con.runAble=true;
			con.start();
//			con.sleeptime=60000;			
			JBstart.setEnabled(false);	
			JBstop.setEnabled(true);
			menustart.setEnabled(false);
            menustop.setEnabled(true);
            //System.out.println("Run start!");
		}
	};
	
//	停止按钮监听器
	ActionListener stop=new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			con.runAble=false;
//			con.sleeptime=0;
			con.interrupt();
			
			JBstop.setEnabled(false);
			JBstart.setEnabled(true);
			menustart.setEnabled(true);
            menustop.setEnabled(false);
		}
	};
	
//	最小化按钮监听器
	ActionListener min=new ActionListener() {	
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JF.setVisible(false);
		}
	};
	
//	窗口搭建函数
	public void build(){
		Jname=new JLabel("数据库用户名：");
		Jpwd=new JLabel("密码：");
		Jip=new JLabel("优时服务器IP");
		Jport=new JLabel("服务器端口");
		Jstart=new JLabel("开始服务");
		Jstop=new JLabel("停止服务");
		Jmin=new JLabel("最小化");
		JTFname=new JTextField(_name,8);
		JTFip=new JTextField(_Sip, 12);
		JTFport=new JTextField(_Sport,4);
		jPFpwd=new JPasswordField(_pwd, 8);
		ImageIcon Istart=new ImageIcon(getClass().getResource("_start.jpg"));
		ImageIcon Istop=new ImageIcon(getClass().getResource("_stop.jpg"));
		ImageIcon Imin=new ImageIcon(getClass().getResource("_min.jpg"));
		JBstart=new JButton("开始服务",Istart);
		JBstop=new JButton("停止服务",Istop);
		jBmin=new JButton("最小化",Imin);
		//JBstart.setDisabledIcon(new ImageIcon(getClass().getResource("_startP.jpg")));
		//JBstop.setDisabledIcon(new ImageIcon(getClass().getResource("_stopP.jpg")));
		JBstart.addActionListener(start);
		JBstop.addActionListener(stop);
		jBmin.addActionListener(min);
		JBstart.setEnabled(false);
//		JBstart.addActionListener(startL);
//		JBstop.addActionListener(stopL);
//		jBmin.addActionListener(minL);
		JF=new JFrame("优时数据库同步软件");
		JF.setLayout(new BorderLayout());
		JF.setSize(400, 160);
		JF.setResizable(false);
		JF.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JPs=new JPanel();
		JPc=new JPanel();
		jPn=new JPanel();
//		 JF.getContentPane();
//		JPs.setLayout(new BoxLayout(JPs, BoxLayout.X_AXIS));
//		JPc.setLayout(new BoxLayout(JPs,BoxLayout.X_AXIS));
//		jPn.setLayout(new BoxLayout(JPs,BoxLayout.X_AXIS));
		jPn.add(Jname);
		jPn.add(JTFname);
		jPn.add(Jpwd);
		jPn.add(jPFpwd);
		JPc.add(Jip);
		JPc.add(JTFip);
		JPc.add(Jport);
		JPc.add(JTFport);
		JPs.add(JBstart);
		JPs.add(JBstop);
		JPs.add(jBmin);
		JF.add(jPn,BorderLayout.NORTH);
		JF.add(JPc,BorderLayout.CENTER);
		JF.add(JPs,BorderLayout.SOUTH);
//		JF.add(Jstart);
//		JF.add(Jstop);
//		JF.add(Jmin);
		JF.setLocation(300, 200);
		JF.setVisible(true);
		
	}
	
//	托盘图标创建函数
	public  boolean  CreteTrayIcon(String trayName,PopupMenu popup){ 
        boolean isCreated=false; 
//        final  TrayIcon trayIcon; 
        if (SystemTray.isSupported()) { 
            tray = SystemTray.getSystemTray(); 
            Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(tuopan)); 
            trayIcon = new TrayIcon(image, trayName, popup); 
            trayIcon.setImageAutoSize(true); 
            
            //创建一个Action监听器:左键双击事件 
            final  ActionListener al = new ActionListener() { 
                public void actionPerformed(ActionEvent e) { 
                    //trayIcon.displayMessage("托盘事件", "这个双击事件己收到", TrayIcon.MessageType.WARNING); 
                    //mainFrame.main.setVisible(true); 
                	JF.setVisible(true);
                } 
            }; 
            trayIcon.addActionListener(al); 
            try { 
                tray.add(trayIcon); 
                isCreated=true; 
            } catch (AWTException e) { 
                System.err.println("无法创建托盘:"+e); 
                isCreated=false; 
            } 
        } 
        return isCreated; 
    }
//	托盘图标菜单
	public  PopupMenu createPopup(){ 
         popup = new PopupMenu(); 
         menuExit = new MenuItem("退出"); 
         menustart = new MenuItem("开始服务");
         menustop=new MenuItem("停止服务");
         menuhelp=new MenuItem("帮助");
        //创建退出菜单监听器 
        ActionListener exitListener = new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	con.runAble=false;
            	con.interrupt();
            	
                System.exit(0); 
            } 
        }; 
        //开始服务监听器 
        ActionListener startListener = new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                tuopan="/start.jpg";
                trayIcon.setImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(tuopan)));
                //con.notify();
                
//                con.sleeptime=60000;
                
                con= new connect(_DBname, _table, _ip+":"+_port, _name, _pwd);
    			con.runAble=true;
    			con.start();
                menustart.setEnabled(false);
                menustop.setEnabled(true);
                JBstart.setEnabled(false);	
    			JBstop.setEnabled(true);
            } 
        }; 
        //停止服务监听器
        ActionListener stopListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tuopan="/stop.jpg";
				trayIcon.setImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(tuopan)));
//				try {
//					//con.wait();
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				con.sleeptime=0;
				
				con.runAble=false;
				con.interrupt();
				menustart.setEnabled(true);
                menustop.setEnabled(false);
                JBstart.setEnabled(true);	
    			JBstop.setEnabled(false);
			}
		};
		//帮助监听器
		ActionListener helpListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(JF, "景联文科技有限公司");
			}
		};
        menuExit.addActionListener(exitListener); 
        menustart.addActionListener(startListener);
        menustop.addActionListener(stopListener); 
        menuhelp.addActionListener(helpListener);
        popup.add(menustart); 
        popup.add(menustop);
        popup.add(menuhelp);
        popup.add(menuExit);
        return popup; 
    } 
	
//	同步线程关闭程序
	public void shutup(){
		con.runAble=false;
		
	}
	
	
//	检查网络连接的函数
	public void checkNet(){
		URL url = null;  
        try {  
            url = new URL("http://"+_Sip+":"+_Sport+"/GuobangWebservice/services/DBupdate?wsdl");  
            try {  
                InputStream in = url.openStream();  
                in.close();  
               // System.out.println("网络连接正常！");  
            } catch (IOException e) {  
                //System.out.println("网络连接失败！");  
                JOptionPane.showMessageDialog(null, "优时端服务器未开启！请等待服务器开启后开启本程序！");
                System.exit(0);
            }  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        }
        return;
	}
//	主程序入口
	public static void main(String[] args) {
//		下面两条是连接超时设置和数据获取超时设置
		System.setProperty("sun.net.client.defaultConnectTimeout", "5000");
//		System.setProperty("sun.net.client.defaultReadTimeout", "2000");
//		connect cc;
//		cc=new connect();
		
//		String icfn="/start.jpg";
//		tubiao tt=new tubiao();
//		PopupMenu pop=tt.createPopup();
//		if(!tt.CreteTrayIcon(icfn,"我的托盘",pop)){ 
//            System.out.println("不能创建托盘"); 
//        }
		
		//checkNet();
		callt a=new callt();
		
		a.tuopan="/start.jpg";
		PopupMenu pop=a.createPopup();
		a.CreteTrayIcon("bilibili", pop);
		a.build();
		a.con.start();
		

	}
}
