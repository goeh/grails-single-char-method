package issue

/**
 * An entity with single-character field names.
 */
class PersonLink {
    Person a
    Person b

    String toString() {
        "$a -> $b"
    }
}
