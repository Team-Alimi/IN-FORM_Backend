package goat.inform_backend.repository;

import goat.inform_backend.entity.articles.Articles;
import goat.inform_backend.entity.vendors.VendorType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlesRepository extends JpaRepository<Articles,Integer>{
    Page<Articles> findByVendors_VendorType(VendorType vendorType, Pageable pageable);
    Page<Articles> findByTitleContaining(String search, Pageable pageable);
    Page<Articles> findByTitleContainingAndVendors_VendorType(String search, VendorType type, Pageable pageable);
}
