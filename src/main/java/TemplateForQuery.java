import java.sql.*;

public class TemplateForQuery {

    String url = "jdbc:mysql://localhost:3306/notes";
    String userName = "note_user";
    String pass = "";

    public String getNote(String x){  //issue with this method, always returns null
        String data=null;
        String query = "select note from notes where timestamp="+x;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                data = resultSet.getInt(1) + " -> " + resultSet.getString(3);
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

    public void getNote(){
        String data;
        String query = "select * from notes";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            //resultSet.next();
            while (resultSet.next()) {
                data = resultSet.getInt(1) + ". " + resultSet.getString(2) + " -> " + resultSet.getString(3);
                System.out.println(data);
            }

            statement.close();
            connection.close();
        }
        catch (ClassNotFoundException | SQLException exception) {
            System.out.println("error!");
        }
    }

    public void addNote(String x, String note){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, pass);

            String query = "select * from notes ORDER BY notenumber DESC LIMIT 1";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();

            String newNoteNumber;
            if(nullCheck()){
                newNoteNumber = "1";
            }
            else{
                int noteNumber = resultSet.getInt(1);
                newNoteNumber = Integer.toString(noteNumber+1);
            }

            PreparedStatement preparedStatement = connection.prepareStatement("insert into notes values(?,?,?)");
            preparedStatement.setString(1,newNoteNumber);
            preparedStatement.setString(2,x);
            preparedStatement.setString(3,note);

            preparedStatement.executeUpdate();
            System.out.println("note added!");

            preparedStatement.close();
            connection.close();
        }
        catch(SQLException | ClassNotFoundException exception){
            System.out.println("Error while connecting to the database\n error: "+exception);
        }
    }

    public void updateNote(String x, String note){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, userName, pass);

            PreparedStatement preparedStatement = connection.prepareStatement("update notes set note=? where notenumber=?");

            preparedStatement.setString(1,note);
            preparedStatement.setString(2,x);

            preparedStatement.executeUpdate();
            System.out.println("note updated");
        }
        catch(SQLException | ClassNotFoundException exception){
            System.out.println("Error while connecting to the database\n error: "+exception);
        }
    }

    public boolean nullCheck() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, userName, pass);
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * From notes";

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet =  preparedStatement.executeQuery();

            boolean empty = true;
            while( resultSet.next() ) {
                empty = false;
            }
            return empty;
        }
        catch (SQLException exception) {
            System.out.println("Error while connecting to the database\n error: "+exception);
            return false;
        }
    }
}