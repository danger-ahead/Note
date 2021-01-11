import java.sql.*;

public class TemplateForQuery {

    String url = "jdbc:mysql://localhost:3306/notes";
    String userName = "note_user";
    String pass = "";

    public String getNote(int x) throws SQLException, ClassNotFoundException {
        String data=null;
        String query = "select note from notes where timestamp="+x;
        //String query = "select * from notes";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                data = resultSet.getString(2);
            }

            statement.close();
            connection.close();

            return data;
        }
        catch(Exception exception){
            System.out.println("error!");
            return data;
        }


    }

    public void getNote() throws SQLException, ClassNotFoundException {
        String data;
        String query = "select * from notes";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            //resultSet.next();
            while (resultSet.next()) {
                data = resultSet.getInt(1) + " : " + resultSet.getString(2);
                System.out.println(data);
            }

            statement.close();
            connection.close();
        }
        catch (ClassNotFoundException | SQLException exception) {
            System.out.println("error!");
        }
    }

    public void addNote(int x, String note) throws SQLException, ClassNotFoundException {
        try{
            String timeStamp = Integer.toString(x);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, pass);

            PreparedStatement preparedStatement = connection.prepareStatement("insert into notes values(?,?)");
            preparedStatement.setString(1,timeStamp);
            preparedStatement.setString(2,note);

            preparedStatement.executeUpdate();
            System.out.println("note added!");

            preparedStatement.close();
            connection.close();
        }
        catch(SQLException | ClassNotFoundException exception){
            System.out.println("Error while connecting to the database\n error: "+exception);
        }
    }

    public void updateNote(int x, String note) throws SQLException, ClassNotFoundException {
        try{
            String timeStamp = Integer.toString(x);

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, pass);

            PreparedStatement preparedStatement = connection.prepareStatement("update notes set note=? where timestamp=?");

            preparedStatement.setString(1,note);
            preparedStatement.setString(2,timeStamp);

            preparedStatement.executeUpdate();
            System.out.println("note updated");
        }
        catch(SQLException | ClassNotFoundException exception){
            System.out.println("Error while connecting to the database\n error: "+exception);
        }
    }
}
