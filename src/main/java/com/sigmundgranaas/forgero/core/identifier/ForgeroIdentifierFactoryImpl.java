package com.sigmundgranaas.forgero.core.identifier;

import com.sigmundgranaas.forgero.client.forgerotool.model.ModelLayer;
import com.sigmundgranaas.forgero.client.forgerotool.model.ToolPartModelType;
import com.sigmundgranaas.forgero.core.gem.Gem;
import com.sigmundgranaas.forgero.core.identifier.model.ForgeroModelIdentifier;
import com.sigmundgranaas.forgero.core.identifier.tool.*;
import com.sigmundgranaas.forgero.core.material.material.SecondaryMaterial;
import com.sigmundgranaas.forgero.core.tool.ForgeroToolTypes;
import com.sigmundgranaas.forgero.core.toolpart.ForgeroToolPart;
import net.minecraft.util.Identifier;

import java.util.stream.Stream;

public class ForgeroIdentifierFactoryImpl implements ForgeroIdentifierFactory {
    private static ForgeroIdentifierFactoryImpl INSTANCE;

    public static ForgeroIdentifierFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ForgeroIdentifierFactoryImpl();
        }
        return INSTANCE;
    }

    @Override
    public ForgeroIdentifier createForgeroIdentifier(Identifier identifier) {
        String removePath = Stream.of(identifier.getPath().split("/"))
                .reduce((first, second) -> second)
                .orElse("")
                .replace(".png", "")
                .replace("#inventory", "");
        return createForgeroIdentifier(removePath);
    }

    @Override
    public ForgeroIdentifier createForgeroIdentifier(String identifier) {
        return createForgeroIdentifierFromName(identifier);
    }

    @Override
    public ForgeroMaterialIdentifier createForgeroMaterialIdentifier(String identifier) {
        String[] elements = identifier.split("_");
        return new ForgeroMaterialIdentifierImpl(elements[0]);
    }

    @Override
    public ForgeroModelIdentifier createToolPartModelIdentifier(ForgeroToolPart toolPart) {
        return new ForgeroModelIdentifier(toolPart.getPrimaryMaterial().getName(), ToolPartModelType.getModelType(toolPart), ModelLayer.PRIMARY, toolPart.getSchematic().getModel());
    }

    @Override
    public ForgeroModelIdentifier createToolPartModelIdentifier(ForgeroToolTypes toolType, ForgeroToolPart toolPart) {
        return new ForgeroModelIdentifier(toolPart.getPrimaryMaterial().getName(), ToolPartModelType.getModelType(toolPart, toolType), ModelLayer.PRIMARY, toolPart.getSchematic().getModel());
    }

    @Override
    public ForgeroModelIdentifier createToolPartModelIdentifier(ForgeroToolPart toolPart, SecondaryMaterial
            secondaryMaterial) {
        return new ForgeroModelIdentifier(secondaryMaterial.getName(), ToolPartModelType.getModelType(toolPart), ModelLayer.SECONDARY, toolPart.getSchematic().getModel());
    }

    @Override
    public ForgeroModelIdentifier createToolPartModelIdentifier(ForgeroToolTypes toolType, ForgeroToolPart
            toolPart, SecondaryMaterial secondaryMaterial) {
        return new ForgeroModelIdentifier(secondaryMaterial.getName(), ToolPartModelType.getModelType(toolPart, toolType), ModelLayer.SECONDARY, toolPart.getSchematic().getModel());
    }

    @Override
    public ForgeroModelIdentifier createToolPartModelIdentifier(Gem gem, ForgeroToolPart part, ToolPartModelType type) {
        return new ForgeroModelIdentifier(gem.getName(), type, ModelLayer.GEM, part.getSchematic().getModel());
    }

    private ForgeroIdentifier createForgeroIdentifierFromName(String forgeroName) {
        String[] elements = forgeroName.split("_");
        if (elements.length == 1) {
            return new ForgeroMaterialIdentifierImpl(forgeroName);
        } else if (ForgeroToolTypes.isTool(elements[1])) {
            return new ForgeroToolIdentifierImpl(forgeroName);
        } else if (elements.length == 3) {
            return createForgeroToolPartIdentifier(forgeroName);
        }
        throw new IllegalStateException("Unexpected value: " + elements.length);
    }

    private ForgeroIdentifier createForgeroToolPartIdentifier(String forgeroName) {
        if (forgeroName.contains("head")) {
            return new ForgeroToolPartHeadIdentifier(forgeroName);
        } else {
            return new ForgeroToolPartIdentifierImpl(forgeroName);
        }
    }
}
