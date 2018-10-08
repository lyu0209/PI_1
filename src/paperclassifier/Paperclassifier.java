/**
 * This project is used to identify the author address of papers CSE, including
 * <list> distinguish the College of Controll Science and Engineering, Zhejiang University (CSE) from all the addresses.
 * <list> identify the location of CSE in all addresses.
 * <list> distinguish CSE from all the correspondence author addresses.
 * 
 * v1.0 can identify SCI and EI <p>
 * 
 * the paper type must be selected before running the project, input the file address
 * the location of CSE and the correspondence author address will be written after running
 * but some paper still need to be identified manually
 * 
 * The project maintains an address list (Addresses.xls), containing three Sheets, which are
 * <list> useful --- useful list, all addresses are CSE
 * <list> useless --- useless list, all addressed are not CSE
 * <list> undetermined --- undetermined list, cannot identify whether it belongs to CSE, most common addresses are the key laboratory
 * 
 * All SCI papers of Zhejiang University are exported from WOS and saved in xls format. 
 * Sheet must be named "Sheet1".
 * suggestion for searching SCI papers:
 * <list> set the first searching term as follow: Orgnization-Enhances - "Zhejiang University"
 * <list> set the second searching term as follow: address - "control"
 * <list> two searching terms are "and" relationship
 * 
 * Before running, the first two columns of the excel files must be empty
 * the first column will be used to record the location of CSE, "非控制论文" or "第几单位"
 * the second column will be used to record if the CSE is the correspondence author address <p>
 *
 */
package paperclassifier;


/**
 * @version 1.0
 * @author 于玲
 */
public class Paperclassifier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // new MainWindow();
        // new FileSelector();
        new ReminderWindow();
    }
    

}
