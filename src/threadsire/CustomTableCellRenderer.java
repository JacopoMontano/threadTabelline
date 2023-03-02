package threadsire;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private int rowToColor;
    private int columnToColor;
    private Color backgroundColor;
    
    public CustomTableCellRenderer(int row, int column, Color backgroundColor) {
        this.rowToColor = row;
        this.columnToColor = column;
        this.backgroundColor = backgroundColor;
    }
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row == rowToColor && column == columnToColor) {
            cellComponent.setBackground(backgroundColor);
        }
        return cellComponent;
    }
}
