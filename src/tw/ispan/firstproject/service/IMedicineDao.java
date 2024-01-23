package tw.ispan.firstproject.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import tw.ispan.firstproject.entity.Medicine;

public interface IMedicineDao {
	public Medicine getDataById(int id) ;
	public void deleteByName(String name );
	public void updateById(Medicine m);
	public void savePic(String picName)throws FileNotFoundException, SQLException;
	public void getPicById(int id,String name)throws SQLException, IOException ;
	public Connection createConn() throws Exception;
	public void closeConn() throws SQLException;
	

}
