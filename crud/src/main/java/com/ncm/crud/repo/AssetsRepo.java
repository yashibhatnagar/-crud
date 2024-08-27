package com.ncm.crud.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ncm.crud.entity.Assets;

@Repository
public interface AssetsRepo extends JpaRepository<Assets, Integer> {
	@Query("SELECT a.slNo FROM Assets a WHERE a.subcategory.id = :subcategoryId")
    List<String> findSerialNumbersBySubcategoryId(@Param("subcategoryId") Long subcategoryId);

    @Query("SELECT DISTINCT a.companyname FROM Assets a WHERE a.subcategory.id = :subcategoryId")
    List<String> findCompaniesBySubcategoryId(@Param("subcategoryId") Long subcategoryId);


}
