package com.volunteacher.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
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
}
