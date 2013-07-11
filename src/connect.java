/*
 *connect类是一个线程类，用于实现本地数据库向异地服务器的同步
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
//	数据库名称
	String _DBname;
//	数据表名称
	String _TBname;
//	ip地址
	String _IP;
//	数据库用户名
	String _username;
//	数据库密码
	String _userpwd;
//	数据库登陆地址
	String _dbURL;
//	数据库连接
	Connection con=null;
//	数据库连接状态
	Statement sta=null;
//	数据库删除操作
	Statement stdel=null;
//	数据库查询结果
	ResultSet res=null;
//	执行许可
	public  boolean runAble=true;
//	关闭许可
	public  boolean closeAble=false;
//	服务端口
//	DBsynService dbs;
//	DBsynDelegate dbd;
	DBupdate dbd;
	DBupdateService dbs;
	
	public int sleeptime;
//	对象申请，此为默认初始化
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
//	带参数的对象申请
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
//	对象的初始化，和带参数时的申请是一个效果
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
//	服务器地址初始化
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

//	线程运行程序
public void run(){
//			正式程序段
	 
	
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
						System.out.println("开始");
						did=dbd.outboundUpdateMain(res.getInt("RdRecordsID"), res.getInt("bRdFlag"), res.getString("dDate"),res.getString("cBatch"), res.getString("cInvName"), res.getDouble("iQuantity"), res.getString("cInvStd"), res.getBigDecimal("iPrice"),res.getDouble("iUnitCost"));
						System.out.println("结束");
						break;
					case 1:
						System.out.println("开始");
						did=dbd.outboundInsertMain(res.getInt("RdRecordsID"),res.getInt("bRdFlag"), res.getString("dDate"), res.getString("cBatch"), res.getString("cInvName"),res.getString("cWhCode"), res.getDouble("iQuantity"),res.getString("cInvStd"),res.getString("cCode"), res.getString("cDepName"), res.getString("cBusType"), res.getString("cMaker"), res.getBigDecimal("iPrice"),res.getDouble("iUnitCost"));
						System.out.println("结束");
						break;
					case 2:
						System.out.println("开始");
						did=dbd.outboundDeleteMain(res.getInt("RdRecordsID"));
						System.out.println("结束");
						break;
					case 3:	
						System.out.println("开始");
						did=dbd.outboundUpdateMainOrder(res.getInt("RdRecordsID"), res.getString("cCode"), res.getString("dDate"), res.getString("cBusType"), res.getString("cVenName"), res.getString("cMemo"), res.getString("cDepName"));
						System.out.println("结束");
						break;
					default:
						System.err.println("当前数据条数据操作异常!!");
						//报错
						
					}
				}
				if(!runAble)
					break;
				//完成这一条数据的同步，进行删除
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