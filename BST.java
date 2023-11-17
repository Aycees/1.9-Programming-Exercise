import java.io.PrintWriter;

public class BST {
    
    BSTNode root;

    // Method to insert a node
    public void insert(Software data) {
        root = insertRec(root, data);
    }
    
    private BSTNode insertRec(BSTNode root, Software data) {
        if (root == null) {
            root = new BSTNode(data);
            return root;
        }
    
        if (data.getName().compareTo(root.data.getName()) < 0 || (data.getName().compareTo(root.data.getName()) == 0 && data.getVersion().compareTo(root.data.getVersion()) < 0)) {
            root.left = insertRec(root.left, data);
        } else if (data.getName().compareTo(root.data.getName()) > 0 || (data.getName().compareTo(root.data.getName()) == 0 && data.getVersion().compareTo(root.data.getVersion()) > 0)) {
            root.right = insertRec(root.right, data);
        }
    
        return root;
    }

    // Method to do Inorder traversal of BST
    void inorder()  {
       inorderRec(root);
    }

    // A utility function to do inorder traversal of BST
    void inorderRec(BSTNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.printf("%-30s %-15s %-15s %-10s\n", root.data.getName(), root.data.getVersion(), root.data.getQuantity(), root.data.getPrice());
            inorderRec(root.right);
        }
    }
    
    public Boolean bolsearch(String name, String version) {
        return bolsearch(root, name, version);
    }
    
    public Boolean bolsearch(BSTNode root, String name, String version) {
        while (root != null) {
            if (name.compareToIgnoreCase(root.data.getName()) == 0 && version.compareToIgnoreCase(root.data.getVersion()) == 0) {
                return true;
            } else if (name.compareToIgnoreCase(root.data.getName()) < 0 || (name.compareToIgnoreCase(root.data.getName()) == 0 && version.compareToIgnoreCase(root.data.getVersion()) < 0)) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return false;
    }

    public BSTNode search(String name, String version) {
        return searchRec(root, name, version);
    }
    
    private BSTNode searchRec(BSTNode root, String name, String version) {
        if (root == null || (root.data.getName().equals(name) && root.data.getVersion().equals(version))) {
            return root;
        }
    
        if (root.data.getName().compareTo(name) < 0) {
            return searchRec(root.right, name, version);
        }
    
        return searchRec(root.left, name, version);
    }
    

    public void selected(String name, String version, int quantity){
        selected(root, name, version, quantity);
    }
    
    public void selected(BSTNode root, String name, String version, int quantity) {
        while (root != null) {
            if (name.compareToIgnoreCase(root.data.getName()) == 0 && version.compareToIgnoreCase(root.data.getVersion()) == 0) {
                System.out.printf("%-30s %-10s %-10s\n", root.data.getName(), root.data.getVersion(), quantity);
                break;
            } else if (name.compareToIgnoreCase(root.data.getName()) < 0 || (name.compareToIgnoreCase(root.data.getName()) == 0 && version.compareToIgnoreCase(root.data.getVersion()) < 0)) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
    }

    public void buySoftware(String name, String version, int quantity) {
        buySoftware(root, name, version, quantity);
    }

    private void buySoftware(BSTNode root, String name, String version, int quantity) {
        if (root != null) {
            int compareResult = name.compareToIgnoreCase(root.data.getName());
            if (compareResult == 0) {
                compareResult = version.compareToIgnoreCase(root.data.getVersion());
                if (compareResult == 0) {
                    // Found the software with matching name and version, now deduct the quantity
                    int currentQuantity = root.data.getQuantity();
                    if (currentQuantity >= quantity) {
                        root.data.setQuantity(currentQuantity - quantity);
                        System.out.println("Purchase confirmed!");
                    } else {
                        System.out.println("Not enough quantity available for purchase.");
                    }
                } else if (compareResult < 0) {
                    buySoftware(root.left, name, version, quantity);
                    System.out.println("Purchase confirmed!");
                } else {
                    buySoftware(root.right, name, version, quantity);
                    System.out.println("Purchase confirmed!");
                }
            } else if (compareResult < 0) {
                buySoftware(root.left, name, version, quantity);
            } else {
                buySoftware(root.right, name, version, quantity);
            }
        } else {
            System.out.println("Software not found.");
        }
    }

    public void sellSoftware(String name, String version, int quantity) {
        sellSoftware(root, name, version, quantity);
    }

    private void sellSoftware(BSTNode root, String name, String version, int quantity) {
        if (root != null) {
            int compareResult = name.compareToIgnoreCase(root.data.getName());
            if (compareResult == 0) {
                compareResult = version.compareToIgnoreCase(root.data.getVersion());
                if (compareResult == 0) {
                    // Found the software with matching name and version, now add the quantity
                    int currentQuantity = root.data.getQuantity();
                    root.data.setQuantity(currentQuantity + quantity);
                } else if (compareResult < 0) {
                    sellSoftware(root.left, name, version, quantity);
                } else {
                    sellSoftware(root.right, name, version, quantity);
                }
            } else if (compareResult < 0) {
                sellSoftware(root.left, name, version, quantity);
            } else {
                sellSoftware(root.right, name, version, quantity);
            }
        } else {
            System.out.println("Software not found.");
        }
    }

    public void inorderPrint(PrintWriter pw) {
        inorderPrintRec(root, pw);
    }
    
    private void inorderPrintRec(BSTNode root, PrintWriter pw) {
        if (root != null) {
            inorderPrintRec(root.left, pw);
            pw.println(root.data.getName());
            pw.println(root.data.getVersion());
            pw.println(root.data.getQuantity());  
            pw.println(root.data.getPrice());
            inorderPrintRec(root.right, pw);
        }
    }

    public void delete(String name, String version) {
        root = deleteRec(root, name, version);
    }
    
    private BSTNode deleteRec(BSTNode root, String name, String version) {
        if (root == null) {
            return root;
        }
    
        if (name.compareTo(root.data.getName()) < 0 || (name.compareTo(root.data.getName()) == 0 && version.compareTo(root.data.getVersion()) < 0)) {
            root.left = deleteRec(root.left, name, version);
        } else if (name.compareTo(root.data.getName()) > 0 || (name.compareTo(root.data.getName()) == 0 && version.compareTo(root.data.getVersion()) > 0)) {
            root.right = deleteRec(root.right, name, version);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                Software minValue = findMin(root.right);
                root.data = minValue;
                root.right = deleteRec(root.right, root.data.getName(), root.data.getVersion());
            }
        }
    
        return root;
    }
    
    private Software findMin(BSTNode root) {
        if (root.left != null) {
            return findMin(root.left);
        }
        return root.data;
    }

    public BSTNode searchName(String name) {
        return searchRecursive(root, name);
    }
    
    private BSTNode searchRecursive(BSTNode root, String name) {
        if (root == null || root.data.getName().equals(name)) {
            return root;
        }
    
        if (root.data.getName().compareTo(name) < 0) {
            return searchRecursive(root.right, name);
        }
    
        return searchRecursive(root.left, name);
    }

    public int getQuantity(String name, String version) {
        BSTNode node = search(name, version);
        if (node != null) {
            return node.data.getQuantity();
        } else {
            return -1; // Return -1 or any suitable value to indicate that the software was not found
        }
    }
}