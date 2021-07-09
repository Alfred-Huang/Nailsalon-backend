package com.nailsalon.nailsalonbackend.dao;

import com.nailsalon.nailsalonbackend.pojo.NBAPlayer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NBAPlayerDao {
    @Select("select * from user_table")
    List<NBAPlayer> selectAll();
}
