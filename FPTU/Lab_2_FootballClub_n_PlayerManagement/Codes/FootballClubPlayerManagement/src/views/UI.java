package views;

public class UI {
    
    /*
        ###############################
        Header Title
        ###############################
     */
    public static void titleHeader(String title) {
        String border = "----------------------------------------------------------------------";
        
        System.out.println(border);
        System.out.printf(
                "| %-10s                                  |\n", title
        );
        System.out.println(border);
    }

    /*
        ###############################
        Table Header
        ###############################
     */
    public static void tableHeader(String[] columns, int[] widths) {
        StringBuilder header = new StringBuilder();
        String border = "----------------------------------------------------------------------";
        
        // Top border
        header.append(border).append("\n");
        
        // Columns
        header.append("|");
        for (int i = 0; i < columns.length; i++) {
            header.append(String.format(" %-" + widths[i] + "s |" , columns[i]));
        }
        header.append("\n");
        
        // Bottom border
        header.append(border);
        
        System.out.println(header.toString());
    }

    /*
        ###############################
        Footer
        ###############################
     */
    public static void footer() {
        System.out.println(
                "----------------------------------------------------------------------\n"
        );
    }
    
    /*
        ###############################
        Error
        ###############################
     */
    public static void error(String errorMessage) {
        
        System.out.printf(
                "| %-10s                                         |\n", errorMessage
        );
    }
    
    /*
        ###############################
        Success
        ###############################
     */
    public static void success(String successMessage) {
        
        System.out.printf(
                "| %-10s                                         |\n", successMessage
        );
    }
}
