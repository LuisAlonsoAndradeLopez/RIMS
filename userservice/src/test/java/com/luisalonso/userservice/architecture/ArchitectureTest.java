package com.luisalonso.userservice.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.luisalonso.userservice")
public class ArchitectureTest {

    @ArchTest
    static final ArchRule layeredArchitectureRule = layeredArchitecture()

            .consideringAllDependencies()

            .layer("Controller")
            .definedBy("..controller..")

            .layer("Service")
            .definedBy("..service..")

            .layer("Repository")
            .definedBy("..repository..")

            .whereLayer("Controller")
            .mayOnlyAccessLayers("Service")

            .whereLayer("Service")
            .mayOnlyAccessLayers("Repository");

}
