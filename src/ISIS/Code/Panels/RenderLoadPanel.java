package ISIS.Code.Panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderLoadPanel extends DefaultTableCellRenderer {

		private Color background;
		private Color foreground;
		private Font font;

		public RenderLoadPanel() {
			super();
		}

		public RenderLoadPanel(Color background, Color foreground) {
			super();
			this.background = background;
			this.foreground = foreground;
		}

		public RenderLoadPanel(Color background, Color foreground, Font font) {
			this.background = background;
			this.foreground = foreground;
			this.font = font;
		}

		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			Component comp = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
			comp.setBackground(background);
			comp.setForeground(foreground);
			comp.setFont(font);
			return comp;
		}

}