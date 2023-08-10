package mainpackage.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.springframework.stereotype.Component;

@Component
public class PermCustomer {
	
	private int REPNUM;
	
	private String nameAndSurname;
	private int monReq;
	private int tueReq;
	private int wedReq;
	private int thuReq;
	private int friReq;
	private int satReq;
	private int sunReq;
	
	public int getREPNUM() {
        return REPNUM;
    }

    public void setREPNUM(int REPNUM) {
        this.REPNUM = REPNUM;
    }
    
    public void incrementREPNUM() {
        this.REPNUM++;
    }

    public void updateREPNUMInDatabase(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            String updateQuery = "UPDATE PERMCUSTOMER SET REPNUM = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setInt(1, this.REPNUM);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initializeREPNUMFromDatabase(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            String selectQuery = "SELECT REPNUM FROM PERMCUSTOMER";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int initialREPNUM = resultSet.getInt("REPNUM");
                this.REPNUM = initialREPNUM;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String getNameAndSurname() {
		return nameAndSurname;
	}
	
	public void setNameAndSurname(String nameAndSurname) {
		this.nameAndSurname = nameAndSurname;
	}
    
	public int getMonReq() {
		return monReq;
	}

	public void setMonReq(int monReq) {
		this.monReq = monReq;
	}

	public int getTueReq() {
		return tueReq;
	}

	public void setTueReq(int tueReq) {
		this.tueReq = tueReq;
	}

	public int getWedReq() {
		return wedReq;
	}

	public void setWedReq(int wedReq) {
		this.wedReq = wedReq;
	}

	public int getThuReq() {
		return thuReq;
	}

	public void setThuReq(int thuReq) {
		this.thuReq = thuReq;
	}

	public int getFriReq() {
		return friReq;
	}

	public void setFriReq(int friReq) {
		this.friReq = friReq;
	}

	public int getSatReq() {
		return satReq;
	}

	public void setSatReq(int satReq) {
		this.satReq = satReq;
	}

	public int getSunReq() {
		return sunReq;
	}

	public void setSunReq(int sunReq) {
		this.sunReq = sunReq;
	}
	
    
}