package ua.in.sz;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.ObjectGraphIterator;

import java.util.ArrayList;
import java.util.Iterator;

@Slf4j
public class TestObjectGraphIterator {

    public static void main(String[] args) {
        Forest forest = new Forest();
        forest.addTree();
        forest.addTree();
        forest.addTree();
        Branch b1 = forest.getTree(1).addBranch();
        Branch b2 = forest.getTree(1).addBranch();
        Branch b3 = forest.getTree(2).addBranch();
        Branch b4 = forest.getTree(2).addBranch();
        forest.getTree(2).addBranch();
        b1.addLeaf();
        b1.addLeaf();
        b2.addLeaf();
        b3.addLeaf();
        b4.addLeaf();

//        Transformer<Object, Object> transformer = s -> s;
        Transformer<Object, Object> transformer = new LeafFinder();
        Iterator<Object> it = new ObjectGraphIterator<>(forest, transformer);

        while (it.hasNext()) {
            Object object = it.next();
            log.info("Object: {}", object);
        }
    }

    // ================================================================================================================
    // Transformer
    // ================================================================================================================

    static class LeafFinder implements Transformer<Object, Object> {
        public Object transform(Object input) {
            if (input instanceof Iterable) {
                return ((Iterable<?>) input).iterator();
            }
            return input;
        }
    }

    // ================================================================================================================
    // classes
    // ================================================================================================================

    static class Forest extends ArrayList<Tree> {
        Tree addTree() {
            add(new Tree());
            return getTree(size() - 1);
        }

        Tree getTree(int index) {
            return get(index);
        }
    }

    static class Tree extends ArrayList<Branch> {
        Branch addBranch() {
            add(new Branch());
            return getBranch(size() - 1);
        }

        Branch getBranch(int index) {
            return get(index);
        }
    }

    static class Branch extends ArrayList<Leaf> {
        Leaf addLeaf() {
            add(new Leaf());
            return getLeaf(size() - 1);
        }

        Leaf getLeaf(int index) {
            return get(index);
        }
    }

    static class Leaf {
        String colour;

        String getColour() {
            return colour;
        }

        void setColour(String colour) {
            this.colour = colour;
        }
    }
}