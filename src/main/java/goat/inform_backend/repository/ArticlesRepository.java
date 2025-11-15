package goat.inform_backend.repository;

import goat.inform_backend.entity.articles.Articles;
import goat.inform_backend.entity.vendors.VendorType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;  //
import org.springframework.data.repository.query.Param; //

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ArticlesRepository extends JpaRepository<Articles,Integer>{
    Page<Articles> findByVendors_VendorType(VendorType vendorType, Pageable pageable);
    Page<Articles> findByTitleContaining(String search, Pageable pageable);
    Page<Articles> findByTitleContainingAndVendors_VendorType(String search, VendorType type, Pageable pageable);
    Page<Articles> findByVendors_VendorTypeAndCategories_CategoryName(
            VendorType type, String categoryName, Pageable pageable
    );
    @Query("SELECT a FROM Articles a WHERE a.startDate <= :date AND a.dueDate >= :date")
    List<Articles> findByMonth(
            @Param("date") LocalDate date
    );
    @Query("SELECT a FROM Articles a WHERE a.startDate <= :date AND a.dueDate >= :date AND a.vendors.vendorType = :type")
    List<Articles> findByMonthAndVendors_VendorType(
            @Param("date") LocalDate date,
            @Param("type") VendorType type
    );
}
