
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project.tree;
import java.util.ArrayList;
/**
 *
 * @author Pacos
 */
public class TreeNode {
    public String ID;
    public String Symbol;
    public String Value;
    public Boolean Anulable;
    public String Name;
    public String Type;
    public TreeNode Child1;
    public TreeNode Child2;
    public ArrayList<TreeNode> first;
    public ArrayList<TreeNode> last;
    
    public TreeNode() {
        this.Symbol = " ";
        this.Anulable = false;
        this.first = new ArrayList<>();
        this.last = new ArrayList<>();
        this.Name = null;
        this.Child1 = null;
        this.Child2 = null;
        this.Type = "";
    }
}