package com.sigmundgranaas.forgero.core.schematic;

import com.sigmundgranaas.forgero.core.property.Property;
import com.sigmundgranaas.forgero.core.tool.ForgeroToolTypes;
import com.sigmundgranaas.forgero.core.toolpart.ForgeroToolPartTypes;

import java.util.List;

public class HeadSchematic extends Schematic {
    private final ForgeroToolTypes toolType;

    public HeadSchematic(ForgeroToolPartTypes type, String name, List<Property> properties, ForgeroToolTypes toolType, int rarity, String model, int materialCount) {
        super(type, name, properties, rarity, model, materialCount);
        this.toolType = toolType;
    }

    @Override
    public String getSchematicIdentifier() {
        return String.format("%shead_schematic_%s", toolType.getToolName(), getName());
    }

    public ForgeroToolTypes getToolType() {
        return toolType;
    }
}
