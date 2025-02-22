package com.sigmundgranaas.forgero.item.implementation;

import com.sigmundgranaas.forgero.core.ForgeroRegistry;
import com.sigmundgranaas.forgero.core.gem.EmptyGem;
import com.sigmundgranaas.forgero.core.gem.Gem;
import com.sigmundgranaas.forgero.core.identifier.ForgeroIdentifierFactory;
import com.sigmundgranaas.forgero.core.identifier.tool.ForgeroMaterialIdentifierImpl;
import com.sigmundgranaas.forgero.core.material.material.PrimaryMaterial;
import com.sigmundgranaas.forgero.core.material.material.SecondaryMaterial;
import com.sigmundgranaas.forgero.core.schematic.HeadSchematic;
import com.sigmundgranaas.forgero.core.schematic.Schematic;
import com.sigmundgranaas.forgero.core.tool.ForgeroTool;
import com.sigmundgranaas.forgero.core.tool.ForgeroToolTypes;
import com.sigmundgranaas.forgero.core.tool.ForgeroToolWithBinding;
import com.sigmundgranaas.forgero.core.tool.factory.ForgeroToolFactory;
import com.sigmundgranaas.forgero.core.toolpart.ForgeroToolPart;
import com.sigmundgranaas.forgero.core.toolpart.ForgeroToolPartTypes;
import com.sigmundgranaas.forgero.core.toolpart.binding.ToolPartBinding;
import com.sigmundgranaas.forgero.core.toolpart.factory.ForgeroToolPartFactory;
import com.sigmundgranaas.forgero.core.toolpart.factory.ToolPartBuilder;
import com.sigmundgranaas.forgero.core.toolpart.handle.ToolPartHandle;
import com.sigmundgranaas.forgero.core.toolpart.head.ToolPartHead;
import com.sigmundgranaas.forgero.item.ForgeroToolItem;
import com.sigmundgranaas.forgero.item.NBTFactory;
import com.sigmundgranaas.forgero.item.ToolPartItem;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class NBTFactoryImpl implements NBTFactory {

    public static NBTFactory INSTANCE;
    private final Map<String, ForgeroTool> toolCache;

    public NBTFactoryImpl() {
        this.toolCache = new HashMap<>();
    }

    public static NBTFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NBTFactoryImpl();
        }
        return INSTANCE;
    }

    public static String createGemNbtString(Gem gem) {
        return String.format("%s_%s", gem.getLevel(), gem.getIdentifier());
    }

    @Override
    public @NotNull
    ForgeroToolPart createToolPartFromNBT(@NotNull NbtCompound compound) {
        String primaryMaterialString = compound.getString(ToolPartItem.PRIMARY_MATERIAL_IDENTIFIER);
        PrimaryMaterial primary = (PrimaryMaterial) ForgeroRegistry.getInstance().materialCollection().getMaterial(ForgeroIdentifierFactory.INSTANCE.createForgeroMaterialIdentifier(primaryMaterialString));
        String secondaryMaterialString = compound.getString(ToolPartItem.SECONDARY_MATERIAL_IDENTIFIER);

        String gemString = compound.getString(NBTFactory.GEM_NBT_IDENTIFIER);


        String toolPartTypeIdentifier = compound.getString(NBTFactory.TOOL_PART_TYPE_NBT_IDENTIFIER);
        ForgeroToolPartTypes toolPartTypes = ForgeroToolPartTypes.valueOf(toolPartTypeIdentifier.toUpperCase(Locale.ROOT));
        String patternIdentifier;
        ForgeroToolTypes toolType = ForgeroToolTypes.PICKAXE;
        if (compound.contains(NBTFactory.TOOL_PART_HEAD_TYPE_NBT_IDENTIFIER)) {
            String toolTypeIdentifier = compound.getString(NBTFactory.TOOL_PART_HEAD_TYPE_NBT_IDENTIFIER);
            toolType = ForgeroToolTypes.valueOf(toolTypeIdentifier.toUpperCase(Locale.ROOT));
        }

        if (compound.contains(NBTFactory.SCHEMATIC_NBT_IDENTIFIER)) {
            patternIdentifier = compound.getString(NBTFactory.SCHEMATIC_NBT_IDENTIFIER);
        } else {
            if (toolPartTypes == ForgeroToolPartTypes.HEAD) {
                patternIdentifier = String.format("%s%s_pattern_default", toolType.getToolName(), toolPartTypeIdentifier.toLowerCase(Locale.ROOT));
            } else {
                patternIdentifier = String.format("%s_pattern_default", toolPartTypeIdentifier.toLowerCase(Locale.ROOT));
            }

        }


        Schematic pattern = ForgeroRegistry.getInstance().schematicCollection().getSchematics().stream().filter(element -> element.getSchematicIdentifier().equals(patternIdentifier)).findFirst().get();

        ToolPartBuilder builder = switch (toolPartTypes) {
            case HANDLE -> ForgeroToolPartFactory.INSTANCE.createToolPartHandleBuilder(primary, pattern);
            case BINDING -> ForgeroToolPartFactory.INSTANCE.createToolPartBindingBuilder(primary, pattern);
            case HEAD -> ForgeroToolPartFactory.INSTANCE.createToolPartHeadBuilder(primary, (HeadSchematic) pattern);
        };
        if (!secondaryMaterialString.equals("empty")) {
            builder.setSecondary((SecondaryMaterial) ForgeroRegistry.getInstance().materialCollection().getMaterial(new ForgeroMaterialIdentifierImpl(secondaryMaterialString)));
        }


        if (!gemString.equals("")) {
            builder.setGem(getGemFromNbtString(gemString));
        }


        return builder.createToolPart();
    }

    @Override
    public @NotNull
    ForgeroTool createToolFromNBT(@NotNull ForgeroToolItem baseTool, @NotNull NbtCompound compound) {
        NbtCompound toolCompound;
        if (compound.contains(NBTFactory.FORGERO_TOOL_NBT_IDENTIFIER)) {
            toolCompound = compound.getCompound(NBTFactory.FORGERO_TOOL_NBT_IDENTIFIER);
        } else {
            toolCompound = compound;
        }
        assert toolCompound != null;

        Optional<String> hash = Optional.of(toolCompound).map(toolCompounds -> toolCompounds.getString(NBTFactory.HASH_NBT_IDENTIFIER));
        if (hash.isPresent() && toolCache.containsKey(hash.get())) {
            return toolCache.get(hash.get());
        }
        ToolPartHead head;
        ToolPartHandle handle;

        if (toolCompound.contains(ToolPartItem.HEAD_IDENTIFIER)) {
            head = (ToolPartHead) createToolPartFromNBT(toolCompound.getCompound(ToolPartItem.HEAD_IDENTIFIER));
        } else {
            head = baseTool.getHead();
        }

        if (toolCompound.contains(ToolPartItem.HANDLE_IDENTIFIER)) {
            handle = (ToolPartHandle) createToolPartFromNBT(toolCompound.getCompound(ToolPartItem.HANDLE_IDENTIFIER));
        } else {
            handle = baseTool.getHandle();
        }

        Optional<ToolPartBinding> binding = Optional.empty();
        if (toolCompound.contains(ToolPartItem.BINDING_IDENTIFIER)) {
            binding = Optional.of((ToolPartBinding) createToolPartFromNBT(toolCompound.getCompound(ToolPartItem.BINDING_IDENTIFIER)));
        }
        ForgeroTool tool;
        if (binding.isPresent()) {
            tool = ForgeroToolFactory.INSTANCE.createForgeroTool(head, handle, binding.get());
        } else {
            tool = ForgeroToolFactory.INSTANCE.createForgeroTool(head, handle);
        }
        return tool;
    }

    @Override
    public @NotNull
    NbtCompound createNBTFromTool(@NotNull ForgeroTool baseTool) {
        NbtCompound baseCompound = new NbtCompound();
        baseCompound.put(HEAD_NBT_IDENTIFIER, createNBTFromToolPart(baseTool.getToolHead()));
        baseCompound.put(HANDLE_NBT_IDENTIFIER, createNBTFromToolPart(baseTool.getToolHandle()));
        if (baseTool instanceof ForgeroToolWithBinding) {
            baseCompound.put(BINDING_NBT_IDENTIFIER, createNBTFromToolPart(((ForgeroToolWithBinding) baseTool).getBinding()));
        }
        String hash = UUID.randomUUID().toString();
        baseCompound.putString(HASH_NBT_IDENTIFIER, hash);
        toolCache.put(hash, baseTool);
        return baseCompound;
    }

    @Override
    public @NotNull
    NbtCompound createNBTFromToolPart(@NotNull ForgeroToolPart toolPart) {
        NbtCompound baseCompound = new NbtCompound();
        baseCompound.putString(PRIMARY_MATERIAL_NBT_IDENTIFIER, toolPart.getPrimaryMaterial().getName());
        baseCompound.putString(SECONDARY_MATERIAL_NBT_IDENTIFIER, toolPart.getSecondaryMaterial().getName());
        baseCompound.putString(GEM_NBT_IDENTIFIER, createGemNbtString(toolPart.getGem()));
        baseCompound.putString(TOOL_PART_TYPE_NBT_IDENTIFIER, toolPart.getToolPartType().toString());
        baseCompound.putString(TOOL_PART_IDENTIFIER, toolPart.getToolPartIdentifier());
        baseCompound.putString(SCHEMATIC_NBT_IDENTIFIER, toolPart.getSchematic().getSchematicIdentifier());
        if (toolPart.getToolPartType() == ForgeroToolPartTypes.HEAD) {
            baseCompound.putString(TOOL_PART_HEAD_TYPE_NBT_IDENTIFIER, ((ToolPartHead) toolPart).getToolType().toString());
        }
        return baseCompound;
    }

    @Override
    public @NotNull
    Gem createGemFromNbt(@NotNull NbtCompound compound) {
        String gemString = compound.getString(NBTFactory.GEM_NBT_IDENTIFIER);
        if (!gemString.equals("")) {
            return getGemFromNbtString(gemString);
        } else {
            return EmptyGem.createEmptyGem();
        }
    }

    @Override
    public @NotNull
    NbtCompound createNBTFromGem(@NotNull Gem gem, NbtCompound compound) {
        compound.putString(NBTFactoryImpl.GEM_NBT_IDENTIFIER, createGemNbtString(gem));
        compound.putFloat("CustomModelData", gem.getLevel());
        return compound;
    }

    Gem getGemFromNbtString(String nbtGem) {
        String[] elements = nbtGem.split("_");
        if (elements.length < 3) {
            return EmptyGem.createEmptyGem();
        }
        Gem gem = ForgeroRegistry.getInstance().gemCollection().getGems().stream().filter(gem1 -> gem1.getIdentifier().equals(String.format("%s_%s", elements[1], elements[2]))).findFirst().orElse(EmptyGem.createEmptyGem());
        return gem.createGem(Integer.parseInt(elements[0]));
    }

}

