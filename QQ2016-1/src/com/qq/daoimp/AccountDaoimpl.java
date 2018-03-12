package com.qq.daoimp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Vector;

import com.qq.base.DBCconn;
import com.qq.base.cmd;
import com.qq.beens.Account;
import com.qq.dao.AccountDao;

public class AccountDaoimpl implements AccountDao {
	public int getQQid() {
		int qqid = 0;
		Random r = new Random();
		Boolean isExist = true;
		while (isExist) {
			qqid = r.nextInt(99999999) + 1000000000;
			String sql = "select * from Account where qqid = ?";
			Connection con = DBCconn.openDB();
			try {
				PreparedStatement p = con.prepareStatement(sql);
				p.setInt(1, qqid);
				ResultSet rs = p.executeQuery();
				if (!rs.next()) {
					isExist = false;
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

		}
		return qqid;
	}

	public int getPort() {
		int port = 0;
		Random rd = new Random();
		boolean isExit = true;
		while (isExit) {
			port = rd.nextInt(65535 - 1024) + 1024;
			Connection con = DBCconn.openDB();
			String sql = "select * from Account where port = ?";
			try {
				PreparedStatement pt = con.prepareStatement(sql);
				pt.setInt(1, port);
				ResultSet rs = pt.executeQuery();
				// 如果没有下一条就不用再调用随机方法了，就直接返回port
				if (!rs.next()) {
					isExit = false;
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return port;
	}

	public String Save(Account a) {
		Connection con = DBCconn.openDB();
		int qqid = 0;
		String sql = "insert into Account values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement p = con.prepareStatement(sql);
			qqid = getQQid();
			System.out.println(qqid);
			p.setInt(1, qqid);
			p.setString(2, a.getInfoname());
			p.setString(3, a.getPassword());
			p.setString(4, a.getPhoto());
			p.setInt(5, a.getAge());
			p.setString(6, a.getSex());
			p.setString(7, "离线");
			p.setString(8, a.getConstellation());
			p.setString(9, a.getBoold());
			p.setString(10, "汉族");
			p.setString(11, a.getIpadress());
			p.setInt(12, 1234);
			p.setString(13, a.getInterst());
			p.setString(14, a.getPrename());
			p.executeUpdate();
			p.close();
			con.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return new Integer(qqid).toString();

	}

	@Override
	public Account login(Account a) {
		Connection con = DBCconn.openDB();
		Account acc = null;
		// 查询数据库里面是否存在qq账号和密码
		String sql = "select * from  Account where  QQid =? and password = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, a.getQQid());
			ps.setString(2, a.getPassword());
			ResultSet re = ps.executeQuery();
			// 如果存在，就执行下面语气
			if (re.next()) {
				acc = new Account();
				acc.setQQid(re.getInt("QQid"));
				acc.setInfoname(re.getString("infoname"));
				acc.setPassword(re.getString("password"));
				acc.setPhoto(re.getString("photo"));
				acc.setAge(re.getInt("age"));
				acc.setSex(re.getString("sex"));
				acc.setStement(re.getString("stement"));
				acc.setConstellation(re.getString("constellation"));
				acc.setBoold(re.getString("boold"));
				acc.setInterst(re.getString("interst"));
				acc.setPrename(re.getString("prename"));
				// 重新设置IP地址
				InetAddress addr = InetAddress.getLocalHost();
				acc.setIpadress(addr.getHostAddress());
				// 设置端口号为随机的
				int port = getPort();
				acc.setPort(port);
				acc.setStement("在线");
				// 在数据库里面去修改IP地址，端口号，状态
				String sqlUpdate = "update Account set ipadress =?,port = ? ,stement=? where QQid=?";
				PreparedStatement p = con.prepareStatement(sqlUpdate);
				p.setString(1, acc.getIpadress());
				p.setInt(2, acc.getPort());
				p.setString(3, acc.getStement());
				p.setInt(4, acc.getQQid());
				p.executeUpdate();
				ps.close();
				p.close();
				re.close();
				con.close();

			} else {
				acc = null;
			}

		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return acc;
	}

	@Override
	public Vector<Account> getMyfriend(int qqid) {
		String sql = "select a.*,f.groupID from account a inner join frend f on a.QQid=f.friendnum where f.uesrnum=?";
		Vector<Account> VMyfriend = new Vector<Account>();
		Connection con = DBCconn.openDB();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, qqid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account acc = new Account();
				acc.setQQid(rs.getInt("QQid"));
				acc.setInfoname(rs.getString("infoname").trim());
				acc.setPhoto(rs.getString("photo").trim());
				acc.setAge(rs.getInt("age"));
				acc.setSex(rs.getString("sex").trim());
				acc.setStement(rs.getString("stement").trim());
				acc.setConstellation(rs.getString("constellation").trim());
				acc.setBoold(rs.getString("boold").trim());
				acc.setMzhome(rs.getString("mzhome").trim());
				acc.setIpadress(rs.getString("ipadress").trim());
				acc.setPort(rs.getInt("port"));
				acc.setInterst(rs.getString("interst").trim());
				acc.setPrename(rs.getString("prename").trim());
				acc.setGroupID(rs.getString("groupID").trim());
				VMyfriend.add(acc);

			}
			ps.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return VMyfriend;
	}

	@Override
	public void moveGroup(int qqid, int frendid, String groupname) {
		String sql = "update frend set groupID=? where uesrnum=? and friendnum = ? ";
		Connection con = DBCconn.openDB();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, groupname);
			ps.setInt(2, qqid);
			ps.setInt(3, frendid);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	@Override
	public void changeStement(int qqid, String Stement) {
		String sql = "update account set stement = ? where QQid = ?";
		Connection con = DBCconn.openDB();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, Stement);
			ps.setInt(2, qqid);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vector<Vector> findFrend(final String sql) {
		Vector<Vector> datas = new Vector<Vector>();
		Connection con = DBCconn.openDB();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Vector reData = new Vector();
				reData.addElement(rs.getString("photo").trim());
				reData.addElement(rs.getInt("QQid"));
				reData.addElement(rs.getString("infoname").trim());
				reData.addElement(rs.getString("stement").trim());
				reData.addElement(rs.getInt("age"));
				reData.addElement(rs.getString("sex").trim());
				reData.addElement(rs.getString("constellation").trim());
				reData.addElement(rs.getString("boold").trim());
				reData.addElement(rs.getString("mzhome").trim());
				reData.addElement(rs.getString("interst").trim());
				reData.addElement(rs.getString("prename").trim());
				datas.addElement(reData);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return datas;
	}

	@Override
	public boolean isFrend(int myqqid, int friendqqid) {
		String sql = "select * from frend where uesrnum =? and friendnum = ?";
		boolean isfrend = false;
		Connection con = DBCconn.openDB();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, myqqid);
			ps.setInt(2, friendqqid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				isfrend = true;
			}
			ps.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return isfrend;
	}

	@Override
	public Account getSelectedFriend(int myqqid) {
		String sql = "select * from account where QQid = ?";
		Account acc = new Account();
		Connection con = DBCconn.openDB();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, myqqid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				acc.setQQid(rs.getInt("QQid"));
				acc.setInfoname(rs.getString("infoname"));
				acc.setPhoto(rs.getString("photo"));
				acc.setAge(rs.getInt("age"));
				acc.setSex(rs.getString("sex"));
				acc.setStement(rs.getString("stement"));
				acc.setConstellation(rs.getString("constellation"));
				acc.setBoold(rs.getString("boold"));
				acc.setPort(rs.getInt("port"));
				acc.setIpadress(rs.getString("ipadress"));
				acc.setInterst(rs.getString("interst"));
				acc.setPrename(rs.getString("prename"));
			}
			ps.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return acc;
	}

	@Override
	public void addFrend(int myqqid, int frendqqid) {
		Connection con = DBCconn.openDB();
		String sql = "insert into frend values(?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, myqqid);
			ps.setInt(2, frendqqid);
			ps.setString(3, cmd.GROUPNAME[0]);
			ps.setInt(4, 0);
			ps.executeUpdate();
			ps.setInt(1, frendqqid);
			ps.setInt(2, myqqid);
			ps.setString(3, cmd.GROUPNAME[0]);
			ps.setInt(4, 0);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	@Override
	public Account update(Account acc) {
		String sql = "update account set infoname = ?,photo = ?,age = ?,sex = ?,constellation=?,boold =?,interst=?,prename=? where qqid=?";
		Connection con = DBCconn.openDB();
		try {
			int i = 1;
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(i++, acc.getInfoname());
			ps.setString(i++, acc.getPhoto());
			ps.setInt(i++, acc.getAge());
			ps.setString(i++, acc.getSex());
			ps.setString(i++, acc.getConstellation());
			ps.setString(i++, acc.getBoold());
			ps.setString(i++, acc.getInterst());
			ps.setString(i++, acc.getPrename());
			ps.setInt(i++, acc.getQQid());
			i = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch
			e.printStackTrace();
		}

		return acc;
	}

	@Override
	public void delFreand(int myqqid, int frendqqid) {
		String sql = "delete frend where friendnum = ? and uesrnum =?";
		Connection con = DBCconn.openDB();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, myqqid);
			ps.setInt(2, frendqqid);
			System.out.println(frendqqid);
			ps.executeUpdate();
			ps.setInt(1, frendqqid);
			System.out.println(frendqqid);
			ps.setInt(2, myqqid);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	public void updatepassword(int qqid, String password) {
		String sql = "update account set password = ? where qqid = ?";
		Connection con = DBCconn.openDB();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, password);
			ps.setInt(2, qqid);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	@Override
	public Account selectpasswordd(Account acc) {
		String sql = "select * from account where qqid = ?";
		Connection con = DBCconn.openDB();
		Account a = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, acc.getQQid());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				a = new Account();
				a.setPassword(rs.getString("password"));
				rs.close();
			} else {
				return a = null;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return a;

	}

}
