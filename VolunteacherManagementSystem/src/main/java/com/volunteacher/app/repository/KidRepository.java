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


@Repository
public interface KidRepository extends PagingAndSortingRepository<Kid, Long>{
	
	public List<Kid> findAllByGroupGroupId(int group);
	
	public List<Kid> findAllByVillageVillageId(int id);
	
	public List<Kid> findAllByAreaAreaId(int id);
	
	public Page<Kid> findAllByVillageVillageIdAndGroupGroupId(int vid, int gid,Pageable pageable);
	
	public List<Kid> findAllByVillageVillageIdAndAreaAreaId(int vid,int aid);
	
	public List<Kid> findAllByAreaAreaIdAndGroupGroupId(int aid,int gid);
	
	public List<Kid> findAllByAreaAreaIdAndGroupGroupIdAndVillageVillageId(int aid,int vid,int gid);
	
	public List<Kid> findAllByLevel(int level);
	
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
}
