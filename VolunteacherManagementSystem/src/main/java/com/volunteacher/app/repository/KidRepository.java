package com.volunteacher.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Kid;
import com.volunteacher.app.model.Volunteacher;


@Repository
public interface KidRepository extends PagingAndSortingRepository<Kid, Long>{
	
	public Page<Kid> findAllByGroupGroupId(int group,Pageable pageable);
	
	public Page<Kid> findAllByVillageVillageId(int id,Pageable pageable);
	
	public Page<Kid> findAllByAreaAreaId(int id,Pageable pageable);
	
	public List<Kid> findAllByAreaAreaId(int id);
	
	public Page<Kid> findAllByVillageVillageIdAndGroupGroupId(int vid, int gid,Pageable pageable);
	
	public Page<Kid> findAllByVillageVillageIdAndAreaAreaId(int vid,int aid,Pageable pageable);
	
	public Page<Kid> findAllByAreaAreaIdAndGroupGroupId(int aid,int gid,Pageable pageable);
	
	public Page<Kid> findAllByAreaAreaIdAndGroupGroupIdAndVillageVillageId(int aid,int vid,int gid,Pageable pageable);
	
	public Page<Kid> findAllByLevel(int level,Pageable pageable);
	
	@Query(value ="select COUNT(*) from kid where DATE(NOW()) < DATE_ADD(creation_date,INTERVAL 1 YEAR) ORDER BY creation_date DESC",nativeQuery = true)
	public int newAllKids();
	
	@Query(value = "select COUNT(*) from kid",nativeQuery = true)
	public int totalKids();
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM project_kids WHERE kids_kid_id =:id",nativeQuery = true)
	public void deleteKidsProjects(@Param("id") long id);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM event_kids WHERE kids_kid_id =:id",nativeQuery = true)
	public void deleteKidsEvents(@Param("id") long id);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM attendance_kids WHERE kids_kid_id =:id",nativeQuery = true)
	public void deleteKidsAttendance(@Param("id") long id);
	
	@Query(value = "select COUNT(*) from kid where village_village_id = :villageId",nativeQuery = true)
	int totalKidsVillage(int villageId);
	
	public Page<Kid> findAllByStandard(Pageable pageable,int std); 
	
}
