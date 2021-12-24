package com.sigmundgranaas.forgero.material.material.factory;

import com.sigmundgranaas.forgero.material.material.ForgeroMaterial;
import com.sigmundgranaas.forgero.material.material.MaterialPOJO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaterialFactoryImplTest {


    @Test
    void createMaterial() {
        MaterialFactoryImpl factory = new MaterialFactoryImpl();
        ForgeroMaterial material = factory.createMaterial(new MaterialPOJO());

        Assertions.assertEquals("default", material.getName());
    }
}