package com.sigmundgranaas.forgero.client.forgerotool.model;

import com.sigmundgranaas.forgero.core.tool.ForgeroToolTypes;
import com.sigmundgranaas.forgero.core.toolpart.ForgeroToolPart;
import com.sigmundgranaas.forgero.core.toolpart.head.ToolPartHead;

import java.util.Locale;

/**
 * Enum containing all possible model types. Each model should have a corresponding templateTexture
 */
public enum ToolPartModelType {
    PICKAXEHEAD,
    SHOVELHEAD,
    AXEHEAD,

    HANDLE,
    FULLHANDLE,
    MEDIUMHANDLE,
    SHORTHANDLE,

    BINDING,
    PICKAXEBINDING,
    AXEHEADBINDING,
    SHOVELBINDING;

    @SuppressWarnings("DuplicateBranchesInSwitch")
    public static ToolPartModelType getModelType(ForgeroToolPart toolPart) {
        return switch (toolPart.getToolPartType()) {
            case HEAD -> switch (((ToolPartHead) toolPart).getToolType()) {
                case PICKAXE -> PICKAXEHEAD;
                case SHOVEL -> SHOVELHEAD;
                case AXE -> AXEHEAD;
                case SWORD -> PICKAXEHEAD;
            };
            case HANDLE -> HANDLE;
            case BINDING -> BINDING;
        };
    }

    public static ToolPartModelType getModelType(ForgeroToolPart toolPart, ForgeroToolTypes toolType) {
        return switch (toolType) {
            case PICKAXE -> switch (toolPart.getToolPartType()) {
                case HEAD -> PICKAXEHEAD;
                case HANDLE -> FULLHANDLE;
                case BINDING -> PICKAXEBINDING;
            };
            case AXE -> switch (toolPart.getToolPartType()) {
                case HEAD -> AXEHEAD;
                case HANDLE -> MEDIUMHANDLE;
                case BINDING -> AXEHEADBINDING;
            };
            case SHOVEL -> switch (toolPart.getToolPartType()) {
                case HEAD -> SHOVELHEAD;
                case HANDLE -> MEDIUMHANDLE;
                case BINDING -> SHOVELBINDING;
            };
            case SWORD -> PICKAXEHEAD;
        };
    }

    public static boolean isModelIdentifier(String[] identifier) {
        if (identifier.length > 1) {
            for (ToolPartModelType value : ToolPartModelType.values()) {
                if (value.name().toLowerCase().equals(identifier[1].toLowerCase(Locale.ROOT))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isItemModelIdentifier(String[] identifier) {
        if (identifier.length == 2) {
            for (ToolPartModelType value : ToolPartModelType.values()) {
                if (value.name().toLowerCase().equals(identifier[1].toLowerCase(Locale.ROOT))) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toFileName() {
        return this.toString().toLowerCase(Locale.ROOT);
    }
}