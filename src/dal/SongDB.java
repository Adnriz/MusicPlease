package dal;

import BE.Song;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SongDB {
    private SQLController sqlController;

    public SongDB(Map<String, Object> row) throws IOException {
        this.sqlController = new SQLController();
    }

    public List<Song> getAllSongs() throws Exception {

        ArrayList<Song> allSongs = new ArrayList<>();

        try (Connection conn = sqlController.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM dbo.Music;";
            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to Movie object
                String name = rs.getString("title");
                String artist = rs.getString("artist");
                String genre = rs.getString("genre");

                Song song = new Song(name, artist, genre);
                allSongs.add(song);
            }
            return allSongs;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not get the song from database", ex);
        }


    }
}
