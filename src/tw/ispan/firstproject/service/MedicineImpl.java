package tw.ispan.firstproject.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tw.ispan.firstproject.entity.Medicine;

public class MedicineImpl implements IMedicineDao {

	private Connection conn;

	@Override
	public Medicine getDataById(int id)  {
		String sql = "select * from DataGovTw where id =?";
		Medicine m = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			state = conn.prepareStatement(sql);
			state.setInt(1, id);
			rs = state.executeQuery();
			if (rs.next()) {
				m = new Medicine();
				m.setId(rs.getInt("id"));
				m.setDate(rs.getDate("date"));
				m.setName(rs.getString("name"));
				m.setLicenseCode(rs.getString("licenseCode"));
				m.setSupplyStatus(rs.getString("supplyStatus"));
				} 
			}catch (SQLException e) {
					e.printStackTrace();
			}catch(Exception e) {
				System.out.println("該ID不存在");
			}
			try {
				if(rs != null) {
				rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if(rs != null) {
					state.close();
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		return m;
		
	}

	@Override
	public void deleteByName(String name) {
		String sql = "delete from DataGovTw where name =?";
		PreparedStatement state = null;
		try {
			state = conn.prepareStatement(sql);
			state.setString(1,name);
			int count = state.executeUpdate();
			System.out.println("刪除" + count + "筆資料: " + name);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void updateById(Medicine m) {
		String sql = "update  DataGovTw set name = ? where id =?  ";
		PreparedStatement state = null;
		try {
			state = conn.prepareStatement(sql);
			state.setString(1, m.getName());
			state.setInt(2, m.getId());
			int count = state.executeUpdate();
			System.out.println("修改" + count + "筆資料: " + m.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void savePic(String picName) throws FileNotFoundException, SQLException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("./src/pic/java01.png")) ;
		String sql = "insert into Files (FileName,FileContent) values (?,?)";
		PreparedStatement state = conn.prepareStatement(sql);
		state.setString(1, picName);
		state.setBinaryStream(2, bis);
		int count = state.executeUpdate();
		System.out.println("新增" + count + "筆資料: " + picName);

	}

	@Override
	public void getPicById(int id,String name) throws SQLException, IOException {
		
		String sql = "select * from Files where FileId =?";
		PreparedStatement state = conn.prepareStatement(sql);
		state.setInt(1, id);
		ResultSet rs = state.executeQuery();
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("c:/Temp/"+name+".png"));
//		if(rs.next()) {
//			Blob blob = rs.getBlob(3);
//			int file = (int)blob.length();
//			bos.write(blob.getBytes(1, file));
//			bos.flush();
//		}
		
		int i; 
		if(rs.next()) {
			InputStream files = rs.getBinaryStream("FileContent");
			while((i = files.read()) != -1){
					bos.write(i);
					bos.flush();
			}
		}
		  
		
		rs.close();
		state.close();

	}

	@Override
	public Connection createConn() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://localhost:1433;databaseName=IspanFistProject;;encrypt=true;trustServerCertificate=true";
		String user = "watcher";
		String pw = "P@ssw0rd";
		conn = DriverManager.getConnection(url, user, pw);
		System.out.println("isConnection: " + !conn.isClosed());
		return conn;

	}

	@Override
	public void closeConn() throws SQLException {
		if (conn != null) {
			conn.close();
		}

		System.out.println("isClosed");

	}

}
