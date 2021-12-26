package com.sigmundgranaas.forgero.item.instance;

import com.sigmundgranaas.forgero.Forgero;
import com.sigmundgranaas.forgero.core.tool.ForgeroTool;
import com.sigmundgranaas.forgero.item.instance.tool.ForgeroPickaxeInstance;
import com.sigmundgranaas.forgero.item.instance.tool.ForgeroToolInstance;
import com.sigmundgranaas.forgero.item.tool.ForgeroToolItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ForgeroToolInstanceFactoryImpl implements ForgeroToolInstanceFactory {
    public static ForgeroToolInstanceFactory INSTANCE;

    public NBTFactory factory;

    public ForgeroToolInstanceFactoryImpl(NBTFactory factory) {
        this.factory = factory;

    }

    public static ForgeroToolInstanceFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ForgeroToolInstanceFactoryImpl(com.sigmundgranaas.forgero.item.instance.NBTFactory.INSTANCE);
        }
        return INSTANCE;
    }

    @Override
    public @NotNull
    ForgeroToolInstance createForgeroToolInstance(@NotNull ForgeroToolItem baseTool, @NotNull NbtCompound compound) {
        ForgeroTool forgeroTool = NBTFactory.INSTANCE.createToolFromNBT(baseTool, compound);
        return createInstance(forgeroTool);
    }

    @Override
    public @NotNull ForgeroToolInstance createForgeroToolInstance(ForgeroTool tool) {
        return switch (tool.getToolType()) {
            case PICKAXE -> new ForgeroPickaxeInstance(tool);
            case SHOVEL -> null;
            case SWORD -> null;
        };
    }

    private ForgeroToolInstance createInstance(ForgeroTool tool) {
        return switch (tool.getToolType()) {
            case PICKAXE -> new ForgeroPickaxeInstance(tool);
            case SHOVEL -> null;
            case SWORD -> null;
        };
    }

    @Override
    public @NotNull
    ForgeroToolInstance CreateForgeroBaseToolInstance(ForgeroToolItem tool) {
        return createInstance(tool.getTool());
    }

    @Override
    public @NotNull
    Optional<ForgeroToolInstance> CreateForgeroBaseToolInstance(ItemStack stack) {
        if (stack == null) {
            Forgero.LOGGER.warn("Cannot create Tool, as the ItemStack is null");
            return Optional.empty();
        }
        Item baseItem = stack.getItem();

        @Nullable
        NbtCompound itemStackCompound = stack.getNbt();
        if (!(baseItem instanceof ForgeroTool) || itemStackCompound == null || itemStackCompound.isEmpty()) {
            Forgero.LOGGER.warn("Cannot create Tool, as the ItemStack is not a ForgeroTool, the NbtCompound is null or it is empty, returning empty");
            return Optional.empty();

        }

        return Optional.of(createForgeroToolInstance((ForgeroToolItem) baseItem, itemStackCompound));
    }
}
