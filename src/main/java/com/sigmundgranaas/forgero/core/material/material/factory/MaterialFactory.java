package com.sigmundgranaas.forgero.core.material.material.factory;

import com.sigmundgranaas.forgero.core.material.material.ForgeroMaterial;
import com.sigmundgranaas.forgero.core.material.material.MaterialPOJO;

public interface MaterialFactory {
    MaterialFactory INSTANCE = MaterialFactoryImpl.getInstance();

    ForgeroMaterial createMaterial(MaterialPOJO material);
}
