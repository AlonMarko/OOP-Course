import java.util.Collection;

/**
 * Wraps an underlying Collection<String> and serves to both simplify its API and give it a common type
 * with the implemented SimpleHashSets
 */
public class CollectionFacadeSet implements SimpleSet {
    /*
    data members
     */
    // the warped object by this facade
    protected Collection<String> collection;

    /**
     * creates a new facade wrapping the specified collection
     *
     * @param collection - the collection to wrap
     */
    public CollectionFacadeSet(Collection<String> collection) {
        this.collection = collection;
    }

    @Override
    public boolean add(String newValue) {
        if (collection.contains(newValue)) {
            return false;
        } else {
            collection.add(newValue);
            return true;
        }
    }

    @Override
    public boolean contains(String searchVal) {
        return collection.contains(searchVal);
    }

    @Override
    public boolean delete(String toDelete) {
        if (collection.contains(toDelete)) {
            collection.remove(toDelete);
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return collection.size();
    }


}
