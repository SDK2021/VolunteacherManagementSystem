package com.volunteacher.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.volunteacher.app.model.Kid;


@Repository
public interface KidRepository extends PagingAndSortingRepository<Kid, Long>{
	
	public Page<Kid> findAllByGroupGroupId(int group,Pageable pageable);
	
	public Page<Kid> findAllByVillageVillageId(int id,Pageable pageable);
	
	public Page<Kid> findAllByAreaAreaId(int id,Pageable pageable);
	
	public Page<Kid> findAllByVillageVillageIdAndGroupGroupId(int vid, int gid,Pageable pageable);
	
	public Page<Kid> findAllByVillageVillageIdAndAreaAreaId(int vid,int aid,Pageable pageable);
	
	public Page<Kid> findAllByAreaAreaIdAndGroupGroupId(int aid,int gid,Pageable pageable);
	
	public Page<Kid> findAllByAreaAreaIdAndGroupGroupIdAndVillageVillageId(int aid,int vid,int gid,Pageable pageable);
	
	public Page<Kid> findAllByLevel(int level,Pageable pageable);
	
	@Query(value = "select COUNT(*) from kid",nativeQuery = true)
	public int totalKids();
}
