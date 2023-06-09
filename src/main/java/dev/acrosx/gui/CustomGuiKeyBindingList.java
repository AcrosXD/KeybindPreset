package dev.acrosx.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
    @SideOnly(Side.CLIENT)
    public class CustomGuiKeyBindingList extends GuiListExtended {
        private final CustomGuiControls field_148191_k;
        private final Minecraft mc;
        private final GuiListExtended.IGuiListEntry[] listEntries;
        private int maxListLabelWidth = 0;

        public CustomGuiKeyBindingList(CustomGuiControls controls, Minecraft mcIn) {
            super(mcIn, controls.width, controls.height, 63, controls.height - 32, 20);
            this.field_148191_k = controls;
            this.mc = mcIn;
            KeyBinding[] akeybinding = (KeyBinding[]) ArrayUtils.clone(mcIn.gameSettings.keyBindings);
            this.listEntries = new GuiListExtended.IGuiListEntry[akeybinding.length + KeyBinding.getKeybinds().size()];
            Arrays.sort((Object[]) akeybinding);
            int i = 0;
            String s = null;

            for (KeyBinding keybinding : akeybinding) {
                String s1 = keybinding.getKeyCategory();

                if (!s1.equals(s)) {
                    s = s1;
                    this.listEntries[i++] = new CustomGuiKeyBindingList.CategoryEntry(s1);
                }

                int j = mcIn.fontRendererObj.getStringWidth(I18n.format(keybinding.getKeyDescription(), new Object[0]));

                if (j > this.maxListLabelWidth) {
                    this.maxListLabelWidth = j;
                }

                this.listEntries[i++] = new CustomGuiKeyBindingList.KeyEntry(keybinding);
            }
        }

        protected int getSize() {
            return this.listEntries.length;
        }

        /**
         * Gets the IGuiListEntry object for the given index
         */
        public GuiListExtended.IGuiListEntry getListEntry(int index) {
            return this.listEntries[index];
        }

        protected int getScrollBarX() {
            return super.getScrollBarX() + 15;
        }

        /**
         * Gets the width of the list
         */
        public int getListWidth() {
            return super.getListWidth() + 32;
        }

        @SideOnly(Side.CLIENT)
        public class CategoryEntry implements GuiListExtended.IGuiListEntry {
            private final String labelText;
            private final int labelWidth;

            public CategoryEntry(String p_i45028_2_) {
                this.labelText = I18n.format(p_i45028_2_, new Object[0]);
                this.labelWidth = CustomGuiKeyBindingList.this.mc.fontRendererObj.getStringWidth(this.labelText);
            }

            public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
                CustomGuiKeyBindingList.this.mc.fontRendererObj.drawString(this.labelText, CustomGuiKeyBindingList.this.mc.currentScreen.width / 2 - this.labelWidth / 2, y + slotHeight - CustomGuiKeyBindingList.this.mc.fontRendererObj.FONT_HEIGHT - 1, 16777215);
            }

            /**
             * Returns true if the mouse has been pressed on this control.
             */
            public boolean mousePressed(int slotIndex, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
                return false;
            }

            /**
             * Fired when the mouse button is released. Arguments: index, x, y, mouseEvent, relativeX, relativeY
             */
            public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
            }

            public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {
            }
        }

        @SideOnly(Side.CLIENT)
        public class KeyEntry implements GuiListExtended.IGuiListEntry {
            /**
             * The keybinding specified for this KeyEntry
             */
            private final KeyBinding keybinding;
            /**
             * The localized key description for this KeyEntry
             */
            private final String keyDesc;
            private final GuiButton btnChangeKeyBinding;
            private final GuiButton btnReset;

            private KeyEntry(KeyBinding p_i45029_2_) {
                this.keybinding = p_i45029_2_;
                this.keyDesc = I18n.format(p_i45029_2_.getKeyDescription(), new Object[0]);
                this.btnChangeKeyBinding = new GuiButton(0, 0, 0, 75, 20, I18n.format(p_i45029_2_.getKeyDescription(), new Object[0]));
                this.btnReset = new GuiButton(0, 0, 0, 50, 20, I18n.format("controls.reset", new Object[0]));
            }

            public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
                boolean flag = CustomGuiKeyBindingList.this.field_148191_k.buttonId == this.keybinding;
                CustomGuiKeyBindingList.this.mc.fontRendererObj.drawString(this.keyDesc, x + 90 - CustomGuiKeyBindingList.this.maxListLabelWidth, y + slotHeight / 2 - CustomGuiKeyBindingList.this.mc.fontRendererObj.FONT_HEIGHT / 2, 16777215);
                this.btnReset.xPosition = x + 190;
                this.btnReset.yPosition = y;
                this.btnReset.enabled = this.keybinding.getKeyCode() != this.keybinding.getKeyCodeDefault();
                this.btnReset.drawButton(CustomGuiKeyBindingList.this.mc, mouseX, mouseY);
                this.btnChangeKeyBinding.xPosition = x + 105;
                this.btnChangeKeyBinding.yPosition = y;
                this.btnChangeKeyBinding.displayString = GameSettings.getKeyDisplayString(this.keybinding.getKeyCode());
                boolean flag1 = false;

                if (this.keybinding.getKeyCode() != 0) {
                    for (KeyBinding keybinding : CustomGuiKeyBindingList.this.mc.gameSettings.keyBindings) {
                        if (keybinding != this.keybinding && keybinding.getKeyCode() == this.keybinding.getKeyCode()) {
                            flag1 = true;
                            break;
                        }
                    }
                }

                if (flag) {
                    this.btnChangeKeyBinding.displayString = EnumChatFormatting.WHITE + "> " + EnumChatFormatting.YELLOW + this.btnChangeKeyBinding.displayString + EnumChatFormatting.WHITE + " <";
                } else if (flag1) {
                    this.btnChangeKeyBinding.displayString = EnumChatFormatting.RED + this.btnChangeKeyBinding.displayString;
                }

                this.btnChangeKeyBinding.drawButton(CustomGuiKeyBindingList.this.mc, mouseX, mouseY);
            }

            /**
             * Returns true if the mouse has been pressed on this control.
             */
            public boolean mousePressed(int slotIndex, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
                if (this.btnChangeKeyBinding.mousePressed(CustomGuiKeyBindingList.this.mc, p_148278_2_, p_148278_3_)) {
                    CustomGuiKeyBindingList.this.field_148191_k.buttonId = this.keybinding;
                    return true;
                } else if (this.btnReset.mousePressed(CustomGuiKeyBindingList.this.mc, p_148278_2_, p_148278_3_)) {
                    CustomGuiKeyBindingList.this.mc.gameSettings.setOptionKeyBinding(this.keybinding, this.keybinding.getKeyCodeDefault());
                    KeyBinding.resetKeyBindingArrayAndHash();
                    return true;
                } else {
                    return false;
                }
            }

            /**
             * Fired when the mouse button is released. Arguments: index, x, y, mouseEvent, relativeX, relativeY
             */
            public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
                this.btnChangeKeyBinding.mouseReleased(x, y);
                this.btnReset.mouseReleased(x, y);
            }

            public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {
            }
        }
    }