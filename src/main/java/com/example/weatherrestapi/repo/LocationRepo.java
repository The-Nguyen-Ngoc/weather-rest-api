package com.example.weatherrestapi.repo;

import com.example.weatherrestapi.entity.Location;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface LocationRepo extends CrudRepository<Location, String> {

    @Query("SELECT l FROM Location l WHERE l.trashed = false ")
    public List<Location> findUnTrashed();
    @Query("SELECT l FROM Location l WHERE l.trashed = false and l.code=?1")
    public Location findUnTrashedByCode(String code);
    @Modifying
    @Query("update Location SET trashed = true WHERE code =?1")
    public void deleteLocation(String code);
}
