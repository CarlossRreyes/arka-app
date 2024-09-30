package com.arka.app_services.services;

public interface ISeedService {

    public void runSeed();

    public void insertRoles();
    public void insertPrivileges();
    public void insertUsers();


    public void deleteUserRole();
    public void deleteRolePrivilege();
    public void deleteRole();
    public void deleteUser();
    public void deletePrivilege();

    public void insertCategories();
    public void insertProducts();
    public void insertPackages();
    public void insertBusinessType();
    public void insertBusiness();


    public void deleteAllImageProducts();
    public void deleteAllCategoriesProducts();
    public void deleteAllPackageDetails();
    public void deleteAllProductPricing();
    public void deteleAllPackageDetailsProduct();
    public void deleteAllPackagePricing();
    
    public void deleteAllCategories();
    public void deleteAllProducts();
    public void deleteAllPackages();
    public void deleteAllBusinessType();
    public void deleteAllBusiness();

}


