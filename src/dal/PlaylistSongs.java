package dal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlaylistSongs {

    private SQLController sqlController;

    public PlaylistSongs() throws IOException {
        this.sqlController = new SQLController();
    }

    // Method to save a song into a playlist
    public void addSongToPlaylist(int playlistId, int songId) {
        String query = "INSERT INTO PlaylistSongs (PlaylistID, SongID) VALUES (?, ?)";
        sqlController.executePreparedUpdate(query, playlistId, songId);
    }

    // Method to remove a song from a playlist
    public void removeSongFromPlaylist(int playlistId, int songId) {
        String query = "DELETE FROM PlaylistSongs WHERE PlaylistID = ? AND SongID = ?";
        sqlController.executePreparedUpdate(query, playlistId, songId);
    }

    // Method to get songs from a playlist
    public List<SongDB> getSongsFromPlaylist(int playlistId) throws IOException {
        String query = "SELECT * FROM SongDB JOIN PlaylistSongs ON SongDB.SongID = PlaylistSongs.SongID WHERE PlaylistID = ?";
        List<Map<String, Object>> result = sqlController.executePreparedQuery(query, playlistId);
        List<SongDB> songsInPlaylist = new ArrayList<>();

        for (Map<String, Object> row : result){
            // assuming your SongDB class have a constructor that initiate it from Map
            songsInPlaylist.add(new SongDB(row));
        }

        return songsInPlaylist;
    }
}