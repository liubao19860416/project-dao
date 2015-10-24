package com.saike.grape.dao.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MaintenanceUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSplitCode() {
        String brand = "RW-";
        String subBrand = "SH-";
        String series = "550";
        String cata = "-s-";
        
        testCodes( brand );
        testCodes( brand, subBrand );
        testCodes( brand, subBrand, series );
        testCodes( brand, subBrand, series, cata );
    }

    @Test
    public void testSplitAndWildCode() {
        String brand = "RW-";
        String subBrand = "SH-";
        String series = "550";
        String cata = "-s-";
        
        testWildCodes( brand );
        testWildCodes( brand, subBrand );
        testWildCodes( brand, subBrand, series );
        testWildCodes( brand, subBrand, series, cata );
    }

    @Test
    public void testSplitAndWildVehicleCode() {
        String brand = "RW-";
        String subBrand = "SH-";
        String series = "550";
        String cata = "-s-";
        String car = "MAT";
        
        testWildVehicleCodes( brand, subBrand, series, cata, car );
    }

    @Test
    public void testMaintenanceTypeVerifyByKm() {
        
        int lastKm = 0; // 0 km
        int periodInKm = 10000; // 10k km
        
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
            lastKm, 1, periodInKm ) == DAOConstants.MAINTENANCE_TYPE_LOW );
        
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
            lastKm, 1000, periodInKm ) == DAOConstants.MAINTENANCE_TYPE_LOW );
        
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
            lastKm, 2300, periodInKm ) == DAOConstants.MAINTENANCE_TYPE_LOW );
        
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
            lastKm, 3500, periodInKm ) == DAOConstants.MAINTENANCE_TYPE_LOW );
        
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
            lastKm, 5100, periodInKm ) == DAOConstants.MAINTENANCE_TYPE_LOW );
        
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
            lastKm, 5300, periodInKm ) == DAOConstants.MAINTENANCE_TYPE_LOW );
        
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
            lastKm, 6800, periodInKm ) == DAOConstants.MAINTENANCE_TYPE_MEDIUM );
        
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
            lastKm, 7100, periodInKm ) == DAOConstants.MAINTENANCE_TYPE_MEDIUM );
        
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
            lastKm, 7500, periodInKm ) == DAOConstants.MAINTENANCE_TYPE_MEDIUM );
        
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
            lastKm, 7501, periodInKm ) == DAOConstants.MAINTENANCE_TYPE_HIGH );
        
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
            lastKm, 7550, periodInKm ) == DAOConstants.MAINTENANCE_TYPE_HIGH );
        
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
            lastKm, 10000, periodInKm ) == DAOConstants.MAINTENANCE_TYPE_HIGH );
        
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
            lastKm, 10010, periodInKm ) == DAOConstants.MAINTENANCE_TYPE_HIGH );
    }

    @Test
    public void testMaintenanceTypeVerifyByDay() {
        
        int lastDays = 0; // 0 days
        int periodInDays = 730; // two years
        
        // ~3 months
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
           lastDays, 120, periodInDays ) == DAOConstants.MAINTENANCE_TYPE_LOW );
        
        // ~5 months
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
           lastDays, 150, periodInDays ) == DAOConstants.MAINTENANCE_TYPE_LOW );
        
        // ~8 months
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
           lastDays, 240, periodInDays ) == DAOConstants.MAINTENANCE_TYPE_LOW );
        
        // ~12 months
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
           lastDays, 365, periodInDays ) == DAOConstants.MAINTENANCE_TYPE_LOW );
        
        // ~15 months
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
           lastDays, 385, periodInDays ) == DAOConstants.MAINTENANCE_TYPE_LOW );
        
        // ~18 months
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
           lastDays, 535, periodInDays ) == DAOConstants.MAINTENANCE_TYPE_MEDIUM );
        
        // ~18 months
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
           lastDays, 545, periodInDays ) == DAOConstants.MAINTENANCE_TYPE_MEDIUM );
        
        // ~19 months
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
           lastDays, 571, periodInDays ) == DAOConstants.MAINTENANCE_TYPE_HIGH );
        
        // ~21 months
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
           lastDays, 620, periodInDays ) == DAOConstants.MAINTENANCE_TYPE_HIGH );
        
        // ~24 months
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
           lastDays, 730, periodInDays ) == DAOConstants.MAINTENANCE_TYPE_HIGH );
        
        // ~25 months
        assertTrue( MaintenanceUtils.maintenanceTypeVerify( 
           lastDays, 730, periodInDays ) == DAOConstants.MAINTENANCE_TYPE_HIGH );
    }
    
    @Test
    public void testIsPeriodMaintenanceFitted() {
        
        int lastKm = 0; // 0 km
        int periodInKm = 5000; // 10k km
        
        assertFalse( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastKm, 1, periodInKm ) );
        
        assertFalse( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastKm, 1500, periodInKm ) );
        
        assertFalse( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastKm, 3000, periodInKm ) );
        
        assertFalse( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastKm, 3500, periodInKm ) );
        
        assertFalse( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastKm, 4100, periodInKm ) );
        
        assertTrue( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastKm, 4500, periodInKm ) );
        
        assertTrue( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastKm, 4700, periodInKm ) );
        
        assertTrue( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastKm, 5000, periodInKm ) );
        
        assertTrue( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastKm, 5010, periodInKm ) );
        
        
        int lastDays = 0; // 0 days
        int periodInDays = 730; // 2 years
        
        // < 1 month
        assertFalse( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastDays, 1, periodInDays ) );
        
        // ~1 month
        assertFalse( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastDays, 30, periodInDays ) );
        
        // ~10 month
        assertFalse( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastDays, 305, periodInDays ) );
        
        // ~12 month
        assertFalse( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastDays, 365, periodInDays ) );
        
        // ~13 month
        assertFalse( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastDays, 390, periodInDays ) );
        
        // ~17 month
        assertFalse( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastDays, 510, periodInDays ) );
        
        // ~20 month
        assertFalse( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastDays, 610, periodInDays ) );
        
        // ~22 month
        assertTrue( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastDays, 660, periodInDays ) );
        
        // ~24 month
        assertTrue( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastDays, 729, periodInDays ) );
        
        // ~24 month
        assertTrue( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastDays, 749, periodInDays ) );
        
        // ~25 month
        assertTrue( MaintenanceUtils
                .isPeriodMaintenanceFitted( lastDays, 760, periodInDays ) );
        
    }
    
    @Test
    public void testMaintenanceTypeVerifyPeriodically() {
        int periodValue = 10000; // 10k km
        
        assertTrue( DAOConstants.MAINTENANCE_TYPE_LOW ==
                MaintenanceUtils.maintenanceTypeVerifyPeriodically(
                            1, periodValue) );
        
        assertTrue( DAOConstants.MAINTENANCE_TYPE_LOW ==
                MaintenanceUtils.maintenanceTypeVerifyPeriodically(
                            50, periodValue) );
        
        assertTrue( DAOConstants.MAINTENANCE_TYPE_LOW ==
                MaintenanceUtils.maintenanceTypeVerifyPeriodically(
                            5000, periodValue) );
        
        assertTrue( DAOConstants.MAINTENANCE_TYPE_MEDIUM ==
                MaintenanceUtils.maintenanceTypeVerifyPeriodically(
                            7500, periodValue) );
        
        assertTrue( DAOConstants.MAINTENANCE_TYPE_HIGH ==
                MaintenanceUtils.maintenanceTypeVerifyPeriodically(
                            8500, periodValue) );
        
        assertTrue( DAOConstants.MAINTENANCE_TYPE_HIGH ==
                MaintenanceUtils.maintenanceTypeVerifyPeriodically(
                            10000, periodValue) );
        
        assertTrue( DAOConstants.MAINTENANCE_TYPE_LOW ==
                MaintenanceUtils.maintenanceTypeVerifyPeriodically(
                            10001, periodValue) );
        
        assertTrue( DAOConstants.MAINTENANCE_TYPE_LOW ==
                MaintenanceUtils.maintenanceTypeVerifyPeriodically(
                            15000, periodValue) );
    }
    
    protected void testCodes( String... codes ) {
        StringBuilder sbd = new StringBuilder();
        for( String code : codes ) {
            sbd.append( code );
        }
        
        String[] ss = MaintenanceUtils.splitCode( sbd.toString() );
        assertNotNull( ss );
        assertTrue( ss.length == codes.length );
        for( int i = 0; i < codes.length; i++ ) {
            assertEquals( ss[i], codes[i] );
        }
    }

    protected void testWildCodes( String... codes ) {
        StringBuilder sbd = new StringBuilder();
        for( String code : codes ) {
            sbd.append( code );
        }
        
        String[] ss = MaintenanceUtils.splitAndWildCode( sbd.toString() );
        assertNotNull( ss );
        assertTrue( ss.length == codes.length );
        String prefix = "";
        for( int i = 0; i < codes.length; i++ ) {
            prefix = prefix + codes[i];
            assertEquals( ss[ i ], prefix + DAOConstants.CODE_WILDCARD );
        }
    }

    protected void testWildVehicleCodes( String... codes ) {
        StringBuilder sbd = new StringBuilder();
        for( String code : codes ) {
            sbd.append( code );
        }
        
        String[] ss = MaintenanceUtils.splitAndWildVehicleCode( sbd.toString() );
        assertNotNull( ss );
        assertTrue( ss.length == codes.length - 1 );
        String prefix = "";
        for( int i = 0; i < codes.length - 1; i++ ) {
            prefix = prefix + codes[i];
            assertEquals( ss[ i ], prefix + DAOConstants.CODE_WILDCARD );
        }
    }
    
}
