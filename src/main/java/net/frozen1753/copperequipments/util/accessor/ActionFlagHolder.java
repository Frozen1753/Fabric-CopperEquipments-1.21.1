package net.frozen1753.copperequipments.util.accessor;

public interface ActionFlagHolder {
    boolean hasPlayedFlag(ActionType type);
    void setPlayedFlag(ActionType type, boolean value);
}

