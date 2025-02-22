package com.sigmundgranaas.forgero.core.toolpart.factory;

import com.sigmundgranaas.forgero.core.ForgeroRegistry;
import com.sigmundgranaas.forgero.core.gem.EmptyGem;
import com.sigmundgranaas.forgero.core.identifier.tool.ForgeroToolPartIdentifier;
import com.sigmundgranaas.forgero.core.material.MaterialCollection;
import com.sigmundgranaas.forgero.core.material.material.EmptySecondaryMaterial;
import com.sigmundgranaas.forgero.core.material.material.PrimaryMaterial;
import com.sigmundgranaas.forgero.core.schematic.HeadSchematic;
import com.sigmundgranaas.forgero.core.schematic.Schematic;
import com.sigmundgranaas.forgero.core.schematic.SchematicCollection;
import com.sigmundgranaas.forgero.core.toolpart.ForgeroToolPart;
import com.sigmundgranaas.forgero.core.toolpart.binding.Binding;
import com.sigmundgranaas.forgero.core.toolpart.binding.BindingState;
import com.sigmundgranaas.forgero.core.toolpart.binding.ToolPartBinding;
import com.sigmundgranaas.forgero.core.toolpart.handle.Handle;
import com.sigmundgranaas.forgero.core.toolpart.handle.HandleState;
import com.sigmundgranaas.forgero.core.toolpart.handle.ToolPartHandle;
import com.sigmundgranaas.forgero.core.toolpart.head.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ForgeroToolPartFactoryImpl implements ForgeroToolPartFactory {
    private static ForgeroToolPartFactory INSTANCE;

    public static ForgeroToolPartFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ForgeroToolPartFactoryImpl();
        }
        return INSTANCE;
    }

    @Override
    public @NotNull
    ForgeroToolPart createToolPart(@NotNull ForgeroToolPartIdentifier identifier) {
        PrimaryMaterial material = (PrimaryMaterial) ForgeroRegistry.getInstance().materialCollection().getMaterial(identifier.getMaterial());
        Schematic schematic = ForgeroRegistry.getInstance().schematicCollection().getSchematics().stream().filter((Schematic element) -> element.getSchematicIdentifier().equals(identifier.getSchematic().identifier())).findFirst().get();

        return switch (identifier.getToolPartType()) {
            case HEAD -> createToolPartHead(material, (HeadSchematic) schematic);
            case HANDLE -> new Handle(new HandleState(material, new EmptySecondaryMaterial(), EmptyGem.createEmptyGem(), schematic));
            case BINDING -> new Binding(new BindingState(material, new EmptySecondaryMaterial(), EmptyGem.createEmptyGem(), schematic));
        };
    }

    private ToolPartHead createToolPartHead(PrimaryMaterial material, @NotNull HeadSchematic schematic) {
        HeadState state = new HeadState(material, new EmptySecondaryMaterial(), EmptyGem.createEmptyGem(), schematic);

        return switch (schematic.getToolType()) {
            case PICKAXE -> new PickaxeHead(state);
            case SHOVEL -> new ShovelHead(state);
            case AXE -> new AxeHead(state);
            case SWORD -> new SwordHead(state);
            case HOE -> new HoeHead(state);
        };
    }

    @Override
    public @NotNull
    ToolPartHeadBuilder createToolPartHeadBuilder(@NotNull PrimaryMaterial material, HeadSchematic schematic) {
        return new ToolPartHeadBuilder(material, schematic);
    }

    @Override
    public @NotNull
    ToolPartHandleBuilder createToolPartHandleBuilder(@NotNull PrimaryMaterial material, @NotNull Schematic schematic) {
        return new ToolPartHandleBuilder(material, schematic);
    }

    @Override
    public @NotNull
    ToolPartBindingBuilder createToolPartBindingBuilder(@NotNull PrimaryMaterial material, @NotNull Schematic schematic) {
        return new ToolPartBindingBuilder(material, schematic);
    }

    @Override
    public @NotNull
    ToolPartBuilder createToolPartBuilderFromToolPart(@NotNull ForgeroToolPart toolPart) {
        return switch (toolPart.getToolPartType()) {
            case HEAD -> new ToolPartHeadBuilder((ToolPartHead) toolPart);
            case BINDING -> new ToolPartBindingBuilder((ToolPartBinding) toolPart);
            case HANDLE -> new ToolPartHandleBuilder((ToolPartHandle) toolPart);
        };
    }


    @Override
    public @NotNull
    List<ForgeroToolPart> createBaseToolParts(@NotNull MaterialCollection collection, SchematicCollection schematicCollection) {
        return collection
                .getPrimaryMaterialsAsList()
                .stream()
                .map(material -> schematicCollection
                        .getSchematics()
                        .stream()
                        .map(schematic -> createBaseToolPartsFromMaterial(material, schematic))
                        .flatMap(List::stream).toList())
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<ForgeroToolPart> createBaseToolPartsFromMaterial(PrimaryMaterial material, Schematic schematic) {
        List<ForgeroToolPart> toolParts = new ArrayList<>();
        switch (schematic.getType()) {
            case HEAD -> {
                HeadState state = new HeadState(material, new EmptySecondaryMaterial(), EmptyGem.createEmptyGem(), schematic);
                switch (((HeadSchematic) schematic).getToolType()) {
                    case AXE -> toolParts.add(new AxeHead(state));
                    case SHOVEL -> toolParts.add(new ShovelHead(state));
                    case PICKAXE -> toolParts.add(new PickaxeHead(state));
                    case SWORD -> toolParts.add(new SwordHead(state));
                    case HOE -> toolParts.add(new HoeHead(state));
                }
            }
            case HANDLE -> toolParts.add(new Handle(new HandleState(material, new EmptySecondaryMaterial(), EmptyGem.createEmptyGem(), schematic)));
            case BINDING -> toolParts.add(new Binding(new BindingState(material, new EmptySecondaryMaterial(), EmptyGem.createEmptyGem(), schematic)));
        }
        return toolParts;
    }
}