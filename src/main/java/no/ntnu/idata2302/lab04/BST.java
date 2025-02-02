package no.ntnu.idata2302.lab04;

public class BST {

    public static BST fromValues(int... values) {
        if (values.length < 1)
            throw new IllegalArgumentException("A binary search tree must have at least one value");
        if (values.length == 1)
            return new BST(values[0]);
        var tree = new BST(values[0]);
        for (int i = 1; i < values.length; i++) {
            tree.insert(values[i]);
        }
        return tree;
    }

    private int value;
    private BST left;
    private BST right;

    public BST(int value) {
        this.value = value;
    }

    public BST insert(int givenValue) {
        if (givenValue < value) {
            if (hasLeft()) {
                left.insert(givenValue);
            } else {
                left = new BST(givenValue);
            }
        } else if (givenValue > value) {
            if (hasRight()) {
                right.insert(givenValue);
            } else {
                right = new BST(givenValue);
            }
        }
        return this;
    }

    /**
     * @return the number of items in this tree
     */
    public int size() {
        int size = 1;
        if (hasLeft()) {
            size += left.size();
        }
        if (hasRight()) {
            size += right.size();
        }
        return size;
    }

    private boolean hasLeft() {
        return left != null;
    }

    private boolean hasRight() {
        return right != null;
    }

    /**
     * @return the minimum value in this tree
     */
    int minimum() {
        if (hasLeft()) {
            return left.minimum();
        }
        return value;
    }

    /**
     * @return the maximum value in this tree
     */
    int maximum() {
        if (hasRight()) {
            return right.maximum();
        }
        return value;
    }

    boolean contains(int givenValue) {
        if (value < givenValue) {
            if (hasRight()) {
                return right.contains(givenValue);
            }
            return false;

        } else if (value > givenValue) {
            if (hasLeft()) {
                return left.contains(givenValue);
            }
            return false;

        } else {
            return true;

        }
    }

    int successor(int givenValue) {
        if (value < givenValue) {
            if (hasRight()) {
                return right.successor(givenValue);

            }
            throw new NoSuchValue(givenValue);

        } else if (value > givenValue) {
            if (hasLeft()) {
                try {
                    return left.successor(givenValue);

                } catch (SuccessorNotFound error) {
                    return value;

                } catch (NoSuchValue error) {
                    return value;

                }
            }
            throw new NoSuchValue(givenValue);

        } else {
            if (hasRight()) {
                return right.minimum();

            }
            throw new SuccessorNotFound();
        }
    }

    BST delete(int givenValue) {
        if (givenValue < value) {
            if (hasLeft()) {
                left = left.delete(givenValue);
                return this;
            }
            throw new NoSuchValue(givenValue);
        } else if (givenValue > value) {
            if (hasRight()) {
                right = right.delete(givenValue);
                return this;
            }
            throw new NoSuchValue(givenValue);
        } else {
            if (hasLeft() && !hasRight()) {
                return left;
            }
            if (!hasLeft() && hasRight()) {
                return right;
            }
            if (hasLeft() && hasRight()) {
                try {
                    value = successor(value);

                } catch (SuccessorNotFound error) {
                    return null;

                }
                right.delete(value);
                return this;
            }
            return null;
        }
    }

    /**
     * Formats the tree as a string of ascending values, separated by commas.
     * @return the formatted string
     */
    public String format() {
        var formatted = new StringBuilder();
        if (hasLeft()) {
            formatted.append(left.format());
            formatted.append(", ");
        }
        formatted.append(value);
        if (hasRight()) {
            formatted.append(", ");
            formatted.append(right.format());
        }
        return formatted.toString();
    }

}

class NoSuchValue extends RuntimeException {

    private int value;

    public NoSuchValue(int givenValue) {
        super();
        this.value = givenValue;
    }
}

class SuccessorNotFound extends RuntimeException {
}

class PredecessorNotFound extends RuntimeException {
}
