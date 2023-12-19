package bll.util;


import dal.SongDB;
import BE.Song;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SongManager {

    public SongManager() throws IOException {
        Map<String, Object> row = null;
        this.songDB = new SongDB(row);
    }
    private SongDB songDB;

        public List<Song> getAllSongs() throws Exception {
            return songDB.getAllSongs();
        }


}
