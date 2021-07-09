package com.nailsalon.nailsalonbackend.service;

import com.nailsalon.nailsalonbackend.pojo.NBAPlayer;

import java.io.IOException;
import java.util.Map;

public interface NBAPlayerService {
    boolean addPlayer(NBAPlayer player, String id) throws IOException;
    Map<String, Object> getPlayer(String id) throws IOException;
    boolean updatePlayer(NBAPlayer player, String id) throws IOException;
}
