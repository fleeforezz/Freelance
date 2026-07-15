package views;

public class UI {
    
    /*
        ###############################
        Header Title
        ###############################
     */
    public static void titleHeader(String title, int length) {

        border(length);
        System.out.printf(
                "| %-10s                                     |\n", title
        );
        border(length);
    }

    /*
        ###############################
        Table Header
        ###############################
     */
    public static void tableHeader(String[] columns, int[] widths) {
        StringBuilder header = new StringBuilder();
        
        // Columns
        header.append("|");

        int totalWidth = 1; // first |
        
        for (int i = 0; i < columns.length; i++) {
            header.append(
                    String.format(" %-" + widths[i] + "s |" , columns[i])
            );

            totalWidth += widths[i] + 4;
        }
        
        border(totalWidth);
        System.out.println(header.toString());
        border(totalWidth);
    }

    /*
        ###############################
        Border
        ###############################
     */
    public static void border(int length) {
        
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
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
