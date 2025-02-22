package com.sigmundgranaas.forgero.core.tool;

import com.sigmundgranaas.forgero.core.identifier.tool.ForgeroToolIdentifier;
import com.sigmundgranaas.forgero.core.material.material.PrimaryMaterial;
import com.sigmundgranaas.forgero.core.property.Property;
import com.sigmundgranaas.forgero.core.property.PropertyStream;
import com.sigmundgranaas.forgero.core.property.Target;
import com.sigmundgranaas.forgero.core.toolpart.handle.ToolPartHandle;
import com.sigmundgranaas.forgero.core.toolpart.head.ToolPartHead;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ForgeroTool {
    @NotNull
    ToolPartHead getToolHead();

    @NotNull
    PrimaryMaterial getPrimaryMaterial();

    @NotNull
    ToolPartHandle getToolHandle();

    @NotNull
    ForgeroToolIdentifier getIdentifier();

    @NotNull
    String getShortToolIdentifierString();

    @NotNull
    String getToolIdentifierString();

    @NotNull
    ForgeroToolTypes getToolType();

    int getDurability(Target target);

    float getAttackDamage(Target target);

    float getAttackSpeed(Target target);

    float getMiningSpeedMultiplier(Target target);

    int getMiningLevel(Target target);

    PrimaryMaterial getMaterial();

    void createToolDescription(ToolDescriptionWriter writer);

    void createWeaponDescription(ToolDescriptionWriter writer);

    List<Property> getProperties(Target target);

    default PropertyStream getPropertyStream() {
        return Property.stream(getProperties(Target.createEmptyTarget()));
    }

    default PropertyStream getPropertyStream(Target target) {
        return Property.stream(getProperties(target));
    }
}
