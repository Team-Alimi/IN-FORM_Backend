package goat.inform_backend.dto;

import goat.inform_backend.entity.vendors.Vendors;
import lombok.Getter;

@Getter
public class VendorResponseDto {
    private final String vendor_name;

    public VendorResponseDto(Vendors vendor) {
        this.vendor_name = vendor.getVendorName();
    }
}