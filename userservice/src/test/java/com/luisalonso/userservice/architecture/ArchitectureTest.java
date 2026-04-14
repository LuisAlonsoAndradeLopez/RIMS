package com.luisalonso.userservice.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.luisalonso.userservice")
public class ArchitectureTest {

    @ArchTest
    static final ArchRule layeredArchitectureRule = layeredArchitecture()

            .consideringOnlyDependenciesInLayers()

            .layer("Controller")
            .definedBy("..controller..")

            .layer("Service")
            .definedBy("..service..")

            .layer("Repository")
            .definedBy("..repository..")

            .layer("Mapper")
            .definedBy("..mapper..")

            .layer("DTO")
            .definedBy("..dto..")

            .layer("Entity")
            .definedBy("..entity..")

            .whereLayer("Controller")
            .mayOnlyAccessLayers("Service", "DTO")

            .whereLayer("Service")
            .mayOnlyAccessLayers("Repository", "Mapper", "DTO")

            .whereLayer("Mapper")
            .mayOnlyAccessLayers("DTO", "Entity")

            .whereLayer("Repository")
            .mayOnlyAccessLayers("Entity");

}
