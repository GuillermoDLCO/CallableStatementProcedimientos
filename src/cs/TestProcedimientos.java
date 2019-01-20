package cs;

import datos.Conexion;
import java.sql.*;

public class TestProcedimientos {
    
    public static void main(String args[]){
        int empleadoId = 100;
        //salario = salario*incremento
        double incrementoSalario = 1.1;
        Connection con;
        try {
            con = Conexion.getConnection();
            Statement stmt = null;
            ResultSet rs = null;
            CallableStatement cstmt = null;
            
            stmt = con.createStatement();
            
            //Se llama al StoreProcedure para incrementar el salario
            System.out.println("Aumento del 10% al empleado: " + empleadoId);
            cstmt = con.prepareCall("{call set_employee_salary(?,?)}");
            cstmt.setInt(1, empleadoId);
            cstmt.setDouble(2, incrementoSalario);
            cstmt.execute();
            cstmt.close();
            String query = "SELECT first_name, salary FROM employees "
                    + "WHERE employee_id = " + empleadoId;
            rs = stmt.executeQuery(query);
            rs.next();
            System.out.println("Nombre: " + rs.getString(1));
            System.out.println("Salario nuevo: " + rs.getFloat(2));
            
                    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
