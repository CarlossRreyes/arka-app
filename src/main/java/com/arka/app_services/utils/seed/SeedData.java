package com.arka.app_services.utils.seed;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.arka.app_services.dtos.admin.create.BusinessCreateDto;
import com.arka.app_services.dtos.admin.create.BusinessTypeCreateDto;
import com.arka.app_services.dtos.admin.create.CategoryCreateDto;
import com.arka.app_services.dtos.admin.create.PackageCreateDto;
import com.arka.app_services.dtos.admin.create.PackageDetailsCreateDto;
import com.arka.app_services.dtos.admin.create.ProductCreateDto;
import com.arka.app_services.dtos.client.read.PrivilegeCliDto;
// import com.arka.app_services.dtos.consumer.read.PrivilegeCliDto;
// import com.arka.app_services.dtos.consumer.read.RoleCliDto;
// import com.arka.app_services.dtos.consumer.read.UserCliDto;
import com.arka.app_services.dtos.client.read.RoleCliDto;
import com.arka.app_services.dtos.client.read.UserCliDto;

import lombok.Getter;

@Getter
@Component
public class SeedData {

    private List<CategoryCreateDto> categories = new ArrayList<>();
    private List<ProductCreateDto> products = new ArrayList<>();
    private List<PackageCreateDto> packages = new ArrayList<>();
    private List<BusinessTypeCreateDto> businessTypes = new ArrayList<>();
    private List<BusinessCreateDto> businesses = new ArrayList<>();
    private List<RoleCliDto> roles = new ArrayList<>();
    private List<PrivilegeCliDto> privileges = new ArrayList<>();
    private List<UserCliDto> users = new ArrayList<>();


    public SeedData() {



        //********************Admin User for Default************
        users.add( 
        new UserCliDto(                  
                "carlos@gmail.com",
                "Abc123", 
                true      
            )
        );




        //*******************Roles***************
        roles.add( 
        new RoleCliDto(                  
                "ADMIN",
                "This is description admin role", 
                true      
            )
        );
        roles.add( 
        new RoleCliDto(                  
                "CLIENT",
                "This is description client role", 
                true      
            )
        );
        
        
        //*******************Privileges***************
        privileges.add( 
            new PrivilegeCliDto(                  
                "READ_PRIVILEGE",
                "This is description WRITE_PRIVILEGE", 
                true      
            )
        );
        privileges.add( 
            new PrivilegeCliDto(                  
                "WRITE_PRIVILEGE",
                "This is description WRITE_PRIVILEGE", 
                true      
            )
        );
        
        

        //*****************Type Business*********
        businessTypes.add( 
        new BusinessTypeCreateDto(                  
                "BEBN01",
                "Beibidas, Lacteos", 
                "This is Beibidas, Lacteos description."             
            )
        );

        businessTypes.add( 
        new BusinessTypeCreateDto(                  
                "FARBN01",
                "Farmacias", 
                "This is farmacia description."             
            )
        );

        businessTypes.add( 
        new BusinessTypeCreateDto(                  
                "FERREBN01",
                "Ferreterias", 
                "This is farmacia description."             
            )
        );
        

        //****************Business***************
        businesses.add(
            new BusinessCreateDto(
                "COCABI01",
                "Coca cola company",
                "BEBN01",
                "This is Coca cola company",
                "Ecuador, Guayas, via a Daule",
                "image.jpg"
                // ,new HashSet<>( Arrays.asList( "SDA001", "COCA001" ))
                

            )
        );

        businesses.add(
            new BusinessCreateDto(
                "TONYBN01",
                "Tony company",
                "BEBN01",
                "This is Tony company",
                "Ecuador, Guayas, via a Duran",
                "image.jpg"
                // ,new HashSet<>( Arrays.asList( "SDA001", "COCA001" ))
            )
        );



        // ***************Categories*************
        // categories.add( 
        // new CategoryCreateDto(                  
        //         "ENR001",
        //         "Energizing", 
        //         "This is Energizing description.", 
        //         "e.jpg", 
        //         true
        //     )
        // );
        // categories.add( 
        //     new CategoryCreateDto(               
        //         "WAT001",
        //         "Water", 
        //         "This is agua description", 
        //         "w.jpg", 
        //         true
        //     )
        // );
        categories.add( 
            new CategoryCreateDto(            
                "SDA001",
                "COCABI01",
                "Soda", 
                "This is soda description", 
                "s.jpg", 
                true
            )
        );
        
        // categories.add( 
        //     new CategoryCreateDto(
        //         "JUI001",
        //         "Juices", 
        //         "This is juice description", 
        //         "s.jpg", 
        //         true
        //     )
        // );


        // categories.add( 
        //     new CategoryCreateDto(
        //         "TE001",
        //         "Té", 
        //         "This is Té description", 
        //         "s.jpg", 
        //         true
        //     )
        // );
        // categories.add( 
        //     new CategoryCreateDto(                    
        //         "NEC001",
        //         "Néctar", 
        //         "This is Néctar description", 
        //         "s.jpg", 
        //         true
        //     )
        // );

        categories.add( 
            new CategoryCreateDto(                  
                "COCA001",
                "COCABI01",
                "Coca Cola", 
                "This is Coca Cola", 
                "s.jpg", 
                true
            )
        );



        // ****************SABOR ORIGINAL*************
        products.add(
            new ProductCreateDto(  
                "COCABI01",              
                "COCAPT001", 
                "Coca-Cola Sabor Original 3000 ML.", 
                "Coca-Cola Sabor Original 3000 ML.", 
                BigDecimal.valueOf( 3.00 ),                 
                50, 
                true, 
                new HashSet<>( Arrays.asList( "SDA001", "COCA001" )),
                new HashSet<>( 
                    Arrays.asList( 
                        "69cac728-c102-42b5-9df0-fd5795646c64_Coca-Cola%20Sabor%20Original%203000%20ML.png"                        
                    )
                )
                
            )
        );
        products.add(
            new ProductCreateDto(     
                "COCABI01",             
                "COCAPT002", 
                "Coca-Cola Sabor Original 1000 ML.", 
                "Coca-Cola Sabor Original 1000 ML.", 
                BigDecimal.valueOf( 1.00 ),                 
                200, 
                true, 
                new HashSet<>( Arrays.asList( "SDA001", "COCA001" )),
                new HashSet<>( 
                    Arrays.asList( 
                        "762574e6-f9ed-4724-a9ee-7fc638bc9216_Coca-Cola%20Sabor%20Original%201000%20ML.png"                        
                    )
                )
                
            )
        );



        //*******************PACKAGE *****************/
        packages.add(
            new PackageCreateDto(
                6, 
                BigDecimal.valueOf(16.06), 
                false,
                new HashSet<>(
                    Arrays.asList(
                        new PackageDetailsCreateDto( "COCAPT001",  6, BigDecimal.valueOf(16.06))
                        // new PackageDetailsCreateDto( "COCA001",  22, BigDecimal.valueOf(6.06))
                    )
                )
            )
        );
        packages.add(
            new PackageCreateDto(
                0, 
                BigDecimal.valueOf(10.12), 
                true,
                new HashSet<>(
                    Arrays.asList(
                        new PackageDetailsCreateDto( "COCAPT001",  6, BigDecimal.valueOf(16.06)),
                        new PackageDetailsCreateDto( "COCAPT002",  12, BigDecimal.valueOf(10.22))
                    )
                )
            )
        );
    }


}
