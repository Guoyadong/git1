/*
 * ����Ϊ�ͻ��˵����࣬���ƽ����ͬ������
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
//	�������ڲ�����
	/*
	class aaa extends Thread{
		public aaa(){
			
		}
		public void run(){
//			��һ���ǲ��������õ�
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
				System.out.println("����wait");
				//res=dd.sayhello(req);
				System.out.println("ִ�н���");
				//runAble=false;
				System.out.println(res);
				//bw.write(res+"\r\n");
				//bw.flush();
				long time2 = System.currentTimeMillis();
				long time=time2-time1;
				System.out.println("��Ӧʱ�䣺"+time);
				//bw.write("��Ӧʱ�䣺"+time+"\r\n");
				//bw.flush();
				}
				
			//bw.close();
			}
			catch(Exception e){
				System.out.println("�����쳣");
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
//	����ͼ������
	public String tuopan;
//	���̰�ť
	TrayIcon trayIcon;

    PopupMenu popup; 
    MenuItem menuExit; 
    MenuItem menustart;
    MenuItem menustop;
    MenuItem menuhelp;
	
//	���ڶ���
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
		con.setName("ͬ������");
		System.out.println("Sip:"+_Sip+";Sport:"+_Sport);
		con.initService("http://"+_Sip+":"+_Sport);
	}
	
//	��ʼ��ť������
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
	
//	ֹͣ��ť������
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
	
//	��С����ť������
	ActionListener min=new ActionListener() {	
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JF.setVisible(false);
		}
	};
	
//	���ڴ����
	public void build(){
		Jname=new JLabel("���ݿ��û�����");
		Jpwd=new JLabel("���룺");
		Jip=new JLabel("��ʱ������IP");
		Jport=new JLabel("�������˿�");
		Jstart=new JLabel("��ʼ����");
		Jstop=new JLabel("ֹͣ����");
		Jmin=new JLabel("��С��");
		JTFname=new JTextField(_name,8);
		JTFip=new JTextField(_Sip, 12);
		JTFport=new JTextField(_Sport,4);
		jPFpwd=new JPasswordField(_pwd, 8);
		ImageIcon Istart=new ImageIcon(getClass().getResource("_start.jpg"));
		ImageIcon Istop=new ImageIcon(getClass().getResource("_stop.jpg"));
		ImageIcon Imin=new ImageIcon(getClass().getResource("_min.jpg"));
		JBstart=new JButton("��ʼ����",Istart);
		JBstop=new JButton("ֹͣ����",Istop);
		jBmin=new JButton("��С��",Imin);
		//JBstart.setDisabledIcon(new ImageIcon(getClass().getResource("_startP.jpg")));
		//JBstop.setDisabledIcon(new ImageIcon(getClass().getResource("_stopP.jpg")));
		JBstart.addActionListener(start);
		JBstop.addActionListener(stop);
		jBmin.addActionListener(min);
		JBstart.setEnabled(false);
//		JBstart.addActionListener(startL);
//		JBstop.addActionListener(stopL);
//		jBmin.addActionListener(minL);
		JF=new JFrame("��ʱ���ݿ�ͬ�����");
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
	
//	����ͼ�괴������
	public  boolean  CreteTrayIcon(String trayName,PopupMenu popup){ 
        boolean isCreated=false; 
//        final  TrayIcon trayIcon; 
        if (SystemTray.isSupported()) { 
            tray = SystemTray.getSystemTray(); 
            Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(tuopan)); 
            trayIcon = new TrayIcon(image, trayName, popup); 
            trayIcon.setImageAutoSize(true); 
            
            //����һ��Action������:���˫���¼� 
            final  ActionListener al = new ActionListener() { 
                public void actionPerformed(ActionEvent e) { 
                    //trayIcon.displayMessage("�����¼�", "���˫���¼����յ�", TrayIcon.MessageType.WARNING); 
                    //mainFrame.main.setVisible(true); 
                	JF.setVisible(true);
                } 
            }; 
            trayIcon.addActionListener(al); 
            try { 
                tray.add(trayIcon); 
                isCreated=true; 
            } catch (AWTException e) { 
                System.err.println("�޷���������:"+e); 
                isCreated=false; 
            } 
        } 
        return isCreated; 
    }
//	����ͼ��˵�
	public  PopupMenu createPopup(){ 
         popup = new PopupMenu(); 
         menuExit = new MenuItem("�˳�"); 
         menustart = new MenuItem("��ʼ����");
         menustop=new MenuItem("ֹͣ����");
         menuhelp=new MenuItem("����");
        //�����˳��˵������� 
        ActionListener exitListener = new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
            	con.runAble=false;
            	con.interrupt();
            	
                System.exit(0); 
            } 
        }; 
        //��ʼ��������� 
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
        //ֹͣ���������
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
		//����������
		ActionListener helpListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(JF, "�����ĿƼ����޹�˾");
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
	
//	ͬ���̹߳رճ���
	public void shutup(){
		con.runAble=false;
		
	}
	
	
//	����������ӵĺ���
	public void checkNet(){
		URL url = null;  
        try {  
            url = new URL("http://"+_Sip+":"+_Sport+"/GuobangWebservice/services/DBupdate?wsdl");  
            try {  
                InputStream in = url.openStream();  
                in.close();  
               // System.out.println("��������������");  
            } catch (IOException e) {  
                //System.out.println("��������ʧ�ܣ�");  
                JOptionPane.showMessageDialog(null, "��ʱ�˷�����δ��������ȴ���������������������");
                System.exit(0);
            }  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        }
        return;
	}
//	���������
	public static void main(String[] args) {
//		�������������ӳ�ʱ���ú����ݻ�ȡ��ʱ����
		System.setProperty("sun.net.client.defaultConnectTimeout", "5000");
//		System.setProperty("sun.net.client.defaultReadTimeout", "2000");
//		connect cc;
//		cc=new connect();
		
//		String icfn="/start.jpg";
//		tubiao tt=new tubiao();
//		PopupMenu pop=tt.createPopup();
//		if(!tt.CreteTrayIcon(icfn,"�ҵ�����",pop)){ 
//            System.out.println("���ܴ�������"); 
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
