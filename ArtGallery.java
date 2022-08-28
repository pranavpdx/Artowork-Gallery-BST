//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: The file This class models the Artwork Gallery implemented as a binary search tree. The
// search criteria include the year of creation of the artwork, the name of the artwork and its cost
// 
//
// Author: Pranav Sharma, Pujan Patel
// Email: pnsharma@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None used
// Online Sources: None used
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class models the Artwork Gallery implemented as a binary search tree. The search criteria
 * include the year of creation of the artwork, the name of the artwork and its cost.
 * 
 * @author Pranav Sharma
 * @author Pujan Patel
 *
 */
public class ArtGallery {
  // Complete the TODO tags in this source file

  public BSTNode<Artwork> root; // root node of the artwork catalog BST
  private int size; // size of the artwork catalog tree

  /**
   * Checks whether this binary search tree (BST) is empty
   * 
   * @return true if this ArtworkGallery is empty, false otherwise
   */
  public boolean isEmpty() {
    // TODO complete the implementation of this method
    if (root == null || size == 0) {
      return true;
    }
    return false; // Default return statement added to resolve compiler errors
  }

  /**
   * Returns the number of artwork pieces stored in this BST.
   * 
   * @return the size of this ArtworkGallery
   */
  public int size() {
    // TODO complete the implementation of this method
    return size; // Default return statement added to resolve compiler errors
  }

  /**
   * Checks whether this ArtworkGallery contains a Artwork given its name, year, and cost.
   * 
   * @param name name of the Artwork to search
   * @param year year of creation of the Artwork to search
   * @param cost cost of the Artwork to search
   * @return true if there is a match with this Artwork in this BST, and false otherwise
   */
  public boolean lookup(String name, int year, double cost) {
    // Hint: create a new artwork with the provided name and year and default cost and use it in the
    // search operation
    Artwork searchArtwork = new Artwork(name, year, cost);
    return lookupHelper(searchArtwork, root);

  }

  /**
   * Recursive helper method to search whether there is a match with a given Artwork in the subtree
   * rooted at current
   * 
   * @param target  a reference to a Artwork we are searching for a match in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return true if match found and false otherwise
   */
  protected static boolean lookupHelper(Artwork target, BSTNode<Artwork> current) {
    // TODO complete the implementation of this method
    if (current == null) {
      return false;
    }
    if (current.getData().equals(target)) {
      return true;
    }
    if (current.getData().compareTo(target) < 0) {
      return lookupHelper(target, current.getRight());
    } else if (current.getData().compareTo(target) > 0) {
      return lookupHelper(target, current.getLeft());
    }
    return false;

  }

  /**
   * Adds a new artwork piece to this ArtworkGallery
   * 
   * @param newArtwork a new Artwork to add to this BST (gallery of artworks).
   * @return true if the newArtwork was successfully added to this gallery, and returns false if
   *         there is a match with this Artwork already stored in gallery.
   * @throws NullPointerException if newArtwork is null
   */
  public boolean addArtwork(Artwork newArtwork) {
    if (newArtwork == null) {
      throw new NullPointerException("newArtwork is null");
    }
    if (root == null) {
      root = new BSTNode<Artwork>(newArtwork);
      size++;
      return true;
    } else {
      boolean isAdded = addArtworkHelper(newArtwork, root);
      if (isAdded) {
        size++;
      }
      return isAdded;
    }

  }

  /**
   * Recursive helper method to add a new Artwork to an ArtworkGallery rooted at current.
   * 
   * @param current    The "root" of the subtree we are inserting new Artwork into.
   * @param newArtwork The Artwork to be added to a BST rooted at current.
   * @return true if the newArtwork was successfully added to this ArtworkGallery, false if a match
   *         with newArtwork is already present in the subtree rooted at current.
   */
  protected static boolean addArtworkHelper(Artwork newArtwork, BSTNode<Artwork> current) {
    // TODO complete the implementation of this method
    if (lookupHelper(newArtwork, current)) {
      return false;
    }
    if (current.getData().compareTo(newArtwork) < 0) {
      if (current.getRight() == null) {
        current.setRight(new BSTNode<>(newArtwork));
        return true;
      } else {
        return addArtworkHelper(newArtwork, current.getRight());
      }
    } else {
      if (current.getLeft() == null) {
        current.setLeft(new BSTNode<>(newArtwork));
        return true;
      } else {
        return addArtworkHelper(newArtwork, current.getLeft());
      }
    }
  }

  /**
   * Gets the recent best Artwork in this BST (meaning the largest artwork in this gallery)
   * 
   * @return the best (largest) Artwork (the most recent, highest cost artwork) in this
   *         ArtworkGallery, and null if this tree is empty.
   */
  public Artwork getBestArtwork() {
    BSTNode<Artwork> temp = root;
    BSTNode<Artwork> tempTracker = null;
    while (temp != null) {
      tempTracker = temp;
      temp = temp.getRight();
    }
    if(tempTracker == null) {
      return null;
    }
    return tempTracker.getData();
  }

  /**
   * Returns a String representation of all the artwork stored within this BST in the increasing
   * order of year, separated by a newline "\n". For instance
   * 
   * "[(Name: Stars, Artist1) (Year: 1988) (Cost: $300.0)]" + "\n" + "[(Name: Sky, Artist1) (Year:
   * 2003) (Cost: $550.0)]" + "\n"
   * 
   * @return a String representation of all the artwork stored within this BST sorted in an
   *         increasing order with respect to the result of Artwork.compareTo() method (year, cost,
   *         name). Returns an empty string "" if this BST is empty.
   */
  @Override
  public String toString() {
    return toStringHelper(root).trim();
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a ArtworkGallery is provided in the
   * description of the above toString() method.
   * 
   * @param current reference to the current Artwork within this BST (root of a subtree)
   * @return a String representation of all the artworks stored in the sub-tree rooted at current in
   *         increasing order with respect to the result of Artwork.compareTo() method (year, cost,
   *         name). Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Artwork> current) {
    // TODO complete the implementation of this method
    String output = "";
    if (current != null) {
      output += toStringHelper(current.getLeft());
      output += "[(Name: " + current.getData().getName() + ") (Year: " + current.getData().getYear()
          + ") (Cost: $" + current.getData().getCost() + ")]" + "\n";
      output += toStringHelper(current.getRight());
    }
    return output; // Default return statement added to resolve compiler errors
  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current counting the
   * number of nodes and NOT the number of edges from current to the deepest leaf
   * 
   * @param current pointer to the current BSTNode within a ArtworkGallery (root of a subtree)
   * @return height of the subtree rooted at current
   */
  protected static int heightHelper(BSTNode<Artwork> current) {
    // TODO complete the implementation of this method
    // Base case:
    if (current == null) {
      return 0;
    }
    return Math.max(heightHelper(current.getLeft()), heightHelper(current.getRight())) + 1;
  }

  /**
   * Search for all artwork objects created on a given year and have a maximum cost value.
   * 
   * @param year creation year of artwork
   * @param cost the maximum cost we would like to search for a artwork
   * @return a list of all the artwork objects whose year equals our lookup year key and maximum
   *         cost. If no artwork satisfies the lookup query, this method returns an empty arraylist
   */
  public ArrayList<Artwork> lookupAll(int year, double cost) {
    return lookupAllHelper(year, cost, root);
  }

  /**
   * Recursive helper method to lookup the list of artworks given their year of creation and a
   * maximum value of cost
   * 
   * @param year    the year we would like to search for a artwork
   * @param cost    the maximum cost we would like to search for a artwork
   * @param current "root" of the subtree we are looking for a match to find within it.
   * @return a list of all the artwork objects whose year equals our lookup year key and maximum
   *         cost stored in the subtree rooted at current. If no artwork satisfies the lookup query,
   *         this method returns an empty arraylist
   */
  protected static ArrayList<Artwork> lookupAllHelper(int year, double cost,
      BSTNode<Artwork> current) {
    ArrayList<Artwork> array = new ArrayList<>();
    if (current == null) {
      return array;
    }
    if (current.getData().getYear() == year && current.getData().getCost() <= cost) {
      array.add(current.getData());
      array.addAll(lookupAllHelper(year, cost, current.getLeft()));
      array.addAll(lookupAllHelper(year, cost, current.getRight()));
      return array;
    }
    array.addAll(lookupAllHelper(year, cost, current.getLeft()));
    array.addAll(lookupAllHelper(year, cost, current.getRight()));
    return array;
  }

  /**
   * Buy an artwork with the specified name, year and cost. In terms of BST operation, this is
   * equivalent to finding the specific node and deleting it from the tree
   * 
   * @param name name of the artwork, artist
   * @param year creation year of artwork
   * @throws a NoSuchElementException with a descriptive error message if there is no Artwork found
   *           with the buying criteria
   */

  public void buyArtwork(String name, int year, double cost) {
    Artwork artwork = new Artwork(name, year, cost);
    root = buyArtworkHelper(artwork, root);
    size--;
  }

  /**
   * Recursive helper method to buy artwork given the name, year and cost. In terms of BST
   * operation, this is equivalent to finding the specific node and deleting it from the tree
   * 
   * @param target  a reference to a Artwork we are searching to remove in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return the new "root" of the subtree we are checking after trying to remove target
   * @throws a NoSuchElementException with a descriptive error message if there is no Artwork found
   *           with the buying criteria in the BST rooted at current
   */
  protected static BSTNode<Artwork> buyArtworkHelper(Artwork target, BSTNode<Artwork> current) {
    // TODO complete the implementation of this method. Problem decomposition and hints are provided
    // in the comments below

    // if current == null (empty subtree rooted at current), no match found, throw an exception

    if (current == null) {
      throw new NoSuchElementException(
          "no Artwork found with the buying criteria in the BST rooted at current");
    }
    // Compare the
    /*
     * target to the data at current and proceed accordingly // Recurse on the left or right subtree
     * with respect to the comparison result // Make sure to use the output of the recursive call to
     * appropriately set the left or the // right child of current accordingly if
     * (current.getData().compareTo(target) == 1) { return buyArtworkHelper(target,
     * current.getLeft()); } else if (current.getData().compareTo(target) == -1) { return
     * buyArtworkHelper(target, current.getRight()); }else { if(current.getLeft() == null) { return
     * current.getRight(); }else if(current.getRight() == null) { return current.getLeft(); }else {
     * current =
     * 
     * } }
     */
    // attempt 2
    BSTNode<Artwork> parent = null;
    BSTNode<Artwork> temp = current;
    while (temp != null && !temp.getData().equals(target)) {
      // update the parent to the current node
      parent = temp;

      // if the given key is less than the current node, go to the left subtree;
      // otherwise, go to the right subtree
      if (temp.getData().compareTo(target) == 1) {
        temp = temp.getLeft();
      } else if (temp.getData().compareTo(target) == -1) {
        temp = temp.getRight();
      }
    }
    // no children
    if (temp != null) {
      if (temp.getLeft() == null && temp.getRight() == null) {
        // its the root
        if (temp == current) {
          temp = null;
        } else {

          if (parent.getLeft() == temp) {
            parent.setLeft(null);
          } else {
            parent.setRight(null);
          }

        }
      }
      // node to be deleted has two children
      else if (temp.getLeft() != null && temp.getRight() != null) {
        // find its inorder successor node
        BSTNode<Artwork> successor =
            new BSTNode<Artwork>(getSuccessor(temp.getRight()), null, null);

        // store successor value
        Artwork newTarget = getSuccessor(temp.getRight());

        // recursively delete the successor. Note that the successor
        // will have at most one child (right child)
        buyArtworkHelper(newTarget, current);

        // copy value of the successor to the current node
        current = new BSTNode<Artwork>(newTarget, current.getLeft(), current.getRight());
      } else {
        // choose a child node
        BSTNode<Artwork> child = (temp.getLeft() != null) ? temp.getLeft() : temp.getRight();

        // if the node to be deleted is not a root node, set its parent
        // to its child
        if (temp != current) {
          if (temp == parent.getLeft()) {
            parent.setLeft(child);
          } else {
            parent.setRight(child);
          }
        }

        // if the node to be deleted is a root node, then set the root to the child
        else {
          current = child;
        }
      }
    } else {
      throw new NoSuchElementException(
          "no Artwork found with the buying criteria in the BST rooted at current");
    }
    return current;
    // if match with target found, three cases should be considered. Feel free to organize the order
    // of these cases at your choice.

    // current may be a leaf (has no children), set current to null.

    // current may have only one child, set current to that child (whether left or right child)

    // current may have two children,
    // Replace current with a new BSTNode whose data field value is the successor of target in the
    // tree, and having the same left and right children as current. Notice carefully that you
    // cannot
    // set the data of a BSTNode.
    // The successor is the smallest element at the right subtree of current
    // Then, remove the successor from the right subtree. The successor must have up to one child.

    // Make sure to return current (the new root to this subtree) at the end of each case or at
    // the end of the method.

  }

  /**
   * Helper method to find the successor of a node while performing a delete operation (buyArtwork)
   * The successor is defined as the smallest key in the right subtree. We assume by default that
   * node is not null
   * 
   * @param node node whose successor is to be found in the tree
   * @return return the key of the successor node
   */

  protected static Artwork getSuccessor(BSTNode<Artwork> node) {

    // TODO complete the implementation of this method
    BSTNode<Artwork> temp = node.getRight();
    if (temp == null) {
      return node.getData();
    }
    while (temp.getLeft() != null) {
      temp = temp.getLeft();
    }
    return temp.getData(); // Default return statement added to resolve compiler errors
  }
}
