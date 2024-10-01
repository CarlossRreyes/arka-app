package com.arka.app_services.utils.seed;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;





import com.arka.app_services.dtos.client.create.BusinessCreateCliDto;
import com.arka.app_services.dtos.client.create.BusinessTypeCreateCliDto;
import com.arka.app_services.dtos.client.create.CategoryCreateCliDto;
import com.arka.app_services.dtos.client.create.PackageCreateCliDto;
import com.arka.app_services.dtos.client.create.PackageDetailsCreateCliDto;
import com.arka.app_services.dtos.client.create.PrivilegeCreateCliDto;
import com.arka.app_services.dtos.client.create.ProductCreateCliDto;
import com.arka.app_services.dtos.client.create.RoleCreateCliDto;
import com.arka.app_services.dtos.client.create.UserCreateCliDto;


import lombok.Getter;

@Getter
@Component
public class SeedData {

    private List<CategoryCreateCliDto> categories = new ArrayList<>();
    private List<ProductCreateCliDto> products = new ArrayList<>();
    private List<PackageCreateCliDto> packages = new ArrayList<>();
    private List<BusinessTypeCreateCliDto> businessTypes = new ArrayList<>();
    private List<BusinessCreateCliDto> businesses = new ArrayList<>();
    private List<RoleCreateCliDto> roles = new ArrayList<>();
    private List<PrivilegeCreateCliDto> privileges = new ArrayList<>();
    private List<UserCreateCliDto> users = new ArrayList<>();


    public SeedData() {



        //********************Admin User for Default************
        users.add( 
        new UserCreateCliDto(                  
                "carlos@gmail.com",
                "Abc123", 
                true      
            )
        );




        //*******************Roles***************
        roles.add( 
        new RoleCreateCliDto(                  
                "ADMIN",
                "This is description admin role", 
                true      
            )
        );
        roles.add( 
        new RoleCreateCliDto(                  
                "CLIENT",
                "This is description client role", 
                true      
            )
        );
        
        
        //*******************Privileges***************
        privileges.add( 
            new PrivilegeCreateCliDto(                  
                "READ_PRIVILEGE",
                "This is description WRITE_PRIVILEGE", 
                true      
            )
        );
        privileges.add( 
            new PrivilegeCreateCliDto(                  
                "WRITE_PRIVILEGE",
                "This is description WRITE_PRIVILEGE", 
                true      
            )
        );
        
        

        //*****************Type Business*********
        businessTypes.add( 
        new BusinessTypeCreateCliDto(                  
                "BEBN01",
                "Beibidas, Lacteos", 
                "This is Beibidas, Lacteos description."             
            )
        );

        businessTypes.add( 
        new BusinessTypeCreateCliDto(                  
                "FARBN01",
                "Farmacias", 
                "This is farmacia description."             
            )
        );

        businessTypes.add( 
        new BusinessTypeCreateCliDto(                  
                "FERREBN01",
                "Ferreterias", 
                "This is farmacia description."             
            )
        );
        

        //****************Business***************
        businesses.add(
            new BusinessCreateCliDto(
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
            new BusinessCreateCliDto(
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
        // new CategoryCreateCliDto(                  
        //         "ENR001",
        //         "Energizing", 
        //         "This is Energizing description.", 
        //         "e.jpg", 
        //         true
        //     )
        // );
        // categories.add( 
        //     new CategoryCreateCliDto(               
        //         "WAT001",
        //         "Water", 
        //         "This is agua description", 
        //         "w.jpg", 
        //         true
        //     )
        // );
        categories.add( 
            new CategoryCreateCliDto(            
                "COCABI01",
                "SDA001",
                "Soda", 
                "This is soda description", 
                "s.jpg", 
                true
            )
        );
        
        // categories.add( 
        //     new CategoryCreateCliDto(
        //         "JUI001",
        //         "Juices", 
        //         "This is juice description", 
        //         "s.jpg", 
        //         true
        //     )
        // );


        // categories.add( 
        //     new CategoryCreateCliDto(
        //         "TE001",
        //         "Té", 
        //         "This is Té description", 
        //         "s.jpg", 
        //         true
        //     )
        // );
        // categories.add( 
        //     new CategoryCreateCliDto(                    
        //         "NEC001",
        //         "Néctar", 
        //         "This is Néctar description", 
        //         "s.jpg", 
        //         true
        //     )
        // );

        categories.add( 
            new CategoryCreateCliDto(                  
                "COCABI01",
                "COCA001",
                "Coca Cola", 
                "This is Coca Cola", 
                "s.jpg", 
                true
            )
        );



        // ****************SABOR ORIGINAL*************
        products.add(
            new ProductCreateCliDto(  
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
            new ProductCreateCliDto(     
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
            new PackageCreateCliDto(
                6, 
                BigDecimal.valueOf(16.06), 
                false,
                new HashSet<>(
                    Arrays.asList(
                        new PackageDetailsCreateCliDto( "COCAPT001",  6, BigDecimal.valueOf(16.06))
                        // new PackageDetailsCreateCliDto( "COCA001",  22, BigDecimal.valueOf(6.06))
                    )
                )
            )
        );
        packages.add(
            new PackageCreateCliDto(
                0, 
                BigDecimal.valueOf(10.12), 
                true,
                new HashSet<>(
                    Arrays.asList(
                        new PackageDetailsCreateCliDto( "COCAPT001",  6, BigDecimal.valueOf(16.06)),
                        new PackageDetailsCreateCliDto( "COCAPT002",  12, BigDecimal.valueOf(10.22))
                    )
                )
            )
        );
    }


}
