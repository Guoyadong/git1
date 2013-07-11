/*
 *connect����һ���߳��࣬����ʵ�ֱ������ݿ�����ط�������ͬ��
 *
 */
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.namespace.QName;
import ws.DBupdate;
import ws.DBupdateService;
import ws.Test;


class connect extends Thread{
//	���ݿ�����
	String _DBname;
//	���ݱ�����
	String _TBname;
//	ip��ַ
	String _IP;
//	���ݿ��û���
	String _username;
//	���ݿ�����
	String _userpwd;
//	���ݿ��½��ַ
	String _dbURL;
//	���ݿ�����
	Connection con=null;
//	���ݿ�����״̬
	Statement sta=null;
//	���ݿ�ɾ������
	Statement stdel=null;
//	���ݿ��ѯ���
	ResultSet res=null;
//	ִ�����
	public  boolean runAble=true;
//	�ر����
	public  boolean closeAble=false;
//	����˿�
//	DBsynService dbs;
//	DBsynDelegate dbd;
	DBupdate dbd;
	DBupdateService dbs;
	
	public int sleeptime;
//	�������룬��ΪĬ�ϳ�ʼ��
public connect(){
	 _DBname="UFDATA_401_2013";
	 _TBname="RdRecords_Temp";
	 _IP="192.168.10.105:1433";
	 _username="sa";
	 _userpwd="sa";
	 _dbURL="jdbc:sqlserver://"+_IP+";DatabaseName="+_DBname;
	 sleeptime=60000;
	 dbs=new DBupdateService();
	 dbd=dbs.getDBupdate();
}
//	�������Ķ�������
public connect(String DatabaseName,String TableName,String IP,String UserName,String UserPassword){
	_DBname=DatabaseName;
	_TBname=TableName;
	_IP=IP;
	_username=UserName;
	_userpwd=UserPassword;
	_dbURL="jdbc:sqlserver://"+_IP+";DatabaseName="+_DBname;
	dbs=new DBupdateService();
	 dbd=dbs.getDBupdate();
	 sleeptime=10000;
}
//	����ĳ�ʼ�����ʹ�����ʱ��������һ��Ч��
public void init(String DatabaseName,String TableName,String IP,String UserName,String UserPassword){
	_DBname=DatabaseName;
	_TBname=TableName;
	_IP=IP;
	_username=UserName;
	_userpwd=UserPassword;
	_dbURL="jdbc:sqlserver://"+_IP+";DatabaseName="+_DBname;
	dbs=new DBupdateService();
	dbd=dbs.getDBupdate();
	sleeptime=60000;
}
//	��������ַ��ʼ��
public void initService(String url){
	URL baseUrl,uurl = null;
	baseUrl = ws.DBupdateService.class.getResource(".");
	try {
		uurl = new URL(baseUrl,url+"/GuobangWebservice/services/DBupdate?wsdl");
	} catch (MalformedURLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	QName qn=new QName("http://ws/","DBsynService");
	
		dbs=new DBupdateService(uurl, new QName("http://ws","DBupdateService"));
		dbd=dbs.getDBupdate();
	
}

//	�߳����г���
public void run(){
//			��ʽ�����
	 
	
	String driverName="com.microsoft.jdbc.sqlserver.SQLServerDriver";
	String query="select * from "+_TBname;

	try{
		while(runAble){
			sleep(sleeptime);
			//wait(sleeptime);
			System.out.println("start drivername");
			Class.forName(driverName);
			System.out.println("drivername get ok");
			con=DriverManager.getConnection(_dbURL, _username, _userpwd);
			System.out.println("con get ok");
			sta=con.createStatement();
			stdel=con.createStatement();
			res=sta.executeQuery(query);
			System.out.println("start while");
			while(res.next() && runAble)
			{
				System.out.println("while start");
				int sign=res.getInt("Operation");
				int did=0;
				closeAble=false;
//				System.out.println("teststart");
//				System.out.println(dbd.test(12, 123, 123, 123));
//				System.out.println("test over");
				System.out.println("start switch");
				
				while(did==0 && runAble)
				{
					
					//---------
					switch(sign)
					{
					case 0:
						System.out.println("��ʼ");
						did=dbd.outboundUpdateMain(res.getInt("RdRecordsID"), res.getInt("bRdFlag"), res.getString("dDate"),res.getString("cBatch"), res.getString("cInvName"), res.getDouble("iQuantity"), res.getString("cInvStd"), res.getBigDecimal("iPrice"),res.getDouble("iUnitCost"));
						System.out.println("����");
						break;
					case 1:
						System.out.println("��ʼ");
						did=dbd.outboundInsertMain(res.getInt("RdRecordsID"),res.getInt("bRdFlag"), res.getString("dDate"), res.getString("cBatch"), res.getString("cInvName"),res.getString("cWhCode"), res.getDouble("iQuantity"),res.getString("cInvStd"),res.getString("cCode"), res.getString("cDepName"), res.getString("cBusType"), res.getString("cMaker"), res.getBigDecimal("iPrice"),res.getDouble("iUnitCost"));
						System.out.println("����");
						break;
					case 2:
						System.out.println("��ʼ");
						did=dbd.outboundDeleteMain(res.getInt("RdRecordsID"));
						System.out.println("����");
						break;
					case 3:	
						System.out.println("��ʼ");
						did=dbd.outboundUpdateMainOrder(res.getInt("RdRecordsID"), res.getString("cCode"), res.getString("dDate"), res.getString("cBusType"), res.getString("cVenName"), res.getString("cMemo"), res.getString("cDepName"));
						System.out.println("����");
						break;
					default:
						System.err.println("��ǰ���������ݲ����쳣!!");
						//����
						
					}
				}
				if(!runAble)
					break;
				//�����һ�����ݵ�ͬ��������ɾ��
				int ss=res.getInt("AutoID");
				System.out.println("start delete");
				String deleteQ="delete from "+_TBname+" where AutoID='"+ss+"'";
				System.out.println(deleteQ);
				stdel.executeUpdate(deleteQ);
				System.out.println("delete ok");
				
//				res.close();
				closeAble=true;
				System.out.println("1 time ok");
			}
//			sta.close();
//			con.close();
		}
	}
	catch (InterruptedException e) {
		// TODO: handle exception
		System.out.println("interrupt error");
	}
	catch (SQLException e) {
		// TODO: handle exception
		System.out.println("sql error");
		e.printStackTrace();
	}
	catch(Exception e){
		System.out.println("error");
		e.printStackTrace();
		try{
			sta.close();
			con.close();
		//	res.close();
		}
		catch(Exception o){
			
		}
	}
	
	System.out.println("Run run over!");
}
}